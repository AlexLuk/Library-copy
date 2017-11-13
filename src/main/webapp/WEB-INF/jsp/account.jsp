<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="templates/taglibs.jsp" %>

<sec:authorize access="hasRole('ROLE_READER')">
    <%@ include file="reader/find.jsp" %>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <%@ include file="admin/orders.jsp" %>
</sec:authorize>
