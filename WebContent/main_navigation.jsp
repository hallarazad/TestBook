<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<!-- 2. $MAIN_NAVIGATION ===========================================================================

	Main navigation
-->
	<div id="main-navbar" class="navbar navbar-inverse" role="navigation">
		<!-- Main menu toggle -->
		<button type="button" id="main-menu-toggle"><i class="navbar-icon fa fa-bars icon"></i><span class="hide-menu-text">Hide Menu</span></button>
		
		<div class="navbar-inner">
			<!-- Main navbar header -->
			<div class="navbar-header">

				<!-- Logo -->
				<a href="index.html" class="navbar-brand">
					<div><img alt="Pixel Admin" src="assets/images/pixel-admin/main-navbar-logo.png"></div>
					TestBook
				</a>

				<!-- Main navbar toggle -->
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar-collapse"><i class="navbar-icon fa fa-bars"></i></button>

			</div> <!-- / .navbar-header -->

			<div id="main-navbar-collapse" class="collapse navbar-collapse main-navbar-collapse">
				<div>
					
					
					<ul class="nav navbar-nav">
						<li>
							<a href="timelineAction">Home</a>
						</li>
						<li>
							<a href="${session.username}">Profile</a>
						</li>
						<!-- <li>
							<a href="#">Quick Competition</a>
						</li> -->
						<!-- 
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown</a>
							<ul class="dropdown-menu">
								<li><a href="#">First item</a></li>
								<li><a href="#">Second item</a></li>
								<li class="divider"></li>
								<li><a href="#">Third item</a></li>
							</ul>
						</li>
						-->
					</ul> <!-- / .navbar-nav -->
					
					
					<div class="right clearfix">
						<ul class="nav navbar-nav pull-right right-navbar-nav">

<!-- 3. $NAVBAR_ICON_BUTTONS =======================================================================

							Navbar Icon Buttons

							NOTE: .nav-icon-btn triggers a dropdown menu on desktop screens only. On small screens .nav-icon-btn acts like a hyperlink.

							Classes:
							* 'nav-icon-btn-info'
							* 'nav-icon-btn-success'
							* 'nav-icon-btn-warning'
							* 'nav-icon-btn-danger' 
-->

<!-- /3. $END_NAVBAR_ICON_BUTTONS -->
														
							<li onclick="friendRequestsSeen()" id="friend-requests-li" class="nav-icon-btn nav-icon-btn-success dropdown">
								<a href="#messages" class="dropdown-toggle" data-toggle="dropdown">
									
									<span id="friendRequestCountSpan" class="label" style="font-size: 0.86em; margin-right: -0.3em;"><s:property value='friendRequestCount'/></span>
									
									<i class="nav-icon fa fa-user"></i>
									<span class="small-screen-text">Friend Requests</span>
								</a>

								<!-- MESSAGES -->
								
								<!-- Javascript -->
								<script>
									
									function friendRequestsSeen() {
										
										$.post("friendRequestsSeen", function(data, status) {
											if(status === "success") {
										
											}
										});
									}
								
									init.push(function () {
										
										$('#main-navbar-messages').slimScroll({ height: 250 });
									});
								</script>
								<!-- / Javascript -->
								
								
								<!-- call jugaar method onclick which will add 'open' class to the friend requests div to keep it open -->
								<div onclick="jugaar()" class="dropdown-menu widget-messages-alt no-padding" style="width: 300px;">
									<div class="messages-list" id="main-navbar-messages">
										
										<s:iterator value="friendRequests">
											<s:if test="is_seen == true">
												<div class="message">
													<img src="<s:property value='sender_picurl'/>" alt="" class="message-avatar">
													<a href="<s:property value='sender_username'/>" class="message-subject"><strong><s:property value='sender_fullname'/></strong> <span class="text-sm pull-right"><abbr style="border-bottom: 0" class="timeago" title="<s:property value="date_time"/>"></abbr></span></a>
													<div class="message-description" id="friendRequestBtnsDiv<s:property value='friend_request_id'/>">
														<button onclick="acceptRequest(<s:property value='friend_request_id'/>)" class="btn btn-flat btn-xs btn-labeled btn-info"><span class="btn-label icon fa fa-user"></span>Be Friends</button>
														<button class="btn btn-flat btn-xs btn-labeled btn-danger"><span class="btn-label icon fa fa-ban"></span>Delete</button>
														
													</div>
												</div> <!-- / friend request -->
											</s:if>
											<s:else>
												<div class="message bg-panel">
													<img src="<s:property value='sender_picurl'/>" alt="" class="message-avatar">
													<a href="<s:property value='sender_username'/>" class="message-subject"><strong><s:property value='sender_fullname'/></strong> <span class="text-sm pull-right"><abbr style="border-bottom: 0" class="timeago" title="<s:property value="date_time"/>"></abbr></span></a>
													<div class="message-description" id="friendRequestBtnsDiv<s:property value='friend_request_id'/>">
														<button onclick="acceptRequest(<s:property value='friend_request_id'/>)" class="btn btn-flat btn-xs btn-labeled btn-info"><span class="btn-label icon fa fa-user"></span>Be Friends</button>
														<button class="btn btn-flat btn-xs btn-labeled btn-danger"><span class="btn-label icon fa fa-ban"></span>Delete</button>
														
													</div>
												</div> <!-- / friend request -->
											</s:else>
											
										</s:iterator>
										
										<s:if test="friendRequests.size == 0">
											<i style="margin-left:20%; top: 50%">You have no friend requests!</i>
										</s:if>
										
										<script>
											function jugaar() {
												
												setTimeout(function() {
													
													try {
														$('#friend-requests-li').addClass('open');
													}catch(e) {
														alert(e.message);
													}
												
												}, 0.1);
											}
										</script>
									
										<!-- Javascript for friend request handling -->
										<script>
											function acceptRequest(request_id) {
												$.post("acceptRequest", {friend_request_id:request_id}, function(data, status) {
													if(status === "success") {
														$("#friendRequestBtnsDiv"+request_id).html(data);
													}
												});
											}
										</script>
										<!-- /Javascript -->
									
									</div> <!-- / .messages-list -->
									<a href="#" class="messages-link" id="seeMoreRequestsBtn">MORE MESSAGES</a>
								</div> <!-- / .dropdown-menu -->
							</li>
<!-- /3. $END_NAVBAR_ICON_BUTTONS -->
							
							<script>
								init.push(function() {
									var count = $("#friendRequestCountSpan").text();
									
									if(count == 0) {
										$("#friendRequestCountSpan").addClass("hidden");
									}
									
									if(count < 4) {
										
										$("#seeMoreRequestsBtn").text("NO FRIEND REQUESTS");
										$("#seeMoreRequestsBtn").attr("href", "javascript: return null;");
									}
								});
							</script>

							<li>
								<form class="navbar-form pull-left">
									<input type="text" class="form-control" placeholder="Search">
								</form>
							</li>
							
							<!-- Javascript -->
							<script>
								init.push(function () {
									$('#rankBtn').tooltip();
								});
							</script>
							<!-- Javascript -->

							
							
							<li class="dropdown">
								<a href="#" class="dropdown-toggle user-menu" data-toggle="dropdown">
									<img src="${sessionScope.picurl}">
									<span>${sessionScope.fullname}</span>
									<button id="rankBtn" type="button" class="btn rounded badge badge-info btn-info tooltip-info" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Your Rank among your <s:property value="friendsCount"/> friend(s)"><s:property value="rank"/></button>
									<!--<span class="badge badge-default" style="font-size:17px"><font face="arial" size="3"><s:property value="rank"/></font> </span>-->
								</a>
								<ul class="dropdown-menu">
									<li><a href="#"><span class="label label-warning pull-right">New</span>Profile</a></li>
									
									<li><a href="#"><i class="dropdown-icon fa fa-cog"></i>&nbsp;&nbsp;Settings</a></li>
									<li class="divider"></li>
									<li><a href="logout"><i class="dropdown-icon fa fa-power-off"></i>&nbsp;&nbsp;Logout</a></li>
								</ul>
							</li>
							<li class=""> </li>
						</ul> <!-- / .navbar-nav -->
					</div> <!-- / .right -->
				</div>
			</div> <!-- / #main-navbar-collapse -->
		</div> <!-- / .navbar-inner -->
	</div> <!-- / #main-navbar -->
	<!-- /2. $END_MAIN_NAVIGATION -->

	
