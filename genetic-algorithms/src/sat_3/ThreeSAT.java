package sat_3;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author david
 */
public class ThreeSAT implements Runnable {
    // Parámetros
    private Poblacion poblacionActual;
    private Configuracion conf;
    // Para la GUI
    private DefaultListModel<String> log = null;
    private JList<String> listLog = null;
    
    public ThreeSAT(Configuracion configuracion) {
        this.conf = configuracion;
        this.poblacionActual = new Poblacion(this.conf.getTamPoblacion());
    }
    
    public void evolucionar() {
        // Iterar todas las generaciones
        // Comienza desde 1 ya que la población inicial es la primera
        for (int g = 1; g < this.conf.getNumGeneraciones(); g++) {
            // Garantizamos crear una nueva población
            ArrayList<Individuo> individuos = new ArrayList<>();
            // Vamos a generar cada uno de los individuos
            for (int j = 0; j < this.conf.getTamPoblacion(); j++) {
                // Seleccionamos la pareja
                    // El segundo parámetro es para indicar que es la madre
                Individuo madre = this.conf.aplicarSeleccion(this.poblacionActual, 0);
                    // El segundo parámetro es para indicar que es padre
                Individuo padre = this.conf.aplicarSeleccion(this.poblacionActual, 1);
                // Reproducimos
                Individuo hijo = Reproduccion.cruzaPorMascara(this.conf.getMascara(), madre, padre);
                // Mutamos
                Muta.mutaBit(this.conf.getProbMuta(), hijo);
                // Agregamos
                individuos.add(hijo);
            }
            // Actualizar la población actual
            this.poblacionActual = new Poblacion(individuos);
            // pedimos el mejor a la poblacion 
            Individuo mejor  = this.poblacionActual.getMejor();
            int f = mejor.getFitness();
            //System.out.println("g: " + g + " f: " + f);
            // Logging
            try {
                if(this.log != null && this.listLog != null) {
                    //if(this.log.size() == 1000) this.log.clear();
                    this.log.addElement("g: " + g + " f: " + f);
                    int lastIndex = this.log.getSize() - 1;
                    if(lastIndex >= 1) this.listLog.ensureIndexIsVisible(lastIndex);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
            // comprobar la convergencia
            if(f == 550) {
                String sol = Arrays.toString(mejor.getGenotipo());
                System.out.println("g: "+g+" ->" + sol);
                break;
            }
        }
    }
    
    @Override
    public void run() {
        evolucionar();
    }
    
    public void agregarIndividuo(Individuo ind) {
        this.poblacionActual.agregarIndividuo(ind);
        // En la configuración del genético aumentamos en una unidad el tamaño de
        // la población.
        this.conf.setTamPoblacion(this.conf.getTamPoblacion() + 1);
    }
    
    // Métodos de acceso
    public Poblacion getPoblacionActual() {
        return poblacionActual;
    }
    
    public Configuracion getConf() {
        return conf;
    }
    
        // Para la GUI
    public void setLog(DefaultListModel<String> log) {
        this.log = log;
    }
    
    public void setListLog(JList<String> listLog) {
        this.listLog = listLog;
    }

    public static void main(String args[]) {
        Tokenizador.leerDatos();
        Seleccion.TipoSeleccion ts[] = new Seleccion.TipoSeleccion[] {
            Seleccion.TipoSeleccion.RANDOM,
            Seleccion.TipoSeleccion.TORNEO
        };
        // args[#generaciones, #población, #.#probMuta]
        Configuracion conf = new Configuracion(150000, 50, 0.65, 
            Reproduccion.generarMascara(100), ts);
        // Instanciamos el genético
        ThreeSAT threeSAT = new ThreeSAT(conf);
        threeSAT.evolucionar();
    }
}
