package animals;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import clydeconservationsystem.CagesCollection;

/**
 * The Menagerie class will store all the animals in Clyde Conservation's care.
 * <p>
 * This class have static methods that will handle the collection of animals.
 * <p>
 * To check: seems I actually designed by chance a class using a Singleton design pattern:
 * <p>
 * <a href="https://www.javatpoint.com/singleton-design-pattern-in-java">Singleton design pattern in Java</a>
 * <p>
 * Missing a method to return the array though, but
 *  it is not needed in my design
 *
 */
public class Menagerie {
    private static final Logger LOGGER = Logger.getLogger(Menagerie.class.getName());
    // ArrayList to store the animals
    private static ArrayList<Animal> menagerieCollection=new ArrayList<>();
    // constant to hold the name of the menagerie file
    private static final String FILE_NAME ="menagerie.dat";
    // using a private constructor to prevent the creation of Menagerie objects
    private Menagerie(){}
    /**
     * The method will display the details of all the animals in the collection.
     */
    public static void displayAllAnimals(){
        // local variable to display the animal position in the collection.
        // if empty, display status.
        if (menagerieCollection.isEmpty())
            LOGGER.info("There is no animals stored");
        else{
            // using an iterator to loop over the animal collection.
            Iterator<Animal> iter=menagerieCollection.iterator();
            while (iter.hasNext()){
                // displaying the index and the animal details.
                Animal animal= iter.next();
                LOGGER.info(animal.getDetails());
            }
        }
    }

    /**
     * Method to display all the unassigned animals
     */
    public static void displayUnassignedAnimals(){
            Iterator<Animal>iter= menagerieCollection.iterator();

            while (iter.hasNext()) {
                Animal an = iter.next();
                if (!CagesCollection.isAssigned(an)) {
                    LOGGER.info("--- Animal ID: " + an.getAnimalID() + " --- Name: " + an.getName() +
                            " --- Type: " + an.getType() + " --- Category: " + an.getCategory());
                }
            }
    }
    /**
     * Method to count all the unassigned animals, the result will be used as a test condition
     * @return Number of unassigned animals
     */
    public static int countUnassignedAnimals(){
        int count=0;
        Iterator<Animal>iter= menagerieCollection.iterator();
        while (iter.hasNext()) {
            Animal an = iter.next();
            if (!CagesCollection.isAssigned(an))
                count++;
        }
        return count;
    }

    /**
     * Method to add a new animal to the collection
     * @param newAnimal Animal to add.
     */
    public static void addAnimal(Animal newAnimal){
        menagerieCollection.add(newAnimal);
    }

    /**
     * Method to remove an animal from the collection using its index
     * @param index Position of the animal in the collection
     */
    public static void  removeAnimal(int index){
        menagerieCollection.remove(index);
    }

    /**
     * Method to remove an animal from the collection using its name
     * @param name Name of the animal
     */
    public static void  removeAnimal(String name){

        Iterator<Animal> iter=menagerieCollection.iterator();
        while (iter.hasNext()){
            Animal animal= iter.next();
            if (animal.getName().equals(name))
                iter.remove();
        }
    }

    /**
     * This method will save the Menagerie ArrayList in a file
     * Link to resource found while searching for how-to:
     * <p>
     * <a href="https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the">Java - How Can I Write My ArrayList to a file</a>
     * @see FileOutputStream
     * @see ObjectOutputStream
     *
     */
    public static void saveMenagerie(){
        // try-catch to get any IO errors
        try(
            FileOutputStream fos=new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
        ){
            // initialise an output stream for the file
            oos.writeObject(menagerieCollection);
        }
        catch (IOException e){
            LOGGER.info("Save_Menagerie: "+e.getMessage());
        }
    }
    /**
     * Method to load a saved menagerie from a file
     * @see FileInputStream
     * @see ObjectInputStream
     */
    public static void loadMenagerie(){

    try (FileInputStream fis = new FileInputStream(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
        menagerieCollection.clear();
        menagerieCollection = (ArrayList<Animal>) ois.readObject();
        Animal.offsetIdBase(menagerieCollection.size());
        }catch (ClassNotFoundException e) {
        LOGGER.warning("Load_Menagerie1: " + e.getMessage());
    } catch (IOException e) {
        LOGGER.warning("Load_Menagerie2: " + e.getMessage());
    }
    }
    /**
     * Method to check if an animal exists in the menagerie
     * @param animal Animal to check
     * @return True or False
     */
    public static boolean isPresent(Animal animal){
        if (menagerieCollection.isEmpty())
            return false;
        else{
            Iterator<Animal> iter= menagerieCollection.iterator();
            while (iter.hasNext()){
                Animal an= iter.next();
                if (an.getAnimalID()==animal.getAnimalID())
                    return true;
            }
            return false;
        }
    }

    /**
     * Method to check if an object with this id exist in the arraylist
     * @param id Animal ID to check
     * @return exists or not
     */
    public static boolean idExist(int id){
        if (menagerieCollection.isEmpty())
            return false;
        else{
            Iterator<Animal> iter= menagerieCollection.iterator();
            while (iter.hasNext()){
                Animal an= iter.next();
                if (an.getAnimalID()==id)
                    return true;
            }
            return false;
        }
    }

    /**
     * Returns the animal ID of the Animal object passed in parameter
     * @param animalID Animal object to get the ID from
     * @return animalID
     */
    public static Animal getAnimal(int animalID){
        if (menagerieCollection.isEmpty())
            return null;
        else{
            Iterator<Animal> iter= menagerieCollection.iterator();
            while (iter.hasNext()){
                Animal an= iter.next();
                if (an.getAnimalID()==animalID)
                    return an;
            }
            return null;
        }
    }
// end of class
}
