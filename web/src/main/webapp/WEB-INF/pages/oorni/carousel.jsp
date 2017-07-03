<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="carousel.title" /></title>
</head>

<div class="col-sm-2">
	<h2>
		<fmt:message key="carousel.heading" />
	</h2>
</div>
<div class="col-sm-7">
	<spring:bind path="carousel.*">
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

	<form:form commandName="carousel" method="post" enctype="multipart/form-data"
		action="/admin/add-carousel" id="carouselForm"
		autocomplete="off" cssClass="well">
		<form:hidden path="carouselId" />
		<form:hidden path="imagePath"/>
		<form:hidden path="createdOn"/>
		<form:hidden path="createdBy"/>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="carouselTitle"
					id="carouselTitle" placeholder="Title"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<form:textarea cssClass="form-control" path="description"
					id="description" placeholder="Description"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="merchantName"
					id="merchantName" placeholder="Merchant"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<form:select cssClass="form-control" path="contentType"
					id="contentType" placeholder="Cntent Type">
					<form:option value="html">HTML</form:option>
					<form:option value="image">IMAGE</form:option>
					<form:option value="text">TEXT</form:option>
				</form:select>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="content"
					id="content" placeholder="Content"/>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="targetURL"
					id="targetURL" placeholder="URL"/>
			</div>
		</div>
				
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="labelsString"
					id="labelsString" placeholder="Labels" value="carousels,coupon"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label class="checkbox-inline"> <form:checkbox
						path="enabled" id="enabled" /> <fmt:message
						key="merchant.enabled" />
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
				<button type="button" class="btn btn-default" name="cancel"
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
	var merchantData = new Bloodhound({
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

	// Initialize the Bloodhound suggestion engine
	merchantData.initialize();

	// Instantiate the Typeahead UI
	$('#merchantName').typeahead(null, {
	    displayKey: 'value',
	    source: merchantData.ttAdapter()
	}).on('typeahead:selected', function (obj, datum) {
		console.log(datum.value);
		if($('#labelsString').val() != null && $('#labelsString').val().length > 0) {
			$('#labelsString').val($('#labelsString').val() + ',' + datum.value)
		} else {
			$('#labelsString').val(datum.value)
		}
	});
	
</script>
</html>