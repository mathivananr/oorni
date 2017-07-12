<%@ include file="/common/taglibs.jsp"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<head>
	<title>${label}</title>
</head>
<div id="offers-container" class="offers-container col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<input type="hidden" id="label" value="${label}"/>
	<input type="hidden" id="pageNo" value="${pageNo}" />
	<div class="card card-block">
		<div class="row-nomargin">
			<h1><p class="theme-color text-capitalize">${label}</p></h1>
		</div>
	</div>
	<div class="" id="offersList">
		<c:forEach var="offer" items="${offers}">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 card card-block">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div id="offer-left" class="col-lg-2 col-md-2 col-sm-4 col-xs-12">
						<div id="offer-image" class="offer-image">
							<c:choose>
							  <c:when test="${not empty offer.imagePath}">
							    <div class="text-center"><a href="${offer.URL}" ><img style="max-height: 150px;" class="col-lg-12 col-md-12 col-sm-10 col-xs-10" alt="${offer.merchantName}" data-src = "${offer.imagePath}" src=""></a></div>
							  </c:when>
							 <%--  <c:when test="${not empty offer.merchantLogoPath}">
							    <div class="text-center"><a href="${offer.merchantLogoPath}" ><img class="col-lg-12 col-md-12 col-sm-10 col-xs-10" alt="${offer.merchantName}" data-src="${offer.merchantLogoPath}" src=""></a></div>
							  </c:when> --%>
							  <c:otherwise>
							  	<div class="text-center"><a href="/${offer.merchantName}" ><img class="col-lg-12 col-md-12 col-sm-10 col-xs-10 offer-image-merchant-name" alt="${offer.merchantName}" data-src="${offer.merchantLogoPath}" src="/images/merchant/logo/${fn:toLowerCase(fn:replace(offer.merchantName,' ', '_'))}.jpg"></a></div>
							    <%-- <div class="text-center"><p class="col-lg-10 col-md-10 col-sm-12 col-xs-12 offer-image-merchant-name">${offer.merchantName}</p></div> --%>
							  </c:otherwise>
						  </c:choose>
						 </div>
					</div>
					<div id="offer-center" class="col-lg-7 col-md-7 col-sm-5 col-xs-12">
						<div id="offer-title" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 margin-bottom10"><h4><strong>${offer.offerTitle}</strong></h4></div>
						<div id="offer-body" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p>${fn:replace(offer.description, newLine, '<br />')}</p>
						</div>
					</div>
					<div id="offer-right" class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
							<div class="text-center"><p class="col-lg-10 col-md-10 col-sm-10 col-xs-10 offer-hint">${offer.offerHint}</p></div>
							<div class="text-center"><p class="col-md-10 col-sm-10 col-xs-10"><a href="${offer.URL}" class="btn btn-oorni" target="_blank">VISIT OFFER</a></p></div>
							<c:choose> 
								 <c:when test="${not empty offer.couponCode}">
								   <div class="text-center"><p class="col-lg-10 col-md-10 col-sm-10 col-xs-10 offer-coupon-code">Code : ${offer.couponCode}</p></div>
								 </c:when>
								 <c:otherwise>
								   <div class="text-center"><p class="col-lg-10 col-md-10 col-sm-10 col-xs-10 offer-coupon-code">Deal Active</p></div>
								 </c:otherwise>
							</c:choose>
							<div class="text-center">
								<p class="col-lg-10 col-md-10 col-sm-10 col-xs-10 offer-valid">
									End : <c:choose>
											  <c:when test="${not empty offer.formattedEnd}">
											    ${offer.formattedEnd}
											  </c:when>
											  <c:otherwise>
											    Until Stock
											  </c:otherwise>
										  </c:choose>
								</p>
							</div>
					</div>
					<hr/>
					<div id="offer-footer" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<c:forEach var="offerLabel" items="${offer.labels}">
									<c:if test="${offerLabel.isHidden == false}">
										<span><a href="/${fn:replace(offerLabel.label, ' ', '-')}/" title="${offerLabel.label}" class="tag tag-info text-capitalize">${offerLabel.label}</a></span>
									</c:if>
								</c:forEach>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="loadOffers" class="ajax-load-more lazyLoader">
		<div class="btn-load-more btn-loadmore" data-pageno="1">
			<c:choose>
			  <c:when test="${not empty label}">
			    <nav>
				  <ul class="pager">
				  	<c:if test="${pageNo gt 1}">
				    	<li><a href="/${label}/page/${pageNo-1}">Previous</a></li>
				    </c:if>
				    <li><a href="#!/${pageNo}" id="loadMore" class="btn btn-default">Load More Offers</a></li>
				    <li><a href="/${label}/page/${pageNo+1}">Next</a></li>
				  </ul>
				</nav>
			  </c:when>
			  <c:otherwise>
			    <nav>
				  <ul class="pager">
				  	<c:if test="${pageNo gt 1}">
				    	<li><a href="/page/${pageNo-1}">Previous</a></li>
				    </c:if>
				    <li><a href="javascript:void(0);" id="loadMore" class="btn btn-default">Load More Offers</a></li>
				    <li><a href="/page/${pageNo+1}">Next</a></li>
				  </ul>
				</nav>
			  </c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		var label = window.location.pathname;
		if(label) {
			var labels = label.split('/');
			if(labels.length <=3) {
				if(labels[1] == '') {
					loadCarousel('home');
				} else {
					loadCarousel(labels[1]);
				}
			}
		}
	});
</script>