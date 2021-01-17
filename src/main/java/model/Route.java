package model;

public class Route {

    private int route_id;
    private String tuition;
    private String dni;
    private int origin;
    private int destination;
    private String departure;
    private String arrive;

    public Route() {

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
        return "Route{" +
                "route_id=" + route_id +
                ", tuition='" + tuition + '\'' +
                ", dni=" + dni +
                ", origin=" + origin +
                ", destination=" + destination +
                ", departure='" + departure + '\'' +
                ", arrive='" + arrive + '\'' +
                '}';
    }
}
