#include "../bibliotecas.h"

class Cruza {
    public:
        static int * generarMascaraAleatoria(int dim) {
            random_device rd;
            mt19937_64 gen(rd());
            uniform_int_distribution<unsigned int> dis;
            int* mask = new int[dim];
            for(int i = 0; i < dim; i++) {
                mask[i] = dis(gen) % 2;
            }
            return mask;
        }

        static Individuo cruzaXMascara(int * mask, Individuo madre, Individuo padre) {
            Individuo hijo1(madre.getN());
            Individuo hijo2(madre.getN());
            int *gen1 = new int[madre.getN()];
            int *gen2 = new int[padre.getN()];

            for(int i = 0; i < madre.getN(); i++) {
                if(mask[i] == 1) {
                    gen1[i] = madre.getGenotipo()[i];
                    gen2[i] = padre.getGenotipo()[i];
                }
                else {
                    gen1[i] = padre.getGenotipo()[i];
                    gen2[i] = madre.getGenotipo()[i];
                }
            }
            hijo1 = Individuo(padre.getN(), gen1);
            hijo2 = Individuo(padre.getN(), gen2);
            if(hijo1.getFitness() > hijo2.getFitness())
                return hijo1;
            return hijo2;
        }
};
