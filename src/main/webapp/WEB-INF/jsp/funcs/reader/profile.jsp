<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
    <br/>
    <label for="firstName"><b><spring:message code="firstName"/>:</b></label>
    <input type="text" class="form-control" name="firstName" id="firstName" value=""><br/>

    <label for="lastName"><b><spring:message code="lastName"/>:</b></label>
    <input type="text" class="form-control" name="lastName" id="lastName" value=""><br/>

    <label for="patronymic"><b><spring:message code="patronymic"/>:</b></label>
    <input type="text" class="form-control" name="patronymic" id="patronymic" value=""><br/>

    <b><spring:message code="registration_date"/>:</b>
    _date <br/><br/>

    <b><spring:message code="fines"/>: </b>
    _fines <br/><br/>

    <label for="changeEmail"><b><spring:message code="changeEmail"/>:</b></label>
    <input type="text" class="form-control" name="changeEmail" id="changeEmail" value=""><br/>

    <label for="changePassword"><b><spring:message code="changePassword"/>:</b></label>
    <input type="text" class="form-control" name="changePassword" id="changePassword" value=""><br/>

</div>