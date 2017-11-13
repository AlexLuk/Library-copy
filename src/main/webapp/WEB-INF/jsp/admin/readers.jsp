<%@ page import="org.library.db.domain.Reader" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.LibraryService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Reader> readers = libraryService.getAllReaders();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="showReaders" /> - Library Online&trade;</title>
    <%@ include file="../templates/meta.jsp" %>
</head>

<body>
    <%@ include file="../templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <div id="status_message" class="bg-danger"></div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><spring:message code="fullName" /></th>
                    <th><spring:message code="registrationDate" /></th>
                    <th><spring:message code="fines" /></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <%for (Reader reader : readers) {%>
                        <tr>
                            <td><%= reader.getFullName() %></td>
                            <td><%= reader.convertLocalDate().toString() %></td>
                            <td>
                                <input type="number" id="fines" min="0" value="<%= reader.getFines() %>"/>&nbsp;&#x20bd;
                            </td>
                            <td>
                                <%if (!reader.getFirstName().equals("admin")) {%>
                                    <button type="submit" class="btn btn-primary deleteReader"
                                            id="deleteReader_<%= reader.getId()%>" name="deleteReader_<%= reader.getId()%>">
                                        <spring:message code="toDeleteReader" />
                                    </button>
                                <%}%>
                            </td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
