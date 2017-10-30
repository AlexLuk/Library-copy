package org.library.db.models;

import javax.persistence.*;

@Entity
@NamedQueries
({
        @NamedQuery( name = Author.GET_BY_SURNAME,
                query = "SELECT a from Author a where a.surname like concat('%',:surname,'%') "),

        @NamedQuery( name = Author.GET_BY_NAME,
                query = "SELECT a from Author a where a.name like concat('%',:name,'%') "),

        @NamedQuery( name = Author.GET_BY_PATRONYMIC,
                query = "SELECT a from Author a where a.patronymic like concat('%',:patronymic,'%') "),

        @NamedQuery( name = Author.GET_BY_YEAR,
                query = "SELECT a from Author a where a.yearOfBirth = :year")

})
@Table(name = "author")
public class Author extends Base {
    public static final String GET_BY_SURNAME = "Author.getBySurname";
    public static final String GET_BY_NAME = "Author.getByName";
    public static final String GET_BY_PATRONYMIC = "Author.getByPatronymic";
    public static final String GET_BY_YEAR = "Author.getByYear";

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
