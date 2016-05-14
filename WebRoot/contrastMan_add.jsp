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
		<title>新增技术比中人员信息</title>
	</head>
	<body>
		<form name="personAddForm" action="personAction!add" method="post"
			enctype="multipart/form-data" onsubmit="">
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
							<span>信息录入</span><span>流转信息</span>
						</div>
						<div class="tabCon">
							<div class="row cl text-c">
								<h1 style="line-height: 80px;">
									<small style="color: #000;">技术比中</small>
								</h1>
							</div>
							<div class="col-12 mb-10 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7">
								技术比中人员基本情况
							</div>
							<div class="row cl">
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											比中类别：
										</label>
									</div>
									<div class="col-4">
										<s:select list="#{0:'DNA比中',1:'指纹比中'}" cssClass="input-text"
											name="contrastMan.contrastType" listKey="key"
											listValue="value" cssStyle="width:200px"></s:select>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											人员姓名：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="person.name" id="name"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											性别：
										</label>
									</div>
									<div class="col-4">
										<s:select list="#{1:'男',0:'女'}" cssClass="input-text"
											name="person.sex" listKey="key" listValue="value"
											cssStyle="width:200px"></s:select>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											人员身份证号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="person.idcard"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											户籍地址：
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
											DNA编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="contrastMan.dnanumber" id="dnanumber"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											指掌纹编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="contrastMan.fingerPrintNumber"
											id="fingerPrintNumber" cssClass="input-text radius size-M "
											cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											采集单位：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="contrastMan.contrastUnit" id="contrastUnit"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											采集人：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="contrastMan.contrastName" id="contrastName"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											比中时间：
										</label>
									</div>
									<div class="col-4">
										<input type="text" name="contrastMan.contrastTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"
											id="logmin" class="input-text Wdate"
											style="width: 200px;">
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											采集时间：
										</label>
									</div>
									<div class="col-4">
										<input type="text" name="contrastMan.collectionTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"
											id="logmin" class="input-text Wdate"
											style="width: 200px;">
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											前科情况：
										</label>
									</div>
									<div class="col-10">
										<s:textarea name="contrastMan.criminalRecordSituation"
											cssClass="input-text radius size-M "
											cssStyle="width: 95%; height: 50px;"></s:textarea>
									</div>
								</div>
								<div class="row cl mb-10">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="line-height: 45px;">
										<tr>
											<td width="16.7%">
												<label class="form-label text-r">
													办案单位：
												</label>
											</td>
											<td colspan="4">
												<s:select
													list="#{0:'请选择',1:'案审中队',2:'禁毒中队',3:'技术中队',4:'图侦中队',5:'大案中队',6:'城北中队',7:'城南中队',8:'其他'}"
													cssClass="input-text" name="contrastMan.manageUnit"
													listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											</td>
										</tr>
									</table>
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
														width="200px"
															height="auto" alt="户籍照片" />
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
															height="auto" alt="前科照片" />
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
								</div>
								<div class="row cl mb-10"></div>
							</div>



						</div>
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
			</form>
	</body>
</html>