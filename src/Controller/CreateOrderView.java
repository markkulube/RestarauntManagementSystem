package Controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.ws.Action;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Controller.Restaurant.selectedTable;


public class CreateOrderView extends StageController implements Initializable {

    @FXML
    private  Button viewTableOrder, confirmDish, addDish, removeDish, printBill, confirmR1, confirmR2, cust1Chair, cust2Chair, cust3Chair, cust4Chair, reset;

    @FXML
    private  ChoiceBox<String> dish, request1, request2, plusminus1, plusminus2;

    @FXML
    private TableView custTableOrder;

    @FXML
    private TableColumn dishTable, tableReq1, tableReq2, priceTable;

    @FXML
    private Parent root;

    @FXML
    private Label tableLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(user instanceof Greeter) {
            dish.setVisible(false); confirmDish.setVisible(false); addDish.setVisible(false);
            confirmR1.setVisible(false); confirmR2.setVisible(false); request1.setVisible(false);
            request2.setVisible(false); plusminus1.setVisible(false); plusminus2.setVisible(false);
            addDish.setVisible(false); reset.setVisible(false); viewTableOrder.setVisible(false);
            removeDish.setVisible(false);

        } else {

            MenuController.choiceBoxDishes(dish);

            plusminus1.getItems().addAll("-1", "+1", "0");
            plusminus2.getItems().addAll("-1", "+1", "0");
            plusminus1.setValue("0");
            plusminus2.setValue("0");
            request1.getItems().addAll("None");
            request1.setValue("None");
            request2.getItems().addAll("None");
            request2.setValue("None");
            request1.setDisable(true);
            request2.setDisable(true);

            plusminus1.setDisable(true);
            confirmR1.setDisable(true);

            plusminus2.setDisable(true);
            confirmR2.setDisable(true);
            addDish.setDisable(true);

            plusminus1.setValue("0");
            plusminus2.setValue("0");

            viewTableOrder.setDisable(true);

            dish.setDisable(true);
        }

        cust1Chair.setText(selectedTable.getCustomerAtTabl().get(0).getcustID());
        cust2Chair.setText(selectedTable.getCustomerAtTabl().get(1).getcustID());
        cust3Chair.setText(selectedTable.getCustomerAtTabl().get(2).getcustID());;
        cust4Chair.setText(selectedTable.getCustomerAtTabl().get(3).getcustID());;

        tableLabel.setText("Table " + selectedTable.getTableID() + ": [select customer] order ");


    }

    public void Customer(ActionEvent event) {
        if((((Button) event.getSource()).getId()).equals(cust1Chair.getId())){
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(0)));
        }

        if((((Button) event.getSource()).getId()).equals(cust2Chair.getId())){
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(1)));
        }

        if((((Button) event.getSource()).getId()).equals(cust3Chair.getId())){
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(2)));
        }

        if((((Button) event.getSource()).getId()).equals(cust4Chair.getId())){
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(3)));
        }

        dishTable.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        tableReq1.setCellValueFactory(new PropertyValueFactory<Dish, String>("request1"));
        tableReq2.setCellValueFactory(new PropertyValueFactory<Dish, String>("request2"));
        priceTable.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));

        //Step 4: add data inside table

        custTableOrder.setItems(curCustomer.getMyOrder().getDishes());
        viewTableOrder.setDisable(false);
        dish.setDisable(false);
        confirmDish.setDisable(false);
        tableLabel.setText("Table " + selectedTable.getTableID() + ": " + curCustomer.getcustID() + "'s order");
        custTableOrder.getSelectionModel().clearSelection();
        Reset();
        custTableOrder.refresh();
    }

    public void Reset() {
        request1.getItems().remove(0, request1.getItems().size());
        request2.getItems().remove(0, request2.getItems().size());

        plusminus1.setDisable(true);
        request1.setDisable(true);
        confirmR1.setDisable(true);
        plusminus1.getSelectionModel().clearSelection();

        plusminus2.setDisable(true);
        request2.setDisable(true);
        confirmR2.setDisable(true);
        plusminus2.getSelectionModel().clearSelection();


        request1.setValue("None");
        request2.setValue("None");
        plusminus1.setValue("0");
        plusminus2.setValue("0");

        dish.setDisable(false);

        confirmDish.setDisable(false);
        addDish.setDisable(true);
    }

    public void ConfirmDish() {
        if (!dish.isDisabled()){
            dish.setDisable(true);
            confirmDish.setDisable(true);

        MenuController.choiceIngreds(dish.getValue(), request1);
        MenuController.choiceIngreds(dish.getValue(), request2);
            plusminus1.setDisable(false);
            request1.setDisable(false);
            confirmR1.setDisable(false);
        }
    }

    public void ConfirmRequest(ActionEvent event) {

        if((((Button) event.getSource()).getId()).equals(confirmR1.getId())) {
            plusminus1.setDisable(true);
            request1.setDisable(true);
            confirmR1.setDisable(true);
            plusminus2.setDisable(false);
            request2.setDisable(false);
            confirmR2.setDisable(false);
        }

        if((((Button) event.getSource()).getId()).equals(confirmR2.getId())) {
            plusminus2.setDisable(true);
            request2.setDisable(true);
            addDish.setDisable(false);
            confirmR2.setDisable(true);
            if(request1.getValue().equals("None")) {
                plusminus1.setValue("0");
            }
            if(request2.getValue().equals("None")) {
                plusminus2.setValue("0");
            }

            if (plusminus1.getValue().equals("0")) {
                request1.setValue("None");
            }

            if (plusminus2.getValue().equals("0")) {
                request2.setValue("None");
            }
        }
    }

    public void AddDish(ActionEvent event) throws Exception {

        String dishOrder = dish.getValue() + " # " +  request1.getValue() + ": " + plusminus1.getValue()
                + " % "  + request2.getValue() + ": " + plusminus2.getValue();
        if(!dishOrder.equals("")) {
            Boolean inStock = curCustomer.createDish(dishOrder, curCustomer.getcustID());
            if(!inStock) {
                prevStage = (Stage) addDish.getScene().getWindow();
                name = "NoCustAlert.fxml";
                double width = 250;
                double height = 150;
                String title = "Error";
                nextStage(name, prevStage, width, height, title);
            }else{
                kitchen.subtractStock(curCustomer.getMyOrder().getCustOrder().get((curCustomer.getMyOrder().getCustOrder().size() - 1)));
            }
        }

        // Step: 3# Associate data with columns
        dishTable.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        tableReq1.setCellValueFactory(new PropertyValueFactory<Dish, String>("request1"));
        tableReq2.setCellValueFactory(new PropertyValueFactory<Dish, String>("request2"));
        priceTable.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));

        //Step 4: add data inside table
        custTableOrder.setItems(curCustomer.getMyOrder().getDishes());

        addDish.setDisable(true);
        custTableOrder.refresh();
        Reset();
    }



    public void ViewTableOrder() throws Exception {
        if(!custTableOrder.getItems().isEmpty()) {
        prevStage = (Stage) viewTableOrder.getScene().getWindow();
        name = "TableOrder.fxml";
        nextStage(name, prevStage, 926, 422, "Table Order");
        }
    }
}
