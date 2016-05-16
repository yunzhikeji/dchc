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
		<title>个人资料</title>
	</head>
	<body>
		<div class="cl pd-20" style="background-color: #5bacb6">
			<img src="images/user.png" alt="" class="avatar size-XL l">
			<dl style="margin-left: 80px; padding-top: 13px; color: #fff">
				<dt>
					<span class="f-18">权限：
					<s:if test="userRole.userLimit==0">普通用户</s:if>
					<s:if test="userRole.userLimit==1">普通管理员</s:if>
					<s:if test="userRole.userLimit==2">超级管理员</s:if>
					</span>
				</dt>
			</dl>
		</div>
		<div class="pd-20">
		<form name="userRoleUpdateForm" id="form-change-password" class="form form-horizontal" action="userRoleAction!updateCurrentUserRole" method="post" enctype="multipart/form-data"  onsubmit="" >
				<s:hidden name="userRole.id"/>
				<s:hidden name="userRole.photo"/>
				<s:hidden name="userRole.password"/>
				<s:hidden name="userRole.userLimit"/>
				<div class="row cl" style="padding-left: 35%">
					<div class="col-3">
						<table width="176" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="176" align="center">
									<img id="myimage" class="img-responsive thumbnail"   src="<%=basePath%>${userRole.photo }"
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
									accept="image/jpeg,image/png,image/jpg" onchange="change();"
										id="myfile"></s:file>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red"> </span>所属单位：
					</label>
					<div class="formControls col-4">
						<input class="input-text" value="德城分局" readonly="readonly"
							autocomplete="off" placeholder="德城分局">
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red"> </span>所属部门：
					</label>
					<div class="formControls col-4">
						<s:select list="units" cssClass="input-text radius size-M"
							listValue="name" listKey="id" name="userRole.unit.id"
							headerKey="0l" id="userRoleUnit"></s:select>
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red"> </span>姓名：
					</label>
					<div class="formControls col-4">
						<s:textfield id="realname" name="userRole.realname"
							cssClass="input-text radius size-M "></s:textfield>
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red"> </span>性别：
					</label>
					<div class="formControls col-4">
						<s:select list="#{1:'男',0:'女'}" cssClass="input-text"
							name="userRole.sex" listKey="key" listValue="value"></s:select>
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red"> </span>警号：
					</label>
					<div class="formControls col-4">
						<s:textfield id="number" name="userRole.number"
							cssClass="input-text radius size-M "></s:textfield>
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						联系方式：
					</label>
					<div class="formControls col-4">
						<s:textfield id="telphone" name="userRole.telphone"
							cssClass="input-text radius size-M "></s:textfield>
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red">*</span>登录用户名：
					</label>
					<div class="formControls col-4">
					<s:textfield id="username" name="userRole.username"
							cssClass="input-text radius size-M "></s:textfield>
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red">*</span>新密码：
					</label>
					<div class="formControls col-4">
						<input type="password" class="input-text" autocomplete="off"
							placeholder="不修改请留空" name="password1" id="new-password"
							datatype="*1-18" ignore="ignore">
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4">
						<span class="c-red">*</span>确认密码：
					</label>
					<div class="formControls col-4">
						<input type="password" class="input-text" autocomplete="off"
							placeholder="不修改请留空" name="password2" id="new-password2"
							recheck="password1" datatype="*1-18" errormsg="您两次输入的密码不一致！"
							ignore="ignore">
					</div>
					<div class="col-4">
					</div>
				</div>
				<div class="row cl">
					<div class="col-8 col-offset-4">
						<input class="btn btn-primary radius" type="submit"
							value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript"
			src="lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script>
$(function(){
	$("#form-change-password").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	});
	
});
</script>
	</body>
</html>