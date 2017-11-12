<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.library.db.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="org.library.services.LibraryService" %>
<%@ page import="org.library.db.domain.Author" %>

<div class="tab-pane fade show active" id="nav-find" role="tabpanel" aria-labelledby="nav-find-tab">
    <div id="status_message" class="bg-danger"></div>
    <br/>
    <%@ include file="filter.jsp" %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="title" /></th>
            <th><spring:message code="author" /></th>
            <th><spring:message code="language" /></th>
            <th><spring:message code="year" /></th>
            <th><spring:message code="genre" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody class="content_res_book">
            <%for (Book book : books) {
                List<Author> authors = libraryService.getAllAuthors(book.getId());
            %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td>
                        <%for (Author author : authors) {%>
                        <%= author.getFullName() %><br/>
                        <%}%>
                    </td>
                    <td><%= book.getLanguage().toLowerCase() %></td>
                    <td><%= book.getYear() %></td>
                    <td>
                        <spring:message code="<%= libraryService.getGenre(book.getGenre().getId()) %>" />
                    </td>
                    <td>
                        <form method="post" class="doNotProcess">
                            <button type="submit" class="btn btn-primary orderHands"
                                    id="orderHands_<%= book.getId()%>" name="orderHands_<%= book.getId()%>">
                                <spring:message code="toOrderOnHands" />
                            </button>
                        </form>
                    </td>
                    <td>
                        <form method="post" class="doNotProcess">
                            <button type="submit" class="btn btn-primary orderLib"
                                    id="orderLib_<%= book.getId()%>" name="orderLib_<%= book.getId()%>">
                                <spring:message code="toOrderInLib" />
                            </button>
                        </form>
                    </td>
                </tr>
            <%}%>
        </tbody>
    </table>
</div>

<div class="hidden">
    <div id="orderOnHandsForm">
        <form method="post" class="doNotProcess">
            <button type="submit" class="btn btn-primary orderHands" id="orderHands" name="orderHands">
                <spring:message code="toOrderOnHands" />
            </button>
        </form>
    </div>
    <div id="orderInLibForm">
        <form method="post" class="doNotProcess">
            <button type="submit" class="btn btn-primary orderLib" id="orderLib" name="orderLib">
                <spring:message code="toOrderInLib" />
            </button>
        </form>
    </div>
</div>
