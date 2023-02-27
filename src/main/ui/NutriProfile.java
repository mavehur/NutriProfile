package ui;

import model.Ingredient;
import model.IngredientDatabase;

import java.util.List;
import java.util.Scanner;

public class NutriProfile {
    private static Ingredient singleIngredient;
    private static IngredientDatabase ingredDb;
    private static List<Ingredient> ingredientList;
    private static Scanner input;
    private static int choose;

    public NutriProfile() {

        ingredDb = new IngredientDatabase();
        ingredientList = ingredDb.getIngredientDb();

        input = new Scanner(System.in);

        runNutriProfile();
    }

    private void runNutriProfile() {
        int choose;
        boolean isAppRunning = true;

        while (isAppRunning) {
            displayMainMenu();
            choose = input.nextInt();


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


    public static void searchIngredient() {
//        String category = ingredient.getCategory();
//        String name = ingredient.getName();
//        String reason = ingredient.getReason();
//        boolean isIngredientFound = true; // check if ingredient exists

        if (ingredDb.isIngredientDbEmpty()) {
            System.out.println("Your list is empty. Please add it.");
        } else {
            System.out.println("Which ingredient are you looking for?: ");
            String chosenName = input.nextLine();

            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getName().equalsIgnoreCase(chosenName)) {
                    // Name exists, so return its corresponding values
                    System.out.println("Category: " + ingredient.getCategory()
                            + "Ingredient Name: " + ingredient.getName()
                            + ", Reason: " + ingredient.getReason());
//                    isIngredientFound = true;

                } else {
                    System.out.println("It doesn't exist. Please add it.");

                }
            }
        }
    }




//        if (singleIngredient.doesIngredientExist()) {
//            System.out.println("It doesn't exist.");
//        } else {
//            System.out.println("found!");
//
//        }
//        System.out.println("Category: " + category);
//        System.out.println("Ingredient Name: " + name);
//        System.out.println("Reason: " + reason);

    // Check if the name exists in the inputList



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
                    ingredIndex++;
                    System.out.println("#" + ingredIndex + "\tName" + "\tReason");
                    System.out.println(ingredient.getName() + "\t\t" + ingredient.getReason());
                }
            }

        }

    }

//        System.out.println("You chose to view a list");
//        System.out.println("\t\t==============================");
//        System.out.println("\t\t                              ");
//        System.out.println("\t\t           Category           ");
//        System.out.println("\t\t           1. GOOD            ");
//        System.out.println("\t\t           2. BAD             ");
//        System.out.println("\t\t                              ");
//        System.out.println("\t\t==============================");
//        System.out.println("Enter the number of a category");
//        choose = input.nextInt();
//        //리스트 불러오는거 추가!!!
//
//    }

    public static void chooseSubOption() {
        System.out.println("\t\t==============================");
        System.out.println("\t\t                              ");
        System.out.println("\t\t      1. Return to main       ");
        System.out.println("\t\t      0. Exit                 ");
        System.out.println("\t\t                              ");
        System.out.println("\t\t==============================");
        System.out.println("Enter the number of an option: ");
        choose = input.nextInt();

        if (choose == 1) {
            displayMainMenu();
        } else if (choose == 0) {
            System.out.println("See you next time!");
            System.exit(0); // exit the application
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void addIngredients() {
        System.out.println("You chose to add an ingredient");

        System.out.println("Enter category name (GOOD or BAD): ");
        input.nextLine();
        String category = input.nextLine();

        System.out.println("Enter ingredient name: ");
        String name = input.nextLine();

        System.out.println("Enter reason (e.g. allergy causing): ");
        String reason = input.nextLine();

        Ingredient ingredient = new Ingredient(category, name, reason);

        ingredDb.addToDb(ingredient); //!!!
        System.out.println(ingredient.getName() + " is successfully added!");


    }


    public static Ingredient chooseIngredient(List<Ingredient> ingredientCollection) {
        for (Ingredient ingredient: ingredientCollection) {
            System.out.println("For " + ingredient.getName() + " press " + ingredientCollection.indexOf(ingredient));
        }
        int index = input.nextInt();
        return ingredientCollection.get(index);
    }

    public static void deleteIngredients() {
        System.out.println("Enter ingredient number to delete");
        Ingredient ingredient = chooseIngredient(ingredientList);
        ingredDb.deleteIngredientFromDb(ingredient);
        System.out.print(ingredient.getName() + " is successfully deleted!");
    }

}