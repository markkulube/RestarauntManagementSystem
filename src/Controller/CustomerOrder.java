package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A CustomerOrder is the Dishes selected by each
 * customer at a table for their meal.
 */

/*
1. New method createDish for each individual dish on addDish in CreateOrderView
 */


public class CustomerOrder {
    
    private ObservableList<Dish> custOrder;
    private String custOrderID;
    private ObservableList<Dish> dishes;
    private ObservableList<Dish> confirmedDishes;
    private Dish dishGUI;
    
    public CustomerOrder() {
        //super(custOrderID);
        this.custOrder = FXCollections.observableArrayList();
        this.custOrderID = custOrderID;
        this.dishes = FXCollections.observableArrayList();
        this.confirmedDishes = FXCollections.observableArrayList();
    }


    public boolean makeDish(String dishOrder, String custID) {
        boolean inStock=false;
        custOrderID = custID;
        String[] orderStage4 = dishOrder.split("#");
        String[] orderStage5 = orderStage4[1].split("%");
        Map<String, Integer> ingredAddSub = new HashMap<>();
        for (int k = 0; k < orderStage5.length; k++) {
            String[] orderStage6 = orderStage5[k].split(":");
            String ingredAddSubName = orderStage6[0].trim();
            if (!ingredAddSubName.equals("None")) {
                Integer ingredAddSubChange = Integer.parseInt(orderStage6[1].trim());
                if(!ingredAddSub.keySet().contains(ingredAddSubName)) {
                    ingredAddSub.put(ingredAddSubName, ingredAddSubChange);
                }else {
                    int newIngredQty = ingredAddSub.get(ingredAddSubName) + ingredAddSubChange;
                    ingredAddSub.remove(ingredAddSubName);
                    ingredAddSub.put(ingredAddSubName, newIngredQty);
                }
            }
        }

        for (int l = 0; l < Restaurant.menu.getMenu().size(); l++) {

            if (((Dish) ((Restaurant.menu.getMenu()).get(l))).getName().equals(orderStage4[0].trim())) {
                Dish tempDish = (Dish) (Restaurant.menu.getMenu()).get(l);
                Dish tempDish2 = new Dish(tempDish.getName(), tempDish.getPrice(), tempDish.getIngredients());

                for (String key : ingredAddSub.keySet()) {
                    int newIngredQty = ingredAddSub.get(key) + tempDish2.getIngredients().get(key);
                    if (newIngredQty < 0) {
                        tempDish2.getIngredients().put(key, 0);
                    } else {
                        tempDish2.getIngredients().put(key, newIngredQty);
                        if (ingredAddSub.get(key) > 0) {
                            int ingredSubChange = ingredAddSub.get(key);
                            double newPrice = (ingredSubChange * 0.5) +  Double.parseDouble( tempDish2.getPrice());
                            tempDish2.setPrice(String.valueOf(newPrice));
                        }
                    }
                }
                tempDish.setIngredSubAdd(ingredAddSub);

                inStock = Restaurant.kitchen.checkDishStock(tempDish2);
                    if(inStock) {
                        custOrder.add(tempDish2);

                        String dishNameGUI = tempDish2.getName();
                        String dishPriceGUI = tempDish2.getPrice() + "";
                        String request1 = orderStage5[0].trim();
                        String request2 = orderStage5[1].trim();
                        Map<String, Integer> dishIngred = tempDish2.getIngredients();
                        dishGUI = new Dish(dishNameGUI, dishPriceGUI, dishIngred);
                        dishGUI.setCustID(custID);
                        dishGUI.setRequest1(request1);
                        dishGUI.setRequest2(request2);


                        dishes.add(dishGUI);
                    }
            }
        }
        return inStock;
    }

    public String getCustOrderID() {
        return custOrderID;
    }
    
    public ObservableList<Dish> getCustOrder() {
        return custOrder;
    }

    public ObservableList<Dish> getDishes() {
        return dishes;
    }

    public ObservableList<Dish> getConfirmedDishes() {
        return confirmedDishes;
    }

    @Override
    public String toString() {
        String myGrub = "";
        if(custOrder.size() == 0) {
            myGrub += "NO Order!!! " + System.lineSeparator();
        }
        for (int i = 0; i < custOrder.size(); i++) {
            myGrub +=  custOrder.get(i).toString() + System.lineSeparator();
        }
        return "Customer: " + getCustOrderID() + System.lineSeparator() + myGrub + System.lineSeparator();
    }
}