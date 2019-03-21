package sat_3;

import java.util.Random;

/**
 *
 * @author david
 */
public class Individuo {
    private int genotipo[];
    private int fitness;
    
    public Individuo() {
        generarGenotipoAleatorio();
        calcularFitness();
    }
    
    public Individuo(int genotipo[]) {
        this.genotipo = genotipo.clone();
        calcularFitness();
    }
    
    private void generarGenotipoAleatorio() {
        this.genotipo = new int[100];
        Random ran = new Random();
        for (int i = 0; i < this.genotipo.length; i++) {
            this.genotipo[i] = ran.nextInt(2);
        }
    }
    
    public void actualizarIndividuo() {
        calcularFitness();
    }

    private void calcularFitness() {
        this.fitness = 0;
        for (Clausula c : Tokenizador.clausulas) {
            // si la cláusula cumple, se incrementa el fitness
            if(verificarClausula(c)) this.fitness++;
        }
    }
    
    private boolean verificarClausula(Clausula c) {
        return (verificarNeg(c.getA())==1 || verificarNeg(c.getB())==1 ||
            verificarNeg(c.getC()) == 1);
    }
    
    private int verificarNeg(int a) {
        boolean negacion = false;
        int valor = -1;
        if(a < 0) {
           a *= -1; // cambiamos el signo, se hace positivo
           negacion = true;   
        }
        if (negacion) {
            valor = this.genotipo[a-1];
            // dado que fue negación, se hace toggle del valor
            if (valor == 0) valor = 1;
            else valor = 0;
        }
        else valor = this.genotipo[a-1];
        
        return valor;
    }

    public int[] getGenotipo() {
        return genotipo;
    }

    public int getFitness() {
        return fitness;
    }
}
