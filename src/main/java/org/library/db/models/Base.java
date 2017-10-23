package org.library.db.models;

import javax.persistence.*;

@MappedSuperclass
public class Base
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    public int getId() { return id;}
    public void setId( int id )
    {
        this.id = id;
    }
}
