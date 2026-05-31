package employees;

import clydeconservationsystem.ValidationException;

/**
 * This class will use the Builder design pattern.
 * This pattern allows a modular approach to constructor.
 * <p>
 * A HeadKeeper object has to have a name and surname attributes.
 * The rest of the attributes are optional and will be assigned default values if left empty at the object instantiation.
 * <p>
 * The constructor is private to force the use of the nested builder class.
 * It will receive an object of the builder class and assign its parameters to the HeadKeeper object.
 * <p>
 * some resources:
 * <p>
 * <a href="https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/">Builder Design Pattern</a>
 * @author Erik
 */
public class HeadKeeper extends Keeper{

    // the ID is made final ,as it doesn't need to change once assigned.

    private HeadKeeper(HdKBuilder build){
        // the constructor will assign each attributes of the nested Builder  class to the HeadKeeper object
        try{
            this.setFirstName(build.firstName);
            this.setLastName(build.lastName);
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
        }

        this.setAddress(build.address);
        this.setContactNumber(build.contactNumber);
        // using a static variable from the parent class. this will assign a unique ID to the child object
        // also increment the static variable.
        this.keeperID =KEEPER_ID_BASE++;
    }


     // override the getDetails() method to include the KeeperID;
    @Override
    public String getDetails() {
        return "\nKeeper details:"+
                "\nKeeper ID: "+ getID()+
                "\nFirst name: "+getFirstName()+
                "\nLast name:"+getLastName()+
                "\nAddress: "+getAddress()+
                "\nContact number: "+getContactNumber();
    }

    /**
     * The builder class is nested and static.
     * <p>
     * Being a static class allows to use its methods without having to create an object.
     * <p>
     * The class will have setters for each attribute.
     * They will check if the parameter has the correct format or throw an exception
     * <p>
     * No getters needed for this class
     */
    public static class HdKBuilder{
        // the class has the exact same attributes.
        private String firstName;
        private String lastName;
        private String address;
        private String contactNumber;

        /**
         * Set the name of the Head Keeper.
         * <p>
         * the method will check that the name has a correct format, or it will throw a validation exception.
         * @param name Name of the Head Keeper
         * @return returns the current HdKBuilder
         * @throws ValidationException Incorrect Name Format
         */
        public HdKBuilder setName(String name) throws ValidationException {
            // checking the name format
            if (name.equals(""))
                // if empty, throw exception
                throw new ValidationException("Name cannot be empty");
            // using a regex to make sure there is no numbers or special characters
            else  if (name.matches("(\\p{Upper})(\\p{Lower}){1,10}")){
                // if format is ok, assign the value to the builder attribute
                this.firstName=name;
                return this;
            }
            else throw new ValidationException("Invalid Name Format");
        }

        /**
         * Set the lastname of the Head Keeper.
         * <p>
         * the method will check that the name has a correct format, or it will throw a validation exception.
         * @param lastName Lastname of the Head Keeper
         * @return returns the current HdKBuilder
         * @throws ValidationException Incorrect Name Format
         */
        public HdKBuilder setLastName(String lastName) throws ValidationException{
            // same logic and checks than for the name
            if (lastName.equals(""))
                throw new ValidationException("Last Name cannot be empty");
            else if(lastName.matches("(\\p{Upper})(\\p{Lower}){1,20}")){
                this.lastName=lastName;
                return this;
            }
            else throw new ValidationException("Invalid Last Name Format");
        }

        /**
         * Set the address of the Head Keeper.
         * <p>
         * no validation on the address to keep simple
         * @param address Name of the Head Keeper
         * @return returns the current HdKBuilder
         */
        public HdKBuilder setAddress(String address){
                // no check for the address, to keep simple
                this.address=address;
                return this;
        }

        /**
         * Set the tel number of the Head Keeper.
         * <p>
         * no validation on the tel to keep simple
         * @param number Telephone number of the Head Keeper
         * @return returns the current HdKBuilder
         */
        public HdKBuilder setContactNumber(String number){
                // no check for the number, to keep simple
                this.contactNumber=number;
                return this;
        }

        /**
         * This is the Builder method.
         * It will check every attribute of the main class.
         * <p>
         * If the name or lastname are empty, it will throw an exception as those are mandatory,
         * otherwise it will assign default values to the missing one.
         * <p>
         * Once done, it will return the created HeadKeeper object.
         * @return HeadKeeper object with all the values assigned.
         * @throws ValidationException Exception thrown if the name or lastname was not passed to the Builder.
         */
        public HeadKeeper HBuilder() throws ValidationException{
            // check if a name is assigned
            if (this.firstName==null)
                throw new ValidationException("Name cannot be empty");
            // check if a last name is assigned
            if (this.lastName==null)
                throw new ValidationException("Name cannot be empty");
            // if no address was set, we assign a default value
            if (this.address==null)
                this.address="To be updated";
            // if no address was set, we assign a default value
            if (this.contactNumber==null)
                this.contactNumber="To be updated";
            // once all has been checked
            // we create a new HeadKeeper object, that will take this HdKBuilder object as parameter
            // and return it to the caller
            return new HeadKeeper(this);
        }

    }
}
