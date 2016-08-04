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
		<form name="personAddForm" action="personAction!update" method="post"
			enctype="multipart/form-data" onsubmit="return checkPerson();">
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
						<s:if
							test="person.getEndSituation()!=null&&person.getEndSituation()!='0'">
							<div class=" f-r pr-5">
								<button type="button" class="btn btn-success radius" id="button"
									name=""
									onclick="addPage('发布案例','successexampleAction!goToAdd?pid=<s:property value="id"/>','500','300')">
									<i class="Hui-iconfont">&#xe6bf;</i> 发布案例
								</button>
							</div>
						</s:if>
					</div>
				</div>

				<div style="width: 100%">
					<div id="tab_demo" class="HuiTab">
						<div class="tabBar cl">
							<span>信息录入</span><span>流转信息</span>
						</div>
						<div class="tabCon">
							<div class="row cl text-c">
								<h1 style="line-height: 80px;">
									<small style="color: #000;"><s:property
											value="pageTileName" />信息表</small>
								</h1>
							</div>

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
														src="<%=basePath%>${disappearman.photo1}" width="200px"
														height="180px;" alt="照片1" />

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
														src="<%=basePath%>${disappearman.photo2}" width="200px"
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
														src="<%=basePath%>${disappearman.photo3}" width="200px"
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




							<s:hidden name="disappearman.id" title="人员子表自身id"></s:hidden>
							<div class="row cl">
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											人员编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="number" name="person.number"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											姓名：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="name" name="person.name"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											外文姓名：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="foreignName" name="disappearman.foreignName"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											别名：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="nickname" name="disappearman.nickname"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
										<input type="text" name="person.birthday" value="<s:property value="person.birthday"/>"
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											户籍地详址：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="person.registerAddress"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											其他证件号码：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.otherIdnumber"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											单位联系人号码：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.unitContactTelphone"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											报案联系人号码：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.reportContactTelphone"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											失踪地址：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.missingAddress"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											失踪日期：
										</label>
									</div>
									<div class="col-4">
										<input type="text" name="disappearman.missingStartTime" value="<s:property value="disappearman.missingStartTime"/>"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
											id="logmin" class="input-text Wdate" style="width: 180px;" />
										-
										<input type="text" name="disappearman.missingEndTime" value="<s:property value="disappearman.missingEndTime"/>"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
											id="logmin" class="input-text Wdate" style="width: 180px;" />
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											发现失踪日期：
										</label>
									</div>
									<div class="col-4">
												<input type="text" name="disappearman.foundMissingTime" value="<s:property value="disappearman.foundMissingTime"/>"
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
										厘米（cm）
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											体型：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.shape"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											足长：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.footLength"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											口音：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.accent"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											衣着情况：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.dressSituation"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
							</div>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											承办人：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="disappearman.revocateName"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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


							<!--发起查证信息-->
							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									发起查证信息
									<input class="btn btn-primary radius mt-10 f-r" type="button"
										onclick="addPage('发起查证信息','judgeAction!goToAdd?pid=<s:property value="id"/>&jtype=2','650','300')"
										value="发起查证信息">
								</div>
								<div class="col-12">
									<table class="table table-border table-bg mb-10"
										style="table-layout: fixed;">
										<thead>
											<tr>
												<th>
													查证次序
												</th>
												<th width="24%">
													报送机构
												</th>
												<th width="57%">
													查证要求
												</th>
												<th width="12%">
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="person.judges.size>0">
												<s:iterator value="person.judges" var="judge"
													status="status">
													<tr>
														<td>
															<s:property value="indexNumber" />
														</td>
														<td>
															<s:property value="reportUnit" />
														</td>
														<td
															style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
															<a href="#" onclick="javascript:void(0)"> <s:property
																	value="judgeRequirement" /> </a>
														</td>
														<td>
															<a style="text-decoration: none" class="ml-5"
																onclick="addPage('编辑查证信息','judgeAction!load?jid=<s:property value="id" />','500','300')"
																href="javascript:;" title="编辑"><i
																class="Hui-iconfont">&#xe6df;</i> </a>
															<a style="text-decoration: none" class="ml-5"
																href="javascript:;"
																onclick="deleteJudge(<s:property value="id" />);"
																title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
														</td>
													</tr>
												</s:iterator>
											</s:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									查证情况
								</div>
								<div class="col-12">
									<table class="table table-border table-bg mb-10">
										<thead>
											<tr>
												<th>
													查证次序
												</th>
												<th align="center">
													刑技查证
												</th>
												<th align="center">
													网技查证
												</th>
												<th align="center">
													情报查证
												</th>
												<th align="center">
													图像查证
												</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="person.judges.size>0">
												<s:iterator value="person.judges" var="judge"
													status="status">
													<tr>
														<td>
															<s:property value="indexNumber" />
														</td>
														<td>
															<s:textarea name="criminalJudge" cssClass="input-text"
																id="criminalJudge"
																cssStyle="width: 260px; height: 180px; float: left;"
																readonly="true"></s:textarea>
														</td>
														<td>
															<s:textarea name="networkJudge" cssClass="input-text"
																id="networkJudge"
																cssStyle="width: 260px; height: 180px; float: left;"
																readonly="true"></s:textarea>
														</td>
														<td>
															<s:textarea name="intelligenceJudge"
																cssClass="input-text" id="intelligenceJudge"
																cssStyle="width: 260px; height: 180px; float: left;"
																readonly="true"></s:textarea>
														</td>
														<td>
															<s:textarea name="imageJudge" cssClass="input-text"
																id="imageJudge"
																cssStyle="width: 260px; height: 180px; float: left;"
																readonly="true"></s:textarea>
														</td>
													</tr>
												</s:iterator>
											</s:if>
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
										<td width="86%" style="padding-left: 9%;">
											<s:radio theme="simple" cssStyle="width:36px"
												cssClass="check-box"
												list='#{ 1:"抓获", 2:"死亡", 3:"撤销案件", 4:"释放", 5:"治安拘留", 6:"刑事拘留", 7:"留置盘问", 8:"其他" }'
												name="person.endSituation" />
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
											<s:textarea name="person.comprehensiveJudge"
												cssClass="input-text" id="input9"
												cssStyle="width: 100%; height: 80px; float: left;"
												placeholder="根据以上研判信息填写综合情况"></s:textarea>
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
											<s:textarea name="person.leaderInstruction"
												cssClass="input-text" id="input9"
												cssStyle="width: 100%; height: 80px; float: left;"
												placeholder="领导批示填写"></s:textarea>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="tabCon">
							<div class="row cl">
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div
										style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
										<s:property value="person.userRole.realname" />
										：录入
									</div>
								</div>
							</div>
							<s:if test="person.judges.size>0">
								<s:iterator value="person.judges" var="judge" status="status">
									<div class="row cl">
										<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
											<div style="height: 25px; width: 200px; text-align: center">
												<i class="Hui-iconfont f-18 ">&#xe674;</i>
											</div>
										</div>
									</div>
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div
											style="height: 100px; width: 200px; border: solid 1px #666">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														部门查证
														<s:property value="indexNumber" />
														:
														<s:property value="reportUnit" />
													</td>
												</tr>
											</table>
										</div>
									</div>
								</s:iterator>
							</s:if>
							<s:if
								test="person.comprehensiveJudge!=null&&person.comprehensiveJudge!=''">
								<div class="row cl">
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div style="height: 25px; width: 200px; text-align: center">
											<i class="Hui-iconfont f-18 ">&#xe674;</i>
										</div>
									</div>
								</div>
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div
										style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
										完结
									</div>
								</div>
							</s:if>
							<s:if
								test="person.leaderInstruction!=null&&person.leaderInstruction!=''">
								<div class="row cl">
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div style="height: 25px; width: 200px; text-align: center">
											<i class="Hui-iconfont f-18 ">&#xe674;</i>
										</div>
									</div>
								</div>
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div
										style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
										领导批示
									</div>
								</div>
							</s:if>
						</div>
					</div>
				</div>
				<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
				<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
				<script type="text/javascript"
					src="lib/My97DatePicker/WdatePicker.js"></script>
				<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
				<script type="text/javascript"
					src="lib/Validform/5.3.2/Validform.min.js"></script>
				<script type="text/javascript"
					src="lib/webuploader/0.1.5/webuploader.min.js"></script>
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
				<s:hidden name="person.type" title="人员类型"></s:hidden>
				<s:hidden name="person.id" title="人员id"></s:hidden>
				<s:hidden name="person.isOutOfTime" title="是否超期办理"></s:hidden>
				<s:hidden name="person.isNew" value="0"></s:hidden>
				<s:if test="person.userRole!=null">
					<s:hidden name="person.userRole.id" title="人员子表userRoleid"></s:hidden>
				</s:if>
				
				<s:hidden name="disappearman.photo1" ></s:hidden>
				<s:hidden name="disappearman.photo2" ></s:hidden>
				<s:hidden name="disappearman.photo3" ></s:hidden>
				
				<s:hidden name="person.photoImg" title="人员照片"></s:hidden>
				<s:hidden name="person.handleState" title="办理状态"></s:hidden>
				<s:hidden name="person.joinDate" title="录入时间"></s:hidden>
		</form>
	</body>
</html>