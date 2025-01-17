package timemanager.data.dao;

import timemanager.data.dto.Account;

import java.util.HashMap;

public class DAOFactory {

//    private Data data;

//    public DAOFactory() {
//        this.data = new DataImpl();
//    }
//
//    public DataImpl getData() {
//        return  DataImpl;
//    }
    public void addAccount(Account account) {
        DataImpl.addAccount(account);
    }
    public Account getAccountByEmail(String email) {
        return DataImpl.getAccountByEmail(email);
    }

    public HashMap<String, Account> getAccounts() {
        return DataImpl.getAccounts();
    }
}
