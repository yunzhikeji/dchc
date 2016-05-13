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
			href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/2.1/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<title>串并案</title>
	</head>
	<body>
		<div class="pd-20">
			<div style="width: 100%">
				<div class="row cl">
					<div class="col-12 mb-10 c-primary f-16"
						style="border-bottom: solid 2px #2DABF7">
						案件视频信息
					</div>
					<div style="width: 1800px; overflow: hidden">
						<div class="index-slider-content clearfix">
							<object type="application/x-shockwave-flash"
								data="http://s9.pdim.gs/static/09d98839c42a00d2.swf"
								width="1000" height="562" id="swf_play"
								style="visibility: visible;">
								<param name="allowFullScreen" value="true">
								<param name="wMode" value="Opaque">
								<param name="allowScriptAccess" value="always">
								<param name="allowFullScreenInteractive" value="true">
								<param name="bgColor" value="#000">
								<param name="flashvars"
									value="resource=http://s9.pdim.gs/static/09d98839c42a00d2.swf&amp;width=1000&amp;height=562&amp;sign=true&amp;videoId=8e1fb0264e0ac055613837177051e1b0&amp;roomUrl=http://www.panda.tv/357885&amp;plflag=2_3&amp;watermark_switch=2&amp;watermark_loc=1&amp;flashId=swf_play">
							</object>
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
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
													说

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
														<div class="vtime">
															<i class="bg"></i><em class="di">1:02:20</em>
														</div>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>

												</li>
											</ul>
										</div>
									</div>
									<div class="tabCon0511">
										<div class="ugc_rec" id="secUGC"
											style="height: 550px; overflow: auto">
											<ul id="tjptList">
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
												<li class="pack" index="0" data-stat-role="ck"
													data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
													<div class="pic">
														<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
															title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
																src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
														</a>
													</div>
													<div class="txt">
														<h6 class="caption">
															<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
																href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
																class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
														</h6>
														<p class="owner">
															自频道：
															<a href="/home/_414378900" target="_blank">小土豆414378900</a>
														</p>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>


							</div>


						</div>
					</div>

					<div class="col-12" style="padding-top: 10px; padding-bottom: 10px">
						<button type="submit" class="btn btn-primary radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6df;</i> 编辑
						</button>
						&nbsp;
						<button type="submit" class="btn btn-primary radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe642;</i> 上传
						</button>
						&nbsp;
						<button type="submit" class="btn btn-primary radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe641;</i> 下载
						</button>
						&nbsp;
						<button type="submit" class="btn btn-success radius size-L" id=""
							name="">
							<i class="iconfont">&#xe601;</i> 放大
						</button>
						&nbsp;
						<button type="submit" class="btn btn-success radius size-L" id=""
							name="">
							<i class="iconfont">&#xe600;</i> 缩小
						</button>
						&nbsp;
						<button type="submit" class="btn btn-success radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6d4;</i>
						</button>
						&nbsp;
						<button type="submit" class="btn btn-success radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6d7;</i>
						</button>
						&nbsp;
						<button type="submit" class="btn btn-success radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6d6;</i>
						</button>
						&nbsp;
						<button type="submit" class="btn btn-success radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6d5;</i>
						</button>
						&nbsp;
						<button type="submit" class="btn btn-primary radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe685;</i> 截图
						</button>
						&nbsp;
						<button type="submit" class="btn btn-primary radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6aa;</i> 分享
						</button>
						&nbsp;
						<button type="submit" class="btn btn-danger radius size-L" id=""
							name="">
							<i class="Hui-iconfont">&#xe6e2;</i> 删除
						</button>
					</div>
					<div class="col-12 mb-10 c-primary f-16"
						style="border-bottom: solid 2px #ddd; color: #999">
						串并案
					</div>
					<div class="col-12" style="padding-top: 10px; padding-bottom: 10px">
						<div style="padding-bottom: 20px">
							<label class="text-r">
								关键字：
							</label>
							<input type="text" class="input-text radius size-M "
								style="width: 500px">
							<button type="submit"
								class="btn btn-primary radius size-MINI ml-5" id="" name=""
								title="导入">
								确定
							</button>
							<button type="submit"
								class="btn btn-warning radius size-MINI ml-5" id="" name=""
								title="导入">
								串并案
							</button>
						</div>


						<div style="width: 13%; float: left">

							<ul id="tjptList">
								<input type="checkbox" class="check-box" id="checkbox-1"
									style="z-index: 99">
								<li class="pack" index="0" data-stat-role="ck"
									data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
									<div class="pic">
										<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
											title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
												src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
										</a>
										<div class="vtime" style="float: right; margin-bottom: 42px">
											<i class="bg"></i><em class="di">1:02:20</em>
										</div>
									</div>
									<div class="txt">
										<h6 class="caption">
											<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
												href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
												class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
										</h6>
										<p class="owner">
											自频道：
											<a href="/home/_414378900" target="_blank">小土豆414378900</a>
										</p>
									</div>

								</li>
							</ul>
						</div>
						<div style="width: 13%; float: left">

							<ul id="tjptList">
								<input type="checkbox" class="check-box" id="checkbox-1"
									style="z-index: 99">
								<li class="pack" index="0" data-stat-role="ck"
									data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
									<div class="pic">
										<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
											title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
												src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
										</a>
										<div class="vtime" style="float: right; margin-bottom: 42px">
											<i class="bg"></i><em class="di">1:02:20</em>
										</div>
									</div>
									<div class="txt">
										<h6 class="caption">
											<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
												href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
												class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
										</h6>
										<p class="owner">
											自频道：
											<a href="/home/_414378900" target="_blank">小土豆414378900</a>
										</p>
									</div>

								</li>
							</ul>
						</div>
					</div>
					<div class="col-12 mb-10 c-primary f-16"
						style="border-bottom: solid 2px #ddd; color: #999">
						已串并案
					</div>
					<div class="col-12" style="padding-top: 10px; padding-bottom: 10px">
						<div style="width: 13%; float: left">

							<ul id="tjptList">
								<li class="pack" index="0" data-stat-role="ck"
									data-stat-href="http://www.tudou.com/programs/view/J1czfHrgg-0/">
									<div class="pic">
										<a href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
											title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊" class="link"><img
												src="http://g2.tdimg.com/f5e448863e1c8042d0f3ba1909805dc9/t_2.jpg">
										</a>
										<div class="vtime" style="float: right; margin-bottom: 42px">
											<i class="bg"></i><em class="di">1:02:20</em>
										</div>
									</div>
									<div class="txt">
										<h6 class="caption">
											<a title="邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊"
												href="http://www.tudou.com/programs/view/J1czfHrgg-0/"
												class="link">邵东跳跳乐中老年第十套健身操 2721919 邵东浪漫花屋 影尚传媒黄磊</a>
										</h6>
										<p class="owner">
											自频道：
											<a href="/home/_414378900" target="_blank">小土豆414378900</a>
										</p>
									</div>

								</li>
							</ul>
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
										cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"></s:textfield>
								</div>
								<div class="col-2">
									<label class="form-label text-r">
										案件名称：
									</label>
								</div>
								<div class="col-4">
									<s:textfield id="caseName" name="injurycase.caseName"
										cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"></s:textfield>
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
									cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"></s:textfield>
							</div>
							<div class="col-2">
								<label class="form-label text-r">
									案发时间：
								</label>
							</div>
							<div class="col-4">
									<s:textfield name="injurycase.startTime"
									cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"></s:textfield>
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
									cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"></s:textfield>
							</div>
							<div class="col-2">
								<label class="form-label text-r">
									联系电话：
								</label>
							</div>
							<div class="col-4">
								<s:textfield id="telphone" name="injurycase.telphone"
									cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"></s:textfield>
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
									placeholder="简要案情" readonly="true"></s:textfield>
							</div>
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