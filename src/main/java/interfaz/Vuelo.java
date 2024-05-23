package interfaz;

public class Vuelo implements Comparable<Vuelo> {


    private String codigoDeVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;
    private String codigoAerolinea;

    public Vuelo(String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        this.codigoDeVuelo = codigoDeVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.codigoAerolinea = codigoAerolinea;
    }
    public Vuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }


    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public void setCodigoAerolinea(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }


    @Override
    public int compareTo(Vuelo o) {
        return o.codigoDeVuelo.compareTo(this.codigoDeVuelo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return codigoDeVuelo.equals(vuelo.codigoDeVuelo);
    }

}
