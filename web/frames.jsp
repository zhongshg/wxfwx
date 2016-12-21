<%-- 
    Document   : iframes
    Created on : 2014-7-7, 11:32:13
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/img/logourl.png" rel="Shortcut Icon">
        <title>微信公众平台微营销后台管理系统</title>
    </head>

    <frameset cols="230,*" frameborder="no" border="0" framespacing="0">
        <frame src="${pageContext.servletContext.contextPath}/UsersServlet?method=getLeftPowers&count=${count}" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
        <frame src="${pageContext.servletContext.contextPath}/main/main.jsp" name="mainFrame" scrolling="yes" id="mainFrame" title="mainFrame" />
    </frameset>
</html>
