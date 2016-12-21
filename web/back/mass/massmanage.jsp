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
        <title>群发</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                function test() {
                    if ("0,0" == document.getElementById("lid").value && "" == document.getElementById("content").value) {
                        alert("请选择需要群发的信息或填写自定义文本信息！")
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
                        <h3>群发</h3>
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
                            <form action="${pageContext.servletContext.contextPath}/servlet/MassServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="sid" name="sid" value="${sid}"/>
                                <input type="hidden" name="page" value="${param.page}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <c:if test="${'1'==sid}" var="flag">
                                        <p>
                                            <label>预览openid</label>
                                            <!--                                            <input class="text-input large-input" type="text" id="preopenid" name="preopenid" value=""/>
                                                                                        <span>不填则直接群发</span>-->
                                            <select id="preopenid" name="preopenid" class="large-input">
                                                <option value="">不预览直接群发</option>
                                                <c:forEach items="${subscriberList}" var="subscriber">
                                                    <option value="${subscriber.openid}">${subscriber.openid} ${subscriber.nickname}</option>
                                                </c:forEach>
                                            </select>
                                        </p>
                                        <p>
                                            <label>消息</label>
                                            <select id="lid" name="lid" class="large-input">
                                                <option value="0,0">自定义文本信息</option>
                                                <c:forEach items="${textList}" var="text">
                                                    <option value="text,${text.id}">${text.name}</option>
                                                </c:forEach>
                                                <c:forEach items="${materialList}" var="material">
                                                    <!--群发暂无音乐信息-->
                                                    <c:if test="${'music'!=material.type}">
                                                        <option value="${material.type},${material.id}">${material.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach items="${permaterialList}" var="permaterial">
                                                    <!--群发暂无音乐信息-->
                                                    <c:if test="${'music'!=permaterial.type}">
                                                        <option value="${permaterial.type},${permaterial.id}">${permaterial.name}(${permaterial.type})</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </p>
                                        <p>
                                            <label>自定义文本信息</label>
                                            <textarea class="text-input" id="content" name="content" cols="79" rows="15"></textarea>
                                        </p>
                                    </c:if>

                                    <c:if test="${!flag}">
                                        <!--                                        <p>
                                                                                    <label>消息</label>
                                                                                    <select id="lid" name="lid" class="large-input">
                                        <c:forEach items="${newsmList}" var="newsm">
                                            <option value="1,${newsm.id}">${newsm.name}</option>
                                        </c:forEach>
                                    </select>
                                </p>-->
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