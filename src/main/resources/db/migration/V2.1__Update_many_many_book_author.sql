ALTER TABLE author_book CHANGE COLUMN author_id author INT(11);
ALTER TABLE author_book CHANGE COLUMN book_id book INT(11);

ALTER TABLE author_book
  ADD FOREIGN KEY (author) REFERENCES author(id),
  ADD FOREIGN KEY (book) REFERENCES book(id);


ALTER TABLE book CHANGE COLUMN shelf_id shelf_code VARCHAR(30);
ALTER TABLE book CHANGE COLUMN genre_id genre INT(11);

ALTER TABLE book
  ADD FOREIGN KEY (genre) REFERENCES genre(id);


ALTER TABLE book_item CHANGE COLUMN item_id item_code INT(11);
ALTER TABLE book_item CHANGE COLUMN book_id book INT(11);
ALTER TABLE book_item CHANGE COLUMN status_id status INT(11);

ALTER TABLE book_item
  ADD UNIQUE (book, item_code),
  ADD FOREIGN KEY (book) REFERENCES book(id),
  ADD FOREIGN KEY (status) REFERENCES item_status(id);


ALTER TABLE book_order CHANGE COLUMN reader_id reader INT(11);
ALTER TABLE book_order CHANGE COLUMN book_id book INT(11);

ALTER TABLE book_order
  ADD FOREIGN KEY (reader) REFERENCES reader(id),
  ADD FOREIGN KEY (book) REFERENCES book(id);


ALTER TABLE delivery CHANGE COLUMN reader_id reader INT(11);
ALTER TABLE delivery CHANGE COLUMN book_item_id book_item INT(11);

ALTER TABLE delivery
  ADD FOREIGN KEY (reader) REFERENCES reader(id),
  ADD FOREIGN KEY (book_item) REFERENCES book_item(id);

