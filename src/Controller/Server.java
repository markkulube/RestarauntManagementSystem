package Controller;

import java.util.ArrayList;

/**
 * A server takes a table order, menu items as they are ordered
 * and include the table number and any additions and subtractions
 * from each menu item.
 */

public class Server extends  Employee {

    // List of the tables for which the server is responsible
    private ArrayList<Table> myTable;
    
    // Creates a server with the given name and assigned tables
    public Server(int iD, Kitchen kitchen) {
        super(iD, kitchen);
        this.myTable = new ArrayList<>();
    }

    public Server() {
    }
    
    public ArrayList<Table> getMyTable() {
        return myTable;
    }
    
    public void takeOrder(String custOrderText) {
        String[] ServStage1 = custOrderText.split("\\|");
        
        int tableID = Integer.parseInt((ServStage1[3]).trim());
        
        for (int i = 0; i < myTable.size(); i++) {
            Table tempTable = myTable.get(i);
            if((tempTable.getTableID() == tableID)) {
                for (int j = 0; j <((tempTable.getCustomerAtTabl().size())); j++) {
                    
                    Customer tempCustomer = ((Customer) myTable.get(i).getCustomerAtTabl().get(j));
                    
                    tempCustomer.createOrder(custOrderText);
                    
                    tempTable.getTableOrder().addItem(tempCustomer.getMyOrder());
                    
                }
                
                kitchen.addOrder(tempTable.getTableOrder());
                
            }
            
        }
        
    }
    
    public void addTable(Table table) {
        getMyTable().add(table);
    }
    
    
    // String representation of just the number of tables given to the server (subject to change)
    public String toString() {
        return "Server " + this.getEmployeeID() + " handles " + getMyTable().size() + " tables.";
    }
    
    public void deliverOrder(String deliverText) {
        String[] ServStage1 = deliverText.split("\\|");
        
        int tableID = Integer.parseInt((ServStage1[3]).trim());
        
        for (int i = 0; i < myTable.size(); i++) {
            Table tempTable = myTable.get(i);
            if((tempTable.getTableID() == tableID)) {
                for (int j = 0; j <((tempTable.getCustomerAtTabl().size())); j++) {
                    
                    Customer tempCustomer = ((Customer) myTable.get(i).getCustomerAtTabl().get(j));
                    
                    tempCustomer.acceptMyOrder(deliverText);
                    
                    if(tempCustomer.getMyOrder().getCustOrder().size() == 0) {
                        tempTable.getTableOrder().getTableOrder().remove(tempCustomer.getMyOrder());
                    }
                    
                    if(tempCustomer.getCustConfirmedOrder().size() != 0) {
                        tempTable.getTableOrder().getConfirmedOrder().remove(tempCustomer.getCustConfirmedOrder());
                        tempTable.getTableOrder().getConfirmedOrder().add(tempCustomer.getCustConfirmedOrder());
                        
                    }
                }
                if(tempTable.getTableOrder().getTableOrder().size() != 0) {
                    System.out.println("Order #" + tempTable.getTableOrder().getOrderNum() + " - REDO returned Dishes.");
                    System.out.println(tempTable.getTableOrder());
                } else {
                    System.out.println("Order #" + tempTable.getTableOrder().getOrderNum() + " - COMPLETE.");
                }
                
                
            }
            if(tempTable.getTableOrder().getTableOrder().size() != 0) {
                for (int j = 0; j < kitchen.getOrders().size() ; j++) {
                    
                    if (kitchen.getOrders().get(j).equals(tempTable.getTableOrder().getOrderNum())) {
                        kitchen.getOrders().remove(tempTable.getTableOrder());
                    }
                }
            }
        }
    }
    
    public  void printBill(String billText) {
        String[] ServStage1 = billText.split("\\|");
        
        int tableID = Integer.parseInt((ServStage1[3]).trim());
        
        
        for (int i = 0; i < getMyTable().size(); i++) {
            Table tempTable = myTable.get(i);
            if ((tempTable.getTableID() == tableID)) {
                System.out.println(tempTable.getBill());
            }
        }
    }

    public void addOrder(Order order) {
        myOrders.add(order);
    }

}
