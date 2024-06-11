package hogwarts;

public class HarryPotter extends Wizard {
    protected int xHarry, yHarry, HarryPotterSouls; // Współrzędne oraz liczba dusz Harry'ego
    protected double chanceToBreakHorcrux; // Prawdopodobieństwo zniszczenia horkruksa podczas ataku

    // Konstruktor inicjujący Harry'ego Pottera z pozycją, liczbą dusz i szansą na zniszczenie horkruksa
    public HarryPotter(int x, int y, int souls, double chanceToBreakHorcrux) {
        super(x, y, souls, 1, 1); // Wywołanie konstruktora klasy nadrzędnej z określonymi wartościami obrażeń i zespołu
        this.xHarry = x;
        this.yHarry = y;
        this.HarryPotterSouls = souls;
        this.chanceToBreakHorcrux = chanceToBreakHorcrux;
    }

    // Getter dla dusz Harry'ego
    public int getHarrySouls() {
        return HarryPotterSouls;
    }

    // Setter dla dusz Harry'ego
    public void setHarrySouls(int souls) {
        HarryPotterSouls = souls; // Aktualizacja liczby dusz Harry'ego
    }

    // Metoda ataku na horkruksa, modyfikuje szansę trafienia w zależności od pogody środowiska
    public void horcruxAttack(Environment environment, Voldemort voldemort) {
        double bonusToHit = 0.05; // Określenie dodatkowej szansy na trafienie w słoneczną pogodę
        if (environment.isSunnyWeather()) { // Sprawdzenie, czy pogoda jest słoneczna
            this.chanceToBreakHorcrux += bonusToHit; // Zwiększenie szansy na zniszczenie horkruksa
            if (Math.random() < chanceToBreakHorcrux) {
                attackVoldemort(voldemort); // Atak na Voldemorta, jeśli losowa szansa jest mniejsza niż zmodyfikowana szansa
            }
            this.chanceToBreakHorcrux -= bonusToHit; // Resetowanie szansy po ataku
        } else {
            if (Math.random() < chanceToBreakHorcrux) {
                attackVoldemort(voldemort); // Atak na Voldemorta, jeśli losowa szansa jest mniejsza niż podstawowa szansa
            }
        }
    }

    // Metoda atakująca Voldemorta i redukująca liczbę jego horkruksów
    public void attackVoldemort(Voldemort voldemort) {
        voldemort.setHorcrux(voldemort.getHorcrux() - magicDamage); // Zmniejszenie liczby horkruksów o wartość obrażeń magicznych
        System.out.println("Harry zniszczył horkruks\nVoldemort ma " + voldemort.getHorcrux() + " horkruksów");
    }

}

