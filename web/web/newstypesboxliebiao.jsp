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
        <style>
            .box div{
                width:32%;
                text-align:center;
                float:left;
            }
            .box a{height:170px;overflow:hidden; border-bottom:#d6d6d6 1px solid;width:auto; display:block; line-height:auto; padding:10px 8px 0 0}
            .box a img{ float:left; padding:2px; margin:6px 5px 0 6px; border:#cdcdcd 1px solid; width:100%; height:110px;}
            .box a span{font-size:18px;font-weight:bold;color:#298cda; text-decoration:none; padding:10px 0;}
            .box a i{color:#000;font-style:normal; text-decoration:none; padding-left:3px}
            .box a em{ margin-top:6px; color:#888}
        </style>
        <script src="${pageContext.servletContext.contextPath}/web/js/index.js"></script>
        <script src="${pageContext.servletContext.contextPath}/web/js/scroll.js"></script>
        <script src="${pageContext.servletContext.contextPath}/web/js/scrollload.js"></script>
    </head>
    <form id="form1" method="post">
        <input type="hidden" name="sid" value="${sid}"/>
        <input type="hidden" name="id" value="${parentid}"/>
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
                            <c:forEach items="${pu.list}" var="newstypes">
                                <c:if test="${0==newstypes.sign}" var="flag">
                                    <div><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=liebiao&&sid=${newstypes.sid}&id=${newstypes.id}"> <img src="${newstypes.img}" /> <i>${newstypes.name}</i> </a></div>
                                        </c:if>
                                        <c:if test="${!flag}">
                                    <div><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=${newstypes.sid}&id=${newstypes.id}"> <img src="${newstypes.img}" /> <i>${newstypes.name}</i> </a></div>
                                        </c:if>
                                    </c:forEach>
                            <!--           
                            <div><a href=""> <img src="images/11.png" /> <i>NEWS</i> </a> </div>
                            -->

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