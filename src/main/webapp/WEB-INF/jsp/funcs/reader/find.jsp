<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.library.db.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Author" %>

<div class="tab-pane fade show active" id="nav-find" role="tabpanel" aria-labelledby="nav-find-tab">
    <%@ include file="filter.jsp" %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="title" /></th>
            <th><spring:message code="author" /></th>
            <th><spring:message code="year" /></th>
            <th><spring:message code="genre" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody class="content_res_book">
        </tbody>
    </table>
</div>

<div class="hidden">
    <div id="orderOnHandsForm">
        <form method="post" class="doNotProcess">
            <input type="hidden" id="order_hands_book_id" name="order_hands_book_id" value="asfas" />
            <button type="submit" class="btn btn-primary" id="orderHands" name="orderHands">
                <spring:message code="toOrderOnHands" />
            </button>
        </form>
    </div>
    <div id="orderInLibForm">
        <form method="post" class="doNotProcess">
            <input type="hidden" id="order_lib_book_id" name="order_lib_book_id" value="" />
            <button type="submit" class="btn btn-primary" id="orderLib" name="orderLib">
                <spring:message code="toOrderInLib" />
            </button>
        </form>
    </div>
</div>
