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

    //load accounts from file
    public static void loadAccounts() {
        try(Reader reader = new FileReader(DATA_FILE)) {
            Type accountListType = new TypeToken<List<Account>>() {}.getType();
            accounts = GSON.fromJson(reader, accountListType);
            if (accounts == null) accounts = new HashMap<>();
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAccounts() {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            GSON.toJson(accounts, writer);
        } catch (IOException e) {
            e.printStackTrace();
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
