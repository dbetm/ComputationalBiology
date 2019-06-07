#include "individuo.cpp"
#include "poblacion.cpp"
#include "cruza.cpp"
#include "seleccion.cpp"
#include "mutacion.cpp"
#include "geneticoEquis.cpp"

#define PORT 9999
#define LENPACKAGE 1024

class Agente {
    private:
        GeneticoEquis *gen;
        struct sockaddr_in address;
        struct sockaddr_in serv_addr; // servidor
        thread *thread_object;
        int sock;
    public:
        Agente();
        ~Agente();
        int conectar();
        void escuchar();
        void enviarFitness(string);
        void enviarGenotipo(string);
        void recibirGenotipo();
};

Agente::Agente() {
    // Instancia el genético
    this->gen = new GeneticoEquis(30000, 20, 0.4);
    // Correr el genético
    this->thread_object = new thread(ref(*this->gen));
    conectar();
    escuchar();
}

Agente::~Agente() {
    this->thread_object->detach();
}

int Agente::conectar() {
    // Crear el socket y hacer conexión
    if((this->sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        cout << "\nSocket creation error" << endl;
        return -1;
    }
    memset(&serv_addr, '0', sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    // Convert IPv4 addresses from text to binary form
    if(inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr) <= 0) {
        printf("\nInvalid address/ Address not supported \n");
        return -1;
    }
    // Conectar
    if(connect(this->sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        cout << "\nConnection failed" << endl;
        return -1;
    }

    return 0;
}

void Agente::escuchar() {
    cout << "Escuchando..." << endl;
    // aquí se escuchan las acciones
    using namespace std::this_thread; // sleep_for, sleep_until
    using namespace std::chrono; // nanoseconds, system_clock, seconds
    int valread;
    while(true) {
        char buffer[1024] = {0};
        valread = read(this->sock, buffer, 1024);
        if (strcmp(buffer, "set_best") == 0) {
            recibirGenotipo();
        }
        else if(strcmp(buffer, "get_best") == 0) {
            string promesa = this->gen->obtenerPromesa();
            string fitness = "";
            string genotipo = "";
            int i = 0;
            while(promesa[i] != '#') {
                fitness += promesa[i];
                i++;
            }
            for (unsigned int j = i+1; j < promesa.size(); j++) genotipo += promesa[j];
            enviarFitness(fitness);
            sleep_for(nanoseconds(10));
            sleep_until(system_clock::now() + seconds(1));
            enviarGenotipo(genotipo);
        }
    }
}

void Agente::enviarFitness(string fitness) {
    cout << "Enviando fitness: " << fitness << endl;
    send(this->sock, fitness.c_str(), fitness.size(), 0);
}

void Agente::enviarGenotipo(string genotipo) {
    cout << "Enviando genotipo: " << genotipo << endl;
    send(this->sock, genotipo.c_str(), genotipo.size(), 0);
}

void Agente::recibirGenotipo() {
    // Al recibir el genotipo se manda llamar la función para agregarlo
    // a la población.
    char buffer[1024] = {0};
    int valread = read(this->sock, buffer, 1024);
    cout << "\nGenotipo: " << buffer << endl;
    this->gen->agregarPromesa(buffer);
}

int main() {
    Agente agente;
    return 0;
}
