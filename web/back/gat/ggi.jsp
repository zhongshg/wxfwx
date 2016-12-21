<%-- 
    Document   : nim
    Created on : 2014-7-22, 15:40:05
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
                            $("input[name=itemsmckb]:checked").each(function() {
                                ids += "," + $(this).val();
                            });
                        }
                    }
                    $("#ids").val(ids);
                    var form1 = document.getElementById("form1");
                    form1.action = "${pageContext.servletContext.contextPath}/servlet/GatItemsServlet?method=delAll&sid=${gat.sid}";
                    form1.submit();
                });
            });
        </script>
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
                        <li><a class="shortcut-button" href="${pageContext.servletContext.contextPath}/servlet/GatItemsServlet?method=initManage&id=0&sid=${gat.sid}"><span> 
                                <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
                                添加明细 </span></a></li>
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
                        <h3>设置明细</h3>
                        <ul class="content-box-tabs">
                            <li><a href="#tab1" class="default-tab">Table</a></li>
                            <!-- href must be unique and match the id of target div -->
                            <!--<li><a href="#tab2">Forms</a></li>-->
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <form id="form1" method="post" action="${pageContext.servletContext.contextPath}/servlet/GatServlet?method=change">
                            <input type="hidden" id="ids" name="ids"/>
                            <input type="hidden" name="page" value="${param.page}"/>
                            <div class="tab-content default-tab" id="tab1">
                                <!-- This is the target div. id must match the href of this div's tab -->
<!--                                <div class="notification attention png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                </div>-->
                                <table>
                                    <thead>
                                        <tr>
                                            <th width="10%">
                                                <input class="check-all" type="checkbox" />
                                            </th>
                                            <th width="20%">名称</th>
                                            <th width="20%">图片</th>
                                            <th width="20%">排序</th>
                                        </tr>
                                    </thead>
                                    <!--                                <tfoot>
                                                                        <tr>
                                                                            <td colspan="15">
                                                                                <div class="bulk-actions align-left">
                                                                                    <select id="dropdown">
                                                                                        <option value="delall">批量删除</option>
                                                                                    </select>
                                                                                    <a class="button" id="exeall" href="#">执行</a> </div>
                                                                                <div class="pagination"> 
                                                                                    <a href="#" title="First Page">&laquo; First</a><a href="#" title="Previous Page">&laquo; Previous</a> <a href="#" class="number" title="1">1</a> <a href="#" class="number" title="2">2</a> <a href="#" class="number current" title="3">3</a> <a href="#" class="number" title="4">4</a> <a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a> 
                                                                                    {pu.style}
                                                                                </div>
                                                                                 End .pagination 
                                                                                <div class="clear"></div>
                                                                            </td>
                                                                        </tr>
                                                                    </tfoot>-->
                                    <tbody>
                                        <input type="hidden" name="id" value="${gat.id}"/>
                                        <input type="hidden" name="sid" value="${gat.sid}"/>
                                        <c:forEach items="${gatItemsList}" var="gatitems">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="gatitemsckb" value="${gatitems.id}" ${"1"==gatitems.selects? "checked='checked'":""}/>
                                                </td>
                                                <td>${gatitems.name}</td>
                                                <td><img src="${pageContext.servletContext.contextPath}${gatitems.img}" width="60px" height="40px"/></td>
                                                <td>
                                                    <a href="${pageContext.servletContext.contextPath}/servlet/GatItemsServlet?method=order&gatid=${gat.id}&id=${gatitems.id}&dir=0&sid=${gat.sid}&page=${param.page}" title="Edit"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/up.gif" alt="Edit" />上移</a>
                                                    <a href="${pageContext.servletContext.contextPath}/servlet/GatItemsServlet?method=order&gatid=${gat.id}&id=${gatitems.id}&dir=1&sid=${gat.sid}&page=${param.page}" title="Delete"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/down.gif" alt="Delete" />下移</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <p>
                                    <input class="button" type="submit" value="确认" />
                                </p>
                            </div>
                        </form>
                        <!-- End #tab1 -->
                        <!-- End #tab2 -->
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