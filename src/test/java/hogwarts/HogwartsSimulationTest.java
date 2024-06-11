package hogwarts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HogwartsSimulationTest {
    private HogwartsSimulation simulation;

    @BeforeEach
    public void setup() {
        Environment environment = new Environment(50, 20);
        simulation = new HogwartsSimulation(environment);
    }

    @Test
    public void testAddWizard() {
        Wizard wizard = new Wizard(1, 1, 1, 1, 1);
        simulation.addWizard(wizard);
        assertEquals(1, simulation.wizards.size());
    }


}
