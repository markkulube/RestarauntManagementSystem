package Controller;

/**
 * A Greeter seats customer at a table.
 */


 /*
 1. Created Greeter object
 2. recepID, tableSet
 3. Method initCustomer to add customers to a table
  */
public class Greeter extends Employee {

    // ALl tables in the restaraunt
    private TableSet tableSet;

    public Greeter(int iD, TableSet restTables) {
        super(iD);
        this.tableSet = restTables;
    }

    public Greeter(int ID) {
        this.setEmployeeID(ID);
    }

    public void initCustomers(String customer) {
        String[] custGrpStage1 = customer.split(",");
        Table tempTable = Restaurant.getSelectedTable();


        for (int j = 0; j < custGrpStage1.length ; j++) {
            String custName = custGrpStage1[j].trim();
            if(custName.length() != 0) {
                Customer tempCustomer = new Customer(custName);
                tempTable.addCustomer(tempCustomer);
            }
        }
        tempTable.setTableOccupied(true);
    }

}
