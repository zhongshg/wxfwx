<%-- 
    Document   : xiangqing
    Created on : 2015-5-27, 10:47:54
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="textml; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
        <title></title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link rel="apple-touch-icon" href="favicon.ico">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/web/css/style.css">
        <script src="${pageContext.servletContext.contextPath}/web/js/index.js"></script>
        <script src="${pageContext.servletContext.contextPath}/web/js/scroll.js"></script>
        <script src="${pageContext.servletContext.contextPath}/web/js/scrollload.js"></script>
    </head>
    <body>
        <div class="zong"> 
            <!--顶部导航--start-->
            <div class="per-top-nav">
                <div class="pa per-top-l" onclick='document.location.href = "/web/";'><img src="${pageContext.servletContext.contextPath}/web/images/10.png" width="100%" /></div>
                <p class="fs16"><img src="${pageContext.servletContext.contextPath}/web/images/logo.png" height="80%"/></p>
            </div>
            <!--顶部导航--end-->

            <div class="flr"><a href="#">${map.name}</a></div>
            <dl class="lrpage">
                <dt>时间：<fmt:parseDate value="${map.times}" var="times" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                <fmt:formatDate value="${times}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></dt>

                </dl>
                <div class="read_content">
                ${map.content}
            </div>


        </div>
    </body>
</html>