<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="report.title" /></title>
</head>

<div class="col-sm-2">
	<h2>
		<fmt:message key="report.heading" />
	</h2>
</div>
<div class="col-sm-7">
	<spring:bind path="report.*">
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

	<form:form commandName="report" method="post" enctype="multipart/form-data"
		action="/admin/saveReport" id="reportForm"
		autocomplete="off" cssClass="well">
		<form:hidden path="reportId" />
		<form:hidden path="merchantId" />
		<form:hidden path="merchantType" />
		<form:hidden path="createdOn" />
		<form:hidden path="createdBy" />
		<form:hidden path="owner.id" id="ownerId"/>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="merchantName"
					id="merchantName" placeholder="Merchant"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="targetLink"
					id="targetLink" placeholder="target link"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="offerId"
					id="offerId" placeholder="offerId"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="product"
					id="product" placeholder="Product"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="salesAmount"
					id="salesAmount" placeholder="salesAmount"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="merchantPayout"
					id="merchantPayout" placeholder="merchantPayout"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="payout"
					id="payout" placeholder="payout"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="conversionIp"
					id="conversionIp" placeholder="conversionIp"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="username"
					id="username" placeholder="username"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<input class="form-control" id="storeName" placeholder="Store Name" value="${report.owner.store.storeName}"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<%-- <select id="userRoles" name="userRoles" multiple="true" class="form-control">
	                <c:forEach items="${availableRoles}" var="role">
	                <option value="${role.value}" ${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
	                </c:forEach>
	            </select> --%>
				<form:select cssClass="form-control" path="status"
					id="status" placeholder="status">
					<option value="click">Click</option>
					<option value="conversion">Conversion</option>
					<option value="payout pending">Payout Pending</option>
					<option value="merchant reject">Merchant Reject</option>
					<option value="paid">Paid</option>
					<option value="cancelled">Cancelled</option>
				</form:select>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="checkbox-inline"> <form:checkbox
						path="enabled" id="enabled" /> <fmt:message
						key="report.enabled" />
				</label>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<button type="submit" class="btn btn-primary" name="save"
					onclick="bCancel=false">
					<i class="icon-ok icon-white"></i>
					<fmt:message key="button.save" />
				</button>
				<button type="submit" class="btn btn-default" name="cancel"
					onclick="bCancel=true">
					<i class="icon-remove"></i>
					<fmt:message key="button.cancel" />
				</button>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript">
	// Instantiate the Bloodhound suggestion engine
	var merchantNames = new Bloodhound({
	    datumTokenizer: function (datum) {
	        return Bloodhound.tokenizers.whitespace(datum.value);
	    },
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    remote: {
	        url: '/merchant/names?query=%QUERY',
	        wildcard: '%QUERY',
	        filter: function (data) {
	            // Map the remote source JSON array to a JavaScript object array
	            var merchants = JSON.parse(JSON.stringify(data));
	            return $.map(merchants, function (merchantName) {
	                return {
	                    value: merchantName
	                };
	            });
	        }
	    }
	});

	var users = new Bloodhound({
	    datumTokenizer: function (datum) {
	        return Bloodhound.tokenizers.whitespace(datum.value);
	    },
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    remote: {
	        url: '/report/users?query=%QUERY',
	        wildcard: '%QUERY',
	        filter: function (data) {
	            // Map the remote source JSON array to a JavaScript object array
	            var users = JSON.parse(JSON.stringify(data));
	            return $.map(users, function (user) {
	                return {
	                    value: user.ownerId,
	                    key: user.storeName
	                };
	            });
	        }
	    }
	});
	
	// Initialize the Bloodhound suggestion engine
	merchantNames.initialize();
	users.initialize();
	
	// Instantiate the Typeahead UI
	$('#merchantName').typeahead(null, {
	    displayKey: 'value',
	    source: merchantNames.ttAdapter()
	}).on('typeahead:selected', function (obj, datum) {
		//console.log(datum.value);
	});
	
	// Instantiate the Typeahead UI
	$('#storeName').typeahead(null, {
	    displayKey: 'key',
	    source: users.ttAdapter()
	}).on('typeahead:selected', function (obj, datum) {
		$('#ownerId').val(datum.value);
	});
</script>