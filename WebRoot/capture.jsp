<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>a2.html</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
</head>
<script language="JavaScript">

	function viewData() {		
		var sData = window.dialogArguments; 
		window.document.getElementById("output").appendChild(sData);


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
