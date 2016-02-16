
$(document).ready(function() { 

	$("#clearButton").click(function(){
		$("#convalue").val("");
	});


}); 

$(document).ready()

function getCheckVal()
{
	var checkedIDs='';
	for(var i =0;i<$(".indexID").length;i++)
	{
		if($(".indexID")[i].checked)
		{
			checkedIDs = checkedIDs+$(".indexID")[i].value+",";
		}
	}
}

function deleteAllCheckedUnits()
{
		if(confirm('你确定删除这些机构吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			$.ajax({   
			            url:'deleteUnits',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}


function deleteAllCheckedUserRoles()
{
		if(confirm('你确定删除这些(用户)角色吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			$.ajax({   
			            url:'deleteUserRoles',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

function deleteAllCheckedPersons()
{
		if(confirm('你确定删除这些人员信息吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			if(checkedIDs.length>0)
			{
				$.ajax({   
			            url:'deletePersons',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
			}else
			{
				alert("请选择所需删除的人员.");
			}
		}
			
			
}

//删除涉及案件
function deleteLawcase(lawid)
{
		if(confirm('你确定删除该案件吗？'))
		{
			$.ajax({   
			            url:'deleteLawcase',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"lawid":lawid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

//删除疑难解答
function deleteTroubleshooting(troubid)
{
		if(confirm('你确定删除该疑难吗？'))
		{
			$.ajax({   
			            url:'deleteTroubleshooting',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"troubid":troubid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

//删除发起研判信息
function deleteJudge(jid)
{
		if(confirm('你确定删除该研判信息吗？'))
		{
			$.ajax({   
			            url:'deleteJudge',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"jid":jid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}
//删除发起关系人员，同案人员信息
function deleteOtherperson(otherid)
{
		if(confirm('你确定删除该人员信息吗？'))
		{
			$.ajax({   
			            url:'deleteOtherperson',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"otherid":otherid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

//通知公告
function deleteAllCheckedPnotices()
{
		if(confirm('你确定删除这些通知公告吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			$.ajax({   
			            url:'deletePnotices',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

function deleteAllCheckedSuccessexamples()
{
		if(confirm('你确定删除这些成功案例吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			$.ajax({   
			            url:'deleteSuccessexamples',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}


function deleteAllCheckedInjurycases()
{
		if(confirm('你确定删除这些案件吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			$.ajax({   
			            url:'deleteInjurycases',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}



