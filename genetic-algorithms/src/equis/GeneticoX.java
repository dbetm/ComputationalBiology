package equis;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author david
 */
public class GeneticoX {
    // Parametros
    private Poblacion poblacionActual;
    private int numGeneraciones;
    private int tamPoblacion;
    private double probMuta;
    
    public GeneticoX(int numGeneraciones, int tamPoblacion, double probMuta) {
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.poblacionActual = new Poblacion(this.tamPoblacion);
    }
    
    public void evolucionar() {
        // Generar las iteraciones para las generaciones
        for (int g = 1; g < this.numGeneraciones; g++) {
            // Garantizar crear una nueva población
            ArrayList<Individuo> individuos = new ArrayList<>();
            for (int i = 0; i < this.tamPoblacion; i++) {
                // Seleccionamos
                Individuo madre = Seleccion.seleccionAleatoria(this.poblacionActual);
                Individuo padre = Seleccion.seleccionTorneo(this.poblacionActual);
                int mascara[] = new int[]{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
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
    
    public static void main(String args[]) {
        GeneticoX gx = new GeneticoX(25, 25, 0.2);
        gx.evolucionar();
        Individuo mejor = gx.obtenerSolucion();
        System.out.println(mejor.toString());
    }
}
