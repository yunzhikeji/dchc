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
		<title>编辑研判</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="lib/zTree/v3/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};
		
		
		var zNodes = [
			{id:0, name:"无单位"}
		 ];
		 
		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var showVal = $("#showVal");
			showVal.attr("value", v);
		}
		function showMenu() {
			
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		$(document).ready(function(){
			$.ajax({   
		            url:'getUnitVOs',//这里是你的action或者servlert的路径地址   
		            type:'post', //数据发送方式   
		            async:false,
		            dataType:'json',
		            error: function(msg)
		            { //失败   
		            	console.log('请求报送单位失败.');   
		            },   
		            success: function(msg)
		            { //成功
			            if(msg.length>0)
			            {
			            	zNodes = msg;
			            }
					}
				});
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
		</script>
		
	</head>

	<body>
		<div class="pd-20">
			<form name="judgeUpdateForm" class="form form-horizontal" action="judgeAction!update" method="post" onsubmit="">
					<s:hidden name="judge.id"></s:hidden>
					
					<s:if test="judge.person!=null">
					<s:hidden name="judge.person.id"></s:hidden>
					</s:if>
					<s:if test="judge.injurycase!=null">
					<s:hidden name="judge.injurycase.id"></s:hidden>
					</s:if>
					<s:if test="judge.clue!=null">
					<s:hidden name="judge.clue.id"></s:hidden>
					</s:if>
			
					
					
					<s:hidden name="judge.jtype"  title="类型"></s:hidden>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>报送机构：
					</label>
					<span class="form-label col-6"> 
						<span class="formControls col-12"> 
						<input type="text" class="input-text" id="showVal"  onclick="showMenu();" value="<s:property value="judge.reportUnit"/>" placeholder="报送机构" id="input2"
							name="judge.reportUnit" style="width:100%" readonly="readonly"/> 
						</span> 
					</span>
					
				<span class="form-label col-4" style="margin-left:-50px;"> 
					<input type="button" id="citySel" onclick="showMenu();" class="btn btn-primary radius" value="选择部门">
				</span>
				<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
						<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
					</div>
					</div>
				<div class="row cl">
					<label class="form-label col-2">
						报送时间：
					</label>
					<span class="form-label col-3"> 
					<span class="formControls col-12"> <input type="text"
								name="judge.reportTime"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"
								id="troubleshooting" class="input-text Wdate" value="<s:property value="judge.reportTime"/>"
								> </span> </span>
					<label class="form-label col-2">
					<s:if test="judge.jtype==1">研判</s:if><s:if test="judge.jtype==2">查证</s:if><s:if test="judge.jtype==3">上报</s:if>次序：
				</label>
				<span class="form-label col-3"> <span
					class="formControls col-12"> 
					<s:textfield  cssClass="input-text" placeholder="顺序" id="indexNumber"
							name="judge.indexNumber" cssStyle="width: 200px;" ></s:textfield>
							</span> </span>
					</div>
				<div class="row cl">
				<label class="form-label col-2">
					承办人：
				</label>
				<span class="form-label col-3"> <span
					class="formControls col-12"><input type="text" id="transactor"
								class="input-text" 
								name="judge.transactor"
								value="<s:property value="judge.transactor"/>" /> </span> </span>
				<label class="form-label col-2">
					联系电话：
				</label>
				<span class="form-label col-3"> <span
					class="formControls col-12"> 
					<s:textfield  cssClass="input-text" placeholder="联系电话" id="telphone"
							name="judge.telphone" cssStyle="width: 200px;" ></s:textfield>
							</span> </span>
				</div>
		<div class="row cl">
			<label class="form-label col-2">
				<s:if test="judge.jtype==1">研判要求</s:if><s:if test="judge.jtype==2">查证要求</s:if><s:if test="judge.jtype==3">上报内容</s:if>：
			</label>
			<span class="form-label col-9">
			<span class="formControls col-10">
						<s:textarea name="judge.judgeRequirement"
						cssClass="input-text" id="judgeRequirement" 
						cssStyle="width: 113%; height: 120px; float: left;" ></s:textarea>
			</span> </span>
		</div>
		<s:if test="judge.jtype!=3">
	<s:if test='%{#session.userRoleo.unit.number == "技术中队"}'>
			<div class="row cl">
			<label class="form-label col-2">
				刑技<s:if test="judge.jtype==1">研判</s:if><s:if test="judge.jtype==2">查证</s:if>：
			</label>
			<span class="form-label col-9">
			<span class="formControls col-10">
						<s:textarea name="judge.criminalJudge"
						cssClass="input-text" id="criminalJudge" 
						cssStyle="width: 113%; height: 120px; float: left;" placeholder="刑技研判"></s:textarea>
			</span> </span>
			<s:hidden name="judge.networkJudge"></s:hidden>
			<s:hidden name="judge.intelligenceJudge"></s:hidden>
			<s:hidden name="judge.imageJudge"></s:hidden>
		</div>
		</s:if>
		<s:if test='%{#session.userRoleo.unit.number == "371402020000"}'>
		<div class="row cl">
			<label class="form-label col-2">
				网技<s:if test="judge.jtype==1">研判</s:if><s:if test="judge.jtype==2">查证</s:if>：
			</label>
			<span class="form-label col-9">
			<span class="formControls col-10">
						<s:textarea name="judge.networkJudge"
						cssClass="input-text" id="networkJudge" 
						cssStyle="width: 113%; height: 120px; float: left;" placeholder="网技研判"></s:textarea>
			</span> </span>
			<s:hidden name="judge.criminalJudge"></s:hidden>
			<s:hidden name="judge.intelligenceJudge"></s:hidden>
			<s:hidden name="judge.imageJudge"></s:hidden>
		</div>
		</s:if>
		<s:if test='%{#session.userRoleo.unit.number == "371402060000"}'>
		<div class="row cl">
			<label class="form-label col-2">
				情报<s:if test="judge.jtype==1">研判</s:if><s:if test="judge.jtype==2">查证</s:if>：
			</label>
			<span class="form-label col-9">
			<span class="formControls col-10">
						<s:textarea name="judge.intelligenceJudge"
						cssClass="input-text" id="intelligenceJudge" 
						cssStyle="width: 113%; height: 120px; float: left;" placeholder="情报研判"></s:textarea>
			</span> </span>
			<s:hidden name="judge.criminalJudge"></s:hidden>
			<s:hidden name="judge.networkJudge"></s:hidden>
			<s:hidden name="judge.imageJudge"></s:hidden>
		</div>
		</s:if>
		<s:if test='%{#session.userRoleo.unit.number == "图像侦查"}'>
		<div class="row cl">
			<label class="form-label col-2">
				图像<s:if test="judge.jtype==1">研判</s:if><s:if test="judge.jtype==2">查证</s:if>：
			</label>
			<span class="form-label col-9">
			<span class="formControls col-10">
						<s:textarea name="judge.imageJudge"
						cssClass="input-text" id="imageJudge" 
						cssStyle="width: 113%; height: 120px; float: left;" placeholder="图像研判"></s:textarea>
			</span> </span>
			<s:hidden name="judge.criminalJudge"></s:hidden>
			<s:hidden name="judge.networkJudge"></s:hidden>
			<s:hidden name="judge.intelligenceJudge"></s:hidden>
		</div>
		</s:if>
		</s:if>	
			<div class="row cl">
				<s:token></s:token>
				<div class="col-10 col-offset-2">
					<input type="submit" class="btn btn-primary radius" value="保存并提交">
					</input>
					<button onclick="childPage_close();" class="btn btn-default radius"
						type="button">
						&nbsp;&nbsp;取消&nbsp;&nbsp;
					</button>
				</div>
			</div>
		</form>
		</div>
	</body>
</html>
