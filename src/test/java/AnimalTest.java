package test.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.animals.Bird;
import main.java.animals.Mammal;
import main.java.animals.Menagerie;
import main.java.animals.Reptile;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Bird, Mammal, Reptile and Menagerie
 */
class AnimalTest {

    private Bird bird;
    private Mammal mammal;
    private Reptile reptile;
    private String birdName = "Robin";
    private String predatorName= "Spot";
    private String reptileName= "Smiss";
    private String preyType = "Prey";
    private String predatorType = "Predator";
    @BeforeEach
    void setUp() {
        bird    = new Bird(birdName, preyType, "Male", "02/02/2021", "05/01/2021");
        mammal  = new Mammal(predatorName, predatorType, "Male", "04/02/2021", "05/04/2021");
        reptile = new Reptile(reptileName, predatorType, "Male", "04/02/2021", "05/04/2021");
    }

    // --- Bird ---

    @Test
    void bird_getName_returnsCorrectName() {
        assertEquals(birdName, bird.getName());
    }

    @Test
    void bird_getCategory_returnsCorrectCategory() {
        assertEquals("Prey", bird.getCategory());
    }

    @Test
    void bird_getSex_returnsCorrectSex() {
        assertEquals("Male", bird.getSex());
    }

    @Test
    void bird_getDateOfBirth_returnsCorrectDate() {
        assertEquals("02/02/2021", bird.getDateOfBirth());
    }

    @Test
    void bird_getDateOfAcquisition_returnsCorrectDate() {
        assertEquals("05/01/2021", bird.getDateOfAcquisition());
    }

    @Test
    void bird_getType_returnsBird() {
        assertEquals("Bird", bird.getType());
    }

    @Test
    void bird_isCaged_falseByDefault() {
        assertFalse(bird.isCaged());
    }

    @Test
    void bird_setCaged_updatesCorrectly() {
        bird.setCaged(true);
        assertTrue(bird.isCaged());
    }

    @Test
    void bird_getDetails_containsTypeAndName() {
        String details = bird.getDetails();
        assertTrue(details.contains("Bird"));
        assertTrue(details.contains("Robin"));
    }

    @Test
    void bird_animalID_isUnique() {
        Bird bird2 = new Bird("Blue", "Prey", "Female", "01/01/2020", "01/01/2020");
        assertNotEquals(bird.getAnimalID(), bird2.getAnimalID());
    }

    // --- Mammal ---

    @Test
    void mammal_getType_returnsMammal() {
        assertEquals("Mammal", mammal.getType());
    }

    @Test
    void mammal_getDetails_containsMammal() {
        assertTrue(mammal.getDetails().contains("Mammal"));
    }

    @Test
    void mammal_getCategory_returnsCorrectCategory() {
        assertEquals("Predator", mammal.getCategory());
    }

    // --- Reptile ---

    @Test
    void reptile_getType_returnsReptile() {
        assertEquals("Reptile", reptile.getType());
    }

    @Test
    void reptile_getDetails_containsReptile() {
        assertTrue(reptile.getDetails().contains("Reptile"));
    }

    @Test
    void reptile_getName_returnsCorrectName() {
        assertEquals("Smiss", reptile.getName());
    }

    // --- Menagerie ---

    @Test
    void menagerie_addAnimal_animalIsPresent() {
        Menagerie.addAnimal(bird);
        assertTrue(Menagerie.isPresent(bird));
    }

    @Test
    void menagerie_idExist_returnsTrueForAddedAnimal() {
        Menagerie.addAnimal(mammal);
        assertTrue(Menagerie.idExist(mammal.getAnimalID()));
    }

    @Test
    void menagerie_idExist_returnsFalseForUnknownId() {
        assertFalse(Menagerie.idExist(-999));
    }

    @Test
    void menagerie_getAnimal_returnsCorrectAnimal() {
        Menagerie.addAnimal(reptile);
        assertEquals(reptile, Menagerie.getAnimal(reptile.getAnimalID()));
    }

    @Test
    void menagerie_getAnimal_returnsNullForUnknownId() {
        assertNull(Menagerie.getAnimal(-999));
    }
}
