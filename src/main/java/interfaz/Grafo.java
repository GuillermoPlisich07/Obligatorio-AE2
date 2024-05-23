package interfaz;

public class Grafo<T>{

    private final int cantMaxVertices;
    private Vertice<T>[] vertices;
    private Conexion[][] conexiones;
    private int cantidadVertices;

    public int getCantMaxVertices() {
        return cantMaxVertices;
    }

    public Grafo(int cantMaxVertices){
        this.cantMaxVertices = cantMaxVertices;
        this.vertices = new Vertice[cantMaxVertices];
        this.conexiones = new Conexion[cantMaxVertices][cantMaxVertices];
        this.cantidadVertices = 0;
    }

    public Vertice<T>[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertice<T>[] vertices) {
        this.vertices = vertices;
    }

    public Conexion[][] getConexiones() {
        return conexiones;
    }

    public void setConexiones(Conexion[][] conexiones) {
        this.conexiones = conexiones;
    }

    public int getCantidadVertices() {
        return cantidadVertices;
    }

    public void setCantidadVertices(int cantidadVertices) {
        this.cantidadVertices = cantidadVertices;
    }

    public int buscarAeropuerto(T dato) {
        for (int i = 0; i < this.cantidadVertices; i++) {
            if (this.vertices[i].getDato().equals(dato)) {
                return i;
            }
        }
        return -1;
    }

    public void agregarVertice(T dato) {
        if (this.getCantidadVertices() < this.getCantMaxVertices()) {
            this.getVertices()[this.getCantidadVertices()] = new Vertice<T>(dato);
            this.setCantidadVertices(this.getCantidadVertices()+1);
        }
    }

    public void agregarConexion(int origen, int destino, double ponderacion) {
        if (origen >= 0 && origen < this.getCantidadVertices() && destino >= 0 && destino < this.getCantidadVertices()) {
            if (this.conexiones[origen][destino] == null) {
                this.conexiones[origen][destino] = new Conexion(ponderacion);
            }
        }
    }


}
