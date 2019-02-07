package equis;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author david
 */
public class Individuo {
    // Atributos
    private int genotipo[];
    private int fenotipo;
    private int fitness;
    
    public Individuo() {
        this.genotipo = generarGenotipoAleatorio();
        // Calculamos el fenotipo
        this.fenotipo = calcularFenotipo();
        this.fitness = this.fenotipo;
    }
    
    public Individuo(int[] genotipo) {
        this.genotipo = genotipo;
        // Calculamos el fenotipo
        this.fenotipo = calcularFenotipo();
        this.fitness = this.fenotipo;
    }
    
    public void actualizarIndividuo() {
        // Calculamos el fenotipo
        this.fenotipo = calcularFenotipo();
        this.fitness = this.fenotipo;
    }

    private int[] generarGenotipoAleatorio() {
        int ans[] = new int[24];
        Random r = new Random();
        for (int i = 0; i < ans.length; i++) ans[i] = r.nextInt(2);
        return ans;
    }

    private int calcularFenotipo() {
        // Convertir el arreglo de bits a décimal
        int ans = 0;
        int j = 0;
        for (int i = this.genotipo.length - 1; i >= 0; i--, j++) 
            ans += this.genotipo[i] * Math.pow(2, j);
        
        return ans;
    }

    @Override
    public String toString() {
        String fenotipo = String.valueOf(getFenotipo());
        String genotipo = Arrays.toString(getGenotipo());
        String fitness = String.valueOf(getFitness());
        return "Fenotipo: " + fenotipo + "\n" + "Genotipo: " + genotipo + "\n" + 
            "Fitness: " + fitness + "\n";
    }

    public int[] getGenotipo() {
        return genotipo;
    }

    public int getFenotipo() {
        return fenotipo;
    }

    public int getFitness() {
        return fitness;
    }
}
