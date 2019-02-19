package n_reinas;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class GeneticoNReinas {
    // Parametros
    private Poblacion poblacionActual;
    private int numGeneraciones;
    private int tamPoblacion;
    private double probMuta;
    private int n;

    public GeneticoNReinas(int numGeneraciones, int tamPoblacion, double probMuta, int n) {
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.n = n;
        this.poblacionActual = new Poblacion(this.tamPoblacion, this.n);
    }
    
    public void evolucionar() {
        if(buscarSolucionCalculada()) return;
        int mascara[] = Cruza.generarMascaraAleatoria(n);
        // iterar todas las generaciones
        for(int g = 1; g < this.numGeneraciones; g++) {
            // garantizar construir una nueva población
            ArrayList<Individuo> ind = new ArrayList<>();
            for(int i = 0; i < this.tamPoblacion; i++) {
                // seleccionamos
                Individuo madre = Seleccion.seleccionAleatoria(this.poblacionActual);
                Individuo padre = Seleccion.seleccionAleatoria(this.poblacionActual);
                // reproducimos
                Individuo hijo = Cruza.cruzaXMascara(mascara, madre, padre);
                // mutamos 
                // evaluar la probabilidad
                Muta.mutaGen(this.probMuta, hijo);
                // agregamos
                ind.add(hijo);
            }
            // actualizamos la nueva población
            this.poblacionActual = new Poblacion(ind);
            //System.out.println(g);
            // pedimos el mejor a la poblacion 
            Individuo mejor  = this.poblacionActual.getMejor();
            int f = mejor.getFitness();
            System.out.println(f);
            if(f == 0) {
                String sol = Arrays.toString(mejor.getGenotipo());
                System.out.println("g: "+g+" ->" + sol);
                try {
                    String path = "src/n_reinas/" + this.n + ".sol";
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
    
    private boolean buscarSolucionCalculada() {
        boolean ans = false;
        String path = "src/n_reinas/" + this.n + ".sol";
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
    
    public static void main(String args[]) {
        GeneticoNReinas gen = new GeneticoNReinas(100, 50, 0.2, 5);
        gen.evolucionar();
    }
}
