<%@ page import="org.library.db.domain.BookOrder" %>
<%@ page import="org.library.db.domain.Delivery" %>
<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
    LibraryService libraryService = (LibraryService) request.getAttribute("lib_service");
    List<Book> books = libraryService.getAllBooks();
    List<Author> authors = libraryService.getAllAuthors();
    List<BookOrder> orders = libraryService.getAllOrders();
    List<Delivery> deliveredBooks = libraryService.getAllDeliveryItems();
    List<LocalDate> dates = libraryService.getAllDates(deliveredBooks);
%>

<div class="tab-content" id="nav-tabContent">
    <%@ include file="orders.jsp" %>
    <%@ include file="delivered.jsp" %>
    <%@ include file="readers.jsp" %>
    <%@ include file="books.jsp" %>
    <%@ include file="authors.jsp" %>
</div>
