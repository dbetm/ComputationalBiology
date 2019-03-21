package sat_3;

import java.util.Random;

/**
 *
 * @author david
 */
public class Muta {
    
    // Muta a un individuo por referencia
    public static void mutaBit(double probabilidad, Individuo ind) {
        // Evaluar la probabilidad
        double aux = Math.random();
        
        if(aux <= probabilidad) {
            // Modificar un bit del genotipo
            Random ran = new Random();
            int pos  = ran.nextInt(ind.getGenotipo().length);
            if(ind.getGenotipo()[pos] == 1) ind.getGenotipo()[pos] = 0;
            else ind.getGenotipo()[pos] = 1;
            // Actualizar el fenotipo y fitness del individuo
            ind.actualizarIndividuo();
        }
    }
}
