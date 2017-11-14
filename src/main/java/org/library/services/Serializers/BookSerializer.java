package org.library.services.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.library.db.domain.Book;

import java.io.IOException;
import java.util.stream.Collectors;

public class BookSerializer extends StdSerializer<Book> {
    public BookSerializer(Class<Book> t) {
        super(t);
    }

    public BookSerializer() {
        this(null);
    }

    @Override
    public void serialize(Book book, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("title",book.getTitle());
        jsonGenerator.writeStringField("authors",book.getAuthorsFullnames().stream().collect(Collectors.joining("\n")));
        jsonGenerator.writeNumberField("year",book.getYear());
        jsonGenerator.writeStringField("genre",book.getGenre().getName());
        jsonGenerator.writeNumberField("book_id",book.getId());
        jsonGenerator.writeEndObject();
    }
}