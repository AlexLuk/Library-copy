<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<LocalDate> dates = libraryService.getAllDates(deliveredBooks);
%>

<form id="filter_form" class="scaled doNotProcess">
    <div class="form-group">
        <div class="row">
            <div class="col-lg-2 col-md-2 col-xs-2">
                <label for="readerName"><spring:message code="reader" />:</label>
                <input type="text" class="form-control" name="readerName" id="readerName">
            </div>
            <div class="col-lg-2 col-md-2 col-xs-2">
                <label for="book_title"><spring:message code="title" />:</label>
                <input type="text" class="form-control" name="book_title" id="book_title">
            </div>
            <div class="col-lg-2 col-md-2 col-xs-2">
                <label for="time"><spring:message code="time" />:</label>
                <select class="form-control" name="time" id="time">
                    <option></option>
                    <%for (LocalDate date : dates) {%>
                    <option><%= date %></option>
                    <%}%>
                </select>
            </div>
            <div class="col-lg-3 col-md-3 col-xs-3">
                <label for="book_genre"><spring:message code="status" />:</label>
                <select class="form-control" name="book_genre" id="book_genre">
                    <option></option>
                    <option><spring:message code="statusForHome" /></option>
                    <option><spring:message code="statusInLib" /></option>
                </select>
            </div>
            <div class="col-lg-2 col-md-2 col-xs-2">
                <button type="submit" class="btn btn-primary" id="filer_button" name="filer_button">
                    <spring:message code="toFilter" />
                </button>
            </div>
        </div>
    </div>
</form>
