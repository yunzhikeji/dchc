<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
	<head>
		<title>导入文件</title>

		<link href="css/Style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/function.js"></script>
	</head>

	<body>
		<s:form action="injurycaseAction!importdata" method="post"
			enctype="multipart/form-data">
			<s:hidden name="itype"></s:hidden>
			<br>
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td class="ta_01" align="center" background="images/b-info.gif"
						colspan="4">
						<font face="宋体" size="2"><strong>Excel文件数据导入</strong> </font>
					</td>
				</tr>
				<tr>
					<td width="1%" height=30></td>
					<td width="20%"></td>
					<td width="78%"></td>
					<td width="1%"></td>
				</tr>
				<tr>
					<td width="1%"></td>
					<td width="15%" align="center">
						请选择文件:
					</td>
					<td width="83%" align="left">
						<input type="file" name="injurycase_file" style="width: 365px" />
					</td>
					<td width="1%"></td>
				</tr>
				<tr height=50>
					<td colspan=4>
						<a href="template/injurycase_import.xls">下载导入excel模板</a><span
							style="color: red;">&nbsp;注意：请下载模板，输入数据导入</span>
					</td>
				</tr>
				<tr height=2>
					<td colspan=4 background="images/b-info.gif"></td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr>
					<td align="center" colspan=4>
						<s:submit name="import" value="导入"
							cssStyle="width: 60px; font-size:12px; color:black; height=22"></s:submit>
						<INPUT type="button" name="Reset1" id="save" value="关闭"
							onClick="childPage_close();"
							style="width: 60px; font-size: 12px; color: black;">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
