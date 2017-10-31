<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="templates/header.jsp" %>

<div id="registration_from">
    <h4>Please provide information about you:</h4>
    <hr class="my-4">
    <form method="post" action="service" id="login" name="register">
        <div class="form-group">
            <label for="email">Login (your email)</label>
            <input type="email" class="form-control" name="email" id="email" value=""><br/>
            <label for="passwd">Password:</label>
            <input type="password" class="form-control" name="passwd" id="passwd" value=""><br/>
            <label for="name">Name:</label>
            <input type="text" class="form-control" name="name" id="name" value=""><br/>
            <label for="surname">Surname:</label>
            <input type="text" class="form-control" name="surname" id="surname" value=""><br/>
            <label for="patronymic">Patronymic name:</label>
            <input type="text" class="form-control" name="patronymic" id="patronymic" value=""><br/>
            <label for="passport">Passport:</label>
            <input type="text" class="form-control" name="passport" id="passport" value=""><br/>
            <button type="submit" class="btn btn-primary" id="register" name="register">Register</button>
        </div>
    </form>
    <br/>
    <div id="status_message" class="bg-danger"></div>
</div>

<%@ include file="templates/footer.jsp" %>
