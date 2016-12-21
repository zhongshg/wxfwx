<%-- 
    Document   : jsapi
    Created on : 2015-1-14, 16:00:27
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>万巷坊</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
    </head>
    <link href="/Application/Tpl/App/shop/Public/Static/css/foods.css?t=555" rel="stylesheet"
          type="text/css">
    <script type="text/javascript" src="/Application/Tpl/App/shop/Public/Static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/Application/Tpl/App/shop/Public/Static/js/wemall.js?14115"></script>
    <script type="text/javascript" src="/Application/Tpl/App/shop/Public/Static/js/alert.js"></script>

    <style>
        .bg_color{background-color: #fff }
        .submit{background-color: #fff }
        .submit:active {background-color: #fff}
        .menu_topbar {background-color: #fff ;background: -webkit-linear-gradient(top, #fff , #fff);border-bottom: 1px solid #fff;}
        .footermenu ul {border-top: 1px solid #fff ;background-color: #fff;background: -webkit-linear-gradient(top, #fff, #fff);}
        .footermenu ul li a.active {background: -webkit-linear-gradient(top, #fff , #fff);}
        .bg_total{background-color:#FF0000}
    </style>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script>
//新版微信支付
        function onBridgeReady() {
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                "appId": '${jsapimap.appId}', //公众号名称，由商户传入     
                "timeStamp": '${jsapimap.timeStamp}', //时间戳，自1970年以来的秒数     
                "nonceStr": '${jsapimap.nonceStr}', //随机串     
                "package": '${jsapimap.packages}',
                "signType": '${jsapimap.signType}', //微信签名方式:     
                "paySign": '${jsapimap.paySign}' //微信签名 
            },
            function(res) {
                alert("res.err_msg " + res.err_msg);
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    alert("支付成功，请等待发货!");
                    window.setTimeout(function() {
                        window.location.replace("/shop3/index.jsp");
                        //跳转支付成功页面！
                    }, 5000);

                }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
                else {
                    alert("系统错误，请稍后再试!");
                    window.setTimeout(function() {
                        window.location.replace("/shop3/index.jsp");
                    }, 3000);

                }
            }
            );
        }
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    </script>
</html>
