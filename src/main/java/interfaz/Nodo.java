package interfaz;

public class Nodo <T extends Comparable<T>> {

    private T dato;
    private Nodo izq;
    private Nodo der;

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public Nodo(T dato) {
        this.dato = dato;
        this.izq=null;
        this.der=null;
    }
}
