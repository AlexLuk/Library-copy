<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.library.db.domain.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Book> books = libraryService.getAllBooks();
%>

<sec:authorize access="hasRole('ROLE_READER')">
    <%
        List<Genre> genres = libraryService.getAllGenres();
        Reader curUser = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BookOrder> orders = libraryService.getAllOrdersByReader(curUser.getId());
        List<Delivery> deliveredBooks = libraryService.getAllDeliveryItemsByReader(curUser.getId());
    %>
    <%@ include file="reader/find.jsp" %>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <%
        List<BookOrder> orders = libraryService.getAllOrders();
        List<Delivery> deliveredBooks = libraryService.getAllDeliveryItems();
        List<LocalDate> dates = libraryService.getAllDates(deliveredBooks);
    %>
    <%@ include file="admin/orders.jsp" %>
</sec:authorize>
