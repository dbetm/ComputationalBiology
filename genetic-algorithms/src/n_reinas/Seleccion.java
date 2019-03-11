package n_reinas;

import java.util.Random;

/**
 *
 * @author david
 */
public class Seleccion {
    
    public enum TipoSeleccion{RANDOM, TORNEO};
    public enum TipoMuestreo{MEJOR};
        
    // Retorna el mejor individuo de la población 
    public static Individuo seleccionTorneo(Poblacion pob){
        Individuo mejor = new Individuo(pob.getMejor().getGenotipo());
        return mejor;
    }
    
    // Selecciona cualquier individuo de forma aleatoria
    public static Individuo seleccionAleatoria(Poblacion pob) {
        Random ran = new Random();
        int pos = ran.nextInt(pob.getIndividuos().size());
        Individuo mejor = new Individuo(pob.getIndividuos().get(pos).getGenotipo());
        return mejor;
    }
}
