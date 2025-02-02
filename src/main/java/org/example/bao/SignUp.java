package org.example.bao;

public interface SignUp {

    public byte checkUsername(String username);

    public byte checkEmail(String email);

    public byte checkPassword(String password);

    public byte SignUp(String username, String email, String password);
}
