package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order extends Base {
    private int id;
    private Reader reader;
    private Book book;
    private byte onHands;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn( name = "reader_id" )
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @ManyToOne
    @JoinColumn( name = "book_id" )
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    @Basic
    @Column(name = "on_hands")
    public byte getOnHands() {
        return onHands;
    }

    public void setOnHands(byte onHands) {
        this.onHands = onHands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (id != that.id) return false;
        if (onHands != that.onHands) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) onHands;
        return result;
    }
}
