<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="offer.title" /></title>
</head>

<div class="col-sm-2">
	<h2>
		<fmt:message key="offer.heading" />
	</h2>
</div>
<div class="col-sm-7">
	<spring:bind path="offer.*">
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

	<form:form commandName="offer" method="post" enctype="multipart/form-data"
		action="/user/add-offer" id="offerForm"
		autocomplete="off" cssClass="well">
		<form:hidden path="offerId" />
		<form:hidden path="offerHint"/>
		<form:hidden path="imagePath"/>
		<form:hidden path="merchantLogoPath"/>
		<form:hidden path="source"/>
		<form:hidden path="seoKeyword"/>
		<form:hidden path="userCount"/>
		<form:hidden path="createdOn"/>
		<form:hidden path="createdBy"/>
		<form:hidden path="enabled"/>
		<form:hidden path="expired"/>
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="offerTitle"
					id="offerTitle" placeholder="Title"/>
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
				<form:input cssClass="form-control" path="couponCode"
					id="copounCode" placeholder="Coupon Code"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="offerStart"
					id="offerStart" placeholder="Start"/>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="offerEnd"
					id="offerEnd" placeholder="End"/>
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
					id="labelsString" placeholder="Labels" value="offers,coupon"/>
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
    $(function () {
        $('#offerEnd').datetimepicker({
        	format: 'MM/DD/YYYY'
        });
        $('#offerStart').datetimepicker({
        	format: 'MM/DD/YYYY'
        });
    });

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
		//alert(window.location.origin);
		/* var link = window.location.origin+"/"+datum.value+"/";
		window.location.href = link; */
		/* $.ajax({
			type : "GET",
			url : "/get/offers",
			data : "label=" + datum.value + "&pageNo=" + 1,
			success : function(response) {
				if(response.length > 0){
					$("#pageNo").val(1);
					$("#offersList").html(response);
					$('#loadMore').attr('href',datum.value+'/#!/'+2);
					
				} else {
					$("#offersList").html("");
					$("#loadOffers").html('<p align="center"><button id="noOffer" class="btn btn-default">No more offers available.</button></p>');
				}
				//console.log("success");
			},
			error : function(e) {
				//console.log('Error: ' + e);
			}
		}); */
	});
	
</script>
