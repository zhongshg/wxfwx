<%-- 
    Document   : jsapi
    Created on : 2015-11-19, 14:23:48
    Author     : Administrator
--%>

<%@page import="wap.wx.menu.WxJsApiUtils"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String jspurl = request.getParameter("jspurl");
    String linkurl = request.getParameter("linkurl");
    Map<String, String> jsapi = new WxJsApiUtils().jsapi(jspurl);
    String jsApiList = "'checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo'";
    jsapi.put("jsApiList", jsApiList);
%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    wx.config({
        debug: false,
        appId: '<%=jsapi.get("appId")%>',
        timestamp: <%=jsapi.get("timestamp")%>,
        nonceStr: '<%=jsapi.get("noncestr")%>',
        signature: '<%=jsapi.get("signature")%>',
        jsApiList: [<%=jsapi.get("jsApiList")%>]
    });
    wx.ready(function() {
        wx.checkJsApi({
            jsApiList: [
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo'
            ],
            success: function(res) {
            },
            fail: function(res) {
                alert("请更新到微信6.0以上版本！");
            }
        });

        var shareData = {
            title: '花随我动，想种就种，种花赢许巍演唱会门票咯~',
            desc: '种花赢许巍演唱会门票，再度来袭！！11月26日12:00—11月28日9:00期间，关注身边有你大不同官方微信，回复“许巍”即可参与种花活动。活动不限身份，只要您的浇水次数够多，就可赢得本周六的许巍演唱会门票，快邀请小伙伴浇水吧！',
            link: '<%=linkurl%>',
            imgUrl: 'http://wanxiangfangwx.cityapp360.com/vote4/image/xuwei.jpg'
        };
        wx.onMenuShareAppMessage(shareData);
        wx.onMenuShareTimeline(shareData);
        wx.onMenuShareQQ(shareData);
        wx.onMenuShareWeibo(shareData);
    });

    wx.error(function(res) {
    });
</script>
