package n_reinas;

import java.util.Random;

/**
 *
 * @author david
 */
public class Cruza {
    
    public static int[] generarMascaraAleatoria(int dim){
        int mask[] = new int[dim];
        Random ran = new Random();
    
        for(int x = 0; x < dim; x++) {
            mask[x]=ran.nextInt(2);
        }
        return mask;
    }
    
     public static Individuo cruzaXMascara(int mask[], Individuo madre, Individuo padre){
        Individuo hijo1, hijo2;
        int geno1[] = new int[madre.getGenotipo().length];
        int geno2[] = new int[padre.getGenotipo().length];
        // recorrer la máscara de cruza
        for (int x = 0; x < mask.length; x++) {
            // 1 madre y 0 padre
            if(mask[x] == 1) {
                geno1[x] = madre.getGenotipo()[x];
                geno2[x] = padre.getGenotipo()[x];
            }
            else { 
                geno1[x] = padre.getGenotipo()[x];
                geno2[x] = madre.getGenotipo()[x];
            }
        }
        hijo1 = new Individuo(geno1);
        hijo2 = new Individuo(geno2);
    
        if (hijo1.getFitness() < hijo2.getFitness()) return hijo1;
        else return hijo2;
    }
}
