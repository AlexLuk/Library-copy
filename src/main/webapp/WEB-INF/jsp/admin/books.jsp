<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.db.domain.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Author> authors = libraryService.getAllAuthors();
    List<Book> books = libraryService.getAllBooks();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="addBooks" /> - Library Online&trade;</title>
    <%@ include file="../templates/meta.jsp" %>
</head>

<body>
    <%@ include file="../templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <div id="status_message" class="bg-danger"></div>
            <div class="row">
                <div class="col-md-4">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><spring:message code="title" /></th>
                        </tr>
                        </thead>
                        <tbody>
                            <%for (Book book : books) {%>
                                <tr>
                                    <td><%= book.getTitle() %></td>
                                    <td>
                                        <form method="post" class="doNotProcess">
                                            <button type="submit" class="btn btn-primary"
                                                    id="edit_<%= book.getId()%>" name="edit_<%= book.getId()%>">
                                                <spring:message code="toEdit" />
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1"></div>
                <br/><br/>
                <div class="col-md-7">
                    <div class="editForm">
                        <form id="chooseBookForm" class="doNotProcess" method="post">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-5">
                                        <label for="title"><b><spring:message code="title"/>:</b></label>
                                        <input type="text" class="form-control" name="title"
                                               id="title" value=""><br/>

                                        <label for="amount"><b><spring:message code="amount"/>:</b></label>
                                        <input type="text" class="form-control" name="amount"
                                               id="amount" value=""><br/>

                                        <label for="shelf_code"><b><spring:message code="shelfCode"/>:</b></label>
                                        <input type="text" class="form-control" name="shelf_code"
                                               id="shelf_code" value=""><br/>
                                    </div>
                                    <div class="delimeter"></div>
                                    <div class="col-xs-5">
                                        <label for="language"><b><spring:message code="language"/>:</b></label>
                                        <input type="text" class="form-control" name="language"
                                               id="language" value=""><br/>

                                        <label for="year"><b><spring:message code="year"/>:</b></label>
                                        <input type="text" class="form-control" name="year"
                                               id="year" value=""><br/>

                                        <label for="is_rare"><b><spring:message code="rare"/>:</b></label>
                                        <input type="checkbox" class="form-control" name="is_rare"
                                               id="is_rare" checked value=""><br/>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-5">
                                        <button type="submit" class="btn btn-primary" id="save" name="save">
                                            <spring:message code="toSave" />
                                        </button>
                                    </div>
                                    <div class="delimeter"></div>
                                    <div class="col-xs-5">
                                        <button type="submit" class="btn btn-primary" id="delete" name="delete">
                                            <spring:message code="toDelete" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <br/><br/>
                        <form id="deleteAuthors" class="doNotProcess" method="post">
                            <div class="row">
                                <div class="col-md-4">
                                    <label for="choose_book_authors"><spring:message code="addAuthors" />:</label>
                                    <select class="form-control" name="choose_book_authors" id="choose_book_authors">
                                    </select>
                                </div>
                                <div class="delimeter"></div>
                                <div class="col-md-8 scaled">
                                    <button type="submit" class="btn btn-primary" id="deleteAuthor" name="deleteAuthor">
                                        <spring:message code="toDelete" />
                                    </button>
                                </div>
                            </div>
                        </form>
                        <br/>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th><spring:message code="addAuthor" /></th>
                            </tr>
                            </thead>
                            <tbody>
                                <%for (Author author : authors) {%>
                                    <tr>
                                        <td><%= author.getFullName() %></td>
                                        <td>
                                            <form method="post" class="doNotProcess">
                                                <button type="submit" class="btn btn-primary"
                                                        id="add_<%= author.getId()%>" name="add_<%= author.getId()%>">
                                                    <spring:message code="toAdd" />
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
