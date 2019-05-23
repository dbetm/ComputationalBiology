package TSP;

import java.util.Random;

/**
 *
 * @author david
 */
public class Seleccion {
    public static Individuo seleccionTorneo(Poblacion pob) {
        Individuo mejor = new Individuo(pob.getMejor().getGenotipo());
        return mejor;
    }
    
    public static Individuo seleccionAleatoria(Poblacion pob) {
        Random ran = new Random();
        int pos = ran.nextInt(pob.getIndivduos().size());
        Individuo mejor = new Individuo(pob.getIndivduos().get(pos).getGenotipo());
        return mejor;
    }
}
