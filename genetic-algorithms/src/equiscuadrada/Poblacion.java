package equiscuadrada;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Poblacion {
    private ArrayList<Individuo> individuos;

    public Poblacion(int numIndividuos) {
        this.individuos = new ArrayList<>();
        
        for (int i = 0; i < numIndividuos; i++) {
            this.individuos.add(new Individuo());
        }
    }
    
    public Poblacion(ArrayList<Individuo> aux) {
        this.individuos = aux;
    }
    
    public ArrayList<Individuo> getNMejores(int n) {
        return null;
    }
    
    public ArrayList<Individuo> getMuestraAleatoria(int n) {
        return null;
    }
    
    public Individuo getMejor() {
        return null;
    }

    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }
}
