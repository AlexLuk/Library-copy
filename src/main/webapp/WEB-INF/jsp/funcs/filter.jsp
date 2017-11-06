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
                <input type="text" class="form-control" name="book_genre" id="book_genre">
            </div>
        </div>
    </div>
</form>
