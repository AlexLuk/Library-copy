<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="tab-pane fade" id="nav-wishlist" role="tabpanel" aria-labelledby="nav-wishlist-tab">
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
                <button type="submit" class="btn btn-primary" id="delete" name="delete">
                    <spring:message code="toDelete" />
                </button>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
