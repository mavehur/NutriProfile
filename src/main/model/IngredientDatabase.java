package model;

import java.util.ArrayList;
import java.util.List;


// Represents a collection of ingredients
public class IngredientDatabase {

    private final List<Ingredient> ingredientDb;


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
            return true;
        } else {
            return false;
        }
    }


}

