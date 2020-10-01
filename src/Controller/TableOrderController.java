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
import static Controller.Restaurant.selectedTable;


public class TableOrderController extends StageController implements Initializable {

    @FXML
    private  Button submitOrder, accept, reject,submitRedo, updateTableOrder, viewAcceptedDish, viewDish;

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
        //String ordNum = selectedTable.getTableOrder().getOrderNum() + "";


        submitRedo.setDisable(true); redoText.setDisable(true); statusText1.setDisable(true); viewDish.setVisible(false); //viewAcceptedDish.setDisable(true); ;
        accept.setDisable(true); reject.setDisable(true); submitRedo.setDisable(true); updateTableOrder.setDisable(true); //orderStatus.setDisable(true);
        dishStatus.setDisable(true); enterRedo.setDisable(true); redoText.setDisable(true); statusText2.setDisable(true);

        if(Restaurant.selectedTable.getTableOrder().getStrStatus().equals("Ready") && user instanceof  Server) {
            accept.setDisable(false); reject.setDisable(false);
            dishStatus.setDisable(false);
            updateTableOrder.setDisable(false);
        }

        if(Restaurant.selectedTable.getTableOrder().getStrStatus().equals("Delivered") ) {
            ViewAcceptedDish();
        }
        if(!Restaurant.selectedTable.getTableOrder().getStrStatus().equals("Delivered") ) {
            printBill.setDisable(true);
        }

        Restaurant.selectedTable.getTableOrder().getTableOrder().clear();
        observableDishList = FXCollections.observableArrayList();

        ViewDishes();


        if (Restaurant.selectedTable.getTableOrder().getOrderNum() != 0) {
            statusText2.setText(selectedTable.getTableOrder().getStatus());
            submitOrder.setVisible(false);
        }

        custName.setCellValueFactory(new PropertyValueFactory<Dish, String>("custID"));
        dishTable.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        tableReq1.setCellValueFactory(new PropertyValueFactory<Dish, String>("request1"));
        tableReq2.setCellValueFactory(new PropertyValueFactory<Dish, String>("request2"));
        priceTable.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));
        accRej.setCellValueFactory(new PropertyValueFactory<Dish, String>("accrej"));
        redo.setCellValueFactory(new PropertyValueFactory<Dish, String>("redo"));

        tableOrderView.setItems(observableDishList);
        tableOrderView.getColumns().sorted();
        tableOrderView.refresh();

    }

    public void RemoveDish(ActionEvent event) {
    }

    public void SubmitOrder() throws Exception {
        Order tempOrder = selectedTable.getTableOrder();
        kitchen.addOrder(tempOrder);
        if(!Restaurant.user.getMyOrders().contains(tempOrder)) {
            Restaurant.user.addOrder(tempOrder); }

        tempOrder.setServerNum((user.getEmployeeID() + ""));
        tempOrder.setTableNum(selectedTable.getTableID() + "");
        tempOrder.setStatusEnum(OrderStatus.SUBMITTED);

        prevStage = (Stage) submitOrder.getScene().getWindow();
        name = "MyOrders.fxml";
        nextStage(name, prevStage, 650, 500, "Server #" + user.getEmployeeID() + ": Order");
    }

    public void AcceptReject(ActionEvent event) {
        if (!tableOrderView.getSelectionModel().getSelectedCells().isEmpty()) {

            Dish dish = (Dish) tableOrderView.getSelectionModel().getSelectedItem();

            if((((Button) event.getSource()).getId()).equals(accept.getId())) {
                dish.setAccrej("ACC");
                enterRedo.setText("");
            }

            if((((Button) event.getSource()).getId()).equals(reject.getId())) {
                dish.setAccrej("REJ");

                enterRedo.setDisable(false);
                submitRedo.setDisable(false);
                redoText.setDisable(false);
            }
            tableOrderView.refresh();
        }
    }

    public void SubmitRedo(ActionEvent event) {
        if (!tableOrderView.getSelectionModel().getSelectedCells().isEmpty()) {

            Dish dish = (Dish) tableOrderView.getSelectionModel().getSelectedItem();
            dish.setRedo(redoText.getText());

            redoText.clear();
            redoText.setDisable(true);
            submitRedo.setDisable(true);
            tableOrderView.refresh();
        }
    }

    public void UpdateTableOrder() throws Exception {
        ArrayList<Dish> tempDishArray = new ArrayList<>();
        for (int i = 0; i < tableOrderView.getItems().size() ; i++) {
            Dish tempDish = (Dish) tableOrderView.getItems().get(i);
            if(tempDish.getAccrej().trim().equals("ACC")) {
                selectedTable.getTableOrder().updateCustOrder(tempDish, tempDish.getCustID());
                tempDishArray.add(tempDish);
            }
            if(tempDish.getAccrej().equals("REJ")) {
                tempDish.setRedo("");
                tempDish.setAccrej("");
            }
        }

        for (int i = 0; i < tempDishArray.size(); i++) {
            tableOrderView.getItems().remove(tempDishArray.get(i));
        }

        if (tableOrderView.getItems().size() != 0) {
            selectedTable.getTableOrder().setStatusEnum(OrderStatus.REDO);
            statusText2.setText(selectedTable.getTableOrder().getStatus());

            prevStage = (Stage) updateTableOrder.getScene().getWindow();
            name = "MyOrders.fxml";
            nextStage(name, prevStage, 650, 500, "Server" + user.getEmployeeID() + " Orders");
        }
        else {
            selectedTable.getTableOrder().setStatusEnum(OrderStatus.DELIVERED);
            statusText2.setText(selectedTable.getTableOrder().getStatus());
            updateTableOrder.setDisable(true);
            accept.setDisable(true);
            reject.setDisable(true);
            redo.setVisible(false);
            printBill.setDisable(false);

            kitchen.getOrders().remove(selectedTable.getTableOrder());
            kitchen.completeOrders.add(selectedTable.getTableOrder());

            user.getMyOrders().remove(selectedTable.getTableOrder());
            //user.getPreparedReadyOrders().add(selectedTable.getTableOrder());
        }
    }

    public void ViewAcceptedDish() {
        observableDishList.removeAll();
        tableOrderView.getItems().removeAll();
        observableDishList.clear();
        tableOrderView.getItems().clear();

        for (int i = 0; i < selectedTable.getTableOrder().getConfirmedOrder().size(); i++) {
            Dish tempDish = (Dish) selectedTable.getTableOrder().getConfirmedOrder().get(i);
            observableDishList.add(tempDish);
        }


        viewAcceptedDish.setVisible(false);
        viewDish.setVisible(true);
        tableOrderView.setItems(observableDishList);
        tableOrderView.refresh();
    }

    public void ViewDishes() {
        for (int i = 0; i < Restaurant.selectedTable.getCustomerAtTabl().size() ; i++) {

            CustomerOrder tempCustOrder = (CustomerOrder) ((Customer) (Restaurant.selectedTable.getCustomerAtTabl().get(i))).getMyOrder();
            if(!Restaurant.selectedTable.getTableOrder().getTableOrder().contains(tempCustOrder)) {
                Restaurant.selectedTable.getTableOrder().addItem(tempCustOrder);
            }
        }

        observableDishList.removeAll();
        tableOrderView.getItems().removeAll();
        observableDishList.clear();
        tableOrderView.getItems().clear();
        for (int i = 0; i <Restaurant.selectedTable.getTableOrder().getTableOrder().size();  i++) {
            for (int k = 0; k < Restaurant.selectedTable.getTableOrder().getTableOrder().get(i).getDishes().size(); k++) {
                Dish tempDishGUI = Restaurant.selectedTable.getTableOrder().getTableOrder().get(i).getDishes().get(k);
                observableDishList.add(tempDishGUI);
            }
            viewAcceptedDish.setVisible(true);
            viewDish.setVisible(false);
        }

        tableOrderView.refresh();
    }

    public void printBill(ActionEvent event) {

    }
}
