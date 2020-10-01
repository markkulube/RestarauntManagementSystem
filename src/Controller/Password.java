package Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Password {

    private static ArrayList<Map> passwordList;
    private static String allPassword;


    public Password() {
        this.passwordList = new ArrayList<>();
        this.allPassword = "1, m, m1  | 2, m, m2 | 1, g, g1 | 2, g, g2 | 1, s, s1 | 2, s, s2 | " +
                "1, c, c1 | 2, c, c2 | 3, m, m3  | 4, m, m4 | 3, g, g3 | 4, g, g4 | 3, s, s3 | 4, s, s4 | 3, c, c3 | 4, c, c4";
    }

    public void passWordDataBase() {
        String[] passwordStage1 = allPassword.split("\\|");

        for (int i = 0; i < passwordStage1.length; i++) {
            String[] passwordStage2 = passwordStage1[i].trim().split(",");
            String id = passwordStage2[0].trim();
            String username = passwordStage2[1].trim();
            String password = passwordStage2[2].trim();

            Map passwordMap = new HashMap();

            passwordMap.put("id", id);
            passwordMap.put("username", username);
            passwordMap.put("password", password);

            passwordList.add(passwordMap);
        }
    }

    public static boolean checkPassword(String loginInfo) {
        String[] passwordStage1 = loginInfo.split(",");
        String id = passwordStage1[0].trim();
        String username = passwordStage1[1].trim();
        String password = passwordStage1[2].trim();

        for (int i = 0; i <passwordList.size() ; i++) {
            Map tempPassWordMap = passwordList.get(i);
            if((tempPassWordMap.get("id").equals(id) &&  tempPassWordMap.get("username").equals(username)
                    && tempPassWordMap.get("password").equals(password))) {
                return true;
            }
        }
        return false;
    }
}
