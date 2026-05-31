package main.java.clydeconservationsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.animals.Animal;

/**
 * Class to hold the cages owned by Clyde Conservation
 * <p>
 * The class have methods to load and save the cages collection
 * <p>
 * it also has methods to display info on the cages it holds
 * @author Erik
 */
public class CagesCollection {

    private static final String fileName="cages.dat";
    private static ArrayList<Cage> cagesCollection=new ArrayList<>();

    /**
     * Method to add a cage to the collection
     * @param c Cage to add
     */
    public static void addCage(Cage c){
        cagesCollection.add(c);
    }

    /**
     * Method to check if the collection is empty
     * @return True or False
     */
    public static boolean isEmpty(){
        return cagesCollection.isEmpty();
    }

    /**
     * This method will save the Cages ArrayList in a file
     * <p>
     * Link to resource found while searching for how-to
     * <p>
     * <a href="https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the">Java - How Can I Write My ArrayList to a file</a>
     * @see FileOutputStream
     * @see ObjectOutputStream
     *
     */
    public static void saveCagesCollection(){
        // try-catch to get any IO errors
        try{
            // initialise an output stream for the file
            FileOutputStream fos=new FileOutputStream(fileName);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(cagesCollection);
            oos.close();
            fos.close();
        }
        catch (IOException e){
            System.out.println("CageCollections:"+e.getMessage());
        }
    }

    /**
     * Method to load a saved cagesCollection from a file
     * @see FileInputStream
     * @see ObjectInputStream
     */
    public static void loadCagesCollection(){

        try{

            FileInputStream fis=new FileInputStream(fileName);
            ObjectInputStream ois=new ObjectInputStream(fis);
            cagesCollection.clear();
            try{
                // replacing the existing arraylist by the saved one
                cagesCollection=(ArrayList<Cage>)ois.readObject();
                // adding the number of cages to the CAGE_ID_BASE so new cages ID start after the last created one
                Cage.CAGE_ID_BASE=Cage.CAGE_ID_BASE+cagesCollection.size();
                ois.close();
                fis.close();
            }
            catch (ClassNotFoundException e){
                System.out.println("CageCollections:"+e.getMessage());
            }
        }
        catch (IOException e){
            System.out.println("CageCollections:"+e.getMessage());
        }
    }
    /**
     * The method will display the details of all the cages in the collection.
     */
    public static void displayAllCages(){
        // if empty, display status.
        if (cagesCollection.isEmpty())
            System.out.println("There is no cages stored");
        else{
            // using an iterator to loop over the cage collection.
            System.out.println();
            Iterator<Cage> iter=cagesCollection.iterator();
            while (iter.hasNext()){
                // displaying the index and the cage details.
                Cage cage= iter.next();
                // checking if the cage is for predator or prey, based on the first animal assigned
                String type;
                if (cage.isEmpty())
                    type="No Animal in cage";
                else
                    type= cage.getCageCategory();
                // testing if the cage is full or not
                String capacity;
                if (cage.isFull())
                    capacity="Yes";
                else
                    capacity="No";
                System.out.println("----- Cage ID: "+cage.cageID+"--- Cage Size: "+cage.getCageSize()+"--- Animal Type: "+type+
                        "--- At full Capacity? "+capacity);
            }
        }
    }

    /**
     * The method will display the details of the unassigned and not empty cages in the collection.
     */
    public static void displayUnassignedCages(){

        if (cagesCollection.isEmpty())
            System.out.println("There is no cages stored");
        else{
            // using an iterator to loop over the cage collection.
            Iterator<Cage> iter=cagesCollection.iterator();
            while (iter.hasNext()){
                // if not already in the assignment collections, the cage will be displayed, provided it's also not empty
                Cage cage= iter.next();

                if (!AssignmentsCollection.isAssigned(cage)&& !cage.isEmpty()){
                    // checking if the cage is for predator or prey, based on the first animal assigned
                    String type;
                    if (cage.isEmpty())
                        type="No Animal in cage";
                    else
                        type= cage.getCageCategory();
                    // testing if the cage is full or not
                    String capacity;
                    if (cage.isFull())
                        capacity="Yes";
                    else
                        capacity="No";
                    System.out.println("----- Cage ID: "+cage.cageID+"--- Cage Size: "+cage.getCageSize()+"--- Animal Type: "+type+
                            "--- At full Capacity? "+capacity);
                }
            }
        }
    }

    /**
     * Returns the number of unassigned cages
     * @return Unassigned cages
     */
    public static int countUnassignedCages(){
        if (cagesCollection.isEmpty())
            return 0;
        else{
            // using an iterator to loop over the cage collection.
            Iterator<Cage> iter=cagesCollection.iterator();
            int count=0;
            while (iter.hasNext()){
                Cage cage= iter.next();
                if (!AssignmentsCollection.isAssigned(cage)){
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Returns the number of unassigned and empty cages
     * @return Unassigned cages
     */
    public static int countUnassignedAndEmptyCages(){
        if (cagesCollection.isEmpty())
            return 0;
        else{
            // using an iterator to loop over the cage collection.
            Iterator<Cage> iter=cagesCollection.iterator();
            int count=0;
            while (iter.hasNext()){
                Cage cage= iter.next();
                if (!AssignmentsCollection.isAssigned(cage) && !cage.isEmpty()){
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Method to get the index of a cage in the collection
     * <p>
     * will return -1 if not found
     * @param cageID Cage
     * @return -1 or the index in the ArrayList
     */
    public static int getCageIndex(int cageID){
        int index=0;
        if (cagesCollection.isEmpty())
            return -1;
        else{
            Iterator<Cage> iter=cagesCollection.iterator();
            while (iter.hasNext()){
                Cage cg= iter.next();
                if (cg.getCageID()==cageID)
                    return index;
                else
                    index++;
            }
            return -1;
        }
    }
    /**
     * Method to return an cage by its ID
     * @param cageID ID of the cage
     * @return The searched cage
     */
    public static Cage getCage(int cageID){

        if (cagesCollection.isEmpty())
            return null;
        else{
            Iterator<Cage>iter=cagesCollection.iterator();
            while (iter.hasNext()){
                Cage cg=iter.next();
                if (cg.getCageID()==cageID)
                    return cg;
            }
            return null;
        }
    }
    /**
     * Method to check if an animal is present in a cage
     * @param animal Animal to look for
     * @return True or False
     */
    public static boolean isAssigned(Animal animal){
        if (cagesCollection.isEmpty())
            return false;
        else {
            Iterator<Cage> iter=cagesCollection.iterator();
            while (iter.hasNext()){
                Cage cage=iter.next();
                if (cage.isPresent(animal))
                    return true;
            }
            return false;
        }
    }

    /**
     * Method to add an animal to an existing cage
     * @param cageID The Cage ID
     * @param animal The animal to add
     */
    public static void addAnimalToCage(int cageID,Animal animal){

        cagesCollection.get(cageID).assignAnimal(animal);
    }

// End of class
}
