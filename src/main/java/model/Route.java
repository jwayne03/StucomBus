package model;

public class Route {

    private int route_id;
    private int tuition;
    private int dni;
    private String origin;
    private String destination;
    private int departure;
    private int arrive;

    public Route(int route_id, int tuition, int dni, String origin, String destination, int departure, int arrive) {
        this.route_id = route_id;
        this.tuition = tuition;
        this.dni = dni;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrive = arrive;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getArrive() {
        return arrive;
    }

    public void setArrive(int arrive) {
        this.arrive = arrive;
    }
}
