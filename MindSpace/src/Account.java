import java.util.HashMap;

public class Account {

    public enum Gender {
        Male, Female
    }

    private String Name;
    private String Email;
    private String Password;
    private Gender Gender;

    HashMap<String, String> map = new HashMap<>();

    public void data() {
        map.put(Name, Email);
    }

}
