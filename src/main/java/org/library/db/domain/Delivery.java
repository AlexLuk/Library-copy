package org.library.db.domain;

import org.library.misc.Utils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "delivery")
public class Delivery extends Base {
    @ManyToOne
    @JoinColumn(name = "reader")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_item")
    private BookItem bookItem;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date time;

    public Delivery(Timestamp time) {
        this.time = time;
    }

    public Delivery(Reader reader, BookItem bookItem) {
        this.reader = reader;
        this.bookItem = bookItem;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public BookItem getBookItem() {
        return bookItem;
    }
    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public LocalDate convertLocalDate() {
        return Utils.convertLocalDate(time);
    }
}
