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
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/jquery-validate.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		
		<title>新增财产分析人员</title>
	</head>
	<body>
		<form name="personAddForm" id="analyzeMan" action="personAction!add" method="post"
			 onsubmit="return checkPerson();">
			 <input type="hidden" name="person.type"
				value="<s:property value="type"/>" />
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
							<span>信息录入</span><span>流转信息</span>
						</div>
						<div class="tabCon">
							<div class="row cl text-c">
								<h1 style="line-height: 80px; margin-bottom: 10px;">
									<small style="color: #000;">侵财人员信息表</small>
								</h1>
							</div>
							<div class="col-12 mb-10 c-primary f-16"
								style="border-bottom: solid 2px #2DABF7">
								犯罪嫌疑人基本情况
							</div>
							<div class="row cl">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="10%">
											<label class="form-label text-r">
												<span class="c-red">*</span>人员编号：
											</label>
										</td>
										<td colspan="2">
											<s:textfield id="number" name="person.number"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;" required="true"></s:textfield>
										</td>
										<td width="17%">
											&nbsp;
										</td>
										<td width="14%" align="center">
											<p>
												&nbsp;
											</p>
										</td>
										<td width="23%" align="center">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												登记缘由：
											</label>
										</td>
										<td width="22%">
											<s:select
												list="#{0:'请选择',1:'刑事拘留',2:'刑事传唤',3:'留置盘问',4:'取保候审'}"
												cssClass="input-text" name="analyzeMan.registrationReason"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td width="14%">
											<label class="form-label text-r">
												姓名：
											</label>
										</td>
										<td>
											<s:textfield name="person.name" id="name"
												cssClass="input-text radius size-M " required="true"
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td width="14%">
											<label class="form-label text-r">
												性别：
											</label>
										</td>
										<td width="23%">
											<s:select list="#{1:'男',0:'女'}" cssClass="input-text"
												name="person.sex" listKey="key" listValue="value"
												cssStyle="width:200px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												别名（绰号）：
											</label>
										</td>
										<td>
											<s:textfield name="analyzeMan.nickname" id="name"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												文化程度：
											</label>
										</td>
										<td>
											<s:select
												list="#{0:'请选择',1:'研究生',2:'研究生毕业',3:'研究生肄业',4:'大学本科',5:'大学毕业',6:'相当大学毕业',7:'大学肄业',8:'大学专科',9:'专科毕业',10:'相当专科毕业',11:'专科肄业',12:'中专或中技',13:'中专毕业',14:'中技毕业',15:'相当中专或中技毕业',16:'中专或中技肄业',17:'技工学校',18:'技工学校毕业',19:'技工学校肄业',20:'高中',21:'高中毕业',22:'职业高中毕业',23:'农业高中毕业',24:'相当高中毕业',25:'高中肄业',26:'初中',27:'初中毕业',28:'职业初中毕业',29:'农业初中毕业',30:'相当初中毕业',31:'初中肄业',32:'小学',33:'小学毕业',34:'相当小学毕业',35:'小学肄业',36:'文盲或半文盲',37:'博士',38:'博士后'}" cssClass="input-text" name="analyzeMan.degreeEducation"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td width="14%">
											<label class="form-label text-r">
												民族：
											</label>
										</td>
										<td width="23%">																																																																																													        	 																	   	          																									   
												<s:select list="#{0:'请选择',1:'汉族',2:'蒙古族',3:'回族',4:'藏族',5:'维吾尔族',6:'苗族',7:'彝族',8:'壮族',9:'布依族',10:'朝鲜族',11:'满族',12:'侗族',13:'瑶族',14:'白族',15:'土家族',16:'哈尼族',17:'哈萨克族',18:'傣族',19:'黎族',20:'傈僳族',21:'佤族',22:'畲族',23:'高山族',24:'拉祜族',25:'水族',26:'东乡族',27:'纳西族',28:'景颇族',29:'柯尔克孜族',30:'土族',31:'达斡尔族',32:'仫佬族',33:'羌族',34:'布朗族',35:'撒拉族',36:'毛难族',37:'仡佬族',38:'锡伯族',39:'阿昌族',40:'普米族',41:'塔吉克族',42:'怒族',43:'乌孜别克族',44:'俄罗斯族',45:'鄂温克族',46:'崩龙族',47:'保安族',48:'裕固族',49:'京族',50:'塔塔尔族',51:'独龙族',52:'鄂伦春族',53:'赫哲族',54:'门巴族',55:'珞巴族',56:'基诺族',57:'其他',58:'外国血统中国籍'}" cssClass="input-text"
												name="analyzeMan.nationality" listKey="key" listValue="value"
												cssStyle="width:200px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												身份证号码：
											</label>
										</td>
										<td>
											<s:textfield name="person.idcard"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												手机号码：
											</label>
										</td>
										<td>
												<s:textfield name="person.telphone"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td width="14%" align="center">
											<label class="form-label text-r">
												淘宝：
											</label>
										</td>
										<td width="23%">
										<s:textfield name="analyzeMan.taoBaoNumber"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												前科：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'盗窃',2:'抢劫',3:'抢夺',4:'其它',5:'无'}"
												cssClass="input-text" name="analyzeMan.criminalRecord"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											<s:textfield name="analyzeMan.criminalRecordTimes"
												cssClass="input-text radius size-M "
												cssStyle="width: 50px;"></s:textfield>
											次
										</td>
										<td>
											<label class="form-label text-r">
												QQ：
											</label>
										</td>
										<td>
											<s:textfield name="person.qq"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td width="14%" align="center">
											<label class="form-label text-r">
												微信号：
											</label>
										</td>
										<td width="23%">
											<s:textfield name="person.wechat"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												涉嫌案由：
											</label>
										</td>
										<td>
											<s:textfield name="analyzeMan.lawcaseReason"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
										<td>
											<label class="form-label text-r">
												户籍地详址：
											</label>
										</td>
										<td colspan="3">
											<s:textfield name="person.registerAddress"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												入所前职业：
											</label>
										</td>
										<td>
												<s:select
												list="#{0:'请选择',1:'固定职业',2:'临时工',3:'无业'}"
												cssClass="input-text" name="analyzeMan.preOccupation"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td>
											<label class="form-label text-r">
												工作单位：
											</label>
										</td>
										<td colspan="3">
											<s:textfield name="analyzeMan.workdUnit"
												cssClass="input-text radius size-M "
												cssStyle="width: 200px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												暂住证情况：
											</label>
										</td>
										<td colspan="5">
											暂住证有无
											<s:select
												list="#{0:'请选择',1:'有',2:'无'}"
												cssClass="input-text" name="analyzeMan.haveTemporaryResidential"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											是否失效
											<s:select
												list="#{0:'请选择',1:'是',2:'否'}"
												cssClass="input-text" name="analyzeMan.isTemporaryResidentialUseful"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												居住区域：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'省外',2:'市外',3:'市内区外',4:'本区',5:'本单位辖区'}"
												cssClass="input-text" name="analyzeMan.residentialZone"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td>
											<label class="form-label text-r">
												来本市时间：
											</label>
										</td>
										<td colspan="3">
										<s:select
												list="#{0:'请选择',1:'七天',2:'一个月',3:'半年',4:'半年以上'}"
												cssClass="input-text" name="analyzeMan.comeToDztime"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												居住场所：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'租房',2:'旅馆',3:'网吧',4:'寄宿',5:'浴室',6:'车船码头',7:'公园'}"
												cssClass="input-text" name="analyzeMan.stayLocation"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td>
											<label class="form-label text-r">
												地址：
											</label>
										</td>
										<td colspan="3">
											<s:textfield name="analyzeMan.address"
												cssClass="input-text radius size-M "
												cssStyle="width: 400px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												投宿旅店：
											</label>
										</td>
										<td colspan="5">
											有无登记
											<s:select
												list="#{0:'请选择',1:'有',2:'无'}"
												cssClass="input-text" name="analyzeMan.isStayRegister"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											有无未实名登记情况
											<s:select
												list="#{0:'请选择',1:'有',2:'无'}"
												cssClass="input-text" name="analyzeMan.isStayRealname"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											有无同住
											<s:select
												list="#{0:'请选择',1:'有',2:'无'}"
												cssClass="input-text" name="analyzeMan.isStayInmate"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												上网吧：
											</label>
										</td>
										<td colspan="5">
											有无登记
											<s:select
												list="#{0:'请选择',1:'有',2:'无'}"
												cssClass="input-text" name="analyzeMan.isNetplayRegister"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											有无未实名登记情况
												<s:select
												list="#{0:'请选择',1:'有',2:'无'}"
												cssClass="input-text" name="analyzeMan.isNetplayRealname"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											是否案发地附近
											<s:select
												list="#{0:'请选择',1:'是',2:'否'}"
												cssClass="input-text" name="analyzeMan.isCaseHappenNearbyInternetbar"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												经济来源：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'务工',2:'经商',3:'亲朋资助',4:'无'}"
												cssClass="input-text" name="analyzeMan.pocketbook"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td>
											<label class="form-label text-r">
												是否侵财：
											</label>
										</td>
										<td colspan="3">
										<s:select
												list="#{0:'请选择',1:'是',2:'否'}"
												cssClass="input-text" name="analyzeMan.isPropertyCrime"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												是否布控：
											</label>
										</td>
										<td colspan="4">
											<s:select list="#{0:'否',1:'是'}" cssClass="input-text"
													name="person.isMakeControl" listKey="key" listValue="value"
													cssStyle="width:200px"></s:select>
										</td>
									</tr>
								</table>

							</div>
							<div class="row cl mt-20">
								<div class="col-12 mb-10 c-primary f-16"
									style="border-bottom: solid 2px #2DABF7">
									案件情况
								</div>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 45px;">
									<tr>
										<td width="10%">
											<label class="form-label text-r">
												街面犯罪：
											</label>
										</td>
										<td width="22%">
											<s:select
												list="#{0:'请选择',1:'是',2:'否'}"
												cssClass="input-text" name="analyzeMan.isStreetCrime"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td colspan="2">
											<label class="form-label text-r">
												作案地与居住地是否同地（以区为准）：
											</label>
										</td>
										<td colspan="2">
											<s:select
												list="#{0:'请选择',1:'是',2:'否'}"
												cssClass="input-text" name="analyzeMan.isSomeZone"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												作案动机：
											</label>
										</td>
										<td>
											<s:select
												list="#{0:'请选择',1:'经济胁迫',2:'挥霍享乐',3:'寻找刺激',4:'报复纠纷',5:'同伙相约',6:'教唆胁迫'}"
												cssClass="input-text" name="analyzeMan.crimeMotive"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td colspan="2">
											<label class="form-label text-r">
												作案地点选取：
											</label>
										</td>
										<td colspan="2">
										<s:select
												list="#{0:'请选择',1:'城区',2:'农村',3:'城郊',4:'公路',5:'野外'}"
												cssClass="input-text" name="analyzeMan.crimeBigLocation"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
												<s:select
												list="#{0:'请选择',1:'大型商场',2:'小型店铺',3:'广场车站',4:'居民住宅',5:'偏僻地段',6:'机关单位',7:'厂砖企业',8:'娱乐场所',9:'农宅租房'}"
												cssClass="input-text" name="analyzeMan.crimeSmallLocation"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>	
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												作案时间：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'上午作案',2:'下午作案',3:'前半夜作案',4:'后半夜作案'}"
												cssClass="input-text" name="analyzeMan.crimeTime"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td colspan="2">
											<label class="form-label text-r">
												选择交通方式：
											</label>
										</td>
										<td colspan="2">
											来
												<s:select
												list="#{0:'请选择',1:'徒步',2:'公交车',3:'黄鱼车',4:'私家车',5:'私家车',6:'公共自行车',7:'地铁'}"
												cssClass="input-text" name="analyzeMan.transportationTypeCome"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											去
											<s:select
												list="#{0:'请选择',1:'徒步',2:'公交车',3:'黄鱼车',4:'私家车',5:'私家车',6:'公共自行车',7:'地铁'}"
												cssClass="input-text" name="analyzeMan.transportationTypeGo"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												流窜范围：
											</label>
										</td>
										<td>
											<s:select
												list="#{0:'请选择',1:'本地',2:'跨社区',3:'跨街道',4:'跨区县',5:'跨市',6:'跨省'}"
												cssClass="input-text" name="analyzeMan.fledRange"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td colspan="2">
											<label class="form-label text-r">
												作案手段、方式：
											</label>
										</td>
										<td colspan="2">
										<s:textfield name="analyzeMan.crimeMethod"
												cssClass="input-text radius size-M "
												cssStyle="width: 400px;"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												作案分析：
											</label>
										</td>
										<td>
											<s:select
												list="#{0:'请选择',1:'夜盗',2:'白闯',3:'盗窃电动车',4:'其他盗窃',5:'抢劫',6:'诈骗'}"
												cssClass="input-text" name="analyzeMan.crimeAnalyze"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td colspan="2">
											<label class="form-label text-r">
												破案途径：
											</label>
										</td>
										<td colspan="2">
										<s:select
												list="#{0:'请选择',1:'技侦',2:'网监',3:'视频',4:'技术',5:'信息',6:'巡逻设卡',7:'审查',8:'情报',9:'投案自首',10:'银行查询'}"
												cssClass="input-text" name="analyzeMan.solveWay"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r"
												style="line-height: 20px; font-size: 12px;">
												是否结伙作案
												<br>
												（2人及2人以上）：
											</label>
										</td>
										<td>
											<s:select
												list="#{0:'请选择',1:'是',2:'否'}"
												cssClass="input-text" name="analyzeMan.isGangCrime"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td width="14%">
											<label class="form-label text-r">
												结伙成员关系：
											</label>
										</td>
										<td width="17%">
										<s:select
												list="#{0:'请选择',1:'老乡',2:'亲戚',3:'朋友',4:'偶遇',5:'狱友',6:'网友'}"
												cssClass="input-text" name="analyzeMan.gangGx"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td width="14%">
											<label class="form-label text-r">
												成员人数：
											</label>
										</td>
										<td width="23%">
										<s:select
												list="#{0:'请选择',1:'2人',2:'3-7人',3:'8人以上'}"
												cssClass="input-text" name="analyzeMan.gangNumber"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												作案工具来源：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'网购',2:'商购',3:'自制',4:'他人给予'}"
												cssClass="input-text" name="analyzeMan.crimeToolSource"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td>
											<label class="form-label text-r">
												赃物去向：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'本地',2:'跨社区',3:'跨街道',4:'跨区县',5:'跨市',6:'跨省'}"
												cssClass="input-text" name="analyzeMan.foulTo"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td>
											<label class="form-label text-r">
												销赃途径：
											</label>
										</td>
										<td>
										<s:select
												list="#{0:'请选择',1:'卖',2:'典当',3:'散卖',4:'自用',5:'转送'}"
												cssClass="input-text" name="analyzeMan.stolenGoodsWay"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label class="form-label text-r">
												抓获途径：
											</label>
										</td>
										<td colspan="5">
											主要方式
											<s:select
												list="#{0:'请选择',1:'背包机',2:'技侦',3:'布控',4:'网上追逃',5:'信息研判',6:'巡逻设卡',7:'投案自首',8:'其他'}"
												cssClass="input-text" name="analyzeMan.mainArrestWay"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
											辅助方式
													<s:select
												list="#{0:'请选择',1:'背包机',2:'技侦',3:'布控',4:'网上追逃',5:'信息研判',6:'巡逻设卡',7:'投案自首',8:'其他'}"
												cssClass="input-text" name="analyzeMan.assistArrestWay"
												listKey="key" listValue="value" cssStyle="width:180px"></s:select>
										</td>
									</tr>
								</table>
								<div class="col-12">

								</div>
							</div>
				</div>
				<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
				<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
				<script type="text/javascript"
					src="lib/My97DatePicker/WdatePicker.js"></script>
				<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
				<script type="text/javascript"
					src="lib/Validform/5.3.2/Validform.min.js"></script>
				<script type="text/javascript"
					src="lib/webuploader/0.1.5/webuploader.min.js"></script>
				<script type="text/javascript"
					src="lib/ueditor/1.4.3/ueditor.config.js"></script>
				<script type="text/javascript"
					src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
				<script type="text/javascript"
					src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
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