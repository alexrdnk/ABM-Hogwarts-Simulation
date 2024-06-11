package hogwarts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HogwartsGUITest {
    private HogwartsGUI hogwartsGUI;
    private Environment environment;

@BeforeEach
public void setup() {
    environment = mock(Environment.class);
    when(environment.getWidth()).thenReturn(10);
    when(environment.getHeight()).thenReturn(10);
    HogwartsSimulation simulation = mock(HogwartsSimulation.class);
    hogwartsGUI = new HogwartsGUI(environment, 10, simulation);
}

    @Test
    public void setupGridPanel_setsCorrectDimensions() {
        when(environment.getWidth()).thenReturn(10);
        when(environment.getHeight()).thenReturn(10);
        hogwartsGUI.setupGridPanel();
        assertEquals(100, hogwartsGUI.gridPanel.getPreferredSize().width);
        assertEquals(100, hogwartsGUI.gridPanel.getPreferredSize().height);
    }

    @Test
    public void updateGrid_callsPopulateGrid() {
        HogwartsGUI spyHogwartsGUI = Mockito.spy(hogwartsGUI);
        spyHogwartsGUI.updateGrid();
        verify(spyHogwartsGUI, times(1)).populateGrid();
    }


}