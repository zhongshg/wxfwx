<%-- 
    Document   : menusmanage
    Created on : 2014-7-23, 9:42:48
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>自定义菜单管理</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                $(document).ready(function() {
                    $("#seturl").val($("#url").val());
                    $("#seturl").change(function() {
                        $("#url").val($("#seturl").val());
                    });
                });
                function test() {
                    if ("" == document.getElementById("name").value) {
                        return false;
                    }
                }
            </script>
        </head>
        <body>
            <div id="body-wrapper">
                <div id="main-content">
                    <!-- Main Content Section with everything -->
                    <noscript>
                        <!-- Show a notification if the user has disabled javascript -->
                        <div class="notification error png_bg">
                            <div> Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
                                Download From <a href="http://www.exet.tk">exet.tk</a></div>
                        </div>
                    </noscript>
                    <!-- Page Head -->
                    <p id="page-intro">What would you like to do?</p>
                    <ul class="shortcut-buttons-set">
                        <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/image_add_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/clock_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/comment_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                </ul>
                <!-- End .shortcut-buttons-set -->
                <div class="clear"></div>
                <!-- End .clear -->
                <div class="content-box">
                    <!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>自定义菜单管理</h3>
                        <ul class="content-box-tabs">
                            <!--<li><a href="#tab1">Table</a></li>-->
                            <!-- href must be unique and match the id of target div -->
                            <li><a href="#tab2" class="default-tab">Forms</a></li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <div class="tab-content default-tab" id="tab2">
                            <form action="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${menus.id}"/>
                                <input type="hidden" id="parentid" name="parentid" value="${menus.parentid}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <!--//id,menukey,name,parentid,menutype,url,sendid,sendtype-->
                                    <p>
                                        <label>名称</label>
                                        <input class="text-input large-input" type="text" id="name" name="name" value="${menus.name}" />
                                        <span id="namespan" class="input-notification error png_bg"></span>
                                    </p>
                                    <p>
                                        <label>事件类型</label>
                                        <select name="menutype" class="large-input">
                                            <option value="0" ${"0"==menus.menutype?"selected='selected'":""}>发送信息</option>
                                            <option value="1" ${"1"==menus.menutype?"selected='selected'":""}>跳转网页</option>
                                        </select>
                                    </p>
                                    <p>
                                        <label>发送信息</label>
                                        <select name="sendid" class="large-input">
                                            <option value="0,0">请选择信息</option>
                                            <option value="qrcode,0" ${menus.sendid==0&&menus.sendtype=="qrcode"?"selected='selected'":""}>二维码</option>
                                            <c:forEach items="${textList}" var="text">
                                                <option value="text,${text.id}" ${"text"==menus.sendtype&&menus.sendid==text.id?"selected='selected'":""}>${text.name}(文本)</option>
                                            </c:forEach>
                                            <c:forEach items="${newsList}" var="news">
                                                <option value="news,${news.id}" ${"news"==menus.sendtype&&menus.sendid==news.id?"selected='selected'":""}>${news.name}（图文）</option>
                                            </c:forEach>
                                            <c:forEach items="${materialList}" var="material">
                                                <!--过滤群发图文-->
                                                <c:if test="${'news'!=material.type}">
                                                    <option value="${material.type},${material.id}" ${material.type==menus.sendtype&&menus.sendid==material.id?"selected='selected'":""}>${material.name}（${material.type}）</option>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach items="${permaterialList}" var="permaterial">
                                                <!--过滤群发图文-->
                                                <c:if test="${'news'!=permaterial.type}">
                                                    <option value="${permaterial.type},${permaterial.id}" ${permaterial.type==menus.sendtype&&menus.sendid==permaterial.id?"selected='selected'":""}>${permaterial.name}(${permaterial.type})</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>跳转路径</label>
                                        <select id="seturl" class="large-input">
                                            <option value="">请选择跳转路径</option>
                                            <c:forEach items="${newssList}" var="newss">
                                                <c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=newsdemo&id=${newss.id}#mp.weixin.qq.com" scope="page"/>
                                                <option value="${u}" ${menus.url==u?"selected='selected'":""}>${newss.name}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>自定义路径</label>
                                        <input class="text-input large-input" type="text" id="url" name="url" value="${menus.url}" />
                                    </p>
                                    <c:if test="${0==menus.parentid}" var="isSub">
                                        <p>
                                            <label>排序</label>
                                            <select name="orders" class="large-input">
                                                <option value="0">默认</option>
                                                <c:forEach items="${orderList}" var="orders">
                                                    <option value="${orders}" ${menus.orders==orders?"selected='selected'":""}>${orders}</option>
                                                </c:forEach>
                                            </select>
                                        </p>
                                    </c:if>
                                    <c:if test="${!isSub}">
                                        <input type="hidden" name="orders" value="${menus.orders}"/>
                                    </c:if>
                                    <p id="submit">
                                        <input class="button" id="testbutton" type="submit" value="提交" />
                                    </p>
                                </fieldset>
                                <div class="clear"></div>
                                <!-- End .clear -->
                            </form>
                        </div>
                        <!-- End #tab2 -->
                    </div>
                    <!-- End .content-box-content -->
                </div>
                <!-- End .content-box -->

                <div class="clear"></div>
                <!-- Start Notifications -->
<!--                <div class="notification attention png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                    <div> Attention notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero. </div>
                </div>-->
                <!-- End Notifications -->
                <%@include file="../../public/foot.jsp" %>
                <!-- End #footer -->
            </div>
            <!-- End #main-content -->
        </div>
    </body>
    <!-- Download From www.exet.tk-->
</html>