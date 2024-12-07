package timemanager.data.dao;

import timemanager.data.dto.Account;

import java.util.HashMap;

public class DataImpl implements Data {
    private static HashMap<String, Account> accountsBase;
    static  {
        accountsBase = new HashMap<>();

        // Initialize with some sample data
        Account A = new Account("Abdullah Mostafa", "abdullah@gmail.com", "Programmer1", Account.Gender.Male);
        Account M = new Account("Moataz Mohamed", "moataz@gmail.com", "Programmer2", Account.Gender.Male);
        Account W = new Account("Youssef Wahba", "youssef@gmail.com", "Programmer3", Account.Gender.Male);

        accountsBase.put(A.getEmail(), A);
        accountsBase.put(M.getEmail(), M);
        accountsBase.put(W.getEmail(), W);



    }

    // Method to get account by email
    public Account getAccountByEmail(String email) {
        return accountsBase.get(email);
    }

    // Method to add account to the "database"
    public void addAccount(Account account) {
        accountsBase.put(account.getEmail(), account);
    }

    // Method to get all accounts
    public HashMap<String, Account> getAllAccounts() {
        return accountsBase;
    }
}
