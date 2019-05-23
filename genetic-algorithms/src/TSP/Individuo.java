package TSP;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Individuo {
    private int n; // número de ciudades
    private int ciudadInicial;
    private int genotipo[];
    private double fitnessDistancia;
    private double fitnessInclinacion;
    private double fitnessGeneral;

    public Individuo(int n, int ciudadInicial) {
        this.n = n;
        this.ciudadInicial = ciudadInicial;
        generarGenotipoAleatorio();
        calcularFitnessDistancias();
        calcularFitnessInclinaciones();
        calcularFitnessGeneral();
    }
    
    public Individuo(int genotipo[]) {
        this.n = genotipo.length;
        this.ciudadInicial = genotipo[0];
        this.genotipo = genotipo.clone();
        calcularFitnessDistancias();
        calcularFitnessInclinaciones();
        calcularFitnessGeneral();
    }
    
    private void generarGenotipoAleatorio() {
        this.genotipo = new int[this.n];
        Random ran = new Random();
        ArrayList<Integer> lista = new ArrayList<>();
        
        // Inicializar
        for (int i = 0; i < this.genotipo.length; i++) {
            if(i != this.ciudadInicial) lista.add(i);
        }
        this.genotipo[0] = this.ciudadInicial;
        
        // Agregamos el resto de ciudades de forma aleatoria
        for (int i = 1; i < this.genotipo.length; i++) {
            int index = ran.nextInt(lista.size());
            this.genotipo[i] = lista.remove(index);
        }
    }
    
    public void actualizarIndividuo() {
        calcularFitnessDistancias();
        calcularFitnessInclinaciones();
        calcularFitnessGeneral();
    }

    private void calcularFitnessDistancias() {
        int ultima = this.genotipo[this.n-1];
        int primera = this.genotipo[0];
        this.fitnessDistancia = Herramientas.distancias[primera][ultima];
        // Recorremos el genotipo
        for (int i = 0; i < this.n-1; i++) {
            this.fitnessDistancia += 
                Herramientas.distancias[this.genotipo[i]][this.genotipo[i+1]];
        }
    }
    
    /*
    Es un fitness representativo de la cantidad de subidas
    */
    private void calcularFitnessInclinaciones() {
        this.fitnessInclinacion = 0;
        // recorrer el individudo y consultamos las inclinaciones
        for (int x = 0 ; x < this.genotipo.length-1; x++) {
            if(Herramientas.inclinaciones[this.genotipo[x+1]] > 
                Herramientas.inclinaciones[this.genotipo[x]]) {
                this.fitnessInclinacion++;
            }
            /*
            this.fitnessInclinacion += 
                Herramientas.inclinaciones[this.genotipo[x]]- 
                    Herramientas.inclinaciones[this.genotipo[x+1]];
            System.out.println(
                Herramientas.inclinaciones[this.genotipo[x]] + " - " +
                Herramientas.inclinaciones[this.genotipo[x+1]] + " = " +
                this.fitnessInclinacion
            );
            */
        }
        // agregamos la inclinación de la ultima a la inicial
        if(Herramientas.inclinaciones[this.genotipo[0]] > 
            Herramientas.inclinaciones[this.genotipo[this.genotipo.length-1]]){
            this.fitnessInclinacion++;
        }
        /*
        this.fitnessInclinacion += 
            Herramientas.inclinaciones[this.genotipo[this.genotipo.length-1]] - 
                Herramientas.inclinaciones[this.genotipo[0]];
        System.out.println(
            Herramientas.inclinaciones[this.genotipo[this.genotipo.length-1]] + " - " +
            Herramientas.inclinaciones[this.genotipo[0]] + " = " + 
            this.fitnessInclinacion
        );
        */
        //System.out.println("Inclinación: " + this.fitnessInclinacion);
        //System.out.println("");
    }
    
    /*
    Da un número demasiado grande!
    private void calcularFitnessGeneral() {
        this.fitnessGeneral = 0;
        double VALORCALORIFICO = 59.4e6;
        double masa = 1000;
        double velocidad = 50;
        double energiaRequerida = 1 / (0.95 * 0.03584);
        double distancia, inclinacion;
        // Recorremos el genotipo del individuo y vamos consultando 2 pts a la vez
        for (int i = 0; i < this.genotipo.length-1; i++) {
            distancia = Herramientas.distancias[this.genotipo[i]][this.genotipo[i+1]];
            inclinacion = Herramientas.inclinaciones[this.genotipo[i]]- 
                Herramientas.inclinaciones[this.genotipo[i+1]];
            energiaRequerida *= (masa*9.81*
                Math.cos(Math.atan(Math.toRadians(inclinacion/distancia))));
            energiaRequerida *= 0.015*(0.3/2)*0.3*1.8*Math.pow(velocidad, 2);
            energiaRequerida *= distancia;
            this.fitnessGeneral += energiaRequerida / VALORCALORIFICO;
        }
        
        distancia = 
            Herramientas.distancias[this.genotipo[0]]
                [this.genotipo[this.getGenotipo().length-1]];
        inclinacion = Herramientas.inclinaciones[this.genotipo[0]]- 
            Herramientas.inclinaciones[this.genotipo[this.getGenotipo().length-1]];
        energiaRequerida *= (masa*9.81*Math.cos(Math.atan(Math.toRadians(
            inclinacion/distancia))));
        energiaRequerida *= 0.015*(0.3/2)*0.3*1.8*Math.pow(velocidad, 2);
        energiaRequerida *= distancia;
        
        // Agregar el primero con el último
        this.fitnessGeneral += energiaRequerida / VALORCALORIFICO;
        System.out.println("");
    }
    */
    
    /*
    Se busca minimizar el número de subidas y la distancia recorrida.
    */
    private void calcularFitnessGeneral() {
        // Multiplicamos cada fitness por una constante k_# para definir un nivel de importancia
        double k1 = 1;
        double k2 = 1 - k1;
//        this.fitnessDistancia *= k1;
//        this.fitnessInclinacion *= k2;
        //this.fitnessGeneral = (k1 * this.fitnessDistancia) + (k2 * this.fitnessInclinacion);
        this.fitnessGeneral = this.fitnessDistancia;
    }
    
    // ### MÉTODOS DE ACCESO ###
    public int getN() {
        return n;
    }

    public int getCiudadInicial() {
        return ciudadInicial;
    }

    public double getFitnessDistancia() {
        return fitnessDistancia;
    }

    public double getFitnessInclinacion() {
        return fitnessInclinacion;
    }

    public double getFitnessGeneral() {
        return fitnessGeneral;
    }

    public int[] getGenotipo() {
        return genotipo;
    }  
    
    public static void main(String args[]) {
        int numCiudades = 10;
        Herramientas.cargarDistancias();
        Herramientas.generarInclinacionesAleatorias(numCiudades, 11);
        Individuo ind = new Individuo(numCiudades, 1);
    }
}
