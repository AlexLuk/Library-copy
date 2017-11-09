package org.library.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "book_item")
public class BookItem extends Base {
    @Basic
    @Column(name = "item_id")
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ItemStatus status;

    public BookItem() {
    }

    public BookItem(int itemId, Book book, ItemStatus itemStatus) {
        this.itemId = itemId;
        this.book = book;
        this.status = itemStatus;
    }

    public BookItem(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
