<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
        <meta name="description" content="乐享微信">
        <title>${activity.name}</title>
        <link href="${pageContext.servletContext.contextPath}/css/activity-style.css" rel="stylesheet" type="text/css">
        <!--<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/safe.js"></script>-->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.rotate.min.js"></script>
    </head>
    <body class="activity-lottery-winning">
        <c:if test="${activity.starttime le now && now le activity.endtime && '0'==activity.state}" var="teststate">
            <div class="main" style="overflow: hidden;">
                <div id="outercont">
                    <div id="outer-cont">
                        <div id="outer"><img src="${pageContext.servletContext.contextPath}/img/activity-lottery-3.png" width="100%"></div>
                    </div>
                    <div id="inner-cont">
                        <div id="inner"><img src="${pageContext.servletContext.contextPath}/img/activity-lottery-2.png"></div>
                    </div>
                </div>
                <div class="content">
                    <div class="boxcontent boxyellow" id="result" style="display:none">
                        <div class="box">
                            <div class="title-orange"><span>恭喜你中奖了</span></div>
                            <div class="Detail">
                                <a class="ui-link" href="http://www.weixinjia.net/mobile/showresult.html" id="opendialog" style="display: none;" data-rel="dialog"></a>
                                <p>你中了：<span class="red" id="prizetype">一等奖</span></p>
                                <p>你的兑奖SN码：<span class="red" id="sncode"></span></p>
                                <p class="red">本次兑奖码已经关联你的微信号，你可向公众号发送 兑奖 进行查询!</p>

                                <p>
                                    <input name="" class="px" id="tel" type="text" placeholder="输入您的手机号码">
                                </p>
                                <p>
                                    <input class="pxbtn" id="save-btn" name="提 交" type="button" value="提 交">
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="boxcontent boxyellow">
                        <div class="box">
                            <div class="title-green"><span>奖项设置：</span></div>
                            <div class="Detail">
                                58到家红包<br/>
                                德馨斋礼包<br/>
                                挑替礼包<br/>
                                第一坊礼包

                                <!--c:forEach items="{prizeList}" var="prize">
                                    <br/>{prize.codename}:{prize.name}
                                /c:forEach-->
                            </div>
                        </div>
                    </div>
                    <div class="boxcontent boxyellow">
                        <div class="box">
                            <div class="title-green">活动说明：</div><br/>
                            <div class="Detail">
                                <p>
                                    ${activity.remark}
                                    投票就送一次转盘机会，多投多送！
                                </p>
                                <p>
                                    <!--发送微信“<span style="color:#F00; font-weight:bold;">${activity.activitykey}</span>”查询获奖情况。<br>-->
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                var lostDeg = [66, 186, 306];
                var prizeDeg = [6, 126, 246];
                var giftDeg = [36, 96, 156, 216, 276, 336];
                var prize, sncode, pname;
                outter = document.getElementById('outer');
                inner = document.getElementById('inner');
                $("#inner").bind("click", roulette);
                function roulette() {
                    $("#inner").unbind();
                    $.ajax({
                        url: "${pageContext.servletContext.contextPath}/ForeServlet?method=roulette_do2",
                        dataType: "json",
                        data: {
                            openid: "${openid}",
                            aid: '${activity.id}'
                        },
//                        beforeSend: function() {
//                            timer = setInterval(function() {
//                                i += 5;
//                                outter.style.webkitTransform = 'rotate(' + i + 'deg)';
//                                outter.style.MozTransform = 'rotate(' + i + 'deg)';
//                            }, 1);
//                        },
                        success: function(data) {
                            //投票临时区
                            if ("1" == data) {
                                $("#outer").rotate({
                                    duration: 8000, //转动时间间隔（转动速度） 
                                    angle: 6, //开始角度 
                                    animateTo: 360 * 5 + 336, //转动角度
                                    easing: $.easing.easeOutSine, //动画扩展 
                                    callback: function() { //回调函数 
                                        alert("恭喜您获得58到家红包，请领取！")
                                        window.setTimeout(function() {
                                            window.location.replace("http://t.jzt.58.com/releaseCoupon/releaseHongbaoIndex?info=ACFCC05689184097385B838C07DB5827B1468D804454FA936F7A714F3162F157");
                                        }, 500);
                                    }
                                });
                                return;
                            } else {
                                $("#outer").rotate({
                                    duration: 8000, //转动时间间隔（转动速度） 
                                    angle: 6, //开始角度 
                                    animateTo: 360 * 5 + (36 || 216), //转动角度
                                    easing: $.easing.easeOutSine, //动画扩展 
                                    callback: function() { //回调函数 
                                        alert("没有中奖，不要灰心哦，明天再来吧！");
                                        return;
                                    }
                                });
                            }
                            // //投票临时区结束
//                            if ("invalid" == data.error) {
//                                $("#outer").rotate({
//                                    duration: 1000, //转动时间间隔（转动速度） 
//                                    angle: 6, //开始角度 
//                                    animateTo: 180, //转动角度
//                                    easing: $.easing.easeOutSine, //动画扩展 
//                                    callback: function() { //回调函数 
//                                        alert("${activity.overpoint}");
//                                    }
//                                });
//                                return;
//                            }
//                            if ("getsn" == data.error) {
//                                $("#outer").rotate({
//                                    duration: 1000, //转动时间间隔（转动速度） 
//                                    angle: 6, //开始角度 
//                                    animateTo: 180, //转动角度
//                                    easing: $.easing.easeOutSine, //动画扩展 
//                                    callback: function() { //回调函数 
//                                        alert("${activity.overpoint}");
//                                    }
//                                });
//                                return;
//                            }
//                            if ("point" == data.error) {
//                                prize = data.prizetype;
//                                sncode = data.sn;
//                                pname = data.pname;
//                                if (4 == prize || 5 == prize) {
//                                    $("#outer").rotate({
//                                        duration: 8000, //转动时间间隔（转动速度） 
//                                        angle: 6, //开始角度 
//                                        animateTo: 360 * 3 + giftDeg[parseInt(giftDeg.length * Math.random())], //转动角度
//                                        easing: $.easing.easeOutSine, //动画扩展 
//                                        callback: function() { //回调函数 
////                                            alert('恭喜您中了' + data.codename + "!");
//                                            $("#sncode").text(sncode);
//                                            $("#prizetype").text(pname);
//                                            $("#result").slideToggle(500);
//                                            $("#outercont").slideUp(500);
//                                        }
//                                    });
//                                } else {
//                                    $("#outer").rotate({
//                                        duration: 8000, //转动时间间隔（转动速度） 
//                                        angle: 6, //开始角度 
//                                        animateTo: 360 * 3 + prizeDeg[prize - 1], //转动角度
//                                        easing: $.easing.easeOutSine, //动画扩展 
//                                        callback: function() { //回调函数 
////                                            alert('恭喜您中了' + data.codename + "!");
//                                            $("#sncode").text(sncode);
//                                            $("#prizetype").text(pname);
//                                            $("#result").slideToggle(500);
//                                            $("#outercont").slideUp(500);
//                                        }
//                                    });
//                                }
//                            } else {
//                                $("#outer").rotate({
//                                    duration: 8000, //转动时间间隔（转动速度） 
//                                    angle: 6, //开始角度 
//                                    animateTo: 360 * 3 + lostDeg[parseInt(lostDeg.length * Math.random())], //转动角度
//                                    easing: $.easing.easeOutSine, //动画扩展 
//                                    callback: function() { //回调函数 
//                                        alert("${activity.nopoint}");
//                                        $("#inner").bind("click", roulette);
//                                        return;
//                                    }
//                                });
//                            }
//                        },
//                        error: function() {
//                            $("#outer").rotate({
//                                duration: 8000, //转动时间间隔（转动速度） 
//                                angle: 6, //开始角度 
//                                animateTo: 360 * 3 + lostDeg[parseInt(lostDeg.length * Math.random())], //转动角度
//                                easing: $.easing.easeOutSine, //动画扩展 
//                                callback: function() { //回调函数 
//                                    alert("${activity.nopoint}");
//                                    $("#inner").bind("click", roulette);
//                                    return;
//                                }
//                            });
                        },
                        timeout: 3000
                    });
                }
                $("#save-btn").bind("click", function() {
                    var btn = $(this);
                    var tel = $("#tel").val();
                    if (tel == '') {
                        alert("请输入手机号码");
                        return;
                    }
                    var regu = /^(13|14|15|18)[0-9]{9}$/;
                    var re = new RegExp(regu);
                    if (!re.test(tel)) {
                        alert("请输入正确手机号码");
                        return;
                    }
                    var submitData = {
                        code: $("#sncode").text(),
                        tel: tel
                    };
                    $.post('${pageContext.servletContext.contextPath}/ForeServlet?method=roulette_ok', submitData, function(data) {
                        alert("提交成功！");
                        return window.location.href = "${pageContext.servletContext.contextPath}/ForeServlet?method=roulette&id=${activity.id}&openid=${openid}";
                    }, "json");
                });
            </script>
        </c:if>
        <c:if test="${!teststate}">
            <div class="main">
                <div id="outercont">
                    <div id="outer-cont">
                        <div id="outer"><img src="${pageContext.servletContext.contextPath}/img/activity-lottery-3.png" width="100%"></div>
                    </div>
                    <div id="inner-cont">
                        <div id="inner"><img src="${pageContext.servletContext.contextPath}/img/activity-lottery-2.png"></div>
                    </div>
                </div>
                <div class="content">
                    <div class="boxcontent boxyellow">
                        <div class="box">
                            <div class="title-green"><span>奖项设置：</span></div>
                            <div class="Detail">
                                <c:forEach items="${prizeList}" var="prize">
                                    <br/>${prize.codename}:${prize.name}
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="boxcontent boxyellow">
                        <div class="box">
                            <div class="title-green">活动说明：</div><br/>
                            <div class="Detail">
                                <p>
                                    ${activity.endpoint}
                                </p>
                                <p>
                                    发送微信“<span style="color:#F00; font-weight:bold;">${activity.activitykey}</span>”查询获奖情况。<br>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>