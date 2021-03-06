<%-- 
    Document   : users
    Created on : 2014-7-9, 9:01:38
    Author     : Administrator
--%>

<%@page import="wap.wx.util.WxReader"%>
<%@page import="wap.wx.dao.SendredpacklogsDAO"%>
<%@page import="wap.wx.menu.WxPayUtils"%>
<%@page import="java.util.Date"%>
<%@page import="wap.wx.menu.WxMenuUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="wap.wx.dao.SubscriberDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>发送红包</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
        </head>
    <%
        List<Map<String, String>> subscriberList = new SubscriberDAO().getList();
        String method = request.getParameter("method");
        if ("sendredpack".equals(method)) {
            String openid = request.getParameter("openid");
            openid = "0".equals(openid) ? request.getParameter("re_openid") : openid;
            String total_amount = String.valueOf(Float.parseFloat(request.getParameter("total_amount")) * 100);
            Map<String, String> map = new HashMap<String, String>();
            map.put("act_name", request.getParameter("act_name"));
            map.put("wishing", request.getParameter("wishing"));
            map.put("mch_billno", WxReader.getWxInfo().get("mch_id") + WxMenuUtils.format2.format(new java.util.Date()) + String.valueOf((int) ((Math.random() * 9 + 1) * 1000)));
            map.put("remark", request.getParameter("remark"));
            map = new WxPayUtils().sendredpack2(request, map, openid, total_amount, request.getRemoteAddr());
            if (null != map.get("mch_billno")) {
                //成功处理
                map.put("openid", openid);
                map.put("nick_name", "小狗队");
                map.put("send_name", "小狗队");
                map.put("total_amount", total_amount);
                map.put("client_ip", request.getRemoteAddr());
                map.put("send_listid", map.get("send_listid"));
                map.put("send_time", WxMenuUtils.format.format(new Date()));
                map.put("state", "1");
                map.put("prizeid", "0");
                map.put("times", WxMenuUtils.format.format(new Date()));
                map.put("mch_billno_send", map.get("mch_billno"));
                map.put("isexp", "1");
                map.put("exptimes", WxMenuUtils.format.format(new Date()));
                new SendredpacklogsDAO().add(map);
                out.print("<script>alert('发送成功!')</script>");
            } else {
                out.print("<script>alert('发送失败," + map.get("return_msg") + "!')</script>");
            }
        }
    %>
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
                        <h3>发送红包</h3>
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
                            <form action="?method=sendredpack" id="form1" method="post" onsubmit="return test();">
                                <fieldset>
                                    <p>
                                        <label>接收用户</label>
                                        <select id="openid" name="openid" class="large-input">
                                            <option value="0">自定义openid</option>
                                            <%
                                                for (Map<String, String> subscriber : subscriberList) {
                                            %>
                                            <option value="<%=subscriber.get("openid")%>"><%=subscriber.get("openid")%>&nbsp;<%=subscriber.get("nickname")%>&nbsp;<img src="<%=subscriber.get("headimgurl")%>" width="30px" height="20px"/></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </p>
                                    <p>
                                        <label>自定义openid</label>
                                        <input class="text-input large-input datepicker" type="text" id="re_openid" name="re_openid" value=""/>
                                    </p>
                                    <p>
                                        <label>发送金额(元，介于1~200之间)</label>
                                        <input class="text-input large-input datepicker" type="number" id="total_amount" name="total_amount" value=""/>
                                    </p>
                                    <p>
                                        <label>活动名称</label>
                                        <input class="text-input large-input datepicker" type="text" id="act_name" name="act_name" value=""/>
                                    </p>
                                    <p>
                                        <label>红包祝福语</label>
                                        <textarea class="text-input" id="wishing" name="wishing" cols="29" rows="15"></textarea>
                                    </p>
                                    <p>
                                        <label>备注</label>
                                        <textarea class="text-input" id="remark" name="remark" cols="29" rows="15">小狗队</textarea>
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
        <script>
                                $(document).ready(function() {
                                    $("#openid").change(function() {
                                        $("#re_openid").val($("#openid option:selected").val());
                                    });
                                });
                                function test() {
                                    var openid = document.getElementById("openid").value;
                                    var re_openid = document.getElementById("re_openid").value;
                                    var total_amount = document.getElementById("total_amount").value;
                                    var act_name = document.getElementById("act_name").value;
                                    var wishing = document.getElementById("wishing").value;
                                    var remark = document.getElementById("remark").value;
                                    if ("0" == openid && ("" == re_openid || "0" == re_openid)) {
                                        alert("请填写或选择接收用户openid!");
                                        return false;
                                    } else if ("" == total_amount || Number(total_amount) < 1 || Number(total_amount) > 200) {
                                        alert("红包金额介于1元~200元之间！");
                                        return false;
                                    } else if ("" == act_name) {
                                        alert("请填写活动名称！");
                                        return false;
                                    } else if ("" == wishing) {
                                        alert("请填写红包祝福语！");
                                        return false;
                                    } else if ("" == remark) {
                                        alert("备注不能为空！");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
        </script>
    </body>
    <!-- Download From www.exet.tk-->
</html>