package equiscuadrada;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Pruebas {
    public static void main(String args[]) {
        // Generamos una población de 15 personas
        Poblacion pob = new Poblacion(5);
        
        System.out.println("");
        ArrayList<Individuo> mejores = pob.getNMejores(2);
        Individuo m = pob.getMejor();
        ArrayList<Individuo> ma = pob.getMuestraAleatoria(3);
        System.out.println("");

        // Creamos otra población con los individuos de la anterior
        Poblacion pob1 = new Poblacion(pob.getIndividuos());

        // Mutamos uno de los individuos de la última población
        Muta.mutaBit(0.9, pob1.getIndividuos().get(0));
        System.out.println("");
        
        // Se está pasando el arreglo de individuos por referencia, 
        // así que tal vez esto genere alguna inconsistencia
        
        // Se hace una selección aleatoria
        Individuo ind = Seleccion.seleccionAleatoria(pob);
        System.out.println("");
        
        // Ahora se hace una reproducción
        // ¿quizá convenga que sea método estático?
        Reproduccion rep = new Reproduccion();
        
        Individuo ind2 = rep.cruzaPorMascara(new int[]{1,0,1,0,1,0,1,0,1,0,1,0}, 
            pob.getIndividuos().get(0), pob.getIndividuos().get(1));
        System.out.println("");
    }
}
