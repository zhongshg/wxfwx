<%-- 
    Document   : sendmesser
    Created on : 2014-7-9, 9:01:38
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>发送客服消息</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                $(document).ready(function() {
                    $("#testbutton").click(function() {
                        var openid = $("#openid").val();
                        var lid = $("#lid").val();
                        var thumb = $("#thumb").val();
                        var remark = $("#remark").val();
                        if ("" == openid) {
                            alert("请选择接收人！");
                            return false;
                        } else if ("0,0" == lid && "" == remark) {
                            alert("请选择发送信息或输入自定义消息！");
                            return false;
//                        } else if (("video" == lid.split(",") || "thumb" == lid.split(",")) && "" == thumb) {
//                            alert("请选择缩略图！");
//                            return false;
                        } else {
                            $.post("${pageContext.servletContext.contextPath}/servlet/SendMesSerServlet?method=manage", {"openid": openid, "lid": lid, "thumb": "", "remark": remark}, function(result) {
                                if ("0" == result) {
                                    alert("发送成功！");
                                    location.reload();
                                }
                            });
                        }
                    });
                });
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
                        <h3>发送客服消息</h3>
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
                            <form action="javascript:void(0);" id="form1" method="post">
                                <input type="hidden" name="page" value="${param.page}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>发送人</label>
                                        <select id="openid" name="openid" class="large-input">
                                            <option value="">请选择</option>
                                            <c:forEach items="${subscriberList}" var="subscriber">
                                                <option value="${subscriber.openid}">${subscriber.openid} ${subscriber.nickname}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>消息</label>
                                        <select id="lid" name="lid" class="large-input">
                                            <option value="0,0">自定义消息</option>
                                            <c:forEach items="${textList}" var="text">
                                                <option value="text,${text.id}" ${"text"==natives.type&&natives.lid==text.id?"selected='selected'":""}>${text.name}(文本)</option>
                                            </c:forEach>
                                            <c:forEach items="${newsList}" var="news">
                                                <option value="news,${news.id}" ${"news"==natives.type&&natives.lid==news.id?"selected='selected'":""}>${news.name}（图文）</option>
                                            </c:forEach>
                                            <c:forEach items="${materialList}" var="material">
                                                <!--过滤群发图文-->
                                                <c:if test="${'news'!=material.type}">
                                                    <option value="${material.type},${material.id}" ${material.type==natives.type&&natives.lid==material.id?"selected='selected'":""}>${material.name}（${material.type}）</option>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach items="${permaterialList}" var="permaterial">
                                                <!--过滤群发图文-->
                                                <c:if test="${'news'!=permaterial.type}">
                                                    <option value="${permaterial.type},${permaterial.id}" ${permaterial.type==natives.type&&natives.lid==permaterial.id?"selected='selected'":""}>${permaterial.name}(${permaterial.type})</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <!--                                    <p>
                                                                            <label>缩略图(视频)</label>
                                                                            <select id="thumb" name="thumb" class="large-input">
                                                                                <option value="">请选择</option>
                                    <c:forEach items="${materialList}" var="material">
                                        <c:if test="${'thumb'==material.type}">
                                            <option value="${material.id}" ${natives.lid==material.id?"selected='selected'":""}>${material.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </p>-->
                                    <p>
                                        <label>自定义消息内容</label>
                                        <textarea class="text-input" id="remark" name="remark" cols="79" rows="15"></textarea>
                                    </p>
                                    <p id="submit">
                                        <input class="button" id="testbutton" type="button" value="发送" />
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