package RPTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author david
 */
public class Tokenizador {
    // Datos de entrenamiento
    public static ArrayList<Patron> instancias;
    // Datos de prueba
    public static ArrayList<Patron> test;
    
    public static void leerDatos(int tipo) {
        String texto, aux;
        LinkedList<String> lista = new LinkedList();
        if(tipo == 0) instancias = new ArrayList<>();
        else if(tipo == 1) test = new ArrayList<>();
        
        try {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    texto = aux;
                    lista.add(texto);
                }
                // Se cierra el BufferedReader
                lee.close();
                //System.out.println(lista.size());

                ArrayList<String> lista2 = new ArrayList<>();
                String clase = "";
                for (int i = 0; i < lista.size(); i++) {
                    StringTokenizer st = new StringTokenizer(lista.get(i), ",");

                    while (st.hasMoreTokens()) {
                        lista2.add(st.nextToken());
                    }
                     
                    double[] vector = new double[lista2.size() - 1];

                    for (int x = 0; x < lista2.size() - 1; x++) {
                        vector[x] = Double.parseDouble(lista2.get(x));
                    }

                    clase = lista2.get(lista2.size()-1);
                    // a la coleccion de patrones se agrega un nuevo patron
                    if(tipo == 0) {
                        instancias.add(new Patron(vector, clase));
                    }
                    else if(tipo == 1) {
                        test.add(new Patron(vector, clase));
                    }
                    
                    lista2.clear();
                }

            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                + "\nNo se ha encontrado el archivo",
                "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
    }   

}
