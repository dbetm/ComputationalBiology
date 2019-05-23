#include "individuo.cpp"
#include "poblacion.cpp"
#include "cruza.cpp"
#include "seleccion.cpp"
#include "mutacion.cpp"
#include "geneticoEquis.cpp"

int main() {
    GeneticoEquis gen = GeneticoEquis(25, 23, 0.4);
    gen.evolucionar();

    return 0;
}
