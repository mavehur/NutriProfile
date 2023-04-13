# NutriProfile

### 🚀UBC 22-23W2 CPSC 210 Personal Project

No matter how healthy some ingredients are known to be, if they don't fit your constitution, should it be still 
considered healthy? The ideal diet varies depending on an individual's specific needs, goals, and dietary restrictions.

**NutriProfile** is designed to help individuals achieve optimal health. NutriProfile is suitable for anyone looking to 
improve their overall health and well-being through better nutrition.

This can include people looking to:

- Lose weight
- Increase energy levels
- Reduce the risk of chronic diseases
- Address specific health concerns such as food allergies or intolerances
- Eat a more balanced and nutritious diet

⚠️ It is important to note that if you have any health concerns, it's always best to consult with a healthcare 
professional first.

### ✅︎ What functions does NutriProfile have?
You can add ingredients in the category (GOOD or BAD) with a reason why they are good or bad for you, 
, and view/delete/modify ingredients in the list under its corresponding category. 

### 💡 How did NutriProfile start the journey?

Grapefruit used to be one of my favorite fruits. However, I recently heard from my doctor that grapefruit is not 
suitable for my constitution and can worsen my health!! For me, who have avoided foods only known to be unhealthy, 
this made me feel the need to study more about myself and find optimized ingredients. So, I created this program 
to organize and classify them.

### 📒 User Stories

- As a user, I want to be able to select a category and add an ingredient to it.
- As a user, I want to be able to select a category and view the list of ingredients with their corresponding reasons in it.
- As a user, I want to be able to search an ingredient and view its category, name and reason.
- As a user, I want to be able to remove an ingredient from category.
- As a user, I want to be able to save my ingredients list to file if I choose to.
- As a user, I want to be able to load my ingredients list from file if I choose to.
- As a user, I want to be able to quit the program. 

### 💯️ Instructions for Grader
- You can add ingredients to a list by clicking "Add Ingredient."
- You can view a list of ingredients by clicking "View My List."
- You can locate my visual components after saving and loading a file by clicking "Save My List" and "Load My List."
- You can save the state of my application by clicking "Save My List."
- You can reload the state of my application by clicking "Load My List."
- You can clear the whole list of ingredients by clicking "Delete My List."
- You can quit the program by clicking "quit."

### Phase 4: Task 2
Samples of printed events when quitting the Application:

- When adding an ingredient to the IngredientDatabase:                                 
  Wed Apr 12 16:50:08 PDT 2023
  Ingredient has been added to the database
  Category: Good
  Name: Coffee
  Reason: Energy Booster!

- When adding two ingredients to the IngredientDatabase and then clearing the whole database:
  Wed Apr 12 16:50:35 PDT 2023
  Ingredient has been added to the database
  Category: Good
  Name: Coffee
  Reason: Energy Booster!
  Wed Apr 12 16:51:05 PDT 2023
  Ingredient has been added to the database
  Category: Bad
  Name: Candy
  Reason: Too much sugar :(
  Wed Apr 12 16:51:14 PDT 2023
  All ingredients have been cleared from the database

### Phase 4: Task 3
Q. How can this app be improved by refactoring? 
- To improve code organization and maintenance, I would move the event listeners for each button into distinct classes 
that implement the ActionListener interface. 
- Instead of creating separate methods for each button panel, it would be more efficient to create one method that 
receives an array of button labels and generates a JPanel with buttons that have the specified labels. Then this method 
would be able to receive an ActionListener for each button as an argument. 