package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order extends Base {
    private Byte onHands;
    private Reader reader;
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @ManyToOne
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    @Basic
    @Column(name = "on_hands")
    public Byte getOnHands() {
        return onHands;
    }
    public void setOnHands(Byte onHands) {
        this.onHands = onHands;
    }
}
