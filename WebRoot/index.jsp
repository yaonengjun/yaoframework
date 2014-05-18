<%@ page language="java" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.trs.idm.util.CookieHelper"%><html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
	<%= java.util.HashMap.class.getResource("/User.hbm.xml") %>


  	<br>
  	<br>
  	
  
    <a href="login.jsp">login.jsp</a> <br>
    <a href="framework/listConfig.jsp">listConfig.jsp</a> <br>
    <a href="study/javaee/servletpath.jsp?para=aaaa">study/javaee/servletpath.jsp?para=aaaa</a> <br>
    <a href="study/cookie/addCookie.jsp">通过Server端在Response里放置Cookie：study/cookie/addCookie.jsp</a> <br>
    <a href="study/cookie/addCookieByJs.jsp">通过JS放置：study/cookie/addCookieByJs.jsp</a> <br>
    <a href="study/cookie/readCookie.jsp">study/cookie/readCookie.jsp</a> <br>
    <a href="study/cookie/removeCookie.jsp">study/cookie/removeCookie.jsp</a> <br>
  </body>
</html>
