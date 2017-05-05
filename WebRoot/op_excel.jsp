<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
    <script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="js/H-ui.js"></script>
    <script type="text/javascript" src="js/H-ui.admin.js"></script>
    <script type="text/javascript" src="js/pageKit.js"></script>
    <script type="text/javascript" src="js/checkUtil.js"></script>
    <title>消息提示页面</title>
</head>
<body>
<div class="xmWraper ">
    <div class="xmconbox pd-20">
        <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
            <div class="row cl box-shadow pd-10  bk-gray radius"
                 style="background-color: #FFF;">
                <div class="pd-20">
                    <table class="table table-border table-bordered table-bg ">
                        <thead>
                        <tr>
                            <th>
                                <span>用户操作消息：</span>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr bgcolor="#FFFFFF">
                            <td>
                                ${errorInfo}
                                <button onclick="childPage_close();" class="btn btn-success radius r mr-20" type="button">
                                    关闭并退出
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="table  table-bg ">
                        <thead>
                        <tr bgcolor="#EEF4EA">
                            <th colspan="2">
                                <span>系统基本信息</span>
                            </th>
                        </tr>
                        </thead>
                        <tr bgcolor="#FFFFFF">
                            <td width="10%" bgcolor="#FFFFFF">
                                感谢您的使用：
                            </td>
                            <td width="90%" bgcolor="#FFFFFF">
                                ${session.currentUserRole.username}
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td>
                                系统版本信息：
                            </td>
                            <td>
                                德城公安合成平台v1.0
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
    </div>

</div>
</body>
</html>