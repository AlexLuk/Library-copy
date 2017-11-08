<%@ page import="org.library.db.domain.BookOrder" %>
<%@ page import="org.library.db.domain.Delivery" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Book> books = libraryService.getAllBooks();
    List<Author> authors = libraryService.getAllAuthors();
    List<BookOrder> orders = libraryService.getAllOrders();
    List<Delivery> deliveredBooks = libraryService.getAllDeliveryItems();
%>

<div class="tab-content" id="nav-tabContent">
    <%@ include file="curOrders.jsp" %>
    <%@ include file="deliveredBooks.jsp" %>
    <%@ include file="showReaders.jsp" %>
    <%@ include file="showBooks.jsp" %>
    <%@ include file="showAuthors.jsp" %>
</div>
