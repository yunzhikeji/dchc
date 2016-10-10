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
			<!--[if lt IE 9]>
		<script type="text/javascript" src="lib/html5.js"></script>
		<script type="text/javascript" src="lib/respond.min.js"></script>
		<script type="text/javascript" src="lib/PIE_IE678.js"></script>
		<![endif]-->
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
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
		<title>修改刑侦线索</title>
	</head>
	<body>
		<form name="clueAddForm" action="clueAction!update" method="post"
			onsubmit="return  checkClue();">
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
						<s:if
							test="clue.getEndSituation()!=null&&clue.getEndSituation()!='0'">
							<div class=" f-r pr-5">
								<button type="button" class="btn btn-success radius" id="button"
									name=""
									onclick="addPage('发布案例','successexampleAction!goToAdd?cid=<s:property value="id"/>','500','300')">
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
							<div class="col-12 mb-10 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7">
								线索信息
							</div>
							<div class="row cl">
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="number" name="clue.number"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>

									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											密级：
										</label>
									</div>
									<div class="col-10">
										<s:textfield id="securityClassification"
											name="clue.securityClassification"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
										(A：本单位领导和接收单位领导可查看。B：本单位和接收单位可查看。C：所有单位可查看)
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											情报类别：
										</label>
									</div>
									<div class="col-10">
										<s:textfield id="securityClassification"
											name="clue.intelligenceType"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											标题：
										</label>
									</div>
									<div class="col-10">
										<s:textfield id="title" name="clue.title"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											联系人：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="contactName" name="clue.contactName"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											电话：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="telphone" name="clue.telphone"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											线索内容：
										</label>
									</div>
									<div class="col-10">
										<s:textfield name="clue.clueMessage" cssClass="input-text"
											id="input9"
											cssStyle="width: 100%; height: 80px; float: left;"
											placeholder="线索内容"></s:textfield>
									</div>
								</div>
							</div>
							<!--携带物品-->
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
										<s:textfield name="clue.carrier"
											cssClass="input-text radius size-M " cssStyle="width: 80%;"></s:textfield>
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
										<s:textarea name="clue.carryTool"
											cssClass="input-text radius size-M " cssStyle="width: 80%;"></s:textarea>
										多个物品请输入&quot;,&quot;隔开
									</div>
								</div>
							</div>

							<div class="row cl mt-20">
								<div class="col-12 mb-0 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
									案件基本信息
									<input class="btn btn-primary radius mt-10 f-r" type="button"
										onclick="addPage('新增涉案情况','lawcaseAction!goToAdd?cid=<s:property value="id"/>','500','300')"
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
											<s:if test="clue.lawcases.size>0">
												<s:iterator value="clue.lawcases" var="lawcase"
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
										onclick="addPage('发起疑难','troubleshootingAction!goToAdd?cid=<s:property value="id"/>','500','300')"
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

											<s:if test="clue.troubleshootings.size>0">
												<s:iterator value="clue.troubleshootings"
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
										onclick="addPage('发起研判信息','judgeAction!goToAdd?cid=<s:property value="id"/>&jtype=1','650','300')"
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
												</t>
												<th>
													研判要求
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="clue.judges.size>0">
												<s:iterator value="clue.judges" var="judge" status="status">
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
													技侦信息
												</th>
												<th align="center">
													网络信息
												</th>
												<th align="center">
													情报信息
												</th>
												<th align="center">
													图像信息
												</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="clue.judges.size>0">
												<s:iterator value="clue.judges" var="judge" status="status">
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
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="86%" style="padding-left: 9%;">
											<s:radio theme="simple" cssStyle="width:36px"
												list="#{ '1':'抓获', '2':'死亡', '3':'撤销案件', '4':'释放', '5':'治安拘留', '6':'刑事拘留', '7':'留置盘问', '8':'其他' }"
												name="clue.endSituation" />
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
											<s:textarea name="clue.comprehensiveJudge"
												cssClass="input-text" id="input9"
												cssStyle="width: 100%; height: 80px; float: left;"
												placeholder="根据以上研判信息填写综合情况"></s:textarea>
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
											<s:textarea name="clue.leaderInstruction"
												cssClass="input-text" id="input9"
												cssStyle="width: 100%; height: 80px; float: left;"
												placeholder="领导批示填写"></s:textarea>
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
										<s:property value="clue.userRole.realname" />
										：录入
									</div>
								</div>
							</div>
							<s:if test="clue.judges.size>0">
								<s:iterator value="clue.judges" var="judge"
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
								test="clue.comprehensiveJudge!=null&&clue.comprehensiveJudge!=''">
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
								test="clue.leaderInstruction!=null&&clue.leaderInstruction!=''">
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


						<s:hidden name="clue.ctype" title="线索类型"></s:hidden>
						<s:hidden name="clue.id" title="线索id"></s:hidden>
						<s:hidden name="clue.isOutOfTime" title="是否超期办理"></s:hidden>
						<s:hidden name="clue.isNew" value="0"></s:hidden>
						<s:if test="clue.userRole!=null">
						<s:hidden name="clue.userRole.id" title="线索子表userRoleid"></s:hidden>
						</s:if>
						<s:hidden name="clue.handleState" title="办理状态"></s:hidden>
						<s:hidden name="clue.joinDate" title="录入时间"></s:hidden>
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