<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Reader" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Reader> readers = libraryService.getAllReaders();
%>

<div class="tab-pane fade show active" id="nav-readers" role="tabpanel" aria-labelledby="nav-readers-tab">
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
            <td><%= reader.getFines() %></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
