package main.java.clydeconservationsystem;

import java.io.Serializable;

import main.java.animals.Animal;


/**
 * The Cage class defines the basics of cages in the application.
 *
 * @author Erik
 */
public abstract class Cage implements Serializable {

    /**
     * Base to create unique IDs for cages.
     * <p>
     * it is public and has setters as it needs to be modified when loading the Cage collection file
     */
    protected static int CAGE_ID_BASE=300;
    // variable to store the category of the first animal assigned to it
    // this will allow to check if the animals added later are compatible
    /**
     * Will hold the category of animals in the cage, based on the first animal assigned to the cage.
     */
    protected String cageCategory;
    /**
     * Method to return the cage Category
     * <p>
     * the Category is determined by the first animal placed in the cage.
     * <p>
     * it can be:
     * <p>
     * - null: the cage has no animals assigned yet
     * <p>
     * - 'Predator': the first assigned animal was a predator type, cage can only house predators now
     * <p>
     * - 'Prey': the first assigned animal was a prey type, cage can only house prey now
     *
     * @return 'Predator', 'Prey', null
     */
    public String getCageCategory(){
        return cageCategory;
    }

    /**
     * ID of the cage
     */
    protected int cageID;

    /**
     * Method to get the cageID
     * @return the Cage ID
     */
    public int getCageID(){return cageID;}
    /**
     * Method to test if any animal has been assigned to the cage
     * @return True or False
     */
    public abstract boolean isEmpty();

    /**
     * add an animal to a cage
     * @param animal Animal to add to the cage
     */
    public abstract void assignAnimal(Animal animal);

    /**
     * Method to test if the cage is at full capacity
     * @return True or False
     */
    public abstract boolean isFull();

    /**
     * The Method will display the details of the cage
     * @return Cage details
     */
    public abstract String getCageDetails();

    /**
     * This method will tell if a specific Animal is present in the cage
     * @param animal Animal to look for
     * @return True or False
     */
    public abstract boolean isPresent(Animal animal);
    /**
     * The method will display the details of all the animals in the collection.
     */
    public abstract void displayAssignedAnimals();

    /**
     * Method to get the Cage size : Large, Medium or Small
     * @return Cage's Size
     */
    public abstract String getCageSize();

}
