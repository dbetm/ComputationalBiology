package RPFeatureOptimizer;

import RPTools.GeneradorDeInstancias;
import RPTools.MinimaDistancia;
import RPTools.Patron;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Individuo {
    // Atributos
    private int genotipo[]; // Para una representación binaria
    private double fitness;    // Eficacia del individuo o su grado de bondad
    private int numCaracteristicas;
    
    public Individuo(int numCaracteristicas) {
        this.numCaracteristicas = numCaracteristicas;
        this.genotipo = generarGenotipoAleatorio();
        calcularFitness();
    }
    
    public Individuo(int[] genotipo) {
        this.genotipo = genotipo;
        this.numCaracteristicas = this.genotipo.length;
        calcularFitness();
    }
    
     public void actualizarIndividuo() {
        // Calculamos el fitness
        calcularFitness();
    }
     
    private int[] generarGenotipoAleatorio() {
        int ans[] = new int[this.numCaracteristicas];
        Random r = new Random();
        for (int i = 0; i < ans.length; i++) ans[i] = r.nextInt(2);
        return ans;
    }

    private void calcularFitness() {
        // Se hace el proceso de clasificación
        // Instanciamos el clasificador supervisado de distancia mínima
        MinimaDistancia md = new MinimaDistancia();
        
        ArrayList<Patron> datasetEntrenamiento = 
            GeneradorDeInstancias.genInstanciasPorCaracteristicas(this.genotipo, 0);
        
        // Se entrena el clasificador
        md.entrena(datasetEntrenamiento);
        
        ArrayList<Patron> datasetTest = 
            GeneradorDeInstancias.genInstanciasPorCaracteristicas(this.genotipo, 1);
        
        this.fitness = md.clasificaConjunto(datasetTest);
    }

    public int[] getGenotipo() {
        return genotipo;
    }

    public double getFitness() {
        return fitness;
    }
}
