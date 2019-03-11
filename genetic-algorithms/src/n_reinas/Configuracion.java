package n_reinas;
/**
 *
 * @author david
 */
public class Configuracion {
    private int numGeneraciones;
    private int tamPoblacion;
    private double probMuta;
    private double pMuestra;
    private int mascara[];
    private int n; // Representa el tamaño del genotipo
    private Seleccion.TipoSeleccion tipoSelecion[];

    public Configuracion(int numGeneraciones, int tamPoblacion, double probMuta, 
        double pMuestra, int n, Seleccion.TipoSeleccion[] tipoSelecion) {
        this.n = n;
        this.numGeneraciones = numGeneraciones;
        this.tamPoblacion = tamPoblacion;
        this.probMuta = probMuta;
        this.pMuestra = pMuestra;
        this.mascara = Cruza.generarMascaraAleatoria(n);
        this.tipoSelecion = tipoSelecion;
    }
    
    // Configuración por defecto
    // Inicialización por defecto de una conf
    public Configuracion(int n) {
        this.n = n; // tamaño genotipo
        this.numGeneraciones = 10000;
        this.tamPoblacion = 50;
        this.probMuta = 0.2;
        this.pMuestra = 0.2;
        this.mascara = Cruza.generarMascaraAleatoria(this.n);
        this.tipoSelecion = new Seleccion.TipoSeleccion[]
            {Seleccion.TipoSeleccion.TORNEO, Seleccion.TipoSeleccion.RANDOM};
    }
    
    public Individuo aplicarSeleccion(Poblacion p, int i) {
        Individuo ind;
        // Evaluar i
        switch(this.tipoSelecion[i]) {
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

    public double getpMuestra() {
        return pMuestra;
    }

    public void setpMuestra(double pMuestra) {
        this.pMuestra = pMuestra;
    }

    public int[] getMascara() {
        return mascara;
    }

    public void setMascara(int[] mascara) {
        this.mascara = mascara;
    }

    public Seleccion.TipoSeleccion[] getTipoSelecion() {
        return tipoSelecion;
    }

    public void setTipoSelecion(Seleccion.TipoSeleccion[] tipoSelecion) {
        this.tipoSelecion = tipoSelecion;
    }

    public int getN() {
        return n;
    }
    
}
