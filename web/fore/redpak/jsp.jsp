<%-- 
    Document   : rules
    Created on : 2015-8-4, 16:08:00
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0067)http://sale.suning.com/images/advertise/001/hbgz30/active-rule.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta content="telephone=no" name="format-detection">
        <title>微红包</title>
        <link rel="stylesheet" href="http://s.404.cn/tpl/static/packet/css/base.css">
        <link rel="stylesheet" href="http://s.404.cn/tpl/static/packet/css/redbg.css">
        <style>
            .activeRule-content{min-height:350px;}
            .activeRule-top:before {content:'我的红包';}
            .activeRule-content .table{width: 100%; text-align: center; color: #ff6741;}
            .activeRule-content .table th{margin:10px 0;color:#ff6741;}
            .activeRule-content .table td a{color:#31a8e7;}
        </style>
    </head>
    <body >
        <section class="pdlayout" >
            <div class="activeRule-top pr ">
                <img src="http://s.404.cn/tpl/static/packet/images/active_bg.png" width="100%" height="59">
            </div>
            <!--
            <div class="activeRule-content  pdlayout pr">
                        活动规则            </div>
            -->
            <c:if test="${'1'==sign}" var="flag">
                <div class="activeRule-content  pdlayout pr">
                    <table class="table" cellspacing="0" cellpadding="0" border="0">
                        <thead>
                        <th>活动名称</th>
                        <th>中奖内容</th>
                        <th>中奖时间</th>
                        <th>红包状态</th>
                        </thead>
                        <c:forEach items="${sendredpacklogsList}" var="sendredpacklogs">
                            <tbody>
                                <tr>
                                    <td>${sendredpacklogs.act_name}</td>
                                    <td>${(0!=sendredpacklogs.total_amount?sendredpacklogs.total_amount/100:(""!=sendredpacklogs.remark?sendredpacklogs.remark:sendredpacklogs.total_amount/100))==0?"红包X5":""}</td>
                                    <td>${sendredpacklogs.times}</td>
                                    <td>${"0"==sendredpacklogs.state?"未兑奖":("0"==sendredpacklogs.isexp?"未兑奖":"已兑奖")}</td>
                                </tr> 
                            </tbody>
                        </c:forEach>
                    </table>
                    <c:if test="${''!=path}">
                        <img src='/${path}' style="width:50%;position: relative;margin-left: 25%;margin-top: 10%;">
                    </c:if>
                </div>
            </c:if>
            <c:if test="${!flag}">
                <div class="activeRule-content  pdlayout pr">
                    ${map.remark}
                </div>
            </c:if>
        </section>
        <script type="text/javascript">
            window.shareData = {
                "moduleName": "Red_packet",
                "moduleID": "0",
                "imgUrl": "http://s.404.cn/tpl/static/packet/images/msg_pic.png",
                "timeLineLink": "http://www.weixinrj.cn/index.php?g=Wap&m=Red_packet&a=index&token=ddorlg1438670446&id=388",
                "sendFriendLink": "http://www.weixinrj.cn/index.php?g=Wap&m=Red_packet&a=index&token=ddorlg1438670446&id=388",
                "weiboLink": "http://www.weixinrj.cn/index.php?g=Wap&m=Red_packet&a=index&token=ddorlg1438670446&id=388",
                "tTitle": "微红包",
                "tContent": "微红包"
            };
        </script>
        <script>
            window.shareData.sendFriendLink = window.shareData.sendFriendLink.replace('http://www.weixinrj.cn', 'http://www.weixinrj.cn');
            document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
                WeixinJSBridge.on('menu:share:appmessage', function(argv) {
                    shareHandle('friend');
                    WeixinJSBridge.invoke('sendAppMessage', {
                        "img_url": window.shareData.imgUrl,
                        "img_width": "640",
                        "img_height": "640",
                        "link": window.shareData.sendFriendLink,
                        "desc": window.shareData.tContent,
                        "title": window.shareData.tTitle
                    }, function(res) {
                        _report('send_msg', res.err_msg);
                    })
                });

                WeixinJSBridge.on('menu:share:timeline', function(argv) {
                    shareHandle('frineds');
                    WeixinJSBridge.invoke('shareTimeline', {
                        "img_url": window.shareData.imgUrl,
                        "img_width": "640",
                        "img_height": "640",
                        "link": window.shareData.sendFriendLink,
                        "desc": window.shareData.tContent,
                        "title": window.shareData.tTitle
                    }, function(res) {
                        _report('timeline', res.err_msg);
                    });
                });

                WeixinJSBridge.on('menu:share:weibo', function(argv) {
                    shareHandle('weibo');
                    WeixinJSBridge.invoke('shareWeibo', {
                        "content": window.shareData.tContent,
                        "url": window.shareData.sendFriendLink,
                    }, function(res) {
                        _report('weibo', res.err_msg);
                    });
                });
            }, false)

            function shareHandle(to) {
                var submitData = {
                    module: window.shareData.moduleName,
                    moduleid: window.shareData.moduleID,
                    token: 'ddorlg1438670446',
                    wecha_id: 'o3USLjo1enMSH9poZIeKzwzHf__w',
                    url: window.shareData.sendFriendLink,
                    to: to
                };
//                $.post('/index.php?g=Wap&m=Share&a=shareData&token=ddorlg1438670446&wecha_id=o3USLjo1enMSH9poZIeKzwzHf__w', submitData, function(data) {
//                }, 'json')
            }
        </script>	   
    </body>
</html>