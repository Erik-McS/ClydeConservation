package test.java;
import main.java.clydeconservationsystem.ValidationException;
import main.java.employees.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Administrator, AssistantKeeper, HeadKeeper and EmployeeRoster
 */
class EmployeeTest {

    // --- Administrator ---

    @Test
    void administrator_getFirstName_returnsCorrectName() {
        Administrator admin = new Administrator("Mark", "Smith", "07722334455", "1 Glasgow av");
        assertEquals("Mark", admin.getFirstName());
    }

    @Test
    void administrator_getLastName_returnsCorrectName() {
        Administrator admin = new Administrator("Mark", "Smith", "07722334455", "1 Glasgow av");
        assertEquals("Smith", admin.getLastName());
    }

    @Test
    void administrator_getID_isUnique() {
        Administrator admin1 = new Administrator("Mark", "Smith", "07722334455", "1 Glasgow av");
        Administrator admin2 = new Administrator("Jane", "Doe", "07711223344", "2 Glasgow av");
        assertNotEquals(admin1.getID(), admin2.getID());
    }

    @Test
    void administrator_getDetails_containsNameAndId() {
        Administrator admin = new Administrator("Mark", "Smith", "07722334455", "1 Glasgow av");
        String details = admin.getDetails();
        assertTrue(details.contains("Mark"));
        assertTrue(details.contains("Smith"));
    }

    @Test
    void administrator_getName_returnsFirstName() {
        Administrator admin = new Administrator("Mark", "Smith", "07722334455", "1 Glasgow av");
        assertEquals("Mark", admin.getName());
    }

    @Test
    void administrator_getSurname_returnsLastName() {
        Administrator admin = new Administrator("Mark", "Smith", "07722334455", "1 Glasgow av");
        assertEquals("Smith", admin.getSurname());
    }

    // --- AssistantKeeper ---

    @Test
    void assistantKeeper_getFirstName_returnsCorrectName() {
        AssistantKeeper keeper = new AssistantKeeper("John", "Smith", "0774536271", "3 Glasgow av");
        assertEquals("John", keeper.getFirstName());
    }

    @Test
    void assistantKeeper_getID_isUnique() {
        AssistantKeeper k1 = new AssistantKeeper("John", "Smith", "0774536271", "3 Glasgow av");
        AssistantKeeper k2 = new AssistantKeeper("Jane", "Brown", "0774536272", "4 Glasgow av");
        assertNotEquals(k1.getID(), k2.getID());
    }

    @Test
    void assistantKeeper_getDetails_containsName() {
        AssistantKeeper keeper = new AssistantKeeper("John", "Smith", "0774536271", "3 Glasgow av");
        assertTrue(keeper.getDetails().contains("John"));
    }

    @Test
    void assistantKeeper_cageCare_doesNotThrow() {
        AssistantKeeper keeper = new AssistantKeeper("John", "Smith", "0774536271", "3 Glasgow av");
        assertDoesNotThrow(keeper::cageCare);
    }

    // --- HeadKeeper ---

    @Test
    void headKeeper_build_withValidData() throws ValidationException {
        HeadKeeper hk = new HeadKeeper.HdKBuilder()
                .setName("Erik").setLastName("Mcseveney")
                .setContactNumber("07755667788").setAddress("2 Glasgow av")
                .HBuilder();
        assertEquals("Erik", hk.getFirstName());
        assertEquals("Mcseveney", hk.getLastName());
    }

    @Test
    void headKeeper_build_defaultsAddress_whenNotSet() throws ValidationException {
        HeadKeeper hk = new HeadKeeper.HdKBuilder()
                .setName("Erik").setLastName("Mcseveney")
                .HBuilder();
        assertEquals("To be updated", hk.getAddress());
    }

    @Test
    void headKeeper_build_defaultsContactNumber_whenNotSet() throws ValidationException {
        HeadKeeper hk = new HeadKeeper.HdKBuilder()
                .setName("Erik").setLastName("Mcseveney")
                .HBuilder();
        assertEquals("To be updated", hk.getContactNumber());
    }

    @Test
    void headKeeper_build_throwsException_whenNameMissing() {
        assertThrows(ValidationException.class, () ->
                new HeadKeeper.HdKBuilder().setLastName("Mcseveney").HBuilder()
        );
    }

    @Test
    void headKeeper_build_throwsException_whenLastNameMissing() {
        assertThrows(ValidationException.class, () ->
                new HeadKeeper.HdKBuilder().setName("Erik").HBuilder()
        );
    }

    @Test
    void headKeeper_build_throwsException_whenInvalidName() {
        assertThrows(ValidationException.class, () ->
                new HeadKeeper.HdKBuilder().setName("erik123")
        );
    }

    @Test
    void headKeeper_getDetails_containsName() throws ValidationException {
        HeadKeeper hk = new HeadKeeper.HdKBuilder()
                .setName("Erik").setLastName("Mcseveney").HBuilder();
        assertTrue(hk.getDetails().contains("Erik"));
    }

    // --- EmployeeRoster ---

    @Test
    void employeeRoster_addEmployee_employeeIsPresent() {
        AssistantKeeper keeper = new AssistantKeeper("Tom", "Tomas", "0778965342", "4 Glasgow av");
        EmployeeRoster.addEmployee(keeper);
        assertNotNull(EmployeeRoster.getKeeper(keeper.getID()));
    }

    @Test
    void employeeRoster_getKeeper_returnsNullForUnknownId() {
        assertNull(EmployeeRoster.getKeeper(-999));
    }

    @Test
    void employeeRoster_addEmployee_rejectsDuplicate() {
        AssistantKeeper keeper = new AssistantKeeper("Daryl", "Make", "0774510987", "5 Glasgow av");
        EmployeeRoster.addEmployee(keeper);
        int countBefore = EmployeeRoster.countKeepers();
        EmployeeRoster.addEmployee(keeper); // duplicate
        assertEquals(countBefore, EmployeeRoster.countKeepers());
    }

    @Test
    void employeeRoster_newEmployeeExists_returnsTrueForExisting() {
        EmployeeRoster.addEmployee(new AssistantKeeper("Patrick", "Brien", "0779455778", "6 Glasgow av"));
        assertTrue(EmployeeRoster.newEmployeeExists("Patrick", "Brien"));
    }

    @Test
    void employeeRoster_newEmployeeExists_returnsFalseForUnknown() {
        assertFalse(EmployeeRoster.newEmployeeExists("Unknown", "Person"));
    }
}
