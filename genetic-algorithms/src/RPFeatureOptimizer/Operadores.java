package RPFeatureOptimizer;

import java.util.Random;

/**
 *
 * @author david
 */
public class Operadores {
    // Atributos estáticos
    public static int mascara[];
    
    // ### MUTACIÓN ###
    
    // Muta a un individuo por referencia
    public static void mutar(double probabilidad, Individuo ind) {
        // Evaluar la probabilidad
        double aux = Math.random();
        
        if(aux <= probabilidad) {
            // Modificar un bit del genotipo
            Random ran = new Random();
            int pos  = ran.nextInt(ind.getGenotipo().length);
            if(ind.getGenotipo()[pos] == 1) ind.getGenotipo()[pos] = 0;
            else ind.getGenotipo()[pos] = 1;
            // Actualizar el fenotipo y fitness del individuo
            ind.actualizarIndividuo();
        }
    }
    
    
    // ### REPRODUCCIÓN ###
    
    public static void generarMascara(int tam) {
        mascara = new int[tam];
        Random ran = new Random();
        
        for (int i = 0; i < tam; i++) {
            mascara[i] = ran.nextInt(2);
        }
    }
    
    public static Individuo cruzar(Individuo madre, Individuo padre) {
        Individuo hijo1, hijo2; 
        // Representación genotipica para construir los hijos
        int geno1[] = new int[mascara.length];
        int geno2[] = new int[mascara.length];
        // Recorrer la máscara de cruza
        // Madre con 1, padre con 0
        for (int i = 0; i < mascara.length; i++) {
            if(mascara[i] == 1) {
                geno1[i] = madre.getGenotipo()[i];
                geno2[i] = padre.getGenotipo()[i];
            }
            else {
                geno1[i] = padre.getGenotipo()[i];
                geno2[i] = madre.getGenotipo()[i];
            }
        }
        hijo1 = new Individuo(geno1);
        hijo2 = new Individuo(geno2);
        // Sobrevive el que entrega más fitness
        return (hijo1.getFitness() >= hijo2.getFitness()) ? hijo1 : hijo2;
    }
    
    // ### SELECCIÓN ###
    
    public static Individuo seleccionarPorTorneo(Poblacion pob) {
        Individuo mejor = new Individuo(pob.getMejor().getGenotipo());
        return mejor;
    }
    
    public static Individuo seleccionarAleatorio(Poblacion pob) {
        Random ran = new Random();
        int pos = ran.nextInt(pob.getIndividuos().size());
        Individuo nuevo = new Individuo(pob.getIndividuos().get(pos).getGenotipo());
        return nuevo;
    }
}
