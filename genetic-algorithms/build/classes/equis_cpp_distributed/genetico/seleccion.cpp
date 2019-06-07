#include "../bibliotecas.h"

class Seleccion {
    public:
        static Individuo seleccionTorneo(Poblacion poblacion){
            Individuo mejor = Individuo(12, poblacion.getMejor().getGenotipo());
            return mejor;
        }

        static Individuo seleccionAletoria(Poblacion poblacion){
            random_device rd;
            mt19937_64 gen(rd());
            uniform_int_distribution<unsigned int> dis;
            int pos = dis(gen) % poblacion.getIndividuos().size();
            Individuo mejor = Individuo(12, poblacion.getIndividuos()[pos].getGenotipo());
            return mejor;
        }
};
