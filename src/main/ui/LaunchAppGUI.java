package ui;

import model.ConservationSite;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;

public class LaunchAppGUI {
    private static final String JSON_STORE = "./data/conservatory.json"; //sources file's pathname
    private Scanner scanner;   //scanner to capture user input
    private ConservationSite conservationSite; //our conservation site
    private JsonWriter jsonWriter; //JSonWriter to write to file
    private JsonReader jsonReader; //JSonReader to read from file



    //MODIFIES: this
    //EFFECT: initiates the conservation site and the scanner object to capture user inputs
    private void init() {
        conservationSite = new ConservationSite();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


}
