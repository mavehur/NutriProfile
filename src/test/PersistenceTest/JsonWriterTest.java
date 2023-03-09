package PersistenceTest;

import model.Ingredient;
import model.IngredientDatabase;

import org.junit.jupiter.api.Test;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    // test to write a file that doesn't exist
    void testWriterFileNotExist() {
        try {
            IngredientDatabase id = new IngredientDatabase();
            JsonWriter writer = new JsonWriter("./data/file1.json");

            writer.open();

            fail("Your file doesn't exist.");

        } catch (IOException e) {
            // pass
        }
    }

    @Test
    // test to write a file that exists and is empty
    void testReaderEmptyIngredientDatabase() {
        try {
            IngredientDatabase id = new IngredientDatabase();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyIngredientDatabase.json");

            writer.open();
            writer.write(id);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyIngredientDatabase.json");
            id = reader.read();
            assertEquals(0, id.numberOfIngredients());
            // pass

        } catch (IOException e) {
            fail("IOException isn't suppose to occur");
        }
    }

    @Test
    // test to write a file
    void testWriterNonEmptyIngredientDatabase() {
        try {
            IngredientDatabase id = new IngredientDatabase();
            id.addToDb(new Ingredient("good", "cranberry", "kidney"));
            id.addToDb(new Ingredient("bad", "energy drink", "too much sugar"));
            id.addToDb(new Ingredient("bad", "candy", "too much sugar"));

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyIngredientDatabase.json");

            writer.open();
            writer.write(id);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyIngredientDatabase.json");
            id = reader.read();
            List<Ingredient> ingredDb = id.getIngredientDb();

            assertEquals(3, ingredDb.size());

            checkIngredient("good", "cranberry", "kidney", ingredDb.get(0));
            checkIngredient("bad", "energy drink", "too much sugar", ingredDb.get(1));
            checkIngredient("bad", "candy", "too much sugar", ingredDb.get(2));
            // pass

        } catch (IOException e) {
            fail("IOException isn't suppose to occur");
        }

    }}
