package n_reinas;

import java.util.Random;

/**
 *
 * @author david
 */
public class Individuo {
    private int genotipo[];
    private int fitness;
    private int n;

    public Individuo(int n) {
        this.n = n;
        this.genotipo = calcularGenotipoAleatorio();
    }

    public Individuo(int[] genotipo) {
        this.genotipo = genotipo;
        this.n = this.genotipo.length;
        calcularFitness();
    }
    
    public void actualizarIndividuo() {
        calcularFitness();
    }

    public void calcularFitness() {
        this.fitness = 0;
        
        for (int i = 0; i < this.n-1; i++) {
            for (int j = i+1; j < this.n; j++) {
                // Evaluamos
                    // Horizontales
                boolean c1 = this.genotipo[i] == this.genotipo[j];
                    // Diagonal descendente
                boolean c2 = this.genotipo[i]-i == this.genotipo[j]-j;
                    // Diagonal ascendente 
                boolean c3 = this.genotipo[i]+i == this.genotipo[j]+j;
                if(c1 || c2 || c3) this.fitness += 2;
            }
        }
    }

    private int[] calcularGenotipoAleatorio() {
        int genotipo[] = new int[this.n];
        Random r = new Random();
        for (int i = 0; i < this.n; i++) {
            genotipo[i] = r.nextInt(this.n);
        }
        return genotipo;
    }

    public int[] getGenotipo() {
        return genotipo;
    }

    public int getFitness() {
        return fitness;
    }

    public int getN() {
        return n;
    }
}
