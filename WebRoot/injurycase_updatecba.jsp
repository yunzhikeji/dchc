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
		<link href="css/cba.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/ncss.css" rel="stylesheet" type="text/css" />
		<link href="css/cba-add.css" rel="stylesheet" type="text/css" />
		<link href="css/cba-vd.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet"
			href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<link href="css/video-js.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<script type="text/javascript" src="js/videojs-ie8.min.js"></script>
		
		<title>串并案</title>
		<script type="text/javascript">
			var mediaVideos=<s:property value="mediaVideos" escape="false" />;
			var inid=<s:property value="injurycase.id" />;
			  function cp() {
		            var video = document.getElementById("my-video");
		            var button = document.getElementById("play");
		            if(video.paused) {
		                video.play();
		                button.textContent ="||";
		            }else{
		                video.pause();
		                button.textContent =">";
		            }
		        }
		        function restart() {var video = document.getElementById("my-video");
		            video.currentTime = 0;
		        }function skip(value) {var video = document.getElementById("my-video");
		            video.currentTime += value;
		        };

		     (function() {
		  "use strict";
		  var video, $output;
		  var scale = 0.5;
		  var initialize = function() {
		    $output = $("#output");
		    video = $("#my-video").get(0);
		    $("#capture").click(captureImage);        
		  };
		  var captureImage = function() {
		    var canvas = document.createElement("canvas");
		    canvas.id = "_canvas";
		    canvas.width = video.videoWidth * scale;
		    canvas.height = video.videoHeight * scale;
		    canvas.getContext('2d')
		       .drawImage(video, 0, 0, canvas.width, canvas.height);
		    window.showModalDialog("capture.jsp?inid="+inid,canvas,"status:no;resizable:yes;dialogHeight:700px;dialogWidth:900px;unadorne:yes;help:no");
		  };
		  $(initialize);      
		}());

		</script>
	</head>
	<body>
		<div class="pd-20">
			<div style="width: 100%">
				<div class="row cl">
					<div class="col-12 mb-10 c-primary f-16"
						style="border-bottom: solid 2px #2DABF7">
						案件视频信息
					</div>
					<div style="width: 1920px; overflow: hidden">
						<div class="index-slider-content clearfix">
						
							
							 <video id="my-video" class="video-js" controls preload="auto" width="1000px" height="562"
							   data-setup="{}" style="float: left">
							    <source class="videoSrc" src="${mediaVideos[0].src}" type='video/mp4'>
							    <source class="videoSrc" src="${mediaVideos[0].src}" type='video/webm'>
							    <p class="vjs-no-js">
							    	当前浏览器不支持该视频格式,请使用MP4格式视频格式。
							    </p>
							  </video>
						  <script type="text/javascript" src="js/video.js"></script>
						  
						<!-- 
							<video width="1000px" src="${mediaVideos[0].src}"
								height="562" id="swf_play" style="visibility: visible;"
								controls="controls">
							暂无视频
							</video> -->
							
							<div class="small-pic-container">
								<div id="tab_demo0511" class="HuiTab0511">
									<div class="tabBar0511 cl">
										<span style="width: 100px; text-align: center">同案视频</span>
										<span style="width: 100px; text-align: center">同案图片</span>
									</div>
									<div class="tabCon0511">
										<div class="ugc_rec" id="secUGC"
											style="height: 550px; overflow: auto">
											<ul id="tjptList">
												<s:if test="mediaVideos==null||mediaVideos.size<1">
													暂无视频
												</s:if>
												<s:else>
													<s:iterator value="mediaVideos" var="media" status="status">
														<li class="pack" index="0" data-stat-role="ck">
															<div class="pic">
																<a href="javascript:void(0)"
																	onclick="changeVideo(<s:property value="id"/>);"> <video
																		src="${src}" width="200px;"
																		height="92px;" style="background-color:#000"></video> </a>
															</div>
															<div class="txt">
																<h6 class="caption">
																	视频名称：
																	<s:property value="title" />
																</h6>
																<p class="owner">
																	上传时间：
																	<s:property value="uptime" />
																</p>
															</div>
														</li>
													</s:iterator>
												</s:else>
											</ul>
										</div>
									</div>
									<div class="tabCon0511">
										<div class="ugc_rec" id="secUGC"
											style="height: 550px; overflow: auto">
											<ul id="tjptList">
												<s:if test="mediaImages==null||mediaImages.size<1">
													暂无图片
												</s:if>
												<s:else>
													<s:iterator value="mediaImages" var="media" status="status">
														<li class="pack" index="0" data-stat-role="ck">
															<div class="pic">

																<img src="${src}" width="200px;"
																	height="90px;" style="background-color:#000"/>
															</div>
															<div class="txt">
																<h6 class="caption">
																	图片名称
																	<s:property value="title" />
																</h6>
																<p class="owner">
																	<a href="javascript:;"
																		onclick="deleteMedia(<s:property value="id" />);"
																		title="删除图片">删除</a>
																</p>
															</div>
														</li>
													</s:iterator>
												</s:else>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12" style="padding-top: 10px; padding-bottom: 10px">
						<button type="button" class="btn btn-primary radius size-L"
							id="capture" name="">
							<i class="Hui-iconfont">&#xe6df;</i> 截图
						</button>
						&nbsp;
						<button type="button" class="btn btn-success radius size-L" id=""
							name="" onclick="skip(-10)">
							<i class="Hui-iconfont">&#xe6d4;</i>
						</button>
						&nbsp;
						<button type="button" class="btn btn-success radius size-L" id=""
							name="" onclick="skip(10)">
							<i class="Hui-iconfont">&#xe6d7;</i>
						</button>
					</div>

					<div class="col-12 mb-10 c-primary f-16"
						style="border-bottom: solid 2px #ddd; color: #999">

						<div class="col-12 mb-10  c-primary f-16"
							style="border-bottom: solid 2px #2DABF7">
							串并案
						</div>

						<div class="col-12"
							style="padding-top: 10px; padding-bottom: 10px">
							<div style="padding-bottom: 20px">
								<form action="injurycaseAction!loadcba" method="post">
									<label class="text-r">
										案件查询关键字：
									</label>
									<s:textfield cssClass="input-text radius size-M "
										name="keyword" id="keyword" cssStyle="width: 200px">
									</s:textfield>
									<input type="hidden" name="id" id="injurycaseId"
										value="<s:property value="injurycase.id"/>" />
									<button type="submit" class="btn btn-primary radius  ml-5"
										id="" name="" title="查 询">
										查询
									</button>
								</form>
							</div>

							<div style="padding-bottom: 20px">
								<label class="text-r">
									串并案系列名称：
								</label>
								<s:textfield name="injurycase.series"
									cssClass="input-text radius size-M " id="series"
									cssStyle="width: 200px">
								</s:textfield>
								<button type="button" onclick="handleInjurycaseSeries();"
									class="btn btn-warning radius  ml-5" id="" name="" title="串并">
									串并案件
								</button>
							</div>
							<s:if test="injurycases==null||injurycases.size<1">
								<div class="col-12 mb-10  c-primary f-16" id="injurycases"
									style="font-size: 20px; font-family: serif; color: red;">
									未查询到相关案件
								</div>
							</s:if>
							<s:iterator value="injurycases" var="injurycase" status="status">
								<div style="width: 20%; float: left">
									<ul id="tjptList">
										<li class="pack" index="0" data-stat-role="ck">
											<div class="pic">
												<a
													href="injurycaseAction!loadcba?id=<s:property value="id"/>"
													title="单个案件" class="link"><img
														src="<s:property value="imageCase"/>"
														alt="暂无图片"> </a>
											</div>
											<div class="txt">
												<h6 class="caption">
													案件名称：
													<s:property value="caseName" />
												</h6>
												<p class="owner">
													<s:if test="isRelated==1">
													串并案系列名称：<span class="video-cate"
															id="series<s:property value="id"/>"><s:property
																value="series" /> </span>
													</s:if>
													<s:else>
										未串并
									</s:else>
												</p>
											</div>
											<div style="text-align: center; height: 10%;">
												<input name="indexID" class="indexID" type="checkbox"
													class="indexID" value="<s:property value="id"/>">
											</div>
										</li>
									</ul>
								</div>
							</s:iterator>


							<div class="col-12 mb-10  c-primary f-16"
								style="border-bottom: solid 2px #2DABF7">
								已串并案件
							</div>
							<s:if test="injurycaseSeries==null||injurycaseSeries.size<1">
								<div class="col-12 mb-10  c-primary f-16" id="injurycaseSeries"
									style="font-size: 20px; font-family: serif; color: red;">
									没有已串并案件
								</div>
							</s:if>
							<s:iterator value="injurycaseSeries" var="injurycase"
								status="status">
								<div style="width: 20%; float: left">
									<ul id="tjptList">
										<li class="pack" index="0" data-stat-role="ck">
											<div class="pic">
												<a
													href="injurycaseAction!loadcba?id=<s:property value="id"/>"
													title="单个案件" class="link"><img
														src="<s:property value="imageCase"/>">
												</a>
											</div>
											<div class="txt">
												<h6 class="caption">
													<s:property value="caseName" />
												</h6>
												<p class="owner">
													<s:if test="isRelated==1">
													串并案系列名称：<span class="video-cate"><s:property
																value="series" /> </span>
													</s:if>
													<s:else>
										未串并
									</s:else>
												</p>
											</div>
										</li>
									</ul>
								</div>
							</s:iterator>

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

										<img id="myimage1" src="${injurycase.imageCase}"
											class="img-responsive thumbnail" width="200px"
											height="180px;" alt="暂无案件图片" />

									</div>
									<div class="col-2">
										<label class="form-label text-r">
											串并案系列名称：
										</label>
									</div>
									<div class="col-4">
										<s:textfield name="injurycase.series"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"
											readonly="true"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											是否串并案：
										</label>
									</div>
									<div class="col-4">
										<s:if test="injurycase.sisRelated==1">
											<s:textfield value="是" cssClass="input-text radius size-M "
												cssStyle="width: 200px;" readonly="true"></s:textfield>
										</s:if>
										<s:else>
											<s:textfield value="否" cssClass="input-text radius size-M "
												cssStyle="width: 200px;" readonly="true"></s:textfield>
										</s:else>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案件分类：
										</label>
									</div>
									<div class="col-4">
										<s:if test="injurycase.itype==1">
											<s:textfield value="刑事案件"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;" readonly="true"></s:textfield>
										</s:if>
										<s:if test="injurycase.itype==2">
											<s:textfield value="重伤案件"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;" readonly="true"></s:textfield>
										</s:if>
										<s:if test="injurycase.itype==3">
											<s:textfield value="团伙系列案件"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;" readonly="true"></s:textfield>
										</s:if>
										<s:if test="injurycase.itype==4">
											<s:textfield value="行政案件"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;" readonly="true"></s:textfield>
										</s:if>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											案件编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="caseNumber" name="injurycase.caseNumber"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"
											readonly="true"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案件名称：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="caseName" name="injurycase.caseName"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"
											readonly="true"></s:textfield>
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
										cssClass="input-text radius size-M " cssStyle="width: 200px;"
										readonly="true"></s:textfield>
								</div>
								<div class="col-2">
									<label class="form-label text-r">
										案发时间：
									</label>
								</div>
								<div class="col-4">
									<s:textfield name="injurycase.startTime"
										cssClass="input-text radius size-M " cssStyle="width: 200px;"
										readonly="true"></s:textfield>
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
										cssClass="input-text radius size-M " cssStyle="width: 200px;"
										readonly="true"></s:textfield>
								</div>
								<div class="col-2">
									<label class="form-label text-r">
										联系电话：
									</label>
								</div>
								<div class="col-4">
									<s:textfield id="telphone" name="injurycase.telphone"
										cssClass="input-text radius size-M " cssStyle="width: 200px;"
										readonly="true"></s:textfield>
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
										id="input9" cssStyle="width: 100%; height: 80px; float: left;"
										placeholder="简要案情" readonly="true"></s:textarea>
								</div>
							</div>
							<script type="text/javascript">

$(function(){
$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0");
});

$(function(){
$.Huitab0511("#tab_demo0511 .tabBar0511 span","#tab_demo0511 .tabCon0511","current","click","0");
});


/*部门流转*/
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
	</body>
</html>