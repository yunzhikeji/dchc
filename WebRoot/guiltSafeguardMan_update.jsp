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
			enctype="multipart/form-data" onsubmit="">
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
						<s:if test="person.getEndSituation()!=null&&person.getEndSituation()!='0'">
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


							<div class="row cl">
								<div class="col-12 mb-10 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									人员基本信息
								</div>
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
											<!--  
											<button type="submit"
												class="btn btn-warning radius size-MINI ml-5" id="" name=""
												title="导入">
												<i class="Hui-iconfont">&#xe645;</i>
											</button>
											-->
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
														<img src="<%=basePath%>${person.photoImg}" id="myimage"
															class="img-responsive thumbnail" width="176px"
															height="220px" alt="人员照片" />
														<script type="text/javascript">
															function change() {
															    var pic = document.getElementById("myimage"),
															        file = document.getElementById("myfile");
															    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
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
															cssClass="btn btn-primary radius mt-10"
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
												value="<s:property value="person.birthday"/>"
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
									</tr>
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
								</table>
							</div>
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
											<img
												src="<%=basePath%>${guiltSafeguardMan.criminalRecordPhoto1}"
												id="myimage1" class="img-responsive thumbnail" width="176px"
												height="220px" alt="前科照片1" />
											<script type="text/javascript">
															function change1() {
															    var pic1 = document.getElementById("myimage1"),
															        file1 = document.getElementById("myfile1");
															    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext1!='png'&&ext1!='jpg'&&ext1!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
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
												cssClass="btn btn-primary radius mt-10"
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
												src="<%=basePath%>${guiltSafeguardMan.criminalRecordPhoto2}"
												width="176px" height="220px" alt="前科照片2" />
											<script type="text/javascript">
															function change2() {
															    var pic2 = document.getElementById("myimage2"),
															        file2 = document.getElementById("myfile2");
															    var ext2=file2.value.substring(file2.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext2!='png'&&ext2!='jpg'&&ext2!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
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
												cssClass="btn btn-primary radius mt-10"
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
											<img
												src="<%=basePath%>${guiltSafeguardMan.criminalRecordPhoto3}"
												id="myimage3" class="img-responsive thumbnail" width="176px"
												height="220px" alt="前科照片3" />
											<script type="text/javascript">
															function change3() {
															    var pic3 = document.getElementById("myimage3"),
															        file3 = document.getElementById("myfile3");
															    var ext3=file3.value.substring(file3.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext3!='png'&&ext3!='jpg'&&ext3!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
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
												cssClass="btn btn-primary radius mt-10"
												accept="image/jpeg,image/png,image/jpg"
												onchange="change3();" id="myfile3"></s:file>
										</td>
									</tr>
								</table>
							</div>
						</div>

						<!--关系人信息-->
						<div class="row cl mt-20">
							<div class="col-12 mb-0 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
								关系人信息
								<input class="btn btn-primary radius mt-10 f-r" type="button"
									onclick="addPage('新增关系人','otherpersonAction!goToAdd?pid=<s:property value="id"/>&otype=1','500','300')"
									value="新增关系人">
							</div>
							<div class="col-12">
								<table class="table table-border table-bg">
									<thead>
										<tr>
											<th>
												序号
											</th>
											<th>
												人员编号
											</th>
											<th>
												姓名
											</th>
											<th>
												身份证
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
									<tbody>
										<s:if test="gxrs.size>0">
											<s:iterator value="gxrs" var="otherperson" status="status">
												<tr>
													<td>
														<s:property value="#status.index+1" />
													</td>
													<td>
														<a href="#" onclick="javascript:void(0)"> <s:property
																value="number" /> </a>
													</td>
													<td>
														<s:property value="name" />
													</td>
													<td>
														<s:property value="idcard" />
													</td>
													<td>
														<a style="text-decoration: none" class="ml-5"
															onclick="addPage('编辑同案人','otherpersonAction!load?otherid=<s:property value="id" />','500','300')"
															href="javascript:;" title="编辑"><i
															class="Hui-iconfont">&#xe6df;</i> </a>
														<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteOtherperson(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
													</td>
												</tr>
											</s:iterator>
										</s:if>
									</tbody>
								</table>
							</div>
						</div>


						<!--同案人员-->
						<div class="row cl mt-20">
							<div class="col-12 mb-0 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
								同案人员信息
								<input class="btn btn-primary radius mt-10 f-r" type="button"
									onclick="addPage('新增同案人员','otherpersonAction!goToAdd?pid=<s:property value="id"/>&otype=2','500','300')"
									value="新增同案人员">
							</div>
							<div class="col-12">
								<table class="table table-border table-bg">
									<thead>
										<tr>
											<th>
												序号
											</th>
											<th>
												人员编号
											</th>
											<th>
												姓名
											</th>
											<th>
												身份证
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
									<tbody>
										<s:if test="tars.size>0">
											<s:iterator value="tars" var="otherperson" status="status">
												<tr>
													<td>
														<s:property value="#status.index+1" />
													</td>
													<td>
														<a href="#" onclick="javascript:void(0)"> <s:property
																value="number" /> </a>
													</td>
													<td>
														<s:property value="name" />
													</td>
													<td>
														<s:property value="idcard" />
													</td>
													<td>
														<a style="text-decoration: none" class="ml-5"
															onclick="addPage('编辑同案人','otherpersonAction!load?otherid=<s:property value="id" />','500','300')"
															href="javascript:;" title="编辑"><i
															class="Hui-iconfont">&#xe6df;</i> </a>
														<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteOtherperson(<s:property value="id" />);"
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
								案件基本信息
								<input class="btn btn-primary radius mt-10 f-r" type="button"
									onclick="addPage('新增涉案情况','lawcaseAction!goToAdd?pid=<s:property value="id"/>','500','300')"
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
												案件名称
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
										<s:if test="person.lawcases.size>0">
											<s:iterator value="person.lawcases" var="lawcase"
												status="status">
												<tr>
													<td>
														<s:property value="#status.index+1" />
													</td>
													<td>
														<a href="#" onclick="javascript:void(0)"> <s:property
																value="caseNumber" /> </a>
													</td>
													<td>
														<s:property value="caseName" />
													</td>
													<td>
														<s:property value="fillName" />
													</td>
													<td>
														<s:property value="fillUnit" />
													</td>
													<td>
														<a style="text-decoration: none" class="ml-5"
															onclick="addPage('编辑涉案情况','lawcaseAction!load?lawid=<s:property value="id" />','500','300')"
															href="javascript:;" title="编辑"><i
															class="Hui-iconfont">&#xe6df;</i> </a>
														<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteLawcase(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
													</td>
												</tr>
											</s:iterator>
										</s:if>
									</tbody>
								</table>
							</div>

						</div>


						<!--发起疑难-->
						<div class="row cl mt-20">
							<div class="col-12 mb-0 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
								发起疑难
								<input class="btn btn-primary radius mt-10 f-r" type="button"
									onclick="addPage('发起疑难','troubleshootingAction!goToAdd?pid=<s:property value="id"/>','500','300')"
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
												标题
											</th>
											<th>
												疑难问题
											</th>
											<th>
												发布人
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
									<tbody>

										<s:if test="person.troubleshootings.size>0">
											<s:iterator value="person.troubleshootings"
												var="troubleshooting" status="status">
												<tr>
													<td>
														<s:property value="#status.index+1" />
													</td>
													<td>
														<a href="#" onclick="javascript:void(0)"> <s:property
																value="title" /> </a>
													</td>
													<td>
														<s:property value="question" />
													</td>
													<td>
														<s:property value="issuer" />
													</td>
													<td>
														<a style="text-decoration: none" class="ml-5"
															onclick="addPage('编辑疑难解答','troubleshootingAction!load?troubid=<s:property value="id" />','500','300')"
															href="javascript:;" title="编辑"><i
															class="Hui-iconfont">&#xe6df;</i> </a>
														<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteTroubleshooting(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
													</td>
												</tr>
											</s:iterator>
										</s:if>

									</tbody>
								</table>
							</div>

						</div>

						<!--发起研判信息-->
						<div class="row cl mt-20">
							<div class="col-12 mb-0 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
								发起研判信息
								<input class="btn btn-primary radius mt-10 f-r" type="button"
									onclick="addPage('发起研判信息','judgeAction!goToAdd?pid=<s:property value="id"/>&jtype=1','650','300')"
									value="发起研判信息">
							</div>
							<div class="col-12">
								<table class="table table-border table-bg mb-10"
									style="table-layout: fixed;">
									<thead>
										<tr>
											<th width="7%">
												研判次序
											</th>
											<th width="24%">
												报送机构
											</th>
											<th width="57%">
												研判要求
											</th>
											<th width="12%">
												操作
											</th>
										</tr>
									</thead>
									<tbody>
										<s:if test="person.judges.size>0">
											<s:iterator value="person.judges" var="judge" status="status">
												<tr>
													<td>
														<s:property value="indexNumber" />
													</td>
													<td>
														<s:property value="reportUnit" />
													</td>
													<td
														style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
														<a href="#" onclick="javascript::"> <s:property
																value="judgeRequirement" /> </a>
													</td>
													<td>
														<a style="text-decoration: none" class="ml-5"
															onclick="addPage('编辑研判信息','judgeAction!load?jid=<s:property value="id" />','500','300')"
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
								研判情况
							</div>
							<div class="col-12">
								<table class="table table-border table-bg mb-10">
									<thead>
										<tr>
											<th>
												研判次序
											</th>
											<th align="center">
												刑技研判
											</th>
											<th align="center">
												网技研判
											</th>
											<th align="center">
												情报研判
											</th>
											<th align="center">
												图像侦查
											</th>
										</tr>
									</thead>
									<tbody>
										<s:if test="person.judges.size>0">
											<s:iterator value="person.judges" var="judge" status="status">
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
														<s:textarea name="intelligenceJudge" cssClass="input-text"
															id="intelligenceJudge"
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
										<s:textfield name="person.comprehensiveJudge"
											cssClass="input-text" id="input9"
											cssStyle="width: 100%; height: 80px; float: left;"
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
										<s:textfield name="person.leaderInstruction"
											cssClass="input-text" id="input9"
											cssStyle="width: 100%; height: 80px; float: left;"
											placeholder="领导批示填写"></s:textfield>
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
										<s:property value="person.userRole.realname"/>：录入
									</div>
								</div>
							</div>
							<s:if test="person.judges.size>0">
												<s:iterator value="person.judges" var="judge"
													status="status">
							<div class="row cl">
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div style="height: 25px; width: 200px; text-align: center">
										<i class="Hui-iconfont f-18 ">&#xe674;</i>
									</div>
								</div>
							</div>
							<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
								<div style="height: 100px; width: 200px; border: solid 1px #666">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												研判次序<s:property value="indexNumber" />: <s:property value="reportUnit" />
											</td>
										</tr>
									</table>
								</div>
							</div>
							</s:iterator>
							</s:if>
							<s:if test="person.comprehensiveJudge!=null&&person.comprehensiveJudge!=''">
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
							<s:if test="person.leaderInstruction!=null&&person.leaderInstruction!=''">
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
			<s:hidden name="guiltSafeguardMan.id" title="人员子表自身id"></s:hidden>
			<s:hidden name="guiltSafeguardMan.criminalRecordPhoto1" title="前科照片1"></s:hidden>
			<s:hidden name="guiltSafeguardMan.criminalRecordPhoto2" title="前科照片2"></s:hidden>
			<s:hidden name="guiltSafeguardMan.criminalRecordPhoto3" title="前科照片3"></s:hidden>
			<s:hidden name="person.type" title="人员类型"></s:hidden>
			<s:hidden name="person.id" title="人员id"></s:hidden>
			<s:hidden name="person.userRole.id" title="人员子表userRoleid"></s:hidden>
			<s:hidden name="person.photoImg" title="人员照片"></s:hidden>
			<s:hidden name="person.handleState" title="办理状态"></s:hidden>
			<s:hidden name="person.joinDate" title="录入时间"></s:hidden>
		</form>
	</body>
</html>