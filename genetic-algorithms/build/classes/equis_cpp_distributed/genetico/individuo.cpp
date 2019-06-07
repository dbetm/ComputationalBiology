#include "../bibliotecas.h"

class Individuo {
    private:
        int *genotipo;
        int fenotipo;
        int fitness;
        int n;
        int* generarGenotipoAleatorio();
        int calcularFenotipo();
    public:
        Individuo(int);
        Individuo(int, int *);
        ~Individuo();
        void actualizarIndividuo();
        int* getGenotipo();
        int getFenotipo();
        int getFitness();
        int getN();
};

Individuo::Individuo(int n) {
    this->n = n;
    this->genotipo = generarGenotipoAleatorio();
    // Calcular el fenotipo
    this->fenotipo = calcularFenotipo();
    this->fitness = this->fenotipo;
}

Individuo::Individuo(int n, int *genotipo) {
    this->n = n;
    this->genotipo = genotipo;
    // Calcular el fenotipo
    this->fenotipo = calcularFenotipo();
    this->fitness = this->fenotipo;
}

Individuo::~Individuo(){
    //if(this->genotipo != NULL) free(this->genotipo);
}

void Individuo::actualizarIndividuo() {
    this->fenotipo = calcularFenotipo();
    this->fitness = this->fenotipo;
}

int* Individuo::generarGenotipoAleatorio() {
    int *genotipo = new int[this->n];

    random_device rd;
    mt19937_64 gen(rd());
    uniform_int_distribution<unsigned int> dis;

    for(int i = 0; i < this->n; i++) {
        genotipo[i] = (dis(gen) % 2);
    }

    return genotipo;
}

int Individuo::calcularFenotipo() {
    // Convertir el arreglo de bits en décimal
    int ans = 0;
    int j = 0;
    for (int i = this->n - 1; i >= 0; i--, j++) {
        ans += this->genotipo[i] * pow(2, j);
    }

    return ans;
}

int* Individuo::getGenotipo() {
    return this->genotipo;
}

int Individuo::getFenotipo() {
    return this->fenotipo;
}

int Individuo::getFitness() {
    return this->fitness;
}

int Individuo::getN() {
    return this->n;
}
/*
int main(int argc, char const *argv[]) {
    int n = 12;
    Individuo ind(n);
    int *gen = ind.getGenotipo();

    for (int i = 0; i < n; i++) {
        cout << gen[i] << ", ";
    }
    cout << endl;

    cout << ind.getFenotipo() << endl;
    cout << ind.getFitness() << endl;

    return 0;
}
*/
