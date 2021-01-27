package query;

public class Query {

    // SELECTS

    public static final String SELECT_ALL_DRIVERS = "SELECT * FROM conductor";
    public static final String SELECT_ALL_BUSES = "SELECT * FROM autobus";
    public static final String SELECT_ALL_ROUTES = "SELECT * FROM ruta";
    public static final String SELECT_ALL_PASSENGERS = "SELECT * FROM pasajero";

    // INSERTS

    public static final String INSERT_INTO_CONDUCTOR = "INSERT INTO conductor (dni, nombre, apellido) VALUES (?,?,?)";
    public static final String INSERT_INTO_BUS = "INSERT INTO autobus (matricula, asientos) VALUES (?,?)";
    public static final String INSERT_INTO_ROUTE = "INSERT INTO ruta (idRuta, matricula, conductorDNI, fecha_salida, fecha_llegada, ciudad_origen, ciudad_destino) VALUES(?,?,?,?,?,?,?)";
    public static final String INSERT_INTO_PASSENGER = "INSERT INTO pasajero (dni, nombre, apellido) VALUES (?,?)";

    // DELETES

    public static final String DELETE_DRIVER_DNI = "DELETE FROM conductor WHERE dni= ?";
    public static final String DELETE_BUS_TUITION = "DELETE FROM autobus WHERE matricula= ?";
    public static final String DELETE_ROUTE_IDROUTE = "DELETE FROM ruta WHERE idRuta= ?";
}
