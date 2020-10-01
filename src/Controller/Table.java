package Controller;

import java.util.ArrayList;

/**
 * A Table has a number, a set number of seats (capacity),
 * an order and customers.
 */
public class Table {
    
    // capacity of each table
    private int tableCap;
    
    //table number
    private int tableID;
    
    //customers at table
    private ArrayList<Customer> customerAtTabl;
    
    // Table order
    private Order tableOrder;
    
    // Accepted orders at Table
    private ArrayList<Dish> tableConfirmedOrder;
    
    //Table bill
    private Bill bill;

    private boolean tableOccupied;
    
    public Table(int cap, int iD) {
        this.tableCap = cap;
        this.tableID = iD;
        this.customerAtTabl = new ArrayList<>();
        this.tableOrder = new Order();
        this.tableOccupied = false;
        tableConfirmedOrder = new ArrayList();
        this.bill = new Bill(tableOrder);
    }

    public Table() {

    }

    public int getTableCap() {
        return tableCap;
    }

    public boolean isTableOccupied() {
        return tableOccupied;
    }

    public void setTableOccupied(boolean tableOccupied) {
        this.tableOccupied = tableOccupied;
    }

    // add customer to table so long it has seating
    public void addCustomer(Customer customer) {
        customerAtTabl.add(customer);
    }
    
    // Return tableID
    public int getTableID() {
        return tableID;
    }
    
    //
    public ArrayList<Customer> getCustomerAtTabl() {
        return customerAtTabl;
    }

    
    public Order getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(Order tableOrder) {
        this.tableOrder = tableOrder;
    }

    public Bill getBill() {
        return bill;
    }

    public void updateConfirmedOrder(Dish dish, String custID) {
        tableConfirmedOrder.add(dish);
    }
}