package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;


public class MenuController extends StageController implements Initializable{

    @FXML
    private Button tables, back, logOut, myOrders, exit;

    @FXML
    private Stage curStage, prevStage, nextStage, exitMenu;

    @FXML
    private Parent root;

    @FXML
    private TableView menuTable;

    @FXML
    TableColumn name, price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText("Dish");
        price.setText("Price");
        name.setPrefWidth(230);
        price.setPrefWidth(230);
        //menuTable.getColumns().addAll(name, price);

        // Step : 1# Create a dish class that will represent data
        // Step2: 2# Define data in an Observable list and add data as you want to show inside table
        ObservableList<Dish> dishes = FXCollections.observableArrayList();
        ArrayList tempMenu = Restaurant.getMenu().getMenu();
        for (int i = 0; i < tempMenu.size(); i++) {
            dishes.add((Dish) tempMenu.get(i));
        }

        // Step: 3# Associate data with columns
        name.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Dish, String>("price"));

        //Step 4: add data inside table
        menuTable.setItems(dishes);
    }

    public static void choiceBoxDishes(ChoiceBox choice) {
        ChoiceBox choiceBox = choice;
        for (Object object: Restaurant.getMenu().getMenu()) {
            choiceBox.getItems().addAll(((Dish) object).getName());
        }
    }

    public static void choiceIngreds(String dishName, ChoiceBox choice) {
        ChoiceBox choiceBox = choice;
        for (Object object: Restaurant.getMenu().getMenu()) {
            if(((Dish) object).getName().equals(dishName)) {
                for (String key: ((Dish) object).getIngredients().keySet()) {
                    choiceBox.getItems().addAll(key);
                }
                choiceBox.getItems().addAll("None");

            }

        }
    }
}



