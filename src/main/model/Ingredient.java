package model;

import java.util.Objects;

// Represents an ingredient with its category, name, and reason.
public class Ingredient {
    private String category;
    private String name;
    private String reason;

    public Ingredient(String category, String name, String reason) {

        this.category = category;
        this.name = name;
        this.reason = reason;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}