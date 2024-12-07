package timemanager.business.bao;

import timemanager.data.dao.Data;
import timemanager.data.dto.Account;
import timemanager.data.dao.DAOFactory;

public class SignInImpl implements SignIn {
    private Data data = new DAOFactory().data();

    // Check if the email exists in the "database"
    public byte checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return 0; // Email field is empty
        }

        Account account = data.getAccountByEmail(email);
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

        Account account = data.getAccountByEmail(email);
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
