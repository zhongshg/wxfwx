<%-- 
    Document   : index
    Created on : 2014-7-7, 9:30:55
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/css.css"/>
        <link href="${pageContext.servletContext.contextPath}/img/logourl.png" rel="Shortcut Icon">
        <title>欢迎光临微信公众平台微营销后台管理系统</title>
        <!--Adobe Edge Runtime-->
        <script type="text/javascript" charset="utf-8" src="${pageContext.servletContext.contextPath}/login_edgePreload.js"></script>
        <style>
            .edgeLoad-EDGE-30622614 { visibility:hidden; }
        </style>
        <!--Adobe Edge Runtime End-->
        <script type="text/javascript">
            function forget() {
                window.location.href = "${pageContext.servletContext.contextPath}/safes/identity.jsp?ecstr=a3g34s0_2rd3dsw";
            }
            function formsubmit() {
                var username = document.getElementById("username").value;
                var passwords = document.getElementById("passwords").value;
                if (username == "") {
                    document.getElementById("samp1").innerHTML = "*";
                } else {
                    document.getElementById("samp1").innerHTML = "";
                }
                if (passwords == "") {
                    document.getElementById("samp2").innerHTML = "*";
                } else {
                    document.getElementById("samp2").innerHTML = "";
                }
                if (username != "" && passwords != "") {
                    var form = document.getElementById("formlogin");
                    form.action = "${pageContext.servletContext.contextPath}/UsersServlet?method=login";
                    form.submit();
                }
            }
            function back() {
                var result = document.getElementById("result").value;
                var back = document.getElementById("back").value;
                if ("1" == back) {
                    parent.location.href = "${pageContext.servletContext.contextPath}/index.jsp?message=1";
                } else if ("2" == back) {
                    parent.location.href = "${pageContext.servletContext.contextPath}/index.jsp?message=2";
                }
            }
            window.onload = back;
        </script>
    </head>
    <body scroll="no" style="margin:0;padding:0;overflow-x:hidden;overflow-y:hidden" >
        <div id="Stage" class="EDGE-30622614">
        </div>
        <div class="logo">
            <img src="${pageContext.servletContext.contextPath}/images/logo.png" width="303" height="98"> </div>
        <div class="denglu">
            <form id="formlogin" name="formlogin" method="post" action="#">
                <input type="hidden" id="result" value="${requestScope.result}"/>
                <input type="hidden" id="back" value="${back}"/>
                <table width="526" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="153" height="90">&nbsp;</td>
                        <td width="297">&nbsp;</td>
                        <td width="76">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="right"  class="ziti">用户名：</td>
                        <td align="center"  class="wbk"><input id="username" name="username" type="text" class="text"></td>
                        <td><samp class="hongse" id="samp1"></samp></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="ziti" align="right">密码：</td>
                        <td align="center" class="wbk"><input id="passwords" name="passwords" type="password" class="text"></td>
                        <td><span class="hongse" id="samp2"></span></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><span id="spanmessage" class="mmts">${null!=requestScope.message?requestScope.message:("0"==param.message?"请登录":("1"==param.message?"退出成功":("2"==param.message?"密码重置成功，请登录":("3"==param.message?"用户名或密码错误":param.message))))}</span></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center" valign="middle" class=""><img src="${pageContext.servletContext.contextPath}/images/dlan.png" width="372" height="48" onclick="javascript:formsubmit();"></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><samp class="wjmm" onclick="javascript:forget();">忘记密码？</samp></td>
                    </tr>
                    <tr>
                        <td height="59" colspan="3" align="center" valign="bottom"><a href="http://www.tl-kj.com/" target="_blank" style="text-decoration: none"><span class="jszc">济南天澜科技有限公司微信公众平台微营销后台管理系统</span></a></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>