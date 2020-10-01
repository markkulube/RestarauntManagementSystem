package Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

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


public class ManagerView extends StageController implements Initializable {

    @FXML
    private  Button checkInventory, billRecord;

    @FXML
    private TableView tableOrderView;

    @FXML
    private TableColumn custName, dishTable, tableReq1, tableReq2, priceTable, accRej,redo;

    @FXML
    private Label tableLabel, statusText1, statusText2, dishStatus, enterRedo, orderStatus;

    @FXML
    private TextField redoText;

    @FXML
    private Parent root;

    private ObservableList<Dish> observableDishList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void CheckInventory(ActionEvent event) throws Exception {
        prevStage = (Stage) checkInventory.getScene().getWindow();
        name = "Ingredient.fxml";
        nextStage(name, prevStage, 600, 452, "Inventory Home");

    }

    public void BillRecord(ActionEvent event) throws Exception {
        prevStage = (Stage) billRecord.getScene().getWindow();
        name = "AllBills.fxml";
        nextStage(name, prevStage, 600, 400, "All Bills Home");
    }
}


