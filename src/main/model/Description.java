package model;

import org.json.JSONObject;
import persistence.Writable;

//Representing the description associated with an injured wildlife
public class Description implements Writable {

    private String content; //the story and description (in words) of the animal

    //REQUIRES: content is not null
    //EFFECT: creates a description object and set its content to be a story
    public Description(String content) {
        setContents(content);

    }

    //setters
    public void setContents(String content) {

        this.content = content;
    }

    //getters
    public String getContents() {

        return content;
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", content);
        return jsonObject;
    }
}
