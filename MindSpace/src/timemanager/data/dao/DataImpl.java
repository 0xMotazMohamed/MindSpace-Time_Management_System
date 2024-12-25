package timemanager.data.dao;

import timemanager.data.dto.Account;
import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;

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

        Project p1 = new Project("Project 1", "Description of Project 1");
                p1.addTask(new Task("task 1"), p1.getToDoTasks());
                p1.addTask(new Task("task 2"), p1.getInProgressTasks());
                p1.addTask(new Task("task 3"), p1.getCompletedTasks());
        A.addProject(p1);




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
