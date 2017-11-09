<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="tab-pane fade" id="nav-delivered-books" role="tabpanel" aria-labelledby="nav-delivered-books-tab">
    <div id="status_message" class="bg-danger"></div>
    <br/>
    <%@ include file="filterOrders.jsp" %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="fullName" /></th>
            <th><spring:message code="title" /></th>
            <th><spring:message code="status" /></th>
            <th><spring:message code="time" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%for (Delivery delivery : deliveredBooks) {%>
        <tr>
            <td><%= delivery.getReader().getFullName() %></td>
            <td><%= delivery.getBookItem().getBook().getTitle() %></td>
            <td><%= delivery.convertLocalDate() %></td>
            <td>
                <% if(delivery.getBookItem().getStatus().getName().equals("on_hands")) { %>
                <spring:message code="statusForHome" />
                <%} else {%>
                <spring:message code="statusInLib" />
                <%}%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
