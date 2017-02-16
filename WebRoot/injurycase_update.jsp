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
		<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<!--[if lt IE 9]>
		<script type="text/javascript" src="lib/html5.js"></script>
		<script type="text/javascript" src="lib/respond.min.js"></script>
		<script type="text/javascript" src="lib/PIE_IE678.js"></script>
		<![endif]-->
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
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
		<title>修改案件</title>
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

		var zNodes =[
		
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
		
		var code;		
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);	
		});
		
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var showVal = $("#crimePattern");
			showVal.attr("value", v);
		}
		
		function showMenu() {
			
			var cityObj = $("#crimePattern");
			var cityOffset = $("#crimePattern").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "crimePattern" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
			
	</script>
	</head>
	<body>
		<form name="injurycaseAddForm" action="injurycaseAction!update"
			method="post" enctype="multipart/form-data"
			onsubmit="return checkCase();">
			<div class="pd-20">
				<div class="row cl">
					<div class="col-6 col-offset-6 ">
						<div class=" f-r pr-5">
							<s:token></s:token>
							<input type="submit" class="btn btn-success radius" id="button"
								value="保存并提交"></input>
						</div>
						<s:if
							test="injurycase.getEndSituation()!=null&&injurycase.getEndSituation()!='0'">
							<div class=" f-r pr-5">
								<button type="button" class="btn btn-success radius" id="button"
									name=""
									onclick="addPage('发布案例','successexampleAction!goToAdd?inid=<s:property value="id"/>','500','300')">
									<i class="Hui-iconfont">&#xe6bf;</i> 发布案例
								</button>
							</div>
						</s:if>
					</div>
				</div>

				<div style="width: 100%">
					<div id="tab_demo" class="HuiTab">
						<div class="tabBar cl">
							<span>信息录入</span><span>流转信息</span>
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
													<img id="myimage1"
														src="${injurycase.imageCase}"
														class="img-responsive thumbnail" width="200px"
														height="180px;" alt="案件图片" />
													<script type="text/javascript">
															function change1() {
															    var pic1 = document.getElementById("myimage1"),
															        file1 = document.getElementById("myfile1");
															    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext1!='png'&&ext1!='jpg'&&ext1!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
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
											value="<s:property value="injurycase.startTime"/>"
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
										<s:textfield id="crimeTarget" name="injurycase.crimeTarget"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
								</div>
								
								<div class="row cl mb-10">
									<div class="col-2">
										<label class="form-label text-r">
											作案对象：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="crimeObject" name="injurycase.crimeObject"
											cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
									</div>
									<div class="col-2">
										<label class="form-label text-r">
											作案方式：
										</label>
									</div>
									<div class="col-4">
										<s:textfield id="crimePattern" name="injurycase.crimePattern" 
											cssClass="input-text radius size-M " cssStyle="width: 200px;" readonly="true"
											onclick="showMenu();"></s:textfield>

										<div id="menuContent" class="menuContent"
											style="display: none;">
											<ul id="treeDemo" class="ztree"></ul>
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
											鉴定人：
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

								<s:if test="injurycase.itype==2">
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


								<div class="row cl mt-20">
									<div class="col-12 mb-0 c-primary f-16"
										style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
										上传视频信息
										<input class="btn btn-primary radius mt-10 f-r" type="button"
											onclick="addPage('新增视频','mediaAction!goToAdd?inid=<s:property value="id"/>&mtype=1','500','300')"
											value="新增视频">
									</div>
									<div class="col-12">
										<table class="table table-border table-bg">
											<thead>
												<tr>
													<th>
														序号
													</th>
													<th>
														视频标题
													</th>
													<th>
														视频描述
													</th>
													<th>
														操作
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="medias.size>0">
													<s:iterator value="medias" var="media" status="status">
														<tr>
															<td>
																<s:property value="#status.index+1" />
															</td>
															<td>
																<a href="mediaAction!view?mid=<s:property value="id" />"
																	onclick="javascript:void(0)"> <s:property
																		value="title" /> </a>
															</td>
															<td>
																<s:property value="descript" />
															</td>
															<td>
																<a style="text-decoration: none" class="ml-5"
																	onclick="addPage('编辑视频','mediaAction!load?mid=<s:property value="id" />','500','300')"
																	href="javascript:;" title="编辑"><i
																	class="Hui-iconfont">&#xe6df;</i> </a>
																<a style="text-decoration: none" class="ml-5"
																	href="javascript:;"
																	onclick="deleteMedia(<s:property value="id" />);"
																	title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</tbody>
										</table>
									</div>


									<div class="row cl mt-20">
										<div class="col-12 mb-0 c-primary f-16"
											style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
											嫌疑人员信息
											<input class="btn btn-primary radius mt-10 f-r" type="button"
												onclick="addPage('新增嫌疑人员','otherpersonAction!goToAdd?inid=<s:property value="id"/>&otype=2','500','300')"
												value="新增嫌疑人员">
										</div>
										<div class="col-12">
											<table class="table table-border table-bg">
												<thead>
													<tr>
														<th>
															序号
														</th>
														<th>
															人员编号
														</th>
														<th>
															姓名
														</th>
														<th>
															身份证
														</th>
														<th>
															操作
														</th>
													</tr>
												</thead>
												<tbody>
													<s:if test="tars.size>0">
														<s:iterator value="tars" var="otherperson" status="status">
															<tr>
																<td>
																	<s:property value="#status.index+1" />
																</td>
																<td>
																	<a href="#" onclick="javascript:void(0)"> <s:property
																			value="number" /> </a>
																</td>
																<td>
																	<s:property value="name" />
																</td>
																<td>
																	<s:property value="idcard" />
																</td>
																<td>
																	<a style="text-decoration: none" class="ml-5"
																		onclick="addPage('编辑嫌疑人','otherpersonAction!load?otherid=<s:property value="id" />','500','300')"
																		href="javascript:;" title="编辑"><i
																		class="Hui-iconfont">&#xe6df;</i> </a>
																	<a style="text-decoration: none" class="ml-5"
																		href="javascript:;"
																		onclick="deleteOtherperson(<s:property value="id" />);"
																		title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</tbody>
											</table>
										</div>


										<div class="row cl mt-20">
											<div class="col-12 mb-0 c-primary f-16"
												style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
												发起疑难
												<input class="btn btn-primary radius mt-10 f-r"
													type="button"
													onclick="addPage('发起疑难','troubleshootingAction!goToAdd?inid=<s:property value="id"/>','500','300')"
													value="发起疑难">
											</div>
											<div class="col-12">
												<table class="table table-border table-bg mb-10">
													<thead>
														<tr>
															<th>
																序号
															</th>
															<th>
																标题
															</th>
															<th>
																疑难问题
															</th>
															<th>
																发布人
															</th>
															<th>
																操作
															</th>
														</tr>
													</thead>
													<tbody>

														<s:if test="injurycase.troubleshootings.size>0">
															<s:iterator value="injurycase.troubleshootings"
																var="troubleshooting" status="status">
																<tr>
																	<td>
																		<s:property value="#status.index+1" />
																	</td>
																	<td>
																		<a href="#" onclick="javascript:void(0)"> <s:property
																				value="title" /> </a>
																	</td>
																	<td>
																		<s:property value="question" />
																	</td>
																	<td>
																		<s:property value="issuer" />
																	</td>
																	<td>
																		<a style="text-decoration: none" class="ml-5"
																			onclick="addPage('编辑疑难解答','troubleshootingAction!load?troubid=<s:property value="id" />','500','300')"
																			href="javascript:;" title="编辑"><i
																			class="Hui-iconfont">&#xe6df;</i> </a>
																		<a style="text-decoration: none" class="ml-5"
																			href="javascript:;"
																			onclick="deleteTroubleshooting(<s:property value="id" />);"
																			title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
																	</td>
																</tr>
															</s:iterator>
														</s:if>

													</tbody>
												</table>
											</div>

										</div>

										<!--发起研判信息-->
										<div class="row cl mt-20">
											<div class="col-12 mb-0 c-primary f-16"
												style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
												发起研判信息
												<input class="btn btn-primary radius mt-10 f-r"
													type="button"
													onclick="addPage('发起研判信息','judgeAction!goToAdd?inid=<s:property value="id"/>&jtype=1','650','300')"
													value="发起研判信息">
											</div>
											<div class="col-12">
												<table class="table table-border table-bg mb-10"
													style="table-layout: fixed;">
													<thead>
														<tr>
															<th>
																研判次序
															</th>
															<th>
																报送机构
															</th>
															<th>
																研判要求
															</th>
															<th>
																操作
															</th>
														</tr>
													</thead>
													<tbody>
														<s:if test="injurycase.judges.size>0">
															<s:iterator value="injurycase.judges" var="judge"
																status="status">
																<tr>
																	<td>
																		<s:property value="indexNumber" />
																	</td>
																	<td>
																		<s:property value="reportUnit" />
																	</td>
																	<td
																		style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
																		<a href="#" onclick="javascript:void(0)"> <s:property
																				value="judgeRequirement" /> </a>
																	</td>
																	<td>
																		<a style="text-decoration: none" class="ml-5"
																			onclick="addPage('编辑研判信息','judgeAction!load?jid=<s:property value="id" />','500','300')"
																			href="javascript:;" title="编辑"><i
																			class="Hui-iconfont">&#xe6df;</i> </a>
																		<a style="text-decoration: none" class="ml-5"
																			href="javascript:;"
																			onclick="deleteJudge(<s:property value="id" />);"
																			title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
																	</td>
																</tr>
															</s:iterator>
														</s:if>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row cl mt-20">
											<div class="col-12 mb-0 c-primary f-16"
												style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
												研判情况
											</div>
											<div class="col-12">
												<table class="table table-border table-bg mb-10">
													<thead>
														<tr>
															<th>
																研判次序
															</th>
															<th align="center">
																技侦信息
															</th>
															<th align="center">
																网络信息
															</th>
															<th align="center">
																情报信息
															</th>
															<th align="center">
																图像信息
															</th>
														</tr>
													</thead>
													<tbody>
														<s:if test="injurycase.judges.size>0">
															<s:iterator value="injurycase.judges" var="judge"
																status="status">
																<tr>
																	<td>
																		<s:property value="indexNumber" />
																	</td>
																	<td>
																		<s:textarea name="criminalJudge" cssClass="input-text"
																			id="criminalJudge"
																			cssStyle="width: 260px; height: 180px; float: left;"
																			readonly="true"></s:textarea>
																	</td>
																	<td>
																		<s:textarea name="networkJudge" cssClass="input-text"
																			id="networkJudge"
																			cssStyle="width: 260px; height: 180px; float: left;"
																			readonly="true"></s:textarea>
																	</td>
																	<td>
																		<s:textarea name="intelligenceJudge"
																			cssClass="input-text" id="intelligenceJudge"
																			cssStyle="width: 260px; height: 180px; float: left;"
																			readonly="true"></s:textarea>
																	</td>
																	<td>
																		<s:textarea name="imageJudge" cssClass="input-text"
																			id="imageJudge"
																			cssStyle="width: 260px; height: 180px; float: left;"
																			readonly="true"></s:textarea>
																	</td>
																</tr>
															</s:iterator>
														</s:if>
													</tbody>
												</table>
											</div>
										</div>

										<div class="row cl">
											<div class="col-12 mb-10 c-primary f-16"
												style="border-bottom: solid 2px #2DABF7">
												完结情况
											</div>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 45px;">
												<tr>
													<td width="86%" style="padding-left: 9%;">
														<s:radio theme="simple" cssStyle="width:36px"
															list="#{ '1':'抓获', '2':'死亡', '3':'撤销案件', '4':'释放', '5':'治安拘留', '6':'刑事拘留', '7':'留置盘问', '8':'其他' }"
															name="injurycase.endSituation" />
													</td>
												</tr>
											</table>

										</div>
										<div class="col-12 mb-10 c-primary f-16"
											style="border-bottom: solid 2px #2DABF7">
											综合情况
										</div>

										<div class="row cl">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 45px;">
												<tr>
													<td width="10%" valign="top">
														<label class="form-label text-r">
															综合情况：
														</label>
													</td>
													<td>
														<s:textarea name="injurycase.comprehensiveJudge"
															cssClass="input-text" id="input9"
															cssStyle="width: 100%; height: 80px; float: left;"
															placeholder="根据以上研判信息填写综合情况"></s:textarea>
													</td>
												</tr>
											</table>
											<div class="col-12 mb-10 c-primary f-16"
												style="border-bottom: solid 2px #2DABF7">
												领导批示
											</div>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 45px;">
												<tr>
													<td width="10%" valign="top">
														<label class="form-label text-r">
															领导批示：
														</label>
													</td>
													<td>
														<s:textarea name="injurycase.leaderInstruction"
															cssClass="input-text" id="input9"
															cssStyle="width: 100%; height: 80px; float: left;"
															placeholder="领导批示填写"></s:textarea>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tabCon">
							<div class="row cl">
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div
										style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
										<s:property value="injurycase.userRole.realname" />
										：录入
									</div>
								</div>
							</div>
							<s:if test="injurycase.judges.size>0">
								<s:iterator value="injurycase.judges" var="judge"
									status="status">
									<div class="row cl">
										<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
											<div style="height: 25px; width: 200px; text-align: center">
												<i class="Hui-iconfont f-18 ">&#xe674;</i>
											</div>
										</div>
									</div>
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div
											style="height: 100px; width: 200px; border: solid 1px #666">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														研判次序
														<s:property value="indexNumber" />
														:
														<s:property value="reportUnit" />
													</td>
												</tr>
											</table>
										</div>
									</div>
								</s:iterator>
							</s:if>
							<s:if
								test="injurycase.comprehensiveJudge!=null&&injurycase.comprehensiveJudge!=''">
								<div class="row cl">
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div style="height: 25px; width: 200px; text-align: center">
											<i class="Hui-iconfont f-18 ">&#xe674;</i>
										</div>
									</div>
								</div>
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div
										style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
										完结
									</div>
								</div>
							</s:if>
							<s:if
								test="injurycase.leaderInstruction!=null&&injurycase.leaderInstruction!=''">
								<div class="row cl">
									<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
										<div style="height: 25px; width: 200px; text-align: center">
											<i class="Hui-iconfont f-18 ">&#xe674;</i>
										</div>
									</div>
								</div>
								<div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
									<div
										style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
										领导批示
									</div>
								</div>
							</s:if>
						</div>
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
				<s:hidden name="injurycase.imageCase" title="案件图片"></s:hidden>
				<s:hidden name="injurycase.isCanvas" title="水印"></s:hidden>
				<s:hidden name="injurycase.caseIds" title="caseIds"></s:hidden>
				<s:hidden name="injurycase.isOutOfTime" title="是否超期办理"></s:hidden>
				<s:hidden name="injurycase.isNew" value="0"></s:hidden>
				<s:hidden name="injurycase.itype" title="案件类型"></s:hidden>
				<s:hidden name="injurycase.id" title="案件id"></s:hidden>

				<s:if test="injurycase.userRole!=null">
					<s:hidden name="injurycase.userRole.id" title="案件子表userRoleid"></s:hidden>
				</s:if>
				<s:hidden name="injurycase.handleState" title="办理状态"></s:hidden>
				<s:hidden name="injurycase.joinDate" title="录入时间"></s:hidden>
		</form>
	</body>
</html>