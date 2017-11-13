<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="hidden">
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

    <div id="succ_order_created"><spring:message code="succOrderCreated" /></div>
    <div id="error_order_create"><spring:message code="errOrderCreate" /></div>
    <div id="succ_delete_account"><spring:message code="succAccountDeleted" /></div>
    <div id="error_delete_account"><spring:message code="errDeleteAccount" /></div>
</div>

<!-- Start scripts -->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/utils.js"></script>
<script src="${pageContext.request.contextPath}/js/process_requests.js"></script>
<script src="${pageContext.request.contextPath}/js/html_output.js"></script>
<!-- end scripts -->
