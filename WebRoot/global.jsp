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
                <meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
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
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

	</head>
	<body>
		<div id=container>
			<center style="margin-top: 100px">
				<h1>
					<strong>一键搜索</strong>
				</h1>
			</center>

			<form name="queryForm" method="post" action="queryAction!query"
				target="_self">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					style="line-height: 55px; margin-top: 50px;">
					<tbody>
						<tr height="55">
							<td align="right" style="padding-right: 100px">
								人员:
								<s:select list="#{0:'选择类型',1:'人员姓名',2:'人员编号',3:'身份证号',4:'录入人员姓名'
								,5:'DNA',6:'指纹',7:'户籍地',8:'手机号'
								,9:'微信号',10:'性别',11:'QQ号',12:'出生日期'
								,13:'银行卡号',14:'车牌号',15:'车架号',16:'手机串号'
								,17:'发动机号',18:'现住地详址',19:'失踪日期',20:'失踪地址',21:'报案联系人'
								,22:'失踪经过',23:'衣着情况',24:'暂住地址',25:'工作单位',26:'备注信息',27:'携带物品',28:'携带工具'}"
									cssClass="input-text" name="personOption" listKey="key"
									listValue="value" cssStyle="height:45px;width:200px"></s:select>
							</td>
							<td align="left" style="padding-left: 100px">
								案件:
								<s:select
									list="#{0:'选择类型',1:'案件编号',2:'案件地址',3:'案件名称',4:'录入人员姓名'
									,5:'录入单位',6:'案发时间'}"
									cssClass="input-text" name="injurycaseOption" listKey="key"
									listValue="value" cssStyle="height:45px;width:200px"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="text" name="convalue" value="" class="input-text"
									style="height: 45px; width: 800px">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<span style="color:red;">注：输入时间格式示例 2016-10-10 ;输入性别示例 男 ;</span>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button type="submit" class="btn btn-success"
									style="width: 100px">
									<i class="Hui-iconfont"></i> 查询
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>

		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript"
			src="lib/lightbox2/2.8.1/js/lightbox-plus-jquery.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript">
$(function(){
	$.Huihover(".portfolio-area li");
});
</script>
</html>
