<%-- 
    Document   : enrol
    Created on : 2015-3-9, 11:16:06
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
        <title>在线报名</title>        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/page_mp_article_improve_combo24f185.css">
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <style>
                form .fore-input {
                    width: 93% !important;
                    font-size: 16px !important;
                    padding: 6px !important;
                    margin-left: 7px;
                    margin-bottom: 6px;
                }
                .button{
                    width: 100%;height: 35px;border-radius:6px;
                }
            </style>
            <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#button").click(function() {
                    var openid = $("#openid").val();
                    var sid = $("#sid").val();
                    var name = $("#name").val();
                    var ages = $("#ages").val();
                    var phone = $("#phone").val();
                    var tel = $("#tel").val();
                    var email = $("#email").val();
                    if ("" == name) {
                        alert("姓名不能为空！");
                        return false;
                    } else if (!/^([0-9]|[0-9]{2}|100)$/.test(ages)) {
                        alert("年龄输入不正确！");
                        return false;
                    } else if (!/^[0-9]*$/.test(phone)) {
                        alert("电话输入不正确！");
                        return false;
                    } else if (!/^(13|14|15|18)[0-9]{9}$/.test(tel)) {
                        alert("手机输入不正确！");
                        return false;
                    } else if (!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)) {
                        alert("邮箱输入不正确！");
                        return false;
                    } else {
                        $.post("${pageContext.servletContext.contextPath}/ForeServlet?method=enrol_do", {"openid": $("#openid").val(), "sid": $("#sid").val(), "name": name, "sex": $("input[name='sex']:checked").val(), "ages": ages, "phone": phone, "tel": tel, "email": email, "education": $("#education").val(), "specialty": $("#specialty").val(), "comname": $("#comname").val(), "remark": $("#remark").val()}, function(result) {
                            if (result) {
                                alert("提交成功,谢谢您的参与！");
                                location.replace("${pageContext.servletContext.contextPath}/ForeServlet?method=enrol&sid=" + sid + "&openid=" + openid);
                            }
                        });
                    }
                });
            });
        </script>
    </head>
    <body id="activity-detail" class="zh_CN " ontouchstart="">
        <div id="js_article" class="rich_media">                
            <div id="js_top_ad_area" class="top_banner"></div>
            <div class="rich_media_inner">
                <div id="page-content">
                    <div id="img-content" class="rich_media_area_primary">
                        <h2 class="rich_media_title" id="activity-name">                        在线报名                     </h2>
                        <div class="rich_media_meta_list">
                            <em id="post-date" class="rich_media_meta rich_media_meta_text"><fmt:parseDate value="${now}" var="date"/><fmt:formatDate value="${date}" type="date"/></em>                                                
                            <!--<em class="rich_media_meta rich_media_meta_text">作者</em>-->
                            <a class="rich_media_meta rich_media_meta_link rich_media_meta_nickname" href="javascript:void(0);" id="post-user">天澜科技</a>
                            <!--<span class="rich_media_meta rich_media_meta_text rich_media_meta_nickname">中建信和·山东</span>-->
                        </div>
                        <div class="rich_media_content" id="js_content">
                            <form action="javascript:void(0);" method="post">
                                <input type="hidden" id="sid" name="sid" value="${sid}"/>
                                <input type="hidden" id="openid" name="openid" value="${openid}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <label>姓名</label>
                                    <input class="text-input fore-input" type="text" id="name" name="name" /><br/>
                                    <label>性别</label>
                                    <input type="radio" name="sex" value="0" checked="checked"/>
                                    男&nbsp;
                                    <input type="radio" name="sex" value="1"/>
                                    女 <br/><br/>
                                    <label>年龄</label>
                                    <input class="text-input fore-input" type="number" id="ages" name="ages" />
                                    <label>电话</label>
                                    <input class="text-input fore-input" type="number" id="phone" name="phone" />
                                    <label>手机</label>
                                    <input class="text-input fore-input" type="tel" id="tel" name="tel" />
                                    <label>邮箱</label>
                                    <input class="text-input fore-input" type="email" id="email" name="email" />
                                    <label>学历</label>
                                    <input class="text-input fore-input" type="text" id="education" name="education" />
                                    <label>专业</label>
                                    <input class="text-input fore-input" type="text" id="specialty" name="specialty" />
                                    <label>公司名称</label>
                                    <input class="text-input fore-input" type="text" id="comname" name="comname" />
                                    <label>备注</label>
                                    <textarea class="text-input fore-input" id="remark" name="remark"></textarea>
                                    <input class="button" id="button" type="button" value="提交" />
                                </fieldset>
                                <div class="clear"></div>
                                <!-- End .clear -->
                            </form>
                        </div>
                        <!--<link rel="stylesheet" type="text/css" href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/page/appmsg/page_mp_article_improve_combo23b1e9.css">-->
                        <!--                        <div class="rich_media_tool" id="js_toobar">
                                                    <div id="js_read_area" class="media_tool_meta tips_global meta_primary" style="display:none;">阅读 <span id="readNum"></span></div>
                                                    <span style="display:none;" class="media_tool_meta meta_primary tips_global meta_praise" id="like">
                                                        <i class="icon_praise_gray"></i><span class="praise_num" id="likeNum"></span>                        </span>
                                                    <a id="js_report_article" style="display:none;" class="media_tool_meta tips_global meta_extra" href="javascript:void(0);">举报</a>
                                                </div>-->
                    </div>                
                    <div class="rich_media_area_extra">
                        <div class="mpda_bottom_container" id="js_bottom_ad_area"></div>
                        <div id="js_iframetest" style="display:none;"></div>
                    </div>
                </div>
                <div id="js_pc_qr_code" class="qr_code_pc_outer" style="display:none;">
                    <div class="qr_code_pc_inner">
                        <div class="qr_code_pc">
                            <img id="js_pc_qr_code_img" class="qr_code_pc_img">
                            <p>微信扫一扫<br>关注该公众号</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>window.moon_map = {"a/gotoappdetail.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/a/gotoappdetail236317.js", "a/ios.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/a/ios236317.js", "a/android.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/a/android22772d.js", "a/profile.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/a/profile224ef3.js", "biz_common/utils/report.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/utils/report224ef3.js", "biz_common/utils/cookie.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/utils/cookie224ef3.js", "appmsg/comment.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/comment23ad25.js", "appmsg/like.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/like2340dc.js", "appmsg/a.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/a2420fd.js", "biz_common/tmpl.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/tmpl224ef3.js", "biz_common/ui/imgonepx.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/ui/imgonepx224ef3.js", "biz_common/dom/attr.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/dom/attr22f190.js", "biz_wap/utils/ajax.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_wap/utils/ajax22589f.js", "biz_common/utils/string/html.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/utils/string/html224ef3.js", "appmsg/report.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/report23c757.js", "biz_common/dom/class.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/dom/class236751.js", "appmsg/report_and_source.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/report_and_source23a582.js", "appmsg/page_pos.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/page_pos23c757.js", "appmsg/cdn_speed_report.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/cdn_speed_report224ef3.js", "appmsg/iframe.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/iframe238f79.js", "appmsg/review_image.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/review_image2420fd.js", "appmsg/outer_link.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/outer_link224ef3.js", "biz_wap/jsapi/core.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_wap/jsapi/core22589f.js", "biz_wap/utils/mmversion.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_wap/utils/mmversion224ef3.js", "biz_common/dom/event.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/dom/event23c291.js", "appmsg/async.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/async243273.js", "biz_wap/ui/lazyload_img.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_wap/ui/lazyload_img23354e.js", "biz_common/log/jserr.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/log/jserr22589f.js", "appmsg/share.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/share23c757.js", "appmsg/cdn_img_lib.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/cdn_img_lib23c757.js", "biz_common/utils/url/parse.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_common/utils/url/parse238f07.js", "appmsg/index.js": "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/appmsg/index23cca7.js"};</script>
        <script type="text/javascript" src="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_wap/moon230eaa.js"></script>
        <script type="text/javascript">
            var not_in_mm_css = "http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/page/appmsg/not_in_mm23b0e8.css";
            var tid = "";
            var aid = "";
            var appuin = "MjM5MTQ5MTgwNQ==";
            var source = "";
            var scene = 75;
            var itemidx = "";
            var nickname = "${news.name}";
            var ct = "1425865555";
            var user_name = "gh_cdac14c68852";
            var fakeid = "";
            var version = "";
            var is_limit_user = "0";
            var msg_title = "${news.name}";
            var msg_desc = "${news.name}";
            var msg_cdn_url = "http://mmbiz.qpic.cn/mmbiz/VicSxoMOxxmYdngblAg4RAW4HP7Cs4GoE1HLMNdVSfgjGsbnE4kNkrUGpXmlbmFt40jZkrxzbXfCmF5yWZFUwng/0";
            var msg_link = "http://mp.weixin.qq.com/s?__biz=MjM5MTQ5MTgwNQ==&amp;mid=203946776&amp;idx=1&amp;sn=732509b39470b7861bdffd5fe4df56c4#rd";
            var user_uin = "0" * 1;
            var msg_source_url = '';
            var networkType;
            var appmsgid = '' || '203946776';
            var comment_id = "0" * 1;
            var svr_time = "1425865655" * 1;
            var comment_enabled = "" * 1;
            var is_need_reward = "0" * 1;
            var is_https_res = ("" * 1) && (location.protocol == "https:");
            var devicetype = "";
            seajs.use("appmsg/index.js");
        </script>
    </body>
</html>