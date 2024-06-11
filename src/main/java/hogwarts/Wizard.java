package hogwarts;

import java.util.Random;

public class Wizard {
    protected int x, y; // Współrzędne czarodzieja na siatce środowiska
    protected int health; // Aktualne zdrowie czarodzieja
    protected int magicDamage; // Obrażenia magiczne, które czarodziej może zadać
    protected int team; // Identyfikator zespołu, do którego należy czarodziej

    private int lastDx = 0; // Ostatni kierunek ruchu poziomego
    private int lastDy = 0; // Ostatni kierunek ruchu pionowego
    private static final Random RANDOM = new Random(); // Generator liczb losowych dla ruchów i działań

    // Konstruktor do inicjalizacji obiektu Wizard z jego pozycją, zdrowiem, obrażeniami magicznymi i zespołem
    public Wizard(int x, int y, int health, int magicDamage, int team) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.magicDamage = magicDamage;
        this.team = team;
    }

    // Getter dla współrzędnej x
    public int getX() {
        return x;
    }

    // Getter dla współrzędnej y
    public int getY() {
        return y;
    }

    // Getter dla zespołu
    public int getTeam() {
        return team;
    }

    // Setter dla zespołu
    public void setTeam(int team) {
        this.team = team;
    }

    // Getter dla zdrowia
    public int getHealth() {
        return health;
    }

    // Setter dla zdrowia
    public void setHealth(int health) {
        this.health = health;
    }

    // Metoda do przemieszczania czarodzieja w środowisku
    public void move(Environment environment) {
        int dx, dy;
        boolean moved = false;
        if (this instanceof HarryPotter) { // Specyficzny ruch dla postaci Harry'ego Pottera
            environment.moveWizard(this, 7, 15);
        } else if (this instanceof Voldemort) { // Specyficzny ruch dla postaci Voldemorta
            environment.moveWizard(this, 42, 15);
        } else { // Ogólna logika ruchu dla innych czarodziejów
            for (int attempts = 0; attempts < 10 && !moved; attempts++) {
                dx = (RANDOM.nextBoolean() ? lastDx : RANDOM.nextInt(3) - 1); // Losowe wybieranie kontynuowania w ostatnim kierunku lub zmiana
                dy = (RANDOM.nextBoolean() ? lastDy : RANDOM.nextInt(3) - 1);

                int newX = x + dx;
                int newY = y + dy;

                if ((dx != 0 || dy != 0) && environment.isValidPosition(newX, newY) && environment.isEmpty(newX, newY)) {
                    environment.moveWizard(this, newX, newY); // Wykonanie ruchu
                    lastDx = dx; // Aktualizacja ostatniego kierunku
                    lastDy = dy;
                    moved = true;
                }
            }

            if (!moved) { // Jeśli nie znaleziono ważnego ruchu, zresetuj kierunki
                lastDx = 0;
                lastDy = 0;
            }
        }
    }

    // Metoda definiująca działania czarodzieja w środowisku
    public void act(Environment environment) {
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int newX = x + xOffset;
                int newY = y + yOffset;
                if (environment.isValidPosition(newX, newY)) {
                    Wizard wizard = environment.getWizardAt(newX, newY);
                    if (wizard != null && wizard.getTeam() != this.getTeam()) { // Sprawdzanie obecności wrogich czarodziejów w pobliżu
                        if (wizard instanceof HarryPotter || wizard instanceof Voldemort) {
                            move(environment); // Specyficzne zachowanie w obliczu głównych postaci
                        } else {
                            int chanceOfDefense = 10; // Procent szansy na obronę
                            if (RANDOM.nextInt(100) < chanceOfDefense) {
                                move(environment); // Próba ucieczki
                            } else {
                                attack(wizard); // Atak na wrogiego czarodzieja
                            }
                        }
                    }
                }
            }
        }
    }

    // Metoda do atakowania innego czarodzieja
    public void attack(Wizard enemy) {
        if (enemy != null && this.team != enemy.getTeam()) {
            enemy.setHealth(enemy.getHealth() - this.magicDamage); // Zmniejszenie zdrowia wroga o obrażenia magiczne
        }
    }
}
