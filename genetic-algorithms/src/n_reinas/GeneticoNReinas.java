package n_reinas;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import tools.Grafica;

/**
 *
 * @author david
 */
public class GeneticoNReinas implements Runnable {
    // Parametros
    private Poblacion poblacionActual;
    private Configuracion configuracion;
    private int historia[];
    private DefaultListModel<String> log = null;
    private JList<String> listLog = null;
    private boolean graficarAlTerminar;
    private Thread hilo;

    public GeneticoNReinas(Configuracion conf) {
        this.configuracion = conf;
        this.poblacionActual = new Poblacion(this.configuracion.getTamPoblacion(), 
            this.configuracion.getN());
        this.historia = new int[this.configuracion.getNumGeneraciones()];
        this.graficarAlTerminar = false;
    }
    
    public void evolucionar() {
        // Random r = new Random();
        // if(buscarSolucionCalculada()) return;
        int mascara[] = this.configuracion.getMascara();
        // iterar todas las generaciones
        for(int g = 1; g < this.configuracion.getNumGeneraciones(); g++) {

            // garantizar construir una nueva población
            ArrayList<Individuo> ind;
            // calcular un N
            int n = (int)(this.configuracion.getTamPoblacion() 
                * this.configuracion.getpMuestra());
            if (n > 0){
                ind = new ArrayList<>();
                ind.add(this.poblacionActual.getMejor());
            }
            else ind = new ArrayList<>();
            
            for(int i = n; i < this.configuracion.getTamPoblacion(); i++) {
                // seleccionamos
                Individuo madre = this.configuracion.aplicarSeleccion(this.poblacionActual, 0);
                Individuo padre = this.configuracion.aplicarSeleccion(this.poblacionActual, 1);
                // reproducimos
                Individuo hijo = Cruza.cruzaXMascara(this.configuracion.getMascara(), madre, padre);
                // mutamos 
                // evaluar la probabilidad
                Muta.mutaGen(this.configuracion.getProbMuta(), hijo);
                // agregamos
                ind.add(hijo);
            }
            // actualizamos la nueva población
            this.poblacionActual = new Poblacion(ind);
            //System.out.println(g);
            // pedimos el mejor a la poblacion 
            Individuo mejor  = this.poblacionActual.getMejor();
            int f = mejor.getFitness();
            this.historia[g] = f;
            //System.out.println("g: " + g + "\tf: " +f);
            // Para mostrarlo en la lista
            if(this.log != null && this.listLog != null) {
                if(this.log.size() == 500) this.log.clear();
                this.log.addElement("g: " + g + " f: " + f);
                int lastIndex = this.log.getSize() - 1;
                if(lastIndex >= 0) this.listLog.ensureIndexIsVisible(lastIndex);
            }
            if(f == 0) {
                String sol = Arrays.toString(mejor.getGenotipo());
                System.out.println("g: "+g+" ->" + sol);
                try {
                    String path = "src/n_reinas/" + this.configuracion.getN() + ".sol";
                    PrintWriter archivo = new PrintWriter(path, "UTF-8");
                    archivo.println(sol);
                    archivo.close();
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        } 
    }

    public void setLog(DefaultListModel<String> log) {
        this.log = log;
    }
    
    public void setListLog(JList<String> listLog) {
        this.listLog = listLog;
    }
    
    public int[] getHistoria() {
        return historia;
    }
    
    public boolean isGraficarAlTerminar() {
        return graficarAlTerminar;
    }

    public void setGraficarAlTerminar(boolean graficarAlTerminar) {
        this.graficarAlTerminar = graficarAlTerminar;
    }
   
    private boolean buscarSolucionCalculada() {
        boolean ans = false;
        String path = "src/n_reinas/" + this.configuracion.getN() + ".sol";
        File f = new File(path);
        if(f.exists()) {
            try {
                Scanner in = new Scanner(new FileReader(path));
                StringBuilder sb = new StringBuilder();
                while(in.hasNext()) sb.append(in.next());
                in.close();
                System.out.println("Solución, ya antes calculada, encontrada");
                System.out.println(sb.toString());
                ans = true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ans;
    }
    
    public Thread getHilo() {
        return hilo;
    }

    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }
    
    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public Poblacion getPoblacionActual() {
        return poblacionActual;
    }
    
    public void agregarIndividuo(Individuo ind) {
        this.poblacionActual.agregarIndividuo(ind);
        this.configuracion.setTamPoblacion(this.configuracion.getTamPoblacion() + 1);
    }
    
    @Override
    public void run() {
        evolucionar();
        if(this.graficarAlTerminar) {
            Grafica g = new Grafica("Historia", "Generaciones", "Fitness");
            g.agregarSerie("N-Reinas", this.historia);
            g.crearGrafica();
        }
    }
    
    public static void main(String args[]) {
        Seleccion.TipoSeleccion ts[] = new Seleccion.TipoSeleccion[]{
            Seleccion.TipoSeleccion.RANDOM,
            Seleccion.TipoSeleccion.TORNEO
        };
        Configuracion conf = new Configuracion(1000, 25, 0.5, 0.05, 100, ts);
        GeneticoNReinas gen = new GeneticoNReinas(conf);
        //gen.evolucionar();
        Thread hilo = new Thread(gen);
        hilo.start();    
    }
}
