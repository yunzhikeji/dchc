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
		<title>媒体视频</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>

	</head>
	<script language="text/javascript">
	console.log("跳转打印..");
	var params = window.location.search;
	
	console.log(params);


	params = params.substring(6);


	console.log(params);


	function viewData() {
		var sData = window.dialogArguments;

		window.document.getElementById("output").appendChild(sData);
		document.getElementById("inid").setAttribute("value", params);
		pain();
	}
	window.onload = viewData;
function viewPaint(){

var canvas_ = document.getElementById("_canvas");



var context = canvas_.getContext("2d");
context.strokeStyle = "red";
context.lineWidth = 4;

var array_paint = [];
var current_y = 0;
var current_x = 0;
//判断鼠标是否按下
var m_down = false;


function paint()
{
context.beginPath();
context.moveTo(array_paint[0][0],array_paint[0][1]);
if(array_paint.length == 1)
context.lineTo(array_paint[0][0] +1,array_paint[0][1] +1);
else
{
var i =1; 
for(i in array_paint)
{
context.lineTo(array_paint[i][0],array_paint[i][1]);
context.moveTo(array_paint[i][0],array_paint[i][1]);
}

}
context.closePath();
context.stroke();
}


//按下鼠标
canvas_.onmousedown = function(event)
{
m_down = true;
current_x = event.offsetX;
current_y = event.offsetY;
array_paint.push([current_x,current_y]);
paint();
}


//鼠标松开,清空数据
canvas_.onmouseup = function(event)
{
m_down = false;
array_paint = [];
}


//鼠标按下后拖动
canvas_.onmousemove = function(event)
{
if(m_down)
{
current_x = event.offsetX;
current_y = event.offsetY;
array_paint.push([current_x,current_y]);
paint();
}
}

}


function mark(){
	alert("过来了么");
	var thumb = document.getElementById("_canvas");
	document.getElementById("sMark").setAttribute("value",thumb.toDataURL());

		var title = $("#title").val();
	
	if(title==''||title==null)
	{
		alert("媒体名称不能为空.");
		return false;
	}
}

</script>
	<body>

		<input id="sData1" type="hidden" name="media.picSrc" />
		<form name="mediaAddForm" action="mediaAction!add1" method="post"
			enctype="multipart/form-data" onsubmit="return mark();">
			<input id="inid" type="hidden" name="media.injurycase.id" />
			<input type="hidden" name="media.mtype" value="0" />
			<input id="sMark" type="hidden" name="media.picSrc" />
			<div class="pd-20">
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							媒体名称：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="title" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="media.title"></s:textfield>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							媒体描述：
						</label>
					</div>
					<div class="col-4">
						<s:textarea name="media.descript" cssClass="input-text"
							cssStyle="width: 260px; height: 180px; float: left;"></s:textarea>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							上传时间：
						</label>
					</div>
					<div class="col-4">
						<input type="text" name="media.uptime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
							id="logmin" class="input-text Wdate" style="width: 200px;">
					</div>
				</div>

				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							截图：
						</label>
					</div>
					<div class="col-10">
						<div class="col-3">
							<div id="output"></div>

						</div>
					</div>
				</div>
				<div class="row cl">
					<s:token></s:token>
					<div class="col-10 col-offset-2">
						<input type="submit" class="btn btn-primary radius" value="保存并提交" />
						<button onclick="viewPaint();" class="btn btn-primary radius"
							type="button">
							&nbsp;&nbsp;画笔工具&nbsp;&nbsp;
						</button>
						<button onclick="window.close();" class="btn btn-default radius"
							type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
					</div>
				</div>
		</form>
	</body>
</html>
