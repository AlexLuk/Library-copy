package org.library.db.domain;

import javax.persistence.*;
import java.io.Serializable;

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
}
