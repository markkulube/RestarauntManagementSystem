package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * The total cost of all delivered items
 * at a table.
 */
public class Bill implements Cloneable {
    
    //Order from table
    private Order order;
    
    private int tableID;

    private SimpleStringProperty dish;

    private SimpleStringProperty price;

    private SimpleStringProperty status;

    private SimpleStringProperty billNum;

    private SimpleStringProperty orderNum;

    private SimpleStringProperty total;

    private SimpleStringProperty date;
    
    public Bill(Order order){
        this.order = order;
        this.status =  new SimpleStringProperty("Pending");
        this.orderNum = new SimpleStringProperty(this.order.getOrderNum() + "");
        this.billNum = new SimpleStringProperty(this.order.getOrderNum() + "");
        this.total = new SimpleStringProperty(calculateBill() + "");
        this.date = new SimpleStringProperty("");
    }

    protected Object clone() throws CloneNotSupportedException {
        Bill cloneBill = new Bill(order);
        return cloneBill;
    }

    public String getOrderNum() {
        return orderNum.get();
    }

    public SimpleStringProperty orderNumProperty() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum.set(orderNum);
    }

    public String getStatus() {
        return status.get();
    }

    public String getBillNum() {
        return billNum.get();
    }

    public SimpleStringProperty billNumProperty() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum.set(billNum);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDish() {
        return dish.get();
    }

    public SimpleStringProperty dishProperty() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish.set(dish);
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

    //Calculates the total price of the order and returns it
    public double calculateBill(){
        double sum = 0;
        // Only accumulate price for dishes that have been confirmed by customer
        for (int i = 0; i < order.getConfirmedOrder().size(); i++) {
                Dish tempDish = (Dish) order.getConfirmedOrder().get(i);
                sum += Double.parseDouble(tempDish.getPrice());
        }
        
        return sum;
    }

    //Calculates the total price of a customer order and returns it
    public double calculateCustBill(ObservableList customerOrder){
        double sum = 0;
        // Only accumulate price for dishes that have been confirmed by customer
        for (int i = 0; i < customerOrder.size(); i++) {
            Dish tempDish = (Dish) order.getConfirmedOrder().get(i);
            sum += Double.parseDouble(tempDish.getPrice());
        }
        return sum;
    }
    
    //Makes bill into a human readable bill
    public String toString(){
        String bill = "Bill for Table #" + tableID + System.lineSeparator();
        
        // Add each dish and its associated price to the bill
        for (int i = 0; i < order.getConfirmedOrder().size(); i++) {
            for (int j = 0; j < ((ArrayList) (order.getConfirmedOrder().get(i))).size(); j++) {
                Dish tempDish = (Dish)((ArrayList) (order.getConfirmedOrder().get(i))).get(j);
                bill += (tempDish.toString() + " $" + tempDish.getPrice() + System.lineSeparator());
            }
        }
        
        bill += "Total Price: $" + calculateBill();
        return bill;
    }
}

