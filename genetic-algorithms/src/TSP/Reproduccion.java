package TSP;

/**
 *
 * @author david
 */
public class Reproduccion {
    
    public static Individuo cruza(Individuo madre, Individuo padre) {
        Individuo hijo;
        int tamGenotipo = madre.getGenotipo().length;
        int hash[] = new int[tamGenotipo];
        int genotipo[] = new int[tamGenotipo];
        // la ciudad inicial no cambia
        genotipo[0] = madre.getGenotipo()[0];
        hash[genotipo[0]] = 1;
        // Completamos el resto del genotipo
        for (int i = 1; i < tamGenotipo; i++) {
            double distanciaEnMadre = 
                Herramientas.distancias[madre.getGenotipo()[i-1]][madre.getGenotipo()[i]];
            double distanciaEnPadre =
                Herramientas.distancias[padre.getGenotipo()[i-1]][padre.getGenotipo()[i]];
            if(distanciaEnMadre <= distanciaEnPadre) {
                genotipo[i] = madre.getGenotipo()[i];
                if(hash[genotipo[i]] == 0) { // se marca el hash
                    hash[genotipo[i]] = 1;
                }
                else { // como ya se usa, entonces se usa del padre
                    genotipo[i] = padre.getGenotipo()[i];
                    hash[genotipo[i]] = 1;
                }
            }
            else {
                genotipo[i] = padre.getGenotipo()[i];
                if(hash[genotipo[i]] == 0) { // se marca el hash
                    hash[genotipo[i]] = 1;
                }
                else { // como ya se usa, entonces se usa de la madre
                    genotipo[i] = madre.getGenotipo()[i];
                    hash[genotipo[i]] = 1;
                }
            }
        }
        // Nace el hijo
        hijo = new Individuo(genotipo);
        return hijo;
    }
}
















