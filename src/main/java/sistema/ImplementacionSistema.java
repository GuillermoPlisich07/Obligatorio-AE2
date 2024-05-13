package sistema;

import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private ABB<Pasajero> ABBPasajeros;

    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {

        if(maxAeropuertos<=5){
            return Retorno.error1("Deben ser mas de 5 Aeropuertos");
        }
        if(maxAerolineas<=3){
            return Retorno.error2("Deben ser mas de 3 Aerolineas");
        }

        this.ABBPasajeros = new ABB<Pasajero>();



        return Retorno.ok();
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {

        if(cedula == null || nombre == null || telefono == null ||
                cedula.isEmpty()|| nombre.isEmpty() || telefono.isEmpty() || categoria == null){
            return Retorno.error1("Algunos de los parametros es vacio o null");
        }
        if(!Pasajero.validarCedula(cedula)){
            return Retorno.error2("La cedula no tiene formato valido");
        }
        Pasajero aux = new Pasajero(cedula);
        if(this.ABBPasajeros.buscar(aux)!=null){
            return Retorno.error3("El pasajero que intenta ingresar ya fue registrado");
        }
        Pasajero nuevo = new Pasajero(cedula,nombre,telefono,categoria);
        ABBPasajeros.insertar(nuevo);
        return Retorno.ok();
    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        if(cedula == null || cedula.isEmpty()){
            return  Retorno.error1("La cedula es vacia!");
        }
        if(!Pasajero.validarCedula(cedula)){
            return Retorno.error2("La cedula no tiene formato valido");
        }
        Pasajero aux = new Pasajero(cedula);
        Pasajero res =this.ABBPasajeros.buscar(aux);
        System.out.println("res = " + res);
        if(res!=null){
            return Retorno.ok(this.ABBPasajeros.getContadorBusqueda(),res.toString());
        }else{
            return Retorno.error3("El Pasajero con esta cedula no existe!");
        }

    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarAerolineasDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return Retorno.noImplementada();
    }


}
