package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Order {
    
    
    // List of all Customer Order at the table
    private ArrayList<CustomerOrder> tableOrder;

    private String cookID;
    private String serverID;

    private ObservableList<Dish> dishes;
    
    // List of accepted dishes by
    // each customer

    private ArrayList<Dish> confirmedOrder;

    private String strStatus, tableID;

    private OrderStatus statusENUM;
    
    // Order number
    private int orderNum;

    // Strings for Observable List
    private SimpleStringProperty ordNum;
    private SimpleStringProperty tableNum;
    private SimpleStringProperty serverNum;
    private SimpleStringProperty cookNum;
    private SimpleStringProperty status;
    private SimpleStringProperty remake;


    // Constructor
    public Order() {
        this.tableOrder = new ArrayList<>();
        this.confirmedOrder = new ArrayList<Dish>();
        this.orderNum = 0;
        this.dishes = FXCollections.observableArrayList();
        this.cookID = "";
        this.serverID = "";
        this.tableID = "";
        this.strStatus = "";
        this.statusENUM = null;

        this.ordNum = new SimpleStringProperty(orderNum + "");
        this.tableNum = new SimpleStringProperty(tableID);
        this.serverNum =  new SimpleStringProperty(serverID);
        this.cookNum = new SimpleStringProperty("TBD");
        this.status = new SimpleStringProperty();
        this.remake =  new SimpleStringProperty("TBD");
    }

    public OrderStatus getStatusENUM() {
        return statusENUM;
    }

    public void updateCustOrder(Dish dish, String custID) {
        for (int j = 0; j < tableOrder.size(); j++) {
            CustomerOrder tempCustOrder = tableOrder.get(j);
            if(tableOrder.get(j).getCustOrderID().equals(custID)) {
                tempCustOrder.getCustOrder().remove(dish);
                tempCustOrder.getDishes().remove(dish);
                tempCustOrder.getConfirmedDishes().add(dish);
                confirmedOrder.add(dish);
            }
        }
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStatusEnum(OrderStatus orderStatus){
        statusENUM = orderStatus;
        switch(orderStatus){
            case SUBMITTED:
                strStatus = "Submitted";
                break;
            case WITHCOOK:
                strStatus = "With Cook";
                break;
            case READY:
                strStatus = "Ready";
                break;
            case REDO:
                strStatus = "Redo";
                break;
            case DELIVERED:
                strStatus = "Delivered";
                break;
            case PAID:
                strStatus = "Paid";
                break;
        }
        status.set(strStatus);
    }

    public String getOrdNum() {
        return ordNum.get();
    }

    public SimpleStringProperty ordNumProperty() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum.set(ordNum);
    }

    public String getTableNum() {
        return tableNum.get();
    }

    public SimpleStringProperty tableNumProperty() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum.set(tableNum);
    }

    public String getServerNum() {
        return serverNum.get();
    }

    public SimpleStringProperty serverNumProperty() {
        return serverNum;
    }

    public void setServerNum(String serverNum) {
        this.serverNum.set(serverNum);
    }

    public String getCookNum() {
        return cookNum.get();
    }

    public SimpleStringProperty cookNumProperty() {
        return cookNum;
    }

    public void setCookNum(String cookNum) {
        this.cookNum.set(cookNum);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getRemake() {
        return remake.get();
    }

    public SimpleStringProperty remakeProperty() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake.set(remake);
    }


    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getServerID() {
        return serverID;
    }

    public String getTableID() {
        return tableID;
    }

    // Add Customer Order to Table Order
    public void addItem(CustomerOrder custOrder) {
        tableOrder.add(custOrder);
    }

    
    public ArrayList<CustomerOrder> getTableOrder() {
        return tableOrder;
    }
    
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
    public int getOrderNum() {
        return orderNum;
    }
    
    public ArrayList getConfirmedOrder() {
        return confirmedOrder;
    }

    public ObservableList<Dish> getDishes() {
        return dishes;
    }
    
    
    @Override
    public String toString() {
        String tableOrderStr ="";
        
        for (int i = 0; i < getTableOrder().size() ; i++) {
            tableOrderStr += getTableOrder().get(i).toString();
        }
        
        tableOrderStr  = "| BEGIN--- " + "Order #" + orderNum + " ---BEGIN |" + System.lineSeparator() +
            tableOrderStr.trim() + System.lineSeparator() + "| END--- " + "Order #" + orderNum + " ---END |"
            + System.lineSeparator();
        
        return tableOrderStr.trim();
    }
}

