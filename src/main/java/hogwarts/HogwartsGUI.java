package hogwarts;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HogwartsGUI extends JFrame {

    private final Environment environment; // Przechowuje informacje o środowisku symulacji
    protected final JPanel gridPanel; // Panel do wyświetlania siatki symulacji
    private final int cellSize; // Rozmiar każdej komórki na siatce
    private final HogwartsSimulation simulation; // Obiekt przechowujący logikę symulacji
    private final Timer timer; // Timer do cyklicznego aktualizowania symulacji
    private Image backgroundImage; // Obraz tła dla interfejsu użytkownika
    private Map<String, ImageIcon> wizardImages; // Mapa przyporządkowująca typom czarodziejów ich obrazy

    public HogwartsGUI(Environment environment, int cellSize, HogwartsSimulation simulation) {
        this.environment = environment;
        this.cellSize = cellSize;
        this.simulation = simulation;
        this.gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        loadBackgroundImage("src\\main\\resources\\hogwarts.png");
        loadWizardImages();
        setupGridPanel();
        setupWindow();
        this.timer = new Timer(200, e -> {
            simulation.run(1);
            updateGrid();
        });
    }

    private void loadBackgroundImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        backgroundImage = icon.getImage();
    }

    private void loadWizardImages() {
        wizardImages = new HashMap<>();
        wizardImages.put("auror_sun", new ImageIcon("src\\main\\resources\\Aurors_sun.png"));
        wizardImages.put("deathEater_sun", new ImageIcon("src\\main\\resources\\DeathEaters_sun.png"));
        wizardImages.put("Harry_sun", new ImageIcon("src\\main\\resources\\Harry_sun.png"));
        wizardImages.put("Voldemort_sun", new ImageIcon("src\\main\\resources\\Voldemort_sun.png"));
        wizardImages.put("auror_rain", new ImageIcon("src\\main\\resources\\Aurors_rain.png"));
        wizardImages.put("deathEater_rain", new ImageIcon("src\\main\\resources\\DeathEaters_rain.png"));
        wizardImages.put("Harry_rain", new ImageIcon("src\\main\\resources\\Harry_rain.png"));
        wizardImages.put("Voldemort_rain", new ImageIcon("src\\main\\resources\\Voldemort_rain.png"));
    }

    protected void setupGridPanel() {
        this.gridPanel.setOpaque(false);
        this.gridPanel.setPreferredSize(new Dimension(environment.getWidth() * cellSize, environment.getHeight() * cellSize));
        this.gridPanel.setLayout(new GridLayout(environment.getHeight(), environment.getWidth()));
    }

    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hogwarts School of Witchcraft and Wizardry Simulation");
        getContentPane().add(gridPanel);
        pack();
        setVisible(true);
        updateGrid();

        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(250, 500));

        JPanel photoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        photoPanel.setPreferredSize(new Dimension(1250, 600));
        photoPanel.setVisible(true);

        SliderDemo aurorsSlider = new SliderDemo("Number of Aurors: ", 0, 100, 10);
        SliderDemo deathEatersSlider = new SliderDemo("Number of Death Eaters: ", 0, 100, 10);
        SliderDemo aurorsHealthSlider = new SliderDemo("Health of Aurors: ", 0, 50, 10);
        SliderDemo aurorsMagicDamageSlider = new SliderDemo("Damage of Aurors: ", 0, 50, 10);
        SliderDemo deathEatersHealthSlider = new SliderDemo("Health of Death Eaters: ", 0, 50, 10);
        SliderDemo deathEatersMagicDamageSlider = new SliderDemo("Damage of Death Eaters: ", 0, 50, 10);
        SliderDemo SkyMagicLevelSlider = new SliderDemo("How often weather changes: ", 0, 5, 1);
        SliderDemo HarrySlider = new SliderDemo("Num of Harry Potter`s souls: ", 1, 10, 1);
        SliderDemo VoldemortSlider = new SliderDemo("Num of Voldemort`s horcrux: ", 1, 10, 1);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton setupButton = new JButton("Setup");

        controlPanel.add(aurorsSlider);
        controlPanel.add(aurorsHealthSlider);
        controlPanel.add(aurorsMagicDamageSlider);
        controlPanel.add(deathEatersSlider);
        controlPanel.add(deathEatersHealthSlider);
        controlPanel.add(deathEatersMagicDamageSlider);
        controlPanel.add(SkyMagicLevelSlider);
        controlPanel.add(HarrySlider);
        controlPanel.add(VoldemortSlider);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(setupButton);

        getContentPane().add(photoPanel, BorderLayout.WEST);
        getContentPane().add(controlPanel, BorderLayout.EAST);

        setupButton.addActionListener(e -> {
            int aurorsCount = aurorsSlider.getSlider().getValue();
            int deathEatersCount = deathEatersSlider.getSlider().getValue();
            int aurorsHealth = aurorsHealthSlider.getSlider().getValue();
            int deathEatersHealth = deathEatersHealthSlider.getSlider().getValue();
            int aurorsDamage = aurorsMagicDamageSlider.getSlider().getValue();
            int deathEatersDamage = deathEatersMagicDamageSlider.getSlider().getValue();
            int skyMagicLevel = SkyMagicLevelSlider.getSlider().getValue();
            int harrySouls = HarrySlider.getSlider().getValue();
            int voldemortHorcrux = VoldemortSlider.getSlider().getValue();
            simulation.setup(aurorsCount, deathEatersCount, aurorsHealth, deathEatersHealth, aurorsDamage, deathEatersDamage, skyMagicLevel, harrySouls, voldemortHorcrux);
            System.out.println("Aurors count: " + aurorsCount + "\n"
                    + "DeathEater count: " + deathEatersCount + "\n"
                    + "Aurors health: " + aurorsHealth + "\n"
                    + "DeathEater health: " + deathEatersHealth + "\n"
                    + "Aurors damage: " + aurorsDamage + "\n"
                    + "DeathEater damage: " + deathEatersDamage + "\n"
                    + "Sky magic level: " + skyMagicLevel);
            updateGrid();
        });

        setupButton.addActionListener(e -> {
            photoPanel.setVisible(false);
            loadBackgroundImage("src\\main\\resources\\grey_bricks_with_red_green_line.png");
            photoPanel.repaint(); // Repaint to show the new background);
        });
        startButton.addActionListener(e -> timer.start());
        stopButton.addActionListener(e -> timer.stop());
    }

    public void updateGrid() {
        gridPanel.removeAll();
        populateGrid();
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    protected void populateGrid() {
        for (int y = 0; y < environment.getHeight(); y++) {
            for (int x = 0; x < environment.getWidth(); x++) {
                gridPanel.add(createCellLabel(x, y));
            }
        }
    }


    private JLabel createCellLabel(int x, int y) {
        JLabel cellLabel = new JLabel();
        cellLabel.setOpaque(false); // Ensure the label itself is transparent
        cellLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        Wizard wizard = environment.getWizardAt(x, y);
        if (wizard != null) {
            ImageIcon icon = null;
            if (wizard instanceof HarryPotter) {
                icon = environment.isSunnyWeather() ? wizardImages.get("Harry_sun") : wizardImages.get("Harry_rain");
            } else if (wizard instanceof Voldemort) {
                icon = environment.isSunnyWeather() ? wizardImages.get("Voldemort_sun") : wizardImages.get("Voldemort_rain");
            } else if (wizard.getTeam() == 1) {
                icon = environment.isSunnyWeather() ? wizardImages.get("auror_sun") : wizardImages.get("auror_rain");
            } else if (wizard.getTeam() == 2) {
                icon = environment.isSunnyWeather() ? wizardImages.get("deathEater_sun") : wizardImages.get("deathEater_rain");
            }

            if (icon != null) {
                cellLabel.setIcon(icon);
                cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }
        } else {
            cellLabel.setBackground(new Color(0, 0, 0, 0)); // Transparent background
            cellLabel.setOpaque(false);
        }
        return cellLabel;

    }
}