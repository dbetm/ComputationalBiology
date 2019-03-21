package equiscuadrada;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author david
 */
public class GeneticoX2 {
    // Parámetros
    private Poblacion poblacionActual;
    private int numGeneraciones;
    private int tamPoblacion;
    private double probMuta;

    public GeneticoX2(int numGeneraciones, int tamPoblacion, double probMuta) {
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.poblacionActual = new Poblacion(this.tamPoblacion);
    }

    public void evolucionar(int mascara[]) {
        // Generar las iteraciones para las generaciones
        for (int g = 1; g < this.numGeneraciones; g++) {
            // Garantizar crear una nueva población
            ArrayList<Individuo> individuos = new ArrayList<>();
            for (int i = 0; i < this.tamPoblacion; i++) {
                // Seleccionamos
                Individuo madre = Seleccion.seleccionAleatoria(this.poblacionActual);
                Individuo padre = Seleccion.seleccionTorneo(this.poblacionActual);
                // Reproducimos
                Individuo hijo = Reproduccion.cruzaPorMascara(mascara, madre, padre);
                // Mutamos
                Muta.mutaBit(this.probMuta, hijo);
                // Agregamos
                individuos.add(hijo);
            }
            this.poblacionActual = new Poblacion(individuos);
        }
    }

    public Individuo obtenerSolucion() {
        return this.poblacionActual.getMejor();
    }
    
    public String obtenerCadenaSolucion() {
        Individuo mejor = this.poblacionActual.getMejor();
        String fenotipo = String.valueOf(mejor.getFenotipo());
        String genotipo = Arrays.toString(mejor.getGenotipo());
        String fitness = String.valueOf(mejor.getFitness());
        return "Fenotipo: " + fenotipo + "\n" + "Genotipo: " + genotipo + "\n" + 
            "Fitness: " + fitness + "\n";
    }
    
    public static void main(String args[]) {
        GeneticoX2 gx2 = new GeneticoX2(25, 25, 0.2);
        int mascara[] = new int[]{1,0,1,0,1,0,1,0,1,0,1,0};
        gx2.evolucionar(mascara);
        Individuo mejor = gx2.obtenerSolucion();
        System.out.println(gx2.obtenerCadenaSolucion());
    }
}
