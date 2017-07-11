<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="bankAccount.title" /></title>
</head>

<div class="col-sm-1">
</div>
<div class="col-sm-10">
	
	<h2>
		<fmt:message key="bankAccount.heading" /> Details
	</h2>
	<hr/>
	<spring:bind path="bankAccount.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger alert-dismissable">
				<a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}">
					<c:out value="${error}" escapeXml="false" />
					<br />
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<form:form commandName="bankAccount" method="post" enctype="multipart/form-data"
		action="/user/bankDetail" id="bankDetailForm"
		autocomplete="off" cssClass="">
		<form:hidden path="bankAccountId" />
		<form:hidden path="createdOn"/>
		<form:hidden path="createdBy"/>
		<form:hidden path="enabled"/>
		
		<div class="row">
            <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="bankAccount.bankName"/>
				<form:input cssClass="form-control input-border-bottom" path="bankName"
					id="bankName" placeholder="Bank Name"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="bankAccount.ifscCode"/>
	            <form:input cssClass="form-control input-border-bottom" path="ifscCode"
					id="ifscCode" placeholder="IFSC Code"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="bankAccount.branch"/>
	           <form:input cssClass="form-control input-border-bottom" path="branch"
					id="branch" placeholder="Branch"/>
            </div>
        </div>
        
				
		<div class="row">
			 <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="bankAccount.accountNo"/>
				<form:input cssClass="form-control input-border-bottom" path="accountNo"
					id="accountNo" placeholder="Account No"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="bankAccount.accountName"/>
	            <form:input cssClass="form-control input-border-bottom" path="accountName"
					id="accountName" placeholder="Account Name"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="bankAccount.city"/>
	          	<form:input cssClass="form-control input-border-bottom" path="city"
					id="city" placeholder="City"/>
            </div>
		</div>
		
		<div class="row">
			 <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="bankAccount.country"/>
				<form:input cssClass="form-control input-border-bottom" path="country"
					id="country" placeholder="Country"/>
            </div>
            <div class="col-sm-4 form-group">
            </div>
            <div class="col-sm-4 form-group">
            </div>
		</div>
		
		<div class="row">
			<div class="form-group pull-right">
				<button type="button" class="btn btn-default" name="cancel"
					onclick="bCancel=true">
					<i class="icon-remove"></i>
					<fmt:message key="button.cancel" />
				</button>
				<button type="submit" class="btn btn-primary" name="save"
					onclick="bCancel=false">
					<i class="icon-ok icon-white"></i>
					<fmt:message key="button.save" />
				</button>
			</div>
		</div>
	</form:form>
</div>
