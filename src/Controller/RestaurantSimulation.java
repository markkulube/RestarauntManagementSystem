package Controller;

import java.io.*;

public class RestaurantSimulation {

    public static Restaurant restaurant;
    
    public static void start() throws IOException, InterruptedException {

        File temp = new File("request.txt");
        if (temp.exists()) {
            RandomAccessFile raf = new RandomAccessFile(temp, "rw");
            raf.setLength(0);
        }

        File file = new File("menu");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String menuTxt = new String(data, "UTF-8");

        //BufferedReader inputFile = new BufferedReader(new FileReader("events"));

        restaurant = new Restaurant();
        restaurant.startRestaraunt();
    }

}


