package clydeconservationsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import employees.Keeper;

/**
 * This class holds the Cages-Keepers assignments.
 * <p>
 * An assignment needs to be assigned a Keeper(mandatory) and can be assigned up to 4 cages.
 * <p>
 * A keeper cannot be assigned to an assignment if he's already in another one.
 * <p>
 * all assignment are stored in an AllocationCollection object.
 * @author Erik
 */
public class AssignmentTable implements Serializable {

    // Variable to hold the Keeper
    private Keeper assignedKeeper;
    // Variable to hold the maximum number of cage a keeper can have
    private final int MAX_ASSIGMENTS=4;
    // arraylist to store the assigned cages.
    private ArrayList<Cage> assignedCages=new ArrayList<>();
    // static variable to create an ID;
    /**
     * Base that will be used to create assignment IDs
     */
    protected static int ASSIGNMENT_ID_BASE=500;
    private final int assignmentID;


    /**
     * Constructor for the assignment object.
     * <p>
     * A table must be created with a Keeper, even if it can be changed later if needed.
     * @param keeper Keeper to assign to the table
     */
    public AssignmentTable(Keeper keeper){
        this.assignedKeeper=keeper;
        assignmentID=ASSIGNMENT_ID_BASE++;
    }
    /**
     * Method to get the assigned Keeper of to allocation table
     * @return The assigned Keeper.
     */
    public Keeper getAssignedKeeper() {
        return assignedKeeper;
    }

    /**
     * Method to return the assignment ID
     * @return assignment ID
     */
    public int getAssignmentID() {
        return assignmentID;
    }

    /**
     * Method to assign a Keeper to the allocation table
     * <p>
     * This should only be used if the existing keeper need to be replaced
     * @param assignedKeeper Keeper to assign
     */
    public void setAssignedKeeper(Keeper assignedKeeper) {
        this.assignedKeeper = assignedKeeper;
    }
    private boolean isMaxedOut(){
        // test if the keeper has room for more cage assignments.
        // returns true if the size of the arraylist is greater or equal than the MAX_ASSIGNMENT constant
        return assignedCages.size()>=MAX_ASSIGMENTS;
    }
    // function to check is the list is empty
    private boolean isEmpty(){
        return assignedCages.isEmpty();
    }

    /**
     * Method to add a cage to a Keeper assignment
     * @param cage Cage to add
     * @return True or false
     */
    public boolean assignCage(Cage cage){
        // we first test if the cage is empty
        // if not, we check if the assigned cages of this keeper is not greater or equal than 4
        if (cage==null){
            System.out.println("Cage do not exists");
            return false;}
        else if(isMaxedOut()){
            System.out.println("This keeper cannot care for any more cages");
            return false;}
        else if (cage.isEmpty()) {
            System.out.println("This cage is empty. it cannot be assigned yet.");
            return false;
        } else {
            assignedCages.add(cage);
            return true;
        }
    }

    /**
     * Method to remove a Cage from the Assignment
     */
    public void removeCage(){
        System.out.println("----- Remove a cage from the assignment -----");
        displayAssignment();
        System.out.println("Please enter the Cage number to remove: ");
        assignedCages.remove(new Scanner(System.in).nextInt()-1);
    }

    /**
     * Method to display the cages assigned in this assignment Table
     */
    public void displayAssignment(){
        int index=1;
        if (isEmpty())
            System.out.println("This keeper has no cages assigned");
        else{
            Iterator<Cage> iter= assignedCages.iterator();
            //System.out.println("----- Assignment ID: "+getAssignmentID()+" -----");
           //System.out.println("Keeper: "+assignedKeeper.getFirstName()+" "+assignedKeeper.getLastName());
            while (iter.hasNext()){
                Cage cge=iter.next();
                System.out.println("\n----- Cage ID "+cge.getCageID()+" -----");
                System.out.println(CagesCollection.getCage(cge.getCageID()).getCageDetails());
            }
        }
    }

    /**
     * Method to return the number of cages assigned to the table.
     * @return Number of cage (0-4).
     */
    public int assignedCages(){
        if (assignedCages.isEmpty())
            return 0;
        else
            return assignedCages.size();
    }

    /**
     * Method to check if a Cage is already assigned in the table
     * @param cage Cage to check
     * @return True or False
     */
    public boolean cageIsPresent(Cage cage){

        if (assignedCages.isEmpty())
            return false;
        else{
            Iterator<Cage> it=assignedCages.iterator();
            while (it.hasNext()){
                Cage cage1=it.next();
                if (cage1.getCageID()==cage.getCageID())
                    return true;
            }
            return false;
        }

    }

// End of class
}
