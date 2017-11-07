package org.library.db.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reader")
public class Reader extends Base {
    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

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
    @Column(name = "registration_date")
    private Date registrationDate;

    @Basic
    @Column(name = "fines")
    private Double fines;

    @Basic
    @Column(name = "is_admin")
    private boolean isAdmin;

    public Reader() {}

    public Reader(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Reader(Reader reader) {
        this.id = reader.getId();
        this.email = reader.getEmail();
        this.password = reader.getPassword();
        this.firstName = reader.getFirstName();
        this.lastName = reader.getLastName();
        this.patronymic = reader.getPatronymic();
        this.registrationDate = reader.getRegistrationDate();
        this.fines = reader.getFines();
        this.isAdmin = reader.getIsAdmin();
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
