package employees;

import clydeconservationsystem.ValidationException;

/**
 * Generic class, just usedto fill the employee roster and be assigned to a group of cage.
 * <p>
 * No methods implemented to keep simple
 * @author Erik
 */
public class AssistantKeeper extends Keeper{

    /**
     * Generic constructor
     * @param name Assistant Keeper's name
     * @param lastName Assistant Keeper's lastname
     * @param number Assistant Keeper's phone number
     * @param Address Assistant Keeper's address
     */
    public AssistantKeeper(String name,String lastName,String number, String Address){
        // assigning class variables
        this.setAddress(Address);

        try {
            this.setFirstName(name);
            this.setLastName(lastName);
        }
        catch (ValidationException e){
            System.out.println(e.getMessage()  );
        }
        this.setContactNumber(number);
        // using a static variable from the parent class. this will assign a unique ID to the child object
        // also increment the static variable.
        this.keeperID =KEEPER_ID_BASE++;

    }

}
