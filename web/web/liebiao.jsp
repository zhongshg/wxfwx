<%-- 
    Document   : liebiao
    Created on : 2015-5-27, 10:40:30
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/web/css/liebiao.css">
        <script src="${pageContext.servletContext.contextPath}/web/js/index.js"></script>
        <script src="${pageContext.servletContext.contextPath}/web/js/scroll.js"></script>
        <script src="${pageContext.servletContext.contextPath}/web/js/scrollload.js"></script>
    </head>
    <form id="form1" method="post">
        <input type="hidden" name="sid" value="${sid}"/>
        <input type="hidden" name="id" value="${newstypesid}"/>
    </form>
    <body>
        <div class="zong"> 
            <!--顶部导航--start-->
            <div class="per-top-nav">
                <div class="pa per-top-l" onclick='document.location.href = "/web/";'><img src="${pageContext.servletContext.contextPath}/web/images/10.png" width="100%" /></div>
                <p class="fs16"><img src="${pageContext.servletContext.contextPath}/web/images/logo.png" height="80%"/></p>
            </div>
            <!--顶部导航--end-->

            <center>
                <%@include file="lunbo.jsp" %>
            </center>
            <div id="divedit" class="con">
                <div id="divtop1">
                    <div id="divjrzd" class="mod">
                        <div id="ulist" class="box">
                            <c:forEach items="${pu.list}" var="map">
                                <!--特殊区域 客户案例无下级-->
                                <c:if test="${2==sid}">
                                    <a href="javascript:void(0);"> <img src="${map.img}" /> <i>${map.name}</i> </a>
                                    </c:if>
                                    <c:if test="${2!=sid}">

                                    <a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=${map.id}"> <img src="${map.img}" /> <i>${map.name}</i> </a> 

                                </c:if>
                            </c:forEach>
                            <!--                            <a href=""> <img src="images/11.png" /> <i>NEWS</i> </a> 
                                                        <a href=""> <img src="images/11.png" /> <i>LENDING GUIDELINES</i> </a> 
                                                        <a href=""> <img src="images/11.png" /><i>REQUIRED INFORMATION</i> </a>-->

                            <br/>
                        </div>
                    </div>
                </div>
            </div>
            <center>
                ${pu.forestyle}
            </center>
        </div>
    </body>
</html>