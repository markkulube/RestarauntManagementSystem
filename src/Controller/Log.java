package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A Log writes to log.txt
 */
public class Log {
  private File log = new File("log.txt");
  private FileWriter logWriter;
  
  Log() {
    boolean b = createLog();
    if (b)
      System.out.println("Log successfully created");
  }
  
  private boolean createLog() {
    boolean b = false;
    if(!log.exists()) {
      try{
        b = log.createNewFile();
      } catch (IOException e1) {
        System.out.println("Couldn't create log.txt");
      }
    }
    
    try{
      logWriter = new FileWriter(log);
    } catch (IOException e2) {
      System.out.println("Couldn't create FileWriter for log.txt");
    }
    return b;
  }
  
  public void writeLog(String s) {
    try {
      logWriter.write("[" + System.currentTimeMillis() + "]: " + s);
    } catch (IOException e1) {
      System.out.println("Could not write to log.txt");
    }
  }
 
}
