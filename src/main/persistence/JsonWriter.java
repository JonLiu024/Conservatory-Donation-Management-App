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
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECT: converts the destination pathname string to an abstract pathname,
    // and opens writer for writing, throws FileNotFoundException if destination file cannot be
    //opened for writing
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    public void open() throws FileNotFoundException {
        File file = new File(destination);
        writer = new PrintWriter(file);
    }


    //REQUIRES: cs is not null
    //MODIFIES: this
    //EFFECTS: converts the data in conservation site cs into a JSON object and writes
    //the JSON to file.
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    public void write(ConservationSite cs) {
        JSONObject json = cs.toJson();
        saveToFile(json.toString(INDENTFACTOR));
    }


    //MODIFIES: this
    //EFFECTS: closes writer
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: print the json to the file
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private void saveToFile(String json) {
        writer.print(json);
    }

}
