package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {

    private Event event1;
    private Event event2;
    private Date date;


    @BeforeEach
    public void setup() {
        event1 = new Event("event1 is created!");
        date = Calendar.getInstance().getTime();

    }


    @Test
    public void testConstructor() {
        assertEquals("event1 is created!", event1.getDescription());
        assertEquals(date, event1.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "event1 is created!", event1.toString());
    }

    @Test
    public void testEqualsNullObj() {
        event2 = null;
        assertFalse(event1.equals(event2));
    }


    @Test
    public void testEqualDifferentType() {
        String event3 = "";
        assertFalse(event1.equals(event3));
    }

}
