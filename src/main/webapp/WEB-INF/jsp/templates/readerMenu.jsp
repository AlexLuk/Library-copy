<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<li class="nav-item active">
    <a class="nav-link" href="/account"><spring:message code="findOrderBooks" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/account/reader/delivered"><spring:message code="currentOrders" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/account/reader/wishlist"><spring:message code="whishlist" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/account/reader/profile"><spring:message code="profile" /></a>
</li>
