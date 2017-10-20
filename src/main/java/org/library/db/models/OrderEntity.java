package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class OrderEntity {
    private int id;
    private ReaderEntity reader;
    private BookEntity book;
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
    public ReaderEntity getReader() {
        return reader;
    }
    public void setReader(ReaderEntity reader) {
        this.reader = reader;
    }

    @ManyToOne
    @JoinColumn( name = "book_id" )
    public BookEntity getBook() {
        return book;
    }
    public void setBook(BookEntity book) {
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

        OrderEntity that = (OrderEntity) o;

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
