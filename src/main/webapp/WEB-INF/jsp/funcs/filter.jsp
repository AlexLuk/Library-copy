<%@ page import="org.library.db.domain.Genre" %>
<% List<Genre> genres = bookService.getAllGenres(); %>

<form id="filter_form">
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
                <input type="text" class="form-control" name="book_year" id="book_year">
            </div>
            <div class="delimeter"></div>
            <div class="col-xs-5">
                <label for="book_genre"><spring:message code="genre" />:</label>
                <select class="form-control" name="book_genre" id="book_genre">
                    <option></option>
                    <%for (Genre genre : genres) {%>
                        <option><spring:message code="<%= genre.getName() %>" /></option>
                    <%}%>
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
