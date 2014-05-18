<%@ taglib uri="/tld/extremecomponents" prefix="ec"%>

<html>

<head>
<title>eXtremeTest</title>

<script type="text/javascript" src="./jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	$(".menu a").hover(function() {
		$(this).next("em").animate({opacity: "show", top: "-75"}, "slow");
	}, function() {
		$(this).next("em").animate({opacity: "hide", top: "-85"}, "fast");
	});


});
</script>

<style type="text/css">
.eXtremeTable {
	margin: 0;
	padding: 0;
}

.eXtremeTable select {
	font-family: Verdana;
	font-size: 9px;
	border: solid 1px #EEE;
	width: 75px;
}

.eXtremeTable .tableRegion {
	border: 1px solid silver;
	padding: 2px;
	font-family: Verdana;
	font-size: 10px;
	margin-top: 7px;
}

.eXtremeTable .filter {
	background-color: #efefef;
}

.eXtremeTable .filter input {
	font-family: Verdana;
	font-size: 10px;
	width: 100%;
}

.eXtremeTable .filter select {
	font-family: Verdana;
	font-size: 9px;
	border: solid 1px #EEE;
	width: 100%;
}

.eXtremeTable .tableHeader {
	background-color: #308dbb;
	color: white;
	font-family: Verdana;
	font-size: 11px;
	font-weight: bold;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4;
	padding-bottom: 4;
	margin: 0;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
}

.eXtremeTable .tableHeaderSort {
	background-color: #3a95c2;
	color: white;
	font-family: Verdana;
	font-size: 11px;
	font-weight: bold;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4;
	padding-bottom: 4;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
}

.eXtremeTable .odd a,.even a {
	color: Black;
	font-size: 10px;
}

.eXtremeTable .odd td,.eXtremeTable .even td {
	padding-top: 2px;
	padding-right: 3px;
	padding-bottom: 2px;
	padding-left: 3px;
	vertical-align: middle;
	font-family: Verdana;
	font-size: 10px;
}

.eXtremeTable .odd {
	background-color: #FFFFFF;
}

.eXtremeTable .even {
	background-color: #dfe4e8;
}

.eXtremeTable .highlight td {
	color: black;
	font-size: 10px;
	padding-top: 2px;
	padding-right: 3px;
	padding-bottom: 2px;
	padding-left: 3px;
	vertical-align: middle;
	background-color: #fdecae;
}

.eXtremeTable .highlight a,.highlight a {
	color: black;
	font-size: 10px;
}

.eXtremeTable .toolbar {
	background-color: #F4F4F4;
	font-family: Verdana;
	font-size: 9px;
	margin-right: 1px;
	border-right: 1px solid silver;
	border-left: 1px solid silver;
	border-top: 1px solid silver;
	border-bottom: 1px solid silver;
}

.eXtremeTable .toolbar td {
	color: #444444;
	padding: 0px 3px 0px 3px;
	text-align: center;
}

.eXtremeTable .separator {
	width: 7px;
}

.eXtremeTable .statusBar {
	background-color: #F4F4F4;
	font-family: Verdana;
	font-size: 10px;
}

.eXtremeTable .filterButtons {
	background-color: #efefef;
	text-align: right;
}

.eXtremeTable .title {
	color: #444444;
	font-weight: bold;
	font-family: Verdana;
	font-size: 15px;
	vertical-align: middle;
}

.eXtremeTable .title span {
	margin-left: 7px;
}

.eXtremeTable .formButtons {
	display: block;
	margin-top: 10px;
	margin-left: 5px;
}

.eXtremeTable .formButton {
	cursor: pointer;
	font-family: Verdana;
	font-size: 10px;
	font-weight: bold;
	background-color: #308dbb;
	color: white;
	margin-top: 5px;
	border: outset 1px #333;
	vertical-align: middle;
}

.eXtremeTable .tableTotal {
	background-color: #FFFFFF;
	border-top: solid 1px Silver;
}

.eXtremeTable .tableTotalEmpty {
	background-color: #FFFFFF;
}



.menu {
	margin: 100px 0 0;
	padding: 0;
	list-style: none;
}
.menu ec:column {
	padding: 0;
	margin: 0 2px;
	float: left;
	position: relative;
	text-align: center;
}
.menu a {
	padding: 14px 10px;
	display: block;
	color: #000000;
	width: 144px;
	text-decoration: none;
	font-weight: bold;
	background: url(.images/button.gif) no-repeat center center;
}
.menu ec:column em {
	#background: url(.images/hover.png) no-repeat;
	background: url(.images/detail.jpg) no-repeat;
	width: 690px;
	height: 517px;
	position: absolute;
	top: -85px;
	left: -15px;
	text-align: center;
	padding: 20px 12px 10px;
	font-style: normal;
	z-index: 2;
	display: none;
	
}

</style>

</head>

<%
	java.util.List presidents = new java.util.ArrayList();
%>

<%
	java.util.Map president = new java.util.HashMap();
%>
<%
	president.put("name", "George Washington");
%>
<%
	president.put("nickname", "Father of His Country");
%>
<%
	president.put("term", "1789-1797");
%>
<%
	presidents.add(president);
%>

<%
	president = new java.util.HashMap();
%>
<%
	president.put("name", "John Adams");
%>
<%
	president.put("nickname", "Atlas of Independence");
%>
<%
	president.put("term", "1797-1801");
%>
<%
	presidents.add(president);
%>

<%
	president = new java.util.HashMap();
%>
<%
	president.put("name", "Thomas Jefferson");
%>
<%
	president.put("nickname", "Man of the People, Sage of Monticello");
%>
<%
	president.put("term", "1801-09");
%>
<%
	presidents.add(president);
%>

<%
	president = new java.util.HashMap();
%>
<%
	president.put("name", "James Madison");
%>
<%
	president.put("nickname", "Father of the Constitution");
%>
<%
	president.put("term", "1809-17");
%>
<%
	presidents.add(president);
%>

<%
	president = new java.util.HashMap();
%>
<%
	president.put("name", "James Monroe");
%>
<%
	president.put("nickname", "The Last Cocked Hat, Era-of-Good-Feelings President");
%>
<%
	president.put("term", "1817-25");
%>
<%
	presidents.add(president);
%>

<%
	president = new java.util.HashMap();
%>
<%
	president.put("name", "John Adams");
%>
<%
	president.put("nickname", "Old Man Eloquent");
%>
<%
	president.put("term", "1825-29");
%>
<%
	presidents.add(president);
%>

<%
	request.setAttribute("pres", presidents);
%>

<body style="margin: 25px;">

	<p style="font-family: Verdana; font-size: 14px;">Congratulations!! You have successfully configured eXtremeTable!
	</p>

	<br>

	<% int i = 1; %>
	<ec:table  items="pres" action="./test2.jsp" imagePath="./images/*.gif"  title="Presidents" width="60%" rowsDisplayed="5" >
		<ec:row styleClass="menu" >
			<ec:column property="name"> <img title="./images/detail.jpg" src ="./images/xhs.jpg" width="" /> </ec:column>
			<ec:column property="nickname" />
			<ec:column property="term" />
		</ec:row>
	</ec:table>

	<br>

	<p style="font-family: Verdana; font-size: 12px">Below is the code that generates the above display.</p>

	<pre class="bodyText" style="background-color: #eee; padding: 2px; width: 60%; font-family: Verdana; font-size: 11px;">
	&lt;ec:table 
		items="pres"
		action="./test1.jsp"
		imagePath="./images/*.gif"
		title="Presidents"
		width="60%"
		rowsDisplayed="5"
		&gt;
		&lt;ec:row&gt;
			&lt;ec:column property="name"/&gt;
			&lt;ec:column property="nickname"/&gt;
			&lt;ec:column property="term"/&gt;
		&lt;/ec:row&gt;
	&lt;/ec:table&gt;
</pre>

	<br>

	<p style="font-family: Verdana; font-size: 11px; width: 500px">Note: if you are not seeing any images then be sure
		to include the images included with the distribution. This example assumes that the images are in an /images/table/
		directory. You can see this by looking at the imagePath attribute on the eXtremeTable example. If your images are
		somewhere else then just adjust the imagePath.</p>

</body>
</html>