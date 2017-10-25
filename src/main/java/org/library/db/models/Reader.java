package org.library.db.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reader")
public class Reader extends Base {
    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

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
    @Column(name = "passport")
    private String passport;

    @Basic
    @Column(name = "registration_date")
    private Date registrationDate;

    @Basic
    @Column(name = "fines")
    private Double fines;

    @Basic
    @Column(name = "is_admin")
    private boolean isAdmin;

    public Reader() {}

    public Reader(String login, String password) {
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

    public String getPassport() {
        return passport;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getFines() {
        return fines;
    }
    public void setFines(Double fines) {
        this.fines = fines;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
