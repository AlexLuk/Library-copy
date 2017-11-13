package org.library.db.domain;

import com.google.common.collect.Lists;
import org.library.misc.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author extends Base {
    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "patronymic")
    private String patronymic;

    @Basic
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @ManyToMany(mappedBy = "authors")
    @OrderBy("title")
    private List<Book> books = new ArrayList<>();

    public Author(){
    }

    public Author(String firstName, String patronymic, String lastName, int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return Utils.getFullName(getLastName(), getFirstName(), getPatronymic());
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
