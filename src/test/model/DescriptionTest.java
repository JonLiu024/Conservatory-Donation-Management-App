package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DescriptionTest {

    private Wildlife wildlife1;
    private Description description;


    @BeforeEach
    public void setup() {
        wildlife1 = new Wildlife("snow leopard", 2000, ConservationStatus.CD, LocalDate.now());
        wildlife1.setDescription(description);
        description = new Description("This snow leopard is injured!");

    }

    @Test
    public void testConstructor() {
        assertEquals("This snow leopard is injured!", description.getContents());
    }

}
