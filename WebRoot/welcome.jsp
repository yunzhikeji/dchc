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
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<title>我的桌面</title>
	</head>
	<body>

		<div class="pd-20">
			<div class="Huialert Huialert-info">
				<i class="icon-remove f-r" style="cursor: pointer;">关闭</i>
				<p>
					欢迎登录 德城公安-合成作战平台 您上次登录时间：${session.userRoleo.beforeLoginTime}
				</p>

				<div style="width: 100%; margin-top: 15px;">
					<div class="BreakingNewsController easing" id="breakingnews">
						<div class="bn-title"></div>
						<ul id="abc">
							<s:iterator value="pnotices" var="pnotice" status="status">
								<li>
									<a href="pnoticeAction!view?id=<s:property value="id" />">【<s:property
											value="releaseTime" />】<s:property value="title" /> </a>
								</li>
							</s:iterator>
						</ul>
						<div class="bn-arrows">
							<span class="bn-arrows-left"></span><span class="bn-arrows-right"></span>
						</div>
					</div>
				</div>
			</div>

			<div class="row cl">
				<div class="col-6" style="padding: 5px;">
					<div class="tabBarbox">
						<div id="tab_daiban" class="HuiTab">
							<div class="tabBar cl">
								<span>治安人员</span><span>刑事人员</span><span>其他人员</span><span>线索管理</span><span>案件管理</span>
							</div>


							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<table class="table table-border table-bg table-bordered radius"
									style="margin: 1%; width: 98%">
									<thead class="text-c">
										<tr class="active">
											<th width="20%">
												事项名称
											</th>
											<th style="color: red">
												未办理
											</th>
											<th>
												在办理
											</th>
											<th>
												已完结
											</th>
											<th>
												总计
											</th>
										</tr>
									</thead>
									<tr class="text-c">
										<td>
											赌博人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(0).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(0).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(0).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(0).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											涉恶人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(1).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(1).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(1).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(1).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											涉黄人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(2).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(2).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(2).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(2).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											食药环人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(3).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(3).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(3).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(3).totalNumber"/>
										</td>
									</tr>

								</table>

							</div>

							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<table class="table table-border table-bg table-bordered radius"
									style="margin: 1%; width: 98%">
									<thead class="text-c">
										<tr class="active">
											<th width="20%">
												事项名称
											</th>
											<th style="color: red">
												未办理
											</th>
											<th>
												在办理
											</th>
											<th>
												已完结
											</th>
											<th>
												总计
											</th>
										</tr>
									</thead>
									<tr class="text-c">
										<td>
											涉毒人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(4).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(4).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(4).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(4).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											负案在逃人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											留置盘问人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(5).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(5).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(5).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(5).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											侵财人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(8).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											刑事传唤人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(7).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(7).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(7).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(7).totalNumber"/>
										</td>
									</tr>
								</table>
							</div>

							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<table class="table table-border table-bg table-bordered radius"
									style="margin: 1%; width: 98%">
									<thead class="text-c">
										<tr class="active">
											<th width="20%">
												事项名称
											</th>
											<th style="color: red">
												未办理
											</th>
											<th>
												在办理
											</th>
											<th>
												已完结
											</th>
											<th>
												总计
											</th>
										</tr>
									</thead>
									<tr class="text-c">
										<td>
											失踪人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(10).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(10).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(10).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(10).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											维稳人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(9).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(9).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(9).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(9).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											侵财人员分析
										</td>
										<td>
											<s:property value="personNumberVOs.get(11).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(11).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(11).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(11).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											技术比中人员
										</td>
										<td>
											<s:property value="personNumberVOs.get(12).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(12).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(12).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(12).totalNumber"/>
										</td>
									</tr>
								</table>
							</div>

							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<table class="table table-border table-bg table-bordered radius"
									style="margin: 1%; width: 98%">
									<thead class="text-c">
										<tr class="active">
											<th width="20%">
												事项名称
											</th>
											<th style="color: red">
												未办理
											</th>
											<th>
												在办理
											</th>
											<th>
												已完结
											</th>
											<th>
												总计
											</th>
										</tr>
									</thead>
									<tr class="text-c">
										<td>
											普通线索
										</td>
										<td>
											<s:property value="personNumberVOs.get(13).number1"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(13).number2"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(13).number3"/>
										</td>
										<td>
											<s:property value="personNumberVOs.get(13).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											刑侦线索
										</td>
										<td>
											<s:property value="clueNumberVOs.get(0).number1"/>
										</td>
										<td>
											<s:property value="clueNumberVOs.get(0).number2"/>
										</td>
										<td>
											<s:property value="clueNumberVOs.get(0).number3"/>
										</td>
										<td>
											<s:property value="clueNumberVOs.get(0).totalNumber"/>
										</td>
									</tr>
								</table>
							</div>


							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<table class="table table-border table-bg table-bordered radius"
									style="margin: 1%; width: 98%">
									<thead class="text-c">
										<tr class="active">
											<th width="20%">
												事项名称
											</th>
											<th style="color: red">
												未办理
											</th>
											<th>
												在办理
											</th>
											<th>
												已完结
											</th>
											<th>
												总计
											</th>
										</tr>
									</thead>
									<tr class="text-c">
										<td>
											一般案件
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(0).number1"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(0).number2"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(0).number3"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(0).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											团伙系列案件
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(2).number1"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(2).number2"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(2).number3"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(2).totalNumber"/>
										</td>
									</tr>
									<tr class="text-c">
										<td>
											重伤案件
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(1).number1"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(1).number2"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(1).number3"/>
										</td>
										<td>
											<s:property value="injurycaseNumberVOs.get(1).totalNumber"/>
										</td>
									</tr>
								</table>
							</div>

						</div>
					</div>

				</div>
				<div class="col-6" style="padding: 5px;">
					<div class="tabBarbox">
						<div id="tab_yanpan" class="HuiTab">
							<div class="tabBar cl">
								<span>测试机构1</span><span>测试机构2</span>
							</div>
							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<div id="container" style="min-width: 450px; height: 230px"></div>
							</div>
							<div class="tabCon">
								<div class="tit">
									事项办理情况统计表
									<span class="label label-warning radius f-r "></span>
								</div>
								<div id="container2" style="min-width: 450px; height: 230px"></div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="row cl">
				<div class="col-6" style="padding: 5px;">
					<div class="panel panel-default">
						<div class="panel-header">
							成功案例
						</div>
						<div class="panel-body">
							<ul class="tlist">
								<s:iterator value="successexamples" var="successexample"
									status="status">
									<li class="box-1">
										<a
											href="successexampleAction!view?id=<s:property value="id"/>"><time>[<s:property
												value="releaseTime" />]</time> <s:property value="title" /> </a>
									</li>
								</s:iterator>
							</ul>

						</div>
					</div>
				</div>
				<div class="col-6" style="padding: 5px;">
					<div class="panel panel-default">
						<div class="panel-header">
							疑难解答
						</div>
						<s:iterator value="troubleshootings" var="troubleshooting"
							status="status">
							<li class="box-1">
								<a
									href="troubleshootingAction!load?troubid=<s:property value="id"/>"><time>[<s:property
										value="releaseTime" />]</time> <s:property value="title" /> </a>
							</li>
						</s:iterator>
					</div>
				</div>
			</div>

			<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
			<script type="text/javascript"
				src="lib/Validform/5.3.2/Validform.min.js"></script>
			<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
			<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
			<script type="text/javascript"
				src="lib/Highcharts/4.1.7/js/highcharts.js"></script>
			<script type="text/javascript"
				src="lib/Highcharts/4.1.7/js/modules/exporting.js"></script>
			<script type="text/javascript" src="js/H-ui.js"></script>
			<script type="text/javascript" src="js/H-ui.admin.js"></script>
			<script type="text/javascript" src="js/breakingnews.js"></script>
			<script type="text/javascript">
$(function(){
$.Huitab("#tab_daiban .tabBar span","#tab_daiban .tabCon","current","click","1");

$.Huitab("#tab_yanpan .tabBar span","#tab_yanpan .tabCon","current","click","1");
});
</script>
			<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '已完结研判信息合计'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '一月',
                '二月',
                '三月',
                '四月',
                '五月',
                '六月',
                '七月',
                '八月',
                '九月',
                '十月',
                '十一月',
                '十二月'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '数量(件)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '人员研判',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }, {
            name: '线索研判',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

        }, {
            name: '案件研判',
            data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]

        }]
    });
    
        $('#container2').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '已完结研判信息合计'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '一月',
                '二月',
                '三月',
                '四月',
                '五月',
                '六月',
                '七月',
                '八月',
                '九月',
                '十月',
                '十一月',
                '十二月'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '数量(件)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '人员研判',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }, {
            name: '线索研判',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

        }, {
            name: '案件研判',
            data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]

        }]
    });
    
    
    
    
    
    
});				




</script>
			<script>
$(function(){
	$('#breakingnews').BreakingNews({
		title: '通知公告',
		titlebgcolor: '#1D8FC5',
		linkhovercolor: '#1D8FC5',
		border: '1px solid #83D3E4',
		timer: 4000,
		effect: 'slide'	
	});

});
</script>
	</body>
</html>