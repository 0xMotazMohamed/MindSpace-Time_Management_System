package timemanager.data.dao;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import timemanager.data.dto.Account;
import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class DataImpl {
    public static final String DATA_FILE = "accounts.json";
    public static final Gson GSON = new Gson();
    public static HashMap<String, Account> accounts = new HashMap<>();

    static {
        loadAccounts();
    }

    //load accounts from file if not exist or empty, initialize new one
    public static void loadAccounts() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (Reader reader = new FileReader(DATA_FILE)) {
                Type accountMapType = new TypeToken<HashMap<String, Account>>() {}.getType();
                accounts = GSON.fromJson(reader, accountMapType);
                if (accounts == null) accounts = new HashMap<>();
            } catch (IOException e) {
                System.err.println("Error reading accounts from file:" + e.getMessage());
                accounts = new HashMap<>();
            }
        } else {
            System.out.println("No saved data found. Starting fresh.");
            accounts = new HashMap<>();
        }
    }

    public static void saveAccounts() {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            GSON.toJson(accounts, writer);
        } catch (IOException e) {
            System.err.println("Error saving accounts to file:" + e.getMessage());
        }
    }

    public static void addAccount(Account account) {
        accounts.put(account.getEmail(), account);
        saveAccounts();
    }

    public static Account getAccountByEmail(String email) {
        return accounts.get(email);
    }

    public static HashMap<String, Account> getAccounts() {
        return accounts;
    }
}
