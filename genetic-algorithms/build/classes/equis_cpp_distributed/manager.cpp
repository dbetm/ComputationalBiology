//#include "bibliotecas.h"
#include "cliente.cpp"

#define PORT 9999
#define LENPACKAGE 1024

class Manager {
    private:
        vector <Cliente> clientes;
        int n;
        // Sockets
        char buffer[LENPACKAGE];
        int server_fd;
        struct sockaddr_in address;
    public:
        Manager();
        Manager(int);
        ~Manager();
        void escuchar();
        void registrarCliente();
        void solicitarMejorIndividuo();
        void enviarMejorGenotipo();
        void mostrarClientes();
        void operator()(int a) {
            while(true) {
                escuchar();
            }
        }
};

Manager::Manager() {
    this->n = 0;
    int opt = 1;
    // Longitud de la direccción
    int addrlen = sizeof(this->address);
    memset(this->buffer, 0, LENPACKAGE * sizeof(this->buffer[0]));

    // Creando el descriptor de socket del archivo
    if((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
    // Indicar que se va usar el puerto 8080
    if(setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
       perror("setsocketopt");
       exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
    // Binding
    if(bind(server_fd, (struct sockaddr*)&address, sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }
    // Poner a escuchar el servidor
    if(listen(server_fd, 3) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }
}

Manager::Manager(int n) {
    this->n = n;
}

Manager::~Manager() {
    if(!this->clientes.empty()) this->clientes.clear();
}

// Escucha las peticiones de los clientes
void Manager::escuchar() {
    cout << "Hola" << endl;
    this_thread::sleep_for(chrono::milliseconds(1000));
    // Aceptar un nuevo cliente
    int new_socket, valread;
    string mensaje = "Soy el manager";
    int addrlen = sizeof(address);
    if((new_socket = accept(server_fd, (struct sockaddr*)&address,
    (socklen_t*)&addrlen)) < 0) {
        perror("accept");
        exit(EXIT_FAILURE);
    }
    valread = read(new_socket, this->buffer, 1024);
    printf(">> %s\n", buffer);
    printf("%d\n", valread);
    cout << address.sin_port << endl;
    // Enviamos un mensaje de respuesta
    send(new_socket, mensaje.c_str(), mensaje.size(), 0);
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
        cout << this->clientes[i].toString() << endl;
    }
}

int main(int argc, char const *argv[]) {
    Manager manager(12);
    thread t1(Manager(), 0);
    //t1.join();
    cout << "FIN" << endl;

    cin.get();
    return 0;
}
