<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/ncss.css" rel="stylesheet" type="text/css" />
		<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<title>修改人员信息</title>
	</head>
	<body>
		<form name="personAddForm" action="personAction!add" method="post"
			enctype="multipart/form-data" onsubmit="return checkPerson();">
			<input type="hidden" name="person.type"
				value="<s:property value="type"/>" />
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
					</div>
				</div>

				<div style="width: 100%">
					<div id="tab_demo" class="HuiTab">
						<div class="tabBar cl">
							<span>信息录入</span>
						</div>
						<div class="tabCon">
							<div class="row cl text-c">
								<h1 style="line-height: 80px;">
									<small style="color: #000;"><s:property
											value="pageTileName" />信息表</small>
								</h1>
							</div>

							<!-- 失踪人员  disappearman -->
							<s:if test="type==11">
								<div class="row cl mt-15">
									<div class="col-12 mb-15 c-primary f-16"
										style="border-bottom: solid 2px #2DABF7">
										人员照片
										<span class="label label-danger radius">【图片不大于5M】</span>
									</div>
									<div class="col-3">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<img id="myimage1" class="img-responsive thumbnail"
														width="200px"
															height="auto" alt="照片1" />
													<script type="text/javascript">
															function change1() {
															    var pic1 = document.getElementById("myimage1"),
															        file1 = document.getElementById("myfile1");
															    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext1!='png'&&ext1!='jpg'&&ext1!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															          file1.value=""; 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file1.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic1.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic1.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic1.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader1(file1);
															     }
															     pic1.alt = '图片';
															}
															 function html5Reader1(file1){
															     var file1 = file1.files[0];
															     var reader1 = new FileReader();
															     reader1.readAsDataURL(file1);
															     reader1.onload = function(e){
															         var pic1 = document.getElementById("myimage1");
															         pic1.src=this.result;
															     }
															 }
												</script>


												</td>
											</tr>
											<tr>
												<td align="center">
													<s:file name="picture1"
														accept="image/jpeg,image/png,image/jpg"
														onchange="change1();" id="myfile1"></s:file>

												</td>
											</tr>
										</table>

									</div>
									<div class="col-3">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">

													<img id="myimage2" class="img-responsive thumbnail"
														width="200px"
															height="180px;" alt="照片2" />
													<script type="text/javascript">
															function change2() {
															    var pic2 = document.getElementById("myimage2"),
															        file2 = document.getElementById("myfile2");
															    var ext2=file2.value.substring(file2.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext2!='png'&&ext2!='jpg'&&ext2!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															          file2.value=""; 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file2.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic2.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic2.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic2.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader2(file2);
															     }
															     pic2.alt = '图片';
															}
															 function html5Reader2(file2){
															     var file2 = file2.files[0];
															     var reader2 = new FileReader();
															     reader2.readAsDataURL(file2);
															     reader2.onload = function(e){
															         var pic2 = document.getElementById("myimage2");
															         pic2.src=this.result;
															     }
															 }
												</script>


												</td>
											</tr>
											<tr>
												<td align="center">
													<s:file name="picture2"
														accept="image/jpeg,image/png,image/jpg"
														onchange="change2();" id="myfile2"></s:file>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-3">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<img id="myimage3" class="img-responsive thumbnail"
														width="200px"
															height="180px;" alt="照片3" />
													<script type="text/javascript">
															function change3() {
															    var pic3 = document.getElementById("myimage3"),
															        file3 = document.getElementById("myfile3");
															    var ext3=file3.value.substring(file3.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext3!='png'&&ext3!='jpg'&&ext3!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！");
 																	file3.value=""; 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file3.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic3.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic3.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic3.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader3(file3);
															     }
															     pic3.alt = '图片';
															}
															 function html5Reader3(file3){
															     var file3 = file3.files[0];
															     var reader3 = new FileReader();
															     reader3.readAsDataURL(file3);
															     reader3.onload = function(e){
															         var pic3 = document.getElementById("myimage3");
															         pic3.src=this.result;
															     }
															 }
															 </script>
												</td>
											</tr>
											<tr>
												<td align="center">
													<s:file name="picture3"
														accept="image/jpeg,image/png,image/jpg"
														onchange="change3();" id="myfile3"></s:file>
												</td>
											</tr>
										</table>
									</div>
								</div>

							</s:if>



							<div class="row cl">
								<div class="col-12 mb-10 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									人员基本信息
								</div>
								<!-- disappearman start line -->
								<s:if test="type==11">
									<div class="row cl">
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													人员编号：
												</label>
											</div>
											<div class="col-4">
												<s:textfield id="number" name="person.number"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													姓名：
												</label>
											</div>
											<div class="col-4">
												<s:textfield id="name" name="person.name"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													外文姓名：
												</label>
											</div>
											<div class="col-4">
												<s:textfield id="foreignName"
													name="disappearman.foreignName"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													别名：
												</label>
											</div>
											<div class="col-4">
												<s:textfield id="nickname" name="disappearman.nickname"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													性 别：
												</label>
											</div>
											<div class="col-4">
												<s:select list="#{1:'男',0:'女'}" cssClass="input-text"
													name="person.sex" listKey="key" listValue="value"
													cssStyle="width:200px"></s:select>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													出生日期：
												</label>
											</div>
											<div class="col-4">
												<input type="text" name="person.birthday"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
													id="logmin" class="input-text Wdate" style="width: 200px;">
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													身份证号：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="person.idcard"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													户籍地详址：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="person.registerAddress"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													其他证件名称：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.otherIdname"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													其他证件号码：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.otherIdnumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													单位联系人姓名：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.unitContactName"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													单位联系人号码：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.unitContactTelphone"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													报案联系人姓名：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.reportContactName"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													报案联系人号码：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.reportContactTelphone"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													现住地址：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.currentAddress"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													失踪地址：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.missingAddress"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													失踪日期：
												</label>
											</div>
											<div class="col-4">
												<input type="text" name="disappearman.missingStartTime"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
													id="logmin" class="input-text Wdate" style="width: 180px;" />
												-
												<input type="text" name="disappearman.missingEndTime"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
													id="logmin" class="input-text Wdate" style="width: 180px;" />
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													发现失踪日期：
												</label>
											</div>
											<div class="col-4">
												<input type="text" name="disappearman.foundMissingTime"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
													id="logmin" class="input-text Wdate" style="width: 180px;" />
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													失踪经过原因：
												</label>
											</div>
											<div class="col-10">
												<s:textarea name="disappearman.missingCause"
													cssClass="input-text radius size-M "
													cssStyle="width:80%; height: 120px;"></s:textarea>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													身高：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.height"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
												厘米（cm）
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													体型：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.shape"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													脸型：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.feature"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													足长：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.footLength"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
												毫米（mm）
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													血型：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.bloodType"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
											<div class="col-2">
												<label class="form-label text-r">
													口音：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.accent"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													特殊特征：
												</label>
											</div>
											<div class="col-10">
												<s:textarea name="disappearman.specificFeature"
													cssClass="input-text radius size-M "
													cssStyle="width: 80%; height: 120px;"></s:textarea>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													体表特征：
												</label>
											</div>
											<div class="col-10">
												<s:textarea name="disappearman.bodyFeature"
													cssClass="input-text radius size-M "
													cssStyle="width: 80%; height: 120px;"></s:textarea>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													特殊特征描述：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.specificFeatureCon"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>

											<div class="col-2">
												<label class="form-label text-r">
													衣着情况：
												</label>
											</div>
											<div class="col-4">
												<s:textfield name="disappearman.dressSituation"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</div>
										</div>
										<div class="row cl mb-10">
											<div class="col-2">
												<label class="form-label text-r">
													亲属血样信息：
												</label>
											</div>
											<div class="col-10">
												<s:textarea name="disappearman.relativeBlood"
													cssClass="input-text radius size-M "
													cssStyle="width: 80%; height: 120px;"></s:textarea>
											</div>
										</div>
								</s:if>
								<!-- disappearman over -->
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="20%">
											<label class="form-label text-r">
												<span class="c-red">*</span>人员编号：
											</label>
										</td>
										<td>
											<s:textfield id="number" name="person.number"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												<span class="c-red">*</span>姓 名：
											</label>
										</td>
										<td width="21%">
											<s:textfield name="person.name" id="name"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td width="26%" rowspan="5" align="left">
											<table width="176" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="176" align="center">
														<img id="myimage" class="img-responsive thumbnail"
															width="200px"
															height="180px;" alt="人员照片" />
														<script type="text/javascript">
															function change() {
															    var pic = document.getElementById("myimage"),
															        file = document.getElementById("myfile");
															    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！");
															          file.value="";  
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader(file);
															     }
															     pic.alt = '图片';
															}
															 function html5Reader(file){
															     var file = file.files[0];
															     var reader = new FileReader();
															     reader.readAsDataURL(file);
															     reader.onload = function(e){
															         var pic = document.getElementById("myimage");
															         pic.src=this.result;
															     }
															 }
												</script>
													</td>
												</tr>
												<tr>
													<td width="176" align="center">
														<s:file name="picture"
															accept="image/jpeg,image/png,image/jpg"
															onchange="change();" id="myfile"></s:file>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												性 别：
											</label>
										</td>
										<td width="25%">
											<s:select list="#{1:'男',0:'女'}" cssClass="input-text"
												name="person.sex" listKey="key" listValue="value"
												cssStyle="width:200px"></s:select>
										</td>
										<td width="18%">
											<label class="form-label text-r">
												出生日期：
											</label>
										</td>
										<td>
											<input type="text" name="person.birthday"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" style="width: 200px;">
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												QQ：
											</label>
										</td>
										<td>
											<s:textfield name="person.qq"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												微信号：
											</label>
										</td>
										<td colspan="2">
											<s:textfield name="person.wechat"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>

									<tr>
										<td>
											<label class="form-label text-r">
												身份证号：
											</label>
										</td>
										<td>
											<s:textfield name="person.idcard"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												手机号码：
											</label>
										</td>
										<td colspan="2">
											<s:textfield name="person.telphone"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												户籍地详址：
											</label>
										</td>
										<td>
											<s:textfield name="person.registerAddress"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												户籍地区划：
											</label>
										</td>
										<td>
											<s:textfield name="person.registerAddressArea"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<!--           person over line         -->

									<!--   gamblingCriminalMan start line   -->
									<s:if test="type<=8">
										<tr>
											<td>
												<label class="form-label text-r">
													DNA编号：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.dnanumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													其它身份信息：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.otherId"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<label class="form-label text-r">
													指纹编号：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.fingerPrintNumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													足迹编号：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.footPrintNumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
										</tr>

										<tr>
											<td>
												<label class="form-label text-r">
													现住地区划：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.currentAddressArea"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													现住地详址：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="gamblingCriminalMan.currentAddress"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<label class="form-label text-r">
													虚拟身份：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.virtualId"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													银行卡信息：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="gamblingCriminalMan.bankCard"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<label class="form-label text-r">
													绰号：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.nickname"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													车牌号：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="gamblingCriminalMan.carLicenseNumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<label class="form-label text-r">
													发动机号：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.engineNumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													车架号：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="gamblingCriminalMan.carFrameNumber"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<label class="form-label text-r">
													手机串号：
												</label>
											</td>
											<td>
												<s:textfield name="gamblingCriminalMan.imei"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>

											<s:if test="type==1">
												<td>
													<label class="form-label text-r">
														赌场角色：
													</label>
												</td>
												<td colspan="2">
													<s:textfield name="gamblingCriminalMan.casinoRole"
														cssClass="input-text radius size-M "
														cssStyle="width: 200px;"></s:textfield>
												</td>
												<s:hidden name="gamblingCriminalMan.equivocation"
													title="可疑度"></s:hidden>
											</s:if>
											<s:elseif test="type==6">
												<td>
													<label class="form-label text-r">
														可疑度：
													</label>
												</td>
												<td colspan="2">
													<s:textfield name="gamblingCriminalMan.equivocation"
														cssClass="input-text radius size-M "
														cssStyle="width: 200px;"></s:textfield>
												</td>
												<s:hidden name="gamblingCriminalMan.casinoRole" title="赌场角色"></s:hidden>
											</s:elseif>
											<s:else>
												<s:hidden name="gamblingCriminalMan.casinoRole" title="赌场角色"></s:hidden>
												<s:hidden name="gamblingCriminalMan.equivocation"
													title="可疑度"></s:hidden>
											</s:else>
										</tr>
										<s:if test="type==1">
											<tr>
												<td>
													<label class="form-label text-r">
														赌博形式：
													</label>
												</td>
												<td>
													<s:textfield name="gamblingCriminalMan.gambleType"
														cssClass="input-text radius size-M "
														cssStyle="width: 200px;"></s:textfield>
												</td>
												<td>
													<label class="form-label text-r">
														是否涉毒：
													</label>
												</td>
												<td>
													<s:select list="#{0:'否',1:'是'}" cssClass="input-text"
														name="gamblingCriminalMan.isDrugRelated" listKey="key"
														listValue="value" cssStyle="width:200px"></s:select>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</s:if>
										<s:else>
											<s:hidden name="gamblingCriminalMan.gambleType" title="赌博类型"></s:hidden>
											<s:hidden name="gamblingCriminalMan.isDrugRelated"
												title="是否涉毒"></s:hidden>
										</s:else>
										<tr>
											<td>
												<label class="form-label text-r">
													信息提取情况：
												</label>
											</td>
											<td colspan="4">
												<label for="check-box">
													<s:checkboxlist theme="simple" cssStyle="width:36px"
														name="gamblingCriminalMan.infoExtraction"
														list="{'提取手机信息','提取银行卡信息','提取DNA','提取指纹','提取鞋印'}" />
												</label>
											</td>
										</tr>
									</s:if>

									<!--   gamblingCriminalMan over line   -->

									<!--   guiltSafeguardMan start line   -->
									<s:if test="type>8&&type<=10">
										<tr>
											<td height="53">
												<label class="form-label text-r">
													工作单位：
												</label>
											</td>
											<td>
												<s:textfield name="guiltSafeguardMan.workdUnit"
													cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<td>
												<label class="form-label text-r">
													暂住地址：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="guiltSafeguardMan.temporaryAddress"
													cssClass="input-text radius size-M "
													cssStyle="width: 350px;"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<label class="form-label text-r">
													落脚地：
												</label>
											</td>
											<td colspan="4">

												<s:textfield name="guiltSafeguardMan.location"
													cssClass="input-text radius size-M "
													cssStyle="width: 780px;"></s:textfield>
											</td>
										</tr>
									</s:if>
									<!--   guiltSafeguardMan over line   -->

									<!--  disappearman 失踪人员没有布控信息 -->
									<s:if test="type!=11">
										<tr>
											<td>
												<label class="form-label text-r">
													是否布控：
												</label>
											</td>
											<td colspan="4">
												<s:select list="#{0:'否',1:'是'}" cssClass="input-text"
													name="person.isMakeControl" listKey="key" listValue="value"
													cssStyle="width:200px"></s:select>
											</td>
										</tr>
									</s:if>
								</table>
							</div>
							<!-- 1-8 11携带物品工具 -->
							<s:if test="type<=8||type==11">
								<div class="row cl mt-20">
									<div class="col-12 mb-10 c-primary f-16"
										style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
										携带物品
									</div>
									<div class="row cl mb-10">
										<div class="col-1">
											<label class="form-label text-l">
												携带物品：
											</label>
										</div>
										<div class="col-11">
											<s:textfield name="person.carrier"
												cssClass="input-text radius size-M " cssStyle="width: 80%;"></s:textfield>
											多个物品请输入&quot;,&quot;隔开
										</div>
									</div>
									<div class="row cl mb-10">
										<div class="col-1">
											<label class="form-label text-l">
												携带工具：
											</label>
										</div>
										<div class="col-11">
											<s:textfield name="person.carryTool"
												cssClass="input-text radius size-M " cssStyle="width: 80%;"></s:textfield>
											多个物品请输入&quot;,&quot;隔开
										</div>
									</div>
								</div>
							</s:if>

							<!-- disapperman 撤销情况 -->
							<s:if test="type==11">
								<div class="row cl mt-20">
									<div class="col-12 mb-10 c-primary f-16"
										style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
										撤销情况
									</div>
									<div class="row cl mb-10">
										<div class="col-2">
											<label class="form-label text-r">
												撤销单位：
											</label>
										</div>
										<div class="col-4">
											<s:textfield name="disappearman.revocateUnit"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</div>
										<div class="col-2">
											<label class="form-label text-r">
												承办人：
											</label>
										</div>
										<div class="col-4">
											<s:textfield name="disappearman.revocateName"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</div>
									</div>
									<div class="row cl mb-10">
										<div class="col-2">
											<label class="form-label text-r">
												撤销日期：
											</label>
										</div>
										<div class="col-4">
											<input type="text" name="disappearman.revocateTime"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" style="width: 200px;">
										</div>
									</div>
									<div class="row cl mb-10">
										<div class="col-2">
											<label class="form-label text-r">
												撤销原因：
											</label>
										</div>
										<div class="col-10">
											<s:textarea name="disappearman.revocateReason"
												cssClass="input-text radius size-M "
												cssStyle="width: 95%; height: 50px;"></s:textarea>
										</div>
									</div>
								</div>
							</s:if>
						</div>
						<!-- 留置盘问 盘问原因 -->
						<s:if test="type==6">
							<div class="row cl mt-20">
								<div class="col-12 mb-10 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									盘问原因
								</div>
								<div class="row cl">
									<div class="col-1">
										<label class="form-label text-r">
											盘问原因：
										</label>
									</div>
									<div class="col-11">
										<s:textfield cssClass="input-text radius size-M "
											cssStyle="width:80%; height:200px;"
											name="gamblingCriminalMan.interrogateReason"></s:textfield>
									</div>
								</div>
							</div>
						</s:if>
						<s:else>
							<s:hidden name="gamblingCriminalMan.interrogateReason"
								title="盘问原因"></s:hidden>
						</s:else>


						<!-- guiltSafeguardMan 负罪在逃、维稳人员 的前科照片、关系人、同案人 -->
						<s:if test="type>8&&type<=10">
							<div class="row cl mt-15">
								<div class="col-12 mb-15 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									前科照片
									<span class="label label-danger radius">【图片不大于5M】</span>
								</div>
								<div class="col-3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">

												<img id="myimage1" class="img-responsive thumbnail"
													width="200px"
															height="180px;" alt="添加前科照片1" />
												<script type="text/javascript">
															function change1() {
															    var pic1 = document.getElementById("myimage1"),
															        file1 = document.getElementById("myfile1");
															    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext1!='png'&&ext1!='jpg'&&ext1!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															          file1.value=""; 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file1.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic1.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic1.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic1.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader1(file1);
															     }
															     pic1.alt = '图片';
															}
															 function html5Reader1(file1){
															     var file1 = file1.files[0];
															     var reader1 = new FileReader();
															     reader1.readAsDataURL(file1);
															     reader1.onload = function(e){
															         var pic1 = document.getElementById("myimage1");
															         pic1.src=this.result;
															     }
															 }
												</script>



											</td>
										</tr>
										<tr>
											<td align="center">
												<s:file name="picture1"
													accept="image/jpeg,image/png,image/jpg"
													onchange="change1();" id="myfile1"></s:file>
											</td>
										</tr>
									</table>

								</div>
								<div class="col-3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<img id="myimage2" class="img-responsive thumbnail"
													width="200px"
															height="180px;" alt="添加前科照片2" />
												<script type="text/javascript">
															function change2() {
															    var pic2 = document.getElementById("myimage2"),
															        file2 = document.getElementById("myfile2");
															    var ext2=file2.value.substring(file2.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext2!='png'&&ext2!='jpg'&&ext2!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															          file2.value=""; 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file2.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic2.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic2.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic2.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader2(file2);
															     }
															     pic2.alt = '图片';
															}
															 function html5Reader2(file2){
															     var file2 = file2.files[0];
															     var reader2 = new FileReader();
															     reader2.readAsDataURL(file2);
															     reader2.onload = function(e){
															         var pic2 = document.getElementById("myimage2");
															         pic2.src=this.result;
															     }
															 }
												</script>
											</td>
										</tr>
										<tr>
											<td align="center">
												<s:file name="picture2"
													accept="image/jpeg,image/png,image/jpg"
													onchange="change2();" id="myfile2"></s:file>
											</td>
										</tr>
									</table>
								</div>
								<div class="col-3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<img id="myimage3" class="img-responsive thumbnail"
													width="200px"
															height="180px;" alt="添加前科照片3" />
												<script type="text/javascript">
															function change3() {
															    var pic3 = document.getElementById("myimage3"),
															        file3 = document.getElementById("myfile3");
															    var ext3=file3.value.substring(file3.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext3!='png'&&ext3!='jpg'&&ext3!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															          file3.value=""; 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file3.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic3.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic3.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic3.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader3(file3);
															     }
															     pic3.alt = '图片';
															}
															 function html5Reader3(file3){
															     var file3 = file3.files[0];
															     var reader3 = new FileReader();
															     reader3.readAsDataURL(file3);
															     reader3.onload = function(e){
															         var pic3 = document.getElementById("myimage3");
															         pic3.src=this.result;
															     }
															 }
															 </script>
											</td>
										</tr>
										<tr>
											<td align="center">
												<s:file name="picture3"
													accept="image/jpeg,image/png,image/jpg"
													onchange="change3();" id="myfile3"></s:file>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</s:if>

						<!--  
							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									案件基本信息
									<input class="btn btn-primary radius mt-10 f-r" type="button"
										onclick="addsaqk('新增涉案情况','addsaqk.html','500','300')"
										value="新增涉案情况">
								</div>
								<div class="col-12">
									<table class="table table-border table-bg mb-10">
										<thead>
											<tr>
												<th>
													序号
												</th>
												<th>
													案件编号
												</th>
												<th>
													案件基本情况
												</th>
												<th>
													填报人
												</th>
												<th>
													填报机构
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>


							
							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									发起疑难
									<input class="btn btn-primary radius mt-10 f-r" type="button"
										onclick="addsaqk('发起疑难','difficult-start.html','500','300')"
										value="发起疑难">
								</div>
								<div class="col-12">
									<table class="table table-border table-bg mb-10">
										<thead>
											<tr>
												<th>
													序号
												</th>
												<th>
													案件编号
												</th>
												<th>
													案件基本情况
												</th>
												<th>
													填报人
												</th>
												<th>
													填报机构
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>


							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									发起研判信息
									<input class="btn btn-primary radius mt-10 f-r" type="button"
										onclick="addgxr('发起研判','addyanpan.html','500','300')"
										value="发起研判">
								</div>
								<div class="col-12">
									<table class="table table-border table-bg mb-10">
										<thead>
											<tr>
												<th>
													研判序号
												</th>
												<th width="14%">
													报送机构
												</th>
												<th width="67%">
													研判要求
												</th>
												<th width="12%">
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									研判情况
								</div>
								<div class="col-12">
									<table class="table table-border table-bg mb-10">
										<thead>
											<tr>
												<th width="7%" bgcolor="#F5FAFE">
													研判序号
												</th>
												<th align="center">
													1
												</th>
												<th align="center">
													2
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													刑技研判
												</td>
												<td>
													<textarea name="input" class="input-text" id="input"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
												<td>
													<textarea name="input2" class="input-text" id="input2"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
											</tr>
											<tr>
												<td>
													网技研判
												</td>
												<td>
													<textarea name="input3" class="input-text" id="input3"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
												<td>
													<textarea name="input4" class="input-text" id="input4"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
											</tr>
											<tr>
												<td>
													情报研判
												</td>
												<td>
													<textarea name="input5" class="input-text" id="input5"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
												<td>
													<textarea name="input6" class="input-text" id="input6"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
											</tr>
											<tr>
												<td>
													图像侦查
												</td>
												<td>
													<textarea name="input7" class="input-text" id="input7"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
												<td>
													<textarea name="input8" class="input-text" id="input8"
														style="width: 400px; height: 80px; float: left;"
														placeholder=""></textarea>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row cl">
								<div class="col-12 mb-10 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									完结情况
								</div>
								
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="86%" style="padding-left:9%;">
											<s:radio theme="simple" cssStyle="width:36px" cssClass="check-box" list='#{ 1:"抓获", 2:"死亡", 3:"撤销案件", 4:"释放", 5:"治安拘留", 6:"刑事拘留", 7:"留置盘问", 8:"其他" }' name="person.endSituation" />
											
											
										</td>
									</tr>
								</table>

							</div>
							<div class="col-12 mb-10 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7">
								综合情况
							</div>

							<div class="row cl">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="10%" valign="top">
											<label class="form-label text-r">
												综合情况：
											</label>
										</td>
										<td>
												<s:textfield name="person.comprehensiveJudge" cssClass="input-text" id="input9"
												cssStyle="width: 800px; height: 80px; float: left;"
												placeholder="根据以上研判信息填写综合情况"></s:textfield>
										</td>
									</tr>
								</table>
								<div class="col-12 mb-10 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									领导批示
								</div>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="10%" valign="top">
											<label class="form-label text-r">
												领导批示：
											</label>
										</td>
										<td>
										<s:textfield name="person.leaderInstruction" cssClass="input-text" id="input9"
												cssStyle="width: 800px; height: 80px; float: left;"
												placeholder="领导批示填写"></s:textfield>
										</td>
									</tr>
								</table>
							</div>
						</div>
				</div>
				-->
						<script type="text/javascript"
							src="lib/jquery/1.9.1/jquery.min.js"></script>
						<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
						<script type="text/javascript"
							src="lib/My97DatePicker/WdatePicker.js"></script>
						<script type="text/javascript"
							src="lib/icheck/jquery.icheck.min.js"></script>
						<script type="text/javascript"
							src="lib/Validform/5.3.2/Validform.min.js"></script>
						<script type="text/javascript"
							src="lib/webuploader/0.1.5/webuploader.min.js"></script>
						<script type="text/javascript"
							src="lib/ueditor/1.4.3/ueditor.config.js"></script>
						<script type="text/javascript"
							src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
						<script type="text/javascript"
							src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
						<script type="text/javascript" src="js/H-ui.js"></script>
						<script type="text/javascript" src="js/H-ui.admin.js"></script>
						<script type="text/javascript">

$(function(){
$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0");
});

/*新增关系人*/
function addgxr(title,url,w,h){
	layer.open({
    type: 2,
    area: ['800px', '500px'],
    fix: false, //不固定
	title:title,
    maxmin: true,
    content: url
});
}
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '10%'
	});
});

/*机构流转*/
function Department_change(title,url,w,h){
	var index = layer.open({
		type: 2,
		area: ['800px', '500px'],
		title: title,
		content: url
	});
	
}
/*案例-发布*/
function article_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}
/*案例-保存*/
function article_save(obj,id){
	layer.confirm('确认要保存吗？',function(index){
		layer.msg('已保存!',{icon: 6,time:1000});
	});
}
</script>
		</form>
	</body>
</html>