<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="paymentRequest.title" /></title>
</head>

<div class="col-sm-1"></div>
<div class="col-sm-10">

	<h2>
		<fmt:message key="paymentRequest.heading" />
	</h2>
	<hr />
	<spring:bind path="payment.*">
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

	<form:form commandName="payment" method="post"
		enctype="multipart/form-data" action="/user/paymentRequest"
		id="paymentRequestForm" autocomplete="off" cssClass="">
		<form:hidden path="paymentId" />
		<form:hidden path="createdOn" />
		<form:hidden path="createdBy" />

		<div class="row">
			<div class="col-sm-4 form-group">
				<appfuse:label styleClass="control-label"
					key="payment.paymentAmount" />
				<form:input cssClass="form-control input-border-bottom"
					path="paymentAmount" id="paymentAmount" placeholder1="Amount" />
			</div>
			<div class="col-sm-4 form-group">
				<appfuse:label styleClass="control-label" key="payment.paymentMode" />
				<form:select cssClass="form-control input-border-bottom"
					path="paymentMode" id="paymentMode" placeholder1="Payment Mode">
					<form:option value="bank account">Bank Account</form:option>
					<form:option value="online wallet">Online Wallet</form:option>
				</form:select>
			</div>
		</div>
		<div id="bankAccountDiv">
			<h5>
				Account Details
			</h5>
			<hr/>
			<div class="row">
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.bankAccount.bankName" />
					<form:input cssClass="form-control input-border-bottom"
						path="bankAccount.bankName" id="bankName" placeholder1="Bank Name" />
				</div>
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.bankAccount.accountNo" />
					<form:input cssClass="form-control input-border-bottom"
						path="bankAccount.accountNo" id="accountNo" placeholder1="Account No" />
				</div>
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.bankAccount.accountName" />
					<form:input cssClass="form-control input-border-bottom"
						path="bankAccount.accountName" id="accountName" placeholder1="Account Name" />
				</div>
			</div> 
			<div class="row">
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.bankAccount.ifscCode" />
					<form:input cssClass="form-control input-border-bottom"
						path="bankAccount.ifscCode" id="ifscCode" placeholder1="IFSC Code" />
				</div>
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.bankAccount.branch" />
					<form:input cssClass="form-control input-border-bottom"
						path="bankAccount.branch" id="branch" placeholder1="Branch" />
				</div>
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.bankAccount.city" />
					<form:input cssClass="form-control input-border-bottom"
						path="bankAccount.city" id="city" placeholder1="City" />
				</div>
			</div>
		</div>
		<div id="onlineWalletDiv">
			<h5>
				Wallet Details
			</h5>
			<hr/>
			<div class="row">
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.onlineWallet.walletName" />
					<form:input cssClass="form-control input-border-bottom"
						path="onlineWallet.walletName" id="walletName" placeholder1="Wallet Name" />
				</div>
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.onlineWallet.walletMobileNumber" />
					<form:input cssClass="form-control input-border-bottom"
						path="onlineWallet.walletMobileNumber" id="mobileNumber" placeholder1="Mobile No" />
				</div>
				<div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.onlineWallet.walletEmail" />
					<form:input cssClass="form-control input-border-bottom"
						path="onlineWallet.walletEmail" id="email" placeholder1="Email" />
				</div>
			</div> 
		</div>
		<div class="row">
			<security:authorize  ifAllGranted="ROLE_ADMIN">
                <div class="col-sm-4 form-group">
					<appfuse:label styleClass="control-label" key="payment.status" />
					<form:select cssClass="form-control input-border-bottom"
					path="status" id="status" placeholder1="Status">
					<form:option value="open">Open</form:option>
					<form:option value="approved">Approved</form:option>
					<form:option value="payment completed">Payment Completed</form:option>
					<form:option value="cancelled">Cancelled</form:option>
				</form:select>
				</div>
            </security:authorize>
            <security:authorize  ifAllGranted="ROLE_USER">
                <form:hidden path="status" />
            </security:authorize>
		</div>
		<%-- <div class="row">
			 <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="payment.accountNo"/>
				<form:input cssClass="form-control input-border-bottom" path="accountNo"
					id="accountNo" placeholder1="Account No"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="payment.accountName"/>
	            <form:input cssClass="form-control input-border-bottom" path="accountName"
					id="accountName" placeholder1="Account Name"/>
            </div>
            <div class="col-sm-4 form-group">
            	<appfuse:label styleClass="control-label" key="payment.city"/>
	          	<form:input cssClass="form-control input-border-bottom" path="city"
					id="city" placeholder1="City"/>
            </div>
		</div>
		
		<div class="row">
			 <div class="col-sm-4 form-group">
                <appfuse:label styleClass="control-label" key="payment.country"/>
				<form:input cssClass="form-control input-border-bottom" path="country"
					id="country" placeholder1="Country"/>
            </div>
            <div class="col-sm-4 form-group">
            </div>
            <div class="col-sm-4 form-group">
            </div>
		</div> --%>

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

<script type="text/javascript">

	function changePaymentMode() {
		if($('#paymentMode').val() == 'bank account') {
			$('#onlineWalletDiv').hide();
			$('#bankAccountDiv').show();
		} else if ($('#paymentMode').val() == 'online wallet'){
			$('#bankAccountDiv').hide();
			$('#onlineWalletDiv').show();
		}
	}

	$(function() {
		changePaymentMode();
	});
	
	$('#paymentMode').change(function(){
		changePaymentMode();
	});
</script>