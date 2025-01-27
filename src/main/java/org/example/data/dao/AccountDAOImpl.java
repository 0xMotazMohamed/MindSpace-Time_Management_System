package org.example.data.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.data.dto.Account;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AccountDAOImpl implements AccountDAO {
    private static final String APPDATA_PATH = System.getenv("APPDATA") + File.separator + ".MindSpace";
    private static final String LOCAL_JSON_PATH = APPDATA_PATH + File.separator + "accounts.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static HashMap<String, Account> accounts = new HashMap<>();

    // Singleton Pattern: To be sure we have only one instance deals with accounts.json file
    private static final AccountDAOImpl instance = new AccountDAOImpl();
    private AccountDAOImpl() {
        initializeDirectoryAndFile();
    }
    public static AccountDAOImpl getInstance() {
        return instance;
    }

    // Ensure json file exist in Appdata dir
    private void initializeDirectoryAndFile() {
        try {
            // Create dir
            Files.createDirectories(Paths.get(APPDATA_PATH));
            // Create JSON file
            File jsonFile = new File(LOCAL_JSON_PATH);
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
                try (FileWriter writer = new FileWriter(jsonFile)){
                    GSON.toJson(new HashMap<String, Account>(), writer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error initializing dir / file" + e.getMessage());
        }
    }

    //load accounts from file if not exist or empty, initialize new one
    public void loadAccounts() {
        try (FileReader reader = new FileReader(LOCAL_JSON_PATH)) {
            Type accountMapType = new TypeToken<HashMap<String, Account>>() {}.getType();
            accounts = GSON.fromJson(reader, accountMapType);
            if (accounts == null) accounts = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error reading accounts from file:" + e.getMessage());
            accounts = new HashMap<>();
        }
    }

    public void saveAccounts() {
        try (FileWriter writer = new FileWriter(LOCAL_JSON_PATH)) {
            GSON.toJson(accounts, writer);
            System.out.println("accounts saved");
        } catch (IOException e) {
            System.err.println("Error saving accounts to file:" + e.getMessage());
        }
    }

    @Override
    public void addAccount(Account account) {
        accounts.put(account.getEmail(), account);
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(String email) {
        if (accounts.remove(email) != null) {
            System.out.println("account removed");
        }
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accounts.get(email);
    }

    @Override
    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    @Override
    public void updateData() {
        GitHubDownloader.getInstance().download();
        AccountDAOImpl.getInstance().saveAccounts();
        GitHubUploader.getInstance().upload();
    }
}
