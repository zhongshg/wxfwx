<%-- 
    Document   : index
    Created on : 2015-12-25, 10:25:24
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>微留言</title><script type="text/javascript">var yyuc_jspath = "/@system/";</script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/fore/forum/system/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/fore/forum/system/js/yyucadapter.js"></script>
        <meta content="" name="Keywords">
        <meta content="" name="Description">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/fore/forum/css/mwm/msg/msg.css" media="all" />	

        <meta content="" name="description">
        <meta content="" name="keywords">
        <meta content="eric.wu" name="author">
        <meta content="application/xhtml+xml;charset=UTF-8" http-equiv="Content-Type">
        <meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
        <meta content="no-cache" http-equiv="pragma">
        <meta content="0" http-equiv="expires">
        <meta content="telephone=no, address=no" name="format-detection">
        <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">

        <script type="text/javascript">
            $(document).ready(function() {
                $("#showcard1").click(function() {
                    var btn = $(this);
                    var wxname = $("#wxname1").val();
                    if (wxname == '') {
                        alert("请输入昵称");
                        return;
                    }
                    var info = $("#info1").val();
                    if (info == '') {
                        alert("请输入内容");
                        return;
                    }
                    var submitData = {
                        nc: wxname,
                        msg: info,
                        pid: "0",
                        sid: "${sid}",
                        openid: "${openid}"
                    };
                    $.post('${pageContext.servletContext.contextPath}/ForeServlet?method=wx2forum_do', submitData,
                            function(data) {
                                if (data == 'ok') {
                                    alert('留言成功');
                                    setTimeout("window.location.replace('${pageContext.servletContext.contextPath}/ForeServlet?method=wx2forum&sid=${sid}&openid=${openid}')", 1000);
                                    return;
                                } else {
                                    alert("留言失败，请稍后重试！");
                                    return;
                                }
                            });
                });
                //
                $("#showcard2").click(function() {
                    var btn = $(this);
                    var wxname = $("#wxname2").val();
                    if (wxname == '') {
                        alert("请输入昵称");
                        return;
                    }
                    var info = $("#info2").val();
                    if (info == '') {
                        alert("请输入内容");
                        return;
                    }
                    var submitData = {
                        nc: wxname,
                        msg: info,
                        pid: "0",
                        sid: "${sid}",
                        openid: "${openid}"
                    };
                    $.post('${pageContext.servletContext.contextPath}/ForeServlet?method=wx2forum_do', submitData,
                            function(data) {
                                if (data == 'ok') {
                                    alert('留言成功');
                                    setTimeout("window.location.replace('${pageContext.servletContext.contextPath}/ForeServlet?method=wx2forum&sid=${sid}&openid=${openid}')", 1000);
                                    return;
                                } else {
                                    alert("留言失败，请稍后重试！");
                                    return;
                                }
                            });
                });
                //
                $(".hhsubmit").click(function() {
                    var objid = $(this).attr("date");
                    var info = $(".hly" + objid).val();
                    if (info == '') {
                        alert("请输入内容");
                        return;
                    }
                    var submitData = {
                        nc: '我很好',
                        pid: objid,
                        msg: info,
                        sid: "${sid}",
                        openid: "${openid}"
                    };
                    $.post('${pageContext.servletContext.contextPath}/ForeServlet?method=wx2forum_do', submitData,
                            function(data) {
                                if (data == 'ok') {
                                    alert('回复成功');
                                    setTimeout("window.location.replace('${pageContext.servletContext.contextPath}/ForeServlet?method=wx2forum&sid=${sid}&openid=${openid}')", 1000);
                                    return;
                                } else {
                                    alert("回复失败，请稍后重试！");
                                    return;
                                }
                            });
                });
                //
                $(".hfinfo").click(function() {
                    var objid = $(this).attr("date");
                    $(".hhly" + objid).slideToggle();
                });
                //
                $(".hhbt").click(function() {
                    var objid = $(this).attr("date");
                    $(".hhly" + objid).slideToggle();
                });
                //
                $("#windowclosebutton").click(function() {
                    $("#windowcenter").slideUp(500);
                });
                //
                $("#alertclose").click(function() {
                    $("#windowcenter").slideUp(500);
                });
            });
            //
            function alert(title) {
                window.scrollTo(0, -1);
                $("#windowcenter").slideToggle("slow");
                $("#txt").html(title);
                setTimeout(function() {
                    $("#windowcenter").slideUp(500);
                }, 4000);
            }
            //
            $(document).ready(function() {
                $(".first1").click(function() {
                    $(".ly1").slideToggle();
                });
                $(".first2").click(function() {
                    $(".ly2").slideToggle();
                });
            });
        </script> 
    </head>

    <body id="message" onselectstart="return true;" ondragstart="return false;">
        <div class="container">
            <div class="qiandaobanner">
                <a href="javascript:history.go(-1);">
                    <img src="${pageContext.servletContext.contextPath}/fore/forum/res/lyheadpic.jpg" style="width:100%;" />
                </a>
            </div>

            <div class="cardexplain">
                <div class="window" id="windowcenter">
                    <div id="title" class="wtitle">操作提示<span class="close" id="alertclose"></span></div>
                    <div class="content">
                        <div id="txt"></div>
                    </div>
                </div>

                <div class="history">
                    <div class="history-date">
                        <ul>
                            <a><h2 class="first first1" style="position: relative;">请点击留言</h2></a>
                            <!--<li class="nob  mb"><div class="beizhu">留言审核通过后才会显示在留言墙上！</div></li>-->
                            <li class="green bounceInDown nob ly1" style="display:none">
                                <dl>
                                    <dt>
                                    <input name="wxname" type="text" class="px" id="wxname1" value="" placeholder="请输入您的昵称">
                                    </dt>
                                    <dt>
                                    <textarea name="info" class="pxtextarea" style=" height:60px;" id="info1" placeholder="请输入留言"></textarea>
                                    </dt>
                                    <dt><a id="showcard1" class="submit" href="javascript:void(0)">提交留言</a></dt>
                                </dl>
                            </li>
                            <c:forEach items="${map}" var="map">
                                <li class="green bounceInDown">
                                    <h3>
                                        <!-- <img src="http://www.apiwx.com/index/images/logo100x100.jpg"> -->
                                        ${map.key.name}<span>${map.key.times}</span>
                                        <div class="clr"></div>
                                    </h3>
                                    <dl>
                                        <dt class="hfinfo">${map.key.remark}</dt>
                                    </dl>
                                    <dl class="huifu">
                                        <dt>
                                        <span>																														
                                            <a class="hhbt czan" date="${map.key.id}" href="javascript:void(0)">回复</a>
                                            <p style="display:none;" class="hhly${map.key.id}">
                                                <textarea name="info" class="pxtextarea hly${map.key.id}"></textarea> 
                                                <a class="hhsubmit submit" date="${map.key.id}" href="javascript:void(0)">确定</a>
                                            </p>
                                        </span>
                                        </dt>
                                    </dl>
                                    <c:forEach items="${map.value}" var="reply">
                                        <dl class="huifu">
                                            <dt><span>${reply.name}回复：${reply.remark}</span></dt>
                                        </dl>
                                    </c:forEach>
                                </li>
                            </c:forEach>


                            <li class="green bounceInDown nob ly2" style="display:none;">
                                <dl>
                                    <dt>
                                    <input name="wxname" type="text" class="px" id="wxname2" value="" placeholder="请输入您的昵称">
                                    </dt>
                                    <dt>
                                    <textarea name="info" class="pxtextarea" style=" height:60px;" id="info2" placeholder="请输入留言"></textarea>
                                    </dt>
                                    <dt>
                                    <a id="showcard2" class="submit" href="javascript:void(0)">提交留言</a>
                                    </dt>
                                </dl>
                            </li>
                            <a><h2 class="first first2" style="position: relative;">请点击留言</h2></a>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">

    </script>
    <div class="mfooter" id="wxgjfooter" style="text-align: center;width: 100%;height: 20px;line-height: 20px;margin-top:10px;">
        <span class="sp2"><a href="http://www.tl-kj.com/" style="color: #5e5e5e;font-size: 12px;">@天澜科技（电话0531-88918191）技术支持</a></span>
    </div>
    <div style="width: 0px;height: 0px;overflow: hidden;">
        <script src="http://s22.cnzz.com/z_stat.php?id=1000151448&web_id=1000151448" language="JavaScript"></script>
    </div>
    <script>
        /**
         $(function(){
         if($('body').height()<$(window).height()){
         $('body').height($(window).height());
         $('#wxgjfooter').css('position','fixed').css('bottom','0px');
         }
         });
         **/
    </script>
</body>

</html>