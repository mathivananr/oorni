/* ========================================================================= */
/*	Preloader
/* ========================================================================= */

jQuery(window).load(function() {
	$("#preloader").fadeOut("slow");
});

$(window).scroll(function(){
	if(!$("#navigation").hasClass("navbar-fixed-top")){
		$("#navigation").addClass("navbar-fixed-top");
	}
	
	if($(window).scrollTop() == 0) {
		$("#navigation").removeClass("navbar-fixed-top");
	}
	if($('#offers-container').html() != undefined && $(window).scrollTop() >= $('#offers-container').offset().top + $('#offers-container').outerHeight() - window.innerHeight && $("#scrollReached").val() == "false") {
        $("#scrollReached").val("true");
        loadMoreOffers();
    }
});

$(window).on('hashchange', function() {
	var target = document.location.hash.replace("#", "");
	if(target== 'login') {
		$('#myModal').modal('show');
	}
});

(function () {
    $(function () {
    	
    	/** 
    	 * log user actions by read user link clicks 
    	 */
    	$('a').click(
    			function(e) {
    				var link = $(this).attr('href');
    				var title = $(this).attr('title');
    				if (title != undefined && title != null && title != ''
    						&& link != undefined && link != null && link != '') {
    					$.ajax({
    						type : "GET",
    						url : "/log",
    						data : "title=" + title + "&link=" + link,
    						success : function(response) {
    							//console.log("success");
    						},
    						error : function(e) {
    							//console.log('Error: ' + e);
    						}
    					});
    			}
    	});
    	
    	var target = document.location.hash.replace("#", "");
    	if(target== 'login') {
    		$('#myModal').modal('show');
    	}
        var SideBAR;
        SideBAR = (function () {
            function SideBAR() {}

            SideBAR.prototype.expandMyMenu = function () {
            	$("#side-menu").removeClass("hide");
            	$("nav.sidebar").addClass("side-menu");
                return $("nav.sidebar").removeClass("sidebar-menu-collapsed").addClass("sidebar-menu-expanded");
            };

            SideBAR.prototype.collapseMyMenu = function () {
            	$("#side-menu").addClass("hide");
            	$("nav.sidebar").removeClass("side-menu");
                return $("nav.sidebar").removeClass("sidebar-menu-expanded").addClass("sidebar-menu-collapsed");
            };

            SideBAR.prototype.showMenuTexts = function () {
                return $("nav.sidebar ul a span.expanded-element").show();
            };

            SideBAR.prototype.hideMenuTexts = function () {
                return $("nav.sidebar ul a span.expanded-element").hide();
            };

            SideBAR.prototype.showActiveSubMenu = function () {
                $("li.active ul.level2").show();
                return $("li.active a.expandable").css({
                    width: "100%"
                });
            };

            SideBAR.prototype.hideActiveSubMenu = function () {
                return $("li.active ul.level2").hide();
            };

            SideBAR.prototype.adjustPaddingOnExpand = function () {
                $("ul.level1 li a.expandable").css({
                    padding: "1px 4px 4px 0px"
                });
                return $("ul.level1 li.active a.expandable").css({
                    padding: "1px 4px 4px 4px"
                });
            };

            SideBAR.prototype.resetOriginalPaddingOnCollapse = function () {
                $("ul.nbs-level1 li a.expandable").css({
                    padding: "4px 4px 4px 0px"
                });
                return $("ul.level1 li.active a.expandable").css({
                    padding: "4px"
                });
            };

            SideBAR.prototype.ignite = function () {
                return (function (instance) {
                    return $("#justify-icon").click(function (e) {
                        if ($(this).parent("nav.sidebar").hasClass("sidebar-menu-collapsed")) {
                            instance.adjustPaddingOnExpand();
                            instance.expandMyMenu();
                            instance.showMenuTexts();
                            instance.showActiveSubMenu();
                            $(this).css({
                                color: "#FFF"
                            });
                        } else if ($(this).parent("nav.sidebar").hasClass("sidebar-menu-expanded")) {
                            instance.resetOriginalPaddingOnCollapse();
                            instance.collapseMyMenu();
                            instance.hideMenuTexts();
                            instance.hideActiveSubMenu();
                            $(this).css({
                                color: "#0EB493"
                            });
                        }
                        return false;
                    });
                })(this);
            };

            return SideBAR;

        })();
        return (new SideBAR).ignite();
    });

}).call(this);

$(window).bind("load", function() {
    $.each(document.images, function(){
               var this_image = this;
               var src = $(this_image).attr('src') || '' ;
               if(!src.length > 0){
                   //this_image.src = options.loading; // show loading
                   var lsrc = $(this_image).attr('data-src') || '' ;
                   if(lsrc.length > 0){
                       var img = new Image();
                       img.src = lsrc;
                       $(img).load(function() {
                           this_image.src = this.src;
                       });
                   }
               }
           });
  });

/*$(document)
		.ready(
				function() {

					 ========================================================================= 
					
					 * Menu item highlighting /*
					 * =========================================================================
					 

					jQuery('#nav').singlePageNav({
						offset : jQuery('#nav').outerHeight(),
						filter : ':not(.external)',
						speed : 1200,
						currentClass : 'current',
						easing : 'easeInOutExpo',
						updateHash : true,
						beforeStart : function() {
							console.log('begin scrolling');
						},
						onComplete : function() {
							console.log('done scrolling');
						}
					});

					
					 * $(window).scroll(function () { if ($(window).scrollTop() >
					 * 400) {
					 * $("#navigation").css("background-color","#0EB493"); }
					 * else { $("#navigation").css("background-color","rgba(16,
					 * 22, 54, 0.2)"); } });
					 

					 ========================================================================= 
					
					 * Fix Slider Height /*
					 * =========================================================================
					 

					var slideHeight = $(window).height();

					$(
							'#slider, .carousel.slide, .carousel-inner, .carousel-inner .item')
							.css('height', slideHeight);

					$(window)
							.resize(
									function() {
												'use strict',
												$(
														'#slider, .carousel.slide, .carousel-inner, .carousel-inner .item')
														.css('height',
																slideHeight);
									});

					 ========================================================================= 
					
					 * Portfolio Filtering /*
					 * =========================================================================
					 

					// portfolio filtering
					$(".project-wrapper").mixItUp();

					$(".fancybox").fancybox({
						padding : 0,

						openEffect : 'elastic',
						openSpeed : 650,

						closeEffect : 'elastic',
						closeSpeed : 550,

						closeClick : true,
					});

					 ========================================================================= 
					
					 * Parallax /*
					 * =========================================================================
					 

					$('#facts').parallax("50%", 0.3);

					 ========================================================================= 
					
					 * Timer count /*
					 * =========================================================================
					 

					"use strict";
					$(".number-counters").appear(function() {
						$(".number-counters [data-to]").each(function() {
							var e = $(this).attr("data-to");
							$(this).delay(6e3).countTo({
								from : 50,
								to : e,
								speed : 3e3,
								refreshInterval : 50
							})
						})
					});

					 ========================================================================= 
					
					 * Back to Top /*
					 * =========================================================================
					 

					$(window).scroll(function() {
						if ($(window).scrollTop() > 400) {
							$("#back-top").fadeIn(200)
						} else {
							$("#back-top").fadeOut(200)
						}
					});
					$("#back-top").click(function() {
						$("html, body").stop().animate({
							scrollTop : 0
						}, 1500, "easeInOutExpo")
					});

					$('#clock').countdown('2015/5/10', function(event) {
						// $(this).html(event.strftime('%D days left
						// %H:%M:%S'));
					});

					 ========================================================================= 
					
					 * Active Menu item highlighting /*
					 * =========================================================================
					 
					$('#' + $('#active-menu').val()).addClass('active');
					*//***********************************************************
					 * Activate Pop over
					 *//*
					$('[data-toggle="popover"]').popover({
						trigger : 'hover'
					})
				});*/


function loadMoreOffers() {
	var label = $("#label").val();
	var pageNo = $("#pageNo").val();
	if(pageNo == undefined || pageNo == '' || pageNo == NaN) {
		pageNo = 1;
	}
	console.log(pageNo);
	$.ajax({
		type : "GET",
		url : "/get/offers",
		data : "label=" + label + "&pageNo=" + (parseInt(pageNo) + 1),
		success : function(response) {
			if(response.length > 0){
				pageNo = parseInt(pageNo) + 1;
				$("#pageNo").val(pageNo);
				$("#offersList").append(response);
				$('#loadMore').attr('href','#!/'+pageNo);
				$("#scrollReached").val("false");
			} else {
				$("#loadOffers").html('<p align="center"><button id="noOffer" class="btn btn-default">No more offers available.</button></p>');
			}
			//console.log("success");
		},
		error : function(e) {
			//console.log('Error: ' + e);
			console.log(e);
		}
	});
}

$('#loadMore').click(
		function(e) {
			loadMoreOffers();
});

function ajaxLogin(){
    
    var user_pass = $("#j_password").val();
    var user_name = $("#j_username").val(); 
 
//Ajax login - we send credentials to j_spring_security_check (as in form based login
    $.ajax({
          url: "/ajaxLogin",    
          data: { j_username: user_name , j_password: user_pass }, 
          type: "POST",
          beforeSend: function (xhr) {
             xhr.setRequestHeader("X-Ajax-call", "true");
          },
          success: function(result) {   
        	  $('#myModal').modal('hide');
          //if login is success, hide the login modal and
          //re-execute the function which called the protected resource
          //(#7 in the diagram flow)
         /* if (result == "ok") {
  
            $("#ajax_login_error_"+ suffix).html("");            
            $('#ajaxLogin').hide();
             
            return true;
          }else {           
             
            $("#ajax_login_error_"+ suffix).html('<span  class="alert display_b clear_b centeralign">Bad user/password</span>') ;
            return false;           
        }*/
    },
    error: function(XMLHttpRequest, textStatus, errorThrown){
        $("#ajax_login_error_"+ suffix).html("Bad user/password") ;
        return false; 
    }
});
}


function loadCarousel(label) {
	var colors = [['#00c3ff', '#ffff1c'],['#348F50', '#56B4D3'],['#FF512F', '#F09819'],['#fff', '#111']];
	$.ajax({
		type : "GET",
		url : "/carousel/"+label,
		success : function(response) {
			if(response.length > 0){
				var w = window.innerWidth-50;
				var h = window.innerHeight;
				var height = h/2;
				if(w < 400) {
					height = 180;
				} else if(height > 350) {
					height = 350;
				}
				var width = w;
				var titleXPosistion = w/4;
				var titleYPosistion = 50;
				var carouselContent = '<div id="carouselWithCaptions" class="carousel slide" data-ride="carousel">';
				var slideConfig = '';
				var slideContent = '';
				var isActive = 'active';
				var colorCount = 0;
				for(var i = 0; i<response.length; i++) {
					if(colorCount > colors.length-1 ) {
						colorCount = 0;
					}
					if(response[i].contentType && response[i].contentType == 'text') {
						if(slideConfig == '') {
							slideConfig = slideConfig + '<ol class="carousel-indicators">'
						}
						slideConfig = slideConfig + '<li data-target="#carouselWithCaptions" data-slide-to="'+i+'" class="'+isActive+'"></li>';
						if(slideContent == '') {
							slideContent = slideContent + '<div class="carousel-inner" role="listbox">';
						}
						var svg = '<svg width="'+width+'" height="'+height+'" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 '+width+' '+height+'" preserveAspectRatio="none">'
									+	'<defs><linearGradient id="background'+i+'">'
									+	'<stop offset="0%" stop-color="'+colors[colorCount][0]+'" />'
									+	'<stop offset="100%" stop-color="'+colors[colorCount][1]+'" />'
									+	'</linearGradient>'
									+	'<style type="text/css">#holder_159ef10fa87 text { fill:url(#background'+i+');font-weight:normal;font-family:Helvetica, monospace;font-size:40pt } </style>'
									+	'</defs><g id="holder_159ef10fa87"><rect width="'+width+'" height="'+height+'" fill="url(#background'+i+')"></rect><g><text x="50%" y="30%" text-anchor="middle">'+response[i].carouselTitle+'</text></g></g></svg>';
						var src = '\'data:image/svg+xml;base64,'+btoa(svg)+'\'';
						slideContent = slideContent + '<div class="carousel-item '+isActive+'">'
							+'				<a href="/offers" ><img class="d-block img-fluid" alt="'+response[i].carouselTitle+'"'
							+'					src='+src
							+'					data-holder-rendered="true">'
							+'				<div class="carousel-caption d-none d-md-block">'
							+'					<h3>'+response[i].description+'</h3>'
							+'					<p>'+response[i].content+'</p>'
							+'				</div>'
							+'			</div></a>';
							isActive = '';
							colorCount++;
					}
				}
				if(slideConfig != '') {
					slideConfig = slideConfig + '</ol>';
				}
				if(slideContent != '') {
					slideContent = slideContent + '</div>';
				}
				carouselContent = carouselContent + slideConfig + slideContent + '</div>';
				$('#carousel').html(carouselContent);
				$('#carouselWithCaptions').carousel({
					  interval: 3000,
					  cycle: true
					}); 
			} else {
				console.log(response);
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}