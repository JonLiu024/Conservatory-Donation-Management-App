package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



//Representing a log of the important events occurring in the conservatory
//A singleton design pattern is implemented to ensure that there is only one EventLog in the
//system and that the system has global access to the single instance of the EventLog
public class EventLog implements Iterable<Event> {

    private static EventLog theLog; //the only single eventLog object in the system
    private Collection<Event> events;

    //EFFECTS: constructs an eventLog object and restricts the instantiation of the class
    //within the class
    private EventLog() {
        events = new ArrayList<Event>();
    }


    //EFFECT: creates an instance of EventLog as following the singleton design pattern,
    //instantiates EventLog if it hasn't yet been instantiated, returns the instance of EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }


    //REQUIRES: e is not null
    //EFFECTS: e is added into the list of events
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: clears all events in events, an event of log clearance is constructed and added into the list of event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
