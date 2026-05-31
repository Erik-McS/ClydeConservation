package employees;

import java.io.Serializable;

/**
 * The administrator class is used to give access to the Admin actions:
 * Add animal, add Keeper and add Cage.
 * <p>
 * This class will use a simple constructor.
 * <p>
 * no validations were set for this class as only one object will be created in the login simulation.
 * <p>
 * for assessment purposes, data validation code is implemented in the HeadKeeper class
 */
public class Administrator implements Employee, Serializable {

    // class variables.
    private String firstName;
    private String lastName;
    private String address;
    private String contactNumber;
    // the base will be used to create unique ID numbers.
    private static int ADMIN_ID_BASE=200;
    // ID number, final since value should not be changed
    private final int ADMIN_ID;

    /**
     * Constructor for the Administrator class
     * @param firstName Name of the Administrator
     * @param lastName Lastname of the Administrator
     * @param number Telephone number of the Administrator
     * @param address address of the Administrator
     */
    public Administrator(String firstName,String lastName,String number, String address){
        // assigning all parameters to the class variables
        this.setAddress(address);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContactNumber(number);
        // assigning an admin ID and incrementing the base by 1
        this.ADMIN_ID =ADMIN_ID_BASE++;
    }

    // getter and setters

    /**
     * Get the Administrator's firstname
     * @return Firstname
     */
    public String getFirstName() {
        return firstName;
    }
    // only a getter for the ID since it should not be changed once assigned

    /**
     * Get the Administrator's Employee ID
     * @return Employee ID
     */
    public int getADMIN_ID() {
        return ADMIN_ID;
    }

    /**
     * Set the Administrator's firstname
     * @param firstName Admin's firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the Administrator's lastname
     * @return Lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the Administrator's lastname
     * @param lastName Admin's lastname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the Administrator's address
     * @return Adress
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the Administrator's address
     * @param address Admin's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the Administrator's telephone number
     * @return Telephone number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Set the Administrator's telephone numbers
     * @param contactNumber Admin's telephone number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getID(){
        return ADMIN_ID;
    }

    @Override
    public String getName() {
        return firstName;
    }

    @Override
    public String getSurname() {
        return lastName;
    }

    // custom version of the toString() method
    @Override
    public String getDetails() {
        return "\nAdministrator details:"+
                "\nID: "+getID()+
                "\nFirst name: "+getFirstName()+
                "\nLast name:"+getLastName()+
                "\nAddress: "+getAddress()+
                "\nContact number: "+getContactNumber()
                ;
    }
}
