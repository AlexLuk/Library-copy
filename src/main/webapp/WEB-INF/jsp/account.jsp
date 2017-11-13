<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="hasRole('ROLE_READER')">
    <%@ include file="reader/find.jsp" %>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">

</sec:authorize>
