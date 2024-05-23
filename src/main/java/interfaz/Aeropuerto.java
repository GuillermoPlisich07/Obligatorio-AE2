package interfaz;

import java.util.Objects;

public class Aeropuerto implements Comparable<Aeropuerto>{
    String codigo;
    String nombre;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Aeropuerto(String codigo, String nombre)
    {
        this.codigo=codigo;
        this.nombre=nombre;

    }

    public Aeropuerto(String codigo)
    {
        this.codigo=codigo;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int compareTo(Aeropuerto o) {
        return o.codigo.compareTo(this.codigo);
    }
}
