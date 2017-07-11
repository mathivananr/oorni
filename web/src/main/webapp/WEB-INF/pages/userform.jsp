<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="container">
<div class="col-sm-12">
    <h2><fmt:message key="userProfile.heading"/></h2>
    <hr/>
    <%-- <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose> --%>
</div>
<div class="col-sm-12">
    <spring:bind path="user.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="user" method="post" action="userform" id="userForm" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
        <form:hidden path="id"/>
        <form:hidden path="username"/>
        <form:hidden path="version"/>
        <form:hidden path="passwordHint"/>
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>

        <%-- <spring:bind path="user.username">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.username"/>
            <form:input cssClass="form-control input-border-bottom" path="username" id="username"/>
            <form:errors path="username" cssClass="help-block"/>
            <c:if test="${pageContext.request.remoteUser == user.username}">
                <span class="help-block">
                    <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
                </span>
            </c:if>
        </div> --%>

        <%-- <spring:bind path="user.passwordHint">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.passwordHint"/>
            <form:input cssClass="form-control input-border-bottom" path="passwordHint" id="passwordHint"/>
            <form:errors path="passwordHint" cssClass="help-block"/>
        </div> --%>
        
        <div class="row">
            <spring:bind path="user.email">
            <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.email"/>
                <form:input cssClass="form-control input-border-bottom" path="email" id="email"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="user.mobileNumber"/>
	            <form:input cssClass="form-control input-border-bottom" path="mobileNumber" id="mobileNumber"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="user.userType"/>
	            <select id="userType" name="userType" class="form-control input-border-bottom">
	                <option value="individual" ${user.userType == 'individual' ? 'selected' : ' '} >Individual</option>
	                <option value="organization" ${user.userType == 'organization' ? 'selected' : ' '} >Organization</option>
	            </select>
            </div>
        </div>
        <div class="row">
            <spring:bind path="user.firstName">
            <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.firstName"/>
                <form:input cssClass="form-control input-border-bottom" path="firstName" id="firstName" maxlength="50"/>
                <form:errors path="firstName" cssClass="help-block"/>
            </div>
            <spring:bind path="user.lastName">
            <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.lastName"/>
                <form:input cssClass="form-control input-border-bottom" path="lastName" id="lastName" maxlength="50"/>
                <form:errors path="lastName" cssClass="help-block"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="user.panNo"/>
	            <form:input cssClass="form-control input-border-bottom" path="panNo" id="panNo"/>
            </div>
        </div>
        <div class="row">
			<div class="col-sm-4 form-group">
	            <appfuse:label styleClass="control-label" key="user.companyName"/>
	            <form:input cssClass="form-control input-border-bottom" path="companyName" id="companyName"/>
            </div>
            <div class="col-sm-4 form-group">
	            <appfuse:label styleClass="control-label" key="user.website"/>
	            <form:input cssClass="form-control input-border-bottom" path="website" id="website"/>
            </div>
        </div>
        
       <%--  <div>
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.address"/>
                    <form:input cssClass="form-control input-border-bottom" path="address.address" id="address.address"/>
                </div>
                <div class="row">
                    <div class="col-sm-7 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.city"/>
                        <form:input cssClass="form-control input-border-bottom" path="address.city" id="address.city"/>
                    </div>
                    <div class="col-sm-2 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.province"/>
                        <form:input cssClass="form-control input-border-bottom" path="address.province" id="address.province"/>
                    </div>
                    <div class="col-sm-3 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                        <form:input cssClass="form-control input-border-bottom" path="address.postalCode" id="address.postalCode"/>
                    </div>
                </div>
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.country"/>
                    <appfuse:country name="address.country" prompt="" default="${user.address.country}"/>
                </div>
            </div>
        </div> --%>
<c:choose>
    <c:when test="${param.from == 'list' or param.method == 'Add'}">
        <div class="form-group">
            <label class="control-label"><fmt:message key="userProfile.accountSettings"/></label>
            <label class="checkbox-inline">
                <form:checkbox path="enabled" id="enabled"/>
                <fmt:message key="user.enabled"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="accountExpired" id="accountExpired"/>
                <fmt:message key="user.accountExpired"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="accountLocked" id="accountLocked"/>
                <fmt:message key="user.accountLocked"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="credentialsExpired" id="credentialsExpired"/>
                <fmt:message key="user.credentialsExpired"/>
            </label>
        </div>
        <div class="form-group">
            <label for="userRoles" class="control-label"><fmt:message key="userProfile.assignRoles"/></label>
            <select id="userRoles" name="userRoles" multiple="true" class="form-control input-border-bottom">
                <c:forEach items="${availableRoles}" var="role">
                <option value="${role.value}" ${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
                </c:forEach>
            </select>
        </div>
    </c:when>
    <c:when test="${not empty user.username}">
        <div class="form-group">
            <%-- <label class="control-label"><fmt:message key="user.roles"/>:</label>
            <div class="readonly">
                <c:forEach var="role" items="${user.roleList}" varStatus="status">
                    <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
                    <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
                </c:forEach>
            </div> --%>
            <form:hidden path="enabled"/>
            <form:hidden path="accountExpired"/>
            <form:hidden path="accountLocked"/>
            <form:hidden path="credentialsExpired"/>
        </div>
    </c:when>
</c:choose>
        <div class="form-group pull-right">
            <%-- <c:if test="${param.from == 'list' and param.method != 'Add'}">
              <button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                  <i class="icon-trash"></i> <fmt:message key="button.delete"/>
              </button>
            </c:if> --%>

            <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
            
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
        </div>
    </form:form>
</div>
</div>
<c:set var="scripts" scope="request">
<script type="text/javascript">
// This is here so we can exclude the selectAll call when roles is hidden
function onFormSubmit(theForm) {
    return validateUser(theForm);
}
</script>
</c:set>

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

