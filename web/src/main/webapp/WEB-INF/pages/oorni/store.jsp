<%@ include file="/common/taglibs.jsp"%>

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div class="row pad-B-10">
		<div class="fb-share-button pull-right" data-href="http://localhost:2020/store/${store.storeName}" data-layout="button" data-size="large" data-mobile-iframe="false"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http://localhost:2020/store/${store.storeName}&amp;src=sdkpreparse">Share</a></div>
	</div>
	<!-- tabs left -->
	<div class="row pad-B-10">
		<div class="tabbable tabs-left col-lg-3 col-md-3 col-sm-4 col-xs-4">
			<ul class="nav nav-tabs " role="tablist">
				<li class="nav-item active col-lg-12 col-md-12 col-sm-12 col-xs-12"><a class="noborder link-color nav-link"
					href="#all" data-toggle="tab" role="tab">All</a></li>
				<c:forEach var="merchantType" items="${merchantTypeList}">
					<li class="nav-item col-lg-12 col-md-12 col-sm-12 col-xs-12"><a class="noborder link-color nav-link"
						href="#${fn:replace(merchantType.typeName,' ', '_')}" data-toggle="tab" role="tab">${merchantType.typeName}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="tab-content col-lg-9 col-md-9 col-sm-8 col-xs-8">
			<div class="tab-pane active col-lg-12 col-md-12 col-sm-12 col-xs-12" id="all" role="tabpanel">
				<c:forEach var="merchantType" items="${merchantTypeList}">
					<div class="row pad-B-10">
						<div class="page-header  pad-L-10 text-capitalize">
							<h3>${merchantType.typeName}</h3>
							<hr/>
						</div>
						<c:forEach var="merchant" items="${merchantType.merchants}">
							<c:if test="${merchant.storeEnabled eq true}">
								<div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
									<div class="thumbnail">
										<a title="click ${merchant.merchantName} and start your shopping" href="/hook/${store.storeName}/${merchant.merchantName}" target="_blank"><img
											src="${merchant.logoPath}" alt="${merchant.description}"
											width="100%" class="slider-store-image"> </a>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<c:forEach var="merchantType" items="${merchantTypeList}">
				<div class="tab-pane col-lg-12 col-md-12 col-sm-12 col-xs-12" id="${fn:replace(merchantType.typeName,' ', '_')}" role="tabpanel">
					<div class="page-header  pad-L-10 text-capitalize">
						<h3>${merchantType.typeName}</h3>
						<hr/>
					</div>
					<c:forEach var="merchant" items="${merchantType.merchants}">
						<div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
							<div class="thumbnail">
								<a title="click ${merchant.merchantName} and start your shopping" href="/hook/${store.storeName}/${merchant.merchantName}" target="_blank"><img
									src="${merchant.logoPath}" alt="${merchant.description}"
									width="100%" class="slider-store-image" > </a>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
</div>