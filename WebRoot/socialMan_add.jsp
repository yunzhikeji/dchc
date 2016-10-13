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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</td>
									<td>
										<label class="form-label text-r">
											<span class="c-red">*</span>姓 名：
										</label>
									</td>
									<td width="21%">
										<s:textfield name="person.name" id="name"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</td>
									<td width="26%" rowspan="5" align="left">
										<table width="176" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td width="176" align="center">
													<img id="myimage" class="img-responsive thumbnail"
														width="200px" height="180px;" alt="人员照片" />
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
											<span style="color:red;">(多项用','隔开至多三项)</span>QQ：
										</label>
									</td>
									<td>
										<s:textfield name="person.qq"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</td>
									<td>
										<label class="form-label text-r">
											<span style="color:red;">(多项用','隔开至多三项)</span>微信号：
										</label>
									</td>
									<td colspan="2">
										<s:textfield name="person.wechat"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</td>
									<td>
										<label class="form-label text-r">
											<span style="color:red;">(多项用','隔开至多三项)</span>手机号码：
										</label>
									</td>
									<td colspan="2">
										<s:textfield name="person.telphone"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</td>
									<td>
										<label class="form-label text-r">
											户籍地区划：
										</label>
									</td>
									<td>
										<s:textfield name="person.registerAddressArea"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</td>
								</tr>
							</table>
						</div>
					</div>

					<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
					<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
					<script type="text/javascript"
						src="lib/My97DatePicker/WdatePicker.js"></script>
					<script type="text/javascript"
						src="lib/icheck/jquery.icheck.min.js"></script>
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
		</form>
	</body>
</html>