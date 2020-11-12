package registration;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static final Map<String, String> userToPassword = new HashMap<>();

    public static void addUser(String user, String password) {
        userToPassword.put(user, password);
    }

    public static boolean doesUserExist(String user) {
        return userToPassword.containsKey(user);
    }

    public static boolean doesPasswordRight(String user, String password) {
        return userToPassword.get(user).equals(password);
    }

}
