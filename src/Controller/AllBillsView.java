package Controller;

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

import java.util.Date;


public class AllBillsView extends StageController implements Initializable {

    @FXML
    private  Button checkOut;

    @FXML
    private TableView allBillView;

    @FXML
    private TableColumn orderNum, billNum, total, date ;

    @FXML
    private Parent root;


    @FXML
    private Label subTotal, tax, gratuity, grandTotal, billStatus;

    @FXML
    private ObservableList<Dish> observableDishList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        observableDishList = paidBills;


        orderNum.setCellValueFactory(new PropertyValueFactory<Dish, String>("orderNum"));
        billNum.setCellValueFactory(new PropertyValueFactory<Dish, String>("billNum"));
        total.setCellValueFactory(new PropertyValueFactory<Dish, String>("total"));
        date.setCellValueFactory(new PropertyValueFactory<Dish, String>("date"));

        allBillView.getItems().clear();
        allBillView.setItems(observableDishList);
        allBillView.refresh();
    }
}