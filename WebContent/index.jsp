<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<!--

TABLE OF CONTENTS.

Use search to find needed section.

=====================================================

|  1. $BODY                 |  Body                 |
|  2. $MAIN_NAVIGATION      |  Main navigation      |
|  3. $NAVBAR_ICON_BUTTONS  |  Navbar Icon Buttons  |
|  4. $MAIN_MENU            |  Main menu            |
|  5. $TIMELINE             |  Timeline             |

=====================================================

-->

<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if IE 9]>         <html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-timeline.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:23 GMT -->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Timeline - Pages - PixelAdmin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	
	<!-- Pixel Admin's stylesheets -->
	<link href="assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/widgets.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">

	<!--[if lt IE 9]>
		<script src="assets/javascripts/ie.min.js"></script>
	<![endif]-->

	<style>
		.page-header .pull-right {
			padding-top: 5px;
		}
		.page-header .pull-right > * {
			display: inline-block;
			vertical-align:middle;
		}
	.aligns	{
				width:40%;
				float:left;
				display:block;
				
			}
			
	.integers {
		font-size:13px;
		font-family:helvetica;
	}
	.liker {
		margin-bottom: 5px;
		line-height: 40px;
	}
	.liker img {
		width: 2.6em; 
		height: 2.6em;
	}
	.liker #btnSpan {
		float: right;
	}
			
			
	</style>
</head>


<!-- 1. $BODY ======================================================================================
	
	Body

	Classes:
	* 'theme-{THEME NAME}'
	* 'right-to-left'      - Sets text direction to right-to-left
	* 'main-menu-right'    - Places the main menu on the right side
	* 'no-main-menu'       - Hides the main menu
	* 'main-navbar-fixed'  - Fixes the main navigation
	* 'main-menu-fixed'    - Fixes the main menu
	* 'main-menu-animated' - Animate main menu
	
-->



<body onload="loadLikesCount()" class="theme-adminflare main-menu-animated main-navbar-fixed">

<s:if test="#session.login != true">
		<jsp:forward page="login.jsp"></jsp:forward>
</s:if>

<!-- hidden field to store current user's id -->
<s:hidden id="userid" value="%{#session.user_id}"></s:hidden>

<script>
	var init = [];
	init.push(function () {
		$('#timeline-centered').switcher();
		$('#timeline-centered').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
			var turn_on = $(this).is(':checked');
			if (turn_on) {
				$('.timeline').addClass('centered');
			} else {
				$('.timeline').removeClass('centered');
			}
		});
	});
</script>
<!-- Demo script  <script src="assets/demo/demo.js"></script> <!-- / Demo script -->

<div id="main-wrapper">


<!-- Main Navigation -->

<jsp:include page="main_navigation.jsp"></jsp:include>

<!-- /Main Navigation -->





<!-- 4. $MAIN_MENU =================================================================================

		Main menu
		
		Notes:
		* to make the menu item active, add a class 'active' to the <li>
		  example: <li class="active">...</li>
		* multilevel submenu example:
			<li class="mm-dropdown">
			  <a href="#"><span class="mm-text">Submenu item text 1</span></a>
			  <ul>
				<li>...</li>
				<li class="mm-dropdown">
				  <a href="#"><span class="mm-text">Submenu item text 2</span></a>
				  <ul>
					<li>...</li>
					...
				  </ul>
				</li>
				...
			  </ul>
			</li>
-->
	<!-- Side Navigation -->
	
	<jsp:include page="navigation.jsp"></jsp:include>	
	
	<!-- /Side Navigation -->
	
	<div id="content-wrapper">
		<div class="page-header">
			<!-- <h1><span class="text-light-gray">Pages / </span>Timeline</h1> -->
			<!-- <div class="pull-right">
				<div class="text-semibold text-light-gray text-sm">وچ تي&nbsp;&nbsp;&nbsp;</div>
				
				<input type="checkbox" data-class="switcher-success" id="timeline-centered">
				
				<br/>
				<br/>
				
				
			</div> -->
			
			
			
		</div>
		<!-- / .page-header -->
		

<!-- 5. $TIMELINE ==================================================================================

		Timeline

		To use centered layout just add .centered class to the .timeline 
		NOTE: IE8 Respond.js has a bug https://github.com/scottjehl/Respond/issues/166
-->
		<div   id="timeline" class="timeline">
			<!-- Timeline header -->
			
			<s:hidden id="lastHistoryId" value="%{lastHistoryId}"></s:hidden>
			
			
			<div class="tl-header now bg-primary">Recent</div>
			
			<s:set name="next" value="true" scope="page" />
			
			<s:iterator value="timelineList">	
			
				<s:if test="post_type == 'test'">
			
					<s:if test="is_public == true">
							
						<s:if test="#next == true">
							<div class="tl-entry left">
							<s:set name="next" value="false"></s:set>
						</s:if>
						<s:else>
							<div class="tl-entry">
							<s:set name="next" value="true"></s:set>
						</s:else>
						
						<div class="tl-time ">
							<span><abbr style="border-bottom: 0" class="timeago" title="<s:property value="date_time"/>"></abbr></span>
						</div>
						
						
						
						<div class="tl-icon bg-success"><img src="<s:property value='pic_url'/>" alt=""></div>
						
						<div class="panel tl-body">
							<strong><a href="<s:property value='username'/>"><s:if test="user_id == #session.user_id">You</s:if><s:else><s:property value="user_name"/></s:else></strong> attempted </a> <strong><a href="category?c=<s:property value="category_id"/>"><s:property value="category_name"/></a> > <a href="subcategory?sc=<s:property value="sub_category_id"/>"><s:property value="sub_category_name"/></a> > <a href="#"><s:property value="test_name"/></a></strong> in which <s:if test="user_id == #session.user_id">you</s:if><s:else>he</s:else>:
							<div class="well well-sm" style="margin: 10px 0 0 0;">
									 
								<ul class="nav nav-pills">
								
									<li class="active"><a href="javascript: return null;">Attempted<span class="label label-warning integers"><s:property value="total_attempted"/></span></a></li>
									<li class="active" ><a href="javascript: return null;" style="background-color:#36a766;">Corrected<span class="label label-warning integers"><s:property value="correct_answers"/></span></a></li>
									<li class="active" ><a href="javascript: return null;" style="background-color:#d54848;">Incorrected<span class="label label-warning integers"><s:property value="wrong_answers"/></span></a></li>
									<li class="active" ><a href="javascript: return null;" style="background-color:#3156be;">Scored<span class="label label-warning integers"><s:property value="score"/>%</span></a></li>
										
								</ul>
								 
							</div>
							<span class="label label-tag valign-middle" style="margin-top: 10px;"><span id="likeSpan<s:property value='post_id'/>"><s:if test="is_liked == false"><a href="javascript: return null;" onclick="like(<s:property value='post_id'/>)" id="like<s:property value='post_id'/>" style="color: white;" class="btn-label icon fa fa-thumbs-o-up text-normal"> <span style="font-family: arial;">Like</span></a></s:if><s:else><a href="javascript: return null;" onclick="unlike(<s:property value='post_id'/>)" id="unlike<s:property value='post_id'/>" style="color: white;" class="btn-label icon fa fa-thumbs-up"> <span style="font-family: arial;">Unlike</span></a></s:else></span>&nbsp;&nbsp;<a id="likes<s:property value='post_id'/>" value="<s:property value='post_id'/>" onclick="likeDetails(<s:property value='post_id'/>)" href="javascript: return null;" class="integers numbers" style="color: white"><s:property value="likesCount"/></a></span>
						</div> <!-- / .tl-body -->
					</div> <!-- / .tl-entry -->
					
					</s:if><!-- is public if end -->
				
				
				</s:if>
				<s:elseif test="post_type == 'competition'">
					
					<div class="tl-entry left">
				<div class="tl-time">
					<span><abbr style="border-bottom: 0" class="timeago" title="<s:property value="date_time"/>"></abbr></span>
				</div>
				<div class="tl-icon bg-success"><img src="<s:property value='pic_url'/>" alt=""></div>
				<div class="panel tl-body">
					<strong><a href="<s:property value='username'/>"><s:if test="user_id == #session.user_id">You</s:if><s:else><s:property value="user_name"/></s:else></a></strong>
					arranged a <strong>competition</strong> of 
					<strong><a href="category?c=<s:property value="category_id"/>"><s:property value="category_name"/></a> > <a href="subcategory?sc=<s:property value="sub_category_id"/>"><s:property value="sub_category_name"/></a> > <a href="#"><s:property value="test_name"/></a> </strong>
					with <strong><s:property value="num_of_questions"/></strong> questions
					between:
					<div class="tl-wide" style="padding: 10px;margin-top:15px;margin-bottom: -10px;background: #f1f1f1">
						
						
						<div class="row">
							
							<s:iterator value="competition_users">
								<div class="col-md-6">
									<div class="stat-panel" style="height: 15em">
										<!-- Danger background, vertically centered text -->
										<div class="stat-cell bg-panel valign-middle">
											<!-- Stat panel bg icon -->
											<s:if test="has_attempted == true">
												<!-- <i style="z-index: 0" class="fa fa-bar-chart-o bg-icon"></i> -->
											</s:if>
											<s:else>
												<i class="fa fa-question-circle bg-icon"></i>
											</s:else>
											<!-- Extra large text -->
											<span class="text-lg text-semibold">
												<img style="width:2.5em; height:2.5em; margin-right: 0.3em; margin-bottom: 0.3em;" class="rounded" src="<s:property value='picurl'/>" alt="">
												<s:property value='fullname'/>
											</span><br>
											<!-- Big text -->
											<span class="text-normal">
												<s:if test="has_attempted == true">
												Attempted <span><abbr style="border-bottom: 0" class="timeago" title="<s:property value="date_time"/>"></abbr></span> <br/>												
											</s:if>
											
											</span><br>
											<!-- Small text -->
											<span class="text-sm">
												<span>
													<s:if test="has_attempted == true">
														<div class="row text-default text-semibold">
															<div class="col-md-4">Correct: <span class="label label-success integers"><s:property value="correct_answers"/></span></div>
															<div class="col-md-4">Wrong: <span class="label label-danger integers"><s:property value="wrong_answers"/></span></div>
															<div class="col-md-4">Score: <span class="label label-success integers"><s:property value="score"/>%</span></div>
														</div>
														<div class="progress progress-striped" style="margin-top: 1.3em; margin-bottom: 0"><div class="progress-bar" style="width: <s:property value='score'/>%;"></div></div>
													</s:if>
													<s:else>
														<s:if test="user_id == #session.user_id">
															<button class="btn btn-sm btn-primary btn-rounded">Attempt Now</button>
														</s:if>
														<s:else>
															<span class="badge badge-info">Not yet attempted</span>
														</s:else>
														
													</s:else>
												</span>
											</span>
										</div> <!-- /.stat-cell -->
										
									</div> <!-- /.stat-panel -->
								</div>
							</s:iterator>
							
							
						</div>
						
						
					</div>
					
					<i class="text-muted text-sm no-padding">
					
						<span class="label label-tag valign-middle form-group-margin" style="margin:0"><span id="likeSpan<s:property value='post_id'/>"><s:if test="is_liked == false"><a href="javascript: return null;" onclick="like(<s:property value='post_id'/>)" id="like<s:property value='post_id'/>" style="color: white;" class="btn-label icon fa fa-thumbs-o-up text-normal"> <span style="font-family: arial;">Like</span></a></s:if><s:else><a href="javascript: return null;" onclick="unlike(<s:property value='post_id'/>)" id="unlike<s:property value='post_id'/>" style="color: white;" class="btn-label icon fa fa-thumbs-up"> <span style="font-family: arial;">Unlike</span></a></s:else></span>&nbsp;&nbsp;<a id="likes<s:property value='post_id'/>" value="<s:property value='post_id'/>" onclick="likeDetails(<s:property value='post_id'/>)" href="javascript: return null;" class="integers numbers" style="color: white"><s:property value="likesCount"/></a></span>
					</i>
					
					<div id="jq-expanding-input" class="form-group-margin expInput">
						<input type="text" class="form-control">
						<div class="expanding-input-hidden" style="margin-top: 10px;">
							<label class="checkbox-inline"><input type="checkbox"> Checkbox</label>
							<button class="btn btn-primary pull-right">Post comment</button>
						</div>
					</div>
					
					<script>
						init.push(function () {
							$(".expInput:eq(1)").expandingInput({
								target: 'input[type="text"]',
								hidden_content: '> div',
								placeholder: 'Input example'
							});
						});
					</script>
					
					
				</div> <!-- / .tl-body -->
			</div>
					
				</s:elseif>
				
			
			</s:iterator>
			
			<button style="visibility: hidden" id="modalButton" data-toggle="modal" data-target="#modal-sizes-1"></button>
			
			<!-- Small modal -->
			<div id="modal-sizes-1" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
						<button type="button" class="close" style="font-size: 30px;" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title">People who like this</h4>
						</div>
						<div id="modal-body" class="modal-body padding-sm">
								
						</div>
					</div> <!-- / .modal-content -->
				</div> <!-- / .modal-dialog -->
			</div> <!-- / .modal -->
			<!-- / Small modal -->
			
			<script>
				init.push(function () {
					$('#modal-body').slimScroll({ height: 350 });
				});
			</script>
			
			<!-- Modals Script -->
			<script>
				
			
				init.push(function() {
					if($(document).width() > 700) {
						$("#timeline").css("margin-left","4.5em");
					} else {
						$("#timeline").css("margin-left","0em");
					}
					$('#uidemo-modals-effects-btn').on('click', function() {
						var $modal  = $('#uidemo-modals-effects-template').clone(true);
						$modal.find('> div').addClass('modal-dialog modal-sm animated ' + $('#uidemo-modals-effects-select').find(":selected").attr('value'));
						$modal.modal('show');
					})
				});
				
				
			</script>
			<!-- /Modals Script -->
			
			
			<script>
				function follow(follower) {
					var user = $("#userid").val();
					
					try {
						
						$.post("follow", {followerId:user, followedId: follower}, function(data, status) {
							
							$("#follow"+follower).remove();
							$("#btnSpan").html('<button id="unfollow'+follower+'" onclick="unfollow('+follower+')" class="btn btn-xs btn-success"><span class="fa fa-check-circle"></span>&nbsp;&nbsp;Unfollow</button>');
							
						});
					
					} catch(e) {
						alert(e.message)
					}
					
		
				}
				
				function unfollow(follower) {
					
					var user = $("#userid").val();
					
					try {
						$.post("unfollow", {followerId:user, followedId: follower}, function(data, status) {
							
							$("#unfollow"+follower).remove();
							$("#btnSpan").html('<button id="follow'+follower+'" onclick="follow('+follower+')" class="btn btn-xs btn-default"><span class="fa fa-check-circle-o"></span>&nbsp;&nbsp;Follow</button>');
							
						});
					} catch(e) {
						alert(e.message)
					}
					
				}
			</script>
			
			
			<script>
			
				
				function likeDetails(historyId) {
					
					var count = $("#likes"+historyId).html();
					if(count > 0) {
						try {
							$("#modal-body").load( "loadLikeDetails?historyId="+historyId, function( response, status, xhr ){
								//alert(xhr.responseText);
								$("#modalButton").trigger("click");
							});
							
						}catch(e) {
								alert(e.message);
						}
					}
					
				}
									
				function like(historyId) {
					
					
					
					try {
						
						$.post("like", {historyId: historyId}, function(data, status) {
							var count = $("#likes"+historyId).html();
							$("#likes"+historyId).html(++count);
							$("#like"+historyId).remove();
							$("#likeSpan"+historyId).html('<a href="javascript: return null;" onclick="unlike('+historyId+')" id="like'+historyId+'" style="color: white;" class="btn-label icon fa fa-thumbs-up"> <span style="font-family: arial;">Unlike</span></a>');
							
						});
					
					} catch(e) {
						alert(e.message)
					}
					
		
				}
				
				
				
				function unlike(historyId) {
					
					try {
						$.post("unlike", {historyId: historyId}, function(data, status) {
							var count = $("#likes"+historyId).html();
							$("#likes"+historyId).html(--count);
							$("#unlike"+historyId).remove();
							$("#likeSpan"+historyId).html('<a href="javascript: return null;" onclick="like('+historyId+')" id="like'+historyId+'" style="color: white;" class="btn-label icon fa fa-thumbs-o-up"> <span style="font-family: arial;">Like</span></a>');
							
						});
					} catch(e) {
						alert(e.message)
					}
					
				}
			</script>
			
			<!--
			Follow notification entry
			<div class="tl-entry">
				<div class="tl-time">
					4h ago
				</div>
				<div class="tl-icon bg-dark-gray"><i class="fa fa-check"></i></div>
				<div class="panel tl-body">
					<img src="assets/demo/avatars/5.jpg" alt="" class="rounded" style=" width: 20px;height: 20px;margin-top: -2px;">&nbsp;&nbsp;<a href="#">اقبال بليديءَ</a>  <a href="#">زاهد لاشاريءَ</a> کي فالو ڪيو
				</div> <!-- / .tl-body -->
			</div> <!-- / .tl-entry -->
			
			
			
			<!-- Timeline header -->
			
			

		</div> <!-- / .timeline -->
	</div> <!-- / #content-wrapper -->
	<div id="main-menu-bg"></div>
</div> <!-- / #main-wrapper -->

<!-- Get jQuery from Google CDN -->
<!--[if !IE]> -->
	
	<script type="text/javascript"> window.jQuery || document.write('<script src="ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js">'+"<"+"/script>"); </script>
<!-- <![endif]-->
<!--[if lte IE 9]>
	<script type="text/javascript"> window.jQuery || document.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">'+"<"+"/script>"); </script>
<![endif]-->


<!-- Pixel Admin's javascripts -->

<script src="assets/javascripts/jquery.timeago.js"></script>
<script src="assets/javascripts/bootstrap.min.js"></script>
<script src="assets/javascripts/pixel-admin.min.js"></script>

<script>
	$(document).ready(function() {
	  $("abbr.timeago").timeago();
	});
</script>

<script type="text/javascript">
	
	init.push(function () {
		// Javascript code here
	})
	try {
		
		window.PixelAdmin.start(init);
		
	}catch(e) {
		
	}
</script>

			
			<script>
			
				var lastHistoryId = $("#lastHistoryId").val();
				
				$(window).scroll(function () {
					if ($(window).scrollTop() >= $(document).height() - $(window).height() - 10) {
					    $.post("loadMoreTimeline", {from:lastHistoryId}, function(data, status) {
					    	try {
					    		var obj = JSON.parse(data);
						    	if(obj.from != 0) {
									$("#timeline").append(obj.posts);
									$("abbr.timeago").timeago();
									lastHistoryId = obj.from;
									
						    	}
					    	}catch(e) {
					    		//alert(e.message);
					    	}
							
					    });
					}
				});
				
			</script>
			
			<script>

				function loadLikesCount() {
					try {
					
						var length = $(".numbers").length;
						for(var i=0; i<length; i++) {
							var historyId = $(".numbers:eq("+i+")").attr("value");
							var val = "";
							
							try {
								$(".numbers:eq("+i+")").load( "loadLikesCount?historyId="+historyId, function( response, status, xhr ){
									//alert(xhr.responseText);
								});
							}catch(e) {
								alert(e.message)
							}
							
						}
						
						setTimeout(loadLikesCount, 3000);
					
					} catch(e) {
						alert(e.message);
					}
					
					
					
				}
				
				loadLikesCount();

			</script>

</body>
















<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-timeline.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:23 GMT -->
</html>