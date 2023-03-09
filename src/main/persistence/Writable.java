package persistence;

import org.json.JSONObject;


public interface Writable {

    // This method is modeled based on UBC CPSC 210 WorkRoom APP
    // EFFECTS: returns as JSON object
    JSONObject toJson();
}
