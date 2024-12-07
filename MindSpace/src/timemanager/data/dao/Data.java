package timemanager.data.dao;

import timemanager.data.dto.Account;
import java.util.HashMap;

public interface Data {
    Account getAccountByEmail(String email);
    void addAccount(Account account);
    HashMap<String, Account> getAllAccounts();
}
