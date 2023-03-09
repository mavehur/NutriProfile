package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

// Unit tests for IngredientDatabase class
public class IngredientDatabaseTest {

    private IngredientDatabase database;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;


    @BeforeEach
    void runBefore() {
        database = new IngredientDatabase();
        ingredient1 = new Ingredient("GOOD", "Cranberry", "Kidney Infection");
        ingredient2 = new Ingredient("GOOD", "Orange", "Vitamin C");
        ingredient3 = new Ingredient("BAD", "White flour", "Gluten");
    }

    @Test
    // test for getter and setter
    void changeValue() {
        ingredient2.setCategory("GOOD");
        ingredient2.setName("Orange");
        ingredient2.setReason("Vitamin C");
        assertEquals(ingredient2.getCategory(), "GOOD");
        assertEquals(ingredient2.getName(), "Orange");
        assertEquals(ingredient2.getReason(), "Vitamin C");
    }

    @Test
    // test for AddToDb by adding one ingredient
    void testAddOneIngredientsToDb() {
        assertTrue(database.addToDb(ingredient1));
        assertFalse(database.addToDb(ingredient1));
    }

    @Test
    // test for AddtoDb by adding multiple ingredients
    void testAddMultipleIngredientsToDb() {
        assertTrue(database.addToDb(ingredient1));
        assertTrue(database.addToDb(ingredient2));
        assertTrue(database.addToDb(ingredient3));
        assertFalse(database.addToDb(ingredient1));
        assertFalse(database.addToDb(ingredient2));
        assertFalse(database.addToDb(ingredient3));
    }

    @Test
    // test for isIngredientDbEmpty
    void testIsIngredientDbEmpty() {
        assertTrue(database.isIngredientDbEmpty());
        database.addToDb(ingredient1);
        assertFalse(database.isIngredientDbEmpty());
    }

    @Test
    // test for GetIngredientDb
    void testGetIngredientDb() {
        List<Ingredient> ingredients = database.getIngredientDb();
        assertTrue(ingredients.isEmpty());
        database.addToDb(ingredient1);
        database.addToDb(ingredient2);
        ingredients = database.getIngredientDb();
        assertEquals(ingredients.size(), 2);
        assertTrue(ingredients.contains(ingredient1));
        assertTrue(ingredients.contains(ingredient2));
        assertFalse(ingredients.contains(ingredient3));
    }

    @Test
    // test for DeleteIngredientFromDb by deleting one ingredient by index
    void testDeleteOneIngredientFromDbByIndex() {
        assertFalse(database.deleteIngredientFromDb(0));
        database.addToDb(ingredient1);
        assertFalse(database.deleteIngredientFromDb(1));
        assertTrue(database.deleteIngredientFromDb(0));
        assertTrue(database.isIngredientDbEmpty());
    }

    @Test
    // test for DeleteIngredientFromDb by deleting multiple ingredients by index
    void testDeleteMultipleIngredientFromDbByIndex() {
        assertFalse(database.deleteIngredientFromDb(0));
        database.addToDb(ingredient1);
        database.addToDb(ingredient2);
        database.addToDb(ingredient3);
        assertTrue(database.deleteIngredientFromDb(0));
        assertTrue(database.deleteIngredientFromDb(1));
        assertEquals(database.numberOfIngredients(), 1);
        assertFalse(database.isIngredientDbEmpty());
        assertTrue(database.deleteIngredientFromDb(0));
        assertTrue(database.isIngredientDbEmpty());
    }

    @Test
    // test for DeleteIngredientFromDb by deleting one ingredient by Ingredient
    void testDeleteOneIngredientFromDbByIngredient() {
        assertFalse(database.deleteIngredientFromDb(ingredient1));
        database.addToDb(ingredient1);
        assertFalse(database.deleteIngredientFromDb(ingredient2));
        assertTrue(database.deleteIngredientFromDb(ingredient1));
        assertTrue(database.isIngredientDbEmpty());
    }

    @Test
    // test for DeleteIngredientFromDb by deleting multiple ingredients by Ingredient
    void testDeleteMultipleIngredientFromDbByIngredient() {
        assertFalse(database.deleteIngredientFromDb(ingredient2));
        database.addToDb(ingredient1);
        database.addToDb(ingredient2);
        assertFalse(database.deleteIngredientFromDb(ingredient3));
        assertTrue(database.deleteIngredientFromDb(ingredient2));
        assertFalse(database.isIngredientDbEmpty());
        assertTrue(database.deleteIngredientFromDb(ingredient1));
        assertTrue(database.isIngredientDbEmpty());
    }



}