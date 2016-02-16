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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>关系人员，同案人员</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>

	</head>

	<body>
		<form name="otherpersonUpdateForm" action="otherpersonAction!update"
			method="post" enctype="multipart/form-data" onsubmit="">
			<s:hidden name="otherperson.id"></s:hidden>
			
			<s:if test="otherperson.person!=null">
			<s:hidden name="otherperson.person.id"></s:hidden>
			</s:if>
			<s:if test="otherperson.injurycase!=null">
			<s:hidden name="otherperson.injurycase.id"></s:hidden>
			</s:if>
			
			
			
			<s:hidden name="otherperson.otype"></s:hidden>
			<s:hidden name="otherperson.frontPhoto"></s:hidden>
			<s:hidden name="otherperson.leftPhoto"></s:hidden>
			<s:hidden name="otherperson.rightPhoto"></s:hidden>
			<div class="pd-20">
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							人员编号：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="number" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="otherperson.number"></s:textfield>
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							姓名：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="name" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="otherperson.name"></s:textfield>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							身份证：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="idcard" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="otherperson.idcard"></s:textfield>
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							现住地行政区划：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="currentAddressArea"
							cssClass="input-text radius size-M" cssStyle="width:200px;"
							name="otherperson.currentAddressArea"></s:textfield>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							手机号：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="telphone" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="otherperson.telphone"></s:textfield>
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							现住地详址：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="currentAddress"
							cssClass="input-text radius size-M" cssStyle="width:200px;"
							name="otherperson.currentAddress"></s:textfield>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							微信号：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="wechat" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="otherperson.wechat"></s:textfield>
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							QQ：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="qq" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="otherperson.qq"></s:textfield>
					</div>
				</div>

				<div class="row cl mb-10">
					<s:if test="otype==1">
						<div class="col-2">
							<label class="form-label text-r">
								关系：
							</label>
						</div>
						<div class="col-4">
							<s:textfield id="relation" cssClass="input-text radius size-M"
								cssStyle="width:200px;" name="otherperson.relation"></s:textfield>
						</div>
					</s:if>
				</div>


				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							照片：
						</label>
					</div>
					<div class="col-10">
						<div class="col-3">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center">
										<img id="myimage1" src="<%=basePath%>${otherperson.frontPhoto}" class="img-responsive thumbnail"
											width="100px" height="120px" alt="正面照片" />
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
											accept="image/jpeg,image/png,image/jpg" onchange="change1();"
											id="myfile1"></s:file>
									</td>
								</tr>
							</table>

						</div>
						<div class="col-3">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center">
										<img id="myimage2" src="<%=basePath%>${otherperson.leftPhoto}" class="img-responsive thumbnail"
											width="100px" height="120px" alt="左侧照片" />
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
											accept="image/jpeg,image/png,image/jpg" onchange="change2();"
											id="myfile2"></s:file>
									</td>
								</tr>
							</table>

						</div>
						<div class="col-3">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center">
										<img id="myimage3" src="<%=basePath%>${otherperson.rightPhoto}" class="img-responsive thumbnail"
											width="100px" height="120px" alt="右侧照片" />
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
											accept="image/jpeg,image/png,image/jpg" onchange="change3();"
											id="myfile3"></s:file>
									</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="row cl">
					<s:token></s:token>
					<div class="col-10 col-offset-2">
						<input type="submit" class="btn btn-primary radius" value="保存并提交">
						</input>
						<button onclick="childPage_close();"
							class="btn btn-default radius" type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
					</div>
				</div>
		</form>
	</body>
</html>
