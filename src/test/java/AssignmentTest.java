
import animals.Bird;
import clydeconservationsystem.*;
import employees.AssistantKeeper;
import employees.HeadKeeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for AssignmentTable, AssignmentsCollection and ValidationException
 */
class AssignmentTest {

    private AssistantKeeper keeper;
    private SmallCage occupiedCage;
    private SmallCage emptyCage;
    private Bird bird;

    @BeforeEach
    void setUp() {
        keeper = new AssistantKeeper("John", "Smith", "0774536271", "3 Glasgow av");
        bird = new Bird("Robin", "Prey", "Male", "02/02/2021", "05/01/2021");
        occupiedCage = new SmallCage();
        occupiedCage.assignAnimal(bird);
        emptyCage = new SmallCage();
    }

    // --- AssignmentTable ---

    @Test
    void assignmentTable_getAssignedKeeper_returnsCorrectKeeper() {
        AssignmentTable table = new AssignmentTable(keeper);
        assertEquals(keeper, table.getAssignedKeeper());
    }

    @Test
    void assignmentTable_getAssignmentID_isUnique() {
        AssignmentTable t1 = new AssignmentTable(keeper);
        AssignmentTable t2 = new AssignmentTable(keeper);
        assertNotEquals(t1.getAssignmentID(), t2.getAssignmentID());
    }

    @Test
    void assignmentTable_assignCage_succeeds_withOccupiedCage() {
        AssignmentTable table = new AssignmentTable(keeper);
        assertTrue(table.assignCage(occupiedCage));
    }

    @Test
    void assignmentTable_assignCage_fails_withEmptyCage() {
        AssignmentTable table = new AssignmentTable(keeper);
        assertFalse(table.assignCage(emptyCage));
    }

    @Test
    void assignmentTable_assignCage_fails_withNullCage() {
        AssignmentTable table = new AssignmentTable(keeper);
        assertFalse(table.assignCage(null));
    }

    @Test
    void assignmentTable_assignedCages_returnsCorrectCount() {
        AssignmentTable table = new AssignmentTable(keeper);
        table.assignCage(occupiedCage);
        assertEquals(1, table.assignedCages());
    }

    @Test
    void assignmentTable_assignedCages_zeroWhenNoneAssigned() {
        AssignmentTable table = new AssignmentTable(keeper);
        assertEquals(0, table.assignedCages());
    }

    @Test
    void assignmentTable_cageIsPresent_returnsTrueAfterAssignment() {
        AssignmentTable table = new AssignmentTable(keeper);
        table.assignCage(occupiedCage);
        assertTrue(table.cageIsPresent(occupiedCage));
    }

    @Test
    void assignmentTable_cageIsPresent_returnsFalseForUnassignedCage() {
        AssignmentTable table = new AssignmentTable(keeper);
        assertFalse(table.cageIsPresent(occupiedCage));
    }

    @Test
    void assignmentTable_maxedOut_after4Cages() {
        AssignmentTable table = new AssignmentTable(keeper);
        for (int i = 0; i < 4; i++) {
            SmallCage cage = new SmallCage();
            cage.assignAnimal(new Bird("Robin", "Prey", "Male", "01/01/2020", "01/01/2020"));
            table.assignCage(cage);
        }
        SmallCage extraCage = new SmallCage();
        extraCage.assignAnimal(new Bird("Extra", "Prey", "Male", "01/01/2020", "01/01/2020"));
        assertFalse(table.assignCage(extraCage));
    }

    @Test
    void assignmentTable_setAssignedKeeper_updatesKeeper() throws ValidationException {
        AssignmentTable table = new AssignmentTable(keeper);
        HeadKeeper newKeeper = new HeadKeeper.HdKBuilder()
                .setName("Erik").setLastName("Mcseveney").HBuilder();
        table.setAssignedKeeper(newKeeper);
        assertEquals(newKeeper, table.getAssignedKeeper());
    }

    // --- ValidationException ---

    @Test
    void validationException_getMessage_containsMessage() {
        ValidationException ex = new ValidationException("Test error");
        assertTrue(ex.getMessage().contains("Test error"));
    }

    // --- CagesCollection ---

    @Test
    void cagesCollection_addCage_isNotEmptyAfterAdd() {
        CagesCollection.addCage(new SmallCage());
        assertFalse(CagesCollection.isEmpty());
    }

    @Test
    void cagesCollection_getCage_returnsCorrectCage() {
        SmallCage cage = new SmallCage();
        CagesCollection.addCage(cage);
        assertEquals(cage, CagesCollection.getCage(cage.getCageID()));
    }

    @Test
    void cagesCollection_getCage_returnsNullForUnknownId() {
        assertNull(CagesCollection.getCage(-999));
    }

    @Test
    void cagesCollection_isAssigned_returnsTrueForAssignedAnimal() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(bird);
        CagesCollection.addCage(cage);
        assertTrue(CagesCollection.isAssigned(bird));
    }
}
