<%@ page import="org.library.db.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Author" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Book> books = libraryService.getAllBooks();
    List<Genre> genres = libraryService.getAllGenres();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="findOrderBooks" /> - Library Online&trade;</title>
    <%@ include file="../templates/meta.jsp" %>
</head>

<body>
    <%@ include file="../templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <div id="status_message" class="bg-danger"></div>
            <%@ include file="filter.jsp" %>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><spring:message code="title" /></th>
                    <th><spring:message code="author" /></th>
                    <th><spring:message code="year" /></th>
                    <th><spring:message code="genre" /></th>
                    <th><spring:message code="rare" /></th>
                    <th><spring:message code="amount" /></th>
                    <th></th>
                </tr>
                </thead>
                <tbody class="content_res_book">
                    <%for (Book book : books) {
                        List<Author> authors = libraryService.getAllAuthors(book.getId());
                    %>
                        <tr>
                            <td><%= book.getTitle() %></td>
                            <td width="20%">
                                <%for (Author author : authors) {%>
                                <%= author.getFullName() %><br/>
                                <%}%>
                            </td>
                            <td><%= book.getYear() %></td>
                            <td>
                                <spring:message code="<%= libraryService.getGenre(book.getGenre().getId()) %>" />
                            </td>
                            <td><%= book.getIsRare() ? "yes" : "no" %></td>
                            <td><%= book.getAmount() %><br/></td>
                            <td>
                                <form method="post" class="doNotProcess">
                                    <button type="submit" class="btn btn-primary orderHands"
                                            id="orderHands_<%= book.getId()%>" name="orderHands_<%= book.getId()%>">
                                        <spring:message code="toOrderOnHands" />
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form method="post" class="doNotProcess">
                                    <button type="submit" class="btn btn-primary orderLib"
                                            id="orderLib_<%= book.getId()%>" name="orderLib_<%= book.getId()%>">
                                        <spring:message code="toOrderInLib" />
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </div>

        <div class="hidden">
            <div id="orderOnHandsForm">
                <form method="post" class="doNotProcess">
                    <button type="submit" class="btn btn-primary orderHands" id="orderHands" name="orderHands">
                        <spring:message code="toOrderOnHands" />
                    </button>
                </form>
            </div>
            <div id="orderInLibForm">
                <form method="post" class="doNotProcess">
                    <button type="submit" class="btn btn-primary orderLib" id="orderLib" name="orderLib">
                        <spring:message code="toOrderInLib" />
                    </button>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
