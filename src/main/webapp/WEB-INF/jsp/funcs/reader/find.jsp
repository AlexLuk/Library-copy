<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.library.db.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.BookService" %>
<%@ page import="org.library.db.domain.Author" %>

<%
    BookService bookService = (BookService) request.getAttribute("book_service");
    List<Book> books = bookService.getAllBooks();
%>

<div class="tab-pane fade show active" id="nav-find" role="tabpanel" aria-labelledby="nav-find-tab">
    <%@ include file="../filter.jsp" %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="title" /></th>
            <th><spring:message code="author" /></th>
            <th><spring:message code="year" /></th>
            <th><spring:message code="genre" /></th>
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
            <td>
                <spring:message code="<%= bookService.getGenre(book.getGenre().getId()) %>" />
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
