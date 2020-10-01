package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * A Restaurant has at most one TableSet, Menu and Kitchen and at
 * least one Manager, Server, Cook.
 */

/*
1. Added cook ID argument
 */

public class Restaurant {
    
    public static TableSet tableSet;

    public static Table selectedTable;

    public static Menu menu;

    public static Kitchen kitchen;

    public static EmployeeGrps employeeGrps;

    public static Cook cook;

    public static Server server;

    public static Order order;

    public static Employee user;

    public static ObservableList<Stage> observableStageList;

    public static ObservableList<Bill> paidBills;
    
    private BufferedReader inputFile;


    
    public static void startRestaraunt () throws InterruptedException, IOException {

        menu = new Menu();

        Map<String, Integer> ingredients = new HashMap<>();
        ingredients.put("Bread", 5);
        ingredients.put("Lettuce", 10);
        ingredients.put("Cheese", 10);
        ingredients.put("Tomato", 10);
        ingredients.put("Sausage", 10);
        ingredients.put("Beef", 10);
        ingredients.put("Potato", 10);
        ingredients.put("Pop Can", 10);
        ingredients.put("Cream Scoop", 10);
        ingredients.put("Cone", 10);
        ingredients.put("Egg", 10);

        kitchen = new Kitchen(ingredients);
        tableSet = new TableSet();
        employeeGrps = new EmployeeGrps();
        employeeGrps.createGrp(4, tableSet, kitchen);

        paidBills = FXCollections.observableArrayList();

        user = new Employee();

        server = new Server();

        cook = new Cook();

        observableStageList = FXCollections.observableArrayList();

        selectedTable = new Table();

        order = selectedTable.getTableOrder();
        }


    public static Menu getMenu() {
        return menu;
    }

    public static void setUser(Employee user) {
        Restaurant.user = user;
    }

    public static Table getSelectedTable() {
        return selectedTable;
    }

    public static void setSelectedTable(Table selectedTable) {
        Restaurant.selectedTable = selectedTable;
    }

    public static TableSet getTableSet() {
        return tableSet;
    }

    public static Order getOrder() {
        return order;
    }
}



