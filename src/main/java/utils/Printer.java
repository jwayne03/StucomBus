package utils;

public class Printer {

    public void welcome() {
        System.out.println("Hello to StucomBus, what do you want to do?");
        System.out.println("1 - Administration");
        System.out.println("2 - Management");
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
}
