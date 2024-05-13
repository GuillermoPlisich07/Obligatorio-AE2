package interfaz;

import java.util.List;

public class ABB<T extends Comparable<T>>{
    public Nodo<T> raiz;
    int contadorBusqueda;

    public int getContadorBusqueda() {
        return contadorBusqueda;
    }


    public void insertar(T dato){
        if(dato!=null){
            if(this.raiz==null){
                this.raiz = new Nodo<T>(dato);
            }else{
                insertar(this.raiz,dato);
            }
        }
    }

    private void insertar(Nodo<T> nodo,T dato){

        if(dato.compareTo(nodo.getDato())<0){
            if(nodo.getIzq() == null){
                nodo.setIzq(new Nodo<T>(dato));
            }else{
                insertar(nodo.getIzq(), dato);
            }
        }else if (dato.compareTo(nodo.getDato())>0){
            if(nodo.getDer() == null){
                nodo.setDer(new Nodo<T>(dato));
            }else{
                insertar(nodo.getDer(), dato);
            }
        }
    }

    public T buscar(T dato){
        this.contadorBusqueda=0;
        return buscar(this.raiz, dato);
    }

    private T buscar(Nodo<T> nodo , T dato){
        if(nodo!=null){
            if(nodo.getDato().equals(dato)){
                return nodo.getDato();
            }else if(nodo.getDato().compareTo(dato)>0){
                this.contadorBusqueda++;
                return (T)buscar(nodo.getIzq(),dato);
            }else{
                this.contadorBusqueda++;
                return (T)buscar(nodo.getDer(),dato);
            }
        }else{
            return null;
        }
    }

    public String listarDescendente(){
        return listarDescendente(this.raiz);
    }

    public String listarDescendente(Nodo<T> nodo){
        if(nodo != null){
            String izq;
            String der;
            if(nodo.getIzq()==null){
                izq = listarDescendente(nodo.getIzq());
            }else{
                izq = listarDescendente(nodo.getIzq())+'|';
            }
            if(nodo.getDer()==null){
                der = listarDescendente(nodo.getDer());
            }else{
                der = listarDescendente(nodo.getDer())+'|';
            }
            return izq+nodo.getDato().toString()+der;
        }
        return "";

    }
}
