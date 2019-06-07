#include "../bibliotecas.h"
/*
Hay un error, intentar con código:
https://rafalcieslak.wordpress.com/2014/05/16/c11-stdthreads-managed-by-a-designated-class/
*/


class GeneticoEquis {
    private:
        int numG;
        int tamP;
        double pMuta;
        Poblacion *pobActual;
    public:
        GeneticoEquis(int, int, double);
        void operator()() {
            evolucionar();
        }
        //GeneticoEquis(int, int, double) : the_thread();
        ~GeneticoEquis();
        //Metodo que comienza con el la evolucion
        void evolucionar();
        string obtenerPromesa();
};

GeneticoEquis::GeneticoEquis(int numG, int tamP, double pMuta) {
    this->numG = numG;
    this->tamP = tamP;
    this->pMuta = pMuta;
}

GeneticoEquis::~GeneticoEquis() {
}

void GeneticoEquis::evolucionar() {
    int maxi = -1;

    this->pobActual = new Poblacion(this->tamP);
    int *mask = Cruza::generarMascaraAleatoria(12);
    Individuo mejor(12);
    for(int g = 0; g < this->numG; g++) {
        vector<Individuo> ind;
        for(int i = 0; i < this->tamP; i++) {
            Individuo madre = Seleccion::seleccionTorneo(*pobActual);
            Individuo padre = Seleccion::seleccionAletoria(*pobActual);
            Individuo hijo = Cruza::cruzaXMascara(mask, madre, padre);
            Mutacion::mutaBit(this->pMuta, hijo);
            ind.push_back(hijo);
        }
        mejor  = pobActual->getMejor();
        string gen = "";
        for(int x = 0; x < 12; x++) {
            if(x != 11)
                gen += to_string(mejor.getGenotipo()[x]) + ",";
            else
                gen += to_string(mejor.getGenotipo()[x]);
        }
        //cout << g << " = fit = " << mejor.getFitness() << " gen = "<<gen<<endl;
        maxi = max(mejor.getFitness(), maxi);
        pobActual = new Poblacion(ind);
    }
    //cout << "Máx es: " << maxi << endl;
}

string GeneticoEquis::obtenerPromesa() {
    Individuo mejor = pobActual->getMejor();
    string gen = "";
    for(int x = 0; x < 12; x++) {
        if(x != 11)
            gen += to_string(mejor.getGenotipo()[x]) + ",";
        else
            gen += to_string(mejor.getGenotipo()[x]);
    }
    return to_string(pobActual->getMejor().getFitness()) + " " + gen;
}
