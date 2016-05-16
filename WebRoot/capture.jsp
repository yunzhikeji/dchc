<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>capture</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
</head>
<script language="JavaScript">
var params = window.location.search;
	alert(params);
	function viewData() {		
		var sData = window.dialogArguments; 

		window.document.getElementById("output").appendChild(sData);
		
		var picSrc = document.getElementById("canvasCapture");
		


	}
	window.onload = viewData;
</script>
<body>
	<form name="form1" action="mediaAction!add" method="post">
		<div id="output"></div>
	 <input type="submit" value="保存并提交" /> 
						<button onclick="window.close();" type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
	</form>
</body>

</html>
