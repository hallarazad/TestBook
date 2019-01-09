<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>

	<s:iterator value="catagories" >
		<li>
			<a tabindex="-1" href="<s:property value="category_id"/>"><span class="mm-text"><s:property value="category_name"/></span></a>
		</li>
	</s:iterator>
		
    