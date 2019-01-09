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
	<title>Update Test - TestBook</title>
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
			
			<form method="post" action="addQuestion" id="add_form">
				
				
				<div class="form-group">
					<!--<span style="color: #36a766; font-weight: 500; font-size: 18px;">پاڪستان جي گادي جو هنڌ ڪهڙو آهي؟</span>-->
					<!-- <textarea placeholder="Question" class="form-control text-bg" rows="3"></textarea> -->
					<input style="padding-top: -20px;" type="text" name="question" id="question" class="form-control input-lg" placeholder="Question">
				</div>
				
				<br/>
				
				<div id="options">
					<div class="form-group option">
						<div class="input-group">
								<span class="input-group-addon">
									<input id="radio" class="radiobtn" name="selectedOption" value="0" type="radio">
								</span>
								<input name="option0.text" type="text" class="form-control option-text">
							</div>
					</div>
	
					<div class="form-group option">
						<div class="input-group">
								<span class="input-group-addon">
									<input id="radio" class="radiobtn" name="selectedOption" value="1" type="radio">
								</span>
								<input name="option1.text" type="text" class="form-control option-text">
							</div>
					</div>
				</div>
				
				<input id="optionCount" type="hidden" name="optionCount" value="2" />
				<input id="testId" type="hidden" name="testId" value="1" /> 
				
				<div class="form-actions">
					<input type="button" onclick="$.when(addOption()).then(setNames())" value="Add Option" class="btn btn-success btn-rounded" />
					<input type="button" onclick="$.when(deleteOption()).then(setNames())" value="Delete Option" class="btn btn-danger btn-rounded" />
					
				</div>
				
				<div class="form-actions">
					<input type="submit" style="color: white" value="Add Question" class="signup-btn bg-primary">
				</div>
			</form>
			<!-- / Form -->
			
			
			
			

			
		</div>
		<!-- Right side -->
	</div>
	<!-- 
	<div class="have-account">
		Already have an account? <a href="pages-signin.html">Sign In</a>
	</div>
	-->
	
	
	<!-- Success Modal -->
		<div id="uidemo-modals-alerts-success" class="modal modal-alert modal-success fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<i class="fa fa-check-circle"></i>
					</div>
					<div class="modal-title">Question Added Successfully!</div>
					<!-- <div class="modal-body">Some alert text</div> -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">OK</button>
					</div>
				</div> <!-- / .modal-content -->
			</div> <!-- / .modal-dialog -->
		</div> <!-- / .modal -->
	<!-- / Success Modal -->
	
	<button id="modalbtn" style="display: none;" class="btn btn-success" data-toggle="modal" data-target="#uidemo-modals-alerts-success"></button>
	
	<script>
	
		
		function addOption() {
			var optionCount = $(".option").length;
			if(optionCount < 5) {
				
				var text = "<div class=\"form-group option\">";
				text += "<div class=\"input-group\">";
				text += "<span class=\"input-group-addon\">";
					text += "<input name=\"\" class=\"radiobtn\" value=\"\" type=\"radio\">";
				text += "</span>";
				text += "<input name=\"\" type=\"text\" class=\"form-control option-text\">";
				text += "</div>";
				text += "</div>";
				
				$("#options").append(text);
				
				setOptionCount();
				
				
			}
		}
		
		function deleteOption() {
			var last = $(".option").length - 1;
			if(last > 1) {
				$(".option").eq(last).remove();
				
			}
			
			setOptionCount();
		}
		
		function setNames() {
			var count = $(".radiobtn").length;
			
			for(var i=0; i<count; i++) {
				
				$(".radiobtn").eq(i).attr("name","selectedOption");
				$(".radiobtn").eq(i).val(""+i);
				$(".option-text").eq(i).attr("name","option"+i+".text");
				//alert($(".option-text").eq(i).attr("name"));
				//alert($(".radiobtn").eq(i).attr("name"));
			}
		}
		
		function setOptionCount() {
			var count = $(".radiobtn").length;
			$("#optionCount").val(count);
			//alert($("#optionCount").val());
		}
	</script>
	
	

 
	



<!-- Pixel Admin's javascripts -->
<script src="assets/javascripts/jquery.js"></script>
<script src="assets/javascripts/pixel-admin.min.js"></script>
<script src="assets/javascripts/bootbox.min.js"></script>
<script src="assets/javascripts/bootstrap.min.js"></script>
<script src="assets/javascripts/jquery.form.js"></script>





<script>
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
		
		//try {
		$("#add_form").validate({ focusInvalid: true, errorPlacement: function () {} });
		alert("234");
		

		// Validate name
		$(".option-text").rules("add", {
			required: true,
			minlength: 15
		});

		// Validate email
		$("#question").rules("add", {
			required: true,
			minlength: 15
		});
		
		// Validate username
		/*$("#username_id").rules("add", {
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
		}); */
		
		//}catch(e){alert(e.message);}
	});

	window.PixelAdmin.start(init);
</script>




	<script>

		try {
  		
        (function() {

            //var bar = $('.bar');
            //var percent = $('.percent');
            //var status = $('#status');

            var form = $('#add_form').ajaxForm({
            	
                beforeSend: function() {
                	
                	var isValid = true;
                    var count = 0;
                    
                    //count the number of empty textboxes
                	$('input[type="text"]').each(function() {
                        if ($.trim($(this).val()) == '') {
                        	count++;
                        	isValid = false;
                        }
                        
                    });
                	
                	if(count > 0) {
                    	$.growl.error({ message: "Please fill all the boxes!" });
                    }
                	
                	
                	//check to see if no option has been checked
                	if($('input[name=selectedOption]:checked').length<=0)
                	{
                		
                		$.growl.error({ message: "Please select the correct option!" });

                		/*
                		bootbox.alert({
							message: "Please select the correct option!",
							callback: function() {
								//alert("Hello world callback");
							},
							className: "bootbox-sm"
						});
                		*/

                		
                		form.abort();
                	 
                	}

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
					$("#modalbtn").trigger("click");
					setNames();
                    //status.html(xhr.responseText);
                }
            });
        })();
        
		}catch(e) {alert(e.message);}

        
 </script>



</body>

<!-- Mirrored from infinite-woodland-5276.herokuapp.com/pages-signup.html by HTTrack Website Copier/3.x [XR&CO'2013], Sun, 15 Mar 2015 07:51:23 GMT -->
</html>
