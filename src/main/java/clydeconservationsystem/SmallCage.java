package clydeconservationsystem;

import java.io.Serializable;

import animals.Animal;


/**
 * Class to represent the small cages used at Clyde Conservation
 * the class also implements the Serializable interface so objects can be saved via a stream in a file
 * @author Erik
 */
public class SmallCage extends Cage implements Serializable {

    // Animal object to contain the assigned animal
    private Animal assignedAnimal;

    /**
     * Default Constructor
     */
    public SmallCage(){
        // creating the ID
        this.cageID=CAGE_ID_BASE++;
    }


    @Override
    public void displayAssignedAnimals() {
        // local variable to display the animal position in the collection.
        int index=0;
        // if empty, display status.
        if (assignedAnimal==null)
            System.out.println("There is no animal assigned to this Small cage");
        else
            System.out.println(assignedAnimal.getDetails());
    }

    @Override
    public boolean isPresent(Animal animal) {
        if (assignedAnimal==null)
            return false;
        else if (assignedAnimal.getAnimalID()==animal.getAnimalID())
                return true;
        else
            return false;
    }

    @Override
    public boolean isEmpty() {
        return assignedAnimal==null;
    }

    @Override
    public void assignAnimal(Animal animal) {
        // we check if the cage is empty
        if (isEmpty()){
            // if empty, we set the category of the cage to the animal category
            cageCategory= animal.getCategory();
            // we then add the animal to the cage
            assignedAnimal=animal;
            // we confirm the assignment
            System.out.println("The following animal has successfully been added to the cage:");
            System.out.println(animal.getDetails());
        }
        // if not empty, we check if full
        else
            System.out.println("There is no room left in this cage for a new animal");
            // otherwise, there is room for another animal
    }
    @Override
    public boolean isFull() {
        return assignedAnimal!=null;
    }

    @Override
    public String getCageDetails() {

        if (isEmpty()) {
            return "\n Cage Size: " +getCageSize()+
                    " ----- Number of animals in the cage: 0" +
                    " ----- Cage Category: N/A";
        }
        else{
            return "\n Cage Size: "+getCageSize()+
                    " ----- Number of animals in the cage: 1"+
                    " ----- Cage Category: "+getCageCategory();
            }
        }

    @Override
    public String getCageSize(){return "Small";}

// End of class
}
