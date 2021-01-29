package model;

public class Route {

    private int route_id;
    private String tuition;
    private String dni;
    private int origin;
    private int destination;
    private String departure;
    private String arrive;

    private Driver driver;
    private int passengerRoute_id;
    private String passengerDNI;

    public Route() {
        this.driver = new Driver();
    }

    public Route(int route_id, String tuition, String dni, int origin, int destination, String departure, String arrive) {
        this.route_id = route_id;
        this.tuition = tuition;
        this.dni = dni;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrive = arrive;
    }

    public Route(int route_id, String passengerDNI, int passengerRoute_id) {
        this.route_id = route_id;
        this.passengerDNI = passengerDNI;
        this.passengerRoute_id = passengerRoute_id;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getPassengerRoute_id() {
        return passengerRoute_id;
    }

    public void setPassengerRoute_id(int passengerRoute_id) {
        this.passengerRoute_id = passengerRoute_id;
    }

    public String getPassengerDNI() {
        return passengerDNI;
    }

    public void setPassengerDNI(String passengerDNI) {
        this.passengerDNI = passengerDNI;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    @Override
    public String toString() {
        return "route_id: " + route_id +
                " \ntuition: " + tuition +
                " \ndni: " + dni +
                " \norigin: " + origin +
                " \ndestination: " + destination +
                " \ndeparture: " + departure +
                " \narrive: " + arrive + "\n";
    }
}
