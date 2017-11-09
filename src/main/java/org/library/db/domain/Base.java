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
    protected Integer id;

    public Integer getId() { return id;}
    public void setId( Integer id )
    {
        this.id = id;
    }
}
