package Controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PopUpView extends StageController implements  Initializable {



    /**
     * The controller class manages switches between stages, logging out
     * and exiting.
     */

    @FXML
    protected Stage exitPage, nextStage, prevStage, curStage;

    @FXML
    protected Button back, logOut, exit, menu, printBill, home, myOrders, tables, viewAllOrder;

    @FXML
    protected Parent root;

    @FXML
    protected ObservableList<Stage> prevStageList;

    @FXML
    protected Customer curCustomer;

    protected String name;

    protected Table selectedTable;

    protected Order order;

    protected Kitchen kitchen;

    protected Employee user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(user instanceof Manager) {
            popMsg.setText("Invalid input");
        }
    }
}
