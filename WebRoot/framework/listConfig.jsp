<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Map"%>
<%@page import="org.oursight.framework.yao.base.service.DataService"%>
<%@page import="org.oursight.framework.yao.base.data.bo.Config"%>
<%@page import="org.oursight.framework.yao.base.data.access.PageImpl"%>
<%@page import="org.oursight.framework.yao.base.data.access.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.oursight.framework.yao.util.RequestUtil"%>
<%@page import="org.oursight.framework.yao.util.StringHelper"%><html>

<%
	String key = RequestUtil.getParameterAndTrim(request, "key");
	List list = null;
	if(StringHelper.isEmpty(key)) {
		Page pageImpl = new PageImpl();
		Config conf = new Config();
		Map result = pageImpl.buildPagination((Object)conf, pageImpl, conf.getPageNumber(),
				"#", new String[] { "id" },
				new boolean[] { false });
		list = (List)result.get("partList");
		System.out.println(list);
	}else {
		Config conf = new Config();
		conf.setKey(key);
		DataService.getCommonDao().insert(conf);
		response.sendRedirect("listConfig.jsp");
	}
		
	
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>еДжцнд╪Ч</title>
</head>
<body>
<form action="listConfig.jsp" method="post">
<input name="key" >
<input type="submit" value="submit" ><br><br>

<%
	if(list != null) {
		for(int i = 0; i < list.size(); i++) {
			Config entry = (Config)list.get(i);
%>
<%=entry.getKey() %><br>	
<%
		}
	}
%>
</form>
</body>
</html>