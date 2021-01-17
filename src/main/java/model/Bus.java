package model;

public class Bus {

    private String tuition;
    private int seating;

    public Bus() {

    }

    public Bus(String tuition, int seating) {
        this.tuition = tuition;
        this.seating = seating;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public int getSeating() {
        return seating;
    }

    public void setSeating(int seating) {
        this.seating = seating;
    }

    @Override
    public String toString() {
        return "Tuition: " + tuition +
                " Seating=" + seating + "\n";
    }
}
