package sat_3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author david
 */
public class ThreeSAT {
    // Parámetros
    private Poblacion poblacionActual;
    private int numGeneraciones;
    private int tamPoblacion; // ¿Cuántos individuos?
    private float probMuta;
    
    public ThreeSAT(int numGeneraciones, int tamPoblacion, float probMuta) {
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.poblacionActual = new Poblacion(this.tamPoblacion);
    }
    
    public void evolucionar(int mascara[]) {
        // Iterar todas las generaciones
        
        // Comienza desde 1 ya que la población inicial es la primera
        for (int g = 1; g < this.numGeneraciones; g++) {
            // Garantizamos crear una nueva población
            ArrayList<Individuo> individuos = new ArrayList<>();
            // Vamos a generar cada uno de los individuos
            for (int j = 0; j < this.tamPoblacion; j++) {
                // Seleccionamos la pareja
                Individuo madre = Seleccion.seleccionTorneo(this.poblacionActual);
                Individuo padre = Seleccion.seleccionTorneo(this.poblacionActual);
                // Reproducimos
                Individuo hijo = Reproduccion.cruzaPorMascara(mascara, madre, padre);
                // Mutamos
                Muta.mutaBit(this.probMuta, hijo);
                // Agregamos
                individuos.add(hijo);
            }
            // Actualizar la población actual
            this.poblacionActual = new Poblacion(individuos);
            // pedimos el mejor a la poblacion 
            Individuo mejor  = this.poblacionActual.getMejor();
            int f = mejor.getFitness();
            // Logging
            System.out.println("g: " + g + " => " + f);
            
            // comprobar la convergencia
            if(f == 550) {
                String sol = Arrays.toString(mejor.getGenotipo());
                System.out.println("g: "+g+" ->" + sol);
                break;
            }
        }
    }

    public static void main(String args[]) {
        Tokenizador.leerDatos();
        // args[#generaciones, #población, #.#probMuta]
        ThreeSAT threeSAT = new ThreeSAT(40000, 39, (float) 0.6);
        threeSAT.evolucionar(Reproduccion.generarMascara(100));
    }
}
