<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Author> authors = libraryService.getAllAuthors();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="addAuthors" /> - Library Online&trade;</title>
    <%@ include file="../templates/meta.jsp" %>
</head>

<body>
    <%@ include file="../templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <div id="status_message" class="bg-danger"></div>
            <div class="row">
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-xs-5">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th><spring:message code="author" /></th>
                                </tr>
                                </thead>
                                <tbody>
                                <%for (Author author : authors) {%>
                                <tr>
                                    <td><%= author.getFullName() %></td>
                                    <td>
                                        <form method="post" class="doNotProcess">
                                            <button type="submit" class="btn btn-primary"
                                                    id="edit_<%= author.getId()%>" name="edit_<%= author.getId()%>">
                                                <spring:message code="toEdit" />
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
                <div class="col-md-1"></div>
                <div class="col-md-7">
                    <form id="chooseAuthorForm" class="doNotProcess" method="post">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-xs-5">
                                    <label for="first_name"><b><spring:message code="firstName"/>:</b></label>
                                    <input type="text" class="form-control" name="first_name"
                                           id="first_name" value=""><br/>

                                    <label for="patronymic"><b><spring:message code="patronymic"/>:</b></label>
                                    <input type="text" class="form-control" name="patronymic"
                                           id="patronymic" value=""><br/>
                                </div>
                                <div class="delimeter"></div>
                                <div class="col-xs-5">
                                    <label for="last_name"><b><spring:message code="lastName"/>:</b></label>
                                    <input type="text" class="form-control" name="last_name"
                                           id="last_name" value=""><br/>

                                    <label for="year_of_birth"><b><spring:message code="yearOfBirth"/>:</b></label>
                                    <input type="text" class="form-control" name="year_of_birth"
                                           id="year_of_birth" value=""><br/>
                                </div>
                            </div>
                            <br/>
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
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
