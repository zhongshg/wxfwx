<%-- 
    Document   : top
    Created on : 2015-5-27, 10:41:32
    Author     : Administrator
--%>

<%@page import="wap.wx.dao.NewstypesDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="topPic">
    <div class="imgSlideMain">
        <div id="imgSlide" data-role="slide" class="imgSlide" style="overflow: hidden; ">
            <ul style="list-style: none; margin: 0px; -webkit-transition: 400ms; -webkit-transform: translate3d(-640px, 0px, 0px); ">
                <%
                    List<Map<String, String>> listlunbo = new NewstypesDAO().getList("6");
                    for (Map<String, String> maplunbo : listlunbo) {
                %>
                <li style=" "> <a href="${pageContext.servletContext.contextPath}<%=maplunbo.get("remark")%>"> <img src="${pageContext.servletContext.contextPath}<%=maplunbo.get("img")%>" width="100%" data-src="${pageContext.servletContext.contextPath}<%=maplunbo.get("img")%>"></a>
                    <h2><%=maplunbo.get("name")%></h2>
                </li>
                <%                            }
                %>
            </ul>
        </div>
        <ul class="navSlide">
            <%
                int i = 0;
                for (Map<String, String> maplunbo : listlunbo) {
                    i++;
            %>
            <li class="i_point <%=(listlunbo.size() - 1) == i ? "active" : ""%>"></li>
                <%                            }
                %>
        </ul>
    </div>
</div>
