package hogwarts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationDataTest {
    private SimulationData simulationData;

    @BeforeEach
    public void setup() {
        simulationData = new SimulationData();
    }

    @Test
    public void addRunTime_increasesRunTimesSize() {
        simulationData.addRunTime(1000L);
        assertEquals(1000, simulationData.runTimes);
    }

    @Test
    public void wizardInfo_increasesWizardInfoSize() {
        simulationData.wizardInfo(5, 5, 100, 100, 10, 10, 3 ,5);
        assertEquals(5, simulationData.aurorsCount);
        assertEquals(5, simulationData.deathEatersCount);
        assertEquals(100, simulationData.aurorsHealth);
        assertEquals(100, simulationData.deathEatersHealth);
        assertEquals(10, simulationData.aurorsDamage);
        assertEquals(10, simulationData.deathEatersDamage);
        assertEquals(3, simulationData.horcrux);
        assertEquals(5, simulationData.souls);
    }

    @Test
    public void weatherInfo_increasesWeatherLevelSize() {
        simulationData.WeatherInfo(5);
        assertEquals(5, simulationData.weatherLevel);
    }

    @Test
    public void winnerInfo_setsWinner() {
        simulationData.WinnerInfo("Aurors");
        assertEquals("Aurors", simulationData.winner);
    }
}
