package Controller;

import javafx.beans.property.SimpleStringProperty;

public class Ingredient {

    private SimpleStringProperty name;
    private SimpleStringProperty quantity;

    public Ingredient(SimpleStringProperty name, SimpleStringProperty quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }
}
