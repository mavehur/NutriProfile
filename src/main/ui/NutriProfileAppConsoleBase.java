package ui;

import model.Ingredient;
import model.IngredientDatabase;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// NutriProfile application
public class NutriProfileAppConsoleBase {

    private static IngredientDatabase ingredDb;
    private static Scanner input;
    private static final String JSONFileNameToStore = "./data/IngredientDatabase.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs NutriProfile application
    public NutriProfileAppConsoleBase() {
        ingredDb = new IngredientDatabase();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSONFileNameToStore);
        jsonReader = new JsonReader(JSONFileNameToStore);

        runNutriProfile();
    }

    // MODIFIES: this
    // EFFECTS: processes input from user
    private void runNutriProfile() {
        int choose;
        boolean isAppRunning = true;

        while (isAppRunning) {
            displayMainMenu();
            String userInput = input.nextLine();
            while (!checkUserInput(userInput)) {
                System.out.println("Invalid choice!");
                userInput = input.nextLine();
            }
            choose = Integer.parseInt(userInput);

            if (choose == 0) {
                isAppRunning = false;
                System.out.println("See you next time!");
                System.exit(0); // exit the application
            } else {
                chooseMainOption(choose);
            }
        }

    }

    // EFFECTS: displays main menu of options
    public static void displayMainMenu() {
        System.out.println("\t\t========= Welcome To NutriProfile =========");
        System.out.println("\t\t                                           ");
        System.out.println("\t\t          1. Search an ingredient          ");
        System.out.println("\t\t          2. View a list of ingredients    ");
        System.out.println("\t\t          3. Add ingredients               ");
        System.out.println("\t\t          4. Delete ingredients            ");
        System.out.println("\t\t          5. Save your list to file        ");
        System.out.println("\t\t          6. Load your list from file      ");
        System.out.println("\t\t          0. Exit                          ");
        System.out.println("\t\t                                           ");
        System.out.println("\t\t===========================================");

        System.out.print("Enter the number of an option: ");


    }

    // EFFECTS: checks if 0 <= user input <= 6, otherwise false.
    public boolean checkUserInput(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return Integer.parseInt(userInput) <= 6 && Integer.parseInt(userInput) >= 0;
    }

    // MODIFIES: this
    // EFFECTS: processes a choice user makes
    public void chooseMainOption(int choose) {

        if (choose == 1) {
            searchIngredient();
        } else if (choose == 2) {
            viewIngredientsList();
        } else if (choose == 3) {
            addIngredients();
        } else if (choose == 4) {
            deleteIngredients();
        } else if (choose == 5) {
            saveIngredientDatabase();
        } else if (choose == 6) {
            loadIngredientDatabase();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

    }


    // MODIFIES: this
    // EFFECTS: conducts searching ingredient user looks for and returns it
    public static void searchIngredient() {
        boolean check = false;

        if (ingredDb.isIngredientDbEmpty()) {
            System.out.println("Your list is empty. Please add a new ingredient.");
        } else {
            System.out.println("Which ingredient are you looking for?: ");
            String chosenName = input.nextLine();
            List<Ingredient> ingredientList = ingredDb.getIngredientDb();
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getName().equalsIgnoreCase(chosenName)) {
                    check = true;
                    System.out.println("Category: " + ingredient.getCategory()
                            + ", Name: " + ingredient.getName()
                            + ", Reason: " + ingredient.getReason());
                }
            }
            if (!check) {
                System.out.println("It doesn't exist. Please add it.");
            }

        }
    }

    // EFFECTS: conducts viewing list of ingredients stored
    public static void viewIngredientsList() {
        if (ingredDb.isIngredientDbEmpty()) {
            System.out.println("Your list is empty. Please add a new ingredient.");
        } else {
            System.out.println("You chose to view a list");
            int ingredIndex = 0;

            System.out.print("Enter a category (GOOD or BAD): ");
            String chosenCategory = input.nextLine();

            System.out.println("Ingredients under category: " + chosenCategory);
            List<Ingredient> ingredientList = ingredDb.getIngredientDb();
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getCategory().equalsIgnoreCase(chosenCategory)) {
                    System.out.println("#" + ingredIndex + "\tName: " + ingredient.getName()
                            + "\tReason: " + ingredient.getReason());
                    ingredIndex++;
                }
            }

        }

    }

    // MODIFIES: this
    // EFFECTS: adds ingredient to database
    public static void addIngredients() {
        System.out.println("You chose to add an ingredient.");

        while (true) {
            System.out.print("Enter category name (GOOD or BAD): ");
            String category = input.nextLine();

            System.out.print("Enter ingredient name: ");
            String name = input.nextLine();

            System.out.print("Enter reason (e.g. allergy causing): ");
            String reason = input.nextLine();

            if (category == null || name == null || reason == null
                    || category.isEmpty() || name.isEmpty() || reason.isEmpty()) {
                System.out.println("Please fill in all fields. ");
            } else {
                Ingredient ingredient = new Ingredient(category, name, reason);
                boolean check = ingredDb.addToDb(ingredient);
                if (check) {
                    System.out.println(ingredient.getName() + " is successfully added!");
                    break;
                } else {
                    System.out.println(ingredient.getName() + " is already in the list. Please add a different one.");
                }
            }
        }
    }

    // REQUIRES: index >= 0
    // EFFECTS: displays ingredients from database with corresponding index
    public static Ingredient chooseIngredient(List<Ingredient> ingredientCollection) {
        for (Ingredient ingredient: ingredientCollection) {
            System.out.println("For " + ingredient.getName() + " press " + ingredientCollection.indexOf(ingredient));
        }
        int index = input.nextInt();
        input.nextLine();

        return ingredientCollection.get(index);
    }

    // MODIFIES: this
    // EFFECTS: delete ingredient from database by choosing its index
    public static void deleteIngredients() {
        if (ingredDb.isIngredientDbEmpty()) {
            System.out.println("There's no ingredient to delete.");
        } else {
            System.out.print("Enter ingredient number to delete");
            Ingredient ingredient = chooseIngredient(ingredDb.getIngredientDb());
            ingredDb.deleteIngredientFromDb(ingredient);
            System.out.println(ingredient.getName() + " is successfully deleted!");
        }
    }

    // EFFECT: saves IngredientDatabase to file
    private void saveIngredientDatabase() {
        try {
            jsonWriter.open();
            jsonWriter.write(ingredDb);
            jsonWriter.close();

            System.out.println("Successfully saved!");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException occurred!");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads IngredientDatabase from file
    private void loadIngredientDatabase() {
        try {
            ingredDb = jsonReader.read();
            System.out.println("Successfully loaded!");

        } catch (IOException e) {
            System.out.println("IOException occurred!");
        }
    }

}