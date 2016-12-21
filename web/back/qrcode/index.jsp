<%-- 
    Document   : index
    Created on : 2015-12-1, 16:47:44
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>生成带参数二维码</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
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
                        <h3>生成带参数二维码</h3>
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
                            <fieldset>
                                <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                <p>
                                    <label>二维码类型</label>
                                    <select id="action_name" class="large-input">
                                        <option value="QR_SCENE">临时二维码</option>
                                        <option value="QR_LIMIT_SCENE">永久二维码</option>
                                        <option value="QR_LIMIT_STR_SCENE">永久字符串参数二维码</option>
                                    </select>
                                </p>
                                <p>
                                    <label>参数（场景值）</label>
                                    <input class="text-input large-input datepicker" type="text" id="scene_id" name="scene_id" value=""/>
                                </p>
                                <p id="submit">
                                    <input class="button" id="testbutton" type="button" value="生成" />
                                </p>
                                <p id="message">
                                </p>
                            </fieldset>
                            <div class="clear"></div>
                            <!-- End .clear -->
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
        <script>
            $(document).ready(function() {
                $("#testbutton").click(function() {
                    var action_name = $("#action_name option:selected").val();
                    var scene_id = $("#scene_id").val();
                    if ("" == scene_id) {
                        alert("请输入场景值");
                        return false;
                    } else if (("QR_SCENE" == action_name || "QR_LIMIT_SCENE" == action_name) && !/^[0-9]*$/.test(scene_id)) {
                        alert("该类型仅支持数字参数，永久二维码1--100000");
                        return false;
                    } else {
                        $.post("${pageContext.servletContext.contextPath}/servlet/PublicServlet?method=getqrcode", {"action_name": action_name, "scene_id": scene_id}, function(result) {
                            $("#message").html("<img src=\"/" + result + "\" width=\"300px\" height=\"300px\"/>");
                        });
                    }
                });
            });
        </script>
    </body>
    <!-- Download From www.exet.tk-->
</html>