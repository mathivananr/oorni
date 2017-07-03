<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="report.title"/></title>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><fmt:message key="report.heading"/></h2>
	<hr/>
	<div class="row">
		<div class="col-lg-2 col-md-2">
		</div>
		<div class="col-lg-10 col-md-10 col-xm-12 col-sm-12">
		  <div class="row">
		    <div class="col-lg-4 col-md-4 col-xm-4 col-sm-4">
		      <div class="well">
		        <h4 class="text-primary"><span class="label label-primary pull-right">${clicks}</span> Clicks </h4>
		      </div>
		    </div>
		    <div class="col-lg-4 col-md-4 col-xm-4 col-sm-4">
		      <div class="well">
		        <h4 class="text-success"><span class="label label-success pull-right">${conversions}</span> Conversions </h4>
		      </div>
		    </div>
		    <div class="col-lg-4 col-md-4 col-xm-4 col-sm-4">
		      <div class="well">
		        <h4 class="text-danger">
		        	<span class="label label-danger pull-right">
			        	<c:choose>
						    <c:when test="${empty payout}">
						        0
						    </c:when>
						    <c:otherwise>
						        ${payout}
						    </c:otherwise>
						</c:choose>
					</span> 
					Payout 
				</h4>
		      </div>
		    </div>
		  </div><!--/row-->    
		</div><!--/col-12-->
	</div><!--/row-->
	<hr/>
    <%-- <form method="get" action="${ctx}/user/report" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-9">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm">
        </span>
        <button id="button.search" class="btn btn-default btn-sm" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form> --%>

   <%--  <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/admin/report?method=Add&from=list'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div> --%>

    <display:table name="report" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="report" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="reportId" escapeXml="true" sortable="true" titleKey="report.reportId" style="width: 10%" />
        <display:column property="merchantName" escapeXml="true" sortable="true" titleKey="report.merchantName"
                        style="width: 15%"/>
        <display:column property="product" escapeXml="true" sortable="true" titleKey="report.product"
        style="width: 10%"/>
        <display:column property="conversionIp" escapeXml="true" sortable="true" titleKey="report.conversionIp"
        style="width: 10%"/>
        <display:column property="salesAmount" escapeXml="true" sortable="true" titleKey="report.salesAmount"
                        style="width: 10%"/>
        <display:column property="payout" escapeXml="true" sortable="true" titleKey="report.payout"
                        style="width: 10%"/>
        <display:column property="status" escapeXml="true" sortable="true" titleKey="report.status"
                        style="width: 10%"/>
        <%-- <display:column sortProperty="enabled" sortable="true" titleKey="report.enabled"
                        style="width: 16%; padding-left: 15px" media="html">
            <input type="checkbox" disabled="disabled" <c:if test="${report.enabled}">checked="checked"</c:if>/>
        </display:column> --%>
        <display:column property="enabled" titleKey="report.enabled" media="csv xml excel pdf"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="report.report"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="report.report"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Report.xls"/>
        <display:setProperty name="export.csv.filename" value="Report.csv"/>
        <display:setProperty name="export.pdf.filename" value="Report.pdf"/>
    </display:table>
</div>
