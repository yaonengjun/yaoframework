<!-- 
	���ڻ�����JVM�����������ж����JNDI����Դ�л�ȡConnection��

 -->
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>

<%
String errMsg = "";
Connection con = null;
try {
	Context ic = new InitialContext();
	DataSource source = (DataSource)ic.lookup("java:comp/env/jdbc/test"); 
	// ynj,20110324����Web����������JNDI����Դʱ���ƺ���Ҫ������д������̫ȷ����
	// DataSource source = (DataSource)ic.lookup("jdbc/test"); 
	con = source.getConnection(); 
} catch (NamingException e) {
	errMsg = "����Դû�ҵ�! " + e.getMessage();
	
}catch (SQLException e) {
	errMsg = "��ȡ�����Ӷ���ʧ��! " + e.getMessage();
}

%>

<body>

<%out.print("ErrorMessage: " + errMsg); %>
<%out.print("Connection: " + con); %>


</body>
</html>