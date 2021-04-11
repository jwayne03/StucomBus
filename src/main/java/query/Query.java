package query;

public class Query {

    // SELECTS

    public static final String SELECT_ALL_DRIVERS = "SELECT * FROM conductor";
    public static final String SELECT_ALL_BUSES = "SELECT * FROM autobus";
    public static final String SELECT_ALL_ROUTES = "SELECT * FROM ruta";
    public static final String SELECT_ALL_PASSENGERS = "SELECT * FROM pasajero";
    public static final String SELECT_ALL_PASSENGERROUTES = "SELECT * FROM pasajerosruta";
    public static final String SELECT_ALL_CITIES = "SELECT * FROM ciudad";
    public static final String SELECT_BUSES_WITH_CONDUCTOR = "SELECT b.matricula, b.asientos FROM autobus b, ruta r WHERE r.conductorDni = ? and r.matricula = b.matricula";
    public static final String SELECT_QUANTITY_PASSENGERS_AGAINST_TUITION = "SELECT a.matricula, r.idruta, count(pr.dniPasajero) as totalpasajeros FROM stucombus.autobus a, stucombus.ruta r, stucombus.pasajerosruta pr WHERE r.matricula = ? and r.matricula = a.matricula and r.idruta = pr.idrutagroup by r.idruta";

    // INSERTS

    public static final String INSERT_INTO_CONDUCTOR = "INSERT INTO conductor (dni, nombre, apellido) VALUES (?,?,?)";
    public static final String INSERT_INTO_BUS = "INSERT INTO autobus (matricula, asientos) VALUES (?,?)";
    public static final String INSERT_INTO_ROUTE = "INSERT INTO ruta (idRuta, matricula, conductorDNI, fecha_salida, fecha_llegada, ciudad_origen, ciudad_destino) VALUES(?,?,?,?,?,?,?)";
    public static final String INSERT_INTO_PASSENGER = "INSERT INTO pasajero (dni, nombre, apellido) VALUES (?,?,?)";
    public static final String INSERT_INTO_PASSENGERROUTES = "INSERT INTO pasajerosruta (dniPasajero, idRuta, idPasajeroRuta) VALUES (?,?,?)";
    public static final String INSERT_CITY = "INSERT INTO ciudad (id, nombre) VALUE (?,?)";

    // DELETES

    public static final String DELETE_DRIVER_DNI = "DELETE FROM conductor WHERE dni= ?";
    public static final String DELETE_BUS_TUITION = "DELETE FROM autobus WHERE matricula= ?";
    public static final String DELETE_ROUTE_IDROUTE = "DELETE FROM ruta WHERE idRuta= ?";
    public static final String DELETE_CITY = "DELETE FROM ciudad WHERE id = ?";
}
