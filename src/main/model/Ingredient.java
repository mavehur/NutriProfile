package model;

import java.util.Objects;

public class Ingredient {
    private String category;
    private String name;
    private String reason;

    public Ingredient(String category, String name, String reason) {
        if (category.isEmpty() || name.isEmpty() || reason.isEmpty()) {
            System.out.println("Please fill in all.");
        } else {
            this.category = category;
            this.name = name;
            this.reason = reason;
        }

    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public String getReason() {
        return this.reason;
    }

    public Boolean doesIngredientNotExist() {
        return name.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient ingredient = (Ingredient) o;
        return Objects.equals(category, ingredient.category)
                && Objects.equals(name, ingredient.name)
                && Objects.equals(reason, ingredient.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, reason);
    }

}