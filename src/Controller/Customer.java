package Controller;

import java.util.ArrayList;

/**
 *  A Customer has an Order listing all menu items
 *  the customer has ordered. Every customer receives
 *  an iD once seated at a table.
 */
public class Customer  {
    
    private String custID;
    private CustomerOrder myOrder;
    //private int tableID;
    private ArrayList<Dish> custConfirmedOrder;
    
    
    // @ TODO put back tableID if we need.
    public Customer (String custID) {
        this.custID = custID;
        //this.tableID = tableID;
        this.myOrder = new CustomerOrder();
        this.custConfirmedOrder = new ArrayList<>();
    }
    
    // @ TODO put back paramater custID if needed
    public Customer() {
    
    }
    
    
    // Create customer order once server
    // has provided input.
    public void createOrder(String custOrderText) {
        //this.myOrder = new CustomerOrder(custOrderText, custID);
    }

    public boolean createDish(String dishOrder, String custID) {
        return myOrder.makeDish(dishOrder, custID);
    }
    
    public void acceptMyOrder(String deliverText) {
        String[] orderStage1 = deliverText.split("\\|");
        
        for (int i = 4; i < orderStage1.length; i++) {
            String[] orderStage2 = orderStage1[i].split("\\*");
            
            String[] orderStage3 = orderStage2[1].split(",");
            
            
            for (int k = 0; k < orderStage3.length ; k++) {
                String[] orderStage4 = orderStage3[k].split("-");
                
                for (int l = 0; l < getMyOrder().getCustOrder().size(); l++) {
                    if( (getMyOrder().getCustOrder().get(l).getName().equals(orderStage4[0].trim())
                        && "ACC".equals(orderStage4[1].trim()))){
                        getMyOrder().getCustOrder().get(l).setDeliveryStatus(true);
                        getCustConfirmedOrder().add(getMyOrder().getCustOrder().get(l));
                        getMyOrder().getCustOrder().remove(l);
                        
                    }
                }
            }
        }
    }
    
    public String getcustID() {
        return custID;
    }
    
    public CustomerOrder getMyOrder() {
        return myOrder;
    }
    
    public ArrayList<Dish> getCustConfirmedOrder() {
        return custConfirmedOrder;
    }
    
    @Override
    public String toString() {
        return  this.getcustID();
    }
}
