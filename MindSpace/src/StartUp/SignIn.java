package StartUp;

public class SignIn {
    private static byte checkEmail(String email) {
        if (email.isEmpty()) {
            return 0;
        }else if (Data.getMap().containsKey(email)) {
            return 1;
        }else {
            return -1;
        }
    }

    private static byte checkPassword(String email, String password) {
        if (password.isEmpty()) {
            return 0;
        }else if (Data.getMap().containsKey(email) && Data.getMap().get(email).equals(password)) {
            return 1;
        }else {
            return -1;
        }
    }

    public static byte logIn(String email, String password) {
        byte e = checkEmail(email);
        byte p = checkPassword(email, password);
        if (e == 0) return 10;
        else if (p == 0) return 20;
        else if (e == -1) return 9;
        else if (p == -1) return 19;
        else return 0;
    }
}