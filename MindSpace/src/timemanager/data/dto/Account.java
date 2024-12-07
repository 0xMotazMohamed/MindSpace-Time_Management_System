package timemanager.data.dto;


import java.util.HashMap;

public class Account {

    public enum Gender {
        Male, Female
    }

    private String name;
    private String email;
    private String password;
    private Gender gender;
    private static HashMap<String, Account> accounts = new HashMap<>();

    public Account(String name, String email, String password, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(HashMap<String, Account> accounts) {
        Account.accounts = accounts;
    }
}
