package PersistenceTest;

import model.Ingredient;
import static org.junit.jupiter.api.Assertions.assertEquals;

// This method is modeled based on UBC CPSC 210 WorkRoom APP
// test to check if each element in ingredient matches.
public class JsonTest {
    protected void checkIngredient(String category, String name, String reason, Ingredient ingredient) {
        assertEquals(category, ingredient.getCategory());
        assertEquals(name, ingredient.getName());
        assertEquals(reason, ingredient.getReason());
    }
}
