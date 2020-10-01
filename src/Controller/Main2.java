package Controller;
//import Model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main2 extends Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        Password password = new Password();
        password.passWordDataBase();

        Menu menu = new Menu();
        RestaurantSimulation restaurantSimulation = new RestaurantSimulation();
        restaurantSimulation.start();
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Restaraunt Log In");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        primaryStage.show();
    }
}



