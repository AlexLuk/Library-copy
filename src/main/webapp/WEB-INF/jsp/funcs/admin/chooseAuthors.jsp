<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-xs-5">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th><spring:message code="author" /></th>
                </tr>
            </thead>
            <tbody>
                <%for (Author author : authors) {%>
                <tr>
                    <td><%= author.getFullName() %></td>
                    <td>
                        <form method="post" class="doNotProcess">
                            <input type="hidden" id="book_id" name="book_id" value="<%= author.getId()%>" />
                            <button type="submit" class="btn btn-primary" id="edit" name="edit">
                                <spring:message code="toEdit" />
                            </button>
                        </form>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</div>
