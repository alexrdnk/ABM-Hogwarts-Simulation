package hogwarts;
import java.io.FileWriter;
import java.io.IOException;

public class SimulationData {

    private int stepSum = 0; // Suma wykonanych kroków symulacji
    protected long runTimes; // Czas wykonania symulacji
    protected int stepCounts, aurorsCount, deathEatersCount, aurorsHealth, deathEatersHealth, aurorsDamage, deathEatersDamage, weatherLevel, horcrux, souls; // Dane dotyczące symulacji
    protected String winner; // Zwycięzca symulacji

    // Metoda do ustawienia zwycięzcy symulacji
    public void WinnerInfo(String winner) {
        this.winner = winner;
    }

    // Metoda do dodawania czasu trwania pojedynczego uruchomienia symulacji
    public void addRunTime(long runTime) {
        this.runTimes = runTime;
    }

    // Metoda do zliczania kroków symulacji
    public void addStepCount(int steps) {
        stepSum += steps;
        System.out.println("Step count: " + stepSum);
    }

    // Metoda do zbierania informacji o czarodziejach biorących udział w symulacji
    public void wizardInfo(int aurorsCount, int deathEatersCount, int aurorsHealth, int deathEatersHealth, int aurorsDamage, int deathEatersDamage, int horcrux, int souls) {
        this.aurorsCount = aurorsCount;
        this.deathEatersCount = deathEatersCount;
        this.aurorsHealth = aurorsHealth;
        this.deathEatersHealth = deathEatersHealth;
        this.aurorsDamage = aurorsDamage;
        this.deathEatersDamage = deathEatersDamage;
        this.horcrux = horcrux;
        this.souls = souls;
    }

    // Metoda do informowania o poziomie magicznym nieba, co może wpływać na pogodę
    public void WeatherInfo(int skyMagicLevel) {
        weatherLevel = skyMagicLevel;
    }

    // Metoda do zapisywania danych symulacji do pliku
    public void saveDataToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            stepCounts = stepSum;
            writer.append("Winner: ").append(winner).append("\n");
            writer.append("\nRun time: ");
            writer.append(String.format("%s", runTimes));
            writer.append("\nSteps: ");
            writer.append(String.format("%s", stepCounts));
            writer.append("\nAurors Count: ");
            writer.append(String.format("%s", aurorsCount));
            writer.append("\nDeath Eaters Count: ");
            writer.append(String.format("%s", deathEatersCount));
            writer.append("\nAurors Health: ");
            writer.append(String.format("%s", aurorsHealth));
            writer.append("\nDeath Eaters Health: ");
            writer.append(String.format("%s", deathEatersHealth));
            writer.append("\nAurors Damage: ");
            writer.append(String.format("%s", aurorsDamage));
            writer.append("\nDeath Eaters Damage: ");
            writer.append(String.format("%s", deathEatersDamage));
            writer.append("\nHarry`s souls count: ");
            writer.append(String.format("%s", souls));
            writer.append("\nVoldemort horcrux count: ");
            writer.append(String.format("%s", horcrux));
            writer.append("\nWeather level: ");
            writer.append(String.format("%s", weatherLevel));
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    // Metoda do obsługi wyjątków wejścia/wyjścia
    private void handleIOException(IOException e) {
        System.err.println("Failed to write to file: " + e.getMessage());
    }

}

