package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "book_item")
public class BookItem extends Base {
    private Book book;
    private Integer itemId;
    private ItemStatus status;

    @ManyToOne
    @JoinColumn( name = "book_id" )
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    @Basic
    @Column(name = "item_id")
    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @ManyToOne
    @JoinColumn (name = "status_id")
    public ItemStatus getStatus() {
        return status;
    }
    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
