<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="templates/header.jsp" %>

<div id="login_form">
    <h4>Please provide your credentials:</h4>
    <hr class="my-4">
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
    <br/>
    <div id="status_message" class="bg-danger"></div>
</div>

<%@ include file="templates/footer.jsp" %>
