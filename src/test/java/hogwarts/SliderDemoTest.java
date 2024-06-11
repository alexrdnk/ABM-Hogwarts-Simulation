package hogwarts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SliderDemoTest {
    private SliderDemo sliderDemo;

    @BeforeEach
    public void setup() {
        sliderDemo = new SliderDemo("Test Slider", 0, 10, 5);
    }

    @Test
    public void sliderInitialValueIsCorrect() {
        assertEquals(5, sliderDemo.getSlider().getValue());
    }

    @Test
    public void sliderMinimumValueIsCorrect() {
        assertEquals(0, sliderDemo.getSlider().getMinimum());
    }

    @Test
    public void sliderMaximumValueIsCorrect() {
        assertEquals(10, sliderDemo.getSlider().getMaximum());
    }


    @Test
    public void sliderMajorTickSpacingIsCorrectForSmallRange() {
        SliderDemo smallRangeSliderDemo = new SliderDemo("Test Slider", 0, 5, 2);
        assertEquals(1, smallRangeSliderDemo.getSlider().getMajorTickSpacing());
    }

    @Test
    public void sliderTicksAreVisible() {
        assertTrue(sliderDemo.getSlider().getPaintTicks());
    }

    @Test
    public void sliderLabelsAreVisible() {
        assertTrue(sliderDemo.getSlider().getPaintLabels());
    }
}
