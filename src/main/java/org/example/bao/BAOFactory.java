package org.example.bao;

import org.example.data.dao.AccountDAOFactory;

public class BAOFactory {
    private AccountDAOFactory accountDaoFactory;

    public BAOFactory(AccountDAOFactory accountDaoFactory) {
        this.accountDaoFactory = accountDaoFactory;
    }

    public AccountDAOFactory getDaoFactory() {
        return  this.accountDaoFactory;
    }

    public SignInImpl getSignIn () {
        return new SignInImpl(accountDaoFactory);
    }

    public SignUpImpl getSignUp() {
        return new SignUpImpl(accountDaoFactory);
    }
}
