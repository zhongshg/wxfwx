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
        <title>图文明细管理</title>
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
                    if ("" == document.getElementById("name").value || "" == document.getElementById("url").value) {
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
                        <h3>图文明细管理</h3>
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
                            <form action="${pageContext.servletContext.contextPath}/servlet/ItemsmServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${itemsm.id}"/>
                                <input type="hidden" id="img" name="img" value="${itemsm.img}"/><%--图片路径--%>
                                <input type="hidden" name="page" value="${param.page}"/>
                                <input type="hidden" name="orders" value="${itemsm.orders}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>名称</label>
                                        <input class="text-input large-input datepicker" type="text" id="name" name="name" value="${itemsm.name}"/>
                                        <span id="namespan" class="input-notification error png_bg"></span></p>
                                    <p>
                                        <label>描述</label>
                                        <textarea class="text-input" id="describes" name="describes" cols="79" rows="15">${itemsm.describes}</textarea>
                                    </p>
                                    <p>
                                        <label>图片上传</label>
                                        <iframe src="${pageContext.servletContext.contextPath}/public/upload.jsp?oldimg=${itemsm.img}" class="text-input medium-input" width="300" height="26" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe>
                                    </p>
                                    <p>
                                        <label>图片预览</label>
                                        <div id="result" name="result">
                                            <c:if test="${!empty itemsm.img}">
                                                <img src='${pageContext.servletContext.contextPath}${itemsm.img}' width='200' height='200'/>
                                            </c:if>
                                            <c:if test="${empty itemsm.img}">
                                                请上传图片
                                            </c:if>
                                        </div>
                                    </p>
                                    <p>
                                        <label>跳转路径</label>
                                        <select id="seturl" class="large-input">
                                            <option value="">请选择跳转路径</option>
                                            <!--c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=newstypeslist&sid=1" scope="page"/>
                                            <option value="${u}" ${itemsm.url==u?"selected='selected'":""}>分类新闻1</option-->
                                            <c:forEach items="${newssList}" var="newss">
                                                <c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=newsdemo&id=${newss.id}" scope="page"/>
                                                <option value="${u}" ${itemsm.url==u?"selected='selected'":""}>${newss.name}</option>
                                            </c:forEach>
                                            <c:forEach items="${activityList}" var="activity">
                                                <c:if test="${'1'==activity.sid}">
                                                    <c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=roulette&id=${activity.id}" scope="page"/>
                                                    <option value="${u}" ${itemsm.url==u?"selected='selected'":""}>${activity.name}</option>
                                                </c:if>
                                                <c:if test="${'2'==activity.sid}">
                                                    <c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=ggk&id=${activity.id}" scope="page"/>
                                                    <option value="${u}" ${itemsm.url==u?"selected='selected'":""}>${activity.name}</option>
                                                </c:if>
                                                <c:if test="${'3'==activity.sid}">
                                                    <c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=sge&id=${activity.id}" scope="page"/>
                                                    <option value="${u}" ${itemsm.url==u?"selected='selected'":""}>${activity.name}</option>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach items="${formsList}" var="forms">
                                                <c:set var="u" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=forms&sid=1&fid=${forms.id}" scope="page"/>
                                                <option value="${u}" ${itemsm.url==u?"selected='selected'":""}>${forms.name}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>自定义路径</label>
                                        <input class="text-input large-input datepicker" type="text" id="url" name="url" value="${itemsm.url}"/>
                                        <span id="urlspan" class="input-notification error png_bg"></span>
                                    </p>
                                    <p>
                                        <label>缩略图(群发)</label>
                                        <select id="mediaid" name="mediaid" class="large-input">
                                            <option value="">请选择缩略图</option>
                                            <c:forEach items="${materialList}" var="material">
                                                <c:if test="${'thumb'==material.type}">
                                                    <option value="${material.mediaid},${material.times}" ${itemsm.mediaid==material.mediaid?"selected='selected'":""}>${material.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>备注</label>
                                        <textarea class="text-input" id="remark" name="remark" cols="79" rows="15">${itemsm.remark}</textarea>
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