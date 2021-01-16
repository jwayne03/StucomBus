package management;

import utils.Printer;
import worker.Worker;

public class Management {

    private Printer printer;
    private Worker worker;

    public Management() {
        printer = new Printer();
        worker = new Worker();
    }

    public void run() {
        printer.managementmenu();

        int option = worker.askInt("Introduce an option: ");

        switch (option) {
            case 1:
                assignRoutePassenger();
                break;
            case 2:
                deletePassengerRoute();
                break;
            case 3:
                seePassengerRoute();
                 break;
            default:
                printer.needToIntroduceAnOption();
                break;
        }
    }

    private void assignRoutePassenger() {

    }

    private void deletePassengerRoute() {

    }

    private void seePassengerRoute() {

    }
}
