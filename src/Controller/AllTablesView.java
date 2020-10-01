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

public class AllTablesView extends StageController implements Initializable {

    @FXML
    private Button table1, table2;

    @FXML
    private Stage prevStage, curStage, nextStage;

    @FXML
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /*
    public void Table2(ActionEvent event) throws IOException {
        prevStage = (Stage) table2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Table2.fxml"));
        Scene scene = new Scene(root, 300, 300);
        Scene prevScene = prevStage.getScene();
        nextStage.setTitle("My Tables");
        nextStage.setScene(scene);
        nextStage.setWidth(650);
        nextStage.setHeight(400);
        //tableLabel.setText("Table #2");
        prevStage.close();
        nextStage.show();
        OpenPreviousStage.addPrevStage(prevStage);
    }
*/
}
