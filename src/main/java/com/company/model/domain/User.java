package com.company.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Entity{
    private String login;
    private String password;
    private String name;
    private String mail;
    private String phoneNumber;
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    public User(int id, String login, String password,
                String name, String mail, String phoneNumber) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass() ) {
            return false;
        }

        User user = (User) obj;
        return user.getLogin().equals(login)
                && user.getPassword().equals(password)
                && user.getName().equals(name)
                && user.getMail().equals(mail)
                && user.getPhoneNumber().equals(phoneNumber)
                && Objects.equals(user.getId(), getId());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (login == null ? 0 : login.hashCode());
        result = 31 * result + (password == null ? 0 : password.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (mail == null ? 0 : mail.hashCode());
        result = 31 * result + (phoneNumber == null ? 0 : phoneNumber.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return login;
    }
}
