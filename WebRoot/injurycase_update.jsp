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
		<title>修改案件</title>
	</head>
	<body>
		<form name="injurycaseAddForm" action="injurycaseAction!update"
			method="post" enctype="multipart/form-data" onsubmit="return checkCase();">
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
						<s:if
							test="injurycase.getEndSituation()!=null&&injurycase.getEndSituation()!='0'">
							<div class=" f-r pr-5">
								<button type="button" class="btn btn-success radius" id="button"
									name=""
									onclick="addPage('发布案例','successexampleAction!goToAdd?inid=<s:property value="id"/>','500','300')">
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
													<s:if
														test="injurycase.imageCase==null||injurycase.imageCase==''">
														<img class="img-responsive thumbnail"
															src="images/noimages.gif" width="200px" height="180px;" />
													</s:if>
													<s:else>
														<img id="myimage1"
															src="<%=basePath%>${injurycase.imageCase}"
															class="img-responsive thumbnail" width="200px"
															height="180px;" alt="案件图片" />
													</s:else>
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
											value="<s:property value="injurycase.startTime"/>"
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

								<s:if test="injurycase.itype==2">
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


								<div class="row cl mt-20">
									<div class="col-12 mb-0 c-primary f-16"
										style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
										上传视频信息
										<input class="btn btn-primary radius mt-10 f-r" type="button"
											onclick="addPage('新增视频','mediaAction!goToAdd?inid=<s:property value="id"/>&mtype=1','500','300')"
											value="新增视频">
									</div>
									<div class="col-12">
										<table class="table table-border table-bg">
											<thead>
												<tr>
													<th>
														序号
													</th>
													<th>
														视频标题
													</th>
													<th>
														视频描述
													</th>
													<th>
														操作
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="medias.size>0">
													<s:iterator value="medias" var="media" status="status">
														<tr>
															<td>
																<s:property value="#status.index+1" />
															</td>
															<td>
																<a href="mediaAction!view?mid=<s:property value="id" />"
																	onclick="javascript:void(0)"> <s:property
																		value="title" /> </a>
															</td>
															<td>
																<s:property value="descript" />
															</td>
															<td>
																<a style="text-decoration: none" class="ml-5"
																	onclick="addPage('编辑视频','mediaAction!load?mid=<s:property value="id" />','500','300')"
																	href="javascript:;" title="编辑"><i
																	class="Hui-iconfont">&#xe6df;</i> </a>
																<a style="text-decoration: none" class="ml-5"
																	href="javascript:;"
																	onclick="deleteMedia(<s:property value="id" />);"
																	title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</tbody>
										</table>
									</div>


									<div class="row cl mt-20">
										<div class="col-12 mb-0 c-primary f-16"
											style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
											同案人员信息
											<input class="btn btn-primary radius mt-10 f-r" type="button"
												onclick="addPage('新增同案人员','otherpersonAction!goToAdd?inid=<s:property value="id"/>&otype=2','500','300')"
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


										<div class="row cl mt-20">
											<div class="col-12 mb-0 c-primary f-16"
												style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
												发起疑难
												<input class="btn btn-primary radius mt-10 f-r"
													type="button"
													onclick="addPage('发起疑难','troubleshootingAction!goToAdd?inid=<s:property value="id"/>','500','300')"
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

														<s:if test="injurycase.troubleshootings.size>0">
															<s:iterator value="injurycase.troubleshootings"
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
												<input class="btn btn-primary radius mt-10 f-r"
													type="button"
													onclick="addPage('发起研判信息','judgeAction!goToAdd?inid=<s:property value="id"/>&jtype=1','650','300')"
													value="发起研判信息">
											</div>
											<div class="col-12">
												<table class="table table-border table-bg mb-10"
													style="table-layout: fixed;">
													<thead>
														<tr>
															<th>
																研判次序
															</th>
															<th>
																报送机构
															</th>
															<th>
																研判要求
															</th>
															<th>
																操作
															</th>
														</tr>
													</thead>
													<tbody>
														<s:if test="injurycase.judges.size>0">
															<s:iterator value="injurycase.judges" var="judge"
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
														<s:if test="injurycase.judges.size>0">
															<s:iterator value="injurycase.judges" var="judge"
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
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 45px;">
												<tr>
													<td width="86%" style="padding-left: 9%;">
														<s:radio theme="simple" cssStyle="width:36px"
															cssClass="check-box"
															list='#{ 1:"抓获", 2:"死亡", 3:"撤销案件", 4:"释放", 5:"治安拘留", 6:"刑事拘留", 7:"留置盘问", 8:"其他" }'
															name="injurycase.endSituation" />
													</td>
												</tr>
											</table>

										</div>
										<div class="col-12 mb-10 c-primary f-16"
											style="border-bottom: solid 2px #2DABF7">
											综合情况
										</div>

										<div class="row cl">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 45px;">
												<tr>
													<td width="10%" valign="top">
														<label class="form-label text-r">
															综合情况：
														</label>
													</td>
													<td>
														<s:textfield name="injurycase.comprehensiveJudge"
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
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 45px;">
												<tr>
													<td width="10%" valign="top">
														<label class="form-label text-r">
															领导批示：
														</label>
													</td>
													<td>
														<s:textfield name="injurycase.leaderInstruction"
															cssClass="input-text" id="input9"
															cssStyle="width: 100%; height: 80px; float: left;"
															placeholder="领导批示填写"></s:textfield>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="tabCon">
								<div class="row cl">
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div
											style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
											<s:property value="injurycase.userRole.realname" />
											：录入
										</div>
									</div>
								</div>
								<s:if test="injurycase.judges.size>0">
									<s:iterator value="injurycase.judges" var="judge"
										status="status">
										<div class="row cl">
											<div class="col-offset-5 col-5-1 text-c"
												style="padding: 5px;">
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
															研判次序
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
									test="injurycase.comprehensiveJudge!=null&&injurycase.comprehensiveJudge!=''">
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
									test="injurycase.leaderInstruction!=null&&injurycase.leaderInstruction!=''">
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
					<s:hidden name="injurycase.imageCase" title="案件图片"></s:hidden>
					<s:hidden name="injurycase.isCanvas" title="水印"></s:hidden>
					<s:hidden name="injurycase.pids" title="pids"></s:hidden>
					<s:hidden name="injurycase.caseIds" title="caseIds"></s:hidden>

					<s:hidden name="injurycase.itype" title="案件类型"></s:hidden>
					<s:hidden name="injurycase.id" title="案件id"></s:hidden>

					<s:if test="injurycase.userRole!=null">
						<s:hidden name="injurycase.userRole.id" title="案件子表userRoleid"></s:hidden>
					</s:if>
					<s:hidden name="injurycase.handleState" title="办理状态"></s:hidden>
					<s:hidden name="injurycase.joinDate" title="录入时间"></s:hidden>
		</form>
	</body>
</html>