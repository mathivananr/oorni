<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="store.title" /></title>
</head>

<div class="col-sm-2">
</div>
<div class="col-sm-7">
	
	<h2>
		<fmt:message key="store.heading" />
		<hr/>
	</h2>
	
	<spring:bind path="store.*">
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

	<form:form commandName="store" method="post" enctype="multipart/form-data"
		action="/user/saveStore" id="storeForm"
		autocomplete="off" cssClass="">
		<form:hidden path="storeId" />
		<form:hidden path="createdOn"/>
		<form:hidden path="enabled"/>
		
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="storeName"
					id="storeName" placeholder="Name"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<form:input cssClass="form-control" path="title"
					id="title" placeholder="Title"/>
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
