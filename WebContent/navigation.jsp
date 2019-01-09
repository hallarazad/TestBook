<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="main-menu" role="navigation">
		<div id="main-menu-inner">
			
			
			<div class="menu-content top" id="menu-content-demo">
			
			
			</div>
			
			<ul class="navigation">
				
				<li>
					<a href="timelineAction"><i class="menu-icon fa fa-book"></i><span class="mm-text">Home</span></a>
				</li>
				<li>
					<a href="choose-a-test"><i class="menu-icon fa fa-question-circle"></i><span class="mm-text">Start A Test</span></a>
				</li>
				
				<!-- Categories Iteration in main menu -->
				<!-- 
				<li class="mm-dropdown">
					<a href="#"><i class="menu-icon fa fa-question-circle"></i><span class="mm-text">Test Categories</span></a>
					<ul>
						
						<s:iterator value="catagories" >
							<li>
								<a tabindex="-1" href="category?c=<s:property value="category_id"/>"><span class="mm-text"><s:property value="category_name"/></span></a>
							</li>
						</s:iterator>
						
					</ul>
				</li>
				-->
				<!-- /Categories Iteration in main menu -->
				<!-- 
				<li>
					<a href="stat-panels.html"><i class="menu-icon fa fa-tasks"></i><span class="mm-text">Menu Item</span></a>
				</li>
				<li>
					<a href="widgets.html"><i class="menu-icon fa fa-flask"></i><span class="mm-text">Menu Item</span></a>
				</li>
				-->
				
				
			</ul> <!-- / .navigation -->
			<div class="menu-content">
				<!-- <a href="pages-invoice.html" class="btn btn-primary btn-block btn-outline dark">A Button</a> -->
				
				
				
			</div>
		</div> <!-- / #main-menu-inner -->
	</div> <!-- / #main-menu -->
<!-- /4. $MAIN_MENU -->