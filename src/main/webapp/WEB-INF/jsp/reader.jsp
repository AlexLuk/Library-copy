<%@ page import="org.library.db.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.BookService" %>
<%@ page import="org.library.db.domain.Author" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
    BookService bookService = (BookService) request.getAttribute("book_service");
    List<Book> books = bookService.getAllBooks();
%>

<div class="container">
    <div class="jumbotron">

        <nav class="nav nav-tabs" id="readerTab" role="tablist">
            <a class="nav-item nav-link active" id="nav-find-tab" data-toggle="tab" href="#nav-find" role="tab"
               aria-controls="nav-find" aria-selected="true">Find/Order books</a>
            <a class="nav-item nav-link" id="nav-return-tab" data-toggle="tab" href="#nav-return" role="tab"
               aria-controls="nav-return" aria-selected="false">Return books</a>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-find" role="tabpanel" aria-labelledby="nav-find-tab">
                <%@ include file="components/filter.jsp" %>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Authors</th>
                        <th>Year</th>
                        <th>Genre</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%for (Book book : books) {
                        List<Author> authors = bookService.getAllAuthors(book.getId());
                    %>
                    <tr>
                        <td><%= book.getTitle() %></td>
                        <td>
                            <%for (Author author : authors) {%>
                            <%= author.getFullName() %><br/>
                            <%}%>
                        </td>
                        <td><%= book.getYear() %></td>
                        <td><%= bookService.getGenre(book.getGenre().getId()) %></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane fade" id="nav-return" role="tabpanel" aria-labelledby="nav-return-tab">
                Return books...
            </div>
        </div>
    </div>
</div>
