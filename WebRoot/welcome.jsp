﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/ncss.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
    <script type="text/javascript" src="js/pageKit.js"></script>
    <script type="text/javascript">

        var pers = "";
        var ins = "";
        var clues = "";
        var judges = "";

        $(function () {
            setInterval("getNewPersons()", 2100);
            setInterval("getNewInjurycases()", 2200);
            setInterval("getNewClues()", 2300);
            setInterval("getNewJudges()", 2400);
        });

        function getNewPersons() {
            $.ajax({
                url: 'getNewPersons',//这里是你的action或者servlert的路径地址
                type: 'post', //数据发送方式
                async: false,
                dataType: 'json',
                error: function (msg) { //失败
                    console.log('获取人员失败.');
                },
                success: function (msg) { //成功
                    if (msg != null && msg.length > 0) {
                        pers = "";
                        for (var i = 0; i < msg.length; i++) {
                            pers = pers + "<li class='box-1'>" +
                                "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑人员\",\"personAction!load?id=" + msg[i].id + "&type=" + msg[i].type + "\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                + "</li>"
                        }
                        $("#newpersons").empty();
                        $("#newpersons").append(pers);
                    }
                }
            });
        }


        function getNewInjurycases() {
            $.ajax({
                url: 'getNewInjurycases',//这里是你的action或者servlert的路径地址
                type: 'post', //数据发送方式
                async: false,
                dataType: 'json',
                error: function (msg) { //失败
                    console.log('获取案件失败.');
                },
                success: function (msg) { //成功
                    if (msg != null && msg.length > 0) {
                        ins = "";
                        for (var i = 0; i < msg.length; i++) {
                            ins = ins + "<li class='box-1'>" +
                                "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑案件\",\"injurycaseAction!load?id=" + msg[i].id + "&itype=" + msg[i].type + "\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                + "</li>"
                        }
                        $("#newinjurycases").empty();
                        $("#newinjurycases").append(ins);
                    }
                }
            });
        }


        function getNewClues() {
            $.ajax({
                url: 'getNewClues',//这里是你的action或者servlert的路径地址
                type: 'post', //数据发送方式
                async: false,
                dataType: 'json',
                error: function (msg) { //失败
                    console.log('获取线索失败.');
                },
                success: function (msg) { //成功
                    if (msg != null && msg.length > 0) {
                        clues = "";
                        for (var i = 0; i < msg.length; i++) {
                            clues = clues + "<li class='box-1'>" +
                                "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑线索\",\"clueAction!load?id=" + msg[i].id + "\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                + "</li>"
                        }
                        $("#newclues").empty();
                        $("#newclues").append(clues);
                    }
                }
            });
        }


        function getNewJudges() {
            $.ajax({
                url: 'getNewJudges',//这里是你的action或者servlert的路径地址
                type: 'post', //数据发送方式
                async: false,
                dataType: 'json',
                error: function (msg) { //失败
                    console.log('获取新增的研判事项失败.');
                },
                success: function (msg) { //成功
                    if (msg != null && msg.length > 0) {
                        judges = "";
                        for (var i = 0; i < msg.length; i++) {
                            switch (msg[i].type) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑人员\",\"personAction!load?id=" + msg[i].id + "&type=" + msg[i].type + "\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                                case 16:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑案件\",\"injurycaseAction!load?id=" + msg[i].id + "&itype=1\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                                case 17:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑案件\",\"injurycaseAction!load?id=" + msg[i].id + "&itype=2\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                                case 18:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑案件\",\"injurycaseAction!load?id=" + msg[i].id + "&itype=3\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                                case 19:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑案件\",\"injurycaseAction!load?id=" + msg[i].id + "&itype=4\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                                case 20:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑刑侦线索\",\"clueAction!load?id=" + msg[i].id + "\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                                case 21:
                                    judges = judges + "<li class='box-1'>" +
                                        "<a style='text-decoration: none;color:red;' class='ml-5' href='javascript:;' onclick='childPageFull(\"编辑普通线索\",\"clueAction!load?id=" + msg[i].id + "\")'><time>[" + msg[i].joinDate + "]</time>" + msg[i].typeName + ":" + msg[i].name + "</a>"
                                        + "</li>"
                                    break;
                            }
                        }
                        $("#newjudges").empty();
                        $("#newjudges").append(judges);
                    }
                }
            });
        }
    </script>
    <title>我的桌面</title>
</head>
<body>

<div class="pd-20">
    <div class="Huialert Huialert-info">
        <i class="icon-remove f-r" style="cursor: pointer;">关闭</i>
        <p>
            欢迎登录 德城公安-合成作战平台 您上次登录时间：${session.currentUserRole.beforeLoginTime}
        </p>
        <div style="width: 100%; margin-top: 15px;">
            <div class="BreakingNewsController easing" id="breakingnews">
                <div class="bn-title"></div>
                <ul id="abc">
                    <s:iterator value="pnotices" var="pnotice" status="status">
                        <li>
                            <a style="text-decoration: none" class="ml-5"
                               onclick="childPageFull('查看通知公告','pnoticeAction!view?id=<s:property value="id"/>')"
                               href="javascript:;" title="查看">【<s:property
                                    value="releaseTime"/>】<s:property value="title"/> </a>
                        </li>
                    </s:iterator>
                </ul>
                <div class="bn-arrows">
                    <span class="bn-arrows-left"></span><span class="bn-arrows-right"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-6" style="padding: 5px;">
        <div class="panel panel-default">
            <div class="panel-header">
                新增人员(未办理)
            </div>
            <div class="panel-body">
                <ul class="tlist" id="newpersons">
                </ul>
            </div>
        </div>
    </div>
    <div class="col-6" style="padding: 5px;">
        <div class="panel panel-default">
            <div class="panel-header">
                新增案件(未办理)
            </div>
            <div class="panel-body">
                <ul class="tlist" id="newinjurycases">
                </ul>
            </div>
        </div>
    </div>
    <div class="col-6" style="padding: 5px;">
        <div class="panel panel-default">
            <div class="panel-header">
                新增线索(未办理)
            </div>
            <div class="panel-body">
                <ul class="tlist" id="newclues">
                </ul>
            </div>
        </div>
    </div>
    <div class="col-6" style="padding: 5px;">
        <div class="panel panel-default">
            <div class="panel-header">
                新增研判信息(未办理)
            </div>
            <div class="panel-body">
                <ul class="tlist" id="newjudges">
                </ul>
            </div>
        </div>
    </div>
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
                            <a style="text-decoration: none" class="ml-5"
                               onclick="childPageFull('查看成功案例','successexampleAction!view?id=<s:property value="id"/>')"
                               href="javascript:;">
                                <time>[<s:property
                                        value="releaseTime"/>]
                                </time>
                                <s:property value="title"/> </a>
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
            <div class="panel-body">
                <ul class="tlist">
                    <s:iterator value="troubleshootings" var="troubleshooting"
                                status="status">
                        <li class="box-1">
                            <a style="text-decoration: none" class="ml-5"
                               onclick="childPageFull('查看疑难解答','troubleshootingAction!load?troubid=<s:property
                                       value="id"/>')"
                               href="javascript:;">
                                <time>[<s:property
                                        value="releaseTime"/>]
                                </time>
                                <s:property value="title"/> </a>
                        </li>
                    </s:iterator>
                </ul>
            </div>
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
    $(function () {
        $.Huitab("#tab_daiban .tabBar span", "#tab_daiban .tabCon", "current", "click", "1");

        $.Huitab("#tab_yanpan .tabBar span", "#tab_yanpan .tabCon", "current", "click", "1");
    });
    $(function () {
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