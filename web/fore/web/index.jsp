<%-- 
    Document   : index
    Created on : 2015-12-28, 8:41:20
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
        <title>微官网</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/fore/web/css/css.css">
        <style>
            /*.ld010 img{ width:50%;}*/
        </style>
    </head>
    <!--ForeServlet?method=wx2web&sid=12-->
    <body>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="10">&nbsp;</td>
                <td>
                    <img src="${pageContext.servletContext.contextPath}${logo}" width="100%" height=""/>
                    <c:forEach items="${mapsList}" var="map" varStatus="s">
                        <a href="${pageContext.servletContext.contextPath}ForeServlet?method=wx2webnews&sid=${sid}&id=${map.id}">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ld010">
                                <tr>
                                    <td>
                                        <div class="ld012_s">
                                            <div class="${1==s.count%2?"ld012_x":"ld012_x1"}">${map.name}</div>
                                            <img src="${pageContext.servletContext.contextPath}${map.img}" height="204" style="float:${1==s.count%2?'right':'left'};"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                </tr>
                            </table>
                        </a>
                    </c:forEach>
                    <!--                    <a href="">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ld010">
                                                <tr>
                                                    <td><div class="ld012_s">
                                                            <div class="ld012_x1">古风雅韵</div>
                                                            <img src="images/112.jpg" width="100%" height="204"/></div></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </a>
                                        <a href="">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ld010">
                                                <tr>
                                                    <td><div class="ld012_s">
                                                            <div class="ld012_x2">人生体验</div>
                                                            <img src="images/112.jpg" width="100%" height="204"/></div></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                </tr>
                                            </table></a>-->
                </td>
                <td width="10">&nbsp;</td>
            </tr>
        </table>
    </body>
</html>
