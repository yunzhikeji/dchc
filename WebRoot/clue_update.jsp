<!DOCTYPE HTML>
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
<link href="css/ncss.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>修改</title>
</head>
<body>
<form name="clueUpdateForm" action="clueAction!update"
			method="post" enctype="multipart/form-data"
			onsubmit="return checkClue();">
<div class="pd-20">
<div  class="row cl">
  <div class="col-6 col-offset-6 ">
      <div class=" f-r pr-5"><button type="submit" class="btn btn-success radius" id="button" name="" onClick="article_save(this,'10001')"><i class="Hui-iconfont">&#xe636;</i> 确认保存</button></div>
      <div class=" f-r pr-5"><button type="submit" class="btn btn-success radius" id="button" name="" onClick="difficult_start('发布案例','addfbal.html')"><i class="Hui-iconfont">&#xe6bf;</i> 发布案例</button></div>
  </div>
</div>

<div style="width:100%"><div id="tab_demo" class="HuiTab">
<div class="tabBar cl"><span>信息录入</span><span>流转信息</span></div> 
<div class="tabCon">
  <div  class="row cl text-c" >
    <h1 style="line-height:80px; "><small style="color:#000;">侦查线索信息表</small></h1> </div>
  <div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7 ">上报信息</div>
    <div  class="row cl">
  <div class="row cl mb-10">
    <div class="col-2" >
      <label class="form-label text-r">编号：</label>
    </div>
    <div class="col-4" >
      123546
</div>
  </div>
  <div class="row cl mb-10">
     <div class="col-2" >
       <label class="form-label text-r">密级：</label></div>
     <div class="col-10" >
       <input type="text" class="input-text radius size-M " style="width:50px;">
(A：本单位领导和接收单位领导可查看。B：本单位和接收单位可查看。C：所有单位可查看) 
     </div>
  </div>
  <div class="row cl mb-10">
    <div class="col-2" >
      <label class="form-label text-r">情报类别：</label>
    </div>
    <div class="col-10" >
     <s:textfield id="intelligenceType" name="clue.intelligenceType"
												cssClass="input-text radius size-M "
												cssStyle="width: 50px;"></s:textfield>
    </div>
  </div>
  <div class="row cl mb-10">
    <div class="col-2" >
      <label class="form-label text-r">标题：</label>
    </div>
    <div class="col-10" >
    <s:textfield id="title" name="clue.title"
												cssClass="input-text radius size-M "
												cssStyle="width:90%;"></s:textfield>
      <input type="text" class="input-text radius size-M " style="width:90%;">
    </div>
  </div>
     <div class="row cl mb-10">
       <div class="col-2" >
       <label class="form-label text-r">联系人：</label></div>
     <div class="col-4" >
       <input type="text" class="input-text radius size-M " style="width:300px;">
     </div>
     <div class="col-2" >
       <label class="form-label text-r">电话：</label></div>
     <div class="col-4" ><input type="text" class="input-text radius size-M " style="width:300px;"></div>
 </div>
     <div class="row cl mb-10">
       <div class="col-2" >
         <label class="form-label text-r">上报单位：</label></div>
     <div class="col-4" >
       <input type="text" class="input-text radius size-M " style="width:300px;">
     </div>
     <div class="col-2" >
       <label class="form-label text-r">上报人：</label></div>
     <div class="col-4" ><input type="text" class="input-text radius size-M " style="width:300px;"></div>
   </div>
     <div class="row cl mb-10">
     <div class="col-2" >
       <label class="form-label text-r">上报时间：</label></div>
     <div class="col-4" >
       <input type="text" class="input-text radius size-M " style="width:300px;">
     </div>
     <div class="col-2" >
       <label class="form-label text-r">联系电话：</label></div>
     <div class="col-4" ><input type="text" class="input-text radius size-M " style="width:300px;"></div>
     </div>
    </div>
     <!--新增嫌疑人 -->
<div class="row cl mt-20">
     <div class="col-12 mb-0 c-primary f-16" style="border-bottom: solid 2px #2DABF7; line-height: 43px;">嫌疑人信息
       <input class="btn btn-primary radius mt-10 f-r" type="button" onclick="addgxr('新增嫌疑人','addxyr.html','900','600')"  value="新增嫌疑人">
     </div>
     <div class="col-12">
     <table class="table table-border table-bg">
  <thead><tr>
    <th>序号</th>
    <th>姓名</th>
    <th>身份证</th>
    <th>工作单位</th>
    <th>联系电话</th>
    <th>操作</th>
  </tr></thead>
 <tbody> <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr></tbody>
</table>
   </div>
  </div>
    <!--案件基本信息-->
<div class="row cl mt-20">
     <div class="col-12 mb-0 c-primary f-16" style="border-bottom: solid 2px #2DABF7; line-height: 43px;">案件基本信息<input class="btn btn-primary radius mt-10 f-r" 

type="button" onclick="addsaqk('新增涉案情况','addsaqk.html','500','300')"  value="新增涉案情况"></div>
     <div class="col-12">
     <table class="table table-border table-bg mb-10">
  <thead><tr>
    <th>序号</th>
    <th>案件编号</th>
    <th>案件基本情况</th>
    <th>填报人</th>
    <th>填报机构</th>
    <th>操作</th>
  </tr></thead>
 <tbody> <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr></tbody>
</table>
     </div>
     
  </div>
<!--携带物品-->
<div class="row cl mt-20">
      <div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7; line-height: 43px;">携带物品
      </div>
      <div class="row cl mb-10">
     <div class="col-1" ><label class="form-label text-l">携带物品：</label></div>
     <div class="col-11" >
       <input type="text" class="input-text radius size-M " style="width:80%;" value="">
     多个物品请输入&quot;,&quot;隔开</div>
     </div>
     <div class="row cl mb-10">
     <div class="col-1" ><label class="form-label text-l">携带工具：</label></div>
     <div class="col-11" >
       <input type="text" class="input-text radius size-M " style="width:80%;" value="">
     多个物品请输入&quot;,&quot;隔开</div>
     </div>
    </div>
  <div class="row cl mt-20">
    <div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7 ">线索信息</div>
     <div class="row cl mb-10">
     <div class="col-1" ><label class="form-label text-l">线索内容：</label></div>
     <div class="col-11" >
       <input type="text" class="input-text radius size-M " style="width:80%;" value="">
     </div>
     </div>
  </div>
    <!--发起疑难-->
<div class="row cl mt-20">
     <div class="col-12 mb-0 c-primary f-16" style="border-bottom: solid 2px #2DABF7; line-height: 43px;">发起疑难<input class="btn btn-primary radius mt-10 f-r" type="button" 

onclick="addsaqk('发起疑难','difficult-start.html','500','300')"  value="发起疑难"></div>
     <div class="col-12">
     <table class="table table-border table-bg mb-10">
  <thead><tr>
    <th>序号</th>
    <th>案件编号</th>
    <th>案件基本情况</th>
    <th>填报人</th>
    <th>填报机构</th>
    <th>操作</th>
  </tr></thead>
 <tbody> <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr></tbody>
</table>
     </div>
     
  </div>
    <!--研判情况-->
<div class="row cl mt-20">
      <div class="col-12 mb-0 c-primary f-16" style="border-bottom: solid 2px #2DABF7; line-height: 43px;">研判情况</div>
      <div class="col-12">
        <table class="table table-border table-bg mb-10">
          <thead>
            <tr>
              <th width="7%" bgcolor="#F5FAFE">研判序号</th>
              <th align="center">1</th>
              <th align="center">2</th>
              </tr>
          </thead>
          <tbody>
            <tr>
              <td>刑技研判</td>
              <td>
                <textarea name="input" class="input-text" id="input" style="width:400px; height:80px; float:left;" placeholder=""></textarea>
              </td>
              <td><textarea name="input2" class="input-text" id="input2" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              </tr>
            <tr>
              <td>网技研判</td>
              <td><textarea name="input3" class="input-text" id="input3" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              <td><textarea name="input4" class="input-text" id="input4" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              </tr>
            <tr>
              <td>情报研判</td>
              <td><textarea name="input5" class="input-text" id="input5" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              <td><textarea name="input6" class="input-text" id="input6" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              </tr>
            <tr>
              <td>图像侦查</td>
              <td><textarea name="input7" class="input-text" id="input7" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              <td><textarea name="input8" class="input-text" id="input8" style="width:400px; height:80px; float:left;" placeholder=""></textarea></td>
              </tr>
          </tbody>
        </table>
      </div>
    </div>
<!--完结情况-->
<div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7 " >完结情况</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:45px;">
  <tr>
    <td width="86%">
      <div class="skin-minimal  " style="margin-left:11%;">
        <div class="check-box" >
          <input type="checkbox"  id="checkbox-1" >
          <label for="checkbox-1">抓获</label>
          </div>
          <div class="check-box" >
          <input type="checkbox"  id="checkbox-2" >
          <label for="checkbox-2">死亡</label>
          </div>
          <div class="check-box" >
          <input type="checkbox"  id="checkbox-3" >
          <label for="checkbox-3">撤销案件</label>
          </div>
          <div class="check-box" >
          <input type="checkbox"  id="checkbox-4" >
          <label for="checkbox-4">释放</label>
          </div>
          <div class="check-box" >
          <input type="checkbox"  id="checkbox-5" >
          <label for="checkbox-5">治安拘留</label>
          </div>
        <div class="check-box">
          <input type="checkbox" id="checkbox-6" >
          <label for="checkbox-6">刑事拘留</label>
          </div>
        <div class="check-box">
          <input type="checkbox" id="checkbox-7" >
          <label for="checkbox-7">留置盘问</label>
          </div>
        <div class="check-box">
          <input type="checkbox" id="checkbox-8" >
          <label for="checkbox-8">其它</label>
          </div>
      </div></td>
    </tr>
</table>
<!--综合情况--> <!--领导批示-->
   <div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7 " >综合情况</div>

  <div class="row cl">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:45px;">
      <tr>
        <td width="10%" valign="top"><label class="form-label text-r">综合情况：</label></td>
        <td><textarea name="input9" class="input-text" id="input9" style="width:800px; height:80px; float:left;" placeholder=""></textarea></td>
        </tr>
      </table>

    
    <div class="col-12 mb-10 c-primary f-16" style="border-bottom: solid 2px #2DABF7 " >领导批示</div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:45px;">
      <tr>
        <td width="10%" valign="top"><label class="form-label text-r">领导批示：</label></td>
        <td><textarea name="input10" class="input-text" id="input10" style="width:800px; height:80px; float:left;" placeholder=""></textarea></td>
        </tr>
    </table>
</div>
  </div>
  <div class="tabCon">
   <div class="row cl">
      <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:100px; width:200px; border:solid 1px #666">
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr bgcolor="#ccc" >
             <td>&nbsp;</td>
           </tr>
           <tr>
               <td>&nbsp;</td>
           </tr>
          </table>
        </div>
      </div>
   </div>
<!--箭头分割-->
<div class="row cl">
    <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:25px; width:200px; text-align:center">
        <i class="Hui-iconfont f-18 ">&#xe674;</i>
        </div>
      </div>
   </div>         <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:100px; width:200px; border:solid 1px #666">
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr bgcolor="#ccc" >
             <td>&nbsp;</td>
           </tr>
           <tr>
               <td>&nbsp;</td>
           </tr>
          </table>
        </div>
      </div>
<!--箭头分割-->
<div class="row cl">
    <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:25px; width:200px; text-align:center">
        <i class="Hui-iconfont f-18 ">&#xe674;</i>
        </div>
      </div>
   </div>     <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:30px; width:200px; line-height:30px; border:solid 1px #666">
           完结
        </div>
      </div>
<!--箭头分割-->
<div class="row cl">
    <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:25px; width:200px; text-align:center">
        <i class="Hui-iconfont f-18 ">&#xe674;</i>
        </div>
      </div>
   </div>     <div class="col-offset-5 col-5-1 text-c" style="padding:5px; ">
        <div style="height:30px; width:200px; line-height:30px; border:solid 1px #666">
           领导批示
        </div>
      </div>
   </div>
</div>
  
  
  </div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script> 
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