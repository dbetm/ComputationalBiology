package equiscuadrada;

import java.util.ArrayList;
import java.util.Random;

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
        this.individuos = (ArrayList<Individuo>) aux.clone();
    }
    
    public ArrayList<Individuo> getNMejores(int n) {
        // Validación
        if(n < this.individuos.size()) {
            // primero se ordenan los individuos
            burbujaOptimizado();
            ArrayList<Individuo> mejores = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                mejores.add(new Individuo(this.individuos.get(i).getGenotipo()));
            }
            return mejores;
        }
        else
            return (ArrayList<Individuo>) this.individuos.clone();
    }
    
    private void burbujaOptimizado() {
        // ordenar
        Individuo temp;
        boolean swapped;
        for (int i = 0; i < this.individuos.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < this.individuos.size() - 1 - i; j++) {
                if(this.individuos.get(j).getFitness() < this.individuos.get(j+1).getFitness()) {
                    temp = new Individuo(this.individuos.get(j).getGenotipo());
                    this.individuos.set(j, new Individuo(this.individuos.get(j+1).getGenotipo()));
                    this.individuos.set(j+1, temp);
                    swapped = true;
                }
            }
            if(!swapped) break; // Ya está ordenado
        }
    }
    
    public ArrayList<Individuo> getMuestraAleatoria(int n) {
        // Validar que n <= tamaño de la población
        if(n < this.individuos.size()) {
            // Creamos una nueva colección
            ArrayList<Individuo> muestra = new ArrayList<>();
            Random ran = new Random();
            for (int i = 0; i < n; i++) {
                int index = ran.nextInt(this.individuos.size());
                Individuo e = new Individuo(this.individuos.get(index).getGenotipo());
                muestra.add(e);
            }
            return muestra;
        }
        else
            return (ArrayList<Individuo>) this.individuos.clone();
    }
    
    public Individuo getMejor() {
        int idMejor = 0;
        
        for (int i = 1; i < this.individuos.size(); i++) {
            if(this.individuos.get(0).getFitness() < 
                this.individuos.get(i).getFitness()) {
                idMejor = i;
            }
        }
        return new Individuo(this.individuos.get(idMejor).getGenotipo());
    }

    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }
}
