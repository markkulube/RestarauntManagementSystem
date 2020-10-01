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

import static Controller.Restaurant.cook;
import static Controller.Restaurant.user;


public class MyOrdersView extends StageController implements Initializable {

    @FXML
    private  Button acceptOrder, tableOrder, myOrder, setReady, setComplete, setDelivered, updateTableOrder;



    @FXML
    private TableColumn ordNum, tableNum, serverNum, cookNum, status, redo;

    @FXML
    private Parent root;

    @FXML
    private Customer curCustomer;

    @FXML
    private Label orderLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderLabel.setText("My Orders: " + user.getWorkerType() + user.getEmployeeID());

        if(user instanceof Server) {
            viewAllOrder.setVisible(false);
            setReady.setVisible(false);
        }


        // Step: 3# Associate data with columns
        ordNum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderNum"));
        tableNum.setCellValueFactory(new PropertyValueFactory<Order, String>("tableNum"));
        serverNum.setCellValueFactory(new PropertyValueFactory<Order, String>("serverNum"));
        cookNum.setCellValueFactory(new PropertyValueFactory<Order, String>("cookNum"));
        status.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
        redo.setCellValueFactory(new PropertyValueFactory<Order, String>("remake"));

        //Step 4: add data inside table

        ObservableList<Order> tempOrdList = FXCollections.observableArrayList();
        for (int i = 0; i < user.getMyOrders().size(); i++) {
            tempOrdList.add(user.getMyOrders().get(i));
        }


        allOrderView.setItems(tempOrdList);
        allOrderView.refresh();
    }




    public void SetReady(ActionEvent event) {
        if (!allOrderView.getSelectionModel().getSelectedCells().isEmpty()) {

            TablePosition pos = (TablePosition) allOrderView.getSelectionModel().getSelectedCells().get(0);
            int rowPos = pos.getRow();
            Integer selected = (Integer) (((TableColumn) (allOrderView.getColumns().get(0))).getCellObservableValue(rowPos).getValue());

            int orderNum = selected;

            Order order = (Order) allOrderView.getSelectionModel().getSelectedItem();

            order.setStatusEnum(OrderStatus.READY);
            user.getMyOrders().remove(order);
            user.getPreparedReadyOrders().add(order);
            //kitchen.subtractStock(order);

            allOrderView.getItems().remove(order);
            allOrderView.refresh();

        }
    }

    public void SetDelivered(ActionEvent event) {
    }

    public void SetComplete(ActionEvent event) {
    }


}