package sat_3;

/**
 *
 * @author david
 */
public class Configuracion {
    private int numGeneraciones;
    private int tamPoblacion;
    private double probMuta;
    private int mascara[];
    private Seleccion.TipoSeleccion tipoSeleccion[];

    public Configuracion(int numGeneraciones, int tamPoblacion, double probMuta, 
        int[] mascara, Seleccion.TipoSeleccion[] tipoSeleccion) {
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.mascara = mascara;
        this.tipoSeleccion = tipoSeleccion;
    }
    
    public Individuo aplicarSeleccion(Poblacion p, int i) {
        Individuo ind;
        // Evaluar i
        switch(this.tipoSeleccion[i]) {
            case RANDOM:
                ind = Seleccion.seleccionAleatoria(p);
                break;
            case TORNEO:
                ind = Seleccion.seleccionTorneo(p);
                break;
            default:
                ind = null;
        }
        return ind;
    }
    
    // Métodos de acceso

    public int getNumGeneraciones() {
        return numGeneraciones;
    }

    public void setNumGeneraciones(int numGeneraciones) {
        this.numGeneraciones = numGeneraciones;
    }

    public int getTamPoblacion() {
        return tamPoblacion;
    }

    public void setTamPoblacion(int tamPoblacion) {
        this.tamPoblacion = tamPoblacion;
    }

    public double getProbMuta() {
        return probMuta;
    }

    public void setProbMuta(double probMuta) {
        this.probMuta = probMuta;
    }

    public int[] getMascara() {
        return mascara;
    }

    public void setMascara(int[] mascara) {
        this.mascara = mascara;
    }

    public Seleccion.TipoSeleccion[] getTipoSeleccion() {
        return tipoSeleccion;
    }

    public void setTipoSeleccion(Seleccion.TipoSeleccion[] tipoSeleccion) {
        this.tipoSeleccion = tipoSeleccion;
    }
}
