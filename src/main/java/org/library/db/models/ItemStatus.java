package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "item_status")
public class ItemStatus extends Base {
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
