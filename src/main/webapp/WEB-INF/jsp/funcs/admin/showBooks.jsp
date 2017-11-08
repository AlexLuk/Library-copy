<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<Book> books = libraryService.getAllBooks(); %>

<div class="tab-pane fade" id="nav-add-book" role="tabpanel" aria-labelledby="nav-add-book-tab">
    <form id="chooseBookForm" class="doNotProcess">
        <div class="form-group">
            <div class="row">
                <div class="col-xs-5 scaled">
                    <label for="choose_books"><spring:message code="addBooks" />:</label>
                    <select class="form-control" name="choose_books" id="choose_books">
                        <%for (Book book : books) {%>
                        <option><%= book.getTitle()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-5">
                    <label for="title"><b><spring:message code="title"/>:</b></label>
                    <input type="text" class="form-control" name="title"
                           id="title" value=""><br/>

                    <label for="amount"><b><spring:message code="amount"/>:</b></label>
                    <input type="text" class="form-control" name="amount"
                           id="amount" value=""><br/>

                    <label for="shelf_id"><b><spring:message code="shelfId"/>:</b></label>
                    <input type="text" class="form-control" name="shelf_id"
                           id="shelf_id" value=""><br/>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <label for="language"><b><spring:message code="language"/>:</b></label>
                    <input type="text" class="form-control" name="language"
                           id="language" value=""><br/>

                    <label for="year"><b><spring:message code="year"/>:</b></label>
                    <input type="text" class="form-control" name="year"
                           id="year" value=""><br/>

                    <label for="is_rare"><b><spring:message code="rare"/>:</b></label>
                    <input type="text" class="form-control" name="is_rare"
                           id="is_rare" value=""><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-5">
                    <form id="manageAuthors" class="doNotProcess">
                        <div class="form-group">
                            <label for="choose_book_authors"><spring:message code="addAuthors" />:</label>
                            <select class="form-control" name="choose_book_authors" id="choose_book_authors">
                            </select>
                            <button type="submit" class="btn btn-primary" id="deleteAuthor" name="deleteAuthor">
                                <spring:message code="toDelete" />
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-5">
                    <button type="submit" class="btn btn-primary" id="save" name="save">
                        <spring:message code="toSave" />
                    </button>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <button type="submit" class="btn btn-primary" id="delete" name="delete">
                        <spring:message code="toDelete" />
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
