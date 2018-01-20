package com.company.domain;

public class Administrator extends Entity {
    private String login;
    private String password;

    public Administrator() {
    }

    public Administrator(int id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return login;
    }
}
