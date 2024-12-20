package timemanager.business.bao;

import timemanager.data.dao.DAOFactory;
import timemanager.data.dao.Data;

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
