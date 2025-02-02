package org.example.data.dao;

import org.example.data.dto.Account;

import java.util.HashMap;

public interface AccountDAO {
    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(String email);
    Account getAccountByEmail(String email);
    HashMap<String, Account> getAccounts();
    void updateData();

}
