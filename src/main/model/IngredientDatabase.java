package model;

import java.util.ArrayList;
import java.util.List;


// list of ingredients !!!
public class IngredientDatabase {

    private final List<Ingredient> ingredientDb;

    public IngredientDatabase() {
        this.ingredientDb = new ArrayList<>();

    }

    public Boolean addToDb(Ingredient ingredient) {
        if (!ingredientDb.contains(ingredient)) {
            ingredientDb.add(ingredient);
            return true;
        } else {
            return false;
        }
    }

    public Boolean isIngredientDbEmpty() {
        return ingredientDb.isEmpty();
    }

    public Ingredient getIngredientIndex(int index) {
        return ingredientDb.get(index);
    }

    public List<Ingredient> getIngredientDb() {
        return ingredientDb;
    }

    public Boolean deleteIngredientFromDb(Ingredient ingredient) {
        if (ingredientDb.contains(ingredient)) {
            this.ingredientDb.remove(ingredient);
            return true;
        } else {
            return false;
        }
    }
}

