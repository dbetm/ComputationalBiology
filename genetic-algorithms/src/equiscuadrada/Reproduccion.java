package equiscuadrada;

import equiscuadrada.Individuo;

/**
 *
 * @author david
 */
public class Reproduccion {
    public static Individuo cruzaPorMascara(int[] mascara, Individuo madre, Individuo padre) {
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
}
