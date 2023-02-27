package ui;

import model.Ingredient;
import model.IngredientDatabase;

import java.util.List;
import java.util.Scanner;

// NutriProfile application
public class NutriProfile {
    private static Ingredient singleIngredient;
    private static IngredientDatabase ingredDb;
    private static List<Ingredient> ingredientList;
    private static Scanner input;
    private static int choose;

    // EFFECTS: runs NutriProfile application
    public NutriProfile() {

        ingredDb = new IngredientDatabase();
        ingredientList = ingredDb.getIngredientDb();

        input = new Scanner(System.in);

        runNutriProfile();
    }

    // MODIFIES: this
    // EFFECTS: processes input from user
    private void runNutriProfile() {
        boolean isAppRunning = true;

        while (isAppRunning) {
            displayMainMenu();
            choose = input.nextInt();
            input.nextLine();

            if (choose == 0) {
                isAppRunning = false;
            } else {
                chooseMainOption(choose);
            }
        }
        System.out.println("See you next time!");
        System.exit(0); // exit the application

    }

    // EFFECTS: displays main menu of options
    public static void displayMainMenu() {
        System.out.println("\t\t==========Welcome To NutriProfile==========");
        System.out.println("\t\t                                           ");
        System.out.println("\t\t          1. Search an ingredient          ");
        System.out.println("\t\t          2. View a list of ingredients    ");
        System.out.println("\t\t          3. Add ingredients               ");
        System.out.println("\t\t          4. Delete ingredients            ");
        System.out.println("\t\t          0. Exit                          ");
        System.out.println("\t\t                                           ");
        System.out.println("\t\t===========================================");

        System.out.println("Enter the number of an option: ");


    }

    // MODIFIES: this
    // EFFECTS: processes choice user makes
    public static void chooseMainOption(int choose) {

        if (choose == 1) {
            searchIngredient();
        } else if (choose == 2) {
            viewIngredientsList();
        } else if (choose == 3) {
            addIngredients();
        } else if (choose == 4) {
            deleteIngredients();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

    }

    // MODIFIES: this
    // EFFECTS: conducts searching ingredient user looks for and returns it
    public static void searchIngredient() {

        if (ingredDb.isIngredientDbEmpty()) {
            System.out.println("Your list is empty. Please add it.");
        } else {
            System.out.println("Which ingredient are you looking for?: ");
            String chosenName = input.nextLine();

            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getName().equalsIgnoreCase(chosenName)) {
                    // Name exists, so return its corresponding values
                    System.out.println("Category: " + ingredient.getCategory()
                            + ", Ingredient Name: " + ingredient.getName()
                            + ", Reason: " + ingredient.getReason());
//                    isIngredientFound = true;

                } else {
                    System.out.println("It doesn't exist. Please add it.");

                }
            }
        }
    }

    // EFFECTS: conducts viewing list of ingredients stored
    public static void viewIngredientsList() {
        if (ingredDb.isIngredientDbEmpty()) {
            System.out.println("Your list is empty. Please add ingredient.");
        } else {
            System.out.println("You chose to view a list");
            int ingredIndex = 0;

            // Ask user to choose a category
            System.out.print("Enter a category (GOOD or BAD): ");
            String chosenCategory = input.nextLine();

            // Output the list of items under the chosen category
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
        System.out.println("You chose to add an ingredient");

        while (true) {
            System.out.println("Enter category name (GOOD or BAD): ");
            String category = input.nextLine();

            System.out.println("Enter ingredient name: ");
            String name = input.nextLine();

            System.out.println("Enter reason (e.g. allergy causing): ");
            String reason = input.nextLine();

            if (category == null || name == null || reason == null
                    || category.isEmpty() || name.isEmpty() || reason.isEmpty()) {
                System.out.println("Please fill in all fields.");
            } else {
                Ingredient ingredient = new Ingredient(category, name, reason);
                boolean check = ingredDb.addToDb(ingredient);
                if (check) {
                    System.out.println(ingredient.getName() + " is successfully added!");
                    break;
                } else {
                    System.out.println(ingredient.getName() + " is already in the list. Please add a different one");
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
        return ingredientCollection.get(index);
    }

    // MODIFIES: this
    // EFFECTS: delete ingredient from database by choosing its index
    public static void deleteIngredients() {
        System.out.println("Enter ingredient number to delete");
        Ingredient ingredient = chooseIngredient(ingredientList);
        ingredDb.deleteIngredientFromDb(ingredient);
        System.out.print(ingredient.getName() + " is successfully deleted!");
    }

}