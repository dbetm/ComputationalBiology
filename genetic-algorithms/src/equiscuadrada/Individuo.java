package equiscuadrada;

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
        this.fitness = this.fenotipo * this.fenotipo;
    }
    
    public Individuo(int[] genotipo) {
        this.genotipo = genotipo;
        // Calculamos el fenotipo
        this.fenotipo = calcularFenotipo();
        this.fitness = this.fenotipo * this.fenotipo;
    }
    
    public void actualizarIndividuo() {
        // Calculamos el fenotipo
        this.fenotipo = calcularFenotipo();
        this.fitness = this.fenotipo * this.fenotipo;
    }

    private int[] generarGenotipoAleatorio() {
        int ans[] = new int[12];
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

    public int[] getGenotipo() {
        return genotipo;
    }


    public int getFenotipo() {
        return fenotipo;
    }


    public int getFitness() {
        return fitness;
    }
    
    public static void main(String args[]) {
        Individuo in = new Individuo();
        
        System.out.println("");
        
        Muta.mutaBit(0.9, in);
        
        System.out.println("");
    }
}
