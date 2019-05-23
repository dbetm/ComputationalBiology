package TSP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;

/**
 *
 * @author david
 */
public class Herramientas {
    public static double distancias[][];
    public static double inclinaciones[];
    
    public static void generarDistanciasAleatorias(int numCiudades, double bound) {
        distancias = new double[numCiudades][numCiudades];
        Random ran = new Random();
        
        for (int i = 0; i < numCiudades-1; i++) {
            for (int j = i+1; j < numCiudades; j++) {
                double val = ran.nextDouble()*(bound-1) + 1;
                distancias[i][j] = val;
                // Reflejo simétrico
                distancias[j][i] = val;
            }
        }
    }
    
    public static void generarInclinacionesAleatorias(int numCiudades, int bound) {
        inclinaciones = new double[numCiudades];
        Random ran = new Random();
        double incli;
        for(int y = 0; y < numCiudades; y++) {
            incli = ran.nextInt(bound) + 0.1;
            inclinaciones[y] = incli;
        }
        System.out.println("");
    }
    
    public static void cargarDistancias() {
        FileReader archivos = null;
        try {
            String cadena;
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();
            archivos = new FileReader(abre);
            BufferedReader lee = new BufferedReader(archivos);
            ArrayList<ArrayList<Double>> Matriz = new ArrayList();
            
            if (abre != null) {
                int i = 0;
                while ((cadena = lee.readLine()) != null) {
                    String[] datos = cadena.split(",");
                    ArrayList<Double> c = new ArrayList();
                    for (int j = 0; j < datos.length; j++) {
                        c.add(Double.parseDouble(datos[j])); 
                    }
                    Matriz.add(c); 
                    i++;
                }
                lee.close();
            }
            distancias = new double[Matriz.size()][Matriz.size()];
            for (int i = 0; i < Matriz.size(); i++) {
                for (int j = 0; j < Matriz.get(0).size(); j++) {
                    distancias[i][j] = Matriz.get(i).get(j);
                }
            }
        }
        catch (FileNotFoundException ex) {            
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                archivos.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void guardarDistancias() {
        
    }
}
