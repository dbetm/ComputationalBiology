package TSP;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class GeneticoTSP {
    // Atributos
    private Poblacion poblacionActual;
    private int numGeneraciones, tamPob;
    private double probMuta;

    public GeneticoTSP(int numGeneraciones, int tamPob, int numCiudades, int ci, 
        double probMuta) {
        this.numGeneraciones = numGeneraciones;
        this.tamPob = tamPob;
        this.probMuta = probMuta;
        // ci es ciudad inicial
        this.poblacionActual = new Poblacion(tamPob, numCiudades, ci);
    }
    
    public void evolucionar() {
        Grafica grafica = new Grafica("Puntos ", "Distancias","Inclinaciones");
        grafica.crearSerie("Individuo");
        Grafica historia = new Grafica("Historia", "Generaciones", "Fitness");
        historia.crearSerie("fitness");
        Individuo mejor = null;
        // generar las iteraciones para las generaciones
        for(int g = 1; g < this.numGeneraciones; g++){
            // garantizar construir una nueva población
            ArrayList<Individuo> ind = new ArrayList<>();
            for(int i = 0; i < this.tamPob; i++) {
                // seleccionamos
                Individuo madre = Seleccion.seleccionTorneo(this.poblacionActual);
                Individuo padre = Seleccion.seleccionAleatoria(this.poblacionActual);

                // reproducimos
                Individuo hijo = Reproduccion.cruza(madre, padre);
                // mutamos 
                    // evaluar la probabilidad
                Muta.mutaGenotipo(this.probMuta, hijo);
                // agregamos
                ind.add(hijo);
            }
            // actualizamos la nueva población
            this.poblacionActual = new Poblacion(ind);
            // pedimos el mejor a la poblacion 
            mejor = this.poblacionActual.getMejor();
            /*
            System.out.println("Fintess distancia: " + mejor.getFitnessDistancia() +
                ", fitness inclinación: " + mejor.getFitnessInclinacion());
            */
            grafica.crearPuntoASerie("Individuo", 1 * mejor.getFitnessDistancia(),
                0 * mejor.getFitnessInclinacion());
            double mejorFitness = mejor.getFitnessGeneral();
            System.out.println(g +": " +  mejorFitness);
            historia.crearPuntoASerie("fitness", g, mejorFitness);
        }
        grafica.crearGrafica();
        historia.crearGrafica();
    }

    public static void main(String args[]) {
        Herramientas.cargarDistancias();
        Herramientas.generarInclinacionesAleatorias(Herramientas.distancias.length, 1000);
        System.out.println("");
        GeneticoTSP g = new GeneticoTSP(10000, 55, Herramientas.distancias.length, 0 , 0.5); 
        g.evolucionar();
    }
}
