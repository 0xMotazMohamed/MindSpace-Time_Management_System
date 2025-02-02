package org.example.bao;

public interface SignIn {
    public byte checkEmail(String email);
    public byte checkPassword(String email, String password);
    public byte logIn(String email, String password);
}