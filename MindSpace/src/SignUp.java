public class SignUp {
    public static String checkUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        username = username.trim();

        if (username.length() < 5 || username.length() > 15) {
            throw new IllegalArgumentException("Username must be between 5 and 15 characters long.");
        }

        if (!username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username can only contain alphanumeric characters.");
        }

        return username;
    }

    public static String checkEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        email = email.trim().toLowerCase();

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'.");
        }

        if (!email.endsWith(".com")) {
            throw new IllegalArgumentException("Email must end with '.com'.");
        }

        return email;
    }
    public static String checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        if (!hasUppercase || !hasDigit) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter and one digit.");
        }

        return password;
    }

}
