<%-- 
    Document   : index
    Created on : 2015-5-27, 10:47:23
    Author     : Administrator
--%>

<%@page import="java.util.HashMap"%>
<%@page import="wap.wx.dao.NewsDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <p class="fs16"><img src="images/logo.png" height="80%"/></p>
            </div>
            <!--顶部导航--end-->
            <%
                Map<String, String> gonggao = new HashMap<String, String>();
                gonggao.put("id", "17");
                gonggao = new NewsDAO().getById(gonggao);
            %>
            <center>
                <%@include file="lunbo.jsp" %>
                <div class="gonggao"><span><img src="images/09.png" width="30"/></span><span>公告：</span>
                    <p><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=<%=gonggao.get("id")%>"><%=gonggao.get("name")%></a></p></div>
                <table width="90%" border="0" cellspacing="0" cellpadding="0" id="sybk">
                    <tr>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=2"><img alt="" src="images/01.png" title=""></a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=2"><img alt="" src="images/02.png" title=""></a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=3"><img alt="" src="images/03.png" title=""></a></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=2">产品介绍</a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=2">客户案例</a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=3">精彩瞬间</a></td>
                    </tr> <tr>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=22"><img alt="" src="images/04.png" title=""></a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=4"><img alt="" src="images/05.png" title=""></a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=5"><img alt="" src="images/06.png" title=""></a></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=22">物业功能</a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=4">新闻动态</a></td>
                        <td align="center"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=newstypesliebiao&sid=5">业务合作</a></td>
                    </tr>
                </table>
            </center>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="syzh">
                <tr>
                    <td width="17%" align="right"><img src="images/07.png" width="40"/></td>
                    <td width="33%"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=1">关于我们&gt;&gt;</a></td>
                    <td width="14%" align="right"><img src="images/08.png" width="40"/></td>
                    <td width="36%"><a href="${pageContext.servletContext.contextPath}/ForeServlet?method=xiangqing&id=3">联系方式&gt;&gt;</a></td>
                </tr>
            </table>

        </div>
    </body>
</html>