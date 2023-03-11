package persistence;

import org.json.JSONObject;

//Representing an interface for model classes to implement their toJson methods
public interface Writable {

    JSONObject toJson();
}
