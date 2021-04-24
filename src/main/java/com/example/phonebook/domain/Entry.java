package com.example.phonebook.domain;

import java.util.Objects;

public class Entry {
    private int idOfElem;
    private String name;
    private String number;

    public Entry() {

    }

    public Entry(int idOfElem, String name, String number) {
        this.idOfElem = idOfElem;
        this.name = name;
        this.number = number;
    }

    public int getIdOfElem() {
        return idOfElem;
    }

    public void setIdOfElem(int idOfElem) {
        this.idOfElem = idOfElem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry that = (Entry) o;
        return idOfElem == that.idOfElem && Objects.equals(name, that.name) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfElem, name, number);
    }

    @Override
    public String toString() {
        return "elemOfPhoneBook{" +
                "idOfElem=" + idOfElem +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

