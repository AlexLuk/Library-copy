<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<% Reader curUser = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); %>

<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
    <br/>
    <form name="profileForm" id="profileForm">
        <div class="form-group">
            <div class="row">
                <div class="col-xs-5">
                    <label for="firstName"><b><spring:message code="firstName"/>:</b></label>
                    <input type="text" class="form-control" name="firstName"
                           id="firstName" value="<%= curUser.getFirstName()%>"><br/>

                    <label for="changeEmail"><b><spring:message code="changeEmail"/>:</b></label>
                    <input type="text" class="form-control" name="changeEmail"
                           id="changeEmail" value="<%= curUser.getEmail()%>"><br/>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <label for="lastName"><b><spring:message code="lastName"/>:</b></label>
                    <input type="text" class="form-control" name="lastName"
                           id="lastName" value="<%= curUser.getLastName()%>"><br/>

                    <label for="changePassword"><b><spring:message code="changePassword"/>:</b></label>
                    <input type="text" class="form-control" name="changePassword"
                           id="changePassword" value=""><br/>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <label for="patronymic"><b><spring:message code="patronymic"/>:</b></label>
                    <input type="text" class="form-control" name="patronymic"
                           id="patronymic" value="<%= curUser.getPatronymic()%>"><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-5">
                    <div id="registr_date"><strong><spring:message
                            code="registrationDate"/>:</strong>&nbsp;<%= curUser.convertLocalDate()%></div><br/>
                    <div id="fines"><strong><spring:message code="fines"/>:</strong>&nbsp;<%= curUser.getFines()%>&#x20bd;</div><br/><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-5">
                    <button type="submit" class="btn btn-primary" id="saveProfile" name="saveProfile">
                        <spring:message code="toSaveProfile" />
                    </button>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <button type="submit" class="btn btn-primary" id="deleteProfile" name="deleteProfile">
                        <spring:message code="toDeleteProfile" />
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
