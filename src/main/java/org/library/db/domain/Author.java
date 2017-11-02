package org.library.db.domain;

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
    @Column(name = "year_of_birth")
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

    public String getFullName() {
        StringBuilder name = new StringBuilder();
        name.append(getName()).append(" ");
        if (!getPatronymic().equals("")) {
            name.append(getPatronymic()).append(" ");
        }
        name.append(getSurname()).append(" ");
        return name.toString();
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
