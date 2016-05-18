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
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
		<script type="text/javascript"
			src="lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript"
			src="lib/webuploader/0.1.5/webuploader.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<title>新增案件</title>
		
	</head>
	<body>
		<form name="injurycaseAddForm" action="injurycaseAction!add"
			method="post" enctype="multipart/form-data" onsubmit="return checkCase();">
			<input type="hidden" name="injurycase.itype"
				value="<s:property value="itype"/>" />
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
							<div class="col-12 mb-10  c-primary f-16"
								style="border-bottom: solid 2px #2DABF7">
								案件基本信息
							</div>
							<div class="row cl">
								<div class="row cl mb-10">

									<div class="col-2">
										<label class="form-label text-r">
											案件照片：
										</label>
									</div>
									<div class="col-4">

										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<img id="myimage1" class="img-responsive thumbnail"
														width="200px"
															height="180px;" alt="案件图片" />
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
									<div class="col-2">
										<label class="form-label text-r">
											串并案系列名称：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="series" name="injurycase.series"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
									<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											是否串并案：
										</label>
									</div>
									<div class="col-4">
										<s:select list="#{0:'否',1:'是'}" cssClass="input-text"
											name="injurycase.isRelated" listKey="key" listValue="value"
											cssStyle="width:200px"></s:select>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案件分类：
										</label>
									</div>
									<div class="col-4">
										<s:if test="itype==1">一般案件</s:if>
										<s:if test="itype==2">重伤案件</s:if>
										<s:if test="itype==3">团伙系列案件</s:if>
									</div>
								</div>
								<div class="row cl">
									<div class="row cl mb-10">
										<div class="col-2">
											<label class="form-label text-r">
												案件编号：
											</label>
										</div>
										<div class="col-4">
											<s:textfield id="caseNumber" name="injurycase.caseNumber"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</div>
										<div class="col-2">
											<label class="form-label text-r">
												案件名称：
											</label>
										</div>
										<div class="col-4">
											<s:textfield id="caseName" name="injurycase.caseName"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</div>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											案发地址：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="injurycase.casePlace"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案发时间：
										</label>
									</div>
									<div class="col-4">
										<input type="text" name="injurycase.startTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
											id="logmin" class="input-text Wdate" style="width: 200px;">
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											鉴定人：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="appraiser" name="injurycase.appraiser"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											联系电话：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="telphone" name="injurycase.telphone"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											简要案情：
										</label>
									</div>
									<div class="col-10">
										<s:textarea name="injurycase.briefCase" cssClass="input-text"
											id="input9"
											cssStyle="width: 100%; height: 80px; float: left;"
											placeholder="简要案情"></s:textarea>
									</div>
								</div>
								<s:if test="itype==2">
									<div class="row cl mb-10">
										<div class="col-2">
											<label class="form-label text-r">
												伤势评估：
											</label>
										</div>
										<div class="col-10">
											<s:textfield name="injurycase.injuryAssess"
												cssClass="input-text" id="input9"
												cssStyle="width: 100%; height: 80px; float: left;"
												placeholder="伤势评估"></s:textfield>
										</div>
									</div>
								</s:if>

								<div class="row cl mb-10"></div>
							</div>


						</div>
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

/*布控人-添加*/
function bukongman_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		area: ['800px', '500px'],
		title: title,
		content: url
	});
	
}
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
/*案例-疑难*/
function difficult_start(title,url,w,h){
	var index = layer.open({
		type: 2,
		area: ['800px', '500px'],
		title: title,
		content: url
	});
	
}
/*案例-保存*/
function article_save(obj,id){
	layer.confirm('确认要保存吗？',function(index){
		layer.msg('已保存!',{icon: 6,time:1000});
	});
}
</script>
	</body>
</html>