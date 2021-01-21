package utils;

public class Printer {

    public void welcome() {
        System.out.println("Hello to StucomBus, what do you want to do?");
        System.out.println("1 - Administration");
        System.out.println("2 - Management");
        System.out.println("0 - Exit");
    }

    public void administrationmenu() {
        System.out.println("What do you want to do?");
        System.out.println("1 - Register a driver");
        System.out.println("2 - Unsubscribe a driver");
        System.out.println("3 - Register a vehicle");
        System.out.println("4 - Unsubscribe a vehicle");
        System.out.println("5 - Register a new route");
        System.out.println("6 - Unsubscribe an existing route");
        System.out.println("7 - Show list of routes");
    }

    public void managementmenu() {
        System.out.println("What do you want to do?");
        System.out.println("1 - Assign route to a passenger");
        System.out.println("2 - Delete passenger of a route");
        System.out.println("3 - See the passenger of the route");
    }

    public void needToIntroduceAnOption() {
        System.out.println("You need to introduce an option");
    }

    public void routeRegisteredSuccessfully(int route_id, String tuition, String dni, int origin, int destination, String departure, String arrive) {
        System.out.println("New route registered successfully");
        System.out.println("route id: " + route_id);
        System.out.println("tuition: " + tuition);
        System.out.println("DNI: " + dni);
        System.out.println("origin: " + origin);
        System.out.println("destination: " + destination);
        System.out.println("departure: " + departure);
        System.out.println("arrive: " + arrive);
    }

    public void cantDeleteThisDriver() {
        System.out.println("You can't delete this driver because have a route assigned");
    }

    public void driverDontExistInTheSystem() {
        System.out.println("This driver doesn't exists in the system");
    }

    public void userHasChooseToExit() {
        System.out.println("You have chose exit, Good Bye!");
    }

    public void driverRemovedSuccessfully() {
        System.out.println("This driver has been removed successfully");
    }
}
