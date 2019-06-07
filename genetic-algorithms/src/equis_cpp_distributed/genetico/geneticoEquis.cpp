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
        void agregarPromesa(string);
        string obtenerPromesa();
        string obtenerFitness();
};

GeneticoEquis::GeneticoEquis(int numG, int tamP, double pMuta) {
    this->numG = numG;
    this->tamP = tamP;
    this->pMuta = pMuta;
}

GeneticoEquis::~GeneticoEquis() {
}

void GeneticoEquis::agregarPromesa(string gen) {
    int *genotipo = new int[30];
    for (int i = 0; i < 30; i++) {
        if(gen[i] == '1') genotipo[i] = 1;
        else genotipo[i] = 0;
    }
    Individuo ind(30, genotipo);
    this->pobActual->agregarIndiduo(ind);
    this->tamP++;
}

void GeneticoEquis::evolucionar() {
    int maxi = -1;

    using namespace std::this_thread; // sleep_for, sleep_until
    using namespace std::chrono; // nanoseconds, system_clock, seconds

    this->pobActual = new Poblacion(this->tamP);
    int *mask = Cruza::generarMascaraAleatoria(30);
    Individuo mejor(30);
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
        for(int x = 0; x < 30; x++) {
            if(x != 11)
                gen += to_string(mejor.getGenotipo()[x]) + ",";
            else
                gen += to_string(mejor.getGenotipo()[x]);
        }
        //cout << g << " = fit = " << mejor.getFitness() << " gen = "<<gen<<endl;
        maxi = max(mejor.getFitness(), maxi);
        pobActual = new Poblacion(ind);
        sleep_for(nanoseconds(10000));
        sleep_until(system_clock::now() + seconds(1));
    }
    //cout << "Máx es: " << maxi << endl;
}

string GeneticoEquis::obtenerPromesa() {
    Individuo mejor = pobActual->getMejor();
    string gen = "";
    for(int x = 0; x < 30; x++) {
        if(mejor.getGenotipo()[x] == 1) gen += "1";
        else gen += "0";
    }
    return to_string(mejor.getFitness()) + "#" + gen;
}
