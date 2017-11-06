
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@ include file="templates/taglibs.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Online - access</title>
    <%@ include file="templates/meta.jsp" %>
</head>

<body>
<%@ include file="templates/nav.jsp" %>

    <div class="container">
        <div class="jumbotron">
            <sec:authorize access="!isAuthenticated()">
                <h4 class="display-4"><spring:message code="greeting"/></h4>
                <p class="lead"><spring:message code="fingertips"/>:</p>
                <hr class="my-4">
                <div id="login_form">
                    <c:if test="${not empty error}">
                        <div class="error alert alert-danger" role="alert"><spring:message code="errInv" /></div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg alert alert-success" role="alert"><spring:message code="succLogout" /></div>
                    </c:if>
                    <form method="post" action="/login" id="login" name="login">
                        <div class="form-group">
                            <label for="email_enter"><spring:message code="email" />:</label>
                            <input type="email" class="form-control" name="email_enter" id="email_enter" value=""><br/>
                            <label for="passwd_enter"><spring:message code="password" />:</label>
                            <input type="password" class="form-control" name="passwd_enter" id="passwd_enter" value=""><br/>
                            <button type="submit" class="btn btn-primary" id="check_passwd" name="check_passwd"><spring:message code="login" /></button>
                        </div>
                    </form>
                    <div class="float-left">
                        <spring:message code="havntAcc" /> <a id="register_link" class="font-weight-light" role="button" data-toggle="modal"
                                  data-target="#registerWindow"><spring:message code="register" /></a>
                    </div>
                    <div class="modal fade" id="registerWindow" tabindex="-1" role="dialog"
                         aria-labelledby="registerWindowLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <form method="post">
                                        <div class="form-group">
                                            <label for="email_register"><spring:message code="email" />:</label>
                                            <input type="email" class="form-control" name="email_register" id="email_register"
                                                   value=""><br/>
                                            <label for="passwd_register"><spring:message code="password" />:</label>
                                            <input type="password" class="form-control" name="passwd_register"
                                                   id="passwd_register"
                                                   value=""><br/>
                                            <label for="name"><spring:message code="name" />:</label>
                                            <input type="text" class="form-control" name="name" id="name" value=""><br/>
                                            <label for="surname"><spring:message code="surname" />:</label>
                                            <input type="text" class="form-control" name="surname" id="surname" value=""><br/>
                                            <label for="patronymic"><spring:message code="patronymic" />:</label>
                                            <input type="text" class="form-control" name="patronymic" id="patronymic"
                                                   value=""><br/>
                                            <button type="submit" class="btn btn-primary" id="register" name="register">
                                                <spring:message code="toRegister" />
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div id="status_message" class="bg-danger"></div>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <c:redirect url="/account"/>
            </sec:authorize>
            <%@ include file="templates/footer.jsp" %>
        </div>
    </div>

</body>
</html>
