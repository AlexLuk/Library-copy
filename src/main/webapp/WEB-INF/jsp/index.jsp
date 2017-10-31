<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="templates/header.jsp" %>

        <h2 class="display-4">Welcome to the online Library</h2>
        <p class="lead">All your books at your fingertips:</p>
        <hr class="my-4">
        <div id="service_buttons">
            <a class="btn btn-info btn-lg btn-block role" id="reader_enter"role="button" href="/account">Reader</a>
            <a class="btn btn-info btn-lg btn-block role" id="admin_enter" role="button" href="/admin">Librarian</a>
        </div>

<%@ include file="templates/footer.jsp" %>
