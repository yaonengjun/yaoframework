<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>




                            |-- Context Path --|-- Servlet Path -|--Path Info--|<br>
http://www.myserver.com     /mywebapp          /helloServlet      /hello<br>
                            |-------- Request URI  ----------------------------|<br>

<br><br>

request.getServletPath(): <%=request.getServletPath() %><br><br>
request.getQueryString(): <%=request.getQueryString() %><br><br>
request.getPathInfo(): <%=request.getPathInfo() %><br><br>
request.getRequestURI(): <%=request.getRequestURI() %><br><br>
request.getRequestURL().toString(): <%=request.getRequestURL().toString()%><br><br>

request.getRemoteHost(): <%=request.getRemoteHost() %><br><br>
request.getRemoteAddr(): <%=request.getRemoteAddr() %><br><br>
request.getServerPort(): <%=request.getServerPort() %><br><br>

</body>
</html>