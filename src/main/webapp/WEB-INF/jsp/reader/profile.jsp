<%@ page import="org.library.db.domain.Reader" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/taglibs.jsp" %>

<%
    Reader curUser = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="profile" /> - Library Online&trade;</title>
    <%@ include file="../templates/meta.jsp" %>
</head>

<body>
    <%@ include file="../templates/nav.jsp" %>
    <div class="container">
        <div class="jumbotron">
            <div id="status_message" class="bg-danger"></div>
            <form name="profileForm" id="profileForm" class="doNotProcess">
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-5">
                            <label for="changeEmail"><b><spring:message code="changeEmail"/>:</b></label>
                            <input type="text" class="form-control" name="changeEmail"
                                   id="changeEmail" value="<%= curUser.getEmail()%>" disabled="disabled"><br/>

                            <div class="col-xs-5">
                                <div id="registr_date"><strong><spring:message
                                        code="registrationDate"/>:</strong>&nbsp;<%= curUser.convertLocalDate()%></div><br/>
                                <div id="fines">
                                    <strong><spring:message code="fines"/>:</strong>&nbsp;<%= curUser.getFines()%>&nbsp;&#x20bd;
                                </div>
                                <br/><br/>
                            </div>
                        </div>
                        <div class="delimeter"></div>
                        <div class="col-xs-5">
                            <label for="lastName"><b><spring:message code="lastName"/>:</b></label>
                            <input type="text" class="form-control" name="lastName"
                                   id="lastName" value="<%= curUser.getLastName()%>"><br/>

                            <label for="firstName"><b><spring:message code="firstName"/>:</b></label>
                            <input type="text" class="form-control" name="firstName"
                                   id="firstName" value="<%= curUser.getFirstName()%>"><br/>

                            <label for="patronymic"><b><spring:message code="patronymic"/>:</b></label>
                            <input type="text" class="form-control" name="patronymic"
                                   id="patronymic" value="<%= curUser.getPatronymic()%>"><br/>

                            <button type="submit" class="btn btn-primary" id="saveProfile" name="saveProfile">
                                <spring:message code="toSaveProfile" />
                            </button>
                        </div>
                        <div class="delimeter"></div>
                        <div class="col-xs-5">
                            <label for="changePassword"><b><spring:message code="changePassword"/>:</b></label>
                            <input type="password" class="form-control" name="changePassword"
                                   id="changePassword" value=""><br/>

                            <label for="confirmPassword"><b><spring:message code="confirmPassword"/>:</b></label>
                            <input type="password" class="form-control" name="confirmPassword"
                                   id="confirmPassword" value=""><br/>

                            <label for="oldPassword"><b><spring:message code="oldPassword"/>:</b></label>
                            <input type="password" class="form-control" name="oldPassword"
                                   id="oldPassword" value=""><br/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="hidden">
        <div id="error_old_password"><spring:message code="errorOldPassword" /></div>
        <div id="errorConfirmPassword"><spring:message code="errorConfirmPassword" /></div>
        <div id="profile_succ"><spring:message code="profileSucc" /></div>
        <div id="profile_fail"><spring:message code="profileFail" /></div>

        <div id="error_firstname"><spring:message code="errorFirstname" /></div>
        <div id="error_lastname"><spring:message code="errorLastname" /></div>
        <div id="error_password"><spring:message code="errorPassword" /></div>
        <div id="error_pwd_check"><spring:message code="errorPwdcheck" /></div>
        <div id="error_pwd_minlen"><spring:message code="errorPwdMinLen" /></div>
        <div id="error_email_req"><spring:message code="errorEmailReq" /></div>
        <div id="error_email"><spring:message code="errorEmail" /></div>

        <div id="error_email_not_unique"><spring:message code="errorEmailNotUnique" /></div>
        <div id="error_contains_parts"><spring:message code="errorContainsParts" /></div>
        <div id="succ_register"><spring:message code="succRegister" /></div>
    </div>
    <%@ include file="../templates/footer.jsp" %>
</body>
</html>
