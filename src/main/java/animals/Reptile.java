package animals;

import clydeconservationsystem.ValidationException;
import java.util.logging.Logger;
/**
 * Class used to describe reptiles
 * <p>
 * it will be used as it own type, further developments could add children types to it
 */
public class Reptile extends Animal{
    // class for the Reptile type
    // unique ID for the animal
    private static final Logger LOGGER = Logger.getLogger(Reptile.class.getName());
    /**
     * The default constructor will use the setters implemented in the parent class
     * <p>
     * Those setters use the Menus class to get users input
     */
    public Reptile(){
        setName();
        setCategory();
        setSex();
        setDateOfBirth();
        setDateOfAcquisition();
        // using the static variable from the parent class to produce a unique ID
        this.animalID= getNextId();
        // the Animal is not assigned to a cage by default
        setCaged(false);
    }
    /**
     * Constructor to use for creating Reptile object from a file
     * <p>
     * may or may not be implemented at this stage
     * @param name Name of the reptile
     * @param category Category of the reptile
     * @param sex Sex of the reptile
     * @param doB Date of Birth of the reptile
     * @param doA Date of Acquisition of the reptile
     */
    public Reptile(String name,String category,String sex,String doB,String doA){
        try{
            setName(name);
            setCategory(category);
            setSex(sex);
            setDateOfAcquisition(doA);
            setDateOfBirth(doB);
            this.animalID= getNextId();
            // the Animal is not assigned to a cage by default
            setCaged(false);
        }
        catch (ValidationException ex){
        LOGGER.warning(ex.getMessage());
        }
    }

    // custom toString()
    @Override
    public String getDetails() {
        return "\nAnimal ID: "+getAnimalID()+" --- Type: Reptile --- "+
                "\nName: "+getName()+
                " --- Category: "+getCategory()+
                " --- Sex: "+getSex()+
                "\nDate of Birth: "+getDateOfBirth()+
                " --- Date of Acquisition: "+getDateOfAcquisition();
    }
}
