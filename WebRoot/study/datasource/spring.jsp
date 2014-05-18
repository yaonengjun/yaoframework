<!-- 
	从Spring的定义的数据源中获取DB的Connection。
	该代码依赖的Spring版本是2.5
	
	以下是本代码对应的Spring数据源的bean。
	<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
		<property name="jndiName" value="jdbc/aaaDS" />
	</bean>
 -->

<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.sql.DataSource"%>
<%@page import="org.springframework.jdbc.datasource.DataSourceUtils"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<%

ApplicationContext ctx = null;
Connection con = null;  
String errorMessage = "";
try {
ctx = WebApplicationContextUtils.getWebApplicationContext(application);
con = DataSourceUtils.getConnection((DataSource)ctx.getBean("dataSource"));
}catch(Throwable t) {
	errorMessage = t.getMessage();
	
}
	

%>

<%out.println("Spring context: " + ctx); %>
<%out.println(); %><br><br>

<%out.println("(DataSource)ctx.getBean(\"dataSource\"): " + (DataSource)ctx.getBean("dataSource")); %>
<%out.println(); %><br><br>


<%out.println("con: " + con); %>
<%out.println(); %><br><br>

<%out.println("errorMessage: " + errorMessage); %>
<%out.println(); %><br><br>

</body>
</html>