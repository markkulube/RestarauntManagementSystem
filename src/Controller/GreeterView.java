package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GreeterView extends StageController implements Initializable{

    @FXML
    private Button addGuests, reservations;

    @FXML
    private AnchorPane node;

    @FXML
    private Label headerLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headerLabel.setText("Greeter " + Restaurant.user.getEmployeeID() + " Profile");
    }


}
