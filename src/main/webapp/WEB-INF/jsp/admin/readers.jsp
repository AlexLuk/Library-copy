<%@ page import="org.library.db.domain.Reader" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="java.util.List" %>

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
                    <%for (Reader reader : readers) {
                        if (!reader.getFirstName().equals("admin")) {%>
                            <tr>
                                <td><%= reader.getFullName() %></td>
                                <td><%= reader.convertLocalDate().toString() %></td>
                                <td>
                                    <input type="number" class = "setFines" id="fines_<%= reader.getId()%>"
                                           name="fines_<%= reader.getId()%>" min="0"
                                           value="<%= reader.getFines() %>"/>&nbsp;&#x20bd;
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-primary deleteReader"
                                            id="deleteReader_<%= reader.getId()%>" name="deleteReader_<%= reader.getId()%>">
                                        <spring:message code="toDeleteReader" />
                                    </button>
                                </td>
                            </tr>
                    <%}}%>
                </tbody>
            </table>
        </div>
    </div>
    <div class="hidden">
        <div id="succ_fines_set"><spring:message code="succFinesSet" /></div>
        <div id="error_fines_set"><spring:message code="errorFinesSet" /></div>

        <div id="succ_delete_account"><spring:message code="succAccountDeleted" /></div>
        <div id="error_delete_account"><spring:message code="errDeleteAccount" /></div>
        <div id="error_delete_account_fines"><spring:message code="errDeleteAccountFines" /></div>
        <div id="error_delete_account_order"><spring:message code="errDeleteAccountOrder" /></div>
        <div id="error_delete_account_delivery"><spring:message code="errDeleteAccountDelivery" /></div>
        <div id="error_delete_account_admin"><spring:message code="errDeleteAccountAdmin" /></div>
    </div>

    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
