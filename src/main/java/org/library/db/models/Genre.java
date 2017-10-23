package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre extends Base {
    private String name;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
