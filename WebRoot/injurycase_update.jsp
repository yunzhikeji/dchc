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
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<title>修改案件</title>
	</head>
	<body>
		<form name="injurycaseAddForm" action="injurycaseAction!update"
			method="post" enctype="multipart/form-data" onsubmit="">
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class="col-6 col-offset-6 ">
							<div class=" f-r pr-5">
								<s:token></s:token>
								<input type="submit" class="btn btn-success radius" id="button"
									value="保存并提交"></input>
							</div>
						<s:if test="injurycase.getEndSituation()!=null&&injurycase.getEndSituation()!='0'">
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
											案件编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="caseNumber" name="injurycase.caseNumber"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案件名称：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="caseName" name="injurycase.caseName"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
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
									<s:textfield name="injurycase.briefCase" cssClass="input-text"
										id="input9" cssStyle="width: 100%; height: 80px; float: left;"
										placeholder="简要案情"></s:textfield>
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
															<a href="#" onclick="javascript::"> <s:property
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
										<input class="btn btn-primary radius mt-10 f-r" type="button"
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
																<a href="#" onclick="javascript::"> <s:property
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
								<!--上报信息-->
								<div class="row cl mt-20">
									<div class="col-12 mb-0 c-primary f-16"
										style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
										上报信息
										<input class="btn btn-primary radius mt-10 f-r" type="button"
											onclick="addPage('上报信息','judgeAction!goToAdd?inid=<s:property value="id"/>&jtype=3','650','300')"
											value="上报信息">
									</div>
									<div class="col-12">
										<table class="table table-border table-bg mb-10"
											style="table-layout: fixed;">
											<thead>
												<tr>
													<th width="7%">
														上报次序
													</th>
													<th width="24%">
														报送机构
													</th>
													<th width="57%">
														上报要求
													</th>
													<th width="12%">
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
																<a href="#" onclick="javascript::"> <s:property
																		value="judgeRequirement" /> </a>
															</td>
															<td>
																<a style="text-decoration: none" class="ml-5"
																	onclick="addPage('编辑上报信息','judgeAction!load?jid=<s:property value="id" />','500','300')"
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
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="line-height: 45px;">
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
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="line-height: 45px;">
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
														上报信息
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
				<s:hidden name="injurycase.itype" title="案件类型"></s:hidden>
				<s:hidden name="injurycase.id" title="案件id"></s:hidden>
				<s:hidden name="injurycase.userRole.id" title="案件子表userRoleid"></s:hidden>
				<s:hidden name="injurycase.handleState" title="办理状态"></s:hidden>
				<s:hidden name="injurycase.joinDate" title="录入时间"></s:hidden>
		</form>
	</body>
</html>