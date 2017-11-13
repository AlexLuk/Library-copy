<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="templates/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="error" /> - Library Online&trade;</title>
    <%@ include file="templates/meta.jsp" %>
</head>

<body>
    <%@ include file="templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><< <spring:message code="toHomePage" /></a>
            <br/>
            <br/>
            <c:if test="${not empty errorCode}">
                <spring:message code="${errorCode}" />
            </c:if>
        </div>
    </div>
    <%@ include file="templates/footer.jsp" %>
</body>
</html>
