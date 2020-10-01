package Controller;

import javafx.stage.Stage;

public class LogOutToLogIn {

    private static Main2 main;

    public LogOutToLogIn() {
        this.main = new Main2();
    }


    public static void logOut(Stage curStage) throws Exception {
        curStage.close();
        Main2 main = new Main2();
        main.start(curStage);
    }
}
