<%-- 
    Document   : index
    Created on : 2014-12-25, 14:16:39
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head time="1419483222">
        <meta charset="utf-8">
        <!--<meta http-equiv="refresh" content="30">-->
        <link rel="dns-prefetch" href="//dzqun.gtimg.com">
        <link rel="dns-prefetch" href="//shp.qlogo.cn">
        <link rel="dns-prefetch" href="//ugc.qpic.cn">
        <link rel="dns-prefetch" href="//wx.qlogo.cn">
        <link rel="dns-prefetch" href="//qlogo1.store.qq.com">
        <link rel="dns-prefetch" href="//q1.qlogo.cn">
        <link rel="dns-prefetch" href="//q2.qlogo.cn">
        <link rel="dns-prefetch" href="//q3.qlogo.cn">
        <link rel="dns-prefetch" href="//q4.qlogo.cn">
        <link rel="dns-prefetch" href="//pub.idqqimg.com">
        <link rel="dns-prefetch" href="//api.wsq.qq.com">
        <link rel="dns-prefetch" href="//isdspeed.qq.com">
        <link rel="dns-prefetch" href="//pingfore.qq.com">
        <link rel="dns-prefetch" href="//jqmt.qq.com">
        <link rel="dns-prefetch" href="//jsqmt.qq.com">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="baidu-tc-cerfication" content="5f496895eb9bff9ec4db4512a7e4e95c">
        <meta name="description" content="为了方便跟大家更好的交流，中国青年报微信公众账号开通微社区啦~">
        <meta name="keywords" content="">
        <!--<link rel="shortcut icon" href="http://dzqun.gtimg.cn/quan/images/favicon.ico">-->
        <title>万巷坊</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/weiforum/zhengwen.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/web/weiforum/weimob-ui-1-1.css" media="all" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/web/weiforum/common.css" media="all" />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <script>
            wx.config({
                debug: false,
                appId: '${jsapi.appId}',
                timestamp: ${jsapi.timestamp},
                nonceStr: '${jsapi.noncestr}',
                signature: '${jsapi.signature}',
                jsApiList: [${jsapi.jsApiList}]
            });
        </script>
        <script src="http://jsqmt.qq.com/cdn_djl.js" type="text/javascript" async=""></script><script type="text/javascript">
            window.g_module = [];
            var STATIC_DOMAIN = 'http://dzqun.gtimg.cn',
                    ARS_TIME = '1419483222',
                    IMG_LOADING = 'data:image/gif;base64,R0lGODlhAQABAIAAAMLCwgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==';

            var g_tsBase = new Date(), g_ts = {};
            var PathUtil = {
                getCPath: function() {
                    return STATIC_DOMAIN + '/quan/scripts/';
                }
            };

            g_ts.css_start = new Date();
            var pageName = '';
        </script>
        <link rel="stylesheet" href="http://dzqun.gtimg.cn/c/1419483222=/quan/style/style.css,/quan/style/post.css" onload="g_ts.css_end = new Date();" onerror="g_ts.css_end = new Date();">
        <!--debug-->
        <script type="text/javascript">
            var sId = 211873835,
                    tId = 0,
                    uId = '0',
                    isNullNick = '0' | false,
                    isManager = 0,
                    authUrl = 'http://ui.ptlogin2.qq.com/cgi-bin/login?appid=710044201&s_url=http%3A%2F%2Fm.wsq.qq.com%2F211873835&style=8&hln_css=http%3A%2F%2Fdzqun.gtimg.cn%2Fquan%2Fimages%2FloginLogo.png&hln_autologin=true',
                    siteLogo = 'http://shp.qlogo.cn/gqclogo/0/211873835/104?max-age=2592000&amp;t=1396942291',
                    CSRFToken = "dc60d1b9",
                    debug = '' | false,
                    DOMAIN = 'http://m.wsq.qq.com/',
                    _speedMark = new Date(),
                    isWX = '' | false,
                    isMQ = '' | false,
                    isAppBar = '' | false,
                    isQQBrowser = '' | false,
                    isWeixinLink = '' | false,
                    newMsgCount = '0',
                    isFriendSite = '0',
                    enabledSmiley = '1';

            window.addEventListener("DOMContentLoaded", function() {
                g_ts.domready = new Date();
            });
            window.addEventListener("load", function() {
                g_ts.domload = new Date();
            });
            document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
                WeixinJSBridge.call('hideToolbar');
                WeixinJSBridge.call('showOptionMenu');
            });

        </script>
        <script src="http://jqmt.qq.com/cdn_dianjiliu.js?a=0.5440375455655158"></script>
        <style>
            .caSide{position:static;right:0;top:10px}
            img{
                /*width: 100%;*/
                margin: 1px 1px 1px 1px;
            }
            .caSide a{width:100%;margin:0 auto;padding:5px 10px 0}
        </style>
        <script>
            $(document).ready(function() {
                window.setInterval(function() {
                    $.post("${pageContext.servletContext.contextPath}/ForeServlet?method=weizhibo_do", {"sid": "${sid}", "newscount": "${newscount}"}, function(result) {
                        result = eval("(" + result + ")");
                        if ("1" == result.success) {
                            $("#messagesign").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>有" + result.addcount + "条新的动态，点击查看...</font>");
                            $("#showCarousel").css("display", "block");
                        }
                        ;
                    });
                }, 3000);
            });
        </script>
    </head>
    <body  style="zoom: 1;">
        <!--class="pt pb "-->
        <script>
            var jsonData = '{\"threadList\":[{\"authorUid\":214149892,\"author\":\"\\u6587\\u5170\\u516c\\u53f8 ...\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/vd8yNL3TS9Ab8vXHibQQPtef9FReQGHrOmibl7p4ibwHx4TDlibOwb0cwMT5xCaibFraiaTHZYqhE1jB1dQpMOqsHtltRI1V8HAhp5\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\\u90af\\u90f8\\u5e02 \\u65b0\\u6d3a\\u8def\",\"rank\":2,\"tId\":1631,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u4e00\\u4e2a\\u6751\\u652f\\u4e66\\u7684\\u80fd\\u529b\\u6709\\u591a\\u5927\\uff1f<br \\/>\\n\\u6cb3\\u5317\\u7701\\u90af\\u90f8\\u5e02\\u6c38\\u5e74\\u53bf\\u53f0\\u53e3\\u6751\\u6751\\u652f\\u4e66\\u6b66\\u5e73\\u6c5f.\\uff08\\u66fe\\u5bf9\\u6751\\u6c11\\u516c\\u5f00\\u8bb2\\uff1a\\u516c\\u68c0\\u6cd5\\u542c\\u4ed6\\u4eec\\u7684\\u2026\\u2026\\u6700\\u540e\\u89c1\\u8bc1\\u679c\\u771f\\u5982\\u6b64\\u2026\\u2026\\u6b66\\u60f3\\u5170\\u53ca\\u5f1f\\u5f1f\\u6b66\\u5408\\u6797\\u513f\\u5b50\\u6b66\\u96f7\\u6653\\u5168\\u90e8\\u6293\\u7d27\\u76d1\\u72f1\\uff09\\u80fd\\u8054\\u5408\\u9000\\u4f11\\u7684\\u653f\\u6cd5\\u59d4\\u8001\\u4e66\\u8bb0\\u674e\\u6625\\u9f99\\u7b49\\u9ad8\\u5b98\\u628a\\u4e00\\u4e2a\\u4f18\\u79c0\\u7684\\u5973\\u4eba\\u2026\\u2026\\u4e00\\u4e2a\\u90af\\u90f8\\u5e02\\u653f\\u534f\\u59d4\\u5458\\u2026\\u2026\\u4e00\\u4e2a\\u6cb3\\u5317\\u7701\\u4f18\\u79c0\\u5171\\u4ea7\\u515a\\u5458\\uff5e\\u4e00\\u4e2a\\u6cb3\\u5317\\u7701...\",\"hCreatedTime\":\"2014-12-18 11:44\",\"hLastPostTime\":\"1\\u5929\\u524d\",\"isStick\":0,\"showMore\":true,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":1,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1631?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E4%B8%80%E4%B8%AA%E6%9D%91%E6%94%AF%E4%B9%A6%E7%9A%84%E8%83%BD%E5%8A%9B%E6%9C%89%E5%A4%9A%E5%A4%A7%EF%BC%9F%E6%B2%B3%E5%8C%97%E7%9C%81%E9%82%AF%E9%83%B8%E5%B8%82%E6%B0%B8%E5%B9%B4%E5%8E%BF%E5%8F%B0%E5%8F%A3%E6%9D%91%E6%9D%91%E6%94%AF%E4%B9%A6%E6%AD%A6%E5%B9%B3%E6%B1%9F.%EF%BC%88%E6%9B%BE%E5%AF%B9%E6%9D%91%E6%B0%91%E5%85%AC%E5%BC%80%E8%AE%B2%EF%BC%9A%E5%85%AC%E6%A3%80%E6%B3%95%E5%90%AC%E4%BB%96%E4%BB%AC...&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1631%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":3,\"rank\":1,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}},{\"authorUid\":214871966,\"author\":\"\\u98ce\\u4e00\\u6837\\u7684\\u7537\\u5b50\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/rPibt62gfVOjbNbibMo9lJHjHPF5njT0HgTRTplW6iaHQ4eC1jsQc4ERbPTQiao8uIYIYrricLvWb0BE0UeWfKwIiaGMIN1l1icSOgI\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\\u90d1\\u5dde\\u5e02\",\"rank\":1,\"tId\":1632,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":5,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u603b\\u611f\\u89c9\\u751f\\u6d3b\\u6ca1\\u6709\\u610f\\u4e49\\uff0c\\u4f5c\\u4e3a\\u9752\\u5e74\\u4e3b\\u529b\\uff0c\\u5e94\\u8be5\\u591a\\u8bfb\\u4e9b\\u4ec0\\u4e48\\u4ec0\\u4e48\\u4e66\\u624d\\u597d\\uff0c\\u5929\\u5929\\u4e0d\\u662fQQ\\u5c31\\u662f\\u7f51\\u6e38\\uff0c\\u96be\\u9053\\u8fd9\\u5c31\\u662f\\u9752\\u6625\\uff1f\",\"hCreatedTime\":\"2014-12-19 10:54\",\"hLastPostTime\":\"2\\u5929\\u524d\",\"isStick\":0,\"replyList\":[{\"tId\":1632,\"pId\":1382,\"isLZ\":false,\"authorUid\":7203430,\"author\":\"\\u7af9\\u6797\\u98ce\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6UCeK5zYUH9W3ibAbUYoJQrlUGtecNCVBTMsKhjatS2ibFlExlKR4Z1QRrvs7Wj7LQUQCGXmY9gA8HWibCT3qZq13f\\/64?max-age=1296000\",\"content\":\"\\u6211\\u89c9\\u5f97\\u5c31\\u662f\\u73b0\\u5728\\u6761\\u4ef6\\u597d\\u4e86\\uff0c\\u751f\\u6d3b\\u592a\\u5b89\\u9038\\u4e86\"},{\"tId\":1632,\"pId\":1384,\"isLZ\":false,\"authorUid\":215739307,\"author\":\"\\u751c\\u8a00\\u871c\\u5b87\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6XMVyfABibKFhtOKfnRqs8vxIia2vf7KmU018XIlxOUNvJeiaXwLVl3YZOReYSA1bxIjuHPrxgbo6lCfAkoD16ylO0\\/64?max-age=1296000\",\"content\":\"\\u53bb\\u65c5\\u884c\\u5427\"},{\"tId\":1632,\"pId\":1385,\"isLZ\":false,\"authorUid\":194683647,\"author\":\"\\u62b9\\u8336\\u7ea2\\u8c46\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/zPHK62E0q5gEVtMyFCaEzxfgdQDmDYONdQDeKgSSWx2X2A70WadqRsSe3SN2sqrNjAK7XLaicibuM3mNCDQMG45LyuEDJUI0ep\\/64?max-age=1296000\",\"content\":\"\\u53bb\\u65c5\\u884c\\u5427\\uff01\"}],\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":1,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1632?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E6%80%BB%E6%84%9F%E8%A7%89%E7%94%9F%E6%B4%BB%E6%B2%A1%E6%9C%89%E6%84%8F%E4%B9%89%EF%BC%8C%E4%BD%9C%E4%B8%BA%E9%9D%92%E5%B9%B4%E4%B8%BB%E5%8A%9B%EF%BC%8C%E5%BA%94%E8%AF%A5%E5%A4%9A%E8%AF%BB%E4%BA%9B%E4%BB%80%E4%B9%88%E4%BB%80%E4%B9%88%E4%B9%A6%E6%89%8D%E5%A5%BD%EF%BC%8C%E5%A4%A9%E5%A4%A9%E4%B8%8D%E6%98%AFQQ%E5%B0%B1%E6%98%AF%E7%BD%91%E6%B8%B8%EF%BC%8C%E9%9A%BE%E9%81%93%E8%BF%99%E5%B0%B1%E6%98%AF%E9%9D%92%E6%98%A5%EF%BC%9F&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1632%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":6,\"rank\":2,\"limit\":{\"thread\":0,\"reply\":0,\"reward\":3}}},{\"authorUid\":213401992,\"author\":\"\\u4f1a\\u8bf4\\u8c0e\\u7684\\u5339\\u8bfa\\u66f9\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/ajNVdqHZLLBL9lxJf5FdThh7riawU5xFrFjW1CicbrTSRLWmobM8WrDMay3J92PWSp2tCPSaFPvxhOQAJECWJL7g\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\\u901a\\u8fbd\\u5e02\",\"tId\":1625,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"<a class=\\\"goLinkBox br ml5 db\\\" href=\\\"http:\\/\\/m.wsq.qq.com\\/safelink?url=http%3A%2F%2Fmp.weixin.qq.com%2Fs%3F__biz%3DMjM5NjY3NjEyMA%3D%3D%26mid%3D203680677%26idx%3D1%26sn%3D83143a80b77f87553a1e5530db14408b%26scene%3D2%26from%3Dtimeline%26isappinstalled%3D0%23rd\\\"><span class=\\\"goLinkBtn wot db f13\\\"><i class=\\\"incoGoLink cf f16 c9\\\"><\\/i>\\u547c\\u683c\\u5409\\u52d2\\u56fe\\u6848\\uff0c\\u8fd913\\u4eba\\u7edd\\u4e0d\\u5bb9\\u5fd8\\u8bb0<\\/span><\\/a>           \\u90a3\\u4e9b\\u5f53\\u5e74\\u5224\\u6848\\u4eba\\u5458\\u4f60\\u4eectmd\\u51fa\\u6765\\u8ba4\\u7f6a\\uff01\\uff01\\uff01\\uff01\\uff01\",\"hCreatedTime\":\"2014-12-17 00:48\",\"hLastPostTime\":\"2014-12-21 12:34\",\"isStick\":0,\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":1,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1625?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%5Bu%3A15a916%5D+++++++++++%E9%82%A3%E4%BA%9B%E5%BD%93%E5%B9%B4%E5%88%A4%E6%A1%88%E4%BA%BA%E5%91%98%E4%BD%A0%E4%BB%ACtmd%E5%87%BA%E6%9D%A5%E8%AE%A4%E7%BD%AA%EF%BC%81%EF%BC%81%EF%BC%81%EF%BC%81%EF%BC%81&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1625%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":3,\"rank\":1,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}},{\"authorUid\":213779962,\"author\":\"Jyb13141314\",\"avatar\":\"http:\\/\\/dzqun.gtimg.cn\\/quan\\/images\\/personImg.jpg\",\"authorGender\":0,\"LBSInfo\":\"\\u4e1c\\u839e\\u5e02 \\u987a\\u5174\\u4e00\\u8def\",\"tId\":1637,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u6709\\u5de5\\u4f24\\u5458\\u5de5\\u516c\\u53f8\\u4e0d\\u4fac\\u6cd5\\u8d54\\u507f\\u8981\\u5728\\u516c\\u53f8\\u81ea\\u6740\\u7ef4\\u6743\\u4e86\",\"hCreatedTime\":\"2014-12-21 12:32\",\"hLastPostTime\":\"2014-12-21 12:32\",\"isStick\":0,\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1637?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E6%9C%89%E5%B7%A5%E4%BC%A4%E5%91%98%E5%B7%A5%E5%85%AC%E5%8F%B8%E4%B8%8D%E4%BE%AC%E6%B3%95%E8%B5%94%E5%81%BF%E8%A6%81%E5%9C%A8%E5%85%AC%E5%8F%B8%E8%87%AA%E6%9D%80%E7%BB%B4%E6%9D%83%E4%BA%86&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1637%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":5,\"rank\":2,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}},{\"authorUid\":7203430,\"author\":\"\\u7af9\\u6797\\u98ce\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6UCeK5zYUH9W3ibAbUYoJQrlUGtecNCVBTMsKhjatS2ibFlExlKR4Z1QRrvs7Wj7LQUQCGXmY9gA8HWibCT3qZq13f\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\",\"tId\":1636,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u4e3a\\u4ec0\\u4e48\\u6211\\u505a\\u4e8b\\u60c5\\u603b\\u662f\\u8fd9\\u4e48\\u6ca1\\u6709\\u5e38\\u6027\\u5462\\uff1f\\u603b\\u662f\\u4e0b\\u5b9a\\u51b3\\u5fc3\\u8981\\u505a\\u4e00\\u4ef6\\u4e8b\\uff0c\\u53ef\\u7ecf\\u5e38\\u53c8\\u534a\\u9014\\u800c\\u5e9f\\uff0c\\u771f\\u6709\\u4e9b\\u77a7\\u4e0d\\u8d77\\u81ea\\u5df1\\u4e86\",\"hCreatedTime\":\"2014-12-21 12:05\",\"hLastPostTime\":\"2014-12-21 12:05\",\"isStick\":0,\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1636?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E4%B8%BA%E4%BB%80%E4%B9%88%E6%88%91%E5%81%9A%E4%BA%8B%E6%83%85%E6%80%BB%E6%98%AF%E8%BF%99%E4%B9%88%E6%B2%A1%E6%9C%89%E5%B8%B8%E6%80%A7%E5%91%A2%EF%BC%9F%E6%80%BB%E6%98%AF%E4%B8%8B%E5%AE%9A%E5%86%B3%E5%BF%83%E8%A6%81%E5%81%9A%E4%B8%80%E4%BB%B6%E4%BA%8B%EF%BC%8C%E5%8F%AF%E7%BB%8F%E5%B8%B8%E5%8F%88%E5%8D%8A%E9%80%94%E8%80%8C%E5%BA%9F%EF%BC%8C%E7%9C%9F%E6%9C%89%E4%BA%9B%E7%9E%A7%E4%B8%8D%E8%B5%B7%E8%87%AA%E5%B7%B1%E4%BA%86&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1636%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":4,\"rank\":1,\"limit\":{\"thread\":2,\"reply\":1,\"reward\":0}}},{\"authorUid\":215739307,\"author\":\"\\u751c\\u8a00\\u871c\\u5b87\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6XMVyfABibKFhtOKfnRqs8vxIia2vf7KmU018XIlxOUNvJeiaXwLVl3YZOReYSA1bxIjuHPrxgbo6lCfAkoD16ylO0\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\\u6606\\u660e\\u5e02\",\"tId\":1635,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u5fc3\\u60c5\\u5f88\\u4e0d\\u597d\\uff0c\\u5468\\u4e00\\u5c31\\u8003\\u8bd5\\u4e86\\uff0c\\u6211\\u60f3\\u6bd4\\u522b\\u4eba\\u505a\\u5f97\\u597d\\u4f46\\u603b\\u662f\\u4e0d\\u884c\\u3002\\u5509 \\u52aa\\u529b\\u5427<img class=\\\"expimg\\\" src=\\\"http:\\/\\/dzqun.gtimg.cn\\/quan\\/images\\/smiley\\/default\\/e163.gif\\\" \\/><img class=\\\"expimg\\\" src=\\\"http:\\/\\/dzqun.gtimg.cn\\/quan\\/images\\/smiley\\/default\\/e163.gif\\\" \\/>\",\"hCreatedTime\":\"2014-12-20 14:11\",\"hLastPostTime\":\"2014-12-20 14:11\",\"isStick\":0,\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1635?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E5%BF%83%E6%83%85%E5%BE%88%E4%B8%8D%E5%A5%BD%EF%BC%8C%E5%91%A8%E4%B8%80%E5%B0%B1%E8%80%83%E8%AF%95%E4%BA%86%EF%BC%8C%E6%88%91%E6%83%B3%E6%AF%94%E5%88%AB%E4%BA%BA%E5%81%9A%E5%BE%97%E5%A5%BD%E4%BD%86%E6%80%BB%E6%98%AF%E4%B8%8D%E8%A1%8C%E3%80%82%E5%94%89+%E5%8A%AA%E5%8A%9B%E5%90%A7%5B%E7%8E%AB%E7%91%B0%5D%5B%E7%8E%AB%E7%91%B0%5D&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1635%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":4,\"rank\":1,\"limit\":{\"thread\":0,\"reply\":1,\"reward\":0}}},{\"authorUid\":198571883,\"author\":\"\\u4e00\\u9a91\\u7ea2\\u5c18\\u5983\\u5b50\\u7b11\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6XEnzKcibjrXAjib2aprgyravZ9LVbjFDICaibPTG5efDuo9Kef0PFHR1JHvicPbG5I5rxmQpfIM5bSiaxFL94mTeGKA\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\",\"tId\":1634,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":2,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u81ea\\u5df1\\u5f88\\u60f3\\u770b\\u4e66\\uff0c\\u4e0d\\u77e5\\u4e3a\\u4f55\\u5c31\\u662f\\u9759\\u4e0d\\u4e0b\\u5fc3\\u6765\\u770b\\uff0c\\u8bf7\\u95ee\\u6709\\u4f55\\u826f\\u7b56\\uff01\\uff1f\",\"hCreatedTime\":\"2014-12-19 18:47\",\"hLastPostTime\":\"2014-12-20 10:29\",\"isStick\":0,\"replyList\":[{\"tId\":1634,\"pId\":1380,\"isLZ\":true,\"authorUid\":198571883,\"author\":\"\\u4e00\\u9a91\\u7ea2\\u5c18\\u5983\\u5b50\\u7b11\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6XEnzKcibjrXAjib2aprgyravZ9LVbjFDICaibPTG5efDuo9Kef0PFHR1JHvicPbG5I5rxmQpfIM5bSiaxFL94mTeGKA\\/64?max-age=1296000\",\"content\":\"\\u6709\\u540c\\u611f\\uff0c\\u73b0\\u5728\\u5f88\\u5c11\\u6709\\u5e74\\u8f7b\\u4eba\\u8ff7\\u4e66\\u7684\\uff01\\u8fd9\\u662f\\u4e2d\\u56fd\\u6587\\u5316\\u7684\\u5371\\u673a\\uff01\"},{\"tId\":1634,\"pId\":1381,\"isLZ\":false,\"authorUid\":215595280,\"author\":\"patima\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/rPibt62gfVOj0qs1BLicaAMtLD0q42V4ibYG6Bo6rrp0Xy20GyeYLODHnGFpAzkR6g5Scwg7CoQORwr5nBRuCq0TjlLAJxJ5ania\\/64?max-age=1296000\",\"content\":\"\\u5148\\u5403\\u9971\\uff0c\\u627e\\u672c\\u4e66\\uff0c\\u518d\\u627e\\u4e2a\\u4e0d\\u51b7\\u4e0d\\u70ed\\u5b89\\u9759\\u7684\\u5730\\u65b9\\uff0c\\u4e00\\u4e2a\\u6c34\\u676f\\uff0c\\u8bfb\\u5230\\u809a\\u5b50\\u997f\\u3002\\u6211\\u5c31\\u8fd9\\u6837\"}],\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1634?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E8%87%AA%E5%B7%B1%E5%BE%88%E6%83%B3%E7%9C%8B%E4%B9%A6%EF%BC%8C%E4%B8%8D%E7%9F%A5%E4%B8%BA%E4%BD%95%E5%B0%B1%E6%98%AF%E9%9D%99%E4%B8%8D%E4%B8%8B%E5%BF%83%E6%9D%A5%E7%9C%8B%EF%BC%8C%E8%AF%B7%E9%97%AE%E6%9C%89%E4%BD%95%E8%89%AF%E7%AD%96%EF%BC%81%EF%BC%9F&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1634%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":3,\"rank\":1,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}},{\"authorUid\":101097077,\"author\":\"\\u540d\\u5b57\\u4e0d\\u597d\\u8d77...\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/icicIvSc9Yf6XUC2YVaFSGk1e9s3zE6zXl2ymjK8oCZibt7X7BvVMuFnibydh62TJ2eeiceRuHWPq2ic5zKX21ia6E5SY2FJosGyWicw\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\",\"tId\":1633,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u8fd9\\u4e2a\\u5ba2\\u6237\\u7aef\\u505a\\u7684\\u4e0d\\u9519\\uff01\",\"hCreatedTime\":\"2014-12-19 13:44\",\"hLastPostTime\":\"2014-12-19 13:44\",\"isStick\":0,\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1633?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E8%BF%99%E4%B8%AA%E5%AE%A2%E6%88%B7%E7%AB%AF%E5%81%9A%E7%9A%84%E4%B8%8D%E9%94%99%EF%BC%81&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1633%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":3,\"rank\":1,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}},{\"authorUid\":213779962,\"author\":\"Jyb13141314\",\"avatar\":\"http:\\/\\/dzqun.gtimg.cn\\/quan\\/images\\/personImg.jpg\",\"authorGender\":0,\"LBSInfo\":\"\\u4e1c\\u839e\\u5e02 \\u987a\\u5174\\u4e09\\u8def\",\"tId\":1630,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"\\u300a\\u5de5\\u4f24\\u4fdd\\u9669\\u6761\\u4f8b\\u300b\\u5728\\u5927\\u591a\\u516c\\u53f8\\u9762\\u524d\\u53ea\\u662f\\u6446\\u8bbe\\uff1f\\u8c01\\u4e4b\\u8fc7\\uff1f\\u8c01\\u5728\\u76d1\\u7ba1\\uff1f\\u519c\\u6c11\\u5de5\\u5de5\\u4f24\\u7ef4\\u6743\\u8270\\u96be\\uff0c\\u8c01\\u6765\\u7ba1\\uff1f\\uff1f\\uff1f\\uff1f\",\"hCreatedTime\":\"2014-12-17 17:57\",\"hLastPostTime\":\"2014-12-17 17:57\",\"isStick\":0,\"showMore\":false,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1630?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%E3%80%8A%E5%B7%A5%E4%BC%A4%E4%BF%9D%E9%99%A9%E6%9D%A1%E4%BE%8B%E3%80%8B%E5%9C%A8%E5%A4%A7%E5%A4%9A%E5%85%AC%E5%8F%B8%E9%9D%A2%E5%89%8D%E5%8F%AA%E6%98%AF%E6%91%86%E8%AE%BE%EF%BC%9F%E8%B0%81%E4%B9%8B%E8%BF%87%EF%BC%9F%E8%B0%81%E5%9C%A8%E7%9B%91%E7%AE%A1%EF%BC%9F%E5%86%9C%E6%B0%91%E5%B7%A5%E5%B7%A5%E4%BC%A4%E7%BB%B4%E6%9D%83%E8%89%B0%E9%9A%BE%EF%BC%8C%E8%B0%81%E6%9D%A5%E7%AE%A1%EF%BC%9F%EF%BC%9F%EF%BC%9F%EF%BC%9F&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1630%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":5,\"rank\":2,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}},{\"authorUid\":212594009,\"author\":\"\\u73a5yueyue3817\",\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/vd8yNL3TS9Ab8vXHibQQPtQF7XatkTz9JYH825oAwuRp8M0mA5pB10dyic0NKay2X1sOe8p0gicpXYLrjTzoUn8MyoiauY7SKfK3\\/64?max-age=1296000\",\"authorGender\":0,\"LBSInfo\":\"\",\"tId\":1629,\"fId\":0,\"sId\":211873835,\"parentId\":0,\"rCount\":0,\"threadType\":0,\"title\":\"\",\"summary\":\"<a class=\\\"goLinkBox br ml5 db\\\" href=\\\"http:\\/\\/m.wsq.qq.com\\/safelink?url=http%3A%2F%2Fwww.morningpost.com.cn%2F\\\"><span class=\\\"goLinkBtn wot db f13\\\"><i class=\\\"incoGoLink cf f16 c9\\\"><\\/i>\\u7f51\\u9875\\u94fe\\u63a5<\\/span><\\/a>             \\u5317\\u4eac\\u6668\\u62a5\\u7f51\\t<br \\/>\\n<br \\/>\\n\\u65e9\\u6559\\u673a\\u6784\\u5173\\u95e8 \\u5bb6\\u957f\\u62a5\\u6848<br \\/>\\n\\u6d89\\u4e8b\\u91d1\\u989d\\u8fbe\\u6570\\u767e\\u4e07 \\u8fd0\\u8425\\u65b9\\u79f0\\u4f1a\\u5c3d\\u5feb\\u7ed9\\u51fa\\u89e3\\u51b3\\u65b9\\u6cd5<br \\/>\\n\\u3000\\u3000\\u8fd1\\u65e5\\uff0c\\u65e9\\u6559\\u673a\\u6784\\u827a\\u672f\\u624d\\u8c1c\\uff08\\u534e\\u8054\\u4e07\\u67f3\\u5e97\\uff09\\u5728\\u672a\\u901a\\u77e5\\u5546\\u573a\\u7269\\u4e1a\\u7684\\u60c5\\u51b5\\u4e0b\\u79c1\\u81ea\\u5173\\u95e8\\u505c\\u4e1a\\u3002\\u7269\\u4e1a\\u548c\\u6570\\u767e\\u540d\\u5bb6\\u957f\\u5747\\u7591\\u5fc3\\u53d7\\u9a97\\uff0c\\u62a5\\u8b66\\u8ba8\\u8bf4\\u6cd5\\uff0c\\u6d89\\u4e8b\\u91d1\\u989d\\u8fbe\\u6570\\u767e\\u4e07\\u3002\\u8be5\\u5e97\\u8fd0...\",\"hCreatedTime\":\"2014-12-17 13:01\",\"hLastPostTime\":\"2014-12-17 13:01\",\"isStick\":0,\"showMore\":true,\"liveId\":0,\"hideMQ\":0,\"joinNumber\":0,\"joinUser\":[],\"isEnd\":0,\"likeNum\":0,\"shareLink\":\"http:\\/\\/m.wsq.qq.com\\/211873835\\/t\\/1629?action=share\",\"share\":{\"qq\":\"\",\"qzone\":\"http:\\/\\/m.wsq.qq.com\\/shareDirect?site=%E4%B8%AD%E9%9D%92%E6%8A%A5%E5%A4%A7%E6%9C%AC%E8%90%A5&title=%E5%BF%AB%E6%9D%A5%E7%9C%8B%E7%9C%8B%E8%BF%99%E4%B8%AA%E8%AF%9D%E9%A2%98&summary=%5Bu%3Ae68a49%5D+++++++++++++%E5%8C%97%E4%BA%AC%E6%99%A8%E6%8A%A5%E7%BD%91%09%E6%97%A9%E6%95%99%E6%9C%BA%E6%9E%84%E5%85%B3%E9%97%A8+%E5%AE%B6%E9%95%BF%E6%8A%A5%E6%A1%88%E6%B6%89%E4%BA%8B%E9%87%91%E9%A2%9D...&targetUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835%2Ft%2F1629%3F_wv%3D1%26source%3Dconnect_qzone&pageUrl=http%3A%2F%2Fm.wsq.qq.com%2F211873835&imageUrl=http%3A%2F%2Fshp.qlogo.cn%2Fgqclogo%2F0%2F211873835%2F200%3Fmax-age%3D2592000%26t%3D1396942291&type=qzone\"},\"videos\":[],\"weishiInfo\":[],\"showPicType\":0,\"closeJoin\":0,\"closeUpdate\":0,\"peId\":0,\"worldCup\":0,\"appId\":0,\"appName\":\"\",\"authorHonor\":0,\"authorExps\":{\"num\":3,\"rank\":1,\"limit\":{\"thread\":2,\"reply\":0,\"reward\":0}}}],\"nextStart\":10,\"newMsgCount\":0,\"threadCount\":568,\"liveThreadCount\":513,\"sitePV\":\"108594\",\"groupStar\":2,\"verifyDeveloper\":1,\"verifyStar\":2,\"fId\":0,\"isLive\":false,\"enabledSmiley\":\"1\",\"siteRankListTopThree\":{\"214871966\":{\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/rPibt62gfVOjbNbibMo9lJHjHPF5njT0HgTRTplW6iaHQ4eC1jsQc4ERbPTQiao8uIYIYrricLvWb0BE0UeWfKwIiaGMIN1l1icSOgI\\/64?max-age=1296000\",\"rank\":1,\"praise\":\"1\"},\"214149892\":{\"avatar\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/vd8yNL3TS9Ab8vXHibQQPtef9FReQGHrOmibl7p4ibwHx4TDlibOwb0cwMT5xCaibFraiaTHZYqhE1jB1dQpMOqsHtltRI1V8HAhp5\\/64?max-age=1296000\",\"rank\":2,\"praise\":\"1\"}}}';
        </script>
        <div class="warp">
            <!--            <div class="topicM worldC" id="pEvent">
                            <img class="topicMImg lazy-hidden" src="http://ugc.qpic.cn/quan_pics/0/93279557_1186/1280" alt="图片" id="pEventImg">
                            <p style="display: none;">活动总参与人数：<span id="pEventNum">${count}</span></p>
                        </div>-->
            <div class="loading f14" id="refreshWait" style="display:none;">
                <div class="loadicon">
                    <span class="blockG" id="rotateG_01"></span>
                    <span class="blockG" id="rotateG_02"></span>
                    <span class="blockG" id="rotateG_03"></span>
                    <span class="blockG" id="rotateG_04"></span>
                    <span class="blockG" id="rotateG_05"></span>
                    <span class="blockG" id="rotateG_06"></span>
                    <span class="blockG" id="rotateG_07"></span>
                    <span class="blockG" id="rotateG_08"></span>
                </div>
                正在加载...
            </div>
            <header class="header oh pr">
                <i class="logo fl mr db"><img src="${pageContext.servletContext.contextPath}/web/weiforum/logo.png" class="tImg br lazy-hidden" width="50" height="50" alt="logo"></i>
                <div class="evtSiteRank" data-link="">
                    <h2 class="title wot f18 fn c0">万巷坊</h2>
                    <p class="subTitle f10 c9">
                        <span class="rankBtn"></span>
                        <span aa="">话题<em id="threadCount">${newscount}</em></span><span>成员<em>${subscribercount}</em></span></p>
                    <!--<span>访问<em id="sitePV">108594</em></span>-->
                    </p>
                </div>
                <!--<a href="javascript:;" class="addBbsBtn db pa followButton" sid="211873835"><span class="addBbs db  br f13 c2">加入</span></a>-->
            </header>

            <div id="showCarousel" class="showCarousel" style="margin-bottom: 8px; visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 100%;display: none;">
                <ul class="sCUl" style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 100%; left: 0px;">
                    <li class="sCLi" style="width: 100%; height: auto; overflow: hidden; float: left;">
                        <div class="customArea customHide">
                            <div class="caSide" style="display: block;">
                                <h4 class="f11 fn c6"></h4>
                                <a href="javascript:void(0);" onclick="javascript:location.reload();" id="messagesign" class="c9 db cf sCNext"></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="container" id="threadList" style="min-height:240px;"><div class="tlNode" id="">
                    <c:forEach items="${newsList}" var="target">
                        <div class="topicBox" id="t_${target.id}_0_0" tid="1631">
                            <div class="topicCon">
                                <div class="personImgDate pr">
                                    <i class="perImg mr db fl pr avatar" uid="214149892">
                                        <img src="${pageContext.servletContext.contextPath}/web/weiforum/logo.png" data-original="${pageContext.servletContext.contextPath}/web/weiforum/logo.png" class="pImg brBig lazy-loaded" width="35" height="35" alt="头像">
                                    </i>
                                    <h3 class="title f13 fn c6">万巷坊
                                        <span class="honorBg brSmall"><i class="iconRank db cf c2"></i></span>
                                    </h3>
                                    <p class="f11">
                                        <span class="time c9 mr">${target.times}</span>
                                        <span class="address c6"><i class="iconloc f14 c1 cf"></i>济南</span>
                                    </p>
                                    <a class="iconAd cf f16 c9 pa db PerPopBtn" tid="1631" href="javascript:;"></a>
                                    <div class="adPop pa perPop" style="display:none;">
                                        <div class="adBCon pr  f12" tid="${target.id}" uid="214149892" author="${target.id}">
                                            <p btntype="markAd">屏蔽</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="detailCon">
                                    <div class="dCon f16">
                                        <!--{target.name}<p/>-->
                                        ${target.content}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div></div>
            <div class="loading f14" id="loadNext" style="display:none;">
                <div class="loadicon">
                    <span class="blockG" id="rotateG_01"></span>
                    <span class="blockG" id="rotateG_02"></span>
                    <span class="blockG" id="rotateG_03"></span>
                    <span class="blockG" id="rotateG_04"></span>
                    <span class="blockG" id="rotateG_05"></span>
                    <span class="blockG" id="rotateG_06"></span>
                    <span class="blockG" id="rotateG_07"></span>
                    <span class="blockG" id="rotateG_08"></span>
                </div>
                正在加载...
            </div>
            <div id="loadNextPos"></div>
            <div class="loading" id="showAll" style="display:none;">已显示全部</div>
        </div>
        <div style="height: 55px;"></div>
        <div id="mcover" onclick="document.getElementById('mcover').style.display = '';" style="display:none;">
            <img src="http://stc.weimob.com/img/guide.png">
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                $("a[name=pointcount]").click(function() {
                    var id = $(this).attr("id");
                    $.post("${pageContext.servletContext.contextPath}/ForeServlet?method=pointcount", {"openid": $("#openid").val(), "target": id, "sid": "1"}, function(result) {
                        if ("0" == result) {
                            $("#" + id + "_show").html(Number($("#" + id + "_show").html()) + Number(1));
                            $("#" + id + "_iconNoPraise").removeClass("iconNoPraise f18 cf").addClass("iconPraise f18 cf");
                        }
                    });
                });
                $("a[name=discusscount]").click(function() {
                    var id = $(this).attr("id");
                    var discontent = prompt("回两句吧...", "");
                    if (discontent != null && discontent != "") {
                        $.post("${pageContext.servletContext.contextPath}/ForeServlet?method=discussadd", {"openid": $("#openid").val(), "target": id, "discontent": discontent, "isreply": "0"}, function(result) {
                            if (result) {
                                $("#" + id + "_discussshow").html(Number($("#" + id + "_discussshow").html()) + Number(1));
                                window.setTimeout(function() {
                                    location.reload();
                                }, 1000);
                            }
                        });
                    }
                });
                $("#publishThread").click(function() {
                    location.replace("${pageContext.servletContext.contextPath}/ForeServlet?method=weiforuminitadd_do&openid=" + $("#openid").val() + "&sid=${sid}");
                });
            });
            wx.ready(function() {
                function previewImage(img, imgs) {
                    wx.previewImage({
                        current: img,
                        urls: imgs
                    });
                }
                $(".detailCon img").click(function() {
                    var img = "${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}" + $(this).attr("src");
                    var imgs = [];
                    $(this).parent().find("img").each(function() {
                        imgs.push("${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}" + $(this).attr("src"));
                    });
                    previewImage(img, imgs);
                });
            });
            $("#threadList").find(".detailCon").each(function() {
                var imgcount = $(this).find("img").length;
                if (1 == imgcount) {
                    $(this).find("img").each(function() {
                        $(this).attr("width", "100%");
                    });
                } else if (2 == imgcount) {
                    $(this).find("img").each(function() {
                        $(this).attr("width", "49%");
                    });
                } else {
                    $(this).find("img").each(function() {
                        $(this).attr("width", "32%");
                    });
                }
            });
        </script>

        <!--        <div class="topBar">
                    <div class="topBarCon pr">
                        <a href="javascript:history.back();" class="qBackBtn" id="goback"><i class="upBtn cf db"></i><span style="color: white">返回</span></a>
                        <h2 class="qTitle">万巷坊微论坛</h2>
                        <img src="${pageContext.servletContext.contextPath}/web/images/logo.png" height="80%"/>
                        <a style="color:#fff;" class="qPublish db" href="http://ui.ptlogin2.qq.com/cgi-bin/login?appid=710044201&amp;s_url=http%3A%2F%2Fm.wsq.qq.com%2F211873835&amp;style=8&amp;hln_css=http%3A%2F%2Fdzqun.gtimg.cn%2Fquan%2Fimages%2FloginLogo.png&amp;hln_autologin=true"><i class="iconPost f21 cf db"></i></a>
                        <a href="javascript:;" id="mqOption" issite="1" class="moreC qMore db" style="display:none;"><i class="circle">●</i><i class="circle">●</i><i class="circle">●</i><i class="db numP" id="navMsgNum">0</i></a>
                    </div>
                </div>-->
        <!--        <div class="bottomBar" id="bottomBar">
                    <div class="bottomBarCon">
                        <a href="javascript:history.back();" class="backBtn needsclick" id="goback"><i class="iconAnswer cf back db"></i></a>
                        <a href="javascript:void(0);" id="publishThread" sid="211873835" class="publish db"><i class="iconPost f18 mr5 cf"></i>发话题</a>
                        <a href="javascript:;" class="moreC db" id="mqOption" issite="1"><i class="circle">●</i><i class="circle">●</i><i class="circle">●</i>
                            <i class="db numP" id="navMsgNum">1</i>
                        </a>
                    </div>
                </div>-->

        <div class="floatLayer br db" id="goTop" style="display: none;"><a class="upBtn cf db" href="#"></a></div>
        <script type="text/javascript">
            var shareUrl = "${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/ForeServlet?method=weizhibo&sid=11",
                    shareTitle = '分享自#万巷坊#',
                    shareDesc = '为了方便跟大家更好的交流，万巷坊开通活动微直播啦~',
                    shareImgUrl = '${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.servletContext.contextPath}/web/weiforum/logo.png';
            g_module.push('module/common', 'module/share');
        </script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/web/weiforum/share.js">
        </script>
    </body>
</html>