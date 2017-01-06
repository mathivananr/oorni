<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="job.title" /></title>
</head>

<div class="col-sm-2">
	<h2>
		<fmt:message key="job.heading" />
	</h2>
</div>
<div class="col-sm-7">

	<form method="post" enctype="multipart/form-data"
		action="/admin/saveJob" id="jobForm"
		autocomplete="off" class="well">
		<div class="row">
			<div class="form-group">
				<input type="text" class="form-control" name="jobName"
					id="jobName" placeholder="Job Name" value="${jobName}"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<select class="form-control" name="jobClass"
					id="jobClass" placeholder="Job Class">
					<option value="com.oorni.job.ProcessFlipkartOffers" ${"com.oorni.job.ProcessFlipkartOffers" == jobClass ? 'selected' : ''}>com.oorni.job.ProcessFlipkartOffers</option>
					<option value="com.oorni.job.ProcessPayoomOffers" ${"com.oorni.job.ProcessPayoomOffers" == jobClass ? 'selected' : ''}>com.oorni.job.ProcessPayoomOffers</option>
					<option value="com.oorni.job.SampleJob.java" ${"com.oorni.job.SampleJob.java" == jobClass ? 'selected' : ''}>com.oorni.job.SampleJob.java</option>
				</select>
				<%-- <input type="text" class="form-control" name="jobClass"
					id="jobClass" placeholder="Job Class" value="${jobClass}"/> --%>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<input type="text" class="form-control" name="interval"
					id="interval" placeholder="Interval in minutes" value="${interval}"/>
			</div>
		</div>
		
		<!-- <div class="row">
			<div class="form-group">
				<select class="form-control" name="status"
					id="status" placeholder="status">
					<option value="click">Click</option>
					<option value="conversion">Conversion</option>
					<option value="payout pending">Payout Pending</option>
					<option value="merchant reject">Merchant Reject</option>
					<option value="paid">Paid</option>
					<option value="cancelled">Cancelled</option>
				</select>
			</div>
		</div> -->

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
	</form>
</div>