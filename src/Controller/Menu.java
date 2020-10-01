package Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Menu lists all Dishes for sale
 * at the restaurant
 */

public class Menu {

    public void printHeader() {
        System.out.println("*-------------------------*");
        System.out.println("|    ******MENU******     |");
        System.out.println("|                         |");
        System.out.println("*-------------------------*");
    }

    public static ArrayList<Dish> menu;

    public Menu() {
        this.menu = makeMenu("Name: Burger, Price: 5, Bread: 2, Beef: 1, Lettuce: 1, Cheese: 1, Tomato: 1 | Name: Hot Dog, Price: 4, Bread: 2, Sausage:1 | Name: Fries, Price: 2, Potato: 2 | + Name: Drink, Price: 1, Pop Can: 2 | Name: Ice Cream, Price: 1, Cream Scoop: 2, Cone: 1 | Name: Poutine, Price: 4, Potato: 2, Cheese: 3 | Name: Boiled Eggs, Price: 1, Egg: 2");
    }

    // Create menu from text file input
    private ArrayList<Dish> makeMenu(String menuText) {
        String[] menuStage1 = menuText.split("\\|");


        ArrayList<Dish> tempMenu = new ArrayList<>();

        for (int i = 0; i <menuStage1.length ; i++) {
            Map<String, Integer> ingredMap = new HashMap<>();
            String[] menuStage2 = menuStage1[i].split(",");
            String[] menuStageName = menuStage2[0].split(":");
            String[] menuStagePrice = menuStage2[1].split(":");
            String dishName = menuStageName[1].trim();
            String dishPrice = menuStagePrice[1].trim();

            //int dishPrice = Integer.parseInt(menuStagePrice[1].trim());

            for(int j=2; j< menuStage2.length; j++ ) {
                String[] menuStage3 = menuStage2[j].split(":");
                ingredMap.put(menuStage3[0].trim(),Integer.parseInt(menuStage3[1].trim()));
            }
            Dish tempFood = new Dish(dishName, dishPrice, ingredMap);
            tempMenu.add(tempFood);
        }
        return tempMenu;
    }

    public static ArrayList getMenu() {
        return menu;
    }

    public String toString() {
        printHeader();
        String menuStr = "";
        for (int i = 0; i < menu.size(); i++) {
            menuStr += this.menu.get(i).getName() + "      ------------    " +
                    this.menu.get(i).getPrice() + System.lineSeparator();
        }
        return menuStr.trim();
    }
}
