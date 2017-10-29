<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="templates/header.jsp" %>

        <nav class="navbar navbar-light bg-light">
            <a class="navbar-brand" href="/">
                <img src="../../img/favicon.ico" width="16" height="16" alt="">Library&trade;
            </a>
        </nav>

        <div class="container">
            <div class="jumbotron">
                <h1 class="display-3">Welcome to the online Library</h1>
                <p class="lead">All your books at your fingertips:</p>
                <hr class="my-4">
                <div id="service_buttons">
                    <button type="button" class="btn btn-info btn-lg btn-block role" data-toggle="modal"
                            data-target="#loginWindow">Enter</button>

                    <div class="modal fade" id="loginWindow" tabindex="-1" role="dialog"
                         aria-labelledby="loginWindowLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="loginWindowLabel">Please provide your credentials:</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form method="post" action="service" id="login" name="login">
                                        <div class="form-group">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" name="email" id="email" value=""><br/>
                                            <label for="passwd">Password:</label>
                                            <input type="password" class="form-control" name="passwd" id="passwd" value=""><br/>
                                            <button type="submit" class="btn btn-primary" id="check_passwd" name="check_passwd">Log in</button>
                                            <button type="submit" class="btn btn-primary" id="register" name="register">Register</button>
                                        </div>
                                    </form>
                                </div>
                                <br/>
                                <div id="status_message" class="bg-danger"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<%@ include file="templates/footer.jsp" %>
