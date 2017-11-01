<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="templates/header.jsp" %>

<div id="login_form">
    <h4>Please provide your credentials:</h4>
    <hr class="my-4">
    <form method="post" action="/checkAuth" id="login" name="login">
        <div class="form-group">
            <label for="email_enter">Email:</label>
            <input type="email" class="form-control" name="email_enter" id="email_enter" value=""><br/>
            <label for="passwd_enter">Password:</label>
            <input type="password" class="form-control" name="passwd_enter" id="passwd_enter" value=""><br/>
            <button type="submit" class="btn btn-primary" id="check_passwd" name="check_passwd">Log in</button>
        </div>
    </form>
    <sec:authorize url="/reader">
        <div class="float-left">
            If you don't have an account yet
            please <a id="register_link" class="font-weight-light" role="button" data-toggle="modal"
                      data-target="#registerWindow">Register</a>
        </div>
    </sec:authorize>
    <div class="modal fade" id="registerWindow" tabindex="-1" role="dialog" aria-labelledby="registerWindowLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <form method="post">
                        <div class="form-group">
                            <label for="email_register">Email</label>
                            <input type="email" class="form-control" name="email_register" id="email_register"
                                   value=""><br/>
                            <label for="passwd_register">Password:</label>
                            <input type="password" class="form-control" name="passwd_register" id="passwd_register"
                                   value=""><br/>
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" name="name" id="name" value=""><br/>
                            <label for="surname">Surname:</label>
                            <input type="text" class="form-control" name="surname" id="surname" value=""><br/>
                            <label for="patronymic">Patronymic name:</label>
                            <input type="text" class="form-control" name="patronymic" id="patronymic" value=""><br/>
                            <button type="submit" class="btn btn-primary" id="register" name="register">Register</button>
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
