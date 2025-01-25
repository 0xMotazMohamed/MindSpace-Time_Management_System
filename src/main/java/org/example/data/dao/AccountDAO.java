package org.example.data.dao;

import org.example.data.dto.Account;

import java.util.HashMap;

public interface AccountDAO {
    public void addAccount(Account account);
    public void updateAccount(Account account);
    public void deleteAccount(String email);
    public Account getAccountByEmail(String email);
    public HashMap<String, Account> getAccounts();

}
