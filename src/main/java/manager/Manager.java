package manager;

import administration.Administration;
import dao.DAO;
import management.Management;
import utils.Printer;
import worker.Worker;

public class Manager {

    private Administration administration;
    private Management management;
    private static Manager manager;
    private final Printer printer;
    private final Worker worker;
    private DAO dao;

    public Manager() {
        administration = new Administration();
        management = new Management();
        printer = new Printer();
        worker = new Worker();
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void run() {
        printer.welcome();

        int option = worker.askInt("Introduce an option:");

        switch (option) {
            case 1:
                administration.run();
                break;
            case 2:
                management.run();
                break;
            default:
                printer.needToIntroduceAnOption();
                break;
        }
    }
}
