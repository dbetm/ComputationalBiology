
package RPTools;

import java.util.ArrayList;

/**
 *
 * @author david
 */
/*
    Para que se generén instancias de entrenamiento/conjunto de
    entrenamiento o bien conjunto para clasificación, 
    de acuerdo a las características seleccionadas.
*/
public class GeneradorDeInstancias {
    
    //Adjunta toda la colección y filtra por características
    public static ArrayList<Patron> genInstanciasPorCaracteristicas(int[] map, int tipo) {
        ArrayList<Patron> aux = new ArrayList<>();
        int unos = 0;
        // Para saber la longitud del vector de las características de cada patrón
        for (int i = 0; i < map.length; i++) if(map[i] == 1) unos++;
        
        if(tipo == 0) { // set de entrenamiento
            // Recorremos las instancias
            for(Patron pOriginal: Tokenizador.instancias) {
                // Asegurar la creación de un patrón
                Patron pNuevo;
                String clase = pOriginal.getClaseOriginal();
                double vectorN[] = new double[unos];
                int j = 0;
                for (int i = 0; i < map.length; i++) {
                    if(map[i] == 1) {
                        vectorN[j] = pOriginal.getCaracteristicas()[i];
                        j++;
                    }
                }
                pNuevo = new Patron(vectorN, clase);
                aux.add(pNuevo);
            }
        }
        else if(tipo == 1) { // set de prueba
            // Recorremos las instancias
            for(Patron pOriginal: Tokenizador.test) {
                // Asegurar la creación de un patrón
                Patron pNuevo;
                String clase = pOriginal.getClaseOriginal();
                double vectorN[] = new double[unos];
                int j = 0;
                for (int i = 0; i < map.length; i++) {
                    if(map[i] == 1) {
                        vectorN[j] = pOriginal.getCaracteristicas()[i];
                        j++;
                    }
                }
                pNuevo = new Patron(vectorN, clase);
                aux.add(pNuevo);
            }
        }
        return aux;
    }
}
