<%@ page language="java" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">

<title>eXremeTable测试汇总页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>


	<br>
	<br>


	<a href="<%=path%>/study/extremetable/test.jsp">eXtremeTable官方自带的实例页面</a>
	<br>
	<a href="<%=path%>/study/extremetable/test1.jsp">自己修改的eXtremeTable页面</a>
	<br>
	<a href="<%=path%>/study/extremetable/test2.jsp">试图在eXtremeTable中显示 </a>
	<br>
</body>
</html>
