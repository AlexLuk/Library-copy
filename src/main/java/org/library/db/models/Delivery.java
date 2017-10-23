package org.library.db.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "delivery")
public class Delivery extends Base {
    private Reader reader;
    private Reader librarian;
    private Book book;
    private Timestamp time;
    private byte onHands;

    @ManyToOne
    @JoinColumn( name = "reader_id" )
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @ManyToOne
    @JoinColumn( name = "librarian_id" )
    public Reader getLibrarian() {
        return librarian;
    }
    public void setLibrarian(Reader librarian) {
        this.librarian = librarian;
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
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "on_hands")
    public byte getOnHands() {
        return onHands;
    }

    public void setOnHands(byte onHands) {
        this.onHands = onHands;
    }
}
