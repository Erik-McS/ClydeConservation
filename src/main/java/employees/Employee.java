package employees;
/**
 * Interface used for Clyde Conservation employees.
 * <p>
 * This will be used across the application to contain employee types.
 * @author Erik
 */
public interface Employee {
    /**
     * Custom version of toString() to implement in child classes.
     * @return The details of the employee.
     */
    public String getDetails();

    /**
     * Method to get the employee ID
     * @return Employee ID
     */
    public int getID();

    /**
     * Method to get the employee name
     * @return Employee name
     */
    public String getName();

    /**
     * Employee to get the employee surname
     * @return Employee surname
     */
    public String getSurname();
}
