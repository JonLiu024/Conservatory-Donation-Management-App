package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestEventLog {
    private EventLog testEventLog;
    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    public void setup() {
        testEventLog = EventLog.getInstance();
        event1 = new Event("event 1 is created!");
        event2 = new Event("event 2 is created!");
        event3 = new Event("event 3 is created!");
        testEventLog.logEvent(event1);
        testEventLog.logEvent(event2);
        testEventLog.logEvent(event3);
    }

    @Test
    public void testLogEvent() {
        List<Event> eventList = new ArrayList<>();
        for (Event event: testEventLog) {
            eventList.add(event);
        }
        assertTrue(eventList.contains(event1));
        assertTrue(eventList.contains(event2));
        assertTrue(eventList.contains(event3));
    }



    @Test
    public void testClear() {
        List<Event> eventList = new ArrayList<>();
        Iterator<Event> itr = EventLog.getInstance().iterator();
        testEventLog.clear();
        Date dateClearance = Calendar.getInstance().getTime();

        for (Event event: testEventLog) {
            eventList.add(event);
        }
        assertEquals(1, eventList.size());
        assertTrue(itr.hasNext());
        assertEquals(dateClearance, eventList.get(0).getDate());
        assertEquals("Event log cleared.", eventList.get(0).getDescription());


        assertFalse(eventList.contains(event3));
        assertFalse(eventList.contains(event2));
        assertFalse(eventList.contains(event1));
    }


}
