package StartUp;

public interface SignUpInterface {

    /**
     * returns: 0 if (empty)
     * returns: -1 if (not between 5-15 characters long) or (contain not ahphanumeric character)
     * returns: 1 if (accepted)
     */
    public byte checkUsername(String username);


    /**
     * returns: 0 if (empty)
     * returns: -1 if (not contain @, .com)
     * returns: 1 if (accepted)
     */
    public byte checkEmail(String email);


    /**
     * returns: 0 if (empty)
     * returns: -1 if (less than 8 characters long) or (not contain uppercase letter and digit)
     * returns: 1 if (accepted)
     */
    public byte checkPassword(String password);



    public byte SignUp(String username, String email, String password);
}
