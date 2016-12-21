<%-- 
    Document   : menu
    Created on : 2014-7-23, 9:07:32
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title></title>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#addimg").click(function() {
                    $("#messages1").hide();
                    $("#messages2").show();
                    $("#error").html("<font color='red'>一级菜单不能多于3个，二级菜单不能多于5个</font>");
                });
            });
        </script>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                function del(parentid, id) {
                    if (confirm("不可恢复,确认？")) {
                        var form2 = document.getElementById("form2");
                        document.getElementById("id").value = id;
                        document.getElementById("parentid").value = parentid;
                        form2.action = "${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=delete";
                        form2.submit();
                    }
                }
                function testuse() {
                    if (confirm("应用微信菜单将会覆盖当前数据，确认？")) {
                        window.location.href = "${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=getWxList&isr=1";
                    }
                }
                function testissue() {
                    if (confirm("发布微信菜单将会覆盖微信数据，确认？")) {
                        window.location.href = "${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=issue";
                    }
                }
        </script>
    </head>
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
                    <c:if test="${'1'==isr}">
                        <li>
                            <c:if test="${3<=fn:length(menuMap) }">
                                <a class="shortcut-button" href="#" id="addimg"><span> 
                                        <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
                                        添加一级菜单 </span>
                                </a>
                            </c:if>
                            <c:if test="${3>fn:length(menuMap) }">
                                <a class="shortcut-button" href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=initManage&parentid=0&id=0"><span> 
                                        <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
                                        添加一级菜单 </span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <li><a class="shortcut-button" href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=getWxList&isr=0"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
                                查看微信菜单 </span></a></li>
                    <li><a class="shortcut-button" href="javascript:testuse();"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/image_add_48.png" alt="icon" /><br />
                                下载微信菜单 </span></a></li>
                    <li><a class="shortcut-button" href="javascript:testissue();"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/clock_48.png" alt="icon" /><br />
                                发布微信菜单 </span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/comment_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                </ul>
                <!-- End .shortcut-buttons-set -->
                <div class="clear"></div>
                <!-- End .clear -->
                <div class="content-box">
                    <!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>自定义菜单管理</h3>
                        <ul class="content-box-tabs">
                            <li><a href="#tab1" class="default-tab">Table</a></li>
                            <!-- href must be unique and match the id of target div -->
                            <!--<li><a href="#tab2">Forms</a></li>-->
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <form id="form1" method="post">
                            <input type="hidden" id="ids" name="ids"/>
                        </form>
                        <form id="form2" method="post">
                            <input type="hidden" id="id" name="id"/>
                            <input type="hidden" id="parentid" name="parentid"/>
                            <input type='submit' style="display: none;"/>
                        </form>
                        <div class="tab-content default-tab" id="tab1">
                            <!-- This is the target div. id must match the href of this div's tab -->
<!--                            <div class="notification attention png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                            </div>-->
                            <!--//id,menukey,name,parentid,menutype,url,sendid,sendtype-->
                            <table>
                                <thead>
                                    <tr>
                                        <th width="10%">名称</th>
                                        <th width="10%">事件</th>
                                        <th width="22%">链接路径</th>
                                        <th width="16%">发送信息</th>
                                        <th width="6%">类型</th>
                                        <th width="5%">排序</th>
                                        <th width="5%">点击</th>
                                        <th width="17%">操作</th>
                                    </tr>
                                </thead>
                            </table>
                            <c:forEach items="${menuMap}" var="menus">
                                <table>
                                    <thead>
                                        <tr>
                                            <!--                                            <th width="0%">
                                                                                            <input class="check-all" type="checkbox" />
                                                                                        </th>-->
                                            <td width="10%"><b>${menus.key.name}</b></td>
                                                    <c:if test="${0<fn:length(menus.value) }">
                                                <td width="10%"><b>设置子菜单</b></td>
                                                <td width="27%">&nbsp;</td>
                                                <td width="17%">&nbsp;</td>
                                                <td width="6%">&nbsp;</td>
                                                <td width="6%"><b>${menus.key.orders}</b></td>
                                                <td width="5%">&nbsp;</td>
                                            </c:if>
                                            <c:if test="${0==fn:length(menus.value) }">
                                                <td width="10%"><b>${"0"==menus.key.menutype?"发送信息":"跳转网页"}</b></td>
                                                <td width="27%"><b>${fn:substring(menus.key.url, 0, 44)}</b></td>
                                                <td width="17%"><b>${menus.key.sendname}</b></td>
                                                <td width="6%"><b>${"0"==menus.key.sendtype?"":menus.key.sendtype}</b></td>
                                                <td width="6%"><b>${menus.key.orders}</b></td>
                                                <td width="5%"><b>${menus.key.viewcounts}</b></td>
                                                    </c:if>
                                                    <c:if test="${'1'==isr}" var="checkSub">
                                                <td width="23%">
                                                    <a href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=initManage&id=${menus.key.id}" title="Edit"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil.png" alt="Edit" /><b>编辑</b></a>
                                                    <a href="javascript:del('0','${menus.key.id}')" title="Delete"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross.png" alt="Delete" /><b>删除</b></a> 
                                                    <c:if test="${5>fn:length(menus.value) }">
                                                        <a href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=initManage&parentid=${menus.key.id}&id=0" title="Edit Meta"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" /><b>添加子菜单</b></a>
                                                    </c:if>
                                                </td>
                                            </c:if>
                                            <c:if test="${!checkSub}">
                                                <td width="15%">&nbsp;</td>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${menus.value}" var="submenus">
                                            <tr>
                                                <!--                                                <td>
                                                                                                    <input type="checkbox" name="powersckb" value="${powers.id}"/>
                                                                                                </td>-->
                                                <td>&nbsp;>&nbsp;&nbsp;${submenus.name}</td>
                                                <td>${"0"==submenus.menutype?"发送信息":"跳转网页"}</td>
                                                <td>${fn:substring(submenus.url, 0, 44)}</td>
                                                <td>${submenus.sendname}</td>
                                                <td>${"0"==submenus.sendtype?"":submenus.sendtype}</td>
                                                <td>&nbsp;</td>
                                                <td width="5%"><b>${submenus.viewcounts}</b></td>
                                                <td>
                                                    <c:if test="${'1'==isr}" var="t">
                                                        <a href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=initManage&parentid=${menus.key.id}&id=${submenus.id}" title="Edit"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil.png" alt="Edit" />编辑</a>
                                                        <a href="javascript:del('${menus.key.id}','${submenus.id}')" title="Delete"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross.png" alt="Delete" />删除</a>
                                                        <a href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=order&parentid=${menus.key.id}&id=${submenus.id}&dir=0" title="Edit"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/up.gif" alt="Edit" />上移</a>
                                                        <a href="${pageContext.servletContext.contextPath}/servlet/MenuServlet?method=order&parentid=${menus.key.id}&id=${submenus.id}&dir=1" title="Delete"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/down.gif" alt="Delete" />下移</a>
                                                    </c:if>
                                                    <c:if test="${!t}">
                                                        &nbsp;
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:forEach>
                        </div>
                        <!-- End #tab1 -->

                        <!-- End #tab2 -->
                    </div>
                    <!-- End .content-box-content -->
                </div>
                <!-- End .content-box -->

                <div class="clear"></div>
                <!-- Start Notifications -->
                <c:if test="${null!=message}">
                    <div id="messages1" class="notification success png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                        <div>${message}</div>
                    </div>
                </c:if>
                <div id="messages2" class="notification attention png_bg" style="display: none;"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                    <div id='error'></div>
                </div>
                <!-- End Notifications -->
                <%@include file="../../public/foot.jsp" %>
                <!-- End #footer -->
            </div>
            <!-- End #main-content -->
        </div>
    </body>
    <!-- Download From www.exet.tk-->
</html>