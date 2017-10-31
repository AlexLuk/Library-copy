<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="templates/header.jsp" %>

        <div class="container">
            <div class="jumbotron">
                <h2 class="display-4">Welcome to the online Library</h2>
                <p class="lead">All your books at your fingertips:</p>
                <hr class="my-4">
                <div id="service_buttons">
                    <a class="btn btn-info btn-lg btn-block role" role="button" href="/account">Reader</a>
                    <a class="btn btn-info btn-lg btn-block role" role="button" href="/admin">Librarian</a>
                </div>
            </div>
        </div>

<%@ include file="templates/footer.jsp" %>
