package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author extends Base {
    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "surname")
    private String surname;

    @Basic
    @Column(name = "patronymic")
    private String patronymic;

    @Basic
    @Column(name = "yearOfBirth")
    private int yearOfBirth;

    public Author(){}

    public Author(String name, String surname, String patronymic, int yearOfBirth) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
