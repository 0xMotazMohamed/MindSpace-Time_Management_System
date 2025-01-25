package org.example.data.dao;

public class AccountDAOFactory {

    public AccountDAO getAccountDAO() {
        return AccountDAOImpl.getInstance();
    }

}
