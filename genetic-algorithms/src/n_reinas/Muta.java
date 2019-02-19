package n_reinas;

import java.util.Random;

/**
 *
 * @author david
 */
public class Muta {
    
    public static void mutaGen(double prob, Individuo ind) {
        // evaluar la probabilidad 
        double aux = Math.random();
        if(aux <= prob) {
            // modificar un bit del genotipo
            Random ran = new Random();
            int posGen = ran.nextInt(ind.getGenotipo().length);
            int posR = ran.nextInt(ind.getGenotipo().length);
            ind.getGenotipo()[posGen] = posR;
            // actualizamos el fenotipo y el fitness
            ind.actualizarIndividuo();
        }
    }
}
