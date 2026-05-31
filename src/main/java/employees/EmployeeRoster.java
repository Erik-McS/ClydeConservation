package employees;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class that will hold the employees of Clyde Conservation
 */
public class EmployeeRoster implements Serializable{
    private static ArrayList<Employee> employees=new ArrayList<>();
    // setting a filename for the employee roster file
    private static final String FILE_NAME="roster.dat";
    // using a private constructor to prevent the creation of EmployeeRoster objects
    private EmployeeRoster(){}
    /**
     * The method will display the details of all the employees in the collection.
     */
    public static void displayEmployees(){
        // local variable to display the Employee position in the collection.
        // if empty, display status.
        if (employees.isEmpty())
            System.out.println("There is no employees stored");
        else{
            // displaying the employees
            for (Employee employee : employees) {
                // displaying the index and the employee details.
                System.out.println("----- Employee ID: " + employee.getID()+" -----");
                System.out.println(employee.getDetails());
            }
        }
    }
    /**
     * Method do display the keepers present in the Employee roster
     */
    public static void  displayKeepers(){
        // if empty, display status.
        if (employees.isEmpty()){
            System.out.println("There is no employees stored in the system");}
        else{
            // using an iterator to loop over the employee collection.
            Iterator<Employee> iter=employees.iterator();
            while (iter.hasNext()){
                // displaying the index and the employee details.
                Employee employee= iter.next();
                System.out.println("-----------------------------------------------------");
                if (employee instanceof Keeper){
                    Keeper emp=(Keeper) employee;
                    System.out.println("----- Keeper ID: "+emp.getID()+" -----");
                    System.out.println("Name: "+emp.getFirstName()+"\nSurname: "+emp.getLastName());
                }
            }
        }
    }
    /**
     * Method used to get the number of Keepers in the roster.
     * <p>
     * this is used when loading the roster from file, to update the ID_BASE so we don't get duplicate IDs
     * @return Number of Keepers in roster
     */
    public static int countKeepers(){
        if (employees.isEmpty())
            return 0;
        else{
            int count=0;
            // using an iterator to loop over the employee collection.
            Iterator<Employee> iter=employees.iterator();
            while (iter.hasNext()){
                // displaying the index and the employee details.
                Employee employee= iter.next();
                if (employee instanceof Keeper){
                    count++;
                }
            }
            return count;
        }
    }
    /**
     * Method to search the employee roster and return a keeper ogject corresponding to the passed keeperID
     * @param keeperID ID of the Keeper
     * @return the searched Keeper, or null if not found
     */
    public static Keeper getKeeper(int keeperID){
        Iterator<Employee> iter= employees.iterator();
        while (iter.hasNext()){
            Employee employee=iter.next();
            if (employee instanceof Keeper){
               Keeper kp=(Keeper) employee;
               if (kp.getID()==keeperID)
                   return kp;
            }
        }
        return null;
    }

    /**
     * Function to return the filename for the roster
     * @return File name
     */
    public static String getFileName(){
        return FILE_NAME;
    }

    /**
     * Method to add a new employee to the collection
     * @param employee Employee to add to the roster
     */
    public static void addEmployee(Employee employee){
        // testing if the person is in the roster
        if (!newEmployeeExists(employee.getName(), employee.getSurname())) {
            employees.add(employee);
            EmployeeRoster.saveRoster();
        }
        else
            System.out.println("\n----- This person is already in the roster -----");
    }

    /**
     * This method will save the Roster ArrayList in a file
     * <p>
     * Link to resource found while searching for how-to:
     * <p>
     * <a href="https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the">Saving ArrayList in a file</a>
     * @see FileOutputStream
     * @see ObjectOutputStream
     *
     */
    public static void saveRoster(){
        // try-catch to get any IO errors
        try
        (
            // initialise a file output stream for the file
            FileOutputStream fos=new FileOutputStream(FILE_NAME);
            // create an object stream to write in the file stream
            ObjectOutputStream oos=new ObjectOutputStream(fos);
        ){
            // writing the employee ArrayList in the file
            oos.writeObject(employees);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to load a saved roster from a file
     * @see FileInputStream
     * @see ObjectInputStream
     */
    public static void loadRoster(){

        try
        (
            FileInputStream fis=new FileInputStream(FILE_NAME);
            ObjectInputStream ois=new ObjectInputStream(fis);
        )
        {
            // clearing the ArrayList to make room to the new one
            employees.clear();
            try{
                // loading the roster
                employees=(ArrayList<Employee>) ois.readObject();
                // modifying the ID_base to account for saved animal
                Keeper.KEEPER_ID_BASE=Keeper.KEEPER_ID_BASE+countKeepers();
            }
            catch (ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to test if someone with that name and surname is already in the roster.
     * <p>
     * this will be, by design choice, considered a duplicate and refused at record creation
     * @param name Name of the employee
     * @param surname Surname of the employee
     * @return Exists or not.
     */
    public static boolean newEmployeeExists(String name, String surname){

        for (Employee emp:employees){
            if (emp.getName().equals(name)&&emp.getSurname().equals(surname)){
                return true;
            }
        }
        return false;
    }
// End of class
}
