package interfaz;

import java.util.Objects;
import java.util.regex.*;

public class Pasajero implements Comparable<Pasajero> {

    String cedula;
    String nombre;
    String telefono;
    Categoria categoria;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Pasajero(String cedula, String nombre, String telefono, Categoria categoria){
        this.cedula=cedula;
        this.nombre=nombre;
        this.telefono=telefono;
        this.categoria=categoria;
    }

    public Pasajero(String cedula){
        this.cedula=cedula;
    }

    public static boolean validarCedula(String cedula) {
        // Expresión regular para validar cédula en el formato especificado
        String regex = "^[1-9]\\d{0,2}(\\.\\d{3}){2}-\\d{1}$";

        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(regex);

        // Crear un objeto Matcher para hacer la comparación
        Matcher matcher = pattern.matcher(cedula);

        // Verificar si la cédula coincide con el patrón
        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(cedula, pasajero.cedula);
    }

    @Override
    public int compareTo(Pasajero o) {
        return o.cedula.compareTo(this.cedula);
    }

    @Override
    public String toString() {
        return this.cedula+";"+this.nombre+";"+this.telefono+";"+this.categoria.getTexto();
    }
}
