<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.library.db.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Author" %>

<div class="tab-pane fade show active" id="nav-find" role="tabpanel" aria-labelledby="nav-find-tab">
    <%@ include file="../filter.jsp" %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="title" /></th>
            <th><spring:message code="author" /></th>
            <th><spring:message code="year" /></th>
            <th><spring:message code="genre" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody id = "table_body">
        <%for (Book book : books) {
            List<Author> authors = libraryService.getAllAuthors(book.getId());
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
                <spring:message code="<%= libraryService.getGenre(book.getGenre().getId()) %>" />
            </td>
            <td>
                <input type="hidden" id="book_id" name="book_id" value="<%= book.getId()%>" />
                    <button type="submit" class="btn btn-primary" id="orderHands" name="orderHands">
                    <spring:message code="toOrderOnHands" />
                </button>
            </td>
            <td>
                <button type="submit" class="btn btn-primary" id="orderLib" name="orderLib">
                    <spring:message code="toOrderInLib" />
                </button>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
