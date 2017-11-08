<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="tab-pane fade" id="nav-orders" role="tabpanel" aria-labelledby="nav-orders-tab">
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
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
