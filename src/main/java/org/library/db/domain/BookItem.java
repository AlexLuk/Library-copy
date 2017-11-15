package org.library.db.domain;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "book_item")
public class BookItem extends Base {
    @Basic
    @Column(name = "itemCode")
    private Integer itemCode;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    @Enumerated(EnumType.ORDINAL)
    private ItemStatus status;

    public BookItem() {
    }

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

    public enum ItemStatus {
        EMPTY (new HashMap<String, String>()
        {{
            put("", "");
            put("", "");
        }}),
        FREE (new HashMap<String, String>()
        {{
            put("en", "free");
            put("ru", "свободна");
            put("db", "free");
        }}),
        ON_HANDS (new HashMap<String, String>()
        {{
            put("en", "on hands");
            put("ru", "на руках");
            put("db", "on_hands");
        }}),
        IN_STUDY (new HashMap<String, String>()
        {{
            put("en", "in library");
            put("ru", "в читальном зале");
            put("db", "in_study");
        }});

        private final HashMap<String, String> vals;

        ItemStatus (HashMap<String, String> vals) {
            this.vals = vals;
        }

        public HashMap<String, String> getVals() {
            return vals;
        }
    }
}
