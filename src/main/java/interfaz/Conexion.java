package interfaz;


public class Conexion<T extends Comparable<T>>{
    private double ponderacion;
    private ABB<T> vuelos;

    public Conexion(double ponderacion){
        this.ponderacion = ponderacion;
        this.vuelos = new ABB<T>();
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    @Override
    public String toString() {
        return "ponderacion=" + ponderacion;
    }

    public boolean existeVuelo(T dato) {
        return vuelos.buscar(dato) != null;
    }

    public void agregarVuelo(T dato) {
        vuelos.insertar(dato);
    }
}
