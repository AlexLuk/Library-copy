<%@ page import="org.library.db.domain.Delivery" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.db.domain.Reader" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");


%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="currentOrders" /> - Library Online&trade;</title>
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
                    <th><spring:message code="title" /></th>
                    <th><spring:message code="status" /></th>
                    <th><spring:message code="time" /></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
