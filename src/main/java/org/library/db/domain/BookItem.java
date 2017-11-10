package org.library.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "book_item")
public class BookItem extends Base {
    @Basic
    @Column(name = "itemCode")
    private Integer itemCode;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemStatus status;

    public BookItem(int itemId, Book book, ItemStatus itemStatus) {
        this.id = itemId;
        this.book = book;
        this.status = itemStatus;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
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
