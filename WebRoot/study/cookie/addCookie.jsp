<%@ page language="java" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//CookieHelper cookiehelper = new CookieHelper(request, response);
//cookiehelper.addCookie("yaonengjun", "11111");

Cookie cookie = new Cookie("yaonengjun", "11111");
cookie.setPath(path);
cookie.setDomain(request.getServerName());
cookie.setMaxAge(30);

response.addCookie(cookie);



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
    <a href="<%=basePath %>/index.jsp">index.jsp</a> <br>
  </body>
</html>
