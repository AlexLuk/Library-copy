<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="hidden">
    <div id="error_firstname"><spring:message code="errorFirstname" /></div>
    <div id="error_lastname"><spring:message code="errorLastname" /></div>
    <div id="error_password"><spring:message code="errorPassword" /></div>
    <div id="error_pwd_check"><spring:message code="errorPwdcheck" /></div>
    <div id="error_pwd_minlen"><spring:message code="errorPwdMinLen" /></div>
    <div id="error_email_req"><spring:message code="errorEmailReq" /></div>
    <div id="error_email"><spring:message code="errorEmail" /></div>

    <div id="error_old_password"><spring:message code="errorOldPassword" /></div>
    <div id="errorConfirmPassword"><spring:message code="errorConfirmPassword" /></div>
    <div id="profile_succ"><spring:message code="profileSucc" /></div>
    <div id="profile_fail"><spring:message code="profileFail" /></div>

    <div id="error_email_not_unique"><spring:message code="errorEmailNotUnique" /></div>
    <div id="error_contains_parts"><spring:message code="errorContainsParts" /></div>
    <div id="succ_register"><spring:message code="succRegister" /></div>

    <div id="succ_order_created"><spring:message code="succOrderCreated" /></div>
    <div id="error_order_create_ordered"><spring:message code="errorOrderCreateOrdered" /></div>
    <div id="error_order_create_delivered"><spring:message code="errorOrderCreateDelivered" /></div>

    <div id="error_order_canceled"><spring:message code="errorOrderCanceled" /></div>
    <div id="succ_order_canceled"><spring:message code="succOrderCanceled" /></div>

    <div id="error_status_change"><spring:message code="errorStatusChange" /></div>
    <div id="succ_status_change"><spring:message code="succStatusChange" /></div>

    <div id="succ_delivery_created"><spring:message code="succDeliveryCreated" /></div>
    <div id="error_delivery_created"><spring:message code="errorDeliveryCreated" /></div>

    <div id="succ_return_book"><spring:message code="succReturnBook" /></div>
    <div id="error_return_book"><spring:message code="errorReturnBook" /></div>

    <div id="succ_delete_account"><spring:message code="succAccountDeleted" /></div>
    <div id="error_delete_account"><spring:message code="errDeleteAccount" /></div>
    <div id="error_delete_account_fines"><spring:message code="errDeleteAccountFines" /></div>
    <div id="error_delete_account_order"><spring:message code="errDeleteAccountOrder" /></div>
    <div id="error_delete_account_delivery"><spring:message code="errDeleteAccountDelivery" /></div>
    <div id="error_delete_account_admin"><spring:message code="errDeleteAccountAdmin" /></div>

    <div id="succ_fines_set"><spring:message code="succFinesSet" /></div>
    <div id="error_fines_set"><spring:message code="errorFinesSet" /></div>


    <div id="datapicker_apply"><spring:message code="datapickerApply" /></div>
    <div id="datapicker_clear"><spring:message code="datapickerClear" /></div>
    <div id="datapicker_format"><spring:message code="datapickerFormat" /></div>
</div>

<!-- Start scripts -->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/utils.js"></script>
<script src="${pageContext.request.contextPath}/js/process_requests.js"></script>
<script src="${pageContext.request.contextPath}/js/html_output.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-validate.bootstrap-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
<!-- end scripts -->
