package com.bbdigital.bbecommerce.Models;

public class Credentials {

    private String login;
    private char[] password;

    public Credentials() {
        super();
    }

    public Credentials(String login, char[] password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}

