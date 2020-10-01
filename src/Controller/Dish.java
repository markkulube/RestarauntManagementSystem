package Controller;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * A Dish appears on the menu of the restaurant
 * at a certain Price. Every Dish has a default
 * set of ingredients which can be modified at the
 * request of a customer
 */
public class Dish {


    private String strName;
    private double dobPrice;

    private SimpleStringProperty custID;
    private SimpleStringProperty name;
    private SimpleStringProperty price;
    private SimpleStringProperty request1;
    private SimpleStringProperty request2;
    private SimpleStringProperty accrej;
    private SimpleStringProperty redo;

    // Price of the dish
    //private double price;

    // The type of ingredients and their quantities associated with the dish
    private Map<String, Integer> ingredients;

    //Adjustements to the ingredients of a particular dish instance
    private Map<String, Integer> ingredSubAdd;

    // Whether or not Dish is has been
    // delivered to customer
    private boolean deliveryStatus;


    // Creates a dish with the specified name, ingredients and customer
    public Dish(String name, String price, Map<String, Integer> ingredients) {
        this.custID = new SimpleStringProperty();
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.accrej = new SimpleStringProperty("");
        this.redo = new SimpleStringProperty("");

        this.ingredSubAdd = new HashMap<>();
        this.request1 = new SimpleStringProperty();
        this.request2 = new SimpleStringProperty();

        this.ingredients = ingredients;


    }



    public String getCustID() {
        return custID.get();
    }

    public String getAccrej() {
        return accrej.get();
    }

    public SimpleStringProperty accrejProperty() {
        return accrej;
    }

    public void setAccrej(String accrej) {
        this.accrej.set(accrej);
    }

    public String getRedo() {
        return redo.get();
    }

    public SimpleStringProperty redoProperty() {
        return redo;
    }

    public void setRedo(String redo) {
        this.redo.set(redo);
    }

    public SimpleStringProperty custIDProperty() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID.set(custID);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getRequest1() {
        return request1.get();
    }

    public SimpleStringProperty request1Property() {
        return request1;
    }

    public void setRequest1(String request1) {
        this.request1.set(request1);
    }

    public String getRequest2() {
        return request2.get();
    }

    public SimpleStringProperty request2Property() {
        return request2;
    }

    public void setRequest2(String request2) {
        this.request2.set(request2);
    }

    public Map<String, Integer> getIngredients() {
        return this.ingredients;
    }

    public void setDeliveryStatus(boolean setDeliveryStatus) {
        this.deliveryStatus = setDeliveryStatus;
    }

    public void setIngredSubAdd(Map<String, Integer> ingredSubAdd) {
        this.ingredSubAdd = ingredSubAdd;
    }

    // String representation of the dish's name (subject to change)
    public String toString() {
        String extra = "";
        if (ingredSubAdd.size() != 0) {
            for (String key: ingredSubAdd.keySet()) {
                extra += "(" + key + ": " + ingredSubAdd.get(key) + ")" + " ";
            }
        } else {
            extra = "None";
        }
        return "Name of dish: " + this.name + " Extras: " + extra.trim(); //+ " Price: $" + this.price;
    }
}