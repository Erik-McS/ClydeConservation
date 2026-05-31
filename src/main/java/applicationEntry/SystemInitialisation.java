package applicationEntry;

import animals.Bird;
import animals.Mammal;
import animals.Menagerie;
import animals.Reptile;
import clydeconservationsystem.*;
import employees.Administrator;
import employees.AssistantKeeper;
import employees.EmployeeRoster;
import employees.HeadKeeper;

import java.io.File;

/**
 * This class is used to create and add some objects to the program collections.
 * <p>
 * it is not included in the class diagram as it is used to simulate previous activity or data entry from the paper system.
 * <p>
 * class is following the Singleton design pattern ( mostly private constructor and statics methods).
 * @author Erik
 */
public class SystemInitialisation {
    // empty and private constructor to prevent object creation.
    private SystemInitialisation(){}

    /**
     * This method will check if 3 of the 4 files used by this app are present in the app folder. if not, it will create some data in the collections and save them as:
     * <p>
     * 'cages.dat': The charity cages collections
     * <p>
     * 'roster.dat': The charity's employees
     * <p>
     * 'menagerie.dat': The charity's animals
     * <p>
     * 'assignments.dat': the assignments(Keeper-Cages), left empty is file is not created yet.
     */
    public static void initialiseData(){
        // check animals
        checkAnimalsFile();
        // check employees
        checkEmployeeFile();
        // check cages
        checkCagesFile();
        // check assignments
        checkAssignmentsFile();
    }

    // Check the files
    private static void checkCagesFile(){
        File f=new File("cages.dat");
        if (!f.exists()){
            createCageCollection();
        }
        else
            CagesCollection.loadCagesCollection();
    }
    private static void checkEmployeeFile(){
        File f=new File("roster.dat");
        if (!f.exists()){
            createEmployeeRoster();
        }
        else
            EmployeeRoster.loadRoster();
    }

    private static void checkAnimalsFile(){
        File f=new File("menagerie.dat");
        if (!f.exists()){
            createMenagerie();
        }
        else
            Menagerie.loadMenagerie();
    }
    private static void checkAssignmentsFile(){
        File f=new File("assignments.dat");
        if (f.exists())
            AssignmentsCollection.loadAssignment();
    }

    private static void createMenagerie(){
        // create some animals
        Menagerie.addAnimal(new Bird("Robin","Prey","Male","02/02/2021","05/01/2021"));
        Menagerie.addAnimal(new Mammal("Spot","Predator","Male","04/02/2021","05/04/2021"));
        Menagerie.addAnimal(new Reptile("Smiss","Predator","Male","04/02/2021","05/04/2021"));
        Menagerie.addAnimal(new Mammal("Daisy","Prey","Female","04/02/2021","05/04/2021"));
        Menagerie.addAnimal(new Bird("Catch","Prey","Female","02/02/2021","05/01/2021"));
        Menagerie.addAnimal(new Mammal("Squish","Prey","Male","04/02/2021","05/04/2021"));
        Menagerie.addAnimal(new Mammal("Tiger","Predator","Female","04/02/2021","05/04/2021"));
        Menagerie.addAnimal(new Reptile("Spot","Predator","Male","04/02/2021","05/04/2021"));
        Menagerie.addAnimal(new Bird("Blue","Prey","Male","04/02/2021","05/04/2021"));
        Menagerie.saveMenagerie();
        System.out.println("------ Menagerie Created: 9 animals added ------");

    }
    private static void createEmployeeRoster(){
        try{
            // one Admin
            EmployeeRoster.addEmployee(new Administrator("Mike","Tajid","0777223344","1 Glasgow av"));
            // one HeadKeeper
            EmployeeRoster.addEmployee(new HeadKeeper.HdKBuilder().setName("Erik").setLastName("Mseveney")
                    .setContactNumber("07755667788").setAddress("2 Glasgow av").HBuilder());
            // 4 Assistant Keepers
            EmployeeRoster.addEmployee(new AssistantKeeper("John","Smith","07745362711","3 Glasgow av"));
            EmployeeRoster.addEmployee(new AssistantKeeper("Tom","Tomas","07789653421","4 Glasgow av"));
            EmployeeRoster.addEmployee(new AssistantKeeper("Daryl","Make","07745109876","5 Glasgow av"));
            EmployeeRoster.addEmployee(new AssistantKeeper("Patrick","Brien","07794557789","6 Glasgow av"));
            // Saving the roster in a file
            EmployeeRoster.saveRoster();
            // printing the roster.
            System.out.println("------ Employee Roster Created: 1 Admin, 1 HeadKeeper, 4 Assistant Keepers ------");

        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
        }
    }
    private static void createCageCollection(){
        /**
         * From the brief:
         * We have five large cages with a capacity for ten animals, three medium
         * cages with a capacity for five animals and seven small cages with a
         * capacity for one animal.
         * **/

        // 5 large cages
        CagesCollection.addCage(new LargeCage());
        CagesCollection.addCage(new LargeCage());
        CagesCollection.addCage(new LargeCage());
        CagesCollection.addCage(new LargeCage());
        CagesCollection.addCage(new LargeCage());
        // 3 medium cages
        CagesCollection.addCage(new MediumCage());
        CagesCollection.addCage(new MediumCage());
        CagesCollection.addCage(new MediumCage());
        // 7 small cages
        CagesCollection.addCage(new SmallCage());
        CagesCollection.addCage(new SmallCage());
        CagesCollection.addCage(new SmallCage());
        CagesCollection.addCage(new SmallCage());
        CagesCollection.addCage(new SmallCage());
        CagesCollection.addCage(new SmallCage());
        CagesCollection.addCage(new SmallCage());

        CagesCollection.saveCagesCollection();
        System.out.println("------ Cage Collections Created: 5 Large Cages, 3 Medium Cages, 7 Small Cages ------");

    }
}
