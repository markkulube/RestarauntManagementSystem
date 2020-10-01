package Controller;

import java.util.ArrayList;

/**
 * A Cook confirms receipt of the order in the kitchen
 * and confirms when the order is ready for delivery by the server
 */

/*
1. Removed static variable numCooks. And replaced with a intstance variable int cookID
2. Created an ArrayList for each cook to store orders they are preparing
 */

public class Cook extends Employee {

  public Cook(int iD, Kitchen kitchen) {
   super(iD, kitchen);
  }

  public Cook() {
  }

  
  // Confirm that an Order has been received in the kitchen and seen by a cook
  public void kitchenStatus(String statusText) {
    // Look at each section of the status separated by | delimiter
    String[] statusStage1 = statusText.split("\\|");
    // Declare orderNum if the order was received
    if(("Received").equals(statusStage1[5].trim())) {
      String[] statusStage2 = statusStage1[4].split(",");
      int orderNum = Integer.parseInt(statusStage2[1].trim());
      
      // Report that an order was seen by the cook for appropriate order numbers
      for (int i = 0; i < Restaurant.kitchen.getOrders().size(); i++) {
        if(orderNum == Restaurant.kitchen.getOrders().get(i).getOrderNum()) {
          //Restaurant.kitchen.getOrders().get(i).setOrderWithCook(true);
          System.out.println("Kitchen Status of Order #" + Restaurant.kitchen.getOrders().get(i).getOrderNum() + ": ");
          System.out.println("Order #" + Restaurant.kitchen.getOrders().get(i).getOrderNum()
              + " " + "SEEN by Cook" + this.getEmployeeID() + System.lineSeparator());
        }
      }
    }
  }
  
  /* Modify order status of an Order to ready */


  public ArrayList<Order> getMyOrders() {
    return myOrders;
  }

  public void addOrder(Order order) {
    myOrders.add(order);
  }
}
