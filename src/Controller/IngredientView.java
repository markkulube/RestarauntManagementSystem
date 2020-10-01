package Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import javax.xml.ws.Action;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static java.lang.Thread.sleep;

import static Controller.Restaurant.observableStageList;
import static Controller.Restaurant.selectedTable;


public class IngredientView extends StageController implements Initializable {

    @FXML
    private Button updateStock1, updateStock2;

    @FXML
    private TableView ingredientView;

    @FXML
    private TableColumn ingredient, quantity, reorder;

    @FXML
    private TextField ingred, qty, file;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ingredTable();
        file.setText("request");
        file.setDisable(true);

    }

    public void UpdateStock(ActionEvent event) throws Exception {
       try { String filename = file.getText();
           if(updateStock2.getId().equals(((Button) event.getSource()).getId()) && ("request".equals(filename))) {
               BufferedReader inputFile = new BufferedReader(new FileReader("request"));
               String input = inputFile.readLine();
               while(input != null) {
                   kitchen.receiveShipment(input = inputFile.readLine());
                   input = inputFile.readLine();
               }
           }
           if(updateStock1.getId().equals(((Button) event.getSource()).getId())) {
               kitchen.receiveShipment(ingred.getText() + ": " + Integer.parseInt(qty.getText()));
               ingred.clear();
               qty.clear();
           }

       } catch (Exception e){
           prevStage = (Stage) home.getScene().getWindow();
           name = "NoCustAlert.fxml";
           double width = 250;
           double height = 150;
           String title = "Error";
           nextStage(name, prevStage, width, height, title);
       }


       ingredTable();

    }

    public void ingredTable() {
        ObservableList<Ingredient> observableStageList = FXCollections.observableArrayList();

        for (String key: kitchen.getInventory().keySet()) {
            Ingredient tempIngred = new Ingredient(new SimpleStringProperty(key), new SimpleStringProperty(kitchen.getInventory().get(key) + ""));
            observableStageList.add(tempIngred);
        }


        ingredient.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Dish, String>("quantity"));
        reorder.setCellValueFactory(new PropertyValueFactory<Dish, String>("reorder"));


        ingredientView.setItems(observableStageList);
        ingredientView.refresh();
    }
}