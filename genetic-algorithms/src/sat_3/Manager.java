package sat_3;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Manager {
    private ArrayList<ThreeSAT> geneticos;
    
    public Manager() {
        this.geneticos = new ArrayList<>();
    }

    public Manager(ArrayList<ThreeSAT> geneticos) {
        this.geneticos = geneticos;
    }
    
    // Retorna el hashcode
    public int agregarGenetico(Configuracion conf) {
        ThreeSAT gen = new ThreeSAT(conf);
        this.geneticos.add(gen);
        return gen.hashCode();
    }
    
    // Lanzar el método evolucionar del genético en un hilo independiente
    public void ejecutarGenetico(int hashCode) {
        for (ThreeSAT genetico : this.geneticos) {
            if(genetico.hashCode() == hashCode) {
                Thread hilo = new Thread(genetico);
                hilo.start();
                break;
            }
        }
    }
    
    public ThreeSAT getGenetico(int hashCode) {
        for (ThreeSAT genetico : this.geneticos) {
            if(genetico.hashCode() == hashCode) return genetico;
        }
        return null;
    }
    
    // Para el intercambio de información entre genéticos
    public void intercambiarMejorIndividuo(int hashOrigen, int hashDestino) {
        ThreeSAT origen = getGenetico(hashOrigen);
        ThreeSAT destino = getGenetico(hashDestino);
        
        Individuo mejor =
            new Individuo(origen.getPoblacionActual().getMejor().getGenotipo());
        // Lo agregamos al destino
        destino.agregarIndividuo(mejor);
    }
    
    public void intercambiarNMejores(int hashOrigen, int hashDestino, int n) {
        ThreeSAT origen = getGenetico(hashOrigen);
        ThreeSAT destino = getGenetico(hashDestino);
        
        ArrayList<Individuo> mejores = origen.getPoblacionActual().getNMejores(n);
        // Los seteamos al destino
        for (Individuo mejor : mejores) destino.agregarIndividuo(mejor);
    }
    
    public void intercambiarNAleatorios(int hashOrigen, int hashDestino, int n) {
        ThreeSAT origen = getGenetico(hashOrigen);
        ThreeSAT destino = getGenetico(hashDestino);
        
        ArrayList<Individuo> randomSample = 
            origen.getPoblacionActual().getMuestraAleatoria(n);
        // Los seteamos al destino
        for (Individuo ind : randomSample) destino.agregarIndividuo(ind);
    }
    
    public void intercambiarMascara(int hashOrigen, int hashDestino) {
        ThreeSAT origen = getGenetico(hashOrigen);
        ThreeSAT destino = getGenetico(hashDestino);
        
        int mascara[] = origen.getConf().getMascara().clone();
        // Seteamos la nueva máscara
        destino.getConf().setMascara(mascara);
    }
}
