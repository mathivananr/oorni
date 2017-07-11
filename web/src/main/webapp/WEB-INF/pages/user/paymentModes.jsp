<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="paymentList.title" /></title>
</head>

<c:if test="${not empty searchError}">
	<div class="alert alert-danger alert-dismissable">
		<a href="#" data-dismiss="alert" class="close">&times;</a>
		<c:out value="${searchError}" />
	</div>
</c:if>
<h2>
	<fmt:message key="paymentList.heading" />
</h2>
<hr/>
<div class="row">
	<div class="col-sm-12">
		<h4>
			<fmt:message key="bankAccount.heading" />
		</h4>
		<div id="actions" class="btn-group">
			<a class="btn btn-primary"
				href="<c:url value='/user/bankDetail?method=Add&from=list'/>"> <i
				class="icon-plus icon-white"></i> <fmt:message key="button.add" /></a> <a
				class="btn btn-default" href="<c:url value='/home'/>"> <i
				class="icon-ok"></i> <fmt:message key="button.done" /></a>
		</div>

		<display:table name="user.bankAccounts" cellspacing="0"
			cellpadding="0" requestURI="" defaultsort="1" id="accounts"
			pagesize="25" class="table table-condensed table-striped table-hover"
			export="true">
			<display:column property="bankName" escapeXml="true" sortable="true"
				titleKey="accounts.bankName" style="width: 15%"
				url="/user/bankDetail?from=list" paramId="id"
				paramProperty="bankAccountId" />
			<display:column property="accountNo" escapeXml="true" sortable="true"
				titleKey="accounts.accountNo" style="width: 25%" />
			<display:column property="accountName" escapeXml="true"
				sortable="true" titleKey="accounts.accountName" style="width: 25%" />
			<display:column property="ifscCode" escapeXml="true" sortable="true"
				titleKey="accounts.ifscCode" style="width: 5%" />
			<display:column property="branch" escapeXml="true" sortable="true"
				titleKey="accounts.branch" style="width: 15%" />
			<display:column property="city" escapeXml="true" sortable="true"
				titleKey="accounts.city" style="width: 10%" />

			<display:setProperty name="paging.banner.item_name">
				<fmt:message key="bankAccounts.title" />
			</display:setProperty>
			<display:setProperty name="paging.banner.items_name">
				<fmt:message key="bankAccounts.title" />
			</display:setProperty>

			<display:setProperty name="export.excel.filename"
				value="Bank Accounts List.xls" />
			<display:setProperty name="export.csv.filename"
				value="Bank Accounts List.csv" />
			<display:setProperty name="export.pdf.filename"
				value="Bank Accounts List.pdf" />
		</display:table>
	</div>
</div>
<hr/>
<div class="row">
	<div class="col-sm-12">
		<h4>
			<fmt:message key="onlineWallet.heading" />
		</h4>

		<div id="actions" class="btn-group">
			<a class="btn btn-primary"
				href="<c:url value='/user/walletDetail?method=Add&from=list'/>">
				<i class="icon-plus icon-white"></i> <fmt:message key="button.add" />
			</a> <a class="btn btn-default" href="<c:url value='/home'/>"> <i
				class="icon-ok"></i> <fmt:message key="button.done" /></a>
		</div>

		<display:table name="user.onlineWallets" cellspacing="0"
			cellpadding="0" requestURI="" defaultsort="1" id="wallets"
			pagesize="25" class="table table-condensed table-striped table-hover"
			export="true">
			<display:column property="walletName" escapeXml="true"
				sortable="true" titleKey="wallets.walletName" style="width: 15%"
				url="/user/walletDetail?from=list" paramId="id"
				paramProperty="walletId" />
			<display:column property="walletProfileName" escapeXml="true"
				sortable="true" titleKey="wallets.profileName" style="width: 25%" />
			<display:column property="walletMobileNumber" escapeXml="true"
				sortable="true" titleKey="wallets.mobileNumber" style="width: 25%" />
			<display:column property="walletEmail" escapeXml="true"
				sortable="true" titleKey="wallets.email" style="width: 15%" />

			<display:setProperty name="paging.banner.item_name">
				<fmt:message key="onlineWallets.title" />
			</display:setProperty>
			<display:setProperty name="paging.banner.items_name">
				<fmt:message key="onlineWallets.title" />
			</display:setProperty>

			<display:setProperty name="export.excel.filename"
				value="Online Wallet List.xls" />
			<display:setProperty name="export.csv.filename"
				value="Online Wallet List.csv" />
			<display:setProperty name="export.pdf.filename"
				value="Online Wallet List.pdf" />
		</display:table>
	</div>
</div>