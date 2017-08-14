<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="en" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
	<!-- meta charec set -->
	<meta charset="utf-8">
	<!-- Always force latest IE rendering engine or request Chrome Frame -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><decorator:title/></title>
	<!-- <title>oorni</title> -->
	<!-- Store Title -->
	<%-- <c:choose>
		<c:when test="${not empty storeTitle}">
			<title>${storeTitle}</title>
		</c:when>
		<c:when test="${not empty appTitle}">
			<title>${appTitle}</title>
		</c:when>
	</c:choose> --%>
	
	<c:choose>
		<c:when test="${not empty metaKeywords}">
			<meta name="keywords" content="${metaKeywords}">
		</c:when>
		<c:when test="${not empty appMetaKeyword}">
			<meta name="keywords" content="${appMetaKeyword}">
		</c:when>
		<c:otherwise>
			<meta name="keywords" content="cashback, shopping cashback, coupons, today offers, best cashback site, best cashback site in india">
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${not empty metaDescription}">
			<meta name="description" content="${metaDescription}">
		</c:when>
		<c:otherwise>
			<meta name="description" content="${appMetaDescription}">
		</c:otherwise>
	</c:choose>
	
	
	<meta property="og:title" content="${ogTitle}" />
	<meta property="og:site_name" content="${appName}" />
	<meta property="og:url" content="${ogURL}" />
	<meta property="og:image" content="${appUrl}/${ogImage}" />
	<meta property="og:description" content="${ogDescription}" />
	<meta name="author" content="${auther}">
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="/images/favicon1.ico" />
	<link rel="stylesheet" type="text/css"
		href="${storeContext.request.contextPath}/assets/v/${applicationScope.assetsVersion}/oorni.css" />
	<script type="text/javascript"
		src="${storeContext.request.contextPath}/assets/v/${applicationScope.assetsVersion}/oorni.js"></script>
</head>

<body id="body">
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.9";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<input type="hidden" id="active-menu" value="${activeMenu}" />
	<input type="hidden" id="scrollReached" value="false" />
	<!-- preloader -->
	<!-- <div id="preloader">
		<img src="/images/core/preloader.gif" alt="Preloader">
	</div> -->
	<!-- end preloader -->

	<!--         Fixed Navigation
        ==================================== -->
<!-- <div class="pull-right right-strap beta">
		<img alt="beta version" src="/images/beta.png" height=75 width=90 />
</div> -->

	<header id="navigation" class="navbar">
		<!-- <div class="container"> -->

		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
			<div class="search-row" >
				<a class="navbar-brand" href="/" style="margin-top:2px;"><h1>oorni</h1></a>
			</div>
		</div>  
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-8 ">
			<!-- <div class="dtable hw100">
				<div class="dtable-cell hw100">
					<div class="text-center">
						<div class="search-row animated fadeInUp">
							<div
								class="col-lg-10 col-md-10 col-sm-10 col-xs-9 search-col relative">
								<i class="icon-docs icon-append"></i> <input type="text"
									name="search" id="search" class="form-control has-icon"
									placeholder="Search">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 search-col">
								<button class="btn btn-primary btn-search btn-block">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div> -->
		</div>

		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<div class="search-row pull-right" role="navigation">
				<security:authentication var="user" property="principal" />
				<ul class="nav nav-pills">
					<c:choose>
						<c:when
							test="${user != null && user != 'anonymousUser' && user.store != null && user.store.storeName != null && user.store.storeName != ''}">
							<li class="nav-item"><a class="nav-link" href="/store/${user.store.storeName}"> 
								<i class="fa fa-th-large" aria-hidden="true"></i>
								<p>View Store</p>
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<!-- <li class="nav-item"><a class="nav-link" href="/createStore"> 
								<i class="fa fa-th-large" aria-hidden="true"></i>
								<p>Create Store</p>
								</a>
							</li> -->
							<li class="nav-item text-center"><a class="nav-link" href="/cashback"> <i
									class="fa fa-inr" aria-hidden="true"></i>
									<p>Cashback</p>
							</a></li>
						</c:otherwise>
					</c:choose>
					
					<li class="nav-item"><a class="nav-link" href="/chat"> <i
							class="fa fa-comment" aria-hidden="true"></i>
							<p>Chat</p>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="/offers"> <i
							class="fa fa-refresh" aria-hidden="true"></i>
							<p>Offers</p>
					</a></li>
					<li class="nav-item dropdown">
						<!-- <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
				    <div class="dropdown-menu">
				      <a class="dropdown-item" href="#">Action</a>
				      <a class="dropdown-item" href="#">Another action</a>
				      <a class="dropdown-item" href="#">Something else here</a>
				      <div class="dropdown-divider"></div>
				      <a class="dropdown-item" href="#">Separated link</a>
				    </div> --> <!-- <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
				    	<i class="fa fa-user" aria-hidden="true"></i>
				    </a> --> <a id="account-menu" href="#account"
						class="nav-link dropdown-toggle" data-toggle="dropdown"> <i
							class="fa fa-user" aria-hidden="true"></i> <i
							class="fa fa-sort-desc" aria-hidden="true"></i>
							<p>Account</p>
					</a>
						<ul class="dropdown-menu" id="account-menus">
							<c:choose>
								<c:when test="${user != null && user != 'anonymousUser'}">
									<li>
										<span>Welcome 
											<b>
												<c:choose>
												    <c:when test="${empty user.firstName || empty user.lastName}">
												    	 ${user.username}
												    </c:when>
												    <c:otherwise>
												    	${user.fullName}
												    </c:otherwise>
												</c:choose>
											</b>
										</span>
									</li>
									<li class="dropdown-divider"></li>
									<li><a href="/userform">Edit Profile</a></li>
									<li class="dropdown-divider"></li>
									<c:if test="${pageContext.request.remoteUser == user.username}">
						           		<li>
						           			<a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
						               	</li>
						               	<li class="dropdown-divider"></li>
						            </c:if>
									<c:choose>
										<c:when
											test="${user.store.storeName != null && user.store.storeName != ''}">
											<li><a href="/store/${user.store.storeName}">View
													Store</a></li>
											<li class="dropdown-divider"></li>
											<li><a href="/user/editStore">Edit Store</a></li>
											<li class="dropdown-divider"></li>
											<li><a href="/user/report">Revenue Report</a></li>
											<li class="dropdown-divider"></li>
											<li><a href="/user/paymentRequest">New Payment Request</a></li>
											<li class="dropdown-divider"></li>
											<li><a href="/user/payments">Payment History</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="/createStore">Create Store</a></li>
										</c:otherwise>
									</c:choose>
									<li class="dropdown-divider"></li>
									<!-- <li><a href="/user/offer-list">Offers</a></li>
									<li class="dropdown-divider"></li>
									<li><a href="/user/add-offer">Add Offer</a></li>
									<li class="dropdown-divider"></li> -->
									<li><a class="btn btn-default" href="/logout">Logout</a></li>
								</c:when>
								<c:otherwise>
									<li>
										<div class="text-center text-xs-center">
											<div id="login-link" class="btn btn-secondary"
												data-toggle="modal" data-target="#myModal" href="#myModal">Login</div>
										</div>
									</li>
									<li><div class="account-signup">
											New User? <a data-toggle="modal" data-target="#myModal"
												href="#myModal">Register</a>
										</div>
								</c:otherwise>
							</c:choose>
						</ul>
					</li>
				</ul>
				<%-- <ul id="nav" class="nav nav-pills">
					<li class="text-center">
						<a href="/chat">
							<i class="fa fa-comment" aria-hidden="true"></i>
							<p>Chat</p>
						</a>
					</li>
					<li class="text-center">
						<a href="/offers">
							<i class="fa fa-refresh" aria-hidden="true"></i>
							<p>Offers</p>
						</a>
					</li>
					<li class="text-center dropdown">
						<a href="#account" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-user" aria-hidden="true"></i>
							<i class="fa fa-sort-desc" aria-hidden="true"></i>
							<p>Account</p>
						</a>
                        <ul class="dropdown-menu" id="account-menus">
							<security:authentication var="user" property="principal" />
							<c:choose>
								<c:when test="${user != null && user != 'anonymousUser'}">
									<li>
											<span>Welcome <b>${user.username}</b></span>
									</li>
									<li class="divider"></li>
									<li>
											<a href="/userform" >Edit Profile</a>
									</li>
									<li class="divider"></li>
									<c:choose>
										<c:when test="${user.store.storeName != null && user.store.storeName != ''}">
											<li>
												<a href="/store/${user.store.storeName}" >View Store</a>													
											</li>
											<li class="divider"></li>
											<li>
												<a href="/user/editStore" >Edit Store</a>													
											</li>
											<li class="divider"></li>
											<li>
												<a href="/user/report" >Report</a>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="/user/createStore" >Create Store</a>
											</li>	
										</c:otherwise>
									</c:choose>
									<li class="divider"></li>
									<li>
											<a href="/user/offer-list" >Offers</a>
									</li>
									<li class="divider"></li>
									<li>
											<a href="/user/add-offer" >Add Offer</a>
									</li>
									<li class="divider"></li>
									<li>
											<a class="btn btn-default" href="/logout">Logout</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<div class="text-center">
											<div id="login-link" class="btn btn-default" data-toggle="modal"
												data-target="#myModal" href="#myModal">Login</div>
										</div>
									</li>
									<li><div class="account-signup">
											New User? <a data-toggle="modal"
												data-target="#myModal" href="#myModal">Register</a>
										</div>
								</c:otherwise>
							</c:choose>
						</ul>
                    </li>
				</ul> --%>
			</div>
		</div>


		<div class="col-lg-1 col-md-1 col-sm-1 col-xs-2 hide">
			<!-- <nav class='sidebar sidebar-menu-collapsed'> 
				<a href='javascript void(0);' id='justify-icon'>
        			<span class='fa fa-bars pull-right'></span>
     			</a>
				<div id="side-menu" class="side-menu hide">
			        <ul class='level1'>
			            <li class='parent-menu'> 
			            	<a class='' href='/offers' title='Offers'>
					            <span class='glyphicon glyphicon-home collapsed-element'></span>
					            <span class='expanded-element'>Offers</span>
					        </a>
			
			                <ul class='sub-menu' style="display:none;">
			                    <li> <a href='#' title='Traffic'>Traffic</a>
			
			                    </li>
			                    <li> <a href='#' title='Conversion rate'>Conversion rate</a>
			
			                    </li>
			                    <li> <a href='#' title='Purchases'>Purchases</a>
			
			                    </li>
			                </ul>
			            </li>
			            <li> 
			            	<a class='expandable' href='/chat' title='Chat'>
			            		<span class='expanded-element'>Chat</span>
			          		</a>
			
			            </li>
			            <li> 
			            	<a class='expandable' href='/electronics' title='Electronics'>
					            <span class='expanded-element'>Electronics</span>
					        </a>
			
			            </li>
			            <li> 
			            	<a class='expandable' href='/mobiles' title='Mobiles'>
			            		<span class='expanded-element'>Mobiles</span>
			          		</a>
			           </li>
			        </ul> 
			        <a href='/logout' id='logout-icon' title='Logout'>
	        			<span class='glyphicon glyphicon-off'></span>
	      			</a>
      			</div>
    		</nav> -->
			<%-- <a class="navbar-brand" href="${applicationUrl}">
					<h1 id="logo">
						<img src="images/core/logo.png" alt="${applicationName}">
						LOGO
					</h1>
				</a> --%>
			<%-- <div class="navbar-header">
				<!--     responsive nav button -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <i
						class="fa fa-bars fa-2x"></i>
				</button>
				<!-- responsive nav button -->

				<!-- logo -->
				<a class="navbar-brand" href="${applicationUrl}">
					<h1 id="logo">
						<img src="images/core/logo.png" alt="${applicationName}">
					</h1>
				</a>
				<!-- logo -->
			</div> --%>
			<!-- main nav -->
			<!-- <nav class="collapse navbar-collapse navbar-right" role="navigation">
				<ul id="nav" class="nav navbar-nav">
					<li class="current"><a href="#body">Home</a></li>
					<li><a href="#features">Features</a></li>
					<li><a href="#works">Work</a></li>
					<li><a href="#team">Team</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
				<ul class="nav pull-right">
		          <li class="dropdown" id="menuLogin">
		            <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">Login</a>
		            <div class="dropdown-menu" style="padding:17px;">
		              <form class="form" id="formLogin"> 
		                <input name="username" id="username" type="text" placeholder="Username"> 
		                <input name="password" id="password" type="password" placeholder="Password"><br>
		                <button type="button" id="btnLogin" class="btn">Login</button>
		              </form>
		            </div>
		          </li>
		        </ul>
			</nav> -->
			<!-- main nav -->

		</div>
		<!-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div id="the-basics">
				  <input class="typeahead" id="search" type="text" placeholder="Search">
				</div>
			</div>
		</div> -->
		<!-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
			<div class="dtable hw100">
				<div class="dtable-cell hw100">
					<div class="row search-row">
						<a href="/user/createStore" class="btn btn-danger pull-right">Create Store</a>
					</div>
				</div>
			</div>
			<ul id="nav" class="nav navbar-nav">
					<li class="current"><a href="#body">Home</a></li>
					<li><a href="#features">Features</a></li>
					<li><a href="#works">Work</a></li>
					<li><a href="#team">Team</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
		</div> -->
	</header>

	<!--        End Fixed Navigation
        ==================================== -->

	<!--        Main Content
        ==================================== -->

	<section id="works" class="works clearfix">
		<div class="project-wrapper">
				
				<!-- <div id="container" class="container" style="">
					<iframe src="https://www4.cbox.ws/box/?boxid=4335407&boxtag=quNgnE" width="100%" height="450" allowtransparency="yes" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>	
				</div> -->
				<!-- <div class="card">
							<div class="card-block">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="">
							<div class="card-block">
								<h4 class="card-title">1.Login</h4>
								<p class="card-text">login</p>
								<a href="#" class="btn btn-primary">Login</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="">
							<div class="card-block">
								<h4 class="card-title">2.Create Store</h4>
								<p class="card-text">create store</p>
								<a href="#" class="btn btn-primary">Create Store</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="">
							<div class="card-block">
								<h4 class="card-title">3.Share with friends</h4>
								<p class="card-text">share the store with your friends</p>
								<a href="#" class="btn btn-primary">Share with friends</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="">
							<div class="card-block">
								<h4 class="card-title">4.Get money</h4>
								<p class="card-text">get cashback whenever someone click and purchase from your store links.</p>
								<a href="#" class="btn btn-primary">Get money</a>
							</div>
						</div>
					</div>
					</div>
					</div> -->
			<!-- <div class="container"> -->
				<%@ include file="/common/messages.jsp"%>
				<div class="" id="main-content">
					<decorator:body />
				</div>
			<!-- </div> -->
		</div>
	</section>
	<!--        Main Content
        ==================================== -->

	<!--         Footer
        ==================================== -->
	<footer id="footer" class="footer">
		<div class="container">
			<%-- <div class="col-md-2 col-sm-6 col-xs-12">
				<div class="footer-single">
					<!-- <img src="images/core/footer-logo.png" alt=""> -->
					<h2>
						<a title="${applicationName} home" href="${applicationUrl}">${applicationName}</a>
					</h2>
				</div>
			</div> --%>
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="text-center">
					<a title="about menu" href="${applicationUrl}/site/about">About</a>
					| <a title="contact menu" href="${applicationUrl}/site/contact">Contact</a>
					| <a title="terms and conditions menu"
						href="${applicationUrl}/site/termsAndConditions">Terms and
						Conditions</a> | <a title="privacy policy menu"
						href="${applicationUrl}/site/privacyPolicy">Privacy Policy</a> | <a
						title="faq menu" href="${applicationUrl}/site/faq">FAQ</a>
				</div>
				<p class="text-center">
					Copyright <i class="fa fa-copyright"></i> 2017 <a
						title="copy rights" href="${applicationUrl}/">${applicationName}</a>.
					All rights reserved. Designed & developed by <a
						title="developed by" href="${applicationUrl}/">oorni.com</a>
				</p>
			</div>
		</div>
	</footer>
	<!--         End Footer
        ==================================== -->

	<!-- <a href="javascript:void(0);" id="back-top"><i
		class="fa fa-angle-up fa-3x"></i></a> -->
	<!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content row" style="padding-bottom:20px;">
					<!-- <div class="row">
						<div class="modal-header" style="padding:0; padding-right:10px;border:0;">
							
							<h4 class="modal-title" id="myModalLabel">Login</h4>
						</div> -->
						<div class="modal-body" style="padding:0;padding-top:5px;">
							<div class="col-xs-6">
								<h3 class="modal-title text-center" id="myModalLabel">Login</h3>
								<hr style="margin-right:10%;">
								<div class="" style="padding-right:30px; border:0; border-right:1px solid #ccc;">
									<form id="loginForm" novalidate="novalidate" method="post"
										action="<c:url value='/j_security_check'/>">
										<div class="form-group">
											<input type="text" class="form-control input-border-bottom" id="j_username"
												name="j_username" value="" required
												title="Please enter you username"
												placeholder="example@gmail.com"> <span
												class="help-block"></span>
										</div>
										<div class="form-group">
											<input type="password" class="form-control input-border-bottom" id="j_password"
												name="j_password" value="" required
												title="Please enter your password" placeholder="password"> <span
												class="help-block"></span>
										</div>
										<div id="loginErrorMsg" class="alert alert-error" style="display:none">Wrong
											username or password</div>
										<button type="submit" class="btn btn-success btn-block">Login</button>
									</form>
								</div>
							</div>
							<div class="col-xs-6">
								<button type="button" class="close pull-right" data-dismiss="modal">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title text-center" id="myModalLabel">Signup</h3>
								<hr>
								<div class="" >
									<form id="signupForm" method="post" action="signup"
										novalidate="novalidate">
										<div class="form-group">
											<input
												type="text" class="form-control input-border-bottom" id="email" name="email"
												value="" required title="Please enter you email"
												placeholder="example@gmail.com"> <span
												class="help-block"></span>
										</div>

										<div class="form-group">
											<input type="password" class="form-control input-border-bottom" id="password"
												name="password" value="" required
												title="Please enter your password" placeholder="password">
											<span class="help-block"></span>
										</div>

										<div id="loginErrorMsg" class="alert alert-error" style="display:none">Wrong
											username og password</div>
										<button type="submit" class="btn btn-danger btn-block">Signup</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
	// Instantiate the Bloodhound suggestion engine
	var labeldata = new Bloodhound({
		datumTokenizer : function(datum) {
			return Bloodhound.tokenizers.whitespace(datum.value);
		},
		queryTokenizer : Bloodhound.tokenizers.whitespace,
		remote : {
			url : '/get/searchSuggest?query=%QUERY',
			wildcard : '%QUERY',
			filter : function(data) {
				// Map the remote source JSON array to a JavaScript object array
				var labels = JSON.parse(JSON.stringify(data));
				return $.map(labels, function(label) {
					return {
						value : label
					};
				});
			}
		}
	});

	// Initialize the Bloodhound suggestion engine
	labeldata.initialize();

	// Instantiate the Typeahead UI
	$('#search').typeahead(null, {
		displayKey : 'value',
		source : labeldata.ttAdapter()
	}).on('typeahead:selected', function(obj, datum) {
		//console.log(datum);
		//alert(window.location.origin);
		var link = window.location.origin + "/" + datum.value + "/";
		window.location.href = link;
		/* $.ajax({
			type : "GET",
			url : "/get/offers",
			data : "label=" + datum.value + "&storeNo=" + 1,
			success : function(response) {
				if(response.length > 0){
					$("#storeNo").val(1);
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
<!-- Essential jQuery Plugins
================================================== -->
<%-- <script type="text/javascript"
	src="${storeContext.request.contextPath}/assets/v/${applicationScope.assetsVersion}/oorni-post.js"></script> --%>
</html>
