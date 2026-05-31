package main.java.applicationEntry;

import main.java.clydeconservationsystem.*;
import main.java.employees.*;

/**
 * Main entry of the program
 */
public class ClydeConservationDriver {

    /**
     * Main method
     * @param args No parameters needed
     */
    public static void main(String[] args) {

        // initialising data
        // files
        // if not found, this will create the following 3 files in the folder:
        // - cages.dat
        // - menagerie.dat
        // - roster.dat
        // this files contain some cages, animals, employees to add to the system to play with
        // the assignment.dat file will be added once the first Cage-Keeper Assignment is created in-app
        // any operation that 'add','assign','create' will add the new objects to the respective file.
        // this will allow data persistence when using the app
        // to clear the collections, delete the 4 files.
        // to start with no data at all1 in the collections, delete or add // to the next line
        SystemInitialisation.initialiseData();

        // using the logAsMenu to simulate an Employee login and store the profile in a variable
        Employee loggedAs=Menus.logAsMenu();

        // this loop has no exit condition by itself as the exit option will be handled
        // by the Menus class itself
        do {
            // the switch statement takes the value returned by Menus.mainMenu() as parameter
            switch (Menus.mainMenu()){
                case 1:
                    // checking if the logged profile is a Keeper
                    if (loggedAs instanceof Keeper){
                        // entering the Head Keeper menu
                        Menus.headKeeperMenu((HeadKeeper)loggedAs);
                    }
                    else{
                        // unauthorised access message
                        System.out.println("""
                                            You do not have the correct accreditation to access the selected application.
                                            Please select the Application related to your role.
                                            """);
                    }
                    break;
                case 2:
                    // checking if the logged profile is a Keeper
                    if (loggedAs instanceof Administrator){
                        // entering the Admin menu
                        Menus.administratorMenu((Administrator)loggedAs);
                    }
                    else{
                        // unauthorised access message
                        System.out.println("""
                                            You do not have the correct accreditation to access the selected application.
                                            Please select the Application related to your role.
                                            """);
                    }
                    break;
                case 5:
                    System.out.println("Incorrect Choice entered");
                    break;
                default:
                    // looping until choice 1-2 is selected. the choice 3 in the Menus.mainMenu will use System.exit(0)
                    // to exit the application

                    break;
            }
        }
        while(true);
    }

}
