<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="payments.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><fmt:message key="payments.heading"/></h2>
	<hr/>
	<br/>
    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/user/paymentRequest?method=Add&from=list'/>">
            <i class="icon-plus icon-white"></i> New Payment Request</a>

        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="payments" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="payment" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <security:authorize  ifAllGranted="ROLE_ADMIN">
        	<display:column property="paymentId" escapeXml="true" sortable="true" titleKey="user.username" style="width: 5%"
                        url="/user/paymentRequest?from=list" paramId="id" paramProperty="paymentId"/>
        </security:authorize>
        <security:authorize  ifAllGranted="ROLE_USER">
        	<display:column value="${payment_rowNum}" title="No" escapeXml="true" sortable="true"/>
        </security:authorize>
        <display:column property="paymentMode" escapeXml="true" sortable="true" titleKey="payment.paymentMode"
                        style="width: 14%"/>
        <display:column property="paymentAmount" sortable="true" titleKey="payment.paymentAmount" style="width: 10%" autolink="true"/>
        <display:column property="createdOn.time" sortable="true" titleKey="payment.requestOn" style="width: 15%" autolink="true"
                        format="{0,date}"/>
	  	<display:column property="bankAccount.bankName" sortable="true" titleKey="payment.bankAccount.bankName" style="width: 15%" autolink="true"
                       />
        <display:column property="bankAccount.accountNo" sortable="true" titleKey="payment.bankAccount.accountNo" style="width: 15%" autolink="true"
                       />
        <display:column property="bankAccount.accountName" sortable="true" titleKey="payment.bankAccount.accountName" style="width: 15%" autolink="true"
                       />
	 	<display:column property="onlineWallet.walletName" sortable="true" titleKey="payment.onlineWallet.walletName" style="width: 15%" autolink="true"
                       />
        <display:column property="onlineWallet.walletMobileNumber" sortable="true" titleKey="payment.onlineWallet.walletMobileNumber" style="width: 15%" autolink="true"
                       />
        <display:column property="status" titleKey="payment.status" />

        <display:setProperty name="paging.banner.item_name"><fmt:message key="payments.payment"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="payments.payments"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Payment List.xls"/>
        <display:setProperty name="export.csv.filename" value="Payment List.csv"/>
        <display:setProperty name="export.pdf.filename" value="Payment List.pdf"/>
    </display:table>
</div>
