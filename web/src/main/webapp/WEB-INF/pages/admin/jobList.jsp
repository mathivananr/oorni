<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="job.title"/></title>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="job.heading"/></h2>

    <form method="get" action="${ctx}/admin/jobs" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-9">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm">
        </span>
        <button id="button.search" class="btn btn-default btn-sm" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/admin/jobform?method=Add&from=list'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="jobList" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="jobs" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="jobName" escapeXml="true" sortable="true" titleKey="jobs.jobName" style="width: 15%"
                        url="/admin/jobform?from=list" paramId="jobName" paramProperty="jobName"/>
        <display:column property="groupName" escapeXml="true" sortable="true" titleKey="jobs.groupName"
                        style="width: 15%"/>
        <display:column property="jobClass" escapeXml="true" sortable="true" titleKey="jobs.class"
                        style="width: 15%"/>
        <display:column property="interval" escapeXml="true" sortable="true" titleKey="jobs.interval"
        style="width: 7%"/>
        <display:column property="previousFireTime" escapeXml="true" sortable="true" titleKey="jobs.previousFireTime"
                        style="width: 10%"/>
		<display:column property="nextFireTime" escapeXml="true" sortable="true" titleKey="jobs.nextFireTime"
                        style="width: 10%"/>
        <display:column property="status" escapeXml="true" sortable="true" titleKey="jobs.status"
                        style="width: 10%"/>
        <display:column escapeXml="true" sortable="true" title="Start" style="width: 10%"
                        url="/admin/startJob?from=list" paramId="jobName" paramProperty="jobName">start</display:column>
        <display:column escapeXml="true" sortable="true" title="Stop" style="width: 10%"
                        url="/admin/stopJob?from=list" paramId="jobName" paramProperty="jobName">stop</display:column>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="job.job"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="job.jobs"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Job List.xls"/>
        <display:setProperty name="export.csv.filename" value="Job List.csv"/>
        <display:setProperty name="export.pdf.filename" value="Job List.pdf"/>
    </display:table>
</div>
