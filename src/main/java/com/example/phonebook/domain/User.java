package com.example.phonebook.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class User {
    private int id;
    private String userPhone;
    private String userName;
    private List<Entry> phoneBookList;

    public User() {

    }

    public User(int id, String userPhone, String userName) {
        this.id = id;
        this.userPhone = userPhone;
        this.userName = userName;
        this.phoneBookList = new ArrayList<>(Arrays.asList(
                new Entry(1, "George", "1234"),
                new Entry(2, "Daniel", "1221"),
                new Entry(3, "Anastasia", "1312")
        ));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<Entry> getPhoneBookList() {
        return phoneBookList;
    }

    public void setPhoneBookList(List<Entry> phoneBookList) {
        this.phoneBookList = phoneBookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userPhone, user.userPhone) && Objects.equals(userName, user.userName) && Objects.equals(phoneBookList, user.phoneBookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userPhone, userName, phoneBookList);
    }
}
