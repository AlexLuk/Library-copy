package org.library.db.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reader")
public class Reader extends Base {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String passport;
    private Date registrationDate;
    private Double fines;
    private Byte isAdmin;

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "passport")
    public String getPassport() {
        return passport;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Basic
    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "fines")
    public Double getFines() {
        return fines;
    }
    public void setFines(Double fines) {
        this.fines = fines;
    }

    @Basic
    @Column(name = "is_admin")
    public Byte getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }
}
