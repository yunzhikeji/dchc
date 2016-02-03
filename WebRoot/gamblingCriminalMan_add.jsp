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
	<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
	<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
	<link href="css/ncss.css" rel="stylesheet" type="text/css" />
	<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
	<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
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
		<title>新增人员信息</title>
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
							<input type="submit"  class="btn btn-success radius" id="button" value="保存并提交"></input>
						</div>
					</div>
				</div>

				<div style="width: 100%">
					<div id="tab_demo" class="HuiTab">
						<div class="tabBar cl">
							<span>信息录入</span>
						</div>
						<div class="tabCon"><div align="right"> 
							</div><div class="row cl text-c">
								<h1 style="line-height: 80px;">
									<small style="color: #000;"><s:property value="pageTileName"/>信息表</small>
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
										<td width="10%">
											<label class="form-label text-r">
												<span class="c-red">*</span>人员编号：
											</label>
										</td>
										<td>
												<s:textfield  id="number" name="person.number" cssClass="input-text radius size-M "
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
											<s:textfield name="person.name" id="name"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td width="26%" rowspan="5" align="left">
											<table width="176" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="176" align="center">
													 <img  id="myimage" class="img-responsive thumbnail" width="176px" height="220px" alt="人员照片"/>
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
													 <s:file name="picture" cssClass="btn btn-primary radius mt-10"  accept="image/jpeg,image/png,image/jpg" onchange="change();" id="myfile"></s:file>
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
											<s:select list="#{1:'男',0:'女'}"
											cssClass="input-text" name="person.sex" listKey="key"
									listValue="value" cssStyle="width:200px"></s:select>
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
												身份证号：
											</label>
										</td>
										<td>
											<s:textfield name="person.idcard"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										
										<td>
											<label class="form-label text-r">
												DNA编号：
											</label>
										</td>
										<td>
										<s:textfield name="gamblingCriminalMan.dnanumber"  cssClass="input-text radius size-M "
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
											<s:textfield name="gamblingCriminalMan.fingerPrintNumber"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												足迹编号：
											</label>
										</td>
										<td>
											<s:textfield name="gamblingCriminalMan.footPrintNumber"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												户籍地区划：
											</label>
										</td>
										<td>
											<s:textfield name="gamblingCriminalMan.registerAddressArea"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												户籍地详址：
											</label>
										</td>
										<td>
												<s:textfield name="person.registerAddress"  cssClass="input-text radius size-M "
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
											<s:textfield name="gamblingCriminalMan.currentAddressArea"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												现住地详址：
											</label>
										</td>
										<td colspan="2">
												<s:textfield name="gamblingCriminalMan.currentAddress"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												其它身份信息：
											</label>
										</td>
										<td>
											<s:textfield name="gamblingCriminalMan.otherId"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												手机号码：
											</label>
										</td>
										<td colspan="2">
										<s:textfield name="person.telphone"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												QQ：
											</label>
										</td>
										<td>
												<s:textfield name="person.qq"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												微信号：
											</label>
										</td>
										<td colspan="2">
											<s:textfield name="person.wechat"  cssClass="input-text radius size-M "
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
												<s:textfield name="gamblingCriminalMan.virtualId"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												银行卡信息：
											</label>
										</td>
										<td colspan="2">
											<s:textfield name="gamblingCriminalMan.bankCard"  cssClass="input-text radius size-M "
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
											<s:textfield name="gamblingCriminalMan.nickname"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												车牌号：
											</label>
										</td>
										<td colspan="2">
												<s:textfield name="gamblingCriminalMan.carLicenseNumber"  cssClass="input-text radius size-M "
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
										<s:textfield name="gamblingCriminalMan.engineNumber"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												车架号：
											</label>
										</td>
										<td colspan="2">
											<s:textfield name="gamblingCriminalMan.carFrameNumber"  cssClass="input-text radius size-M "
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
										<s:textfield name="gamblingCriminalMan.imei"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										
										<s:if test="type==1">
											<td>
												<label class="form-label text-r">
													赌场角色：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="gamblingCriminalMan.casinoRole"  cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<s:hidden name="gamblingCriminalMan.equivocation" title="可疑度"></s:hidden>
										</s:if>
										<s:elseif test="type==6">
											<td>
												<label class="form-label text-r">
													可疑度：
												</label>
											</td>
											<td colspan="2">
												<s:textfield name="gamblingCriminalMan.equivocation"  cssClass="input-text radius size-M "
													cssStyle="width: 200px;"></s:textfield>
											</td>
											<s:hidden name="gamblingCriminalMan.casinoRole" title="赌场角色"></s:hidden>
										</s:elseif>
										<s:else>
											<s:hidden name="gamblingCriminalMan.casinoRole" title="赌场角色"></s:hidden>
											<s:hidden name="gamblingCriminalMan.equivocation" title="可疑度"></s:hidden>
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
										<s:textfield name="gamblingCriminalMan.gambleType"  cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												是否涉毒：
											</label>
										</td>
										<td>
											<s:select list="#{0:'否',1:'是'}"
											cssClass="input-text" name="gamblingCriminalMan.isDrugRelated" listKey="key"
									listValue="value" cssStyle="width:200px"></s:select>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									</s:if>
									<s:else>
										<s:hidden name="gamblingCriminalMan.gambleType" title="赌博类型"></s:hidden>
										<s:hidden name="gamblingCriminalMan.isDrugRelated" title="是否涉毒"></s:hidden>
									</s:else>
									<tr>
										<td>
											<label class="form-label text-r">
												信息提取情况：
											</label>
										</td>
										<td colspan="4">
											<label for="check-box">
											<s:checkboxlist theme="simple" cssStyle="width:36px" name="gamblingCriminalMan.infoExtraction" list="{'提取手机信息','提取银行卡信息','提取DNA','提取指纹','提取鞋印'}" />
											</label>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												是否布控：
											</label>
										</td>
										<td colspan="4">
											<s:select list="#{0:'否',1:'是'}"
											cssClass="input-text" name="person.isMakeControl" listKey="key"
									listValue="value" cssStyle="width:200px"></s:select>
										</td>
									</tr>
								</table>

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
											<s:textfield name="person.carrier"  cssClass="input-text radius size-M "
												cssStyle="width: 80%;"></s:textfield>
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
											<s:textfield name="person.carryTool"  cssClass="input-text radius size-M "
												cssStyle="width: 80%;"></s:textfield>
										多个物品请输入&quot;,&quot;隔开
									</div>
								</div>
							</div>
							<s:if test="type==6">
							<div class="row cl mt-20">
						     <div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7 ">盘问原因</div>
						     <div class="row cl">
						     <div class="col-1" ><label class="form-label text-r">盘问原因：</label></div>
						     <div class="col-11" >
						       <s:textfield cssClass="input-text radius size-M " cssStyle="width:80%; height:150px;" name="gamblingCriminalMan.interrogateReason"></s:textfield>
						     </div>
						     </div> 
						     </s:if>  
						     <s:else>
						     	<s:hidden name="gamblingCriminalMan.interrogateReason" title="盘问原因"></s:hidden>
						     </s:else>
						  </div>
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
												<th width="7%">
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
		</form>
	</body>
</html>