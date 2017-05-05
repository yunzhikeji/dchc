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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>新增角色</title>
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript" src="js/pageKit.js"></script>
<script type="text/javascript" src="js/checkUtil.js"></script>
<script type="text/javascript">

</script>
</head>

<body>

<form name="userRoleAddForm" action="userRoleAction!add" method="post" enctype="multipart/form-data"  onsubmit="return checkUserRole();" >

<div class="pd-20">
	<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">所属机构：</label></div>
     <div class="col-4" >
        <s:select list="units" cssClass="input-text radius size-M" cssStyle="width:200px;" listValue="name" listKey="id"  name="userRole.unit.id" 
             headerKey="0l"  id="userRoleUnit" value=""
           ></s:select> 
     </div>
	</div>
	<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">登陆平台账号：</label></div>
     <div class="col-10" >
      <s:textfield id="username" cssClass="input-text radius size-M" cssStyle="width:200px;" name="uname"  ></s:textfield>
     </div>
  </div>
  <div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">登陆平台密码：</label></div>
     <div class="col-10" >
     	<input  type="text" id="password" class="input-text radius size-M" style="width:200px;" name="pword"/>
     </div>
  </div>
<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">姓名：</label></div>
     <div class="col-4" >
       <s:textfield id="userRoleRealname" cssClass="input-text radius size-M" cssStyle="width:200px;" name="userRole.realname" ></s:textfield>
     </div>
</div>
<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">性别：</label></div>
     <div class="col-4" >
       <s:select list="#{1:'男',0:'女'}" cssClass="input-text" cssStyle="width:200px;"
							name="userRole.sex" listKey="key" listValue="value"></s:select>
     </div>
</div>
<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">用户权限：</label></div>
     <div class="col-10" >
     <s:select list="#{0:'普通用户',1:'管理员',2:'超级管理员'}" cssClass="input-text" cssStyle="width:200px;"
							name="userRole.userLimit" listKey="key" listValue="value"></s:select>
     </div>
  </div>
<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">警员编号：</label></div>
     <div class="col-10" >
      <s:textfield id="userRoleNumber" cssClass="input-text radius size-M" cssStyle="width:200px;" name="userRole.number"  ></s:textfield>
     </div>
  </div>
  <div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">身份证号：</label></div>
     <div class="col-10" >
      <s:textfield id="userRoleCardid" cssClass="input-text radius size-M" cssStyle="width:200px;" name="userRole.cardid"  ></s:textfield>
     </div>
  </div>
  <div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">电话：</label></div>
     <div class="col-10" >
      <s:textfield id="userRoleTelphone" cssClass="input-text radius size-M" cssStyle="width:200px;" name="userRole.telphone"  ></s:textfield>
     </div>
  </div>
  <div class="row cl mb-10" >
     <div class="col-2" >
    <label class="form-label text-r">照片：</label></div>
     <div class="col-10" style="margin-bottom: 10px;">
       <div class="col-3">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr >
    <td align="center" >
    <img  id="myimage" class="img-responsive thumbnail" width="100px" height="120px" alt="正面照片"/>
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
    <td align="center">
    	 <s:file name="file"  cssStyle="width:80%" accept="image/jpeg,image/png,image/jpg" onchange="change();" id="myfile"></s:file>
    </td>
  </tr>
</table>
  </div>

</div>
<div class="row cl">
	    <s:token></s:token>
    <div class="col-10 col-offset-2">
      <input type="submit"  class="btn btn-primary radius" value="保存并提交"> </input>
      <button onclick="childPage_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
    </div>
  </div>
  </div>
  </form>
</body>
</html>
