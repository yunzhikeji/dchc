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
		<title>新增案件</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/ncss.css" rel="stylesheet" type="text/css" />
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

		<link rel="stylesheet"
			href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript">
			var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				showLine : false
			},
			callback: {
				onCheck: onCheck
			}
		};
		
		
		var setting2 = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false,
				showLine : false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: onCheck
			}
		};

		var zNodes_0 =[
		
			{ id:1000, pId:0, name:"预备手段", open:false},
			
			{ id:1100, pId:1000, name:"制造条件", open:false},
			{ id:1101, pId:1100, name:"事先潜伏"},
			{ id:1102, pId:1100, name:"配制钥匙"},
			{ id:1103, pId:1100, name:"窃取钥匙"},
			{ id:1104, pId:1100, name:"骗取钥匙"},
			{ id:1105, pId:1100, name:"探获密码"},
			{ id:1106, pId:1100, name:"预设圈套"},
			{ id:1107, pId:1100, name:"预启门窗"},
			{ id:1108, pId:1100, name:"事先踩点"},
			{ id:1109, pId:1100, name:"调离事主"},
			
			{ id:1110, pId:1100, name:"搭识同宿", open:false},
			{ id:1111, pId:1110, name:"密谋策划"},
			{ id:1112, pId:1110, name:"寻找同伙"},
			{ id:1113, pId:1110, name:"物色对象"},
			{ id:1114, pId:1110, name:"预租场所"},
			{ id:1115, pId:1110, name:"排除障碍"},
			{ id:1116, pId:1110, name:"筹集资金"},
			
			{ id:1300, pId:1000, name:"准备工具", open:false},
			{ id:1301, pId:1300, name:"预筹工具"},
			{ id:1302, pId:1300, name:"预筹运具"},
			{ id:1303, pId:1300, name:"预筹毒物"},
			{ id:1304, pId:1300, name:"预筹炸药"},
			
			{ id:1900, pId:1000, name:"其他预备手段", open:false},
			
			{ id:2000, pId:0, name:"侵入手段", open:false},
			
			{ id:2100, pId:2000, name:"从门侵入", open:false},
			{ id:2101, pId:2100, name:"撞门"},
			{ id:2102, pId:2100, name:"踹门"},
			{ id:2103, pId:2100, name:"撬门"},
			{ id:2104, pId:2100, name:"劈门"},
			{ id:2105, pId:2100, name:"砸门"},
			{ id:2106, pId:2100, name:"炸门"},
			{ id:2107, pId:2100, name:"割门"},
			{ id:2108, pId:2100, name:"卸门"},
			{ id:2109, pId:2100, name:"溜门"},
			{ id:2110, pId:2100, name:"顶门"},
			{ id:2111, pId:2100, name:"拽门"},
			{ id:2112, pId:2100, name:"翻门"},
			{ id:2113, pId:2100, name:"掰门"},
			{ id:2114, pId:2100, name:"撬撞开门"},
			{ id:2115, pId:2100, name:"撬踹开门"},
			{ id:2116, pId:2100, name:"拆砖开门"},
			{ id:2117, pId:2100, name:"刀削门框"},
			{ id:2118, pId:2100, name:"拨开门闩"},
			{ id:2119, pId:2100, name:"门上挖洞"},
			{ id:2120, pId:2100, name:"门上烧洞"},
			{ id:2121, pId:2100, name:"破门玻璃"},
			{ id:2122, pId:2100, name:"卸门玻璃"},
			{ id:2123, pId:2100, name:"窗口开门"},
			{ id:2124, pId:2100, name:"门缝钻入"},
			{ id:2125, pId:2100, name:"尾随入门"},
			{ id:2126, pId:2100, name:"骗开房门"},
			
			{ id:2200, pId:2000, name:"从窗侵入", open:false},
			{ id:2201, pId:2200, name:"天窗钻入"},
			{ id:2202, pId:2200, name:"窗口钻入"},
			{ id:2203, pId:2200, name:"气窗钻入"},
			{ id:2204, pId:2200, name:"窗栅钻入"},
			{ id:2205, pId:2200, name:"破坏窗框"},
			{ id:2206, pId:2200, name:"破坏窗栅"},
			{ id:2207, pId:2200, name:"破护窗网"},
			{ id:2208, pId:2200, name:"破窗玻璃"},
			{ id:2209, pId:2200, name:"卸窗玻璃"},
			{ id:2210, pId:2200, name:"拆砖开窗"},
			{ id:2211, pId:2200, name:"勾窗插销"},
			{ id:2212, pId:2200, name:"破窗插销"},
			{ id:2213, pId:2200, name:"拨钩气窗"},
			
			{ id:2300, pId:2000, name:"攀登侵入", open:false},
			{ id:2301, pId:2300, name:"登物攀爬"},
			{ id:2302, pId:2300, name:"搬物攀爬"},
			{ id:2303, pId:2300, name:"绳索攀爬"},
			{ id:2304, pId:2300, name:"攀爬阳台"},
			{ id:2305, pId:2300, name:"攀脚手架"},
			{ id:2306, pId:2300, name:"登肩越墙"},
			{ id:2307, pId:2300, name:"攀爬越墙"},
			{ id:2308, pId:2300, name:"攀爬跨墙"},
			{ id:2309, pId:2300, name:"攀登倒挂"},
			
			{ id:2400, pId:2000, name:"洞口侵入", open:false},
			{ id:2401, pId:2400, name:"钻洞入室"},
			{ id:2402, pId:2400, name:"拆钻篱笆"},
			{ id:2403, pId:2400, name:"地下挖洞"},
			{ id:2404, pId:2400, name:"墙上挖洞"},
			{ id:2405, pId:2400, name:"房顶开洞"},
			{ id:2406, pId:2400, name:"钻排风口"},
			{ id:2407, pId:2400, name:"天棚侵入"},
			
			{ id:2900, pId:2000, name:"其他侵入手段", open:false},
			
			{ id:3000, pId:0, name:"暴力胁迫手段", open:false},
			{ id:3100, pId:3000, name:"持枪"},
			
			{ id:3200, pId:3000, name:"持械", open:false},
			{ id:3210, pId:3200, name:"持锐器"},
			{ id:3220, pId:3200, name:"持钝器"},
			{ id:3230, pId:3200, name:"持绳"},
			{ id:3240, pId:3200, name:"持警械"},
			
			{ id:3300, pId:3000, name:"引爆", open:false},
			{ id:3301, pId:3300, name:"点火引爆"},
			{ id:3302, pId:3300, name:"投掷引爆"},
			{ id:3303, pId:3300, name:"拆封引爆"},
			{ id:3304, pId:3300, name:"定时引爆"},
			{ id:3305, pId:3300, name:"遥控引爆"},
			{ id:3306, pId:3300, name:"拉线引爆"},
			{ id:3307, pId:3300, name:"通电引爆"},
			{ id:3308, pId:3300, name:"压力引爆"},
			
			{ id:3400, pId:3000, name:"胁迫", open:false},
			{ id:3401, pId:3400, name:"言语胁迫"},
			{ id:3402, pId:3400, name:"书信胁迫"},
			{ id:3403, pId:3400, name:"露械胁迫"},
			{ id:3404, pId:3400, name:"供毒胁迫"},
			{ id:3405, pId:3400, name:"仗势胁迫"},
			{ id:3406, pId:3400, name:"引爆胁迫"},
			{ id:3407, pId:3400, name:"言敲诈胁迫"},
			
			{ id:3500, pId:3000, name:"施暴", open:false},
			{ id:3501, pId:3500, name:"踢打"},
			{ id:3502, pId:3500, name:"扼颈"},
			{ id:3503, pId:3500, name:"勒颈"},
			{ id:3504, pId:3500, name:"搜身"},
			{ id:3505, pId:3500, name:"剪拉"},
			{ id:3506, pId:3500, name:"扭膀"},
			{ id:3507, pId:3500, name:"捆绑"},
			{ id:3508, pId:3500, name:"捂堵"},
			{ id:3509, pId:3500, name:"封堵"},
			{ id:3510, pId:3500, name:"烧烫"},
			{ id:3511, pId:3500, name:"焚烧"},
			{ id:3512, pId:3500, name:"牙咬"},
			{ id:3513, pId:3500, name:"麻醉"},
			{ id:3514, pId:3500, name:"撞击"},
			{ id:3515, pId:3500, name:"注射"},
			{ id:3516, pId:3500, name:"电击"},
			{ id:3517, pId:3500, name:"撒灰"},
			{ id:3518, pId:3500, name:"高处推下"},
			{ id:3519, pId:3500, name:"推人下水"},
			{ id:3520, pId:3500, name:"施放毒气"},
			{ id:3521, pId:3500, name:"释放动物"},
			{ id:3522, pId:3500, name:"投腐蚀剂"},
			
			{ id:3600, pId:3000, name:"劫持", open:false},
			{ id:3601, pId:3600, name:"劫机"},
			{ id:3611, pId:3600, name:"劫船"},
			{ id:3621, pId:3600, name:"劫车"},
			{ id:3631, pId:3600, name:"劫持人质"},
			
			
			{ id:3900, pId:3000, name:"其他暴力胁迫手段", open:false},
			
			{ id:4000, pId:0, name:"窃取手段", open:false},
			
			{ id:4100, pId:4000, name:"破锁开锁", open:false},
			
			{ id:4101, pId:4100, name:"硬物击锁"},
			{ id:4102, pId:4100, name:"钳断锁梁"},
			{ id:4103, pId:4100, name:"锯锉锁梁"},
			{ id:4104, pId:4100, name:"拨弹开锁"},
			{ id:4105, pId:4100, name:"撬扭挂锁"},
			{ id:4106, pId:4100, name:"卸锁弹子"},
			{ id:4107, pId:4100, name:"破锁搭扣"},
			{ id:4108, pId:4100, name:"插片开锁"},
			{ id:4109, pId:4100, name:"钻锁芯子"},
			{ id:4110, pId:4100, name:"用具钩锁"},
			{ id:4111, pId:4100, name:"套绳拉锁"},
			{ id:4112, pId:4100, name:"解密开锁"},
			{ id:4113, pId:4100, name:"钥匙开锁"},
			{ id:4114, pId:4100, name:"腐剂开锁"},
			
			{ id:4200, pId:4000, name:"破盗金柜", open:false},
			{ id:4201, pId:4200, name:"撬金柜门"},
			{ id:4202, pId:4200, name:"金柜挖洞"},
			{ id:4203, pId:4200, name:"锯柜铰链"},
			{ id:4204, pId:4200, name:"焊割金柜"},
			{ id:4205, pId:4200, name:"砸开金柜"},
			{ id:4206, pId:4200, name:"剥剪柜皮"},
			{ id:4207, pId:4200, name:"炸开金柜"},
			{ id:4208, pId:4200, name:"钻柜锁芯"},
			{ id:4209, pId:4200, name:"挤顶柜门"},
			{ id:4210, pId:4200, name:"钻眼拨盘"},
			{ id:4211, pId:4200, name:"拉盘开锁"},
			{ id:4280, pId:4200, name:"搬走金柜"},
			
			{ id:4300, pId:4000, name:"破橱、箱、桌", open:false},
			{ id:4301, pId:4300, name:"破坏板面"},
			{ id:4302, pId:4300, name:"扩缝窃取"},
			
			{ id:4400, pId:4000, name:"窃包", open:false},
			{ id:4401, pId:4400, name:"拎包"},
			{ id:4402, pId:4400, name:"拉包"},
			{ id:4403, pId:4400, name:"割包"},
			{ id:4404, pId:4400, name:"调包"},
			{ id:4405, pId:4400, name:"掏包"},
			
			{ id:4500, pId:4000, name:"车船作案", open:false},
			{ id:4501, pId:4500, name:"割蓬作案"},
			{ id:4502, pId:4500, name:"运行抛物"},
			{ id:4503, pId:4500, name:"撬船舱板"},
			{ id:4504, pId:4500, name:"破包掏心"},
			{ id:4505, pId:4500, name:"运行勾物"},
			{ id:4506, pId:4500, name:"易地转运"},
			{ id:4507, pId:4500, name:"车窗窃取"},
			{ id:4508, pId:4500, name:"拉开车门"},
			{ id:4509, pId:4500, name:"破车玻璃"},
			{ id:4510, pId:4500, name:"破车门"},
			{ id:4511, pId:4500, name:"勾拉车门"},
			{ id:4512, pId:4500, name:"拆盗设备"},
			{ id:4513, pId:4500, name:"在车底挖洞"},
			
			{ id:4600, pId:4000, name:"窃车", open:false},
			{ id:4601, pId:4600, name:"拎打盗车"},
			{ id:4602, pId:4600, name:"断车锁链"},
			{ id:4603, pId:4600, name:"撬砸车锁"},
			{ id:4604, pId:4600, name:"搭线发车"},
			{ id:4605, pId:4600, name:"钥匙开车"},
			{ id:4606, pId:4600, name:"破车保险"},
			{ id:4607, pId:4600, name:"换锁开车"},
			{ id:4608, pId:4600, name:"拖运盗车"},
			{ id:4609, pId:4600, name:"推车盗车"},
			
			{ id:4900, pId:4000, name:"其他窃取手段", open:false},
			{ id:4901, pId:4900, name:"挑勾财物"},
			{ id:4902, pId:4900, name:"窗口窃取"},
			{ id:4903, pId:4900, name:"柜台窃物"},
			{ id:4904, pId:4900, name:"柜内窃款"},
			{ id:4905, pId:4900, name:"取寄存物"},
			{ id:4906, pId:4900, name:"偷晾晒物"},
			{ id:4907, pId:4900, name:"顺手牵羊"},
			{ id:4908, pId:4900, name:"监守自盗"},
			{ id:4909, pId:4900, name:"盗露天物"},
			{ id:4910, pId:4900, name:"盗码并机"},
			{ id:4911, pId:4900, name:"掘墓盗物"},
			{ id:4912, pId:4900, name:"盗蚌取珠"},
			{ id:4913, pId:4900, name:"钻管窃取"},
			{ id:4914, pId:4900, name:"杀蓄盗皮"},
			
			{ id:5000, pId:0, name:"欺诈手段", open:false},
			
			{ id:5100, pId:5000, name:"冒充身份", open:false},
			{ id:5101, pId:5100, name:"冒充外国人"},
			{ id:5102, pId:5100, name:"冒充侨胞"},
			{ id:5103, pId:5100, name:"冒充名人"},
			{ id:5104, pId:5100, name:"冒充高知"},
			{ id:5105, pId:5100, name:"冒充高干"},
			{ id:5106, pId:5100, name:"冒充子女"},
			{ id:5107, pId:5100, name:"冒充军人"},
			{ id:5108, pId:5100, name:"冒充民警"},
			{ id:5109, pId:5100, name:"冒充司法人员"},
			{ id:5110, pId:5100, name:"冒充执勤人员"},
			{ id:5111, pId:5100, name:"冒充稽私人员"},
			{ id:5112, pId:5100, name:"冒充医护人员"},
			{ id:5113, pId:5100, name:"冒充税务人员"},
			{ id:5114, pId:5100, name:"冒充市管人员"},
			{ id:5115, pId:5100, name:"冒充服务人员"},
			{ id:5116, pId:5100, name:"冒充记者"},
			{ id:5117, pId:5100, name:"冒充亲友"},
			{ id:5118, pId:5100, name:"冒充经理"},
			
			{ id:5200, pId:5000, name:"冒用", open:false},
			{ id:5201, pId:5200, name:"冒用支票"},
			{ id:5202, pId:5200, name:"冒用署名"},
			{ id:5203, pId:5200, name:"冒用护照"},
			{ id:5204, pId:5200, name:"冒用执照"},
			{ id:5205, pId:5200, name:"冒用证件"},
			{ id:5206, pId:5200, name:"冒用牌照"},
			{ id:5207, pId:5200, name:"冒用信用卡"},
			{ id:5208, pId:5200, name:"冒用商标"},
			{ id:5209, pId:5200, name:"冒用专利"},
			
			{ id:5300, pId:5000, name:"假借", open:false},
			{ id:5301, pId:5300, name:"假借交友"},
			{ id:5302, pId:5300, name:"假借招工"},
			{ id:5303, pId:5300, name:"假借招生"},
			{ id:5305, pId:5300, name:"假调房屋"},
			{ id:5306, pId:5300, name:"假调工作"},
			{ id:5308, pId:5300, name:"假谈恋爱"},
			{ id:5310, pId:5300, name:"假帮就医"},
			{ id:5311, pId:5300, name:"假办托运"},
			{ id:5312, pId:5300, name:"假证租借"},
			{ id:5313, pId:5300, name:"假传书信"},
			{ id:5314, pId:5300, name:"假借换汇"},
			{ id:5315, pId:5300, name:"假代购销"},
			{ id:5316, pId:5300, name:"假借搭车"},
			{ id:5317, pId:5300, name:"假借搭船"},
			{ id:5318, pId:5300, name:"假借看货"},
			{ id:5319, pId:5300, name:"假借落难"},
			{ id:5320, pId:5300, name:"假借引资"},
			{ id:5321, pId:5300, name:"假借贷款"},
			{ id:5322, pId:5300, name:"假称同行"},
			{ id:5323, pId:5300, name:"订假合同"},
			{ id:5324, pId:5300, name:"假报出口"},
			
			{ id:5400, pId:5000, name:"以假充真", open:false},
			{ id:5401, pId:5400, name:"虚设机构"},
			{ id:5402, pId:5400, name:"虚构事实"},
			{ id:5403, pId:5400, name:"掺杂掺假"},
			{ id:5404, pId:5400, name:"真币掺假"},
			{ id:5405, pId:5400, name:"假币兑真"},
			{ id:5406, pId:5400, name:"假货充真"},
			{ id:5407, pId:5400, name:"以次充好"},
			{ id:5408, pId:5400, name:"以现充古"},
			{ id:5409, pId:5400, name:"以旧充新"},
			{ id:5410, pId:5400, name:"以假换真"},
			{ id:5411, pId:5400, name:"虚开发票"},
			{ id:5412, pId:5400, name:"虚假记录"},
			
			{ id:5500, pId:5000, name:"引诱", open:false},
			{ id:5501, pId:5500, name:"金钱引诱"},
			{ id:5502, pId:5500, name:"物质引诱"},
			{ id:5503, pId:5500, name:"淫物引诱"},
			{ id:5504, pId:5500, name:"色情引诱"},
			{ id:5505, pId:5500, name:"名誉引诱"},
			{ id:5506, pId:5500, name:"出国引诱"},
			{ id:5507, pId:5500, name:"获利引诱"},
			{ id:5508, pId:5500, name:"广告引诱"},
			{ id:5509, pId:5500, name:"敲边引诱"},
			{ id:5510, pId:5500, name:"旅游引诱"},
			{ id:5511, pId:5500, name:"灌酒引诱"},
			
			{ id:5600, pId:5000, name:"行骗", open:false},
			{ id:5601, pId:5600, name:"名借实骗"},
			{ id:5602, pId:5600, name:"加工行骗"},
			{ id:5603, pId:5600, name:"征聘行骗"},
			{ id:5604, pId:5600, name:"单据行骗"},
			{ id:5605, pId:5600, name:"支票行骗"},
			{ id:5606, pId:5600, name:"拆币行骗"},
			{ id:5607, pId:5600, name:"押物行骗"},
			{ id:5608, pId:5600, name:"借舍行骗"},
			{ id:5609, pId:5600, name:"设赌行骗"},
			{ id:5610, pId:5600, name:"献宝行骗"},
			{ id:5611, pId:5600, name:"调币行骗"},
			{ id:5612, pId:5600, name:"抛物行骗"},
			{ id:5613, pId:5600, name:"出租行骗"},
			{ id:5614, pId:5600, name:"征兵行骗"},
			{ id:5615, pId:5600, name:"迷信行骗"},
			{ id:5616, pId:5600, name:"治病行骗"},
			{ id:5617, pId:5600, name:"印章行骗"},
			
			{ id:5700, pId:5000, name:"伪造、变造", open:false},
			{ id:5701, pId:5700, name:"制版"},
			{ id:5702, pId:5700, name:"雕版"},
			{ id:5703, pId:5700, name:"描绘"},
			{ id:5704, pId:5700, name:"剪接"},
			{ id:5705, pId:5700, name:"铸造"},
			{ id:5706, pId:5700, name:"涂改"},
			{ id:5707, pId:5700, name:"挖补"},
			{ id:5708, pId:5700, name:"签名"},
			{ id:5709, pId:5700, name:"揭层"},
			{ id:5710, pId:5700, name:"复印"},
			{ id:5711, pId:5700, name:"打印"},
			{ id:5712, pId:5700, name:"铅印"},
			{ id:5713, pId:5700, name:"冲改"},
			{ id:5714, pId:5700, name:"翻拍"},
			{ id:5715, pId:5700, name:"翻录"},
			{ id:5716, pId:5700, name:"复写"},
			{ id:5717, pId:5700, name:"书写"},
			{ id:5718, pId:5700, name:"电脑伪造"},
			
			
			{ id:5900, pId:5000, name:"其他诈骗手段", open:false},
			
			
			{ id:6000, pId:0, name:"妨害手段", open:false},
			
			{ id:6100, pId:6000, name:"侮辱", open:false},
			{ id:6101, pId:6100, name:"调戏"},
			{ id:6102, pId:6100, name:"猥亵"},
			{ id:6103, pId:6100, name:"戳胸"},
			{ id:6104, pId:6100, name:"戳臀"},
			{ id:6105, pId:6100, name:"顶臀"},
			{ id:6106, pId:6100, name:"窥视"},
			{ id:6107, pId:6100, name:"追堵"},
			{ id:6108, pId:6100, name:"剪割头发"},
			{ id:6109, pId:6100, name:"书信侮辱"},
			{ id:6110, pId:6100, name:"电话侮辱"},
			{ id:6111, pId:6100, name:"裸体露阴"},
			{ id:6112, pId:6100, name:"撕剥衣裤"},
			
			{ id:6200, pId:6000, name:"寻衅滋事", open:false},
			{ id:6202, pId:6200, name:"无理取闹"},
			{ id:6203, pId:6200, name:"行凶伤人"},
			{ id:6204, pId:6200, name:"强拿硬要"},
			{ id:6205, pId:6200, name:"涂抹污物"},
			
			{ id:6300, pId:6000, name:"淫乱", open:false},
			{ id:6301, pId:6300, name:"嫖宿"},
			{ id:6302, pId:6300, name:"同性恋"},
			{ id:6303, pId:6300, name:"色情服务"},
			{ id:6304, pId:6300, name:"交换对象"},
			
			{ id:6400, pId:6000, name:"干扰", open:false},
			{ id:6401, pId:6400, name:"非法制造"},
			{ id:6402, pId:6400, name:"非法贩卖"},
			{ id:6403, pId:6400, name:"非法运输"},
			{ id:6404, pId:6400, name:"非法转移"},
			{ id:6405, pId:6400, name:"教唆"},
			{ id:6406, pId:6400, name:"提供条件"},
			{ id:6407, pId:6400, name:"泄露内幕"},
			{ id:6408, pId:6400, name:"通风报信"},
			{ id:6409, pId:6400, name:"提供伪证"},
			{ id:6410, pId:6400, name:"编造谣言"},
			{ id:6412, pId:6400, name:"毁损财物"},
			{ id:6413, pId:6400, name:"围攻"},
			{ id:6414, pId:6400, name:"逃检抗拒"},
			{ id:6415, pId:6400, name:"高利转贷"},
			{ id:6416, pId:6400, name:"低售国资"},
			{ id:6417, pId:6400, name:"非法投寄"},
			{ id:6418, pId:6400, name:"非法张贴"},
			{ id:6419, pId:6400, name:"非法散发"},
			{ id:6420, pId:6400, name:"非法演讲"},
			{ id:6421, pId:6400, name:"非法呼喊"},
			{ id:6422, pId:6400, name:"私放"},
			{ id:6423, pId:6400, name:"非法传真"},
			{ id:6424, pId:6400, name:"非法递交"},
			{ id:6425, pId:6400, name:"非法删改"},
			{ id:6426, pId:6400, name:"非法播放"},
			{ id:6427, pId:6400, name:"非法电脑传播"},
			{ id:6428, pId:6400, name:"非法口头传播"},
			{ id:6429, pId:6400, name:"修改计算机功能"},
			{ id:6430, pId:6400, name:"删除计算机功能"},
			{ id:6431, pId:6400, name:"干扰计算机功能"},
			{ id:6432, pId:6400, name:"瞒报"},
			{ id:6433, pId:6400, name:"虚报"},
			{ id:6435, pId:6400, name:"闯关"},
			{ id:6436, pId:6400, name:"武装掩护"},
			{ id:6437, pId:6400, name:"卖汇"},
			{ id:6438, pId:6400, name:"避关"},
			{ id:6439, pId:6400, name:"非法存汇"},
			{ id:6440, pId:6400, name:"强迫交易"},
			{ id:6441, pId:6400, name:"逃避商检"},
			
			{ id:7000, pId:0, name:"伪装灭迹手段", open:false},
			{ id:7100, pId:7000, name:"伪装", open:false},
			{ id:7101, pId:7100, name:"男扮女装"},
			{ id:7102, pId:7100, name:"女扮男装"},
			{ id:7103, pId:7100, name:"化妆"},
			{ id:7104, pId:7100, name:"蒙面"},
			{ id:7105, pId:7100, name:"戴眼镜"},
			{ id:7106, pId:7100, name:"戴口罩"},
			{ id:7107, pId:7100, name:"戴手套"},
			{ id:7108, pId:7100, name:"手贴胶布"},
			{ id:7109, pId:7100, name:"鞋底包布"},
			{ id:7110, pId:7100, name:"换鞋"},
			{ id:7111, pId:7100, name:"套鞋"},
			{ id:7112, pId:7100, name:"穿袜包布"},
			{ id:7113, pId:7100, name:"用物铺地"},
			{ id:7114, pId:7100, name:"整容"},
			{ id:7115, pId:7100, name:"语言伪装"},
			
			{ id:7200, pId:7000, name:"毁灭痕迹", open:false},
			{ id:7201, pId:7200, name:"扫擦灭迹"},
			{ id:7202, pId:7200, name:"冲洗灭迹"},
			{ id:7203, pId:7200, name:"纵火灭迹"},
			{ id:7205, pId:7200, name:"碎尸"},
			{ id:7206, pId:7200, name:"移尸"},
			{ id:7207, pId:7200, name:"爆炸灭迹"},
			{ id:7208, pId:7200, name:"撒物灭迹"},
			
			{ id:7300, pId:7000, name:"夹带", open:false},
			{ id:7301, pId:7300, name:"人体夹带"},
			{ id:7302, pId:7300, name:"随身夹带"},
			{ id:7303, pId:7300, name:"动物夹带"},
			{ id:7304, pId:7300, name:"车辆夹带"},
			{ id:7305, pId:7300, name:"船只夹带"},
			{ id:7306, pId:7300, name:"包裹夹带"},
			{ id:7307, pId:7300, name:"飞机夹带"},

			
			{ id:7400, pId:7000, name:"藏匿", open:false},
			{ id:7401, pId:7400, name:"地下藏匿"},
			{ id:7402, pId:7400, name:"树林藏匿"},
			{ id:7403, pId:7400, name:"水下藏匿"},
			{ id:7404, pId:7400, name:"洞穴藏匿"},
			{ id:7405, pId:7400, name:"壁内藏匿"},
			
			{ id:7900, pId:7000, name:"其他伪装灭迹手段", open:false},
			{ id:8000, pId:0, name:"利用计算机手段", open:false},
			{ id:9000, pId:0, name:"其他作案手段", open:false},
			
		];
		
		
		
		var zNodes_1 =[
			{ id:10000, pId:0, name:"工具", open:false},
			{ id:10100, pId:10000, name:"刀", open:false},
			{ id:10101, pId:10100, name:"菜刀"},
			{ id:10102, pId:10100, name:"尖刀"},
			{ id:10103, pId:10100, name:"刮刀"},
			{ id:10104, pId:10100, name:"瓦刀"},
			{ id:10105, pId:10100, name:"电工刀"},
			{ id:10106, pId:10100, name:"西瓜刀"},
			{ id:10107, pId:10100, name:"砍柴刀"},
			{ id:10108, pId:10100, name:"美工刀"},
			{ id:10109, pId:10100, name:"手术刀"},
			{ id:10110, pId:10100, name:"刀片"},
			{ id:10111, pId:10100, name:"玻璃刀"},
			
			{ id:10200, pId:10000, name:"斧", open:false},
			{ id:10201, pId:10200, name:"消防斧"},
			{ id:10202, pId:10200, name:"木工斧"},
			
			{ id:10300, pId:10000, name:"剪", open:false},
			{ id:10301, pId:10300, name:"缝纫剪"},
			{ id:10302, pId:10300, name:"白铁剪"},
			{ id:10303, pId:10300, name:"手术剪"},
			

			{ id:10400, pId:10000, name:"钳",open:false},
			{ id:10401, pId:10400, name:"钢丝钳"},
			{ id:10402, pId:10400, name:"断线钳"},
			{ id:10403, pId:10400, name:"管子钳"},
			{ id:10404, pId:10400, name:"台虎钳"},
			
			{ id:10500, pId:10000, name:"钻",open:false},
			{ id:10501, pId:10500, name:"木工钻"},
			{ id:10502, pId:10500, name:"手摇钻"},
			{ id:10591, pId:10500, name:"手电钻"},
			{ id:10592, pId:10500, name:"冲击钻"},
			
			
			{ id:10600, pId:10000, name:"锉",open:false},
			
			{ id:10700, pId:10000, name:"旋具",open:false},
			
			{ id:10800, pId:10000, name:"扳手",open:false},
			
			{ id:10900, pId:10000, name:"锯",open:false},
			
			{ id:11000, pId:10000, name:"凿",open:false},
			
			{ id:11100, pId:10000, name:"锤",open:false},
			
			{ id:11200, pId:10000, name:"刨",open:false},
			
			{ id:11300, pId:10000, name:"焊割工具",open:false},
			
			{ id:11400, pId:10000, name:"起重工具",open:false},
			
			{ id:11500, pId:10000, name:"农用工具",open:false},
			{ id:11501, pId:11500, name:"锹"},
			{ id:11502, pId:11500, name:"镐"},
			{ id:11503, pId:11500, name:"镰"},
			{ id:11504, pId:11500, name:"锄"},
			{ id:11505, pId:11500, name:"叉"},
			{ id:11506, pId:11500, name:"扁担"},
			
			{ id:11600, pId:10000, name:"警用械具",open:false},
			{ id:11601, pId:11600, name:"手铐"},
			{ id:11602, pId:11600, name:"警棍"},
			{ id:11603, pId:11600, name:"警绳"},
			{ id:11604, pId:11600, name:"脚镣"},
			{ id:11605, pId:11600, name:"催泪弹"},
			{ id:11606, pId:11600, name:"高压水枪"},
			
			{ id:11700, pId:10000, name:"简易工具",open:false},
			{ id:11701, pId:11700, name:"勺"},
			{ id:11702, pId:11700, name:"钢钎"},
			{ id:11703, pId:11700, name:"铁棍"},
			{ id:11704, pId:11700, name:"木棒"},
			{ id:11705, pId:11700, name:"竹竿"},
			{ id:11706, pId:11700, name:"撬棒"},
			{ id:11707, pId:11700, name:"钥匙"},
			
			{ id:19900, pId:10000, name:"其他工具",open:false},
			
			{ id:20000, pId:0, name:"交通工具", open:false},
			{ id:20100, pId:20000, name:"普通汽车", open:false},
			{ id:20101, pId:20100, name:"大客车"},
			{ id:20102, pId:20100, name:"小客车"},
			{ id:20103, pId:20100, name:"大货车"},
			{ id:20104, pId:20100, name:"小货车"},
			{ id:20105, pId:20100, name:"吉普车"},
			{ id:20106, pId:20100, name:"轿车"},
			{ id:20107, pId:20100, name:"微型车"},
			
			
			{ id:20200, pId:20000, name:"专业汽车", open:false},
			{ id:20201, pId:20200, name:"运钞车"},
			{ id:20202, pId:20200, name:"救护车"},
			{ id:20203, pId:20200, name:"消防车"},
			{ id:20205, pId:20200, name:"警车"},
			{ id:20206, pId:20200, name:"军车"},
			{ id:20207, pId:20200, name:"油罐车"},
			{ id:20208, pId:20200, name:"吊车"},
			{ id:20209, pId:20200, name:"铲车"},
			{ id:20210, pId:20200, name:"搅拌车"},
			
			
			{ id:20300, pId:20000, name:"摩托车", open:false},
			{ id:20301, pId:20300, name:"两轮摩托车"},
			{ id:20302, pId:20300, name:"三轮摩托车"},
			
			{ id:20400, pId:20000, name:"助动车", open:false},
			
			{ id:20500, pId:20000, name:"农用车", open:false},
			{ id:20501, pId:20500, name:"大拖拉机"},
			{ id:20502, pId:20500, name:"小拖拉机"},
			{ id:20503, pId:20500, name:"手扶拖拉机"},
			{ id:20504, pId:20500, name:"三轮农运"},
			{ id:20505, pId:20500, name:"四轮农运"},
			{ id:20506, pId:20500, name:"农机车"},
			
			{ id:20600, pId:20000, name:"电车", open:false},
			
			{ id:20700, pId:20000, name:"人力车", open:false},
			{ id:20701, pId:20700, name:"自行车"},
			{ id:20702, pId:20700, name:"三轮车"},
			{ id:20703, pId:20700, name:"二轮车"},
			{ id:20704, pId:20700, name:"独轮车"},
			
			{ id:20800, pId:20000, name:"蓄力车", open:false},
			{ id:20801, pId:20800, name:"马车"},
			
			{ id:21000, pId:20000, name:"火车", open:false},
			
			{ id:29900, pId:20000, name:"其他交通工具", open:false},
			
			{ id:30000, pId:0, name:"化学毒物", open:false},
			{ id:30100, pId:30000, name:"氰化物", open:false},
			{ id:30200, pId:30000, name:"砷磷汞铅锡化合物", open:false},
			{ id:30300, pId:30000, name:"氟、氯化合物", open:false},
			{ id:30400, pId:30000, name:"硝基化合物", open:false},
			{ id:30500, pId:30000, name:"硫、硝、醋酸化合物", open:false},
			{ id:30600, pId:30000, name:"生物碱类", open:false},
			{ id:39900, pId:30000, name:"其他毒物", open:false},
			
			{ id:40000, pId:0, name:"爆炸物品", open:false},
			{ id:40100, pId:40000, name:"炸药", open:false},
			{ id:40101, pId:40100, name:"梯恩梯"},
			{ id:40102, pId:40100, name:"苦味酸"},
			{ id:40103, pId:40100, name:"铵梯炸药"},
			{ id:40104, pId:40100, name:"铵油炸药"},
			{ id:40105, pId:40100, name:"氯酸盐"},
			{ id:40106, pId:40100, name:"特屈尔"},
			{ id:40107, pId:40100, name:"黑索金"},
			{ id:40108, pId:40100, name:"硝化甘油"},
			{ id:40109, pId:40100, name:"硝化棉"},
			{ id:40110, pId:40100, name:"雷汞"},
			{ id:40111, pId:40100, name:"迭氮化铅"},
			{ id:40112, pId:40100, name:"黑火药"},
			
			{ id:40200, pId:40000, name:"起爆器材", open:false},
			{ id:40201, pId:40200, name:"火雷管"},
			{ id:40202, pId:40200, name:"电雷管", open:false},
			{ id:40203, pId:40200, name:"瞬发雷管"},
			{ id:40204, pId:40200, name:"延期雷管"},
			{ id:40205, pId:40200, name:"导火索"},
			{ id:40206, pId:40200, name:"导爆索"},
			{ id:40207, pId:40200, name:"定时装置"},
			
			{ id:49900, pId:40000, name:"其他爆炸物品", open:false},
			
			{ id:50000, pId:0, name:"工艺首饰及文物", open:false},
			{ id:50100, pId:50000, name:"工艺品", open:false},
			{ id:50116, pId:50100, name:"木雕"},
			{ id:50131, pId:50100, name:"瓷器"},
			{ id:50132, pId:50100, name:"陶器"},
			{ id:50133, pId:50100, name:"漆器"},
			{ id:50140, pId:50100, name:"塑器"},
			{ id:50150, pId:50100, name:"字画"},
			
			{ id:50200, pId:50000, name:"首饰品", open:false},
			{ id:50201, pId:50200, name:"项链"},
			{ id:50202, pId:50200, name:"手链"},
			{ id:50203, pId:50200, name:"足链"},
			{ id:50204, pId:50200, name:"耳环"},
			{ id:50205, pId:50200, name:"戒指"},
			{ id:50206, pId:50200, name:"手镯"},
			{ id:50207, pId:50200, name:"挂件"},
			{ id:50209, pId:50200, name:"发夹"},
			
			{ id:50300, pId:50000, name:"金银制品", open:false},
			{ id:50301, pId:50300, name:"金元宝"},
			{ id:50302, pId:50300, name:"银元宝"},
			{ id:50303, pId:50300, name:"金器"},
			{ id:50304, pId:50300, name:"银器"},
			
			{ id:50400, pId:50000, name:"文物", open:false},
			
			{ id:50500, pId:50000, name:"化石", open:false},
			
			{ id:50600, pId:50000, name:"纪念品", open:false},
			{ id:50601, pId:50600, name:"纪念币"},
			{ id:50602, pId:50600, name:"纪念章"},
			
			{ id:59900, pId:50000, name:"其他工艺首饰及文物", open:false},
			
			
			{ id:60000, pId:0, name:"家用电器", open:false},
			{ id:60100, pId:60000, name:"制冷取暖电器", open:false},
			
			{ id:60300, pId:60000, name:"清洁整理器具", open:false},
			
			{ id:60400, pId:60000, name:"厨房器具", open:false},
			
			{ id:60500, pId:60000, name:"美容保健器", open:false},
			
			{ id:60600, pId:60000, name:"试听器材", open:false},
			
			{ id:69900, pId:60000, name:"其他家用电器", open:false},
			
			{ id:70000, pId:0, name:"仪器仪表计量器材", open:false},
			{ id:70100, pId:70000, name:"仪表", open:false},
			
			{ id:70200, pId:70000, name:"光学仪器", open:false},
			
			{ id:70300, pId:70000, name:"钟表", open:false},
			
			{ id:70400, pId:70000, name:"计量器具", open:false},
			{ id:70401, pId:70400, name:"千分尺"},
			{ id:70402, pId:70400, name:"游标卡尺"},
			{ id:70403, pId:70400, name:"天平称"},
			{ id:70404, pId:70400, name:"电子称"},
			{ id:70405, pId:70400, name:"点钞机"},
			
			{ id:70500, pId:70000, name:"安保器材", open:false},
			{ id:70501, pId:70500, name:"报警器"},
			{ id:70502, pId:70500, name:"监控器"},
			
			{ id:70600, pId:70000, name:"仪器仪表计量器材附件", open:false},
			{ id:70601, pId:70600, name:"三脚架"},
			
			{ id:79900, pId:70000, name:"其他仪器仪表计量器材", open:false},
			
			{ id:80000, pId:0, name:"农副渔牧品动植物", open:false},
			{ id:80100, pId:80000, name:"粮食作物", open:false},
			{ id:80200, pId:80000, name:"制糖作物", open:false},
			{ id:80300, pId:80000, name:"油料作物", open:false},
			{ id:80400, pId:80000, name:"作物种子", open:false},
			{ id:80500, pId:80000, name:"蔬菜", open:false},
			{ id:80600, pId:80000, name:"食用菌", open:false},
			{ id:80700, pId:80000, name:"水果", open:false},
			{ id:80800, pId:80000, name:"鲜肉", open:false},
			{ id:80900, pId:80000, name:"鲜蛋", open:false},
			{ id:81000, pId:80000, name:"水产品", open:false},
			{ id:81200, pId:80000, name:"珍贵濒危野生动物制品", open:false},
			{ id:81400, pId:80000, name:"饲料", open:false},
			{ id:81600, pId:80000, name:"珍稀植物及其制品", open:false},
			{ id:89900, pId:80000, name:"其它农副渔牧品动植物", open:false},
			
			{ id:90000, pId:0, name:"食品烟酒", open:false},
			{ id:90100, pId:90000, name:"米面杂粮", open:false},
			{ id:90200, pId:90000, name:"食用油", open:false},
			{ id:90300, pId:90000, name:"调味品", open:false},
			{ id:90400, pId:90000, name:"肉蛋制品", open:false},
			{ id:90500, pId:90000, name:"水产制品", open:false},
			{ id:90600, pId:90000, name:"干果", open:false},
			{ id:90700, pId:90000, name:"糖", open:false},
			{ id:90800, pId:90000, name:"糕点", open:false},
			{ id:90900, pId:90000, name:"乳制品", open:false},
			{ id:91000, pId:90000, name:"饮料", open:false},
			{ id:91100, pId:90000, name:"茶叶", open:false},
			{ id:91200, pId:90000, name:"烟", open:false},
			{ id:91300, pId:90000, name:"酒", open:false},
			{ id:91400, pId:90000, name:"营养保健品", open:false},
			{ id:99900, pId:90000, name:"其他食品烟酒", open:false},
			
			{ id:100000, pId:0, name:"纺织品", open:false},
			{ id:100100, pId:100000, name:"纺织纤维品", open:false},
			{ id:100101, pId:100100, name:"棉花"},
			{ id:100102, pId:100100, name:"羊毛"},
			{ id:100103, pId:100100, name:"兔毛"},
			{ id:100104, pId:100100, name:"蚕丝"},
			{ id:100105, pId:100100, name:"制麻"},
			{ id:100106, pId:100100, name:"化学纤维"},
			
			{ id:100200, pId:100000, name:"针纺织品", open:false},
			{ id:100300, pId:100000, name:"床上用品", open:false},
			{ id:100400, pId:100000, name:"日用织物", open:false},
			{ id:109900, pId:100000, name:"其它纺织品", open:false},
			
			{ id:110000, pId:0, name:"服装鞋帽及穿带品", open:false},
			{ id:110100, pId:110000, name:"上衣", open:false},
			{ id:110200, pId:110000, name:"裤子", open:false},
			{ id:110300, pId:110000, name:"裙", open:false},
			{ id:110400, pId:110000, name:"套装", open:false},
			{ id:110500, pId:110000, name:"大衣", open:false},
			{ id:110600, pId:110000, name:"风雨衣", open:false},
			{ id:110700, pId:110000, name:"鞋", open:false},
			{ id:110701, pId:110700, name:"皮鞋"},
			{ id:110702, pId:110700, name:"布鞋"},
			{ id:110703, pId:110700, name:"塑料鞋"},
			{ id:110704, pId:110700, name:"合成草鞋"},
			{ id:110705, pId:110700, name:"橡胶鞋"},
			{ id:110706, pId:110700, name:"草鞋"},
			{ id:110750, pId:110700, name:"专用鞋"},
			
			{ id:110800, pId:110000, name:"帽类", open:false},
			{ id:110810, pId:110800, name:"生活帽"},
			{ id:110820, pId:110800, name:"运动帽"},
			{ id:110830, pId:110800, name:"工作帽"},
			
			{ id:111000, pId:110000, name:"穿戴品", open:false},
			{ id:111001, pId:111000, name:"口罩"},
			{ id:111002, pId:111000, name:"围巾"},
			{ id:111003, pId:111000, name:"领带"},
			{ id:111004, pId:111000, name:"腰带"},
			{ id:111005, pId:111000, name:"手套"},
			{ id:111006, pId:111000, name:"袜"},
			
			{ id:119900, pId:110000, name:"其它服装鞋帽及穿带品", open:false},
			
			{ id:120000, pId:0, name:"文教体育娱乐用品", open:false},
			{ id:120100, pId:120000, name:"文教用品", open:false},
			{ id:120101, pId:120100, name:"簿册"},
			{ id:120102, pId:120100, name:"钢笔"},
			{ id:120103, pId:120100, name:"圆珠笔"},
			{ id:120104, pId:120100, name:"书刊"},
			{ id:120105, pId:120100, name:"修正液"},
			{ id:120106, pId:120100, name:"标本"},
			
			{ id:120200, pId:120000, name:"文教器材", open:false},
			{ id:120300, pId:120000, name:"集邮品", open:false},
			{ id:120400, pId:120000, name:"音箱制品", open:false},
			{ id:120500, pId:120000, name:"体育用品", open:false},
			{ id:120600, pId:120000, name:"乐器", open:false},
			{ id:120700, pId:120000, name:"玩具", open:false},
			{ id:129900, pId:120000, name:"其他文教体育娱乐用品", open:false},
			
			
			{ id:130000, pId:0, name:"家具及日用杂品", open:false},
			{ id:130100, pId:130000, name:"家具", open:false},
			{ id:130101, pId:130100, name:"床"},
			{ id:130102, pId:130100, name:"桌"},
			{ id:130103, pId:130100, name:"凳椅"},
			{ id:130104, pId:130100, name:"沙发"},
			{ id:130105, pId:130100, name:"橱柜"},
			
			{ id:130200, pId:130000, name:"缝纫用具", open:false},
			{ id:130300, pId:130000, name:"箱包", open:false},
			{ id:130400, pId:130000, name:"日用器皿", open:false},
			{ id:130500, pId:130000, name:"日常用具", open:false},
			{ id:130502, pId:130500, name:"打火机"},
			{ id:130504, pId:130500, name:"净水器"},
			
			{ id:139900, pId:130000, name:"其他家具及日用杂品", open:false},
			
			{ id:140000, pId:0, name:"通用设备及器材", open:false},
			{ id:140100, pId:140000, name:"通用设备", open:false},
			{ id:140200, pId:140000, name:"电工器材", open:false},
			{ id:140300, pId:140000, name:"照明器材", open:false},
			{ id:140301, pId:140300, name:"手电筒"},
			{ id:149900, pId:140000, name:"其他通用设备及器材", open:false},
			
			{ id:150000, pId:0, name:"橡胶类", open:false},
			{ id:150100, pId:150000, name:"橡胶制品", open:false},
			{ id:150300, pId:150000, name:"橡塑材料", open:false},
			
			{ id:160000, pId:0, name:"建筑、装潢材料", open:false},
			{ id:160100, pId:160000, name:"建筑材料", open:false},
			{ id:160200, pId:160000, name:"装潢材料", open:false},
			{ id:169900, pId:160000, name:"其他建筑装璜金属材料", open:false},
			
			{ id:170000, pId:0, name:"煤、石油化工产品", open:false},
			{ id:170100, pId:170000, name:"易燃液体", open:false},
			{ id:170101, pId:170100, name:"汽油"},
			{ id:170102, pId:170100, name:"煤油"},
			{ id:170103, pId:170100, name:"柴油"},
			{ id:170104, pId:170100, name:"润滑油"},
			{ id:170105, pId:170100, name:"桐油"},
			{ id:170106, pId:170100, name:"松节油"},
			{ id:170107, pId:170100, name:"香蕉水"},
			{ id:170108, pId:170100, name:"甲醇"},
			{ id:170109, pId:170100, name:"乙醇"},
			{ id:170110, pId:170100, name:"丙酮"},
			
			{ id:170200, pId:170000, name:"易燃固体", open:false},
			{ id:170300, pId:170000, name:"易燃其他", open:false},
			{ id:170400, pId:170000, name:"腐蚀剂", open:false},
			{ id:170401, pId:170400, name:"硫酸"},
			{ id:170402, pId:170400, name:"硝酸"},
			{ id:170403, pId:170400, name:"盐酸"},
			{ id:170404, pId:170400, name:"烧碱"},
			
			{ id:170500, pId:170000, name:"涂颜染料", open:false},
			{ id:170501, pId:170500, name:"油漆"},
			{ id:170502, pId:170500, name:"油墨"},
			{ id:170503, pId:170500, name:"涂料"},
			{ id:170504, pId:170500, name:"粘合剂"},
			
			
			{ id:170600, pId:170000, name:"感光材料", open:false},
			{ id:170700, pId:170000, name:"日用化工品", open:false},
			{ id:179900, pId:170000, name:"其他煤、石油化工产品", open:false},
			
			
			{ id:180000, pId:0, name:"医药及医疗器械", open:false},
			{ id:180100, pId:180000, name:"西药", open:false},
			{ id:180200, pId:180000, name:"中药材", open:false},
			{ id:180300, pId:180000, name:"中成药", open:false},
			{ id:180500, pId:180000, name:"医疗器械", open:false},
			{ id:189900, pId:180000, name:"其他医药及医疗器械", open:false},
			
			
			{ id:190000, pId:0, name:"毒品", open:false},
			{ id:190100, pId:190000, name:"毒制品", open:false},
			{ id:190101, pId:190100, name:"海洛因"},
			{ id:190102, pId:190100, name:"鸦片"},
			{ id:190103, pId:190100, name:"大麻"},
			{ id:190104, pId:190100, name:"吗啡"},
			{ id:190105, pId:190100, name:"冰毒"},
			{ id:190106, pId:190100, name:"苯丙安类"},
			{ id:190107, pId:190100, name:"埃托啡"},
			{ id:190108, pId:190100, name:"咖啡因"},
			{ id:190109, pId:190100, name:"安钠咖"},
			{ id:190110, pId:190100, name:"杜冷丁"},
			{ id:190111, pId:190100, name:"可卡因"},
			
			{ id:190200, pId:190000, name:"制毒化学品", open:false},
			{ id:190201, pId:190200, name:"醋酸酐"},
			{ id:190202, pId:190200, name:"乙醚"},
			{ id:190203, pId:190200, name:"三氯甲烷"},
			{ id:190204, pId:190200, name:"麻黄素"},
			
			{ id:190300, pId:190000, name:"毒品原植物", open:false},
			{ id:190301, pId:190200, name:"罂粟草"},
			{ id:190302, pId:190200, name:"罂粟壳"},
			{ id:190320, pId:190200, name:"大麻植物"},
			
			{ id:199900, pId:190000, name:"其它毒品", open:false},
			
			{ id:200000, pId:0, name:"淫秽制品", open:false},
			{ id:200100, pId:200000, name:"淫秽书刊", open:false},
			{ id:200200, pId:200000, name:"淫秽音像制品", open:false},
			{ id:209900, pId:200000, name:"其它淫秽制品", open:false},
			
			{ id:210000, pId:0, name:"计算机器材元器件", open:false},
			{ id:210100, pId:210000, name:"计算机", open:false},
			{ id:219900, pId:210000, name:"其他计算机器材元器件", open:false},
			
			{ id:220000, pId:0, name:"通讯设备", open:false},
			{ id:220100, pId:220000, name:"通讯器材", open:false},
			{ id:220110, pId:220100, name:"传输设备"},
			{ id:220111, pId:220100, name:"传真机"},
			{ id:220120, pId:220100, name:"交换设备"},
			{ id:220130, pId:220100, name:"通讯终端设备"},
			{ id:220131, pId:220100, name:"对讲机"},
			{ id:220132, pId:220100, name:"移动电话"},
			{ id:220134, pId:220100, name:"电话"},
			
			
			{ id:229900, pId:220000, name:"其他计算机器材元器件", open:false},
			
			{ id:230000, pId:0, name:"货币", open:false},
			{ id:230156, pId:230000, name:"人民币", open:false},
			{ id:230250, pId:230000, name:"法国法郎", open:false},
			{ id:230280, pId:230000, name:"德国马克", open:false},
			{ id:230300, pId:230000, name:"德拉克马", open:false},
			{ id:230344, pId:230000, name:"香港元", open:false},
			{ id:230360, pId:230000, name:"卢比", open:false},
			{ id:230392, pId:230000, name:"日元", open:false},
			{ id:230408, pId:230000, name:"北朝鲜圆", open:false},
			{ id:230446, pId:230000, name:"澳门元", open:false},
			{ id:230764, pId:230000, name:"铢", open:false},
			{ id:230810, pId:230000, name:"罗斯卢布", open:false},
			{ id:230826, pId:230000, name:"英镑", open:false},
			{ id:230840, pId:230000, name:"美元", open:false},
			{ id:230910, pId:230000, name:"新台湾元", open:false},
			{ id:230952, pId:230000, name:"法朗", open:false},
			{ id:230954, pId:230000, name:"欧元", open:false},
			
			
			{ id:240000, pId:0, name:"证件", open:false},
			{ id:240011, pId:240000, name:"居民身份证"},
			{ id:240012, pId:240000, name:"工作证"},
			{ id:240013, pId:240000, name:"户口薄"},
			{ id:240014, pId:240000, name:"会员证"},
			{ id:240015, pId:240000, name:"记者证"},
			{ id:240016, pId:240000, name:"学生证"},
			{ id:240021, pId:240000, name:"证明证"},
			{ id:240022, pId:240000, name:"军人通行证"},
			{ id:240023, pId:240000, name:"残疾证"},
			{ id:240024, pId:240000, name:"持枪证"},
			{ id:240025, pId:240000, name:"医疗证"},
			{ id:240026, pId:240000, name:"劳保证"},
			{ id:240065, pId:240000, name:"临时出入证"},
			{ id:240071, pId:240000, name:"车辆通行证"},
			{ id:240075, pId:240000, name:"机动车驾驶执照"},
			{ id:240081, pId:240000, name:"汽车牌照"},
			{ id:240082, pId:240000, name:"拖拉机牌照"},
			{ id:240083, pId:240000, name:"摩托车牌照"},
			{ id:240085, pId:240000, name:"三轮车牌照"},
			{ id:240099, pId:240000, name:"其他证件"},
			
			{ id:250000, pId:0, name:"票券", open:false},
			{ id:250001, pId:250000, name:"券"},
			{ id:250023, pId:250000, name:"发票"},
			{ id:250031, pId:250000, name:"飞机票"},
			{ id:250032, pId:250000, name:"火车票"},
			{ id:250035, pId:250000, name:"出租车票"},
			{ id:250046, pId:250000, name:"其他有价票券"},
			{ id:250099, pId:250000, name:"其他无价票券"},
			
			{ id:260000, pId:0, name:"枪支弹药", open:false},
			{ id:260010, pId:260000, name:"手枪"},
			{ id:260030, pId:260000, name:"步枪"},
			{ id:260040, pId:260000, name:"冲锋枪"},
			{ id:260050, pId:260000, name:"机枪"},
			{ id:260069, pId:260000, name:"其他枪支弹药"},
			
		];
		
		
		
		var zNodes_2 =[
			{ id:1, pId:0, name:"病残孕人", open:false},
			{ id:11, pId:1, name:"残肢人"},
			{ id:12, pId:1, name:"瘫痪人"},
			{ id:13, pId:1, name:"聋哑人"},
			{ id:14, pId:1, name:"盲人"},
			{ id:15, pId:1, name:"呆傻人"},
			{ id:16, pId:1, name:"病人"},
			{ id:17, pId:1, name:"孕妇"},
			
			{ id:2, pId:0, name:"对象来源", open:false},
			{ id:21, pId:2, name:"本地"},
			{ id:22, pId:2, name:"外地"},
			{ id:23, pId:2, name:"港澳台"},
			{ id:24, pId:2, name:"国外"},
	
			{ id:3, pId:0, name:"对象性别年龄", open:false},
			{ id:31, pId:3, name:"老年男子"},
			{ id:32, pId:3, name:"老年妇女"},
			{ id:33, pId:3, name:"中年男子"},
			{ id:34, pId:3, name:"中年妇女"},
			{ id:35, pId:3, name:"男青年"},
			{ id:36, pId:3, name:"女青年"},
			{ id:37, pId:3, name:"少男"},
			{ id:38, pId:3, name:"少女"},
			{ id:39, pId:3, name:"幼儿"},
			{ id:310, pId:4, name:"幼女"},
			
			{ id:4, pId:0, name:"对象特别身份", open:false},
			{ id:41, pId:4, name:"人大代表"},
			{ id:42, pId:4, name:"政协委员"},
			{ id:44, pId:4, name:"外交官员"},
			{ id:45, pId:4, name:"高级公务员"},
			{ id:46, pId:4, name:"知名人士"},
			{ id:47, pId:4, name:"民主人士"},
			{ id:48, pId:4, name:"高知"},
			{ id:49, pId:4, name:"现役军人"},
			{ id:410, pId:4, name:"部队职工"},
			{ id:411, pId:4, name:"武装警察"},
			{ id:412, pId:4, name:"警察"},
			{ id:413, pId:4, name:"司法人员"},
			{ id:414, pId:4, name:"保安人员"},
			{ id:415, pId:4, name:"联防人员"},
			{ id:416, pId:4, name:"治保人员"},
			{ id:417, pId:4, name:"港澳台人"},
			{ id:418, pId:4, name:"华侨"},
			{ id:419, pId:4, name:"外国人"},
    
    		{ id:5, pId:0, name:"对象一般身份", open:false},
			{ id:51, pId:5, name:"科研人员"},
			{ id:52, pId:5, name:"技术人员"},
			{ id:53, pId:5, name:"医务人员"},
			{ id:54, pId:5, name:"金融人员"},
			{ id:55, pId:5, name:"财会人员"},
			{ id:56, pId:5, name:"工商人员"},
			{ id:57, pId:5, name:"税务人员"},
			{ id:58, pId:5, name:"海关人员"},
			{ id:59, pId:5, name:"律师"},
			{ id:510, pId:5, name:"教师"},
			{ id:511, pId:5, name:"摄影师"},
			{ id:512, pId:5, name:"文艺工作者"},
			{ id:513, pId:5, name:"体育工作者"},
			{ id:514, pId:5, name:"宗教职业者"},
			{ id:515, pId:5, name:"记者"},
			{ id:516, pId:5, name:"作家"},
			{ id:517, pId:5, name:"编辑"},
			{ id:518, pId:5, name:"翻译"},
			{ id:519, pId:5, name:"考古人员"},
			{ id:520, pId:5, name:"文物人员"},
			{ id:521, pId:5, name:"公务员"},
			{ id:522, pId:5, name:"董事长"},
			{ id:523, pId:5, name:"总经理"},
			{ id:524, pId:5, name:"经理"},
			{ id:525, pId:5, name:"厂长"},
			{ id:526, pId:5, name:"企业管理人员"},
			{ id:527, pId:5, name:"电信人员"},
			{ id:528, pId:5, name:"营业员"},
			{ id:529, pId:5, name:"售货员"},
			{ id:530, pId:5, name:"采购员"},
			{ id:531, pId:5, name:"供销员"},
			{ id:532, pId:5, name:"信托收购员"},
			{ id:533, pId:5, name:"废品收购员"},
			{ id:534, pId:5, name:"服务员"},
			{ id:535, pId:5, name:"售票员"},
			{ id:536, pId:5, name:"理发员"},
			{ id:537, pId:5, name:"厨师"},
			{ id:538, pId:5, name:"炊事员"},
			{ id:539, pId:5, name:"保育员"},
			{ id:540, pId:5, name:"家庭服务人员"},
			{ id:541, pId:5, name:"洗染织补人员"},
			{ id:542, pId:5, name:"公勤人员"},
			{ id:543, pId:5, name:"殡葬人员"},
			{ id:544, pId:5, name:"园林工作人员"},
			{ id:545, pId:5, name:"导游员"},
			{ id:546, pId:5, name:"机电产品修理员"},
			{ id:547, pId:5, name:"生活用品修理员"},
			{ id:548, pId:5, name:"信息业人员"},
			{ id:549, pId:5, name:"农民"},
			{ id:550, pId:5, name:"乡镇企业人员"},
			{ id:551, pId:5, name:"经商农民"},
			{ id:552, pId:5, name:"民工"},
			{ id:553, pId:5, name:"林业人员"},
			{ id:554, pId:5, name:"牧业人员"},
			{ id:555, pId:5, name:"渔业人员"},
			{ id:556, pId:5, name:"船民"},
			{ id:557, pId:5, name:"猎民"},
			{ id:558, pId:5, name:"船员"},
			{ id:559, pId:5, name:"驾驶员"},
			{ id:560, pId:5, name:"出租车司机"},
			{ id:561, pId:5, name:"三轮车夫"},
			{ id:562, pId:5, name:"私营业主"},
			{ id:563, pId:5, name:"个体户"},
			{ id:564, pId:5, name:"工人"},
			{ id:565, pId:5, name:"学生"},
			{ id:566, pId:5, name:"警察"},
			{ id:567, pId:5, name:"司法人员"},
			{ id:568, pId:5, name:"保安人员"},
			{ id:569, pId:5, name:"联防人员"},
			{ id:570, pId:5, name:"治保人员"},
			{ id:571, pId:5, name:"港澳台人"},
			{ id:572, pId:5, name:"华侨"},
			{ id:573, pId:5, name:"外国人"},
			{ id:574, pId:5, name:"现役军人"},
			{ id:575, pId:5, name:"武装警察"},
			{ id:576, pId:5, name:"离退休人员"},
			{ id:577, pId:5, name:"下岗人员"},
			{ id:578, pId:5, name:"无业人员"},
			{ id:579, pId:5, name:"家务"},
			{ id:580, pId:5, name:"外企员工"},
			{ id:581, pId:5, name:"三资企业员工"},
			{ id:582, pId:5, name:"特行人员"},
			{ id:583, pId:5, name:"物业管理人员"},
			
			{ id:6, pId:0, name:"特殊女性服务人员", open:false},
			{ id:61, pId:6, name:"按摩女"},
			{ id:62, pId:6, name:"陪聊女"},
			{ id:63, pId:6, name:"三陪女"},
			
			{ id:7, pId:0, name:"其他", open:false},
			
		];
		
		
		$(document).ready(function(){
		});
		
		var currentObjId = "";
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj(treeId),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
				
			var showVal = $("#"+currentObjId);
			showVal.attr("value", v);
		}
		
		function showMenu(obj) {
			
			var cityObj = $("#"+obj.id);
			var cityOffset = $("#"+obj.id).offset();
			
			currentObjId = obj.id;
			
			if(obj.id=="crimePattern")
			{
				$.fn.zTree.init($("#treeDemo_0"), setting, zNodes_0);	
				$("#menuContent_0").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown0);
			}
			
			if(obj.id=="crimeObject")
			{
				$.fn.zTree.init($("#treeDemo_1"), setting2, zNodes_1);	
				$("#menuContent_1").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown1);
			}
			
			if(obj.id=="crimeTarget")
			{
				$.fn.zTree.init($("#treeDemo_2"), setting2, zNodes_2);	
				$("#menuContent_2").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown2);
			}
			
			
		}
		
		function hideMenu(index) {
		
			switch(index)
			{
				case 0:
				$("#menuContent_0").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown0);
				break;
				
				case 1:
				$("#menuContent_1").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown1);
				break;
				
				case 2:
				$("#menuContent_2").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown2);
				break;
			}
			
			
		}
		
		function onBodyDown0(event) {
			if (!(event.target.id == "crimePattern"|| event.target.id == "menuContent_0" || $(event.target).parents("#menuContent_0").length>0)) {
				hideMenu(0);
			}
		}
		
		function onBodyDown1(event) {
			if (!(event.target.id == "crimeObject"|| event.target.id == "menuContent_1" || $(event.target).parents("#menuContent_1").length>0)) {
				hideMenu(1);
			}
		}
		
		function onBodyDown2(event) {
			if (!(event.target.id == "crimeTarget"|| event.target.id == "menuContent_2" || $(event.target).parents("#menuContent_2").length>0)) {
				hideMenu(2);
			}
		}
			
	</script>
	</head>
	<body>

		<form name="injurycaseAddForm" action="injurycaseAction!add"
			method="post" enctype="multipart/form-data"
			onsubmit="return checkCase();">
			<input type="hidden" name="injurycase.itype"
				value="<s:property value="itype"/>" />
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
					</div>
				</div>

				<div style="width: 100%">
					<div id="tab_demo" class="HuiTab">
						<div class="tabBar cl">
							<span>信息录入</span>
						</div>
						<div class="tabCon">
							<div class="row cl text-c">
								<h1 style="line-height: 80px;">
									<small style="color: #000;"><s:property
											value="pageTileName" />信息表</small>
								</h1>
							</div>
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

										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="center">
													<img id="myimage1" class="img-responsive thumbnail"
														width="200px" height="180px;" alt="案件图片" />
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
													<s:file name="picture1"
														accept="image/jpeg,image/png,image/jpg"
														onchange="change1();" id="myfile1"></s:file>

												</td>
											</tr>
										</table>
									</div>




									<div class="col-2">
										<label class="form-label text-r">
											串并案系列名称：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="series" name="injurycase.series"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											是否串并案：
										</label>
									</div>
									<div class="col-4">
										<s:select list="#{0:'否',1:'是'}" cssClass="input-text"
											name="injurycase.isRelated" listKey="key" listValue="value"
											cssStyle="width:200px"></s:select>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案件分类：
										</label>
									</div>
									<div class="col-4">
										<s:if test="itype==1">刑事案件</s:if>
										<s:if test="itype==2">重伤案件</s:if>
										<s:if test="itype==3">团伙系列案件</s:if>
										<s:if test="itype==4">行政案件</s:if>
									</div>
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
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</div>
										<div class="col-2">
											<label class="form-label text-r">
												案件名称：
											</label>
										</div>
										<div class="col-4">
											<s:textfield id="caseName" name="injurycase.caseName"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
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
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											案发时间：
										</label>
									</div>
									<div class="col-4">
										<input type="text" name="injurycase.startTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
											id="logmin" class="input-text Wdate" style="width: 200px;">
									</div>
								</div>

								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											警情编号：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="situationNum" name="injurycase.situationNum"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											作案目标：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="crimeTarget" name="injurycase.crimeTarget" onclick="showMenu(this);"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
										<div id="menuContent_2" class="menuContent"
											style="display: none;">
											<ul id="treeDemo_2" class="ztree"></ul>
										</div>
									</div>
								</div>
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											作案工具：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="crimeObject" name="injurycase.crimeObject" onclick="showMenu(this);"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
										<div id="menuContent_1" class="menuContent"
											style="display: none;">
											<ul id="treeDemo_1" class="ztree"></ul>
										</div>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											作案方式：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="crimePattern" name="injurycase.crimePattern" 
											cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"
											onclick="showMenu(this);"></s:textfield>

										<div id="menuContent_0" class="menuContent"
											style="display: none;">
											<ul id="treeDemo_0" class="ztree"></ul>
										</div>
									</div>
								</div>


								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											人员特征：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="personFeature"
											name="injurycase.personFeature"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											物品特征：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="goodsFeature" name="injurycase.goodsFeature"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>



								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											办案人：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="appraiser" name="injurycase.appraiser"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											联系电话：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="telphone" name="injurycase.telphone"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
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
											id="input9"
											cssStyle="width: 100%; height: 80px; float: left;"
											placeholder="简要案情"></s:textarea>
									</div>
								</div>


								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											图像细节描述：
										</label>
									</div>
									<div class="col-10">
										<s:textarea name="injurycase.detailsDescription"
											cssClass="input-text" id="detailsDescription"
											cssStyle="width: 100%; height: 80px; float: left;"
											placeholder="图像细节描述"></s:textarea>
									</div>
								</div>


								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											图像实物描述：
										</label>
									</div>
									<div class="col-10">
										<s:textarea name="injurycase.goodsDescription"
											cssClass="input-text" id="goodsDescription"
											cssStyle="width: 100%; height: 80px; float: left;"
											placeholder="图像实物描述"></s:textarea>
									</div>
								</div>

								<s:if test="itype==2">
									<div class="row cl mb-10">
										<div class="col-2">
											<label class="form-label text-r">
												伤势评估：
											</label>
										</div>
										<div class="col-10">
											<s:textarea name="injurycase.injuryAssess"
												cssClass="input-text" id="input9"
												cssStyle="width: 100%; height: 80px; float: left;"
												placeholder="伤势评估"></s:textarea>
										</div>
									</div>
								</s:if>

								<div class="row cl mb-10"></div>
							</div>


						</div>
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
	</body>
</html>