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
                p1.addTask(new Task("task 10"), p1.getToDoTasks());
                p1.addTask(new Task("task 11"), p1.getToDoTasks());
                p1.addTask(new Task("task 12"), p1.getToDoTasks());
        A.addProject(p1);
        Project p2 = new Project("Project 2", "aaaaaaaaaaaaaaaaaaaaa\n" +
                "bbbbbbbbbbbbbbbbbbbbb\n" +
                "cccccccccccccccccccccc\n" +
                "ddddddddddddddddddddd\n" +
                "eeeeeeeeeeeeeeeeeeeee\n" +
                "ffffffffffffffffffffffffffffffffffffffff\n" +
                "ggggggggggggggggggggg");
        p2.addTask(new Task("task 1"), p1.getToDoTasks());
        p2.addTask(new Task("task 20"), p1.getInProgressTasks());
        p2.addTask(new Task("task 21"), p1.getInProgressTasks());
        p2.addTask(new Task("task 22"), p1.getInProgressTasks());
        A.addProject(p2);
        Project p3 = new Project("Project 3", "aaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccc\n" +
                "dddddddddddddddddddddeeeeeeeeeeeeeeeeeeeeeffffffffffffffffffffffffffffffffffffffff\n" +
                "ggggggggggggggggggggg");
        p3.addTask(new Task("task 1"), p3.getToDoTasks());
        p3.addTask(new Task("task 2"), p3.getInProgressTasks());
        p3.addTask(new Task("task 30"), p3.getCompletedTasks());
        p3.addTask(new Task("task 31"), p3.getCompletedTasks());
        p3.addTask(new Task("task 32"), p3.getCompletedTasks());
        A.addProject(p3);




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
