package test.java;
import main.java.animals.Bird;
import main.java.animals.Mammal;
import main.java.clydeconservationsystem.LargeCage;
import main.java.clydeconservationsystem.MediumCage;
import main.java.clydeconservationsystem.SmallCage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SmallCage, MediumCage and LargeCage
 */
class CageTest {

    private Bird predatorBird;
    private Bird preyBird;
    private Mammal preyMammal;

    @BeforeEach
    void setUp() {
        predatorBird = new Bird("Hawk", "Predator", "Male", "01/01/2020", "01/06/2020");
        preyBird     = new Bird("Robin", "Prey", "Female", "02/02/2021", "05/01/2021");
        preyMammal   = new Mammal("Daisy", "Prey", "Female", "04/02/2021", "05/04/2021");
    }

    // --- SmallCage ---

    @Test
    void smallCage_isEmpty_whenCreated() {
        SmallCage cage = new SmallCage();
        assertTrue(cage.isEmpty());
    }

    @Test
    void smallCage_isNotEmpty_afterAssignment() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        assertFalse(cage.isEmpty());
    }

    @Test
    void smallCage_isFull_afterOneAnimal() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        assertTrue(cage.isFull());
    }

    @Test
    void smallCage_isPresent_returnsTrueForAssignedAnimal() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        assertTrue(cage.isPresent(predatorBird));
    }

    @Test
    void smallCage_isPresent_returnsFalseForOtherAnimal() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        assertFalse(cage.isPresent(preyBird));
    }

    @Test
    void smallCage_getCageSize_returnsSmall() {
        assertEquals("Small", new SmallCage().getCageSize());
    }

    @Test
    void smallCage_getCageDetails_whenEmpty() {
        SmallCage cage = new SmallCage();
        assertTrue(cage.getCageDetails().contains("N/A"));
    }

    @Test
    void smallCage_getCageDetails_whenOccupied() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        assertTrue(cage.getCageDetails().contains("Predator"));
    }

    @Test
    void smallCage_getCageCategory_matchesFirstAnimal() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        assertEquals("Predator", cage.getCageCategory());
    }

    @Test
    void smallCage_rejectsSecondAnimal() {
        SmallCage cage = new SmallCage();
        cage.assignAnimal(predatorBird);
        cage.assignAnimal(preyBird); // should be rejected silently
        // cage still has only the first animal
        assertTrue(cage.isPresent(predatorBird));
        assertFalse(cage.isPresent(preyBird));
    }

    // --- MediumCage ---

    @Test
    void mediumCage_isEmpty_whenCreated() {
        assertTrue(new MediumCage().isEmpty());
    }

    @Test
    void mediumCage_getCageSize_returnsMedium() {
        assertEquals("Medium", new MediumCage().getCageSize());
    }

    @Test
    void mediumCage_acceptsCompatibleAnimals() {
        MediumCage cage = new MediumCage();
        cage.assignAnimal(preyBird);
        cage.assignAnimal(preyMammal);
        assertTrue(cage.isPresent(preyBird));
        assertTrue(cage.isPresent(preyMammal));
    }

    @Test
    void mediumCage_rejectsIncompatibleAnimal() {
        MediumCage cage = new MediumCage();
        cage.assignAnimal(preyBird);
        cage.assignAnimal(predatorBird); // incompatible
        assertFalse(cage.isPresent(predatorBird));
    }

    @Test
    void mediumCage_isFull_afterFiveAnimals() {
        MediumCage cage = new MediumCage();
        for (int i = 0; i < 5; i++) {
            cage.assignAnimal(new Bird("Robin", "Prey", "Male", "01/01/2020", "01/01/2020"));
        }
        assertTrue(cage.isFull());
    }

    @Test
    void mediumCage_rejectsAnimal_whenFull() {
        MediumCage cage = new MediumCage();
        for (int i = 0; i < 5; i++) {
            cage.assignAnimal(new Bird("Robin", "Prey", "Male", "01/01/2020", "01/01/2020"));
        }
        Bird extra = new Bird("Extra", "Prey", "Male", "01/01/2020", "01/01/2020");
        cage.assignAnimal(extra);
        assertFalse(cage.isPresent(extra));
    }

    // --- LargeCage ---

    @Test
    void largeCage_isEmpty_whenCreated() {
        assertTrue(new LargeCage().isEmpty());
    }

    @Test
    void largeCage_getCageSize_returnsLarge() {
        assertEquals("Large", new LargeCage().getCageSize());
    }

    @Test
    void largeCage_isFull_afterTenAnimals() {
        LargeCage cage = new LargeCage();
        for (int i = 0; i < 10; i++) {
            cage.assignAnimal(new Bird("Robin", "Prey", "Male", "01/01/2020", "01/01/2020"));
        }
        assertTrue(cage.isFull());
    }

    @Test
    void largeCage_getCAPACITY_returnsTen() {
        assertEquals(10, new LargeCage().getCAPACITY());
    }
}
