#include "../bibliotecas.h"

class Mutacion {
    public:
        static void mutaBit(double probabilidad, Individuo &individuo) {
            random_device rd;
            mt19937_64 gen(rd());
            uniform_int_distribution <unsigned int> dis;
            double prob = (dis(gen) % 10000)/10000;
            if(prob <= probabilidad) {
                int pos = dis(gen) % individuo.getN();
                if(individuo.getGenotipo()[pos] == 1) individuo.getGenotipo()[pos] = 0;
                else individuo.getGenotipo()[pos] = 1;
                individuo.actualizarIndividuo();
            }
        }
};
