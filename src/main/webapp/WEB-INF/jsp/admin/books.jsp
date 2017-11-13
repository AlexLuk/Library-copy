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
            <button type="submit" class="btn btn-primary"
                    id="addNewBook" name="addNewBook"
                    data-toggle="modal" data-target="#popupWindow">
                <spring:message code="toAddNewBook" />
            </button>
            <hr class="my-4">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sx-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><spring:message code="title" /></th>
                            <th><spring:message code="author" /></th>
                            <th><spring:message code="year" /></th>
                            <th><spring:message code="language" /></th>
                            <th><spring:message code="shelfCode" /></th>
                            <th><spring:message code="amount" /></th>
                        </tr>
                        </thead>
                        <tbody>
                            <%for (Book book : books) {%>
                                <tr>
                                    <td><%= book.getTitle() %></td>
                                    <td width="30%">
                                        <%  List<Author> curAuthors = book.getAuthors();
                                            for (Author author : curAuthors) { %>
                                            <%= author.getFullName() %><br/>
                                        <%}%>
                                    </td>
                                    <td><%= book.getYear() %></td>
                                    <td><%= book.getLanguage() %></td>
                                    <td><%= book.getShelfCode() %></td>
                                    <td><%= book.getAmount() %></td>
                                    <td>
                                        <button type="submit" class="btn btn-primary"
                                                id="edit_<%= book.getId()%>" name="edit_<%= book.getId()%>"
                                                data-toggle="modal" data-target="#popupWindow">
                                            <spring:message code="toEdit" />
                                        </button>
                                    </td>
                                </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1"></div>
                <div class="modal fade" id="popupWindow" tabindex="-1" role="dialog"
                     aria-labelledby="popupWindowLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <div id="status_message_popup" class="bg-danger"></div>
                                <form id="chooseBookForm" class="doNotProcess  popupForm" method="post">
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <label for="title"><b><spring:message code="title"/>:</b></label>
                                                <input type="text" class="form-control" name="title"
                                                       id="title" value=""><br/>

                                                <label for="amount"><b><spring:message code="amount"/>:</b></label>
                                                <input type="number" class="form-control" name="amount"
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
                                                <input type="number" class="form-control" name="year"
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
                                <hr class="my-4">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <form id="deleteAuthors" class="doNotProcess scaled" method="post">
                                            <label for="choose_book_authors"><spring:message code="deleteCurAuthor"
                                            />:</label>
                                            <select class="form-control" name="choose_book_authors" id="choose_book_authors">
                                            </select>
                                            <br/>
                                            <button type="submit" class="btn btn-primary" id="deleteAuthor" name="deleteAuthor">
                                                <spring:message code="toDelete" />
                                            </button>
                                        </form>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <form id="addAuthors" class="doNotProcess scaled" method="post">
                                            <label for="choose_authors"><spring:message code="addAuthor" />:</label>
                                            <select class="form-control" name="choose_authors" id="choose_authors">
                                                <%for (Author author : authors) {%>
                                                <option><%= author.getFullName() %></option>
                                                <%}%>
                                            </select>
                                            <br/>
                                            <button type="submit" class="btn btn-primary"
                                                    id="addAuthorBut" name="addAuthorBut">
                                                <spring:message code="toAdd" />
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
