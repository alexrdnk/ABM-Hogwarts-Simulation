package hogwarts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WizardTest {
    private Wizard wizard;

    @Mock
    private Environment environment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        wizard = new Wizard(5, 5, 100, 10, 1);
    }

    @Test
    public void wizardMovesToValidEmptyPosition() {
        when(environment.isValidPosition(anyInt(), anyInt())).thenReturn(true);
        when(environment.isEmpty(anyInt(), anyInt())).thenReturn(true);
        wizard.move(environment);
        verify(environment, times(1)).moveWizard(eq(wizard), anyInt(), anyInt());
    }

    @Test
    public void wizardDoesNotMoveToInvalidPosition() {
        when(environment.isValidPosition(anyInt(), anyInt())).thenReturn(false);
        wizard.move(environment);
        verify(environment, times(0)).moveWizard(eq(wizard), anyInt(), anyInt());
    }

    @Test
    public void wizardDoesNotMoveToOccupiedPosition() {
        when(environment.isValidPosition(anyInt(), anyInt())).thenReturn(true);
        when(environment.isEmpty(anyInt(), anyInt())).thenReturn(false);
        wizard.move(environment);
        verify(environment, times(0)).moveWizard(eq(wizard), anyInt(), anyInt());
    }

@Test
public void wizardAttacksEnemy() {
    Wizard aurorWizard = new Wizard(6, 6, 100, 10, 1);
    Wizard deathEaterWizard = new Wizard(6, 6, 100, 10, 2);
    aurorWizard.attack(deathEaterWizard);
    assertEquals(90, deathEaterWizard.getHealth());
}

    @Test
    public void wizardDoesNotAttackFriend() {
        Wizard friend = new Wizard(6, 6, 100, 10, 1);
        when(environment.getWizardAt(anyInt(), anyInt())).thenReturn(friend);
        wizard.act(environment);
        assertEquals(100, friend.getHealth());
    }
}
