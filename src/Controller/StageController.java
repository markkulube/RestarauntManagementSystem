package Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The controller class manages switches between stages, logging out
 * and exiting.
 */

public class StageController {

    @FXML
    protected Stage exitPage, nextStage, prevStage, curStage;

    @FXML
    protected Button back, logOut, exit, menu, printBill, home, myOrders, tables, viewAllOrder, addGuests, submit, viewSelectedOrder, removeDish, setReady;

    @FXML
    protected Button cust1Chair, cust2Chair, cust3Chair, cust4Chair;


    @FXML
    protected Label popMsg;

    @FXML
    protected TableView custTableOrder, allOrderView;

    @FXML
    protected Parent root;

    @FXML
    protected ObservableList<Stage> prevStageList;

    @FXML
    protected Customer curCustomer;

    protected String name;

    protected Table selectedTable;

    protected Order order;

    protected Kitchen kitchen;

    protected Employee user;

    protected TableSet tableSet;

    protected ObservableList paidBills;

    protected Date date;

    public StageController() {
        this.nextStage = new Stage();
        this.prevStage = new Stage();
        this.prevStageList = Restaurant.observableStageList;
        this.selectedTable = Restaurant.selectedTable;
        this.curCustomer = new Customer();
        this.kitchen = Restaurant.kitchen;
        this.user = Restaurant.user;
        this.tableSet = Restaurant.tableSet;
        this.paidBills = Restaurant.paidBills;
        this.date = new Date();
    }

    public void Table(ActionEvent event) throws Exception {
        Button button = (Button) event.getSource();
        Restaurant.selectedTable = Restaurant.getTableSet().getTableWithId(button.getId().trim());
        prevStage = (Stage) button.getScene().getWindow();
        double width, height;
        String title;
        if((!selectedTable.isTableOccupied()) && user instanceof Server) {
            name = "NoCustAlert.fxml";
            width = 250;
            height = 150;
            title = "No Customer Alert";
        }
        else {
            name = "TableAddCust.fxml";
            width = 612;
            height = 412;
            title = "Add Customers";
        }
        nextStage(name, prevStage, width, height, title);
    }


    public void ViewSelectedOrder(ActionEvent event) throws Exception {
        if (!allOrderView.getSelectionModel().getSelectedCells().isEmpty()) {

            Order order = (Order) allOrderView.getSelectionModel().getSelectedItem();
            Restaurant.setSelectedTable(Restaurant.getTableSet().getTableWithId(("table" + order.getTableNum().trim())));
            prevStage = (Stage) viewSelectedOrder.getScene().getWindow();
            name = "TableOrder.fxml";
            nextStage(name, prevStage, 926, 422, "Table Order");
        }


    }
    public void PrintBill(ActionEvent event) throws Exception {
        prevStage = (Stage) printBill.getScene().getWindow();
        name = "Bill.fxml";
        nextStage(name, prevStage, 600, 400, "Order #:" + selectedTable.getTableOrder().getOrderNum() + " Bill");
    }

    public void LogOut (ActionEvent event) throws Exception {
        curStage = (Stage) logOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        curStage.setTitle("Restaraunt Log In");
        curStage.setScene(new Scene(root, 300, 275));
        curStage.setWidth(300);
        curStage.setHeight(300);
        curStage.show();
    }

    public void Menu(ActionEvent event) throws Exception {
        prevStage = (Stage) menu.getScene().getWindow();
        name = "Menu.fxml";
        nextStage(name, prevStage, 612, 412, "Menu Home");
    }

    public void MyOrders(ActionEvent event) throws Exception {
        prevStage = (Stage) myOrders.getScene().getWindow();
        name = "MyOrders.fxml";
        nextStage(name, prevStage, 700, 600, user.getWorkerType() + " Home");

    }

    public void Home(ActionEvent event) throws Exception {
        prevStage = (Stage) home.getScene().getWindow();
        String worker = "";
        if (user instanceof Greeter) {
            name = "Greeter.fxml";
            worker = " Greeter";
        }
        if(user instanceof Server) {
            name = "Server.fxml";
            worker = " Server";
        }
        if(user instanceof Cook) {
            name = "Cook.fxml";
            worker = " Cook";
        }
        if(user instanceof Manager) {
            name = "Manager.fxml";
            worker = " Manager";
        }

        nextStage(name, prevStage, 612, 412, worker + user.getEmployeeID() + worker + " Home");
    }

    public void Exit() {
        exitPage = (Stage) exit.getScene().getWindow();
        exitPage.close();
    }

    public void nextStage(String name, Stage prevStageT, double width, double height, String title) throws Exception {
        prevStageT.close();

        prevStage = prevStageT;
        prevStageList.add(prevStage);

        root = FXMLLoader.load(getClass().getResource(name));
        Scene scene = new Scene(root, width, height);
        nextStage.setScene(scene);
        nextStage.setTitle(title);
        if("NoCustAlertfxml".equals(name)) {
            nextStage.initModality(Modality.WINDOW_MODAL);
        } else {
            nextStage.show();
        }

    }

    public void RemoveDish(ActionEvent event) {
        if(!custTableOrder.getSelectionModel().getSelectedCells().isEmpty()) {
            Object selectedItem = custTableOrder.getSelectionModel().getSelectedItem();
            custTableOrder.getItems().remove(selectedItem);
            curCustomer.getMyOrder().getDishes().remove(selectedItem);
            custTableOrder.refresh();
        }
    }

    public void Tables(ActionEvent event) throws Exception {
        prevStage = (Stage) tables.getScene().getWindow();
        name = "Tables.fxml";
        nextStage(name, prevStage, 616, 284, "Server  Tables");
        //addPrevStage(prevStage);
    }

    public void Back(ActionEvent event) {
        curStage = (Stage) back.getScene().getWindow();
        //prevStageList.add(curStage);
        prevStage = prevStageList.get((prevStageList.size()) -1);
        curStage.close();
        prevStage.show();
        prevStageList.remove((prevStageList.size()) -1);
    }

    public void ViewAllOrder(ActionEvent event) throws Exception {
        prevStage = (Stage) viewAllOrder.getScene().getWindow();
        name = "AllOrders.fxml";
        nextStage(name, prevStage, 700, 600, user.getWorkerType() + " Home");
    }

    public void AddGuests() throws Exception {
        prevStage = (Stage) addGuests.getScene().getWindow();
        name = "AllTables.fxml";
        nextStage(name, prevStage, 734, 412, "All Tables - Add Guests");
    }

    public ObservableList<Stage> getStageList() {
        return prevStageList;
    }
}


