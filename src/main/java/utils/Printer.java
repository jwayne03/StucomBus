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

    public void thereAreNotExistingRoutes() {
        System.out.println("There are not existing routes in the database.");
    }

    public void thisDriverAlreadyExist() {
        System.out.println("This driver already exist, please try to create a new driver.");
    }

    public void driverHasCreateSuccessfully() {
        System.out.println("The driver has been created successfully");
    }

    public void thereAreNotVehiclesRegisterYet() {
        System.out.println("There are not vehicles registered.");
    }

    public void thereAreNoRoutes() {
        System.out.println("There are not routes registered.");
    }

    public void thisDriverNoExists() {
        System.out.println("This driver doesn't exist");
    }

    public void driverCreated(String name, String surname, String dni) {
        System.out.println("Element created " + name + " " + surname + " " + dni);
    }

    public void passengerCreated(String name) {
        System.out.println(name + " has been created successfully");
    }

    public void createOrTakeRoute() {
        System.out.println("1 - Create new passsenger");
        System.out.println("2 - Assign new route to a passenger");
    }

    public void thePassengerHasBeenAddedToRoute() {
        System.out.println("The passenger has been added successfully to route");
    }

    public void routeReserved(int id_route, String tuition, String driverDNI, int origin, int destiny, String departure, String arrive) {
        System.out.println("The route has been registered successfully");
        System.out.println("ID route: " + id_route);
        System.out.println("Tuition: " + tuition);
        System.out.println("Driver DNI: " + driverDNI);
        origin(origin);

        destination(destiny);
        System.out.println("Departure: " + departure);
        System.out.println("Arrive: " + arrive);
    }

    private void origin(int args) {
        switch (args) {
            case 1:
                System.out.println("Origin: Madrid ");
                break;
            case 2:
                System.out.println("Origin: Barcelona");
                break;
            case 3:
                System.out.println("Origin: Valencia");
                break;
            case 4:
                System.out.println("Origin: Sevilla");
                break;
            case 5:
                System.out.println("Origin: Zaragoza");
                break;
            case 6:
                System.out.println("Origin: Malaga");
                break;
        }
    }

    private void destination(int args) {
        switch (args) {
            case 1:
                System.out.println("Destiny: Madrid ");
                break;
            case 2:
                System.out.println("Destiny: Barcelona");
                break;
            case 3:
                System.out.println("Destiny: Valencia");
                break;
            case 4:
                System.out.println("Destiny: Sevilla");
                break;
            case 5:
                System.out.println("Destiny: Zaragoza");
                break;
            case 6:
                System.out.println("Destiny: Malaga");
                break;
        }
    }
}
