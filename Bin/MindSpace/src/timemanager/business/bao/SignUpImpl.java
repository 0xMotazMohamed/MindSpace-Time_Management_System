package timemanager.business.bao;

import timemanager.data.dao.DAOFactory;
import timemanager.data.dao.DataImpl;
import timemanager.data.dto.Account;

public class SignUpImpl implements SignUp {
    private DAOFactory dao;

    public SignUpImpl(DAOFactory daoFactory) {
        this.dao = daoFactory;
    }

    public byte checkUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return 0; // Username field is Empty
        }

        username = username.trim();

        if (username.length() < 5 || username.length() > 15) {
            return  -1; // Username must be between 5 and 15 characters long
        }

        if (!username.matches("^[a-zA-Z0-9]+$")) {
            return  -1; // Username can only contain alphanumeric characters
        }

        return 1;
    }

    public byte checkEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return 0; // Email field is Empty
        }

        email = email.trim().toLowerCase();

        if (!email.contains("@")) {
            return -1; // Email must contain '@'
        }

        if (!email.endsWith(".com")) {
            return -1; // Email must end with '.com'
        }

        if (dao.getAccountByEmail(email) != null) {
            if (email.equals(dao.getAccounts().get(email).getEmail())) {
                return -2; // Email is used for another account
            }
        }

        return 1;
    }

    public byte checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return 0; // Password field is Empty
        }

        if (password.length() < 8) {
            return  -1; // Password must be at least 8 characters long
        }

        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        if (!hasUppercase || !hasDigit) {
            return  -1; // Password must contain at least one uppercase letter and one digit
        }

        return 1;
    }

    public byte SignUp(String username, String email, String password) {
        byte usernameCheck = checkUsername(username);
        byte emailCheck = checkEmail(email);
        byte passwordCheck = checkPassword(password);

        if (usernameCheck == 0) return 10;  // Username field is empty
        if (usernameCheck == -1) return 9;  // Username incorrect
        if (emailCheck == 0) return 20;  // Email field is empty
        if (emailCheck == -1) return 19;  // Email incorrect
        if (emailCheck == -2) return 18;  // Email used
        if (passwordCheck == 0) return 30;  // Password field is empty
        if (passwordCheck == -1) return 29;  // Password incorrect

        dao.addAccount(new Account(username, email, password));

        return  0; //Successful Sign In
    }
}
