package Controller;

import java.util.ArrayList;

/**
 * A TableSet is the collection of all tables
 * in the restaraunt.
 */

/*
1. Created getter for tableSet ArrayList.
 */
public class TableSet {


    // Total tables in restaurant
    private int totTable;

    // Capcity of tables
    private int tableCap;

    
    // Collection of tables
    ArrayList<Table> tableSet;
    
    public TableSet() {
        this.tableCap = 4;
        this.totTable = 20;
        tableSet = new ArrayList<>();
        addTables(totTable);
    }
    
    //add tables to restaurant
    public void addTables(int totTable) {
        ArrayList tableSet = new ArrayList();
        for(int i=0; i<totTable; i++) {
            int tableID = i + 1;
            Table table = new Table(tableCap, tableID);
            this.addTable(table);
        }
    }

    
    public void addTable(Table table) {
        tableSet.add(table);
    }
    

    public Table getTableWithId(String id) {
        Table table = null;

        for (int i = 0; i < tableSet.size() ; i++) {
            if((id).equals("table" + tableSet.get(i).getTableID())) {
                table = ((tableSet.get(i)));
            }
        }

        return table;
    }


    public ArrayList<Table> getTableSet() {
        return tableSet;
    }

    @Override
    public String toString() {
        String tabls = "";
        
        for(int i=0; i<tableSet.size(); i++) {
            tabls += "[Table " + (i+1) + "]" + " ";
        }
        return tabls;
        
    }
}
