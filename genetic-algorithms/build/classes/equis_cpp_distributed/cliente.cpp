#include "bibliotecas.h"

class Cliente {
    private:
        string ipv4;
        int puerto;
        int fitness;
        string genotipo; // representación en cadena del genotipo
        string estado; // sin_iniciar, corriendo, terminado
        string nombre;
    public:
        Cliente(string);
        Cliente(string, int, string);
        Cliente(string, int, int, string, string);
        ~Cliente();
        void setHost(string);
        void setPuerto(int);
        void setFitness(int);
        void setGenotipo(string);
        void setEstado(string);
        void setNombre(string);
        string getHost();
        int getPuerto();
        int getFitness();
        string getGenotipo();
        string getEstado();
        string getNombre();
        string toString();
};

Cliente::Cliente(string nombre) {
    this->ipv4 = "";
    this->puerto = 0;
    this->fitness = 0;
    this->genotipo = "";
    this->estado = "sin_iniciar";
    this->nombre = nombre;
}

Cliente::Cliente(string host, int puerto, string nombre) {
    this->ipv4 = host;
    this->puerto = puerto;
    this->fitness = 0;
    this->genotipo = "";
    this->estado = "corriendo";
    this->nombre = nombre;
}

Cliente::Cliente(string host, int puerto, int fitness, string genotipo, string nombre) {
    this->ipv4 = host;
    this->puerto = puerto;
    this->fitness = fitness;
    this->genotipo = genotipo;
    this->estado = "corriendo";
    this->nombre = nombre;
}

Cliente::~Cliente() {
}

void Cliente::setHost(string host) {
    this->ipv4 = host;
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

string Cliente::getHost() {
    return this->ipv4;
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
    str += "\nIPv4: " + this->ipv4;
    str += ", puerto: " + to_string(this->puerto);
    str += ", fitness: " + to_string(this->fitness);
    str += "\nGenotipo: " + this->genotipo;
    str += "\nEstado: " + this->estado;
    return str;
}
