package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class TableAddCustController extends StageController implements Initializable {


    @FXML
    private Parent root;

    @FXML
    private AnchorPane node;

    @FXML
    private TextField cust1Name, cust2Name, cust3Name, cust4Name;

    private String cust1, cust2, cust3, cust4, customerNames;

    @FXML
    private Label tableLabel1, tableLabel2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLabel1.setText("Table " + selectedTable.getTableID());

        if (selectedTable.isTableOccupied()) {
            tableLabel2.setText("                   TABLE OCCUPIED");
            submit.setText("Create Order");
            setDisableCustButtons();
        }

        if (user instanceof Greeter) {
            submit.setText("Display");
        }


        if (((user instanceof Server) && user.getMyOrders().contains(selectedTable.getTableOrder()))) {
            submit.setText("View Order");
            setDisableCustButtons();
        }
    }

    public void Submit(ActionEvent event) throws Exception {
        if (Restaurant.selectedTable.getCustomerAtTabl().size() == 0 && ((Restaurant.user)) instanceof Greeter) {
            customerNames = cust1Name.getText() + ", " + cust2Name.getText() + ", " + cust3Name.getText() + ", " + cust4Name.getText();
            ((Greeter) (Restaurant.user)).initCustomers(customerNames);
        }

        if (((user instanceof Server) && user.getMyOrders().contains(selectedTable.getTableOrder()))) {
            prevStage = (Stage) submit.getScene().getWindow();
            name = "TableOrder.fxml";
            nextStage(name, prevStage, 926, 422, user.getWorkerType() + user.getEmployeeID() + " Orders");
        } else {
            prevStage = (Stage) submit.getScene().getWindow();
            name = "CreateOrder.fxml";
            nextStage(name, prevStage, 800, 500, "Add Customer Order");
        }
    }

    public void setDisableCustButtons() {
        cust1Name.setDisable(true);
        cust2Name.setDisable(true);
        cust3Name.setDisable(true);
        cust4Name.setDisable(true);

        if (selectedTable.getCustomerAtTabl().size()  > 0) {
            cust1Name.setText(selectedTable.getCustomerAtTabl().get(0).getcustID());
        }

        if (selectedTable.getCustomerAtTabl().size() > 1) {
            cust2Name.setText(selectedTable.getCustomerAtTabl().get(1).getcustID());
        }

        if (selectedTable.getCustomerAtTabl().size() > 2) {
            cust3Name.setText(selectedTable.getCustomerAtTabl().get(2).getcustID());
        }
        if (selectedTable.getCustomerAtTabl().size() > 3) {
            cust4Name.setText(selectedTable.getCustomerAtTabl().get(3).getcustID());
        }

       /* if (selectedTable.getCustomerAtTabl().size() > 4) {
            cust5Name.setText(selectedTable.getCustomerAtTabl().get(4).getcustID());
        }

        if (selectedTable.getCustomerAtTabl().size() > 5) {
            cust6Name.setText(selectedTable.getCustomerAtTabl().get(5).getcustID());
        }
        if (selectedTable.getCustomerAtTabl().size() > 6) {
            cust7Name.setText(selectedTable.getCustomerAtTabl().get(6).getcustID());
        }

        if (selectedTable.getCustomerAtTabl().size() > 7) {
            cust8Name.setText(selectedTable.getCustomerAtTabl().get(7).getcustID());
        }*/

    }
}
