<%-- 
    Document   : index
    Created on : 2015-10-30, 15:06:37
    Author     : Administrator
--%>

<%@page import="wap.wx.menu.WxMenuUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="wap.wx.dao.SaleMemberDAO"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>关注万巷坊微信</title>
    </head>
    <body>
        <img src="${pageContext.servletContext.contextPath}/${qrcode}" style="width:100%;position:absolute; top:0px; left:0px; ">
    </body>
</html>
