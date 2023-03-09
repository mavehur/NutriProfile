package PersistenceTest;

import model.Ingredient;
import model.IngredientDatabase;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import persistence.JsonReader;

import java.io.IOException;
import java.util.List;


public class JsonReaderTest extends JsonTest {

    @Test
    // test to read a file that doesn't exist
    void testReaderFileNotExist() {
        JsonReader reader = new JsonReader("./data/File1.json");

        try {
            IngredientDatabase id = reader.read();
            fail("Your file doesn't exist.");

        } catch (IOException e) {
            // pass
        }
    }

    @Test
    // test to read a file that exists but is empty
    void testReaderEmptyIngredientDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyIngredientDatabase.json");

        try {
            IngredientDatabase id = reader.read();
            assertEquals(0, id.numberOfIngredients());
            // pass

        } catch (IOException e) {
            fail("IOException isn't suppose to occur.");
        }
    }

    @Test
    // test to read a file with ingredients stored
    void testReaderNonEmptyIngredientDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyIngredientDatabase.json");

        try {
            IngredientDatabase id = reader.read();
            List<Ingredient> ingredDb = id.getIngredientDb();

            assertEquals(3, ingredDb.size());

            checkIngredient("good", "coffee", "life saver", ingredDb.get(0));
            checkIngredient("good", "orange", "vitamin c booster", ingredDb.get(1));
            checkIngredient("bad", "coke", "too much sugar", ingredDb.get(2));
            // pass

        } catch (IOException e) {
            fail("There's something wrote with file. Can't load from file");
        }
    }
}
