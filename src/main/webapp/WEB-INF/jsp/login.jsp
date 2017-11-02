<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@ include file="templates/taglibs.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library</title>
    <%@ include file="templates/meta.jsp" %>
</head>

<body>
<%@ include file="templates/nav.jsp" %>

<div class="container">
    <div class="jumbotron">
        <h3 class="display-4">Welcome to the online Library</h3>
        <p class="lead">All your books at your fingertips:</p>
        <hr class="my-4">
        <div id="login_form">
            <c:if test="${not empty error}">
                <div class="error alert alert-danger" role="alert">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg alert alert-success" role="alert">${msg}</div>
            </c:if>
            <form method="post" action="/checkAuth" id="login" name="login">
                <div class="form-group">
                    <label for="email_enter">Email:</label>
                    <input type="email" class="form-control" name="email_enter" id="email_enter" value=""><br/>
                    <label for="passwd_enter">Password:</label>
                    <input type="password" class="form-control" name="passwd_enter" id="passwd_enter" value=""><br/>
                    <button type="submit" class="btn btn-primary" id="check_passwd" name="check_passwd">Log in</button>
                </div>
            </form>
            <div class="float-left">
                If you don't have an account yet
                please <a id="register_link" class="font-weight-light" role="button" data-toggle="modal"
                          data-target="#registerWindow">Register</a>
            </div>
            <div class="modal fade" id="registerWindow" tabindex="-1" role="dialog"
                 aria-labelledby="registerWindowLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="#" th:action="@{/checks}" th:object="${reader}" method="post">
                                <div class="form-group">
                                    <label for="email_register">Email:</label>
                                    <input type="email" th:field="*{email}" class="form-control" name="email"
                                           id="email_register" value=""><br/>
                                    <label for="passwd_register">Password:</label>
                                    <input type="password" th:field="*{password}" class="form-control" name="password"
                                           id="passwd_register" value=""><br/>
                                     <label for="name">Name:</label>
                                    <input type="text" th:field="*{name}" class="form-control" name="name"
                                           id="name" value=""><br/>
                                    <label for="surname">Surname:</label>
                                    <input type="text" th:field="*{surname}" class="form-control" name="surname"
                                           id="surname" value=""><br/>
                                    <label for="patronymic">Patronymic name:</label>
                                    <input type="text" th:field="*{patronymic}" class="form-control" name="patronymic"
                                           id="patronymic" value=""><br/>
                                    <input type="submit" class="btn btn-primary" id="register" name="register" value="Register"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <div id="status_message" class="bg-danger"></div>
        </div>
        <%@ include file="templates/footer.jsp" %>
    </div>
</div>

</body>
</html>
