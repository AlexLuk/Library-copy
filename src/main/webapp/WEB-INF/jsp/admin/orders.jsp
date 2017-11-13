<%@ page import="org.library.db.domain.BookOrder" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<BookOrder> orders = libraryService.getAllOrders();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="showOrders" /> - Library Online&trade;</title>
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
                    <th><spring:message code="title" /></th>
                    <th><spring:message code="shelfCode" /></th>
                    <th><spring:message code="wishStatus" /></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <%for (BookOrder order : orders) {%>
                    <tr>
                        <td><%= order.getReader().getFullName() %></td>
                        <td><%= order.getBook().getTitle() %></td>
                        <td><%= order.getBook().getShelfCode() %></td>
                        <td>
                            <% if(order.getOnHands()) { %>
                                <spring:message code="wishStatusForHome" />
                            <%} else {%>
                                <spring:message code="wishStatusInLib" />
                            <%}%>
                        </td>
                        <td>
                            <input type="hidden" id="order_id" name="order_id" value="<%= order.getId()%>" />
                            <button type="submit" class="btn btn-primary"
                                    id="giveOrderHands_<%= order.getId()%>" name="giveOrderHands_<%= order.getId()%>">
                                <spring:message code="toServeOnHands" />
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary"
                                    id="giveOrderLib_<%= order.getId()%>" name="giveOrderLib_<%= order.getId()%>">
                                <spring:message code="toServeInLib" />
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
