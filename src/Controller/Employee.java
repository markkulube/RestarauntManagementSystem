package Controller;
//import View.*;

import java.util.ArrayList;

/**
 * The employee class. Every an employee has ID and can receiving new shipment of stock.
 * Some employees may need access to the kitchen.
 */

public class Employee {

    protected ArrayList<Order> myOrders;
    protected ArrayList<Order> preparedReadyOrders;

    //ID
    protected int employeeID;

    // the kitchen
    protected Kitchen kitchen;

    protected  TableSet tableSet;

    protected String workerType;

    public Employee (int ID) {
        this.employeeID = ID;
    }

    public Employee (int ID, TableSet tableSet) {
        this.employeeID = ID;
        this.tableSet = tableSet;
        this.myOrders = new ArrayList<>();
        this.preparedReadyOrders = new ArrayList<>();
    }

    public Employee (int ID, Kitchen kitchen, TableSet tableSet) {
        this.employeeID = ID;
        this.kitchen = kitchen;
        this.myOrders = new ArrayList<>();
        this.preparedReadyOrders = new ArrayList<>();

    }

    public Employee(int ID, Kitchen kitchen)  {
        this.employeeID = ID;
        this.kitchen = kitchen;
        this.workerType = "";
        this.myOrders = new ArrayList<>();
        this.preparedReadyOrders = new ArrayList<>();
    }

    public Employee() {
    }

    public ArrayList<Order> getPreparedReadyOrders() {
        return preparedReadyOrders;
    }

    public void addOrder(Order order) {
        myOrders.add(order);
        }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public ArrayList<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(ArrayList<Order> myOrders) {
        this.myOrders = myOrders;
    }
}
