package RPFeatureOptimizer;

import RPTools.GraficaDouble;
import RPTools.Tokenizador;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author david
 */
public class Genetico {
    // Constantes finales
    public static final int DATASET_ENTRENAMIENTO = 0;
    public static final int DATASET_TEST = 1;
    
    // Atributos
    private Poblacion poblacionActual;
    private int numGeneraciones;
    private int tamPoblacion;
    private double probMuta;
    private int numCaracteristicas;
    private double historia[];

    public Genetico(int numGeneraciones, int tamPoblacion, double probMuta, int numCaracteristicas) {
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.numCaracteristicas = numCaracteristicas;
        this.poblacionActual = new Poblacion(this.tamPoblacion, this.numCaracteristicas);
        this.historia = new double[this.numGeneraciones];
        // Se genera la máscara con la cual se hace la cruza.
        Operadores.generarMascara(this.numCaracteristicas);
    }
    
    public void evolucionar() {
        // Generar las iteraciones para las generaciones
        for (int g = 1; g < this.numGeneraciones; g++) {
            // Garantizar crear una nueva población
            ArrayList<Individuo> individuos = new ArrayList<>();
            for (int i = 0; i < this.tamPoblacion; i++) {
                // Seleccionamos
                Individuo madre = Operadores.seleccionarAleatorio(this.poblacionActual);
                Individuo padre = Operadores.seleccionarPorTorneo(this.poblacionActual);
                // Reproducimos
                Individuo hijo = Operadores.cruzar(madre, padre);
                // Mutamos
                Operadores.mutar(this.probMuta, hijo);
                // Agregamos a la población
                individuos.add(hijo);
            }
            this.poblacionActual = new Poblacion(individuos);
            // Obtenemos el mejor de la población
            Individuo mejor  = this.poblacionActual.getMejor();
            // El fitness del mejor
            double f = mejor.getFitness();
            // Lo agregamos a la historia
            this.historia[g] = f;
            // Lo mostramos en consola
            System.out.println("g: " + g + "\tf: " + f);
        }
    }
    
    public void graficar() {
        GraficaDouble g = new GraficaDouble("Historia", "Generaciones", "Fitness");
        g.agregarSerie("Feature selection", this.historia);
        g.crearGrafica();
    }
    
    public void mostrarMejor() {
        int genotipo[] = this.poblacionActual.getMejor().getGenotipo();
        System.out.println("Genotipo: \n" + Arrays.toString(genotipo));
        System.out.println("Fitness: \n" + this.poblacionActual.getMejor().getFitness());
    }
     
    public static void main(String args[]) {
        // Cargamos el set de entrenamiento
        Tokenizador.leerDatos(DATASET_ENTRENAMIENTO);
        // Ahora el set de prueba
        Tokenizador.leerDatos(DATASET_TEST);
        int numCaracteristicas = Tokenizador.instancias.get(0).getCaracteristicas().length;
        System.out.println("# características: " + numCaracteristicas);
        System.out.println("");
        Genetico genetico = new Genetico(100, 15, 0.8, numCaracteristicas);
        genetico.evolucionar();
        genetico.graficar();
        genetico.mostrarMejor();
    }   
}
