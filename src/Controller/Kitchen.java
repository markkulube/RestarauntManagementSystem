package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * A Kitchen has a Cook, an OrderQueue
 * and inventory of Ingredients
 */

public class Kitchen {
  //Initializes the list of orders and inventory in the kitchen
  // I am using linked lists as a "pseudo-queue", but I can implement my own queue if necessary.
  
  private ObservableList<Order> orders = FXCollections.observableArrayList();
  //private LinkedList<Cook> cooks = new LinkedList<>();
  ObservableList<Order> completeOrders;
  private Map<String, Integer> inventory;
  private int ordNum;
  
  //List of Strings of ingredients already noted.
  private ArrayList<String> notedIngredients = new ArrayList<>();
  
  public Kitchen(Map<String, Integer> list) {
    //Adds all the stock inside the inventory
    this.inventory = list;
    this.ordNum = 0;
    this.completeOrders = FXCollections.observableArrayList();
  }

  public void addOrder(Order order) {
    if (order.getOrderNum() == 0) {
      ordNum++;
      order.setOrderNum(ordNum);
      orders.add(order);
    }
  }

  
  /**
   * Loops over all dishes in the order and subtracts current inventory from the order.
   *
   * @param order Order that I will be reducing stock from
   */
  public void subtractStock(Dish d) {
   /* //Inside every Customer order
    for (CustomerOrder cust: order.getTableOrder()) {
      // Inside every dish in Customer order
      for (Dish d: cust.getCustOrder()) {*/
        Map<String,Integer> ingredients = d.getIngredients();
        // Inside every key in each dish ingredients
        for (String ingredientName : ingredients.keySet()) {
          //Subtracts inventory's stock from the dish's ingredient requriement.
          int newStock = inventory.get(ingredientName) - ingredients.get(ingredientName);
          inventory.put(ingredientName, newStock);
          
          //Check if the new stock is under threshold;
          if (!notedIngredients.contains(ingredientName)) {
            checkThreshold(newStock, ingredientName);
            notedIngredients.add(ingredientName);
          }
        }
      //}
    //}
  }
  
  /**File
   * Checks whether or not the stock of an ingredient is under the threshold
   * If it is under the threshold, it will write to order.txt
   * @param x The current stock of the ingredient
   */
  private void checkThreshold(int x, String name) {
    if (x <= 10) {
      File orderDoc = new File("request");
      if (!orderDoc.exists()) {
        
        try {
          orderDoc.createNewFile();
        } catch (IOException e) {
          //Can handle it later
          System.out.println("Could not create request");
        }
        
      }
      try {
        FileWriter writer = new FileWriter(orderDoc, true);
        writer.write(name + ": 20" + System.lineSeparator());
        writer.close();
      } catch (IOException e2) {
        System.out.println("Could not write in request.txt");
      }
    }
  }

  public boolean checkDishStock(Dish dish) {
    for (String key: dish.getIngredients().keySet()) {
      if(inventory.get(key) - dish.getIngredients().get(key) < 0) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Empties the kitchen notedIngredients because shipment has been restocked.
   */
  public void shipmentReceived() {
    notedIngredients.clear();
  }

  public int getOrdNum() {
    ordNum++;
    return ordNum;
  }

  public ObservableList<Order> getCompleteOrders() {
    return completeOrders;
  }

  public Map<String, Integer> getInventory() {
    return inventory;
  }
  
  public ObservableList<Order> getOrders() {
    return orders;
  }


  public void receiveShipment(String shipment){
    String[] shipmentStage1 = shipment.split(",");
    for (String s: shipmentStage1) {
      String[] Ingredients = s.split(":");
      String IngredientName = Ingredients[0].trim();
      String IngredientQuantity = Ingredients[1].trim();
      Integer CurrentStock = getInventory().get(IngredientName);
      getInventory().replace(IngredientName, Integer.parseInt(IngredientQuantity) + CurrentStock);
    }
  }
}