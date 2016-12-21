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
        <title>应用管理</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/activity.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/wdp/WdatePicker.js" charset="utf-8" defer="defer"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                function test() {
                    if ("" == $("#name").val() || "" == $("#nick_name").val() || "" == $("#send_name").val() || !/^\d+$/.test($("#num").val())) {//|| !/^\d+$/.test($("#money").val())
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
                        <h3>应用管理</h3>
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
                            <form action="${pageContext.servletContext.contextPath}/servlet/SendredpackServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${activity.id}"/>
                                <input type="hidden" name="page" value="${param.page}"/>
                                 <!--<input type="hidden" id="img" name="img" value="${activity.img}"/>-->
                                <%--图片路径--%>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>活动名称*</label>
                                        <input class="text-input large-input datepicker" type="text" id="name" name="name" value="${activity.name}"/>
                                        <span id="namespan" class="input-notification error png_bg"></span>
                                    </p>
                                    <p>
                                        <label>红包类型</label>
                                        <input type="radio" name="type" value="0" checked="checked"/>普通红包&nbsp;<input type="radio" name="type" value="1" ${"1"==activity.type?"checked='checked'":""}/>拼手气红包(其他)
                                    </p>
                                    <input type="hidden" id="nick_name" name="nick_name" value="万巷坊"/>
                                    <!--                                    <p>
                                                                            <label>提供方名称*</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="nick_name" name="nick_name" value="${activity.nick_name}"/>
                                                                        </p>-->
                                    <p>
                                        <label>商户名称*</label>
                                        <input class="text-input large-input datepicker" type="text" id="send_name" name="send_name" value="${activity.send_name}"/>
                                    </p>
                                    <p>
                                        <label>付款总金额(分，普通红包可用)*</label>
                                        <input class="text-input large-input datepicker" type="number" id="money" name="money" value="${activity.money}"/>
                                    </p>
                                    <p>
                                        <label>预计发放人数*</label>
                                        <input class="text-input large-input datepicker" type="text" id="num" name="num" value="${activity.num}"/>
                                    </p>
                                    <p>
                                        <label>红包祝福语</label>
                                        <input class="text-input large-input datepicker" type="text" id="wishing" name="wishing" value="${activity.wishing}"/>
                                    </p>
                                    <!--                                    <p>
                                                                            <label>图片上传</label>
                                                                            <iframe src="${pageContext.servletContext.contextPath}/public/upload.jsp" class="text-input medium-input" width="300" height="26" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe>
                                                                        </p>
                                                                        <p>
                                                                            <label>图片预览</label>
                                                                            <div id="result" name="result">
                                    <c:if test="${!empty activity.img}">
                                        <img src='${pageContext.servletContext.contextPath}${activity.img}' width='200' height='200'/>
                                    </c:if>
                                    <c:if test="${empty activity.img}">
                                        请上传图片
                                    </c:if>
                                </div>
                            </p>-->
                                    <p>
                                        <label>开始时间</label>
                                        <input type="text" id="starttime" name="starttime" value="${activity.starttime}" onfocus="WdatePicker({skin: 'whyGreen', dateFmt: 'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
                                    </p>
                                    <p>
                                        <label>结束时间</label>
                                        <input type="text" id="endtime" name="endtime" value="${activity.endtime}" onfocus="WdatePicker({skin: 'whyGreen', dateFmt: 'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
                                    </p>
                                    <p>
                                        <label>备注</label>
                                        <textarea class="text-input" id="remark" name="remark" cols="79" rows="5">${activity.remark}</textarea>
                                    </p>
                                    <input type="hidden" id="img" name="img" value=""/>
                                    <!--                                    <p>
                                                                            <label>商户logo的url</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="img" name="img" value="${activity.img}"/>
                                                                        </p>-->
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