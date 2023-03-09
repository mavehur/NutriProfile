package persistence;

import model.IngredientDatabase;
import org.json.JSONObject;
import java.io.*;

// represents writer to writes IngredientDatabase as json in file
public class JsonWriter {
    private static final int SpaceForIndentation = 3;
    private PrintWriter writer;
    private String endPoint;

    // This method is modeled based on UBC CPSC 210 WorkRoom APP
    // EFFECTS: creates writer to write in file
    public JsonWriter(String endPoint) {
        this.endPoint = endPoint;
    }

    // This method is modeled based on UBC CPSC 210 WorkRoom APP
    // MODIFIES: this
    // EFFECTS: opens writer and throws FileNotFoundException if not able to open file.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(endPoint);
    }

    // This method is modeled based on UBC CPSC 210 WorkRoom APP
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // This method is modeled based on UBC CPSC 210 WorkRoom APP
    // MODIFIES: this
    // EFFECTS: writes IngredientDatabase as JSON in file
    public void write(IngredientDatabase id) {
        JSONObject json = id.toJson();
        saveInFile(json.toString(SpaceForIndentation));
    }

    // This method is modeled based on UBC CPSC 210 WorkRoom APP
    // MODIFIES: this
    // EFFECTS: writes as string in file
    private void saveInFile(String jsonS) {
        writer.print(jsonS);
    }
}


