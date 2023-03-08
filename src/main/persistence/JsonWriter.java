package persistence;

import model.ConservationSite;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Representing a writer that writes JSON representation of conservation site to file
public class JsonWriter {
    private static final int INDENTFACTOR = 4;  //the indentation factor of contents writing to the file
    private PrintWriter writer;         //JsonWriter's PrintWriter that helps save to a file
    private String destination;         //the destination file that this JsonWriter save to

    //REQUIRES: destination is not null
    //EFFECTS: constructs a JsonWriter object to write the data to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //EFFECT: convert the destination pathname string to an abstract pathname,
    // and opens writer, throws FileNotFoundException if destination file cannot be
    //opened for writing
    public void open() throws FileNotFoundException {
        File file = new File(destination);
        writer = new PrintWriter(file);
    }

    public void write(ConservationSite cs) {
        JSONObject json = cs.toJson();
        saveToFile(json.toString(INDENTFACTOR));
    }

    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

}
