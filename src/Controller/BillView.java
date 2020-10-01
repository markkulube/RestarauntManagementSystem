
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


public class BillView extends StageController implements Initializable {

    @FXML
    private  Button checkOut, custCheckOut, tableBill;

    @FXML
    private TableView billView;

    @FXML
    private TableColumn dish, price;

    @FXML
    private Parent root;


    @FXML
    private Label subTotal, tax, gratuity, grandTotal, billStatus;

    @FXML
    private ObservableList<Dish> observableDishList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!selectedTable.getTableOrder().getStatus().equals("Delivered")) {
            checkOut.setDisable(true);
        }

        custCheckOut.setDisable(true);
        tableBill.setDisable(true);

        curCustomer = null;
        TableBillView();

    }

    public void CheckOut(ActionEvent event) throws CloneNotSupportedException {
        selectedTable.getTableOrder().setStatusEnum(OrderStatus.PAID);
        selectedTable.getBill().setStatus("Paid");
        selectedTable.getBill().setDate(date.toString());
        Bill clone = (Bill) selectedTable.getBill().clone();
        clone.setDate(date.toString());
        billStatus.setText(selectedTable.getBill().getStatus());
        paidBills.add(clone);
        Restaurant.tableSet.getTableSet().remove(Restaurant.selectedTable);
        Restaurant.selectedTable = null;
        Restaurant.selectedTable = new Table(selectedTable.getTableCap(), selectedTable.getTableID());
        selectedTable = null;
        Restaurant.tableSet.addTable(Restaurant.selectedTable);
        selectedTable = Restaurant.selectedTable;
        checkOut.setDisable(true);
        cust1Chair.setDisable(true);
        cust2Chair.setDisable(true);
        cust3Chair.setDisable(true);
        cust4Chair.setDisable(true);
        TableBillView();

    }

    public void CustCheckOut(ActionEvent event) {
        for (Dish dish: curCustomer.getMyOrder().getConfirmedDishes()) {
            selectedTable.getTableOrder().getConfirmedOrder().remove(dish);
        }
        curCustomer.getMyOrder().getConfirmedDishes().clear();

        if(curCustomer.getcustID().equals(((selectedTable.getCustomerAtTabl().get(0).getcustID()))) ){
            cust1Chair.setDisable(true);
        }

        if(curCustomer.getcustID().equals(((selectedTable.getCustomerAtTabl().get(1).getcustID())))) {
            cust2Chair.setDisable(true);
        }

        if(curCustomer.getcustID().equals(((selectedTable.getCustomerAtTabl().get(2).getcustID())))) {
            cust3Chair.setDisable(true);
        }

        if(curCustomer.getcustID().equals(((selectedTable.getCustomerAtTabl().get(3).getcustID())))) {
            cust4Chair.setDisable(true);
        }
        observableDishList = FXCollections.observableArrayList();

        billStatus.setText(selectedTable.getBill().getStatus());

        double subtotal = 0;
        for (int i = 0; i < curCustomer.getMyOrder().getConfirmedDishes().size(); i++) {
            Dish tempDish = (Dish) curCustomer.getMyOrder().getConfirmedDishes().get(i);
            observableDishList.add(tempDish);
            subtotal += Double.parseDouble(tempDish.getPrice());
        }

        double gratuityBill = 0;

        double tax13Percent = subtotal * 0.13;
        if(selectedTable.getCustomerAtTabl().size()>7) {
            gratuityBill = (subtotal * 0.15) / selectedTable.getCustomerAtTabl().size();
        }

        double grandTotalBill = (subtotal + tax13Percent + gratuityBill);

        subTotal.setText(subtotal + "");
        tax.setText(tax13Percent + "");
        gratuity.setText(gratuityBill + "");
        grandTotal.setText(grandTotalBill + "");

        dish.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));



        billView.getItems().clear();
        billView.setItems(observableDishList);
        billView.refresh();
    }

    public void Customer(ActionEvent event) {
        if(curCustomer == null) {
            custCheckOut.setDisable(false);
            tableBill.setDisable(false);
        }

        custCheckOut.setDisable(false);
        tableBill.setDisable(false);
        checkOut.setDisable(true);


        if ((((Button) event.getSource()).getId()).equals(cust1Chair.getId())) {
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(0)));
        }

        if ((((Button) event.getSource()).getId()).equals(cust2Chair.getId())) {
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(1)));

        }

        if ((((Button) event.getSource()).getId()).equals(cust3Chair.getId())) {
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(2)));

        }

        if ((((Button) event.getSource()).getId()).equals(cust4Chair.getId())) {
            curCustomer = ((Customer) (selectedTable.getCustomerAtTabl().get(3)));
        }


        observableDishList = FXCollections.observableArrayList();

        billStatus.setText(selectedTable.getBill().getStatus());

        double subtotal = 0;
        for (int i = 0; i < curCustomer.getMyOrder().getConfirmedDishes().size(); i++) {
            Dish tempDish = (Dish) curCustomer.getMyOrder().getConfirmedDishes().get(i);
            observableDishList.add(tempDish);
            subtotal += Double.parseDouble(tempDish.getPrice());
        }

        double gratuityBill = 0;

        double tax13Percent = subtotal * 0.13;
        if(selectedTable.getCustomerAtTabl().size()>7) {
            gratuityBill = (subtotal * 0.15) / selectedTable.getCustomerAtTabl().size();
        }

        double grandTotalBill = (subtotal + tax13Percent + gratuityBill);

        subTotal.setText(subtotal + "");
        tax.setText(tax13Percent + "");
        gratuity.setText(gratuityBill + "");
        grandTotal.setText(grandTotalBill + "");

        selectedTable.getBill().setTotal(grandTotalBill +"");

        dish.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));

        billView.getItems().clear();
        billView.setItems(observableDishList);
        billView.refresh();
    }

    public void TableBillView() {
        custCheckOut.setDisable(true);
        checkOut.setDisable(false);
        tableBill.setDisable(true);
        observableDishList = FXCollections.observableArrayList();


        double gratuityBill = 0;

        double subtotal = selectedTable.getBill().calculateBill();
        double tax13Percent = subtotal * 0.13;
        if(selectedTable.getCustomerAtTabl().size()>7) {
            gratuityBill = subtotal * 0.15;
        }

        double grandTotalBill = subtotal + tax13Percent + gratuityBill;

        if(grandTotalBill == 0){billStatus.setText("Paid");}

        subTotal.setText(subtotal + "");
        tax.setText(tax13Percent + "");
        gratuity.setText(gratuityBill + "");
        grandTotal.setText(grandTotalBill + "");



        for (int i = 0; i < selectedTable.getTableOrder().getConfirmedOrder().size(); i++) {
            Dish tempDish = (Dish) selectedTable.getTableOrder().getConfirmedOrder().get(i);
            observableDishList.add(tempDish);
        }

        dish.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));

        billView.getItems().clear();
        billView.setItems(observableDishList);
        billView.refresh();
    }
}