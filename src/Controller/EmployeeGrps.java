package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Collections of different employees in restaurant
 */

public class EmployeeGrps {

    // Server group
    private static ObservableList<Server> serverObservableList;

    // Cook group
    private static ObservableList<Cook> cookObservableList;

    // Greeter group
    private static ObservableList<Greeter> greeterObservableList;

    // Manager group
    private static ObservableList<Manager> managerObservableList;

    public EmployeeGrps() {
        this.serverObservableList = FXCollections.observableArrayList();
        this.cookObservableList = FXCollections.observableArrayList();
        this.greeterObservableList = FXCollections.observableArrayList();
        this.managerObservableList = FXCollections.observableArrayList();
    }

    /**
     * Create different Employees objects
     *
     *@param  totEmplNum Number of employees to initilizaze;
     *@param  tableSet Set of tables for the Greeters
     *@param  kitchen Kitchen for the Servers and Cooks
     *
     */
    public void createGrp(int totEmplNum, TableSet tableSet, Kitchen kitchen) {
        for (int i = 0; i < totEmplNum ; i++) {
            Greeter tempGreeter = new Greeter(i+1, tableSet);
            greeterObservableList.add(tempGreeter);

            Manager tempManager = new Manager(i+1, kitchen, tableSet);
            managerObservableList.add(tempManager);

            Server tempServer = new Server(i+1,kitchen);
            if(i+1 == 1) {
                for (int j = 0; j <  2; j++) {
                    tempServer.addTable(tableSet.getTableSet().get(j));
                }
            }
            if(i+1 == 2) {
                for (int j = 2; j < 6; j++) {
                    tempServer.addTable(tableSet.getTableSet().get(j));
                }
            }
            if(i+1 == 3) {
                for (int j = 6; j < 10; j++) {
                    tempServer.addTable(tableSet.getTableSet().get(j));
                }
            }
            if(i+1 == 4) {
                for (int j = 10; j < 14; j++) {
                    tempServer.addTable(tableSet.getTableSet().get(j));
                }
            }

            serverObservableList.add(tempServer);

            Cook tempCook = new Cook(i+1, kitchen);
            cookObservableList.add(tempCook);
        }
    }

    public static ObservableList<Manager> getManagerObservableList() {
        return managerObservableList;
    }

    public Cook getCook(int ID) {
        for (int i = 0; i < cookObservableList.size(); i++) {
            if(cookObservableList.get(i).employeeID ==ID) {
                return cookObservableList.get(i);
            }
        }
        return null;
    }

    public ObservableList<Cook> getCookObservableList() {
        return cookObservableList;
    }

    public  ObservableList<Greeter> getGreeterObservableList() {
        return greeterObservableList;
    }

    public ObservableList<Server> getServerObservableList() {
        return serverObservableList;
    }
}

