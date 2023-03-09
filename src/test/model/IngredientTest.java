package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for Ingredient class
public class IngredientTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;

    @BeforeEach
     void runBefore() {
        ingredient1 = new Ingredient("GOOD", "Cranberry", "Kidney Infection");
        ingredient2 = new Ingredient("GOOD", "Orange", "Vitamin C");
        ingredient3 = new Ingredient("BAD", "White flour", "Gluten");
    }

    @Test
    // test for getter and setter
    void changeValue() {
        ingredient1.setCategory("GOOD");
        ingredient1.setName("Cranberry");
        ingredient1.setReason("Kidney");
        assertEquals(ingredient1.getCategory(), "GOOD");
        assertEquals(ingredient1.getName(), "Cranberry");
        assertEquals(ingredient1.getReason(), "Kidney");
    }

    @Test
    void testConstructor() {
        assertEquals(ingredient1.getCategory(), "GOOD");
        assertEquals(ingredient2.getCategory(), "GOOD");
        assertEquals(ingredient3.getCategory(), "BAD");

        assertEquals(ingredient1.getName(), "Cranberry");
        assertEquals(ingredient2.getName(), "Orange");
        assertEquals(ingredient3.getName(), "White flour");

        assertEquals(ingredient1.getReason(), "Kidney Infection");
        assertEquals(ingredient2.getReason(), "Vitamin C");
        assertEquals(ingredient3.getReason(), "Gluten");

    }

//    // EFFECTS: returns each element in ingredient as string
//    public String toString() {
//        return "category: " + category + ", name: " + name + ", reason: " + reason;
//    }


    @Test
    void testToString() {
        String expectedValue1 = "category: GOOD, name: Cranberry, reason: Kidney Infection";
        String expectedValue2 = "category: GOOD, name: Orange, reason: Vitamin C";

        assertEquals(ingredient1.toString(), expectedValue1);
        assertEquals(ingredient2.toString(), expectedValue2);
    }

}