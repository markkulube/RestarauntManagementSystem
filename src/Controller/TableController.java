package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController extends StageController implements Initializable{

    @FXML
    private Button back, logOut, createOrder, viewOrder, printBill;

    @FXML
    private  Button cust1Chair, cust2Chair, cust3Chair, cust4Chair;

    @FXML private Label tableLabel;

    @FXML
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cust1Chair.setText(((Customer) (selectedTable.getCustomerAtTabl().get(0))).getcustID());
        cust2Chair.setText(((Customer) (selectedTable.getCustomerAtTabl().get(1))).getcustID());
        cust3Chair.setText(((Customer) (selectedTable.getCustomerAtTabl().get(2))).getcustID());
        cust4Chair.setText(((Customer) (selectedTable.getCustomerAtTabl().get(3))).getcustID());

        tableLabel.setText("Table " + Restaurant.selectedTable.getTableID());
    }

    public void CreateOrder(ActionEvent event) throws IOException {
        prevStage = (Stage) createOrder.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));
        Scene scene = new Scene(root, 300, 300);
        Scene prevScene = prevStage.getScene();
        nextStage.setTitle("Create Order");
        nextStage.setScene(scene);
        nextStage.setWidth(850);
        nextStage.setHeight(450);
    }

    public void ViewOrder(ActionEvent event) throws IOException {
        prevStage = (Stage) createOrder.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TableOrder.fxml"));
        Scene scene = new Scene(root, 300, 300);
        Scene prevScene = prevStage.getScene();
        nextStage.setTitle("Table Order");
        nextStage.setScene(scene);
        nextStage.setWidth(850);
        nextStage.setHeight(450);
        //tableLabel.setText("Table #2");
        prevStage.close();
        nextStage.show();
    }

}
