<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-xs-5">
        <label for="choose_authors"><spring:message code="addAuthors" />:</label>
        <select class="form-control" name="choose_authors" id="choose_authors">
            <%for (Author author : authors) {%>
            <option><%= author.getFullName() %></option>
            <%}%>
        </select>
    </div>
</div>
