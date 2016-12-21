<%-- 
    Document   : subscriber
    Created on : 2014-7-21, 16:45:55
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title></title>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#exeall").click(function() {
                    var ids = "";
                    var dropdown = $("#dropdown").val();
                    if ("delall" == dropdown) {
                        if (confirm("不可恢复，确认？")) {
                            $("input[name=subscriberckb]:checked").each(function() {
                                ids += "," + $(this).val();
                            });
                        }
                    }
                    $("#ids").val(ids);
                    var form1 = document.getElementById("form1");
                    form1.action = "${pageContext.servletContext.contextPath}/servlet/SubscriberServlet?method=delAll";
                    form1.submit();
                });
            });
        </script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/wdp/WdatePicker.js" charset="utf-8" defer="defer"></script>

        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <jsp:include page="../../public/css.jsp"></jsp:include>
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
                        <li><a class="shortcut-button" href="#"><span> 
                                    <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/image_add_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/clock_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                    <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/comment_48.png" alt="icon" /><br />
                                &nbsp;</span></a></li>
                </ul>
                <!-- End .shortcut-buttons-set -->
                <div class="clear"></div>
                <!-- End .clear -->
                <div class="content-box">
                    <!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>用户操作记录</h3>
                        <ul class="content-box-tabs">
                            <li><a href="#tab1" class="default-tab">Table</a></li>
                            <!-- href must be unique and match the id of target div -->
                            <!--<li><a href="#tab2">Forms</a></li>-->
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <form id="form1" method="post" action="${pageContext.servletContext.contextPath}/servlet/SaleMemberServlet?method=subscriberdetaillist">
                            <input type="hidden" id="ids" name="ids"/>
                            openid:<input type="text" id="openid" name="openid"  value="${openid}"/>
                            日期:<input type="text" id="subscriberdate" name="subscriberdate"  value="${subscriberdate}"  onfocus="WdatePicker({skin: 'whyGreen', dateFmt: 'yyyy-MM-dd'})" class="Wdate"/>
                            <input type="submit" value="查询"/>
                        </form>
                        <div class="tab-content default-tab" id="tab1">
                            <!-- This is the target div. id must match the href of this div's tab -->
<!--                            <div class="notification attention png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                            </div>-->
                            <table>
                                <thead>
                                    <tr>
                                        <th>
                                            <input class="check-all" type="checkbox" />
                                        </th>
                                        <th>编号</th>
                                        <th>openid</th>
                                        <th>昵称</th>
                                        <th>推荐人</th>
                                        <th>动作</th>
                                        <th>时间</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="15">
                                            <!--                                                                            <div class="bulk-actions align-left">
                                                                                                                            <select id="dropdown">
                                                                                                                                <option value="delall">批量删除</option>
                                                                                                                            </select>
                                                                                                                            <a class="button" id="exeall" href="#">执行</a> </div>-->
                                            <div class="pagination"> 
                                                <!--<a href="#" title="First Page">&laquo; First</a><a href="#" title="Previous Page">&laquo; Previous</a> <a href="#" class="number" title="1">1</a> <a href="#" class="number" title="2">2</a> <a href="#" class="number current" title="3">3</a> <a href="#" class="number" title="4">4</a> <a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a>--> 
                                                ${pu.nomethodstyle}
                                            </div>
                                            End .pagination 
                                            <div class="clear"></div>
                                        </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach items="${pu.list}" var="subscriber" varStatus="s">
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="subscriberckb" value="${subscriber.id}"/>
                                            </td>
                                            <td>${s.count}</td>
                                            <td>${subscriber.openid}</td>
                                            <td>${subscriber.nickname}</td>
                                            <td>${subscriber.smname}</td>
                                            <td>${"0"==subscriber.issubscriber||"1"==subscriber.issubscriber?"关注":"取消"}</td>
                                            <td>${subscriber.times}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <!-- End .content-box-content -->
                </div>
                <!-- End .content-box -->

                <div class="clear"></div>
                <!-- Start Notifications -->
<!--                <div class="notification attention png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                    <div> Attention notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero. </div>
                </div>-->
                <!-- End Notifications -->
                <%@include file="../../public/foot.jsp" %>
                <!-- End #footer -->
            </div>
            <!-- End #main-content -->
        </div>
    </body>
    <!-- Download From www.exet.tk-->
</html>