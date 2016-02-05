﻿<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>资讯列表-刑侦线索</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页<span class="c-gray en">&gt;</span>刑侦线索
<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
       <ul class="Hui-down">
			<li class="dropDown dropDown_hover"><i class="Hui-iconfont">&#xe640;</i><a href="#" class="dropDown_A">导出 <i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="#">Excel2007</a></li>
				<li><a href="#">Excel2013</a></li>
				<li><a href="#">Word文档</a></li>
			</ul>
		</li>
       </ul>           
</nav>


<div class="pd-20">
  <div class="text-c">
    <form class="Huiform" method="post" action="" target="_self">
      <table  width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:35px;">
        <tr height="35">
          <td width="21%" align="left">流水编号：
          <input type="text" placeholder="流水编号" value="" class="input-text" style="width:150px"></td>
          <td width="21%" align="left">上报机构：
          <input type="text" placeholder="上报机构" value="" class="input-text" style="width:150px"></td>
          <td width="21%" align="left">上报民警：
            <input type="text" placeholder="上报民警" value="" class="input-text" style="width:150px"></td>
          <td width="21%">信息状态：
            <select class="select" size="1" name="demo1" datatype="*" nullmsg="请选择" style="width:150px">
              <option value="1">未办理</option>
              <option value="2">办理中</option>
              <option value="3">已完结</option>
          </select></td>
        </tr>
        <tr>
          <td align="left">人员编号：
          <input type="text" placeholder="人员编号" value="" class="input-text" style="width:150px"></td>
          <td colspan="3" align="left">上报时间：
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})" id="logmin" class="input-text Wdate" style="width:150px;">
            -
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})" id="logmax" class="input-text Wdate" style="width:150px;">          </td>
        </tr>
        <tr>
          <td align="left">&nbsp;</td>
          <td colspan="2" align="left">&nbsp;</td>
          <td align="center"><button type="button" class="btn btn-success" id="button2" name="" onClick=""><i class="Hui-iconfont">&#xe665;</i> 查询</button>
          <button type="button" class="btn btn-success" id="button" name="" ><i class="Hui-iconfont">&#xe66b;</i> 清空</button></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="article_add('新增侦查线索','inves-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增信息</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="35"><input type="checkbox" name="" value=""></th>
					<th width="80">流水编号</th>
					<th width="133">标题</th>
					<th width="97">上报单位</th>
					<th width="101">信息状态</th>
					<th width="94">当前环节时间</th>
					<th width="105">办结类型</th>
					<th width="90">上报民警</th>
					<th width="120">上报时间</th>
					<th width="120">操作</th>
					<th width="120">导入同伙系列</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td ><input type="checkbox" value="" name=""></td>
					<td>10001</td>
					<td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10001')" title="查看">资讯标题</u></td>
					<td>城北派出所</td>
					<td>21212</td>
					<td class="f-14 td-manage"></td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">张然</td>
					<td class="f-14 td-manage">2014-6-11</td>
					<td class="f-14 td-manage">
                    
                    <a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑信息','run-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> 
                    <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					<td class="f-14 td-manage">导入</td>
				</tr>
                <tr class="text-c">
					<td ><input type="checkbox" value="" name=""></td>
					<td>10001</td>
					<td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10001')" title="查看">资讯标题</u></td>
					<td>城北派出所</td>
					<td>21212</td>
					<td class="f-14 td-manage"></td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('资讯信息','article-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					<td class="f-14 td-manage">&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
    
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,2,4,5,6,7,8,9]}// 制定列不参与排序
	]
});

/*信息-添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*信息-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*信息-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}


</script> 
</body>
</html>