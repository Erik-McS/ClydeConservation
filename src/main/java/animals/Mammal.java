package animals;

import java.util.logging.Logger;

import clydeconservationsystem.ValidationException;

/**
 * Class used to describe mammals
 * <p>
 * it will be used as it own type, further developments could add children types to it
 */
public class Mammal extends Animal {
    private static final Logger LOGGER = Logger.getLogger(Mammal.class.getName());
    // class for the Mammal type
    /**
     * The default constructor will use the setters implemented in the parent class
     * <p>
     * Those setters use the Menus class to get users input
     */
    public Mammal(){
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
     * Constructor to use for creating Mammal object from a file
     * <p>
     * may or may not be implemented at this stage
     * @param name Name of the mammal
     * @param category Category of the mammal
     * @param sex Sex of the mammal
     * @param doB Date of Birth of the mammal
     * @param doA Date of Acquisition of the mammal
     */
    public Mammal(String name,String category,String sex,String doB,String doA){
        try {
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
            return "\nAnimal ID: "+getAnimalID()+" --- Type: Mammal --- "+
                    "\nName: "+getName()+
                    " --- Category: "+getCategory()+
                    " --- Sex: "+getSex()+
                    "\nDate of Birth: "+getDateOfBirth()+
                    " --- Date of Acquisition: "+getDateOfAcquisition();
        }
}
