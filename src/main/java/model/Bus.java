package model;

public class Bus {

    private int tuition;
    private int seating;

    public Bus(int tuition, int seating) {
        this.tuition = tuition;
        this.seating = seating;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getSeating() {
        return seating;
    }

    public void setSeating(int seating) {
        this.seating = seating;
    }
}
