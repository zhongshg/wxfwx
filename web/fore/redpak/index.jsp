<%-- 
    Document   : index
    Created on : 2015-7-1, 14:49:53
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0069)http://wdqr.com/wxhb/indexhbc.htm?from=singlemessage&isappinstalled=0 -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>万巷坊</title>
        <meta charset="utf-8">
        <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="black" name="apple-mobile-web-app-status-bar-style">
        <meta content="telephone=no" name="format-detection">
        <meta content="email=no" name="format-detection">
        <meta content="www.tenpay.com;" name="pgv">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/fore/redpak/js/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/fore/redpak/js/alert.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/fore/redpak/css/packet.css">
    </head>
    <c:if test="${map.starttime le now && now le map.endtime}" var="actyes" ></c:if>
    <c:if test="${actyes && ('success'==message)}" var="actyesactyes"></c:if>
    <body class="receive ${actyes?(actyesactyes?'show-unopen':'show-received'):'show-expired'} show-random" id="js_stat">
        <!-- 交互说明
            给"receive"添加样式控制不同页面
            1.未拆开|定额 show-unopen
            2.未拆开|随机 show-random
                a 点击"pack-w-s"时给"receive"添加"show-opend"
                b 拆开后，点击"返回"时，将"receive"的样式"show-opend"移除
                c.领自己红包 追加"show-self"
            3.拆开 show-opend
            4.超过有效期 show-expired
            5.已经被领取 show-received
            6.拆开失败 show-open-fail
        <audio src="crack.mp3" style="display:none;" id="music2"></audio>
        <audio src="broken.mp3" style="display:none;" id="music1"></audio>
        <audio src="broken.mp3" style="display:none;" id="music3"></audio> -->

        <div class="un-open " id="hzl1">
            <div class="light">
                <span class="round"></span>

                <h1 class="able-txt"><span id="nickname_contain"></span><span class="tips-random">
                        <label id="nick1"></label>

                        “万巷坊”微信红包            </span>
                </h1>

                <div class="hb-wrap">

                    <div class="money-circle">
                        <span class="ingot"></span>
                        <span class="ingot"></span>
                    </div>

                    <div class="pack-w pack-w-s" id="open_hongbao">
                        <div class="pack">
                            <span class="btn-open"></span>
                        </div>
                        <span class="tips-open"></span>
                        <span class="ingot"></span>
                    </div>
                </div>
            </div>
        </div>
        <footer class="bag-bottom-fixed hb-footer">
            <ul>
                <li class="my-red-bag"><a id="hb-to-my" href="javascript:void(0);">我的红包</a></li>
                <li class="go-shopping"><a id="hb-to-rule" href="javascript:void(0);">活动规则</a></li>
            </ul>
        </footer>
        <audio id="musicBox" src=""></audio>
        <script>
            $(function() {
                $('#open_hongbao').bind("click", clickred);

                $('#hb-to-my').click(function() {
                    window.location.href = "${pageContext.servletContext.contextPath}/ForeServlet?method=myredpack&openid=${openid}&mch_billno=${map.mch_billno}&sign=1&g=Wap&m=Red_packet&a=my_packet&token=zpnipw1435711592&wecha_id=o3USLjo1enMSH9poZIeKzwzHf__w&id=270";
                });
                $('#hb-to-rule').click(function() {
                    window.location.href = "${pageContext.servletContext.contextPath}/ForeServlet?method=myredpack&openid=${openid}&mch_billno=${map.mch_billno}&sign=2g=Wap&m=Red_packet&a=rule&token=zpnipw1435711592&wecha_id=o3USLjo1enMSH9poZIeKzwzHf__w&id=270";
                });
            });

            function clickred() {
                $("#open_hongbao").unbind();
                if (${actyes}) {
                    if (${actyesactyes}) {
                        shake();
                    } else {
                        alert("次数已用完，下次再来吧!");
                    }
                } else {
                    alert("没有可以领取的红包，敬请期待吧！");
                }
            }
            /*
             var i=0;
             var obj = document.getElementById('js_stat');
             obj.addEventListener('touchstart', function(event) {
             
             if(i>=0){
             alert("1.将本红包转发到任意群。</p> 2.添加下方微信号为好友。</p>微信号:584350</p> 3.将本红包转发到朋友圈。</p>注:按顺序完成后领取。");
             }else{
             // 如果这个元素的位置内只有一个手指的话
             var Arr = ["music1","music2","music3"]; 
             //var n = Math.floor(Math.random() * Arr.length + 1)-1;
             document.getElementById(Arr[i]).play();
             var oDiv=document.createElement('div');
             oDiv.style.left=event.targetTouches[0].pageX-150+'px';  // 指定创建的DIV在文档中距离左侧的位置
             oDiv.style.top=event.targetTouches[0].pageY-150+'px';  // 指定创建的DIV在文档中距离顶部的位置
             oDiv.style.zIndex='999'; // 设置边框
             oDiv.style.position='absolute'; // 为新创建的DIV指定绝对定位
             oDiv.innerHTML='<img src="22.png"  width="300"/>'; // 指定高度
             document.body.appendChild(oDiv);
             }
             i++;
             }, false);
             */
            shakeDevice();
            function shakeDevice() {
                if (window.DeviceMotionEvent) {
                    window.addEventListener('devicemotion', deviceMotionHandler, false);
                } else {
                    alert("您的浏览器不支持运动传感事件,请点击红包摇奖！");
                }
                last_update = 0;
                x = 0;
                y = 0;
                z = 0;
                last_x = 0;
                last_y = 0;
                last_z = 0;
            }
            function deviceMotionHandler(eventData) {
                var acceleration = eventData.accelerationIncludingGravity;
                var curTime = new Date().getTime();
                var diffTime = curTime - last_update;
                if (diffTime > 100) {
                    last_update = curTime;
                    x = acceleration.x;
                    y = acceleration.y;
                    z = acceleration.z;
                    var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                    if (speed > 3000) {//2000为移动摇一摇使用的频率
                        if (${actyes}) {
                            if (${actyesactyes}) {
//                                play("{pageContext.servletContext.contextPath}/fore/redpak/img/shake_sound_male.mp3");
//                                window.setTimeout(shake, 2000);
                                shake();
                            } else {
                                alert("您已经领过红包了，下次再来吧!");
                            }
                        } else {
                            alert("没有可以领取的红包，敬请期待吧！");
                        }
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }

            function shake() {
                $.post('${pageContext.servletContext.contextPath}/ForeServlet?method=sendredpackcoupon_do&m=Red_packet&a=getPacket', {token: 'zpnipw1435711592', openid: '${openid}', id: "${map.id}"}, function(data) {
                    data = eval('(' + data + ')');
                    if ("successopen" == data.message) {
//                        play("{pageContext.servletContext.contextPath}/fore/redpak/img/shake_match.mp3");
                        $('.due-to-pages').addClass('show-open');
                        $("#js_stat").addClass("receive show-received show-random");
                    } else {
//                        play("{pageContext.servletContext.contextPath}/fore/redpak/img/shake_nomatch.mp3");
                        $('.due-to-pages').addClass('dut-to-over');
                    }
//                    window.setTimeout(function() {
                    alert(data.msg);
//                    }, 1000);
                    $('#open_hongbao').bind("click", clickred);
                });
            }
            function play(path) {
                var musicBox = document.getElementById("musicBox");
                musicBox.src = path;
                musicBox.play();//pause()
                window.setInterval(function() {
                    if (musicBox.paused) {
                        window.clearInterval();
                        return;
                    }
                }, 1000);
            }
        </script>	

        <script type="text/javascript">
//            window.shareData = {
//                "moduleName": "Red_packet",
//                "moduleID": "0",
//                "imgUrl": "img/msg_pic.png",
//                "timeLineLink": "http://www.weixinrj.cn/index.php?g=Wap&m=Red_packet&a=index&token=zpnipw1435711592&id=270",
//                "sendFriendLink": "http://www.weixinrj.cn/index.php?g=Wap&m=Red_packet&a=index&token=zpnipw1435711592&id=270",
//                "weiboLink": "http://www.weixinrj.cn/index.php?g=Wap&m=Red_packet&a=index&token=zpnipw1435711592&id=270",
//                "tTitle": "微信红包",
//                "tContent": "微信红包"
//            };
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
                    token: 'zpnipw1435711592',
                    wecha_id: 'o3USLjo1enMSH9poZIeKzwzHf__w',
                    url: window.shareData.sendFriendLink,
                    to: to
                };
//                $.post('/index.php?g=Wap&m=Share&a=shareData&token=zpnipw1435711592&wecha_id=o3USLjo1enMSH9poZIeKzwzHf__w', submitData, function(data) {
//                }, 'json')
            }
        </script>	
    </body>
</html>