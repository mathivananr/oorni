<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="store.title" /></title>
</head>

<div class="col-sm-2"></div>
<div class="col-sm-7">

	<%-- <h2>
		<fmt:message key="store.heading" />
	</h2>
	<hr /> --%>
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

	<form:form commandName="store" method="post"
		enctype="multipart/form-data" action="/createStore" id="storeForm"
		autocomplete="off" cssClass="">
		<form:hidden path="storeId" />
		<form:hidden path="createdOn" />
		<form:hidden path="enabled" />

		<security:authentication var="user" property="principal" />
		<c:choose>
			<c:when test="${user == null || user == 'anonymousUser'}">
				<h3>
					Account Details
				</h3>
				
				<hr />
				
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label for="email" class="control-label pull-right"> Create new account by filling account details or <a data-toggle="modal" data-target="#myModal" href="#myModal" >login</a> using your existing account. </label>
				</div>
				
				<div class="row">
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
						<label for="email" class="control-label pull-right">email : </label>
					</div>
					<div class="form-group col-lg-10 col-md-10 col-sm-10 col-xs-12">
						<input type="email" class="form-control input-border-bottom"
							id="email" name="email" value="" required
							title="Please enter you email" placeholder="example@gmail.com">
						<span class="help-block"></span>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
						<label for="password" class="control-label pull-right">password : </label>
					</div>
					<div class="form-group col-lg-10 col-md-10 col-sm-10 col-xs-12">
						<input type="password" class="form-control input-border-bottom"
							id="password" name="password" value="" required
							title="Please enter your password" placeholder="password"> <span
							class="help-block"></span>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
					
		<h3>
			Store Details
		</h3>
		<hr />
		
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
				<label for="storeName" class="control-label pull-right">Name : </label>
			</div>
			<div class="form-group col-lg-10 col-md-10 col-sm-10 col-xs-12">
				<form:input cssClass="form-control input-border-bottom" required="required"
					path="storeName" id="storeName" placeholder="Store Name" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
				<label for="title" class="control-label pull-right">Title : </label>
			</div>
			<div class="form-group col-lg-10 col-md-10 col-sm-10 col-xs-12">
				<form:input cssClass="form-control input-border-bottom" path="title"
					id="title" placeholder="Title" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
				<label for="title" class="control-label pull-right">Description : </label>
			</div>
			<div class="form-group col-lg-10 col-md-10 col-sm-10 col-xs-12">
				<form:textarea cssClass="form-control input-border-bottom"
					path="description" id="description" placeholder="Description" />
			</div>
		</div>
		<div class="row">
			<div class="form-group pull-right">
				<%-- <button type="button" class="btn btn-default" name="cancel"
					onclick="bCancel=true">
					<i class="icon-remove"></i>
					<fmt:message key="button.cancel" />
				</button> --%>
				<button type="submit" class="btn btn-primary" name="save"
					onclick="bCancel=false">
					<i class="icon-ok icon-white"></i>
					Create
				</button>
			</div>
		</div>
	</form:form>
</div>
