package StartUp;

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
    public static void registerAccount(String name, String email, String password, Gender gender) {
        if (accounts.containsKey(email)) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }
        Account account = new Account(name, email, password, gender);
        account.name= SignUp.checkUsername(account.name);
        account.email= SignUp.checkEmail(account.email);
        account.password= SignUp.checkPassword(account.password);
        accounts.put(email, account);
    }
    public static Account getAccountByEmail(String email) {
        return accounts.get(email);
    }

    public String getEmail() {return email;}
    public String getPassword() {return password;}
}
