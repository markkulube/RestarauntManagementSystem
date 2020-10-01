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

public class TablesController extends StageController implements Initializable {

    @FXML
    private Button table1, table2,table3, table4, table5, table6, table7, table8, table9, table10, table11, table12, table13, table14, table15;

    @FXML
    private Label tableLabel;

    @FXML
    private Stage prevStage, curStage, nextStage;

    @FXML
    private Parent root;

    //1. added buttons fx:id table3 - table8


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!(user instanceof Manager)) {

            table1.setVisible(false);
            table2.setVisible(false);
            table3.setVisible(false);
            table4.setVisible(false);
            table5.setVisible(false);
            table6.setVisible(false);
            table7.setVisible(false);
            table8.setVisible(false);
            table9.setVisible(false);
            table10.setVisible(false);
            table11.setVisible(false);
            table12.setVisible(false);
            table13.setVisible(false);
            table14.setVisible(false);
            table15.setVisible(false);

            if (user.getEmployeeID() == 1) {
                table1.setVisible(true);
                table2.setVisible(true);
            }
            if (user.getEmployeeID() == 2) {
                table3.setVisible(true);
                table4.setVisible(true);
                table5.setVisible(true);
                table6.setVisible(true);
            }
            if (user.getEmployeeID() == 3) {
                table7.setVisible(true);
                table8.setVisible(true);
                table9.setVisible(true);
                table10.setVisible(true);
            }
            if (user.getEmployeeID() == 4) {
                table11.setVisible(true);
                table12.setVisible(true);
                table13.setVisible(true);
                table14.setVisible(true);
                table15.setVisible(true);
            }
        }
    }
}


