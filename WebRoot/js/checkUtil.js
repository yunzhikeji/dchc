//unit_*.jsp
function checkUnit()
{
	var unitName = $("#unitName").val();
	var unitNumber = $("#unitNumber").val();
	if(unitName==''||unitName==null)
	{
		alert("机构名称不能为空.");
		return false;
	}
	if(unitNumber==''||unitNumber==null)
	{
		alert("机构代码不能为空.");
		return false;
	}
}

//userRole_*.jsp
function checkUserRole()
{
	var username = $("#username").val();
	var password = $("#password").val();
	var userRoleRealname = $("#userRoleRealname").val();
	var userRoleNumber = $("#userRoleNumber").val();
	var userRoleTelphone = $("#userRoleTelphone").val();
	
	if(username==''||username==null)
	{
		alert("登陆账号不能为空.");
		return false;
	}
	if(password==''||password==null)
	{
		alert("登陆账号密码不能为空.");
		return false;
	}	
	if(userRoleRealname==''||userRoleRealname==null)
	{
		alert("真实姓名不能为空.");
		return false;
	}	
	
	if(userRoleTelphone!='')
	{
		if(!(/(^(\d{3,4}-)?\d{7,8})$|(^1[3|4|5|8][0-9]{9})/.test(userRoleTelphone))||userRoleTelphone.length>11){
		
			 alert("不是正确的11位手机号或者正确的固话");
			return false;
		}
	}	
}




//person_*.jsp
function checkPerson()
{
	var name = $("#name").val();
	var number = $("#number").val();
	
	if(number==''||number==null)
	{
		alert("人员编号不能为空.");
		return false;
	}	
	if(name==''||name==null)
	{
		alert("人员姓名不能为空.");
		return false;
	}
	
}


//user_add.jsp 
function changeProject_add()
{
	console.log("changeProject_add coming....................");
	var projectid = $("#projects").val();
	var url = "userAction!goToAdd?projectid="+projectid;
	url = encodeURI(url);
	location.href  = url;
}

//user_update.jsp
function changeProject_update()
{
 	console.log("changeProject_update coming....................");
	var projectid = $("#projects").val();
	var uid = $("#uid").val();
	var url = "userAction!load?projectid="+projectid+"&id="+uid;
	url = encodeURI(url);
	location.href  = url;
}

function changeLimit_update()
{
 	console.log("changeProject_update coming....................");
	var projectid = $("#projects").val();
	var uid = $("#uid").val();
	var ulimit = $("#limit").val();
	var url = "userAction!load?projectid="+projectid+"&id="+uid+"&ulimit="+ulimit;
	url = encodeURI(url);
	location.href  = url;
}

function changeLimit_add()
{
 	console.log("changeProject_add coming....................");
	var projectid = $("#projects").val();
	var ulimit = $("#limit").val();
	var url = "userAction!goToAdd?projectid="+projectid+"&ulimit="+ulimit;
	url = encodeURI(url);
	location.href  = url;
}

//gateway_update.jsp
function changeProject_updateGateway()
{
 	console.log("changeProject_updateGateway coming....................");
	var projectid = $("#projects").val();
	var gid = $("#gid").val();
	var url = "gatewayAction!load?projectid="+projectid+"&id="+gid;
	url = encodeURI(url);
	location.href  = url;
}

function checkGateaddress()
{
	console.log("检查。。");
	var gateaddress = $("#gateaddress").val();
	var gid = $("#gid").val();
	console.log(gateaddress);
	$.ajax({   
	            url:'checkGateaddress',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            async:false,
	            data: { "gateaddress":gateaddress,"id":gid},
	            dataType:'json',
	            error: function(msg)
	            { //失败   
	            	console.log('post失败');   
	            },   
	            success: function(msg)
	            { //成功
					 if(msg!=null)
					 {
					 	if(gateaddress!=parseInt(msg.gateaddress))
					 	{
					 		alert(msg.msg);
						 	
						 	
						 	if(msg.gateaddress!=null&&msg.gateaddress!=0)
						 	{
						 		$(document).ready(function(){ 
						 		console.log(msg.gateaddress);
						 			$("#gateaddress").val(msg.gateaddress);
						 		});
						 	}else
						 	{
						 		$("#gateaddress").val("");
						 	}
					 	}
					 }
				}
			});
}

function checkChannel()
{
	var gid = $("#gid").val();
	var canUpdate = false;
	$.ajax({   
	            url:'checkCanUpdateChannel',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            async:false,
	            data: {"id":gid},
	            dataType:'json',
	            error: function(msg)
	            { //失败   
	            	console.log('post失败');   
	            },   
	            success: function(msg)
	            { //成功
	            	console.log(msg);
	            	if(msg!=null)
	            	{
		            	if(msg.gateaddress!=null&&parseInt(msg.gateaddress)>0)
		            	{
		            		canUpdate = true;
		            	}else
		            	{
		            		alert("当前网关地址不存在，修改通道无效");
		            		$(document).ready(function(){ 
									   $("#channel").val(msg.channel);
									}); 
		            	}
	            	}else
	            	{
	            		//alert("数据加载错误，请重新加载。");
	            	}
	            	
				}
			});
	
	if(canUpdate)
	{
		//检查通道是否修改过
		var newDate = $("#channel").val();
		console.log(oldDate,newDate);
		if(parseInt(oldDate)!=parseInt(newDate))
		{
			$("#isChannelUpdated").val(1);
		}
		
		var channel = $("#channel").val();
		
		$.ajax({   
		            url:'checkChannel',//这里是你的action或者servlert的路径地址   
		            type:'post', //数据发送方式   
		            async:false,
		            data: { "channel":channel,"id":gid},
		            dataType:'json',
		            error: function(msg)
		            { //失败   
		            	console.log('post失败');   
		            },   
		            success: function(msg)
		            { //成功
		            	console.log(msg);
						 if(msg!=null)
						 {
						 	if(channel!=parseInt(msg.channel))
					 		{
							 	alert(msg.msg);
							 	if(msg.channel!=null&&msg.channel!=0)
							 	{
							 		$(document).ready(function(){ 
									   $("#channel").val(msg.channel);
									}); 
							 	}else
							 	{
							 		$("#channel").val("");
							 	}
							 }
						 }
					}
				});
	}
	
}


function setDataUpdated()
{

	var newDate = $("#intervaltime").val();
	if(parseInt(oldDate)!=parseInt(newDate))
	{
		$("#isDataUpdated").val(1);
	}
}

//获取最新的一个温度数据
function getNewTemp()
{
	console.log("getNewTemp。。");
	var gateaddress = $("#gateaddress").val();
	var gid = $("#gid").val();
	console.log(gateaddress);
	$.ajax({   
	            url:'checkGateaddress',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            async:false,
	            data: { "gateaddress":gateaddress,"id":gid},
	            dataType:'json',
	            error: function(msg)
	            { //失败   
	            	console.log('post失败');   
	            },   
	            success: function(msg)
	            { //成功
					 if(msg!=null)
					 {
					 	alert(msg.msg);
					 }
				}
			});
}


function checkFloat()
{
	$("#alarmtemp").val();
	$("#alarmpressure").val();
	$("#alarmvoltage").val();
	$("#alarmflow").val();
	$("#alarmbmtemp").val();
	
	if(parseInt($("#alarmtemp").val())==0)
	{
		alert("报警温度不能为0");	
		return false;
	}
	if(parseInt($("#alarmpressure").val())==0)
	{
		alert("报警压力不能为0");	
		return false;
	}
	if(parseInt($("#alarmvoltage").val())==0)
	{
		alert("报警电池电压不能为0");	
		return false;
	}
	if(parseInt($("#alarmflow").val())==0)
	{
		alert("报警流量不能为0");	
		return false;
	}
	if(parseInt($("#alarmbmtemp").val())==0)
	{
		alert("报警表面温度不能为0");	
		return false;
	}
	return true;
}


function hiddenUpdateform()
{
	$("#updateform").hide();
	$("#addresslist").show();
}
var phones =  {};
function showUpdateform()
{
	var phonestr = '';
	for(var id in phones) 
	{
    	if(phones[id]!='')
    	{
    		phonestr = phonestr+phones[id]+",";
    	}
	}  
	phonestr = phonestr.substring(0,phonestr.length-1);
	$(document).ready(function(){
	$("#phones").val(phonestr);
	});
	$("#updateform").show();
	$("#addresslist").hide();
}

function backUpdateform()
{
	$("#updateform").show();
	$("#addresslist").hide();
}

$(document).ready(function(){
	$(".checkbox").click(function(event){
		var id = $(this).attr("id");
		
		var phone = $("#phone_"+id).val();
		console.log($(this));
		console.log($(this).attr("checked"));
		if(typeof($(this).attr("checked"))!="undefined")
		{
			console.log("增加");
			phones[id] = phone;
		}else
		{
			console.log("删除");
			phones[id] = "";
		}
		
	});
});

//新增用户时检查用户名是否已经使用
function checkUsername()
{
	var username = $("#username").val();
	$.ajax({   
	            url:'checkUsername',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            async:false,
	            data: { "username":username},
	            dataType:'json',
	            error: function(msg)
	            { //失败   
	            	console.log('post失败');   
	            },   
	            success: function(msg)
	            { //成功
					 if(msg!=null)
					 {
					 	alert(msg.message);
				 		$(document).ready(function(){ 
				 			$("#username").val('');
				 		});
					 }
				}
			});
	
}


function checkPhone()
{
	  if(!(/(^(\d{3,4}-)?\d{7,8})$|(^1[3|4|5|8][0-9]{9})/.test($("#phone").val()))){
        alert("不是正确的11位手机号或者正确的固话");
        $(document).ready(function(){ 
				 			$("#phone").val('');
				 		});
				 		return false;
    }else
    {	
    	return true;
    }
}

function checkUser()
{
	var username = $("#username").val();
	if(username==''||username==null)
	{
		alert("用户名不能为空.");
		return false;
	}
	var password = $("#password").val();
	if(password==''||password==null)
	{
		alert("密码不能为空.");
		return false;
	}

	if(parseInt(ulimit)>1)
	{
		var linetext =  $("[id*='linetext']");
		console.log(linetext);
		var len = 0;
		for(var i=0;i<linetext.length;i++)
		{
		   if(linetext[i].checked==true){
		       len++;
		   }
		}
		if(len==0)
		{
			alert("管理线路不能为空.");
			return false;
		}
	}
	
	
}


function changePassword()
{
	alert("密码重置为123456");
	$(document).ready(function(){
	
		$("#password").val("123456");
	});
}

$(document).ready(function(){
	
	$(".ck").on('blur',function(){
		console.log($(this));
		var ckvalue = $(this).val();
		var ckid = $(this).attr("id");
		if(ckid!=null&&ckid=='lng')
		{
			ckvalue = parseFloat(ckvalue);
			if(isNaN(ckvalue))
			{
				alert("经度输入不正确,请输入正确数字");
				$(this).val(0);
				return;
			}
		}
		else if(ckid!=null&&ckid=='lat')
		{
		
			ckvalue = parseFloat(ckvalue);
			if(isNaN(ckvalue))
			{
					alert("纬度输入不正确,请输入正确数字");
				$(this).val(0);
				return;
			}
		}
		else if(ckid!=null&&ckid=='orderid')
		{
			if(ckvalue=='')
			{
				alert("排序编号不能为空");
				$(this).val(0);
				return;
			}
			ckvalue = Number(ckvalue);
			if(isNaN(ckvalue))
			{
				alert("排序编号输入不正确,请输入正确数字");
				$(this).val(0);
				return;
			}
		
		}
		else if(ckid!=null&&ckid=='gateaddress')
		{
			ckvalue = Number(ckvalue);
			if(isNaN(ckvalue))
			{
				alert("网关地址输入不正确,请输入正确数字");
				$(this).val(0);
				return;
			}
		
		}
		else if(ckid!=null&&ckid=='channel')
		{
			ckvalue = Number(ckvalue);
			if(isNaN(ckvalue))
			{
				alert("无线数据通道输入不正确,请输入正确数字");
				$(this).val(0);
				return;
			}
		
		}
		else if(ckid!=null&&ckid=='phonenumber')
		{
		
			if(!(/(^(\d{3,4}-)?\d{7,8})$|(^1[3|4|5|8][0-9]{9})/.test(ckvalue))){
		        alert("不是正确的11位手机号或者正确的固话");
		         $(this).val('');
				return;
		    }
		  
		}else 
		{
			ckvalue = Number(ckvalue);
			if(ckvalue==0)
			{
				alert("无效数值自动清空.");
				$(this).val('');
				return;
			}
			if(isNaN(ckvalue))
			{
				alert("请输入正确数字");
				$(this).val('');
				return;
			}
		}
		
		$(this).val(ckvalue);
		
	});

});


function checkRePassword()
{
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	if(password!=repassword)
	{
		alert("密码不一致，请重新输入.");
		return false;
	}
}

function checkProject()
{
	var orderid = $("#orderid").val();
	if(orderid=='')
	{
		alert("排序编号不能为空.");
		return false;
	}
}

function  checkLine()
{
	var orderid = $("#orderid").val();
	if(orderid=='')
	{
		alert("排序编号不能为空.");
		return false;
	}
	if(orderid=='1')
	{
		alert("该编号为预留编号，不能使用");
		return false;
	}
}

function checkGateway()
{
	var orderid = $("#orderid").val();
	if(orderid=='')
	{
		alert("排序编号不能为空.");
		return false;
	}
}

