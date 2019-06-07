#include "../bibliotecas.h"

class Poblacion {
    public:
        vector <Individuo> individuos;
        Poblacion(int n);
        Poblacion(vector <Individuo> ind);
        //vector<Individuo> getNMejores(int n);
        Individuo getMejor();
        vector <Individuo> getIndividuos();
        void agregarIndiduo(Individuo);
};

Poblacion::Poblacion(int n) {
    for(int i = 0; i < n ;i++) {
        this->individuos.push_back(Individuo(30));
    }
}

Poblacion::Poblacion(vector<Individuo> ind) {
    this->individuos = ind;
}

Individuo Poblacion::getMejor() {
    int idMejor = 0;
    int n = this->individuos[0].getN();

    for(unsigned int x = 1; x < this->individuos.size(); x++) {
        if(this->individuos[x].getFitness() > this->individuos[idMejor].getFitness()) {
            idMejor = x;
        }
    }
    return Individuo(n, this->individuos[idMejor].getGenotipo());
}

vector<Individuo> Poblacion::getIndividuos() {
    return this->individuos;
}

void Poblacion::agregarIndiduo(Individuo ind) {
    this->individuos.push_back(ind);
}
