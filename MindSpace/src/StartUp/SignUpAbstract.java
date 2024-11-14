package StartUp;

public class SignUpAbstract implements SignUpInterface{

    /**
     * returns: 0 if (empty)
     * returns: -1 if (not between 5-15 characters long) or (contain not ahphanumeric character)
     * returns: 1 if (accepted)
     */
    public byte checkUsername(String username){return 0;}


    /**
     * returns: 0 if (empty)
     * returns: -1 if (not contain @, .com)
     * returns: 1 if (accepted)
     */
    public byte checkEmail(String email) {return 0;}


    /**
     * returns: 0 if (empty)
     * returns: -1 if (less than 8 characters long) or (not contain uppercase letter and digit)
     * returns: 1 if (accepted)
     */
    public byte checkPassword(String password) {return 0;}



    public byte SignUp(String username, String email, String password) {
        byte u = checkUsername(username);
        byte e = checkEmail(email);
        byte p = checkPassword(password);
        if (u == 0) return 10;
        else if (e == 0) return 20;
        else if (p == 0) return 30;
        else if (u == -1) return 9;
        else if (e == -1) return 19;
        else if (p == -1) return 29;
        else return 0;
    }
}
