package org.library.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre extends Base {
    @Basic
    @Column(name = "name")
    private String name;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
