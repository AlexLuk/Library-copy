package org.library.db.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_status")
public class ItemStatus extends Base {
    @Basic
    @Column(name = "name")
    private String name;

    public ItemStatus() {
    }

    public ItemStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
