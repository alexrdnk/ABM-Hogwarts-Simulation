package hogwarts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnvironmentTest {
    private Environment environment;

    @BeforeEach
    public void setup() {
        environment = new Environment(10, 10);
    }

    @Test
    public void setUpdateFrequency_setsCorrectFrequency() {
        environment.setUpdateFrequency(3);
        assertEquals(15, environment.getUpdateFrequency());
    }

    @Test
    public void isValidPosition_returnsFalseForInvalidPosition() {
        assertFalse(environment.isValidPosition(-1, 5));
        assertFalse(environment.isValidPosition(5, -1));
        assertFalse(environment.isValidPosition(10, 5));
        assertFalse(environment.isValidPosition(5, 10));
    }

    @Test
    public void isValidPosition_returnsTrueForValidPosition() {
        assertTrue(environment.isValidPosition(0, 0));
        assertTrue(environment.isValidPosition(9, 9));
    }

    @Test
    public void isEmpty_returnsTrueForEmptyCell() {
        assertTrue(environment.isEmpty(5, 5));
    }

    @Test
    public void moveWizard_movesWizardToNewPosition() {
        Wizard wizard = new Wizard(5, 5, 100, 10, 1);
        environment.placeWizard(wizard, 5, 5);
        environment.moveWizard(wizard, 6, 6);
        assertNull(environment.getWizardAt(5, 5));
        assertEquals(wizard, environment.getWizardAt(6, 6));
    }

    @Test
    public void removeWizard_removesWizardFromGrid() {
        Wizard wizard = new Wizard(5, 5, 100, 10, 1);
        environment.placeWizard(wizard, 5, 5);
        environment.removeWizard(wizard);
        assertNull(environment.getWizardAt(5, 5));
    }

    @Test
    public void placeWizard_placesWizardOnGrid() {
        Wizard wizard = new Wizard(5, 5, 100, 10, 1);
        environment.placeWizard(wizard, 5, 5);
        assertEquals(wizard, environment.getWizardAt(5, 5));
    }
}
