<%@ tag language="java" pageEncoding="UTF-8"%>
<jsp:doBody var="con" scope="page"/>

<%
	String con = (String)jspContext.getAttribute("con");
	String res = con.trim().replace("\n", "<br>");
%>

<%=res%>