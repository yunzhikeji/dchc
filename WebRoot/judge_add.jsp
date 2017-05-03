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
		<title>发起研判</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<link rel="stylesheet"
			href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.excheck-3.5.js"></script>
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
		            url:'getOpertionUnit',//这里是你的action或者servlert的路径地址
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
			<form name="judgeAddForm" class="form form-horizontal"
				enctype="multipart/form-data" action="judgeAction!add" method="post"
				onsubmit="">

				<s:if test="pid!=null&&pid!=0">
					<input type="hidden" name="judge.person.id" value="${pid}" />
				</s:if>
				<s:if test="inid!=null&&inid!=0">
					<input type="hidden" name="judge.injurycase.id" value="${inid}" />
				</s:if>
				<s:if test="cid!=null&&cid!=0">
					<input type="hidden" name="judge.clue.id" value="${cid}" />
				</s:if>
				<input type="hidden" name="judge.jtype" value="${jtype}" />
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>报送机构：
					</label>
					<span class="form-label col-6"> <span
						class="formControls col-10"> <input type="text"
								class="input-text" id="showVal" onclick="showMenu();" value=""
								placeholder="报送机构" id="input2" name="judge.reportUnit"
								style="width: 140%" readonly="readonly" /> </span> </span>

					<span class="form-label col-4"> <input type="button"
							id="citySel" onclick="showMenu();" class="btn btn-primary radius"
							value="选择部门"> </span>
					<div id="menuContent" class="menuContent"
						style="display: none; position: absolute; z-index: 99;">
						<ul id="treeDemo" class="ztree"
							style="margin-top: 0; width: 180px; height: 300px; margin-right: 0;"></ul>
					</div>
				</div>


				<div class="row cl">
					<label class="form-label col-2">
						立案扫描件：
					</label>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center">
								<img id="myimage1" class="img-responsive thumbnail"
									width="300px" height="280px;" alt="扫描件" />
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
								<s:file name="file" accept="image/jpeg,image/png,image/jpg"
									onchange="change1();" id="myfile1"></s:file>
							</td>
						</tr>
					</table>
				</div>


				<div class="row cl">
					<label class="form-label col-2">
						报送时间：
						<span style="color: red">(12个小时内未处理将自动记为超时办理)</span>
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <input type="text"
								name="judge.reportTime"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"
								id="troubleshooting" class="input-text Wdate"> </span> </span>
				</div>

				<div class="row cl">
					<label class="form-label col-2">
						承办人：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"><input type="text"
								id="transactor" class="input-text" name="judge.transactor"
								value="${person.userRole.realname}" /> </span> </span>
					<label class="form-label col-2">
						联系电话：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <s:textfield
								cssClass="input-text" value="" placeholder="联系电话" id="telphone"
								name="judge.telphone" cssStyle="width: 200px;"></s:textfield> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<s:if test="jtype==1">研判要求</s:if>
						<s:if test="jtype==2">查证要求</s:if>
						<s:if test="jtype==3">上报内容</s:if>
						：
					</label>
					<span class="form-label col-9"> <span
						class="formControls col-10"> <s:textarea
								name="judge.judgeRequirement" cssClass="input-text"
								id="judgeRequirement"
								cssStyle="width: 113%; height: 120px; float: left;"></s:textarea>
					</span> </span>
				</div>
					<div class="row cl">
						<s:token></s:token>
						<div class="col-10 col-offset-2">
							<input type="submit" class="btn btn-primary radius" value="保存并提交" />
							<button onclick="childPage_close();"
								class="btn btn-default radius" type="button">
								&nbsp;&nbsp;取消&nbsp;&nbsp;
							</button>
						</div>
					</div>
			</form>
		</div>
	</body>
</html>
