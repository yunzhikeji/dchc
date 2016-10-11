<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>研判工具</title>
    <style type="text/css">
        body {
            background: White;
            color: #000;
            margin: 0;
            padding: 0;
        }

        #container {
            margin: 0 auto;
 
        }
    </style>
    <!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/ncss.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>
<body>
    <div id=container >
    <center style="margin-top:100px"><h1><strong>一键搜索</strong></h1></center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height: 55px; margin-top:50px;">
						<tbody><tr height="55">
							<td align="right" style="padding-right:100px">
								人员:
								<select class="input-text" style="height:45px;width:200px">
    <option value="0" selected="selected">全部</option>
    <option value="1">未办理</option>
    <option value="2">在办理</option>
    <option value="3">已完结</option>


</select>
							</td>
							<td align="left" style="padding-left:100px">
								案件:
								<select  class="input-text" style="height:45px;width:200px">
    <option value="0" selected="selected">全部</option>
    <option value="1">未办理</option>
    <option value="2">在办理</option>
    <option value="3">已完结</option>


</select>

							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="text" value="" class="input-text" style="height:45px;width:800px">
							</td>
							
						</tr>
                        <tr>
                        <td colspan="2" align="center">
								<button type="submit" class="btn btn-success" onclick="javascript:window.location.href='global-list.jsp'"  style="width:100px">
									<i class="Hui-iconfont"></i> 查询
								</button>
								
							</td>
                            </tr>
					</tbody></table></div>

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/lightbox2/2.8.1/js/lightbox-plus-jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function(){
	$.Huihover(".portfolio-area li");
});
</script>


</html>
