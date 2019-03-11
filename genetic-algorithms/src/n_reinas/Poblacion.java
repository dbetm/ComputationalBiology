package n_reinas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Poblacion {
    private ArrayList<Individuo> individuos;
    
    public Poblacion(int numInd, int n) {
        this.individuos = new ArrayList<>();
        // Generamos los indiviuos de forma aleatoria
        for(int i = 0; i < numInd; i++) this.individuos.add(new Individuo(n));
    }
    
    public Poblacion(ArrayList<Individuo> aux) {
        this.individuos = (ArrayList<Individuo>) aux.clone();
    }
    
    public ArrayList<Individuo> getNMejores(int n){
        // validar que n <= tamaño de la población
        if(n < this.individuos.size()) {
            // ordenar a la población
            ordenarPoblacionActual();
            // creamos un coleccion nueva de individuos
            ArrayList<Individuo> muestra = new ArrayList<>();

            for(int x = 0; x < n; x++) {
                Individuo e = new Individuo(this.individuos.get(x).getGenotipo());
                muestra.add(e);
            }   
            return muestra;
        }
        return (ArrayList<Individuo>) this.individuos.clone();
    }
    
    public Individuo getMejor() {
        // 0 es la hipótesis
        int idMejor = 0;
        for(int x = 1;x < this.individuos.size(); x++) {
            if(this.individuos.get(x).getFitness() < 
                this.individuos.get(idMejor).getFitness()){
                idMejor = x;
            }
        }
        return new Individuo(this.individuos.get(idMejor).getGenotipo());
    }
    
    public ArrayList<Individuo> getMuestraAleatoria(int n) {
        // validar que n <= tamaño de la población
        if(n<this.individuos.size()){
            // creamos un coleccion nueva de individuos
            ArrayList<Individuo> muestra = new ArrayList<>();
            Random ran = new Random();
            for(int x = 0; x < n; x++) {
                int pos = ran.nextInt(this.individuos.size());
                Individuo e = new Individuo(this.individuos.get(pos).getGenotipo());
                muestra.add(e);
            }   
            return muestra;
        }
        return (ArrayList<Individuo>) this.individuos.clone();
    }
    
    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    // Método de ordenamiento: Burbuja optimizado
    private void ordenarPoblacionActual() {
        for(int z = 1; z < this.individuos.size(); z++) {
            for(int v = 0; v < (this.individuos.size() - z); v++) {

                if(this.individuos.get(v).getFitness() > 
                    this.individuos.get(v+1).getFitness()) {
                    
                    Individuo aux = 
                        new Individuo(this.individuos.get(v).getGenotipo());
                    this.individuos.set(v, 
                        new Individuo(this.individuos.get(v+1).getGenotipo()));
                    this.individuos.set(v+1,aux);
                }
            }
        }     
    }
    
    public void agregarIndividuo(Individuo ind) {
        this.individuos.add(ind);
    }
}
