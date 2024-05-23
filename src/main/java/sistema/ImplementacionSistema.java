package sistema;

import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private ABB<Pasajero> ABBPasajeros;
    private ABB<Pasajero>[] ABBPasajerosPorCategoria;
    private ABB<Aerolinea> ABBAerolinea;
    private Grafo<Aeropuerto> GrafoAeropuerto;
    private int maxAeropuertos;
    private int maxAerolineas;

    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {

        if(maxAeropuertos<=5){
            return Retorno.error1("Deben ser mas de 5 Aeropuertos");
        }
        if(maxAerolineas<=3){
            return Retorno.error2("Deben ser mas de 3 Aerolineas");
        }

        this.maxAeropuertos=maxAeropuertos;
        this.maxAerolineas=maxAerolineas;

        this.ABBPasajeros = new ABB<Pasajero>();

        this.ABBPasajerosPorCategoria = (ABB<Pasajero>[]) new ABB[3];
        this.ABBPasajerosPorCategoria[0] = new ABB<Pasajero>();
        this.ABBPasajerosPorCategoria[1] = new ABB<Pasajero>();
        this.ABBPasajerosPorCategoria[2] = new ABB<Pasajero>();

        this.ABBAerolinea = new ABB<Aerolinea>();
        this.GrafoAeropuerto = new Grafo<Aeropuerto>(maxAeropuertos);


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
        this.ABBPasajeros.insertar(nuevo);
        this.ABBPasajerosPorCategoria[categoria.getIndice()].insertar(nuevo);
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

        return Retorno.ok(ABBPasajeros.listarAscendente());
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        return Retorno.ok(this.ABBPasajerosPorCategoria[categoria.getIndice()].listarAscendente());
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
        if(this.maxAerolineas<=0){
            return Retorno.error1("Ya no se pueden ingresar mas Aerolineas");
        }
        if(codigo == null || nombre == null || codigo.isEmpty() || nombre.isEmpty()) {
            return Retorno.error2("Algunos de los parametros es vacio o null");
        }
        Aerolinea aux = new Aerolinea(codigo);
        if(this.ABBAerolinea.buscar(aux)!=null){
            return Retorno.error3("La Aerolinea que intenta ingresar ya fue registrado");
        }

        Aerolinea nuevo = new Aerolinea(codigo,nombre);
        ABBAerolinea.insertar(nuevo);
        this.maxAerolineas--;
        return Retorno.ok();
    }

    @Override
    public Retorno listarAerolineasDescendente() {

        return Retorno.ok(ABBAerolinea.listarDescendente());
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {

        // Verificar si se puede agregar más aeropuertos
        if (this.GrafoAeropuerto.getCantidadVertices() < this.maxAeropuertos) {
            // Validación de parámetros
            if (codigo != null && nombre != null && !codigo.isEmpty() && !nombre.isEmpty()) {
                // Verificar si el aeropuerto ya existe
                Aeropuerto nuevoAeropuerto = new Aeropuerto(codigo, nombre);
                int indiceExistente = this.GrafoAeropuerto.buscarAeropuerto(nuevoAeropuerto);

                if (indiceExistente == -1) {
                    // Agregar el aeropuerto al grafo
                    this.GrafoAeropuerto.agregarVertice(nuevoAeropuerto);
                    return Retorno.ok();
                }
                return Retorno.error3("El aeropuerto ya existe");
            }
            return Retorno.error2("El código y el nombre no pueden ser nulos o vacíos");
        }
        return Retorno.error1("Ya se llegó al tope máximo de Aeropuertos");

    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        if (kilometros > 0) {
            if (codigoAeropuertoOrigen != null && codigoAeropuertoDestino != null && !codigoAeropuertoOrigen.isEmpty() && !codigoAeropuertoDestino.isEmpty()) {
                int indiceOrigen = GrafoAeropuerto.buscarAeropuerto(new Aeropuerto(codigoAeropuertoOrigen));
                int indiceDestino = GrafoAeropuerto.buscarAeropuerto(new Aeropuerto(codigoAeropuertoDestino));

                if (indiceOrigen == -1 ) {
                    return Retorno.error3("El aeropuerto de origen no existe");
                } else if (indiceDestino == -1) {
                    return Retorno.error4("El aeropuerto de destino no existe");
                }

                // Verificar si ya existe una conexión entre los aeropuertos
                if (GrafoAeropuerto.getConexiones()[indiceOrigen][indiceDestino] != null) {
                    return Retorno.error5("Ya existe una conexión entre los aeropuertos");
                }

                // Registrar la conexión
                this.GrafoAeropuerto.agregarConexion(indiceOrigen, indiceDestino, kilometros);
                return Retorno.ok();
            }
            return Retorno.error2("Los códigos de los aeropuertos no pueden ser nulos o vacíos");
        }
        return Retorno.error1("Los kilómetros deben ser mayores que 0");
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos,
                                  double costoEnDolares, String codigoAerolinea) {

        if (codigoCiudadOrigen != null && codigoAeropuertoDestino != null && codigoDeVuelo != null && codigoAerolinea != null
                && !codigoCiudadOrigen.isEmpty() && !codigoAeropuertoDestino.isEmpty() && !codigoDeVuelo.isEmpty() && !codigoAerolinea.isEmpty()) {
            if (combustible > 0 && minutos > 0 && costoEnDolares > 0) {
                int indiceOrigen = this.GrafoAeropuerto.buscarAeropuerto(new Aeropuerto(codigoCiudadOrigen));
                int indiceDestino = this.GrafoAeropuerto.buscarAeropuerto(new Aeropuerto(codigoAeropuertoDestino));

                if (indiceOrigen == -1) {
                    return Retorno.error3("Uno o ambos aeropuertos no existen");
                } else if (indiceDestino == -1) {
                    return Retorno.error4("Uno o ambos aeropuertos no existen");
                }

                Aerolinea existeAerolinea = this.ABBAerolinea.buscar(new Aerolinea(codigoAerolinea));
                if (existeAerolinea == null) {
                    return Retorno.error5("No existe la aerolinea que ingreso");
                }

                Conexion conex = this.GrafoAeropuerto.getConexiones()[indiceOrigen][indiceDestino];

                if (conex == null) {
                    return Retorno.error6("No existe una conexión entre los aeropuertos");
                }

                if (conex.existeVuelo(new Vuelo(codigoDeVuelo))) {
                    return Retorno.error7("Ya existe un vuelo con el mismo código en esta conexión");
                }

                Vuelo nuevoVuelo = new Vuelo(codigoDeVuelo, combustible, minutos, costoEnDolares, codigoAerolinea);
                conex.agregarVuelo(nuevoVuelo);
                return Retorno.ok();
            }
            return Retorno.error1("Los valores de tipo numerico no pueden ser iguales o menores a cero");
        }
        return Retorno.error2("Las variables del tipo string no pueden ser vacias");
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
