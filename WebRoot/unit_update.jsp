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
<title>修改部门（机构）</title>
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript">

</script>
</head>

<body>

<form name="unitUpdateForm" action="unitAction!update" method="post" onsubmit="return changeUnit();">
<s:hidden name="unit.id"></s:hidden>
<s:hidden name="unit.unitLimit"></s:hidden>
<s:hidden name="unit.pids"></s:hidden>
<s:hidden name="unit.cids"></s:hidden>
<s:hidden name="unit.cids"></s:hidden>
<div class="pd-20">
	<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">上级机构：</label></div>
     <div class="col-4" >
       <s:textfield cssClass="input-text radius size-M" cssStyle="width:200px;" name="unit.superior" value="德州分局" readonly="true"></s:textfield>
     </div>
	</div>
<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">机构名称：</label></div>
     <div class="col-4" >
       <s:textfield id="unitName" cssClass="input-text radius size-M" cssStyle="width:200px;" name="unit.name" ></s:textfield>
    
     </div>
</div>
<div class="row cl mb-10">
     <div class="col-2" >
    <label class="form-label text-r">机构代码：</label></div>
     <div class="col-10" >
      <s:textfield id="unitNumber" cssClass="input-text radius size-M" cssStyle="width:200px;" name="unit.number"  ></s:textfield>
     </div>
  </div>
<div class="row cl">
	    <s:token></s:token>
    <div class="col-10 col-offset-2">
      <input type="submit"  class="btn btn-primary radius" value="保存并提交"> </input>
      <button onclick="childPage_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
    </div>
  </div>
  </div>
  </form>
</body>
</html>
