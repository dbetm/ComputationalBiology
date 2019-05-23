package RPTools;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class MinimaDistancia implements ClasificadorSupervisado {
    // Para guardar los patrones representativos, uno por clase
    private ArrayList<PatronRepresentativo> representativos;

    public MinimaDistancia() {
        // Se instancia la colección de patrones representativos
        this.representativos = new ArrayList<>();
    }

    @Override
    public void entrena(ArrayList<Patron> instancias) {
        // Agregamos el primer representativo.
        this.representativos.add(new PatronRepresentativo(
                instancias.get(0).getCaracteristicas(),
                instancias.get(0).getClaseOriginal()));
        // Se recorre la coleccion de patrones restantes
        for (int i = 1; i < instancias.size(); i++){
            Patron patron = instancias.get(i);
            // Buscar en la colección de patrones representativos
            buscaYAcumula(patron);
        }
        // Calcular la media para cada patrón representativo
        for (PatronRepresentativo pr: this.representativos) {
            // Recorrremos por características
            for (int i = 0; i < pr.getCaracteristicas().length; i++) {
                pr.getCaracteristicas()[i] /= pr.getNumPatrones();
            }
        }
    }

    @Override
    public void clasifica(Patron patron) {
        // Hipótesis, el primer patrón representativo es respecto a patron el
        // más cercano.
        double distanciaMenor = HerramientasClasificadores
                .calcularDistanciaEuclidiana(patron, this.representativos.get(0));
        patron.setClaseResultante(this.representativos.get(0).getClaseOriginal());
        // Se recorre el resto de PR para descartar la hipótesis o apoyarla
        for (int i = 1; i < this.representativos.size(); i++) {
            double distancia = HerramientasClasificadores.calcularDistanciaEuclidiana(patron,
                    this.representativos.get(i));
            // Si se encuentra una distancia mínima a la antes almacenada,
            // entonces se actualiza
            if(distancia < distanciaMenor) {
                distanciaMenor = distancia;
                // Etiquetamos el patrón con la clase orginasl del PR.
                patron.setClaseResultante(this.representativos.get(i).getClaseOriginal());
            }
        }
    }

    // Para clasificar un conjunto de clasificación / instancias de clasificación
    // Retorna el número de instancias clasificadas correctamente.
    public double clasificaConjunto(ArrayList<Patron> instancias) {
        // Recorremos la colección de clasificación
        int total = instancias.size();
        // Contador de clasificaciones correctos
        int aux = 0;
        for (Patron conejillo : instancias) {
            clasifica(conejillo);
            if(conejillo.getClaseResultante().equals(conejillo.getClaseOriginal())) {
                aux++;
            }
        }
        // Se calcula la eficacia con una regla de 3.
        return (aux * 100) / total;
    }


    private void buscaYAcumula(Patron patron) {
        // para saber cuando no existen instancias representativas.
        int m = -1;
        // Buscar en la colección de representantes
        for (int i = 0; i < this.representativos.size(); i++) {
            // Verificamos si ya existe
            if(patron.getClaseOriginal().equals(
                    this.representativos.get(i).getClaseOriginal())) {
                // Contamos, es decir, se aumenta el número de patrones
                // para ese patrón representativo.
                this.representativos.get(i).setNumPatrones(
                        this.representativos.get(i).getNumPatrones() + 1);
                // Acumulamos, el valor de las características se suma
                for (int j = 0; j < this.representativos.get(i)
                        .getCaracteristicas().length; j++) {
                    this.representativos.get(i).getCaracteristicas()[j] +=
                            patron.getCaracteristicas()[j];
                }
                m = i;
                break;
            }
        }
        if(m == -1) {
            // Se agrega al conjunto de patrones representativos.
            this.representativos.add(new PatronRepresentativo(
                    patron.getCaracteristicas(),
                    patron.getClaseOriginal()
            ));
        }
    }

    public static void main(String[] args) {
        Tokenizador.leerDatos(0);
        int len = Tokenizador.instancias.get(0).getCaracteristicas().length;
        System.out.println("Len: " + len);
        // Instanciamos el clasificador supervisado de distancia mínima
        MinimaDistancia md = new MinimaDistancia();
        // Se hace la selección de características
        ArrayList<Patron> aux = 
            GeneradorDeInstancias.genInstanciasPorCaracteristicas(new int[] {
            0, 1, 0, 1
        }, 0);
        
        // Se entrena
        md.entrena(aux);
        // Para clasificar el mismo conjunto con el que se entrenó
        md.clasificaConjunto(aux);
        // Mostramos en pantalla la eficacia
        //System.out.println("Eficacia: " + md.getEficacia());
    }
    
}
