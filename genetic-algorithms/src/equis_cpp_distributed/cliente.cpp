#include "bibliotecas.h"

class Cliente {
    private:
        int puerto;
        int fitness;
        string genotipo; // representación en cadena del genotipo
        string estado; // sin_iniciar, corriendo, terminado
        string nombre;
    public:
        Cliente(string);
        Cliente(int, string);
        Cliente(int, int, string, string);
        ~Cliente();
        void setPuerto(int);
        void setFitness(int);
        void setGenotipo(string);
        void setEstado(string);
        void setNombre(string);
        int getPuerto();
        int getFitness();
        string getGenotipo();
        string getEstado();
        string getNombre();
        string toString();
};

Cliente::Cliente(string nombre) {
    this->puerto = 0;
    this->fitness = 0;
    this->genotipo = "";
    this->estado = "sin_iniciar";
    this->nombre = nombre;
}

Cliente::Cliente(int puerto, string nombre) {
    this->puerto = puerto;
    this->fitness = 0;
    this->genotipo = "";
    this->estado = "corriendo";
    this->nombre = nombre;
}

Cliente::Cliente(int puerto, int fitness, string genotipo, string nombre) {
    this->puerto = puerto;
    this->fitness = fitness;
    this->genotipo = genotipo;
    this->estado = "corriendo";
    this->nombre = nombre;
}

Cliente::~Cliente() {
}

void Cliente::setPuerto(int puerto) {
    this->puerto = puerto;
}

void Cliente::setFitness(int fitness) {
    this->fitness = fitness;
}

void Cliente::setGenotipo(string genotipo) {
    this->genotipo = genotipo;
}

void Cliente::setEstado(string estado) {
    if(estado == "sin_iniciar" || estado == "corriendo" || estado == "detenido") {
        this->estado = estado;
    }
    else {
        cout << "Estado no permitido" << endl;
    }
}

void Cliente::setNombre(string nombre) {
    this->nombre = nombre;
}

int Cliente::getPuerto() {
    return this->puerto;
}

int Cliente::getFitness() {
    return this->fitness;
}

string Cliente::getGenotipo() {
    return this->genotipo;
}

string Cliente::getEstado() {
    return this->estado;
}

string Cliente::getNombre() {
    return this->nombre;
}

string Cliente::toString() {
    string str = "Nombre: " + this->nombre;
    str += ", puerto: " + to_string(this->puerto);
    str += ", fitness: " + to_string(this->fitness);
    str += "\nGenotipo: " + this->genotipo;
    str += "\nEstado: " + this->estado;
    return str;
}
