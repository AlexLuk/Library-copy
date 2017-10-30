package org.library.db.domain;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "delivery")
public class Delivery extends Base {
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_item_id")
    private BookItem bookItem;

    @Basic
    @Column(name = "time")
    private Timestamp time;

    public Delivery() {}

    public Delivery(Timestamp time) {
        this.time = time;
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

    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
