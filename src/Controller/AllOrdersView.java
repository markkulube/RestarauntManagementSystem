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


public class AllOrdersView extends StageController implements Initializable {

    @FXML
    private  Button acceptOrder, viewCompleteOrder;

    @FXML
    private TableView allOrderView;

    @FXML
    private TableColumn ordNum, tableNum, serverNum, cookNum, status, redo;

    @FXML
    private Parent root;


    @FXML
    private Label orderLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewCompleteOrder.setDisable(true);

        if(user instanceof  Manager || user instanceof Server || (user instanceof Cook && user.getMyOrders().size() > 0)) {
            acceptOrder.setVisible(false);
        }

        if(user instanceof  Manager) {
            myOrders.setVisible(false);
            viewCompleteOrder.setDisable(false);
        }


        // Step: 3# Associate data with columns
        ordNum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderNum"));
        tableNum.setCellValueFactory(new PropertyValueFactory<Order, String>("tableNum"));
        serverNum.setCellValueFactory(new PropertyValueFactory<Order, String>("serverNum"));
        cookNum.setCellValueFactory(new PropertyValueFactory<Order, String>("cookNum"));
        status.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
        redo.setCellValueFactory(new PropertyValueFactory<Order, String>("remake"));

        //Step 4: add data inside table

        allOrderView.setItems(kitchen.getOrders());
        allOrderView.refresh();
    }


    public void AcceptOrder(ActionEvent event){

        if(!allOrderView.getSelectionModel().getSelectedCells().isEmpty()) {
            TablePosition pos = (TablePosition) allOrderView.getSelectionModel().getSelectedCells().get(0);
            int rowPos = pos.getRow();
            Integer selected = (Integer) (((TableColumn) (allOrderView.getColumns().get(0))).getCellObservableValue(rowPos).getValue());

            int orderNum = selected;

            Order order = (Order) allOrderView.getSelectionModel().getSelectedItem();


            order.setCookNum(user.getEmployeeID() + "");

            if (!user.getMyOrders().contains(order)); {
                user.addOrder(order);
                order.setStatusEnum(OrderStatus.WITHCOOK);
                acceptOrder.setDisable(true);
            }

            allOrderView.refresh();
        }
    }


    public void ViewAllOrder(ActionEvent event) {
        allOrderView.getItems().removeAll(0);
        allOrderView.setItems(Restaurant.kitchen.getOrders());
        acceptOrder.setVisible(true);
        viewAllOrder.setVisible(false);
        myOrders.setVisible(true);
        setReady.setVisible(false);
        orderLabel.setText("All Order");
        allOrderView.refresh();
    }

    public void ViewCompleteOrder(ActionEvent event) {
        allOrderView.getItems().removeAll(0);
        allOrderView.setItems(Restaurant.kitchen.getCompleteOrders());
        orderLabel.setText("All Order");
        allOrderView.refresh();
    }



    public void SetReady(ActionEvent event) {

        if(!allOrderView.getSelectionModel().getSelectedCells().isEmpty()){
            Order order = (Order) allOrderView.getSelectionModel().getSelectedItem();
            user.getMyOrders().remove(order);
            order.setStatusEnum(OrderStatus.READY);
            allOrderView.refresh();
        }
    }
}