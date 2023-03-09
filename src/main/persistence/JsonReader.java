package persistence;

import model.Ingredient;
import model.IngredientDatabase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.file.Files;
import org.json.*;

// Represents reader that reads IngredientDatabase from JSON data from file
public class JsonReader {
    private String code;

    // EFFECTS: creates reader to read from code file
    public JsonReader(String code) {
        this.code = code;
    }

    // EFFECTS: reads IngredientDatabase from file and returns it, and
    //          throws IOException when error occurs while reading data from file
    public IngredientDatabase read() throws IOException {
        String jsonData = readFile(code);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseIngredientDatabase(jsonObject);
    }

    // EFFECTS: reads code file as string and returns it
    private String readFile(String code) throws IOException {
        StringBuilder dataCreator = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(code), StandardCharsets.UTF_8)) {
            stream.forEach(dataCreator::append);
        }
        return dataCreator.toString();
    }

    // EFFECTS: parses IngredientDatabase from JSON object and returns it
    private IngredientDatabase parseIngredientDatabase(JSONObject jsonObject) {
        IngredientDatabase id = new IngredientDatabase();
        addIngredients(id, jsonObject);
        return id;
    }

    // MODIFIES: id
    // EFFECTS: parses ingredient from JSON object and adds it to IngredientDatabase
    private void addIngredient(IngredientDatabase id, JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        String name = jsonObject.getString("name");
        String reason = jsonObject.getString("reason");
        Ingredient ingredient = new Ingredient(category, name, reason);
        id.addToDb(ingredient);
    }

    // MODIFIES: id
    // EFFECTS: parses ingredients from JSON object and adds them to IngredientDatabase
    private void addIngredients(IngredientDatabase id, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ingredients");
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(id, nextIngredient);
        }
    }
}

