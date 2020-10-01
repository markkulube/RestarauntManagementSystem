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


public class ServerView extends StageController implements Initializable {

    @FXML
    private Label headerLabel;

    @FXML
    private Parent root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headerLabel.setText("Server " + Restaurant.user.getEmployeeID() + " Profile");
    }

}
