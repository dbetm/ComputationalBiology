//#include "bibliotecas.h"
#include "cliente.cpp"

#define PORT 9999
#define LENPACKAGE 1024

int menu();

class Manager {
    private:
        map<int, Cliente> clientes;
        int n;
        // Sockets
        char buffer[LENPACKAGE];
        int server_fd;
        struct sockaddr_in address;
    public:
        Manager(int);
        ~Manager();
        void escuchar();
        void registrarCliente(int, int);
        void solicitarMejorIndividuo();
        void enviarMejorGenotipo();
        void mostrarClientes();
        void operator()() {
            while(true) {
                escuchar();
            }
        }
};

Manager::Manager(int n) {
    this->n = n;
    int opt = 1;
    // Longitud de la direccción
    int addrlen = sizeof(this->address);
    memset(this->buffer, 0, LENPACKAGE * sizeof(this->buffer[0]));

    // Creando el descriptor de socket del archivo
    if((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
    // Indicar que se va usar el puerto 9999
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

Manager::~Manager() {
    if(!this->clientes.empty()) this->clientes.clear();
}

// Escucha las peticiones de los clientes
void Manager::escuchar() {
    this_thread::sleep_for(chrono::milliseconds(300));
    // Aceptar un nuevo cliente
    int new_socket, valread;
    //string mensaje = "Soy el manager";
    int addrlen = sizeof(address);
    if((new_socket = accept(server_fd, (struct sockaddr*)&address,
        (socklen_t*)&addrlen)) < 0) {
        perror("accept");
        exit(EXIT_FAILURE);
    }
    registrarCliente(new_socket, address.sin_port);
    // leer valor que manda
    //valread = read(new_socket, this->buffer, LENPACKAGE);
    //printf("%s", buffer);
    //printf("%d\n", valread);
    //cout << new_socket << endl;
    //cout << address.sin_port << endl;
    //cout << address.sin_addr.s_addr << endl;
    // Enviamos un mensaje de respuesta
    //send(new_socket, mensaje.c_str(), mensaje.size(), 0);
}

// Registrar cliente, lo agrega al vector
void Manager::registrarCliente(int numSocket, int puerto) {
    string nombre = to_string(this->clientes.size()) + "_genetico";
    Cliente cliente(puerto, nombre);
    this->clientes.insert(pair<int, Cliente>(numSocket, cliente));
    //Cliente cliente("anon");
    //this->clientes.push_back(cliente);
}

// Manda una solicitud a todos los clientes y recibe el fitness + genotipo de cada uno
// de ellos, para de ellos seleccionar el mejor.
void Manager::solicitarMejorIndividuo() {
    int valread;
    // Mandar la acción
    string mensaje = "get_best";
    map<int, Cliente>::iterator itr;
    for(itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        send(itr->first, mensaje.c_str(), mensaje.size(), 0);
    }
    memset(this->buffer, 0, LENPACKAGE * sizeof(this->buffer[0]));
    // Recibir fitness de cada uno
    for(itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        valread = read(itr->first, this->buffer, LENPACKAGE);
        printf("Fitness: %s\n", buffer);
        itr->second.setFitness(atoi(buffer));
    }
    memset(this->buffer, 0, LENPACKAGE * sizeof(this->buffer[0]));
    // Recibir genotipo de cada uno
    for(itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        valread = read(itr->first, this->buffer, LENPACKAGE);
        printf("Genotipo: %s\n", buffer);
        itr->second.setGenotipo(buffer);
    }
}

// Envia a cada uno de los clientes el genotipo del mejor individuo muestreado
void Manager::enviarMejorGenotipo() {
    // Recorremos los clientes para obtener el mejor genotipo
    string mejorGenotipo = "";
    int mejorFitness = -1;
    int numSocket = 0;

    map<int, Cliente>::iterator itr;
    for(itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        if(itr->second.getFitness() > mejorFitness) {
            mejorGenotipo = itr->second.getGenotipo();
            mejorFitness = itr->second.getFitness();
            numSocket = itr->first;
        }
    }
    // Mandar la acción
    string mensaje = "set_best";
    for(itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        if(itr->first != numSocket) {
            send(itr->first, mensaje.c_str(), mensaje.size(), 0);
        }
    }
    // Enviamos el genotipo a todos los clientes, excepto a quien pertenece
    // el mejor genotipo.
    for(itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        if(itr->first != numSocket) {
            send(itr->first, mejorGenotipo.c_str(), mejorGenotipo.size(), 0);
        }
    }
}

void Manager::mostrarClientes() {
    map<int, Cliente>::iterator itr;
    for (itr = this->clientes.begin(); itr != this->clientes.end(); ++itr) {
        cout << "---> " << itr->first << "<---" << endl;
        cout << itr->second.toString() << endl;
    }
}

int menu() {
    int opt;
    cout << "Elige una opción: " << endl;
    cout << "1) Recabar información." << endl;
    cout << "2) Mandar información" << endl;
    cout << "3) Mostrar clientes." << endl;
    cout << "4) Salir." << endl;
    cout << ">> ";
    cin >> opt;

    return opt;
}

int main(int argc, char const *argv[]) {
    Manager manager(30);
    thread t1(ref(manager));
    //t1.join();
    bool control = true;
    do {
        int opt = menu();
        if(opt == 1) manager.solicitarMejorIndividuo();
        else if(opt == 2) manager.enviarMejorGenotipo();
        else if(opt == 3) manager.mostrarClientes();
        else if(opt == 4) control = false;
    } while(control);

    t1.detach();

    cin.get();
    return 0;
}
