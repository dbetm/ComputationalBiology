package TSP;

import java.util.Random;

/**
 *
 * @author david
 */
public class Muta {
    public static void mutaGenotipo(double prob, Individuo ind) {
        // evaluar la probabilidad 
        double aux = Math.random();
        if(aux <= prob) {
            // modificar un bit del genotipo
            Random ran = new Random();
            int posA = ran.nextInt(ind.getGenotipo().length-1)+1;
            int posB = ran.nextInt(ind.getGenotipo().length-1)+1;
            int tmp = ind.getGenotipo()[posA];
            ind.getGenotipo()[posA] = ind.getGenotipo()[posB];
            ind.getGenotipo()[posB] = tmp;
            // actualizamos el fenotipo y el fitness
            ind.actualizarIndividuo();
        }
    }
}
