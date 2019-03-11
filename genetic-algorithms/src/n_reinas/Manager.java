package n_reinas;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Manager {
    private int nG;
    private boolean ejecucion;
    private ArrayList<GeneticoNReinas> geneticos;
    private ArrayList<Configuracion> configuraciones;
    private int tamFenotipo;

    public Manager(int tamFenotipo) {
        this.nG = 0;
        this.tamFenotipo = tamFenotipo;
        this.ejecucion = false;
        this.geneticos = new ArrayList<>();
        this.configuraciones = new ArrayList<>();
    }

    public int getTamFenotipo() {
        return tamFenotipo;
    }

    public void generarGeneticos(ArrayList<Configuracion> confs) {
        this.configuraciones = confs;
        this.nG = confs.size();
        // Generar las configuraciones con base en confs
        for (int i = 0; i < this.nG; i++) {
            GeneticoNReinas gen = new GeneticoNReinas(confs.get(i));
            this.geneticos.add(gen);
        }
    }
    
    public void generarGeneticos(int nG) {
        for (int i = 0; i < nG; i++) {
            this.configuraciones.add(new Configuracion(this.tamFenotipo));
            GeneticoNReinas gen = new GeneticoNReinas(this.configuraciones.get(i));
            this.geneticos.add(gen);
        }
    }
    
    // Retorna el hashcode
    public int agregarGenetico(Configuracion conf) {
        this.configuraciones.add(conf);
        GeneticoNReinas gen = new GeneticoNReinas(conf);
        this.geneticos.add(gen);
        return gen.hashCode();
    }
    
    public void ejecutarGeneticos() {
        // Ejecutar genéticos en un hilo diferente cada uno
        for (GeneticoNReinas genetico : this.geneticos) {
            Thread hilo = new Thread(genetico);
            System.out.println("");
            hilo.start();
            genetico.setHilo(hilo);
        }
        this.ejecucion = true;
    }
    
    public void ejecutarGenetico(int hashCode) {
        for (GeneticoNReinas genetico : this.geneticos) {
            if(genetico.hashCode() == hashCode) {
                Thread hilo = new Thread(genetico);
                hilo.start();
                genetico.setHilo(hilo);
                break;
            }
        }
    }
    
    public void intercambiarMejorIndividuo(int hashOrigen, int hashDestino) {
        GeneticoNReinas origen = getGenetico(hashOrigen);
        GeneticoNReinas destino = getGenetico(hashDestino);
        Individuo mejor =
            new Individuo(origen.getPoblacionActual().getMejor().getGenotipo());
        destino.agregarIndividuo(mejor);
    }
    
    public GeneticoNReinas getGenetico(int hashCode) {
        for (GeneticoNReinas genetico : this.geneticos) {
            if(genetico.hashCode() == hashCode) {
                return genetico;
            }
        }
        return null;
    }
    
    // Pruebas unitarias
    public static void main(String args[]) {
        // Generamos la configuración
        Configuracion c1 = new Configuracion(25000, 100, 0.4, 0.2, 60, 
            new Seleccion.TipoSeleccion[]{Seleccion.TipoSeleccion.RANDOM, 
            Seleccion.TipoSeleccion.TORNEO});
        // Manager
        Manager m = new Manager(c1.getN());
        ArrayList<Configuracion> confs = new ArrayList<>();
        confs.add(c1);
        m.generarGeneticos(confs);
        System.out.println("");
        m.ejecutarGeneticos();
    }
}
