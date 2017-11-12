<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Online</title>
    <%@ include file="templates/meta.jsp" %>
</head>

<body>
    <%@ include file="templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <sec:authorize access="hasRole('ROLE_READER')">
                <%@ include file="reader/reader.jsp" %>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <%@ include file="admin/admin.jsp" %>
            </sec:authorize>
            </div>
    </div>
    <%@ include file="templates/footer.jsp" %>

    <div class="hidden">
        <div id="succ_account_deleted"><spring:message code="succAccountDeleted" /></div>
        <div id="error_delete_account"><spring:message code="errDeleteAccount" /></div>

        <div id="succ_order_created"><spring:message code="succOrderCreated" /></div>
        <div id="error_order_create"><spring:message code="errOrderCreate" /></div>
    </div>

</body>
</html>
