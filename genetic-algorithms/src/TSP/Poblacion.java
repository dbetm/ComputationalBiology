package TSP;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Poblacion {
    private ArrayList<Individuo> indivduos;
    // ci es ciudad inicial
    
    public Poblacion(int numInd,int numCd,int ci) {
        this.indivduos = new ArrayList<>();
        for(int x = 0; x < numInd; x++) this.indivduos.add(new Individuo(numCd,ci));
    }
    
    public Poblacion(ArrayList<Individuo> aux){
        this.indivduos = (ArrayList<Individuo>)aux.clone();
    }
    
    public ArrayList<Individuo> getNMejores(int n){
        // validar que n <= tamaño de la población
        if(n<this.indivduos.size()){
            // ordenar a la población
            ordenarPoblacionActual();
            // creamos un coleccion nueva de individuos
            ArrayList<Individuo> muestra = new ArrayList<>();

            for(int x = this.indivduos.size()-1; x>= this.indivduos.size()-n; x--){
                Individuo e = new Individuo(this.indivduos.get(x).getGenotipo());
                muestra.add(e);
            }   
            return muestra;
        }
        return (ArrayList<Individuo>) this.indivduos.clone();
    }
    
    public Individuo getMejor() {
        int idMejor = 0;
        for(int x = 1; x<this.indivduos.size(); x++){
            if(this.indivduos.get(x).getFitnessGeneral() > 
                this.indivduos.get(idMejor).getFitnessGeneral()){
                idMejor = x;
            }
        }
        return new Individuo(this.indivduos.get(idMejor).getGenotipo());
    }
    
    public ArrayList<Individuo> getMuestraAleatoria(int n){
        // validar que n <= tamaño de la población
        if(n < this.indivduos.size()){
            // creamos un coleccion nueva de individuos
            ArrayList<Individuo> muestra = new ArrayList<>();
            Random ran = new Random();
            for(int x = 0; x < n; x++) {
                int pos = ran.nextInt(this.indivduos.size());
                Individuo e = new Individuo(this.indivduos.get(pos).getGenotipo());
                muestra.add(e);
            }   
            return muestra;
        }
        return (ArrayList<Individuo>) this.indivduos.clone();
    }
    
    private void ordenarPoblacionActual() {
        for(int z = 1; z < this.indivduos.size(); ++z) {
            for(int v = 0; v < (this.indivduos.size() - z); ++v) {
                if(this.indivduos.get(v).getFitnessGeneral()
                    < this.indivduos.get(v+1).getFitnessGeneral()) {
                    Individuo aux = new Individuo(this.indivduos.get(v).getGenotipo());
                    this.indivduos.set(v,new Individuo(this.indivduos.get(v+1).getGenotipo()));
                    this.indivduos.set(v+1,aux);
                }
            }
        }    
    }
    
    public ArrayList<Individuo> getIndivduos() {
        return indivduos;
    }
}
