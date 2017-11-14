<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="filter_form" class="scaled doNotProcess">
    <div class="form-group">
        <div class="row">
            <div class="col-xs-5">
                <label for="book_title"><spring:message code="title" />:</label>
                <input type="text" class="form-control" name="book_title" id="book_title">
            </div>
            <div class="delimeter"></div>
            <div class="col-xs-5">
                <label for="book_author"><spring:message code="author" />:</label>
                <input type="text" class="form-control" name="book_author" id="book_author">
            </div>
            <div class="delimeter"></div>
            <div class="col-xs-5">
                <label for="book_year"><spring:message code="year" />:</label>
                <input type="number" class="form-control" name="book_year" id="book_year">
            </div>
            <div class="delimeter"></div>
            <div class="col-xs-5">
                <label for="book_genre"><spring:message code="genre" />:</label>
                <select class="form-control" name="book_genre" id="book_genre">
                    <option id=""></option>
                    <%for (Book.Genre genre : Book.Genre.values()) {
                        String genreVal = genre.getVals().get("en");
                        if (genreVal != null) {
                    %>
                        <option id = "<%= genreVal %>">
                            <%= genreVal %>
                        </option>
                    <%}}%>
                </select>
            </div>
            <div class="delimeter"></div>
            <div class="col-xs-5">
                <button type="submit" class="btn btn-primary" id="filer_button" name="filer_button">
                    <spring:message code="toFilter" />
                </button>
            </div>
        </div>
    </div>
</form>
