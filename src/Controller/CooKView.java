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

import static java.lang.Thread.sleep;


public class CooKView extends  StageController implements Initializable {

    @FXML
    private Button viewAllOrder;

    @FXML
    private Label labelHeader;

    @FXML
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelHeader.setText(("Cook " + Restaurant.user.getEmployeeID() + " Home"));
    }

    public void ViewAllOrder() throws Exception {
        prevStage = (Stage) viewAllOrder.getScene().getWindow();
        name = "AllOrders.fxml";
        nextStage(name, prevStage, 700, 600, "Cook Home");
    }
}

