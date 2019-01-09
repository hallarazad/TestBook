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
	<link href="assets/stylesheets/ImageSelect.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/chosen.css" rel="stylesheet" type="text/css">

	<style>
		.testlogo {
			width: 70%;
			height: 70%;
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
<body class="theme-default main-menu-animated main-navbar-fixed">

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
		
		<s:iterator value="testsList">
			
			
				
				<div class="col-md-4">
	
					<div class="stat-panel" style="height: 160px; margin: 10px">
						<!-- Success background. vertically centered text -->
						<div class="stat-cell bg-info valign-middle">
							<!-- Stat panel bg icon -->
							<i class="fa fa-question bg-icon"></i>
							<!-- Extra large text -->
							<span style="font-size: 2em;"><strong><s:property value='test_name'/></strong></span><br>
							<!-- Big text -->
							<span class="text-bg">
								
							</span><br>
							<!-- Small text -->
							<span class="text-sm">
								<a href="test?t=<s:property value='test_id'/>"><button class="btn btn-sm btn-rounded">Start Test</button></a>
								<button onclick="openCompetitionModal(<s:property value='test_id'/>)" class="btn btn-sm btn-rounded" data-toggle="modal" data-target="#myModal">Arrange Competition</button>
							</span>
						</div> <!-- /.stat-cell -->
					</div> <!-- /.stat-panel -->
	
	
				</div>
				
			
		
		</s:iterator>
		
		
		<script>
			function openCompetitionModal(testId) {
				$("#friendsSelect").load("loadFriends", function( response, status, xhr ) {
					if(status === "success") {
						if(response != "") {
							$("#testId").val(testId);
							$("#friendsSelect").trigger("chosen:updated");
						} else {
							$("#modalHeading").text("Sorry you have no friends yet!").addClass("text-danger");
							$("#friendsSelect").attr("disabled", "disabled");
							
							$("#friendsSelect").trigger("chosen:updated");
							
						}
					} else {
						
					}
				});
			}
		</script>
		
		<!-- Modal -->
		<div id="myModal" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">Arrange a Competition</h4>
					</div>
					<form action="arrangeCompetition" method="POST">
						<div class="modal-body">
							<h4 id="modalHeading" class="form-group-margin">Select friends to compete with:</h4>
							
							<input type="hidden" id="testId" name="testId" value=""/>
						
							<select id="friendsSelect" name="selectedFriends" class="input-bg" data-placeholder="Search friends" multiple="multiple">
							  
							</select> <br/><br/>
								
							<h4 id="modalHeading" class="form-group-margin">Select number of questions:</h4>	
							
							
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-success active">
								<input checked="checked" type="radio" name="numberOfQuestions" value="10" id="option1"> 10
								</label>
								<label class="btn btn-success">
									<input type="radio" name="numberOfQuestions" value="15" id="option2"> 15
								</label>
								<label class="btn btn-success">
									<input type="radio" name="numberOfQuestions" value="20" id="option3"> 20
								</label>
							</div> <!-- / .btn-group -->
							
								
								
						</div> <!-- / .modal-body -->
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							<input type="submit" class="btn btn-primary" value="Arrange"/>
						</div>
					</form>
				</div> <!-- / .modal-content -->
			</div> <!-- / .modal-dialog -->
		</div> <!-- /.modal -->
		<!-- / Modal -->
		
		

				

	</div> <!-- / #content-wrapper -->
	<div id="main-menu-bg"></div>
</div> <!-- / #main-wrapper -->

<script src="assets/javascripts/jquery-1.11.0.min.js"></script>
<script src="assets/javascripts/chosen.jquery.js"></script>
<script src="assets/javascripts/ImageSelect.jquery.js"></script>


<script>
	$(document).ready(function() {
		try {	
			$("#friendsSelect").chosen({
			    width:"100%",
			    html_template: '<img style="height: 2em; width: 2em;" class="rounded {class_name}" src="{url}" />',
			    disable_search_threshold: 8,
			    no_results_text: "No friend found!",
			    max_selected_options: 10,
			    enable_split_word_search: true,
			    inherit_select_classes: true
			    
			});
		}catch(e) {
			alert(e.message);
		}
	});
</script>






<!-- Pixel Admin's javascripts -->

<script src="assets/javascripts/bootstrap.min.js"></script>
<script src="assets/javascripts/pixel-admin.min.js"></script>



<script type="text/javascript">

	init.push(function () {
		
	});
	window.PixelAdmin.start(init);
</script>

</body>

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-blank.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:27 GMT -->
</html>