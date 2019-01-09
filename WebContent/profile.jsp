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
|  5. $PROFILE              |  Profile              |

=====================================================

-->


<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if IE 9]>         <html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-profile.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:22 GMT -->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><s:property value="summary.fullname"/> - TestBook</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	<!-- Open Sans font from Google CDN -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&amp;subset=latin" rel="stylesheet" type="text/css">

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
		.integers {
			font-size:13px;
			font-family:helvetica;
		}
		
		.social {
			font-size:12px;
			font-family:arial;
			color: black;
			font-weight: bold;
		}
		.username {
			font-size:12px;
			font-family:arial;
			color: black;
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
<body class="theme-adminflare main-menu-animated page-profile">

<!-- hidden field to store current user's id -->
<s:hidden id="userid" value="%{#session.user_id}"></s:hidden>

<s:if test="#session.login != true">
		<jsp:forward page="login.jsp"></jsp:forward>
</s:if>

<script>var init = [];</script>

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
	
	<!-- Include side navigation -->
	<jsp:include page="navigation.jsp"></jsp:include>
	
	<div id="content-wrapper">
<!-- 5. $PROFILE ===================================================================================

		Profile
-->
		<div class="profile-full-name">
			<span class="text-semibold"><s:property value="summary.fullname"/></span> 
		</div>
	 	<div class="profile-row">
			<div class="left-col">
				<div class="profile-block">
					<div class="panel profile-photo">
						<img src="<s:property value='summary.picurl'/>" alt="">
						<!--<span class="badge badge-info" style="height: 20px; border: 0px; background-color: #646464; position: absolute; top: 94%; left: 35%; font-size:17px">Rank <font face="arial" size="3"><s:property value="profileRank"/></font> </span>-->
					</div><br>
					<s:if test="summary.userid != #session.user_id">
						<span id="friendBtnSpan">
							<button onclick="acceptRequest(<s:property value='friend_request_id'/>)" class="btn btn-flat btn-sm btn-labeled btn-success"><span class="btn-label icon fa fa-user"></span>Request Sent..</button>
						</span>
						<span id="followbtnspan">
							<s:if test="isFollowed == true">
								<a href="javascript: return null;" id="unfollowbtn" onclick="unfollowMain(<s:property value='summary.userid'/>)" class="btn btn-sm btn-success"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;Unfollow</a>
							</s:if>
							<s:else>
								<a href="javascript: return null;" id="followbtn" onclick="followMain(<s:property value='summary.userid'/>)" class="btn btn-sm btn-default"><i class="fa fa-check-circle-o"></i> Follow</a>
							</s:else>	
						</span>
						
					</s:if>
				</div>
				
				
             	<script>
            			//script for main profile follow btn
						function followMain(follower) {
							var user = $("#userid").val();
							
							//alert(user);
							
							try {
								
								$.post("follow", {followerId:user, followedId: follower}, function(data, status) {
									
									$(".profile-row #followbtnspan #unfollowbtn").remove();
									$(".profile-row #followbtnspan").html('<a href="javascript: return null;" id="unfollowbtn" onclick="unfollowMain('+follower+')" class="btn btn-sm btn-success"><i class="fa fa-check-circle"></i><span>&nbsp;&nbsp;Unfollow</span></a>');
								});
							
							} catch(e) {
								alert(e.message)
							}
							
				
						}
						
						function unfollowMain(follower) {
							
							var user = $("#userid").val();
							
							try {
								$.post("unfollow", {followerId:user, followedId: follower}, function(data, status) {
									$(".profile-row #followbtnspan #unfollowbtn").remove();
									$(".profile-row #followbtnspan").html('<a href="javascript: return null;" id="followbtn" onclick="followMain('+follower+')" class="btn btn-sm btn-default"><i class="fa fa-check-circle-o"></i><span>&nbsp;&nbsp;Follow</span></a>');
								});
							} catch(e) {
								alert(e.message)
							}
							
						}
					</script>
             	
             	
             	<!-- 
             	<div class="profile-block" style="padding-top: 30px;">
					<div class="stat-cell no-border-t no-padding-hr">
							<div class="pie-chart" data-percent="<s:property value='userProgress' />" id="easy-pie-chart-3">	
								<div class="pie-chart-label" style="margin-top: 0px; color:black"><font face="arial"><s:property value='userProgress' />%</font></div>										
							<canvas height="30" width="100"></canvas></div>
						</div>		
						<div style="border-bottom: 1px dotted black; text-align:center;font-size:18px; color:black;font-weight:bold;"></div>
				</div>
				-->
             	
             	
				
				
				<script>
					init.push(function () {
						// Easy Pie Charts
						var easyPieChartDefaults = {
							animate: 2000,
							scaleColor: false,
							lineWidth: 10,
							lineCap: 'square',
							size: 150,
							trackColor: '#9d9d9d'
						}

						$('#easy-pie-chart-3').easyPieChart($.extend({}, easyPieChartDefaults, {
							barColor: PixelAdmin.settings.consts.COLORS[11]
						}));
					});
				</script>
				
				<div class="panel panel-transparent">
					<div class="panel-heading">
						<span class="panel-title text-semibold">About</span>
					</div>
					<div class="panel-body">
						<s:property value='summary.aboutMe'/>
					</div>
				</div>

				<div class="panel panel-transparent">
					<div class="panel-heading">
						<span class="panel-title text-semibold">Statistics</span>
					</div>
					<div class="list-group">
						<a href="#" class="list-group-item"><strong><s:property value='summary.testsCount'/></strong> Test(s) Attempted</a>
						<a href="#" class="list-group-item"><strong><s:property value='summary.followersCount'/></strong> Followers</a>
						<a href="#" class="list-group-item"><strong><s:property value='summary.followingCount'/></strong> Following</a>
					</div>
				</div>

				<div class="panel panel-transparent profile-skills">
					<div class="panel-heading">
						<span class="panel-title text-semibold">Attempted Categories</span>
					</div>
					<div class="panel-body">
						<s:iterator value="tests">
							<span class="label label-primary"><s:property value="category_name"/> </span>
						</s:iterator>
					</div>
				</div>

				<div class="panel panel-transparent">
					<div class="panel-heading">
						<span style="font-weight: bold;" class="panel-title text-semibold">Social</span>
					</div>
					<div class="list-group">
						<!-- <a href="#" class="list-group-item"><i class="profile-list-icon fa fa-twitter" style="color: #4ab6d5"></i> @dsteiner</a>-->
						<a href="#" class="list-group-item social"><i class="profile-list-icon fa fa-facebook-square" style="color: #1a7ab9"></i> <s:property value='summary.facebook'/></a>
						<a href="#" class="list-group-item social"><i class="profile-list-icon fa fa-envelope" style="color: #888"></i> <s:property value='summary.email'/></a>
					</div>
				</div>

			</div>
			<div class="right-col">

				<hr class="profile-content-hr no-grid-gutter-h">
				
				<div class="profile-content">

					<ul id="profile-tabs" class="nav nav-tabs">
						<li class="active">
							<a href="#profile-tabs-board" data-toggle="tab">Timeline</a>
						</li>
						<!-- <li>
							<a href="#profile-tabs-activity" data-toggle="tab">Friends</a>
						</li> -->
						<li>
							<a href="#profile-tabs-followers" data-toggle="tab">Followers (<strong><s:property value='summary.followersCount'/></strong>)</a>
						</li>
						<li>
							<a href="#profile-tabs-following" data-toggle="tab">Following (<strong><s:property value='summary.followingCount'/></strong>)</a>
						</li>
					</ul>

					<div  class="tab-content tab-content-bordered panel-padding">
						<div style="margin-left: 65px" class="widget-article-comments tab-pane panel no-padding no-border fade in active" id="profile-tabs-board">
							
				<div  id="timeline" class="timeline">
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
							<span><abbr style="border-bottom: 0; overflow: scroll;" class="timeago" title="<s:property value="date_time"/>"></abbr></span>
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
					<div class="tl-wide" style="padding: 10px;margin-top:15px;margin-bottom: 15px;background: #f1f1f1">
						
						
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
														<div>Correct: <span class="label label-success integers"><s:property value="correct_answers"/></span></div>
														<div>Wrong: <span class="label label-danger integers"><s:property value="wrong_answers"/></span></div>
														<div>Score: <span class="label label-success integers"><s:property value="score"/>%</span></div>
														<div class="progress progress-striped" style="margin-top: 1.3em; margin-bottom: 0"><div class="progress-bar" style="width: <s:property value='score'/>%;"></div></div>
													</s:if>
													<s:else>
														<s:if test="user_id == #session.user_id">
															<button class="btn btn-sm btn-primary btn-rounded">Attempt Now</button>
														</s:if>
														<s:else>
															<a href="javascript: return null;" class="badge badge-info">Not yet attempted</a>
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
					
					<i class="text-muted text-sm">
						<span class="label label-tag valign-middle" style="margin-top: 10px;"><span id="likeSpan<s:property value='post_id'/>"><s:if test="is_liked == false"><a href="javascript: return null;" onclick="like(<s:property value='post_id'/>)" id="like<s:property value='post_id'/>" style="color: white;" class="btn-label icon fa fa-thumbs-o-up text-normal"> <span style="font-family: arial;">Like</span></a></s:if><s:else><a href="javascript: return null;" onclick="unlike(<s:property value='post_id'/>)" id="unlike<s:property value='post_id'/>" style="color: white;" class="btn-label icon fa fa-thumbs-up"> <span style="font-family: arial;">Unlike</span></a></s:else></span>&nbsp;&nbsp;<a id="likes<s:property value='post_id'/>" value="<s:property value='post_id'/>" onclick="likeDetails(<s:property value='post_id'/>)" href="javascript: return null;" class="integers numbers" style="color: white"><s:property value="likesCount"/></a></span>
					</i>
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
							<!-- 
							<div class="bg-panel liker">
								<a href="">
									<span>
										<img class="rounded" src="user_profile_pics/hallarazad.jpg"/> &nbsp;
										Hallar Azad Mangi 
									</span>
								</a>
								
								<span id="btnSpan">
									<button class="btn btn-xs btn-success"><span class="fa fa-check-circle-o"></span>&nbsp;&nbsp;Follow</button>
								</span>
							
							</div>
							 -->
							
								
						</div>
					</div> <!-- / .modal-content -->
				</div> <!-- / .modal-dialog -->
			</div> <!-- / .modal -->
			<!-- / Small modal -->
			
			<!-- Modals Script -->
			<script>
				
			
				init.push(function() {
					$('#uidemo-modals-effects-btn').on('click', function() {
						var $modal  = $('#uidemo-modals-effects-template').clone(true);
						$modal.find('> div').addClass('modal-dialog modal-sm animated ' + $('#uidemo-modals-effects-select').find(":selected").attr('value'));
						$modal.modal('show');
					})
				});
			</script>
			<!-- /Modals Script -->
			
			
			<script>
				function followLikebox(follower) {
					var user = $("#userid").val();
					
					try {
						
						$.post("follow", {followerId:user, followedId: follower}, function(data, status) {
							
							$("#follow"+follower).remove();
							$("#btnSpan").html('<button id="unfollow'+follower+'" onclick="unfollowLikebox('+follower+')" class="btn btn-xs btn-success"><span class="fa fa-check-circle"></span>&nbsp;&nbsp;Unfollow</button>');
							
						});
					
					} catch(e) {
						alert(e.message)
					}
					
		
				}
				
				function unfollowLikebox(follower) {
					
					var user = $("#userid").val();
					
					try {
						$.post("unfollow", {followerId:user, followedId: follower}, function(data, status) {
							
							$("#unfollow"+follower).remove();
							$("#btnSpan").html('<button id="follow'+follower+'" onclick="followLikebox('+follower+')" class="btn btn-xs btn-default"><span class="fa fa-check-circle-o"></span>&nbsp;&nbsp;Follow</button>');
							
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
							$("#likeSpan"+historyId).html('<a href="javascript: return null;" onclick="unlike('+historyId+')" id="like'+historyId+'" style="color: white;" class="btn-label icon fa fa-thumbs-up"></a>');
							
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
							$("#likeSpan"+historyId).html('<a href="javascript: return null;" onclick="like('+historyId+')" id="like'+historyId+'" style="color: white;" class="btn-label icon fa fa-thumbs-o-up"></a>');
							
						});
					} catch(e) {
						alert(e.message)
					}
					
				}
			</script>
										
									</div> <!-- / .timeline -->
							
							<!-- /Timeline -->
							
						</div> <!-- / .tab-pane -->
						
						<div class="tab-pane fade" id="profile-tabs-activity">
							
							<!-- Test Records -->
							<!-- Timeline -->
							
									<script>
            			//script for main profile follow btn
						function makeItPublic(history_id) {
							
							try {
								
								$.post("makeItPublic", {historyId:history_id}, function(data, status) {
									
									$("#privacySpan"+history_id+" #public"+history_id).remove();
									$("#privacySpan"+history_id).html('<a href="javascript: return null;" onclick="makeItPrivate('+history_id+')" id="'+history_id+'" data-original-title="as;dkfj" style="color: #618b9d;" class="btn-label icon fa fa-users"></a>');
								});
							
							} catch(e) {
								alert(e.message)
							}
							
				
						}
						
						function makeItPrivate(history_id) {
							
							try {
								$.post("makeItPrivate", {historyId:history_id}, function(data, status) {
									$("#privacySpan"+history_id+" #private"+history_id).remove();
									$("#privacySpan"+history_id).html('<a href="javascript: return null;" onclick="makeItPublic('+history_id+')" id="'+history_id+'" data-original-title="as;dkfj" style="color: #618b9d;" class="btn-label icon fa fa-user"></a>');
								});
							} catch(e) {
								alert(e.message)
							}
							
						}
					</script>
							
									<div class="timeline">
										<!-- Timeline header -->
							
										<div class="tl-header now bg-primary">مشق جو رڪارڊ</div>
										
										<s:set name="next" value="false" scope="page" />
										
										<s:iterator value="testRecord">	
											
											
											<s:if test="is_public == true || #session.user_id == user_id">
											
													
												<s:if test="#next == false">
													<div class="tl-entry left">
													<s:set name="next" value="true"></s:set>
												</s:if>
												<s:else>
													<div class="tl-entry">
													<s:set name="next" value="false"></s:set>
												</s:else>
												
												<div class="tl-time">
													<abbr style="border-bottom: 0" class="timeago" title="<s:property value="date_time"/>"></abbr>
												</div>
												<div class="tl-icon bg-success"><img src="<s:property value='pic_url'/>" alt=""></div>
												<div class="panel tl-body">
													<span id="privacySpan<s:property value='history_id'/>"><s:if test="user_id == #session.user_id"> <s:if test="is_public == true"> <a href="javascript: return null;" onclick="makeItPrivate(<s:property value='history_id'/>)" id="public<s:property value='history_id'/>" data-original-title="as;dkfj" style="color: #618b9d;" class="btn-label icon fa fa-users"></a> </s:if> <s:else><a href="javascript: return null;" onclick="makeItPublic(<s:property value='history_id'/>)" id="private<s:property value='history_id'/>" style="color: #618b9d;" class="btn-label icon fa fa-user"></a></s:else></span> &nbsp;</s:if> <a href="<s:property value='username'/>"> <s:if test="user_id == #session.user_id"> توهان </s:if><s:else><s:property value="user_name"/></s:else></a> <a href="#"><s:property value="category_name"/></a> > <a href="#"><s:property value="sub_category_name"/></a> > <a href="#"><s:property value="test_name"/></a> ٽيسٽ ڏني جنهن ۾ <s:if test="user_id == #session.user_id">توهان</s:if><s:else>هن</s:else>
													<div class="well well-sm" style="margin: 10px 0 0 0;">
												
														<ul class="nav nav-pills">
															<li class="active"><a href="#">مڪمل<span class="label label-warning integers"><s:property value="total_attempted"/></span></a></li>
															<li class="active" ><a style="background-color:#36a766;" href="#">صحيح<span class="label label-warning integers"><s:property value="correct_answers"/></span></a></li>
															<li class="active" ><a style="background-color:#d54848;" href="#">غلط<span class="label label-warning integers"><s:property value="wrong_answers"/></span></a></li>
															<li class="active" ><a style="background-color:#3156be;" href="#">ڪاميابي<span class="label label-warning integers"><s:property value="score"/>%</span></a></li>
																	
														</ul>
													
														
													</div>
													</div> <!-- / .tl-body -->
												</div> <!-- / .tl-entry -->
											
											</s:if><!-- is public if end -->
										
										</s:iterator>
										
										
										
									</div> <!-- / .timeline -->
							
							<!-- /Timeline -->
							
						</div> <!-- / .tab-pane -->
						
						<div class="tab-pane fade widget-followers" id="profile-tabs-followers">

							<script>
									function follow(follower) {
										var user = $("#userid").val();
										
										try {
											
											$.post("follow", {followerId:user, followedId: follower}, function(data, status) {
												
												$("#follow"+follower+1).remove();
												$("#follower"+follower+1+" .follower-controls").html('<a id="unfollow'+follower+1+'" href="javascript: return null;" onclick="unfollow('+follower+')" class="btn btn-xs btn-success"><i class="fa fa-check-circle"></i><span>&nbsp;&nbsp;Unfollow</span></a>');
												
												$("#follow"+follower+2).remove();
												$("#follower"+follower+2+" .follower-controls").html('<a id="unfollow'+follower+2+'" href="javascript: return null;" onclick="unfollow('+follower+')" class="btn btn-xs btn-success"><i class="fa fa-check-circle"></i><span>&nbsp;&nbsp;Unfollow</span></a>');
											});
										
										} catch(e) {
											alert(e.message)
										}
										
							
									}
									
									function unfollow(follower) {
										
										var user = $("#userid").val();
										
										try {
											$.post("unfollow", {followerId:user, followedId: follower}, function(data, status) {
												$("#unfollow"+follower+1).remove();
												$("#follower"+follower+1+" .follower-controls").html('<a href="javascript: return null;" id="follow'+follower+1+'" onclick="follow('+follower+')" class="btn btn-xs btn-default"><i class="fa fa-check-circle-o"></i> Follow</a>');
												
												$("#unfollow"+follower+2).remove();
												$("#follower"+follower+2+" .follower-controls").html('<a href="javascript: return null;" id="follow'+follower+2+'" onclick="follow('+follower+')" class="btn btn-xs btn-default"><i class="fa fa-check-circle-o"></i> Follow</a>');
											});
										} catch(e) {
											alert(e.message)
										}
										
									}
								</script>

							<s:iterator value="followersList">
								
								
								
								
								
								
									<div id="follower<s:property value='userid'/>1" class="follower">
										<img src='<s:property value="picurl"/>' alt="" class="follower-avatar">
										<div class="body">
											<s:if test="userid != #session.user_id">
												<div class="follower-controls">
													<s:if test="isFollowed == true"><a id="unfollow<s:property value='userid'/>2" href="javascript: return null;" onclick="unfollow(<s:property value="userid"/>)" class="btn btn-xs btn-success"><i class="fa fa-check-circle"></i><span>&nbsp;&nbsp;Unfollow</span></a></s:if>
													<s:else><a href="javascript: return null;" id="follow<s:property value='userid'/>2" onclick="follow(<s:property value="userid"/>)" class="btn btn-xs btn-default"><i class="fa fa-check-circle-o"></i> Follow</a></s:else>
												</div>
											</s:if>
											<a href="<s:property value='username'/>" class="follower-name"><s:property value="fullname"/></a><br>
											<a href="<s:property value='username'/>" class="follower-username username"><s:property value="username"/></a>
										</div>
									</div>
								
								
							
							</s:iterator>

						</div> <!-- / .tab-pane -->
						<div class="tab-pane fade widget-followers" id="profile-tabs-following">
							
							<s:iterator value="followingList">
								
								
								<div id="follower<s:property value='userid'/>2" class="follower">
									<img src='<s:property value="picurl"/>' alt="" class="follower-avatar">
									<div class="body">
										<s:if test="userid != #session.user_id">
											<div class="follower-controls">
												<s:if test="isFollowed == true"><a id="unfollow<s:property value='userid'/>2" href="javascript: return null;" onclick="unfollow(<s:property value="userid"/>)" class="btn btn-xs btn-success"><i class="fa fa-check-circle"></i><span>&nbsp;&nbsp;Unfollow</span></a></s:if>
												<s:else><a href="javascript: return null;" id="follow<s:property value='userid'/>2" onclick="follow(<s:property value="userid"/>)" class="btn btn-xs btn-default"><i class="fa fa-check-circle-o"></i> Follow</a></s:else>
											</div>
										</s:if>
										<a href="<s:property value='username'/>" class="follower-name"><s:property value="fullname"/></a><br>
										<a href="<s:property value='username'/>" class="follower-username username"><s:property value="username"/></a>
									</div>
								</div>
								
							
							</s:iterator>

							
						</div> <!-- / .tab-pane -->
					</div> <!-- / .tab-content -->
				</div>
			</div>
		</div>
		

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
				var lastHistoryId;
				
				$(document).ready(function() {
					lastHistoryId = $("#lastHistoryId").val();
					//alert(lastHistoryId);
				});
				
				
				$(window).scroll(function () {
					
					if ($(window).scrollTop() >= $(document).height() - $(window).height() - 10) {
						try{
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
						} catch(e) {
							//alert(e.message)
						}
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


<script>
	$(document).ready(function() {
	  $("abbr.timeago").timeago();
	});
</script>

<script type="text/javascript">
	init.push(function () {
		$('#profile-tabs').tabdrop();

		$("#leave-comment-form").expandingInput({
			target: 'textarea',
			hidden_content: '> div',
			placeholder: 'Write message',
			onAfterExpand: function () {
				$('#leave-comment-form textarea').attr('rows', '3').autosize();
			}
		});
	})
	window.PixelAdmin.start(init);
</script>

</body>

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-profile.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:23 GMT -->
</html>