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
|  5. $CONTENT              |  Content              |

=====================================================

-->


<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if IE 9]>         <html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-blank.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:25 GMT -->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Blank - Pages - PixelAdmin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	
	<!-- Pixel Admin's stylesheets -->
	<link href="assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/widgets.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">

	<style>
		.testlogo {
			width: 70%;
			height: 70%;
		}
		.integers {
			font-size:15px;
			font-family:helvetica;
		}
	</style>

	<!--[if lt IE 9]>
		<script src="assets/javascripts/ie.min.js"></script>
	<![endif]-->

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
<body class="theme-default main-menu-animated">

<script>var init = [];</script>
<!-- Demo script <script src="assets/demo/demo.js"></script> Demo script -->

<div id="main-wrapper">


<!-- Main Navigation -->

<jsp:include page="main_navigation.jsp"></jsp:include>

<!-- /Main Navigation -->


<!-- Include side navigation -->
	<jsp:include page="navigation.jsp"></jsp:include>


	<div id="content-wrapper">
	<!-- 5. $CONTENT -->

		<!-- Content here -->
		
		
		
		<div class="note note-info col-md-4">
				<h3 class="note-title">مڪمل</h4>
				<div class="integers"><s:property value="result.total_attempted"/></div>
		</div>
		
		<div class="note note-success col-md-4">
				<h3 class="note-title">صحيح</h4>
				<div class="integers"><s:property value="result.correct_answers"/></div>
		</div>
		
		<div class="note note-danger col-md-4">
				<h3 class="note-title">غلط</h4>
				<div class="integers"><s:property value="result.wrong_answers"/></div>
		</div>
		
		
		<div>
			<div class="text-center text-lg text-semibold form-group-margin">ڪاميابي: %<s:property value="result.score"/></div>
			<div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width: <s:property value="result.score"/>%;"></div></div>
		</div>
		
		<div>
			<ol class="text-default" style="font-size: 1.1em;">
				<s:iterator value="questionsAttempted">
					
					<li class="bg-panel form-group-margin panel-padding">
						
						<span class=""><s:property value="question"/></span>
						
						<ol style="margin-top: 10px; list-style-type: upper-alpha;" class="text-danger">
							<s:iterator value="options">
								<s:if test="is_correct"><li class="text-success"><s:property value="text"/>&nbsp;&nbsp;<i class="fa fa-check"></i></li></s:if>
								<s:else><li><s:property value="text"/>&nbsp;&nbsp;<i class="fa fa-times"></i></li></s:else>
							</s:iterator>
						</ol>
					</li>
					
				</s:iterator>
			</ol>
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
<script src="assets/javascripts/bootstrap.min.js"></script>
<script src="assets/javascripts/pixel-admin.min.js"></script>


<script type="text/javascript">

	init.push(function () {
		// Javascript code here
	});
	window.PixelAdmin.start(init);
</script>

</body>

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-blank.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:27 GMT -->
</html>