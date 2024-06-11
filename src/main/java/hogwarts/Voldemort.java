package hogwarts;

public class Voldemort extends Wizard {
    protected int xVoldemort, yVoldemort, horcrux; // Współrzędne i liczba horkruksów Voldemorta
    protected double chanceToKill; // Prawdopodobieństwo zabicia Harry'ego i zdobycia dodatkowego horkruksa

    // Konstruktor inicjujący Voldemorta z pozycją, liczbą horkruksów i szansą na zabójstwo
    public Voldemort(int x, int y, int horcrux, double chanceToKill) {
        super(x, y, horcrux, 1, 2); // Wywołanie konstruktora klasy nadrzędnej z określonymi wartościami obrażeń i zespołu
        this.xVoldemort = x;
        this.yVoldemort = y;
        this.horcrux = horcrux;
        this.chanceToKill = chanceToKill;
    }

    // Getter dla liczby horkruksów Voldemorta
    public int getHorcrux() {
        return horcrux;
    }

    // Setter dla liczby horkruksów Voldemorta
    public void setHorcrux(int newHorcrux) {
        horcrux = newHorcrux; // Aktualizacja liczby horkruksów
    }

    // Metoda do ataku na Harry'ego i próby zabicia go oraz zdobycia duszy na nowy horkruks
    public void killAndTakeSoul(Environment environment, HarryPotter harry) {
        double bonusToKill = 0.03; // Dodatkowa szansa na zabójstwo w niepogodę
        if (!environment.isSunnyWeather()) { // Sprawdzenie, czy pogoda nie jest słoneczna
            this.chanceToKill += bonusToKill; // Zwiększenie szansy na zabójstwo
            if (Math.random() < chanceToKill) {
                attackHarry(harry); // Atak na Harry'ego, jeśli losowa szansa jest mniejsza niż zmodyfikowana szansa
            }
            this.chanceToKill -= bonusToKill; // Resetowanie szansy po ataku
        } else {
            if (Math.random() < chanceToKill) {
                attackHarry(harry); // Atak na Harry'ego, jeśli losowa szansa jest mniejsza niż podstawowa szansa
            }
        }
    }

    // Metoda atakująca Harry'ego, zabierająca mu duszę i tworząca dodatkowy horkruks
    public void attackHarry(HarryPotter harry) {
        harry.setHarrySouls(harry.getHarrySouls() - magicDamage); // Zmniejszenie liczby dusz Harry'ego o wartość obrażeń magicznych
        setHorcrux(getHorcrux() + 1); // Zwiększenie liczby horkruksów Voldemorta
        System.out.println("Voldemort zabrał duszę Harry'ego i ma teraz więcej horkruksów\n" + "Harry ma: " + harry.getHarrySouls() + " dusz");
    }

}

