#include "../bibliotecas.h"

class GeneticoEquis {
    public:
        int numG;
        int tamP;
        double pMuta;
        //Constructor por defecto carga una matriz a partir de un txt
        GeneticoEquis(int, int, double);
        //Metodo que comienza con el la evolucion
        void evolucionar();
};

GeneticoEquis::GeneticoEquis(int numG, int tamP, double pMuta){
    this->numG = numG;
    this->tamP = tamP;
    this->pMuta = pMuta;
}

void GeneticoEquis::evolucionar() {
    int maxi = -1;

    Poblacion pobActual = Poblacion(this->tamP);
    int *mask = Cruza::generarMascaraAleatoria(12);
    Individuo mejor(12);
    for(int g = 0; g < this->numG; g++) {
        vector<Individuo> ind;
        for(int i = 0; i < this->tamP; i++) {
            Individuo madre = Seleccion::seleccionTorneo(pobActual);
            Individuo padre = Seleccion::seleccionAletoria(pobActual);
            Individuo hijo = Cruza::cruzaXMascara(mask, madre, padre);
            Mutacion::mutaBit(this->pMuta, hijo);
            ind.push_back(hijo);
        }
        mejor  = pobActual.getMejor();
        string gen = "";
        for(int x = 0; x < 12; x++) {
            if(x != 11)
                gen += to_string(mejor.getGenotipo()[x]) + ",";
            else
                gen += to_string(mejor.getGenotipo()[x]);
        }
        cout << g << " = fit = " << mejor.getFitness() << " gen = "<<gen<<endl;
        maxi = max(mejor.getFitness(), maxi);
        pobActual = Poblacion(ind);
    }
    cout << "Máx es: " << maxi << endl;
}
