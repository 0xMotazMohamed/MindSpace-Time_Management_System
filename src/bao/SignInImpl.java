package org.example.bao;

import org.example.data.dao.DAOFactory;
import org.example.data.dto.Account;

public class SignInImpl implements SignIn {
    private DAOFactory dao;

    public SignInImpl(DAOFactory daoFactory) {
        this.dao = daoFactory;
    }

    // Check if the email exists in the "database"
    public byte checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return 0; // Email field is empty
        }

        Account account = dao.getAccountByEmail(email);
        if (account == null) {
            return -1; // Email not found
        } else {
            return 1; // Email found
        }
    }

    public byte checkPassword(String email, String password) {
        if (password == null || password.isEmpty()) {
            return 0; // Password field is empty
        }

        Account account = dao.getAccountByEmail(email);
        if (account == null) {
            return 0; // Account not found, no need to check password
        } else if (account.getPassword().equals(password)) {
            return 1; // Password matches
        } else {
            return -1; // Password does not match
        }
    }

    public byte logIn(String email, String password) {
        byte emailCheck = checkEmail(email);
        byte passwordCheck = checkPassword(email, password);

        if (emailCheck == 0) return 10;  // Email field is empty
        if (emailCheck == -1) return 9;  // Email incorrect
        if (passwordCheck == 0) return 20;  // Password field is empty
        if (passwordCheck == -1) return 19; // Password incorrect

        return 0; // Successful login
    }
}
