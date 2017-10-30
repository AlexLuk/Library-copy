package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "genre")
@NamedQueries
({
    @NamedQuery( name = Genre.GET_BY_GENRE,
        query = "SELECT a from Genre a where a.name like concat('%',:genre,'%')" )
})

public class Genre extends Base {
    public static final String GET_BY_GENRE = "Author.getByGenre";

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
