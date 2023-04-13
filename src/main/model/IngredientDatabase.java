package model;

import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a collection of ingredients
public class IngredientDatabase implements Writable {
    private List<Ingredient> ingredientDb;

    //EFFECTS: creates database for ingredients stored and retrieved
    public IngredientDatabase() {
        this.ingredientDb = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: if ingredient name doesn't exist in database, add it, otherwise false
    public Boolean addToDb(Ingredient ingredient) {
        for (Ingredient i : ingredientDb) {
            if (i.getName().equalsIgnoreCase(ingredient.getName())) {
                return false;
            }
        }
        ingredientDb.add(ingredient);
        EventLog.getInstance().logEvent(new Event("Ingredient has been added to the database"
                + "\nCategory: " + ingredient.getCategory()
                + "\nName: " + ingredient.getName()
                + "\nReason: " + ingredient.getReason()));
        return true;
    }

    // EFFECTS: returns true if database is empty, otherwise false.
    public Boolean isIngredientDbEmpty() {
        return ingredientDb.isEmpty();
    }

    // EFFECTS: returns database
    public List<Ingredient> getIngredientDb() {
        return ingredientDb;
    }

    // MODIFIES: this
    // EFFECTS:  removes ingredient from database if index is in range, otherwise false
    public Boolean deleteIngredientFromDb(int index) {
        if (!(index >= ingredientDb.size())) {
            this.ingredientDb.remove(index);
            EventLog.getInstance().logEvent(new Event("Ingredient has been deleted from the database"));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if ingredient exists in database, delete it, otherwise false
    public boolean deleteIngredientFromDb(Ingredient ingredient) {
        if (ingredientDb.contains(ingredient)) {
            this.ingredientDb.remove(ingredient);
            EventLog.getInstance().logEvent(new Event("Ingredient has been deleted from the database"));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all ingredients from the database
    public boolean clearDb() {
        ingredientDb.clear();
        EventLog.getInstance().logEvent(new Event("All ingredients have been cleared from the database"));
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonOb = new JSONObject();
        jsonOb.put("ingredients", ingredientsToJson());
        return jsonOb;
    }

    // EFFECTS: returns ingredients in ingredientDatabase as JSON array
    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient id : ingredientDb) {
            jsonArray.put(id.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns the number of ingredients in IngredientDatabase
    public int numberOfIngredients() {
        return ingredientDb.size();
    }
}

