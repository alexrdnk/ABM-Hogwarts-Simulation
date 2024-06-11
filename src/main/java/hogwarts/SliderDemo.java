package hogwarts;

import javax.swing.*;
import java.awt.*;

public class SliderDemo extends JPanel {
    private final JSlider slider; // Slider służący do wybierania wartości

    // Konstruktor klasy SliderDemo inicjuje slider z etykietą, minimalną i maksymalną wartością oraz wartością początkową
    public SliderDemo(String labelText, int min, int max, int value) {
        // Inicjalizacja etykiety i suwaka
        JLabel label = new JLabel(labelText); // Tworzenie etykiety z podanym tekstem
        slider = new JSlider(min, max, value); // Tworzenie suwaka z zadanymi parametrami min, max i wartością początkową

        // Ustawienie głównych odstępów między znacznikami, znaczników i widoczności etykiet w zależności od zakresu
        if (max > 10) {
            slider.setMajorTickSpacing(10); // Dla większych zakresów, odstępy między głównymi znacznikami ustawiane są na 10
        } else {
            slider.setMajorTickSpacing(1); // Dla mniejszych zakresów, każda wartość ma znacznik
        }
        slider.setPaintTicks(true); // Włączenie rysowania znaczników
        slider.setPaintLabels(true); // Włączenie rysowania etykiet przy znacznikach

        // Ustawienie układu i dodanie komponentów
        setLayout(new BorderLayout()); // Użycie BorderLayout jako menedżera układu
        add(label, BorderLayout.NORTH); // Dodanie etykiety na górze panelu
        add(slider, BorderLayout.CENTER); // Dodanie suwaka w centrum panelu
    }

    // Metoda dostępowa do suwaka, jeśli jest potrzebny w innej części programu
    public JSlider getSlider() {
        return slider; // Zwrócenie referencji do suwaka
    }
}
