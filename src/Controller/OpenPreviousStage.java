package Controller;

import javafx.stage.Stage;

import java.util.ArrayList;

public class OpenPreviousStage {

    public ArrayList<Stage> stageList;
    public ArrayList<Stage> sceneList;
    public Stage prevStage, nextStage;

    public OpenPreviousStage() {
        this.stageList = new ArrayList<Stage>();
        this.prevStage = new Stage();
        this.nextStage = new Stage();
    }

    public ArrayList<Stage> getStageList() {
        return stageList;
    }

    public void prevStage(Stage curStage) {
        prevStage = stageList.get((stageList.size()) -1);
        prevStage.show();
        stageList.remove((stageList.size()) -1);
        curStage.close();
    }

    public void addPrevStage(Stage prevStage) {
        Stage prevPrevStage = stageList.get((stageList.size()) - 1);
        if (!prevStage.getScene().getRoot().equals(prevPrevStage.getScene().getRoot())) {
            stageList.add(prevStage);
        }
    }
}
