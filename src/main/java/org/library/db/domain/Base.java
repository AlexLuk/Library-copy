package org.library.db.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass
public class Base implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected int id;

    public int getId() { return id;}
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * Constructs full name
     *
     * @return - full name
     */
    public String getFullName(String firstName, String lastName, String patronimic) {
        StringBuilder name = new StringBuilder();
        name.append(firstName).append(" ");
        if (patronimic != null && !patronimic.equals("")) {
            name.append(patronimic).append(" ");
        }
        name.append(lastName).append(" ");
        return name.toString();
    }

    /**
     * Converts date to LocalDate object
     *
     * @return - converted date
     */
    public LocalDate convertLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
