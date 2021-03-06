package sat_3;

import java.util.Random;

/**
 *
 * @author david
 */
public class Seleccion {
    public enum TipoSeleccion{RANDOM, TORNEO};
    
    public static Individuo seleccionTorneo(Poblacion pob) {
        Individuo mejor = new Individuo(pob.getMejor().getGenotipo());
        return mejor;
    }
    
    public static Individuo seleccionAleatoria(Poblacion pob) {
        Random ran = new Random();
        int pos = ran.nextInt(pob.getIndividuos().size());
        Individuo nuevo = new Individuo(pob.getIndividuos().get(pos).getGenotipo());
        return nuevo;
    }
}
