<%-- 
    Document   : users
    Created on : 2014-7-9, 9:01:38
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>素材信息管理</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                function test() {
                    if ("" == document.getElementById("name").value) {
                        return false;
                    } else if ("video" == document.getElementById("type").value && "," == document.getElementById("mediaid").value) {
                        alert("请选择视频！");
                        return false;
                    } else if ("music" == document.getElementById("type").value && "," == document.getElementById("mediaidmusic").value) {
                        alert("请选择缩略图！");
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
                        <h3>素材信息管理</h3>
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
                            <form action="${pageContext.servletContext.contextPath}/servlet/MaterialServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${material.id}"/>
                                <input type="hidden" name="page" value="${param.page}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>名称</label>
                                        <input class="text-input large-input datepicker" type="text" id="name" name="name" value="${material.name}"/>
                                        <span id="namespan" class="input-notification error png_bg"></span></p>
                                    <p>
                                        <label>类型</label>
                                        <select id="type" name="type" class="text-input large-input datepicker">
                                            <option value="video" ${"video"==material.type?"selected='selected'":""}>视频信息</option>
                                            <option value="music" ${"music"==material.type?"selected='selected'":""}>音乐信息</option>
                                        </select></p>
                                    <p>
                                        <label>视频mediaid(视频)(必选)</label>
                                        <select id="mediaid" name="mediaid" class="text-input large-input datepicker">
                                            <option value=",">请选择视频</option>
                                            <c:forEach items="${materialList}" var="materialsc">
                                                <c:if test="${'video'==materialsc.type}">
                                                    <option value="${materialsc.mediaid},${materialsc.times}" ${material.mediaid==materialsc.mediaid?"selected='selected'":""}>${materialsc.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select></p>
                                    <p>
                                        <label>缩略图mediaid(音乐)(必选)</label>
                                        <select id="mediaidmusic" name="mediaidmusic" class="text-input large-input datepicker">
                                            <option value=",">请选择缩略图</option>
                                            <c:forEach items="${materialList}" var="materialsc">
                                                <c:if test="${'thumb'==materialsc.type}">
                                                    <option value="${materialsc.mediaid},${materialsc.times}" ${material.mediaid==materialsc.mediaid?"selected='selected'":""}>${materialsc.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>标题(视频/音乐)(可选)</label>
                                        <input class="text-input large-input datepicker" type="text" id="title" name="title" value="${material.title}"/>
                                    </p>
                                    <p>
                                        <label>描述(视频/音乐)(可选)</label>
                                        <textarea class="text-input" id="description" name="description" cols="79" rows="10">${material.description}</textarea>
                                    </p>
                                    <p>
                                        <label>音乐链接(音乐)(可选)</label>
                                        <input class="text-input large-input datepicker" type="text" id="musicurl" name="musicurl" value="${material.musicurl}"/>
                                    </p>
                                    <p>
                                        <label>高质量链接(wlan音乐)(可选)</label>
                                        <input class="text-input large-input datepicker" type="text" id="hqmusicurl" name="hqmusicurl" value="${material.hqmusicurl}"/>
                                    </p>
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