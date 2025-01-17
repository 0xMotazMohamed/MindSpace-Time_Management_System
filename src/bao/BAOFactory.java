package org.example.bao;

import org.example.bao.SignInImpl;
import org.example.bao.SignUpImpl;
import org.example.data.dao.DAOFactory;

public class BAOFactory {
    private DAOFactory daoFactory;

    public BAOFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public DAOFactory getDaoFactory() {
        return  this.daoFactory;
    }

    public SignInImpl getSignIn () {
        return new SignInImpl(daoFactory);
    }

    public SignUpImpl getSignUp() {
        return new SignUpImpl(daoFactory);
    }
}
