<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
    <br/>
    <form name="profileForm" id="profileForm">
        <div class="form-group">
            <div class="row">
                <div class="col-xs-5">
                    <label for="firstName"><b><spring:message code="firstName"/>:</b></label>
                    <input type="text" class="form-control" name="firstName" id="firstName" value=""><br/>

                    <label for="changeEmail"><b><spring:message code="changeEmail"/>:</b></label>
                    <input type="text" class="form-control" name="changeEmail" id="changeEmail" value=""><br/>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <label for="lastName"><b><spring:message code="lastName"/>:</b></label>
                    <input type="text" class="form-control" name="lastName" id="lastName" value=""><br/>

                    <label for="changePassword"><b><spring:message code="changePassword"/>:</b></label>
                    <input type="text" class="form-control" name="changePassword" id="changePassword" value=""><br/>
                </div>
                <div class="delimeter"></div>
                <div class="col-xs-5">
                    <label for="patronymic"><b><spring:message code="patronymic"/>:</b></label>
                    <input type="text" class="form-control" name="patronymic" id="patronymic" value=""><br/>
                </div>
            </div>
            <div id="registr_date"><spring:message code="registrationDate"/>:</div><br/><br/>
            <div id="fines"><spring:message code="fines"/>: </div><br/><br/>
        </div>
    </form>
</div>
