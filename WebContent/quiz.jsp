<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if IE 9]>         <html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-signup.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:23 GMT -->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Sign Up - PixelAdmin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	<!-- Pixel Admin's stylesheets -->
	<link href="assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
	<link href="assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">

	<!--[if lt IE 9]>
		<script src="assets/javascripts/ie.min.js"></script>
	<![endif]-->


<!-- $DEMO =========================================================================================

	Remove this section on production
-->
	<style>
		#signup-demo {
			position: fixed;
			right: 0;
			bottom: 0;
			z-index: 10000;
			background: rgba(0,0,0,.6);
			padding: 6px;
			border-radius: 3px;
		}
		#signup-demo img { cursor: pointer; height: 40px; }
		#signup-demo img:hover { opacity: .5; }
		#signup-demo div {
			color: #fff;
			font-size: 10px;
			font-weight: 600;
			padding-bottom: 6px;
		}
	</style>
<!-- / $DEMO -->

<style>
		.integers {
			font-size:13px;
			font-family:helvetica;
		}
		
	</style>

</head>


<!-- 1. $BODY ======================================================================================
	
	Body

	Classes:
	* 'theme-{THEME NAME}'
	* 'right-to-left'     - Sets text direction to right-to-left
-->
<body class="theme-default page-signup">

<script>
	var init = [];
	//init.push(function () {
		//var $div = $('<div id="signup-demo" class="hidden-xs"><div>PAGE BACKGROUND</div></div>'),
		  //  bgs  = [ 'assets/demo/signin-bg-1.jpg', 'assets/demo/signin-bg-2.jpg', 'assets/demo/signin-bg-3.jpg',
		    //		 'assets/demo/signin-bg-4.jpg', 'assets/demo/signin-bg-5.jpg', 'assets/demo/signin-bg-6.jpg',
			//		 'assets/demo/signin-bg-7.jpg', 'assets/demo/signin-bg-8.jpg', 'assets/demo/signin-bg-9.jpg' ];
		//for (var i=0, l=bgs.length; i < l; i++) $div.append($('<img src="' + bgs[i] + '">'));
		//$div.find('img').click(function () {
			//var img = new Image();
			//img.onload = function () {
				//$('#page-signup-bg > img').attr('src', img.src);
				//$(window).resize();
			//}
			//img.src = $(this).attr('src');
		//});
		//$('body').append($div);
	//});
</script>

	<!-- Page background -->
	<div id="page-signup-bg">
		<!-- Background overlay -->
		<div class="overlay"></div>
		<!-- Replace this with your bg image -->
		<img src="assets/demo/signin-bg-3.jpg" alt="">
	</div>
	<!-- / Page background -->

	<!-- Container -->
	<div class="signup-container">
		<!-- Header -->
		<div class="signup-header">
			<a href="index.html" class="logo">
				<img src="assets/demo/logo-big.png" alt="" style="margin-top: -5px;">&nbsp;
				TestBook
			</a> <!-- / .logo -->
			<div class="slogan">
				Quiz, Competition & Social
			</div> <!-- / .slogan -->
		</div>
		<!-- / Header -->

		<!-- Form -->
		<div class="signup-form">
			<form style="display: none" action="proceedTest" id="quizForm">
				
				<input name="questionId" type="hidden" value="" id="questionId" />
				<input type="hidden" name="testId" id="testId" value="<s:property value='t' />" />
				
				<div class="form-group w-icon">
					<span id="questionSpan" style="color: #36a766; font-weight: 500; font-size: 18px;"></span>
				</div>
				
				<br/>
				
				<span id="optionsSpan">
					<div class="form-group w-icon">
						<label class="radio">
							<input type="radio" name="styled-r1" class="px">
							<span class="lbl"></span>
						</label>
					</div>
	
					<div class="form-group w-icon">
						<label class="radio">
							<input type="radio" name="styled-r1" class="px">
							<span class="lbl"></span>
						</label>
					</div>
	
					<div class="form-group w-icon">
						<label class="radio">
							<input type="radio" name="styled-r1" class="px">
							<span class="lbl"></span>
						</label>
					</div>
	
					<div class="form-group w-icon">
						<label class="radio">
							<input type="radio" name="styled-r1" class="px">
							<span class="lbl"></span>
						</label>
					</div>
				</span>
				
				<input name="total" type="hidden" value="" id="total" />
				<input name="attempted" type="hidden" value="1" id="attempted" />
				
				<div class="signup-text">
					<span><span id="attemptedSpan">1</span> / <span id="totalSpan"></span></span>
				</div>

				<div class="form-actions">
					<input type="submit" value="Next" class="signup-btn bg-primary">
				</div>
				<div class="form-actions">
					<a href="timelineAction"><input type="button"  style="background-color: #d54848; color: white" value="End Test" class="signup-btn"> </a>
				</div>
			</form>
			<!-- / Form -->
			
			<div id="input">
				<input type="hidden" id="testId" value="<s:property value='t' />" />
				<div class="form-group w-icon">
					<span style="color: #618b9d; font-weight: 600; font-size: 18px;">Select no of questions</span>
				</div>
				
				<div class="form-group w-icon">
					<label class="radio">
						<input type="radio" value="5" name="noOfQuestions" class="px">
						<span class="lbl">5</span>
					</label>
				</div>
				
				<div class="form-group w-icon">
					<label class="radio">
						<input type="radio" value="10" name="noOfQuestions" class="px">
						<span class="lbl">10</span>
					</label>
				</div>
				
				<div class="form-group w-icon">
					<label class="radio">
						<input type="radio" value="15" name="noOfQuestions" class="px">
						<span class="lbl">15</span>
					</label>
				</div>
				 
				
				<!-- 
				<div class="form-group">
				
					<select id="noOfQuestions" multiple="" class="integers form-control">
						<option selected="selected">10</option>
						<option>15</option>
						<option>20</option>
					</select>
				</div>
				 -->
				
				<div class="form-actions">
					<input onclick="startTest()" type="submit" value="Begin" class="signup-btn bg-primary">
				</div>
			</div>

			
		</div>
		<!-- Right side -->
	</div>
	<!-- 
	<div class="have-account">
		Already have an account? <a href="pages-signin.html">Sign In</a>
	</div>
	-->
	
	<script>
	
		//var total = 0;
		//var testId = 0;
		//var attempted = 1;
		
		function startTest() {
			
			var total = $("input[name=noOfQuestions]:checked").val();
			total = ++total - 1;
			//alert(total);
			var testId = $("#testId").val();
			
			//alert(total + "\n" + testId);
			
			try {
				$.post("startTest", {totalQuestions:total, testId:testId}, function(data, status) {
					try {
					var resObj = JSON.parse(data);
					//alert(resObj.question);
					
					
					$("#questionSpan").html(resObj.question);
					$("#optionsSpan").html(resObj.options);
					$("#questionId").val(resObj.questionId);
					
					$("#totalSpan").html(total);
					$("#total").val(total);
					
					$("#input").css("display", "none");
					$("#quizForm").css("display", "block");
					}catch(e) {alert(e.message);}
				});
			}catch(e) {
				alert(e.message);
			}
			
		}
		
		
		
	</script>
	
	 
	
	


<!-- Get jQuery from Google CDN -->
<!--[if !IE]> -->
	<script type="text/javascript"> window.jQuery || document.write('<script src="../ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js">'+"<"+"/script>"); </script>
<!-- <![endif]-->
<!--[if lte IE 9]>
	<script type="text/javascript"> window.jQuery || document.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">'+"<"+"/script>"); </script>
<![endif]-->


<!-- Pixel Admin's javascripts -->

<script src="assets/javascripts/pixel-admin.min.js"></script>
<script src="assets/javascripts/jquery.js"></script>
<script src="assets/javascripts/bootbox.min.js"></script>
<script src="assets/javascripts/bootstrap.min.js"></script>
<script src="assets/javascripts/jquery.form.js"></script>
<!--<script src="assets/javascripts/jquery-1.11.0.min.js"></script>-->


<script>
		
		//init.push(function () {
			//try{
				
				//bootbox.prompt("ڪيترا سوال هجن؟", function(result) {                
				  //if (result === null) {                                             
				    //Example.show("Prompt dismissed");                              
				  //} else {
				    //Example.show("Hi <b>"+result+"</b>");                          
				  //}
				//});

			//}catch(e) {
				//alert(e.message);
			//}
		
		//});
		

	</script>

<script type="text/javascript">
	// Resize BG
	init.push(function () {
		var $ph  = $('#page-signup-bg'),
		    $img = $ph.find('> img');

		$(window).on('resize', function () {
			$img.attr('style', '');
			if ($img.height() < $ph.height()) {
				$img.css({
					height: '100%',
					width: 'auto'
				});
			}
		});

		$("#signup-form_id").validate({ focusInvalid: true, errorPlacement: function () {} });

		// Validate name
		$("#name_id").rules("add", {
			required: true,
			minlength: 1
		});

		// Validate email
		$("#email_id").rules("add", {
			required: true,
			email: true
		});
		
		// Validate username
		$("#username_id").rules("add", {
			required: true,
			minlength: 3
		});

		// Validate password
		$("#password_id").rules("add", {
			required: true,
			minlength: 6
		});

		// Validate confirm checkbox
		$("#confirm_id").rules("add", {
			required: true
		});
	});

	window.PixelAdmin.start(init);
</script>


<script>

		try {
  		
        (function() {

            //var bar = $('.bar');
            //var percent = $('.percent');
            //var status = $('#status');

            var form = $('#quizForm').ajaxForm({
         
                beforeSend: function() {
                	if($('input[name=selectedOption]:checked').length<=0)
                	{
                		//alert("No radio checked");
                		
                		bootbox.alert({
							message: "توهان ڪو به آپشن سليڪٽ ناهي ڪيو!",
							callback: function() {
								//alert("Hello world callback");
							},
							className: "bootbox-sm"
						});

                		
                		form.abort();
                	 
                	}
                	//alert("Q Id at page = " + $("#questionId").val());
                	
                    //var percentVal = '0%';
                    //bar.width(percentVal)
                    //percent.html(percentVal);
                    
                    
                },
                uploadProgress: function(event, position, total, percentComplete) {
                    //var percentVal = percentComplete + '%';
                    //bar.width(percentVal)
                    //percent.html(percentVal);
                },
                success: function() {
                	//alert("Success!");
                    //var percentVal = '100%';
                    //bar.width(percentVal)
                    //percent.html(percentVal);

                    $(':input','#add_form')
                    .not(':button, :submit, :reset, :hidden')
                    .val('')
                    .removeAttr('checked')
                    .removeAttr('selected');
                    

                },
                complete: function(xhr) {
                	try {
                		
                		if(xhr.responseText === "finish") {
                			window.location.href = "finishTest";
                		} else {
                		
	                		var attempted = $("#attempted").val();
	                		var total = $("#total").val();
	                		
	                		attempted = ++attempted;
	                		//alert(attempted);
	                		$("#attempted").val(attempted);
	                		
	      					var resObj = JSON.parse(xhr.responseText);
	      					//alert(resObj.question);
	      					
	      					//alert("Q Id from server = " + resObj.questionId);
	      					
	      					$("#questionSpan").html(resObj.question);
	      					$("#optionsSpan").html(resObj.options);
	      					$("#questionId").val(resObj.questionId);
	      					
	      					$("#totalSpan").html(total);
	      					$("#attemptedSpan").html(attempted);
	      					$("#total").val(total);
	      					
	      					$("#input").css("display", "none");
	      					$("#quizForm").css("display", "block");
      					
      					}
        					
        				
        			}catch(e) {
        				alert(e.message);
        			}
                }
            });
        })();
        
		}catch(e) {alert(e.message);}

        
 </script>

</body>

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-signup.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:23 GMT -->
</html>
