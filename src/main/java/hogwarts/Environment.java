package hogwarts;

import java.util.Random;

public class Environment {
    private final Wizard[][] grid; // Siatka do przechowywania czarodziejów
    private final int width; // Szerokość siatki
    private final int height; // Wysokość siatki
    private boolean isSunnyWeather; // Flaga określająca, czy pogoda jest słoneczna
    private static final Random random = new Random(); // Generator liczb losowych
    private int stepCounter = 0;  // Licznik kroków symulacji
    private int updateFrequency;  // Częstotliwość zmiany pogody

    // Konstruktor inicjujący środowisko z danymi wymiarami
    public Environment(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Wizard[width][height];
        this.isSunnyWeather = random.nextBoolean();
    }

    // Ustawienie częstotliwości aktualizacji pogody na podstawie poziomu magii
    public void setUpdateFrequency(int magicLevel) {
        switch (magicLevel) {
            case 1:
                updateFrequency = 5;
                break;
            case 2:
                updateFrequency = 10;
                break;
            case 3:
                updateFrequency = 15;
                break;
            case 4:
                updateFrequency = 20;
                break;
            case 5:
                updateFrequency = 30;
                break;
        }
    }

    // Pobieranie ustawionej częstotliwości aktualizacji pogody
    public int getUpdateFrequency() {
        return updateFrequency;
    }

    // Aktualizacja pogody na podstawie licznika kroków i częstotliwości aktualizacji
    public void updateWeather() {
        if (++stepCounter >= updateFrequency) {
            isSunnyWeather = random.nextBoolean();
            stepCounter = 0;  // Reset licznika po aktualizacji pogody
        }
    }

    // Sprawdzanie, czy pogoda jest słoneczna
    public boolean isSunnyWeather() {
        return isSunnyWeather;
    }

    // Walidacja pozycji na siatce
    public boolean isValidPosition(int x, int y) {
        // Sprawdzenie, czy pozycja znajduje się w granicach siatki
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        // Sprawdzenie dodatkowego warunku dla konkretnego obszaru
        return x < 8 || x > 41 || y < 14 || y > 16;
    }

    // Sprawdzenie, czy dane miejsce jest puste
    public boolean isEmpty(int x, int y) {
        return grid[x][y] == null;
    }

    // Przesunięcie czarodzieja na nową pozycję w siatce
    public void moveWizard(Wizard wizard, int newX, int newY) {
        grid[wizard.getX()][wizard.getY()] = null;
        wizard.x = newX;
        wizard.y = newY;
        grid[newX][newY] = wizard;
    }

    // Usunięcie czarodzieja z siatki
    public void removeWizard(Wizard wizard) {
        grid[wizard.getX()][wizard.getY()] = null;
    }

    // Pobranie czarodzieja z danej pozycji
    public Wizard getWizardAt(int x, int y) {
        if (isValidPosition(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    // Umieszczenie czarodzieja w określonej pozycji, jeśli jest to możliwe
    public void placeWizard(Wizard wizard, int x, int y) {
        if (isValidPosition(x, y) && isEmpty(x, y)) {
            grid[x][y] = wizard;
        }
    }

    // Getter dla szerokości siatki
    public int getWidth() {
        return width;
    }

    // Getter dla wysokości siatki
    public int getHeight() {
        return height;
    }

}

