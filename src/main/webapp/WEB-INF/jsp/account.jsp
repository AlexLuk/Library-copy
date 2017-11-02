<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="templates/taglibs.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Online</title>
    <%@ include file="templates/meta.jsp" %>
</head>

<body>
<%@ include file="templates/nav.jsp" %>

    <%@ include file="reader.jsp" %>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <%@ include file="admin.jsp" %>
    </sec:authorize>

</body>
</html>
