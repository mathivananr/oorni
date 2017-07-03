<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="carouselList.title"/></title>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="carouselList.heading"/></h2>

    <form method="get" action="${ctx}/carousels" id="searchForm" class="form-inline">
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
        <a class="btn btn-primary" href="<c:url value='/admin/add-carousel?method=Add&from=list'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="carouselList" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="carousels" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="carouselId" escapeXml="true" sortable="true" titleKey="carousels.carouselId" style="width: 5%"
                        url="/admin/edit-carousel?from=list" paramId="carouselId" paramProperty="carouselId"/>
        <display:column property="merchantName" escapeXml="true" sortable="true" titleKey="carousels.merchantName"
                        style="width: 10%"/>
        <display:column property="carouselTitle" escapeXml="true" sortable="true" titleKey="carousels.title"
                        style="width: 15%"/>
        <display:column property="description" escapeXml="true" sortable="true" titleKey="carousels.description"
                        style="width: 15%"/>
        <display:column property="contentType" escapeXml="true" sortable="true" titleKey="carousels.contentType"
                        style="width: 5%"/>
		<display:column sortProperty="enabled" sortable="true" titleKey="carousels.enabled"
                        style="width: 16%; padding-left: 15px" media="html">
            <input type="checkbox" disabled="disabled" <c:if test="${carousels.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="enabled" titleKey="carousels.enabled" media="csv xml excel pdf"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="carouselList.carousel"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="carouselList.carousels"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Carousel List.xls"/>
        <display:setProperty name="export.csv.filename" value="Carousel List.csv"/>
        <display:setProperty name="export.pdf.filename" value="Carousel List.pdf"/>
    </display:table>
</div>
