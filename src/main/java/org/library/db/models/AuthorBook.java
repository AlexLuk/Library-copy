package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "author_book")
public class AuthorBook extends Base {
    private Author authorId;
    private Book bookId;

    @ManyToOne
    @JoinColumn( name = "author_id" )
    public Author getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    @ManyToOne
    @JoinColumn( name = "book_id" )
    public Book getBookId() {
        return bookId;
    }
    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
