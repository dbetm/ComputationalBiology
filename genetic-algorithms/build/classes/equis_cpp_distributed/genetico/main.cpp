#include "individuo.cpp"
#include "poblacion.cpp"
#include "cruza.cpp"
#include "seleccion.cpp"
#include "mutacion.cpp"
#include "geneticoEquis.cpp"

int menu();

int main() {
    GeneticoEquis gen = GeneticoEquis(100000, 23, 0.4);
    // Correr el genético en un hilo
    thread thread_object(ref(gen));
    //thread_object.join();
    bool control = true;
    do {
        int opt = menu();
        if(opt == 1) {
            cout << gen.obtenerPromesa() << endl;
        }
        else if(opt == 2) {
            control = false;
        }

    } while(control);

    // detach(): desvincula el objeto del hilo en ejecución, ya no se puede
    // hacer join y se puede destruir libremente 
    thread_object.detach();
    //terminate();
    return 0;
}

int menu() {
    int opt;
    cout << "Elige una opción: " << endl;
    cout << "1) Obtener promesa." << endl;
    cout << "2) Salir." << endl;
    cout << ">> ";
    cin >> opt;

    return opt;
}
