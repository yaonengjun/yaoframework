<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<%
String msg = "";
	try { 
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
		//与url指定的数据源建立连接 
			Connection c = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(FAILOVER=ON)(LOAD_BALANCE=OFF)(ADDRESS = (PROTOCOL =TCP)(HOST = 192.168.1.109)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.1.108)(PORT = 1521)))(CONNECT_DATA =(SERVICE_NAME = bill9)))", "nwkaaaout", "nwkaaaout"); 		
			msg = "true";
		} catch (Exception e) { 
			msg = "false! " + e.getMessage();
	}catch(Throwable t){
		msg = t.getMessage();
	}
	

%>

<%out.print(msg); %>
</body>
</html>