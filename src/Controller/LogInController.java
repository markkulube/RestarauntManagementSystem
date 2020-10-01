package Controller;
//import Model.*;
//import View.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class LogInController extends StageController {
    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUserName, txtPassword, employeeID;

    @FXML
    private Button login, exit, reset;

    @FXML
    private Parent root;

    public Label getLblStatus() {
        return lblStatus;
    }

    public void Login(ActionEvent event ) throws Exception {

        String id = employeeID.getText().trim();
        String username = (txtUserName.getText().trim());
        String password = (txtPassword.getText().trim());
        String logInfo = id + ", " + username + ", " + password;
        txtUserName.clear();
        txtPassword.clear();
        employeeID.clear();

        boolean logInfoCheck = Password.checkPassword(logInfo);

        String workerType = "";
        String fxml = "";

        if (logInfoCheck) {
            lblStatus.setText("Login Success");
            sleep(10);

            if ((username.equals("g"))) {
                workerType = "Greeter";
                fxml = "Greeter.fxml";
            }
            if ((username.equals("s"))) {
                workerType = "Server";
                fxml = "Server.fxml";
            }if (username.equals("m")) {
                workerType = "Manager";
                fxml = "Manager.fxml";
         }

            if ((username.equals("c"))) {
                workerType = "Cook";
                fxml = "Cook.fxml";
            }
            setUser(username, id, workerType);
            NextView(fxml, workerType);
            Reset();

        } else {
            lblStatus.setText("Login Failed...Try Again!!");
        }
    }

        public void NextView (String name, String workerType) throws Exception {
            prevStage = (Stage) login.getScene().getWindow();
            nextStage(name, prevStage, 612, 412, workerType + " Home");
        }

    public void setUser(String type, String ID, String workerType) {
        for (int i = 0; i < 4 ; i++) {
            if("g".equals(type) && Restaurant.employeeGrps.getGreeterObservableList().get(i).getEmployeeID() == Integer.parseInt(ID)) {
                Restaurant.setUser(Restaurant.employeeGrps.getGreeterObservableList().get(i));

            }
            if("c".equals(type) && Restaurant.employeeGrps.getCookObservableList().get(i).getEmployeeID() == Integer.parseInt(ID)) {
                Restaurant.setUser(Restaurant.employeeGrps.getCookObservableList().get(i));
            }
            if("s".equals(type) && Restaurant.employeeGrps.getServerObservableList().get(i).getEmployeeID() == Integer.parseInt(ID)) {
                Restaurant.setUser(Restaurant.employeeGrps.getServerObservableList().get(i));
            }

            if("m".equals(type) && Restaurant.employeeGrps.getManagerObservableList().get(i).getEmployeeID() == Integer.parseInt(ID)) {
                Restaurant.setUser(Restaurant.employeeGrps.getManagerObservableList().get(i));
            }
        }
        Restaurant.user.setWorkerType(workerType);
    }

    public void Reset() {
        txtUserName.clear();
        txtPassword.clear();
        employeeID.clear();
        lblStatus.setText("Welcome!");
    }
}
