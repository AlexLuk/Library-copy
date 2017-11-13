<%@ page import="org.library.db.domain.Delivery" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.db.domain.Reader" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    Reader curUser = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Delivery> deliveredBooks = libraryService.getAllDeliveryItemsByReader(curUser.getId());
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
                    <%for (Delivery delivery : deliveredBooks) {%>
                        <tr>
                            <td><%= delivery.getBookItem().getBook().getTitle() %></td>
                            <td>
                                <% if(delivery.getBookItem().getStatus().getName().equals("on_hands")) { %>
                                <spring:message code="statusForHome" />
                                <%} else {%>
                                <spring:message code="statusInLib" />
                                <%}%>
                            </td>
                            <td><%= delivery.convertLocalDate() %></td>
                            <td>
                                <button type="submit" class="btn btn-primary"
                                        id="delete_<%= delivery.getId()%>" name="delete_<%= delivery.getId()%>">
                                    <spring:message code="toReturn" />
                                </button>
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
