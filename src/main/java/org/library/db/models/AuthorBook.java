package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "author_book")
public class AuthorBook extends Base {
    @ManyToOne
    @JoinColumn(name = "author_id" )
    private Author authorId;

    @ManyToOne
    @JoinColumn(name = "book_id" )
    private Book bookId;

    public Author getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public Book getBookId() {
        return bookId;
    }
    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
