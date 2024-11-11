package StartUp;

import java.util.HashMap;

public class Data {

    private static HashMap<String, String> map;

    public static void setMap() {

        Account A = new Account("Abdullah Mostafa", "abdullah@gmail.com", "Programmer1", Account.Gender.Male);
        Account M = new Account("Moataz Mohamed", "moataz@gmail.com", "Programmer2", Account.Gender.Male);
        Account W = new Account("Youssef Wahba", "youssef@gmail.com", "Programmer3", Account.Gender.Male);

        map = new HashMap<>();

        map.put(A.getEmail(), A.getPassword());
        map.put(M.getEmail(), M.getPassword());
        map.put(W.getEmail(), W.getPassword());
    }

    public static HashMap<String, String> getMap() {
        return map;
    }
}
