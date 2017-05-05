<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
    <title>修改人员信息</title>
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/ncss.css" rel="stylesheet" type="text/css"/>
    <link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
          type="text/css"/>
    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
          type="text/css"/>
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
        var setting2 = {
            check: {
                enable: true,
                chkboxType: {"Y": "", "N": ""}
            },
            view: {
                dblClickExpand: false,
                showLine: false
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


        var zNodes_units = [
            {id: 0, name: "无单位"}
        ];

        var zNodes_appraisers = [
            {id: 0, name: "无"}
        ];


        $(document).ready(function () {
            $.ajax({
                url: 'getUnitVOs',//这里是你的action或者servlert的路径地址
                type: 'post', //数据发送方式
                async: false,
                dataType: 'json',
                error: function (msg) { //失败
                    console.log('请求办案单位失败.');
                },
                success: function (msg) { //成功
                    if (msg.length > 0) {
                        zNodes_units = msg;
                    }
                }
            });
            $.fn.zTree.init($("#treeDemo_unit"), setting2, zNodes_units);

        });

        var currentObjId = "";

        function onCheck(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj(treeId),
                nodes = zTree.getCheckedNodes(true),
                v = "";
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);

            var showVal = $("#" + currentObjId);
            showVal.attr("value", v);
        }

        function showMenu(obj) {

            var cityObj = $("#" + obj.id);
            var cityOffset = $("#" + obj.id).offset();

            currentObjId = obj.id;

            if (obj.id == "appraiserUnitName") {
                $.fn.zTree.init($("#treeDemo_unit"), setting2, zNodes_units);
                $("#menuContent_unit").css({
                    left: cityOffset.left + "px",
                    top: cityOffset.top + cityObj.outerHeight() + "px"
                }).slideDown("fast");
                $("body").bind("mousedown", onBodyDownUnit);
            }

            if (obj.id == "appraiser") {

                var unitName = $("#appraiserUnitName").val();
                if (unitName == null || unitName == '') {
                    alert("请选择办案单位.");
                    return false;
                }
                $.ajax({
                    url: 'getUserRoleByUnitName',//这里是你的action或者servlert的路径地址
                    type: 'post', //数据发送方式
                    async: false,
                    dataType: 'json',
                    data: {"unitName": unitName},
                    error: function (msg) { //失败
                        console.log('请求办案人员失败.');
                    },
                    success: function (msg) { //成功
                        if (msg.length > 0) {
                            zNodes_appraisers = msg;
                        }
                    }
                });

                $.fn.zTree.init($("#treeDemo_appraiser"), setting2, zNodes_appraisers);
                $("#menuContent_appraiser").css({
                    left: cityOffset.left + "px",
                    top: cityOffset.top + cityObj.outerHeight() + "px"
                }).slideDown("fast");
                $("body").bind("mousedown", onBodyDownAppraiser);
            }


        }

        function hideMenu(index) {

            switch (index) {
                case 3:
                    $("#menuContent_unit").fadeOut("fast");
                    $("body").unbind("mousedown", onBodyDownUnit);
                    break;
                case 4:
                    $("#menuContent_appraiser").fadeOut("fast");
                    $("body").unbind("mousedown", onBodyDownAppraiser);
                    break;
            }


        }


        function onBodyDownUnit(event) {
            if (!(event.target.id == "appraiserUnitName" || event.target.id == "menuContent_unit" || $(event.target).parents("#menuContent_unit").length > 0)) {
                hideMenu(3);
            }
        }


        function onBodyDownAppraiser(event) {
            if (!(event.target.id == "appraiser" || event.target.id == "menuContent_appraiser" || $(event.target).parents("#menuContent_appraiser").length > 0)) {
                hideMenu(4);
            }
        }

    </script>
</head>
<body>
<form name="personAddForm" action="personAction!update" method="post"
      enctype="multipart/form-data" onsubmit="">
    <div class="pd-20">
        <div class="row cl">
            <div class="col-6 col-offset-6 ">
                <div class=" f-r pr-5">
                    <s:token></s:token>
                    <input type="submit" class="btn btn-success radius" id="button"
                           value="保存并提交"></input>
                </div>

                <s:if
                        test="person.getEndSituation()!=null&&person.getEndSituation()!='0'">
                    <div class=" f-r pr-5">
                        <button type="button" class="btn btn-success radius" id="button"
                                onclick="addPage('发布案例','successexampleAction!goToAdd?pid=<s:property
                                        value="id"/>','500','300')">
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
                                    value="pageTileName"/>信息表
                            </small>
                        </h1>
                    </div>
                    <div class="row cl">
                        <div class="col-12 mb-10 c-primary f-16"
                             style="border-bottom: solid 2px #2DABF7">
                            人员基本信息
                        </div>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               style="line-height: 45px;">
                            <tr>
                                <td width="20%">
                                    <label class="form-label text-r">
                                        <span class="c-red">*</span>人员编号：
                                    </label>
                                </td>
                                <td>
                                    <s:textfield id="number" name="person.number"
                                                 cssClass="input-text radius size-M "
                                                 cssStyle="width: 200px;"></s:textfield>
                                </td>
                                <td>
                                    <label class="form-label text-r">
                                        <span class="c-red">*</span>姓 名：
                                    </label>
                                </td>
                                <td width="21%">
                                    <s:textfield name="person.name" id="name"
                                                 cssClass="input-text radius size-M "
                                                 cssStyle="width: 200px;"></s:textfield>
                                </td>
                                <td width="26%" rowspan="5" align="left">
                                    <table width="176" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="176" align="center">
                                                <img src="${person.photoImg}" id="myimage"
                                                     class="img-responsive thumbnail" width="200px"
                                                     height="180px;" alt="人员照片"/>
                                                <script type="text/javascript">
                                                    function change() {
                                                        var pic = document.getElementById("myimage"),
                                                            file = document.getElementById("myfile");
                                                        var ext = file.value.substring(file.value.lastIndexOf(".") + 1).toLowerCase();
                                                        // gif在IE浏览器暂时无法显示
                                                        if (ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
                                                            alert("图片的格式必须为png或者jpg或者jpeg格式！");
                                                            file.value = "";
                                                            return;
                                                        }
                                                        var isIE = navigator.userAgent.match(/MSIE/) != null,
                                                            isIE6 = navigator.userAgent.match(/MSIE 6.0/) != null;
                                                        if (isIE) {
                                                            file.select();
                                                            var reallocalpath = document.selection.createRange().text;

                                                            // IE6浏览器设置img的src为本地路径可以直接显示图片
                                                            if (isIE6) {
                                                                pic.src = reallocalpath;
                                                            } else {
                                                                // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
                                                                pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
                                                                // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
                                                                pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
                                                            }
                                                        } else {
                                                            html5Reader(file);
                                                        }
                                                        pic.alt = '图片';
                                                    }
                                                    function html5Reader(file) {
                                                        var file = file.files[0];
                                                        var reader = new FileReader();
                                                        reader.readAsDataURL(file);
                                                        reader.onload = function (e) {
                                                            var pic = document.getElementById("myimage");
                                                            pic.src = this.result;
                                                        }
                                                    }
                                                </script>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="176" align="center">
                                                <s:file name="picture"
                                                        accept="image/jpeg,image/png,image/jpg"
                                                        onchange="change();" id="myfile"></s:file>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="form-label text-r">
                                        性 别：
                                    </label>
                                </td>
                                <td width="25%">
                                    <s:select list="#{1:'男',0:'女'}" cssClass="input-text"
                                              name="person.sex" listKey="key" listValue="value"
                                              cssStyle="width:200px"></s:select>
                                </td>
                                <td width="18%">
                                    <label class="form-label text-r">
                                        出生日期：
                                    </label>
                                </td>
                                <td>
                                    <input type="text" name="person.birthday"
                                           value="<s:property value="person.birthday"/>"
                                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
                                           id="logmin" class="input-text Wdate" style="width: 200px;">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="form-label text-r">
                                        办案单位：
                                    </label>
                                </td>
                                <td width="25%">
                                    <s:textfield id="appraiserUnitName" name="person.appraiserUnitName"
                                                 onclick="showMenu(this);"
                                                 cssClass="input-text radius size-M " readonly="true"
                                                 cssStyle="width: 200px;"></s:textfield>
                                    <div id="menuContent_unit" class="menuContent"
                                         style="display: none;">
                                        <ul id="treeDemo_unit" class="ztree"></ul>
                                    </div>
                                </td>
                                <td width="18%">
                                    <label class="form-label text-r">
                                        办案人：
                                    </label>
                                </td>
                                <td>
                                    <s:textfield id="appraiser" name="person.appraiser" onclick="showMenu(this);"
                                                 cssClass="input-text radius size-M " readonly="true"
                                                 cssStyle="width: 200px;"></s:textfield>
                                    <div id="menuContent_appraiser" class="menuContent"
                                         style="display: none;">
                                        <ul id="treeDemo_appraiser" class="ztree"></ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
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
                                <td>
                                    <label class="form-label text-r">
                                        微信号：
                                    </label>
                                </td>
                                <td colspan="2">
                                    <s:textfield name="person.wechat"
                                                 cssClass="input-text radius size-M "
                                                 cssStyle="width: 200px;"></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="form-label text-r">
                                        身份证号：
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
                                <td colspan="2">
                                    <s:textfield name="person.telphone"
                                                 cssClass="input-text radius size-M "
                                                 cssStyle="width: 200px;"></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="form-label text-r">
                                        户籍地详址：
                                    </label>
                                </td>
                                <td>
                                    <s:textfield name="person.registerAddress"
                                                 cssClass="input-text radius size-M "
                                                 cssStyle="width: 200px;"></s:textfield>
                                </td>
                                <td>
                                    <label class="form-label text-r">
                                        户籍地区划：
                                    </label>
                                </td>
                                <td>
                                    <s:textfield name="person.registerAddressArea"
                                                 cssClass="input-text radius size-M "
                                                 cssStyle="width: 200px;"></s:textfield>
                                </td>
                        </table>
                    </div>

                    <div class="row cl mt-20">
                        <div class="col-12 mb-0 c-primary f-16"
                             style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
                            案件基本信息
                            <input class="btn btn-primary radius mt-10 f-r" type="button"
                                   onclick="addPage('新增涉案情况','lawcaseAction!goToAdd?pid=<s:property
                                           value="id"/>','500','300')"
                                   value="新增涉案情况">
                        </div>
                        <div class="col-12">
                            <table class="table table-border table-bg mb-10">
                                <thead>
                                <tr>
                                    <th>
                                        序号
                                    </th>
                                    <th>
                                        案件编号
                                    </th>
                                    <th>
                                        案件名称
                                    </th>
                                    <th>
                                        填报人
                                    </th>
                                    <th>
                                        填报机构
                                    </th>
                                    <th>
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:if test="person.lawcases.size>0">
                                    <s:iterator value="person.lawcases" var="lawcase"
                                                status="status">
                                        <tr>
                                            <td>
                                                <s:property value="#status.index+1"/>
                                            </td>
                                            <td>
                                                <a href="#" onclick="javascript:void(0)"> <s:property
                                                        value="caseNumber"/> </a>
                                            </td>
                                            <td>
                                                <s:property value="caseName"/>
                                            </td>
                                            <td>
                                                <s:property value="fillName"/>
                                            </td>
                                            <td>
                                                <s:property value="fillUnit"/>
                                            </td>
                                            <td>
                                                <a style="text-decoration: none" class="ml-5"
                                                   onclick="addPage('编辑涉案情况','lawcaseAction!load?lawid=<s:property
                                                           value="id"/>','500','300')"
                                                   href="javascript:;" title="编辑"><i
                                                        class="Hui-iconfont">&#xe6df;</i> </a>
                                                <a style="text-decoration: none" class="ml-5"
                                                   href="javascript:;"
                                                   onclick="deleteLawcase(<s:property value="id"/>);"
                                                   title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:if>
                                </tbody>
                            </table>
                        </div>

                    </div>


                    <!--发起疑难-->
                    <div class="row cl mt-20">
                        <div class="col-12 mb-0 c-primary f-16"
                             style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
                            发起疑难
                            <input class="btn btn-primary radius mt-10 f-r" type="button"
                                   onclick="addPage('发起疑难','troubleshootingAction!goToAdd?pid=<s:property
                                           value="id"/>','500','300')"
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

                                <s:if test="person.troubleshootings.size>0">
                                    <s:iterator value="person.troubleshootings"
                                                var="troubleshooting" status="status">
                                        <tr>
                                            <td>
                                                <s:property value="#status.index+1"/>
                                            </td>
                                            <td>
                                                <a href="#" onclick="javascript:void(0)"> <s:property
                                                        value="title"/> </a>
                                            </td>
                                            <td>
                                                <s:property value="question"/>
                                            </td>
                                            <td>
                                                <s:property value="issuer"/>
                                            </td>
                                            <td>
                                                <a style="text-decoration: none" class="ml-5"
                                                   onclick="addPage('编辑疑难解答','troubleshootingAction!load?troubid=
                                                       <s:property value="id"/>','500','300')"
                                                   href="javascript:;" title="编辑"><i
                                                        class="Hui-iconfont">&#xe6df;</i> </a>
                                                <a style="text-decoration: none" class="ml-5"
                                                   href="javascript:;"
                                                   onclick="deleteTroubleshooting(<s:property value="id"/>);"
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
                            <input class="btn btn-primary radius mt-10 f-r" type="button"
                                   onclick="addPage('发起研判信息','judgeAction!goToAdd?id=<s:property
                                           value="id"/>&jtype=1','650','300')"
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
                                <s:if test="person.judges.size>0">
                                    <s:iterator value="person.judges" var="judge"
                                                status="status">
                                        <tr>
                                            <td>
                                                <s:property value="#status.index+1"/>
                                            </td>
                                            <td>
                                                <s:property value="reportUnit"/>
                                            </td>
                                            <td
                                                    style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
                                                <a href="#" onclick="javascript:void(0)"> <s:property
                                                        value="judgeRequirement"/> </a>
                                            </td>
                                            <td>
                                                <a style="text-decoration: none" class="ml-5"
                                                   onclick="addPage('编辑研判信息','judgeAction!load?jid=<s:property
                                                           value="id"/>','500','300')"
                                                   href="javascript:;" title="编辑"><i
                                                        class="Hui-iconfont">&#xe6df;</i> </a>
                                                <a style="text-decoration: none" class="ml-5"
                                                   href="javascript:;"
                                                   onclick="deleteJudge(<s:property value="id"/>);"
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
                                <s:if test="person.judges.size>0">
                                    <s:iterator value="person.judges" var="judge"
                                                status="status">
                                        <tr>
                                            <td>
                                                <s:property value="#status.index+1"/>
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
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               style="line-height: 45px;">
                            <tr>
                                <td width="86%" style="padding-left: 9%;">
                                    <s:radio theme="simple" cssStyle="width:36px"
                                             list='#{ 1:"抓获", 2:"死亡", 3:"撤销案件", 4:"释放", 5:"治安拘留", 6:"刑事拘留", 7:"留置盘问", 8:"其他" }'
                                             name="person.endSituation"/>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <div class="col-12 mb-10 c-primary f-16"
                         style="border-bottom: solid 2px #2DABF7">
                        综合情况
                    </div>

                    <div class="row cl">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               style="line-height: 45px;">
                            <tr>
                                <td width="10%" valign="top">
                                    <label class="form-label text-r">
                                        综合情况：
                                    </label>
                                </td>
                                <td>
                                    <s:textarea name="person.comprehensiveJudge"
                                                cssClass="input-text" id="input9"
                                                cssStyle="width: 800px; height: 80px; float: left;"
                                                placeholder="根据以上研判信息填写综合情况"></s:textarea>
                                </td>
                            </tr>
                        </table>
                        <div class="col-12 mb-10 c-primary f-16"
                             style="border-bottom: solid 2px #2DABF7">
                            领导批示
                        </div>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               style="line-height: 45px;">
                            <tr>
                                <td width="10%" valign="top">
                                    <label class="form-label text-r">
                                        领导批示：
                                    </label>
                                </td>
                                <td>
                                    <s:textarea name="person.leaderInstruction"
                                                cssClass="input-text" id="input9"
                                                cssStyle="width: 800px; height: 80px; float: left;"
                                                placeholder="领导批示填写"></s:textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <!--流转信息-->
                <div class="tabCon">
                    <div class="row cl">
                        <div class="col-offset-5 col-5-1 text-c" style="padding: 5px;">
                            <div
                                    style="height: 30px; width: 200px; line-height: 30px; border: solid 1px #666">
                                <s:property value="person.userRole.realname"/>
                                ：录入
                            </div>
                        </div>
                    </div>
                    <s:if test="person.judges.size>0">
                        <s:iterator value="person.judges" var="judge" status="status">
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
                                                <s:property value="#status.index+1"/>
                                                :
                                                <s:property value="reportUnit"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </s:iterator>
                    </s:if>
                    <s:if
                            test="person.comprehensiveJudge!=null&&person.comprehensiveJudge!=''">
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
                            test="person.leaderInstruction!=null&&person.leaderInstruction!=''">
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
    </div>
    <script type="text/javascript">

        $(function () {
            $.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", "0");
        });

        /*新增关系人*/
        function addgxr(title, url, w, h) {
            layer.open({
                type: 2,
                area: ['800px', '500px'],
                fix: false, //不固定
                title: title,
                maxmin: true,
                content: url
            });
        }
        $(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '10%'
            });
        });

        /*机构流转*/
        function Department_change(title, url, w, h) {
            var index = layer.open({
                type: 2,
                area: ['800px', '500px'],
                title: title,
                content: url
            });

        }
        /*案例-发布*/
        function article_start(obj, id) {
            layer.confirm('确认要发布吗？', function (index) {
                layer.msg('已发布!', {icon: 6, time: 1000});
            });
        }
        /*案例-保存*/
        function article_save(obj, id) {
            layer.confirm('确认要保存吗？', function (index) {
                layer.msg('已保存!', {icon: 6, time: 1000});
            });
        }
    </script>
    <s:hidden name="person.type" title="人员类型"></s:hidden>
    <s:hidden name="person.id" title="人员id"></s:hidden>
    <s:hidden name="person.isOutOfTime" title="是否超期办理"></s:hidden>
    <s:hidden name="person.isNew" value="0"></s:hidden>
    <s:if test="person.userRole!=null">
        <s:hidden name="person.userRole.id" title="人员子表userRoleid"></s:hidden>
    </s:if>
    <s:hidden name="person.photoImg" title="人员照片"></s:hidden>
    <s:hidden name="person.handleState" title="办理状态"></s:hidden>
    <s:hidden name="person.joinDate" title="录入时间"></s:hidden>
</form>
</body>
</html>