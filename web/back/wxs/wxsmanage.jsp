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
        <title>微信管理</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
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
                        <h3>公众号管理</h3>
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
                            <form action="${pageContext.servletContext.contextPath}/servlet/WxsServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${wx.id}"/>
                                <input type="hidden" name="page" value="${param.page}"/>
                                <input type="hidden" name="status" value="0"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>名称</label>
                                        <input class="text-input large-input datepicker" type="text" id="name" name="name" value="${wx.name}"/>
                                        <span id="namespan" class="input-notification error png_bg"></span></p>
                                    <p>
                                        <label>appid</label>
                                        <input class="text-input large-input datepicker" type="text" id="appid" name="appid" value="${wx.appid}"/></p>
                                    <p>
                                        <label>appsecret</label>
                                        <input class="text-input large-input datepicker" type="text" id="appsecret" name="appsecret" value="${wx.appsecret}"/></p>
                                    <p>
                                        <label>token</label>
                                        <input class="text-input large-input datepicker" type="text" id="token" name="token" value="${wx.token}"/>
                                    </p>
                                    <p>
                                        <label>encodingaeskey</label>
                                        <input class="text-input large-input datepicker" type="text" id="encodingaeskey" name="encodingaeskey" value="${wx.encodingaeskey}"/>
                                    </p>
                                    <p>
                                        <label>商户号</label>
                                        <input class="text-input large-input datepicker" type="text" id="mch_id" name="mch_id" value="${wx.mch_id}"/>
                                    </p>
                                    <p>
                                        <label>商户名称</label>
                                        <input class="text-input large-input datepicker" type="text" id="send_name" name="send_name" value="${wx.send_name}"/>
                                    </p>
                                    <p>
                                        <label>支付key</label>
                                        <input class="text-input large-input datepicker" type="text" id="wxpaykey" name="wxpaykey" value="${wx.wxpaykey}"/>
                                    </p>
                                    <p>
                                        <label>p12证书</label>
                                        <iframe src="${pageContext.servletContext.contextPath}/public/upload.jsp?oldimg=${wx.certificate}" class="text-input medium-input" width="300" height="26" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe><p/>
                                        <input type="text" id="img" name="certificate" value="${wx.certificate}"/><%--图片路径--%>
                                    </p>
                                    <p>
                                        <label>提现红包祝福语</label>
                                        <input class="text-input large-input datepicker" type="text" id="wishing" name="wishing" value="${wx.wishing}"/>
                                    </p>
                                    <p>
                                        <label>提现红包备注</label>
                                        <input class="text-input large-input datepicker" type="text" id="redremark" name="redremark" value="${wx.redremark}"/>
                                    </p>
                                    <!--                                    <p>
                                                                            <label>允许退货时间(天)</label>
                                                                            <input class="text-input large-input datepicker" type="number" id="endduring" name="endduring" value="${wx.endduring}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>微商城背景色</label>
                                                                            <input class="text-input large-input datepicker" type="color" id="weishopcolor" name="weishopcolor" value="${wx.weishopcolor}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>微商城标题色</label>
                                                                            <input class="text-input large-input datepicker" type="color" id="weishoptextcolor" name="weishoptextcolor" value="${wx.weishoptextcolor}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>购物车背景色</label>
                                                                            <input class="text-input large-input datepicker" type="color" id="weimyshopcolor" name="weimyshopcolor" value="${wx.weimyshopcolor}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>购物车标题色</label>
                                                                            <input class="text-input large-input datepicker" type="color" id="weimyshoptextcolor" name="weimyshoptextcolor" value="${wx.weimyshoptextcolor}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>二维码页提示语</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="qrcodewarns" name="qrcodewarns" value="${wx.qrcodewarns}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>生成二维码提示语</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="messageqrcodewarns" name="messageqrcodewarns" value="${wx.messageqrcodewarns}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>会员标题1</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="title1" name="title1" value="${wx.title1}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>会员标题2</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="title2" name="title2" value="${wx.title2}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>会员分标题1</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="subtitle1" name="subtitle1" value="${wx.subtitle1}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>会员分标题2</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="subtitle2" name="subtitle2" value="${wx.subtitle2}"/>
                                                                        </p>
                                                                        <p>
                                                                            <label>会员分标题3</label>
                                                                            <input class="text-input large-input datepicker" type="text" id="subtitle3" name="subtitle3" value="${wx.subtitle3}"/>
                                                                        </p>-->
                                    <!--                                    <p>
                                                                            <label>状态</label>
                                                                            <input type="radio" name="status" value="0" checked="checked"/>
                                                                            不启用&nbsp;
                                                                            <input type="radio" name="status" value="1" ${"1"==wx.status?"checked='checked'":""}/>
                                                                            启用
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