package RPTools;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public interface ClasificadorSupervisado {
    // Se entrena con un conjunto de patrones con una clase asignada y [] características
    public void entrena(ArrayList<Patron> instancias);
    // Se pasa un patrón para que lo etiquete
    public void clasifica(Patron patron);
}
