//#include "bibliotecas.h"
#include "cliente.cpp"

class Manager {
    private:
        vector <Cliente> clientes;
        int n;
    public:
        Manager();
        Manager(int);
        ~Manager();
        void escuchar();
        void registrarCliente();
        void solicitarMejorIndividuo();
        void enviarMejorGenotipo();
        void mostrarClientes();
};

Manager::Manager() {
    this->n = 0;
}

Manager::Manager(int n) {
    this->n = n;
}

Manager::~Manager() {
    if(!this->clientes.empty()) this->clientes.clear();
}

// Escucha las peticiones de los clientes
void Manager::escuchar() {

}

// Registrar cliente, lo agrega al vector
void Manager::registrarCliente() {
    Cliente cliente("anon");
    this->clientes.push_back(cliente);
}

// Manda una solicitud a todos los clientes y recibe el fitness + genotipo de cada uno
// de ellos, para de ellos seleccionar el mejor.
void Manager::solicitarMejorIndividuo() {

}

// Envia a cada uno de los clientes el genotipo del mejor individuo muestreado
void Manager::enviarMejorGenotipo() {

}

void Manager::mostrarClientes() {
    for (unsigned int i = 0; i < this->clientes.size(); i++) {
        /* code */
    }
}
