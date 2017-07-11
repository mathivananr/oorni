<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="onlineWallet.title" /></title>
</head>

<div class="col-sm-1">
</div>
<div class="col-sm-10">
	
	<h2>
		<fmt:message key="onlineWallet.heading" /> Details
	</h2>
	<hr/>
	<spring:bind path="onlineWallet.*">
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

	<form:form commandName="onlineWallet" method="post" enctype="multipart/form-data"
		action="/user/walletDetail" id="walletDetailForm"
		autocomplete="off" cssClass="">
		<form:hidden path="walletId" />
		<form:hidden path="createdOn"/>
		<form:hidden path="createdBy"/>
		<form:hidden path="enabled"/>
		
		<div class="row">
            <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="onlineWallet.walletName"/>
				<form:input cssClass="form-control input-border-bottom" path="walletName"
					id="walletName" placeholder="Wallet Name"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="onlineWallet.walletProfileName"/>
	            <form:input cssClass="form-control input-border-bottom" path="walletProfileName"
					id="walletProfileName" placeholder="Profile Name"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="onlineWallet.walletMobileNumber"/>
	           <form:input cssClass="form-control input-border-bottom" path="walletMobileNumber"
					id="walletMobileNumber" placeholder="Mobile Number"/>
            </div>
        </div>
        
				
		<div class="row">
			 <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="onlineWallet.walletEmail"/>
				<form:input cssClass="form-control input-border-bottom" path="walletEmail"
					id="walletEmail" placeholder="Email"/>
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
