<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="tab-pane fade show active" id="nav-show-orders" role="tabpanel" aria-labelledby="nav-show-orders-tab">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="fullName" /></th>
            <th><spring:message code="title" /></th>
            <th><spring:message code="wishStatus" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <%for (BookOrder order : orders) {%>
            <tr>
                <td><%= order.getReader().getFullName() %></td>
                <td><%= order.getBook().getTitle() %></td>
                <td>
                    <% if(order.getOnHands()) { %>
                        <spring:message code="wishStatusForHome" />
                    <%} else {%>
                        <spring:message code="wishStatusInLib" />
                    <%}%>
                </td>
                <td>
                    <input type="hidden" id="order_id" name="order_id" value="<%= order.getId()%>" />
                    <button type="submit" class="btn btn-primary" id="giveOrder" name="giveOrder">
                        <spring:message code="toServe" />
                    </button>
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>
