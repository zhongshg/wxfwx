<%-- 
    Document   : newsmanage
    Created on : 2014-7-29, 10:06:49
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>新闻分类管理</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
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
                        <h3>新闻分类管理</h3>
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
                            <form action="${pageContext.servletContext.contextPath}/servlet/NewstypesServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${newstypes.id}"/>
                                <input type="hidden" id="wxid" name="wxid" value="${wx.id}"/>
                                <input type="hidden" id="img" name="img" value="${newstypes.img}"/><%--图片路径--%>
                                <input type="hidden" id="sid" name="sid" value="${param.sid}"/>
                                <input type="hidden" name="page" value="${param.page}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>名称</label>
                                        <input class="text-input large-input datepicker" type="text" id="name" name="name" value="${newstypes.name}"/>
                                        <span id="namespan" class="input-notification error png_bg"></span></p>
                                    <p>
                                        <label>图片上传</label>
                                        <iframe src="${pageContext.servletContext.contextPath}/public/upload.jsp?oldimg=${newstypes.img}" class="text-input medium-input" width="300" height="26" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe>
                                    </p>
                                    <p>
                                        <label>图片预览</label>
                                        <div id="result" name="result">
                                            <c:if test="${!empty newstypes.img}">
                                                <img src='${pageContext.servletContext.contextPath}${newstypes.img}' width='200' height='200'/>
                                            </c:if>
                                            <c:if test="${empty newstypes.img}">
                                                请上传图片
                                            </c:if>
                                        </div>
                                    </p>
                                    <p>
                                        <label>上级</label>
                                        <select name="parentid" class="large-input">
                                            <option value="0">无</option>
                                            <c:forEach items="${list}" var="newstypess">
                                                <option value="${newstypess.id}" ${newstypes.parentid==newstypess.id?"selected='selected'":""}>${newstypess.name}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <c:if test="${10==param.sid}">
                                        <p>
                                            <label>二维码页提示语</label>
                                            <input class="text-input large-input datepicker" type="text" id="qrcodewarns" name="qrcodewarns" value="${wx.qrcodewarns}"/>
                                        </p>
                                        <p style="display: none;">
                                            <label>二维码页提示语(字体)</label>
                                            <input class="text-input large-input datepicker" type="text" id="qrcodewarnsfontstyle" name="qrcodewarnsfontstyle" value="${wx.qrcodewarnsfontstyle}"/>
                                        </p>
                                        <p>
                                            <label>二维码页提示语(颜色)</label>
                                            <input class="text-input large-input datepicker" type="color" id="qrcodewarnsfontcolor" name="qrcodewarnsfontcolor" value="${wx.qrcodewarnsfontcolor}"/>
                                        </p>
                                        <p>
                                            <label>二维码页提示语(字号)</label>
                                            <input class="text-input large-input datepicker" type="tel" id="qrcodewarnsfontsize" name="qrcodewarnsfontsize" value="${wx.qrcodewarnsfontsize}"/>
                                        </p>
                                        <p>
                                            <label>生成二维码提示语</label>
                                            <input class="text-input large-input datepicker" type="text" id="messageqrcodewarns" name="messageqrcodewarns" value="${wx.messageqrcodewarns}"/>
                                        </p>
                                    </c:if>
                                    <p>
                                        <label>备注</label>
                                        <input class="text-input large-input datepicker" type="text" id="remark" name="remark" value="${newstypes.remark}"/></p>
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