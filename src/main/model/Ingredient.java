package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an ingredient with its category, name, and reason.
public class Ingredient implements Writable {
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

    // EFFECTS: returns each element in ingredient as string
    public String toString() {
        return "category: " + category + "name: " + name + "reason: " + reason;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonOb = new JSONObject();
        jsonOb.put("category", category);
        jsonOb.put("name", name);
        jsonOb.put("reason", reason);
        return jsonOb;
    }
}