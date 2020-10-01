package Controller;

/**
 * The Manager orders new stock of ingredients,
 * accepts the shipment of new stock of ingredients
 * and updates ingredients inventory list
 */

public class Manager extends Employee {

    public Manager(int ID, Kitchen kitchen, TableSet tableSet){
        super(ID, kitchen, tableSet);

    }
}
