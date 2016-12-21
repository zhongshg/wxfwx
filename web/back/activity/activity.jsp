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
                            $("input[name=activityckb]:checked").each(function() {
                                ids += "," + $(this).val();
                            });
                        }
                    }
                    $("#ids").val(ids);
                    var form1 = document.getElementById("form1");
                    form1.action = "${pageContext.servletContext.contextPath}/servlet/ActivityServlet?method=delAll";
                    form1.submit();
                });
            });
        </script>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                function del(id) {
                    if (confirm("不可恢复,确认？")) {
                        window.location.href = "${pageContext.servletContext.contextPath}/servlet/ActivityServlet?method=delete&id=" + id;
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
                    <li><a class="shortcut-button" href="${pageContext.servletContext.contextPath}/servlet/ActivityServlet?method=initManage&id=0"><span> 
                                <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
                                添加 </span></a></li>
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
                        <h3>应用管理</h3>
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
                                        <th>名称</th>
                                        <th>关键字</th>
                                        <!--<th>图片</th>-->
                                        <th>预计参与人数</th>
                                        <th>个人参与次数</th>
                                        <th>个人中奖次数</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>状态</th>
                                        <th width="20%">备注</th>
                                        <th>中奖提示语</th>
                                        <th>未中奖提示语</th>
                                        <th>不能参加提示语</th>
                                        <th>结束提示语</th>
                                        <th>活动类型</th>
                                        <!--                                                                                <th>概率变更参与次数</th>
                                                                                                                        <th>变更后概率</th>-->
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="15">
                                            <div class="bulk-actions align-left">
                                                <select id="dropdown">
                                                    <option value="delall">批量删除</option>
                                                </select>
                                                <a class="button" id="exeall" href="#">执行</a> </div>
                                            <div class="pagination"> 
                                                <!--<a href="#" title="First Page">&laquo; First</a><a href="#" title="Previous Page">&laquo; Previous</a> <a href="#" class="number" title="1">1</a> <a href="#" class="number" title="2">2</a> <a href="#" class="number current" title="3">3</a> <a href="#" class="number" title="4">4</a> <a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a>--> 
                                                ${pu.style}
                                            </div>
                                            <!-- End .pagination -->
                                            <div class="clear"></div>
                                        </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach items="${pu.list}" var="activity">
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="activityckb" value="${activity.id}"/>
                                            </td>
                                            <td>${activity.name}</td>
                                            <td>${activity.activitykey}</td>
                                            <!--<td><img src="${pageContext.servletContext.contextPath}${activity.img}" width="60px" height="40px"/></td>-->
                                            <td>${activity.counts}</td>
                                            <td>${activity.taketimes}</td>
                                            <td>${activity.pointtimes}</td>
                                            <td>${activity.starttime}</td>
                                            <td>${activity.endtime}</td>
                                            <td>${"0"==activity.state?"正常":"结束"}</td>
                                            <td>${activity.remark}</td>
                                            <td>${activity.successpoint}</td>
                                            <td>${activity.nopoint}</td>
                                            <td>${activity.overpoint}</td>
                                            <td>${activity.endpoint}</td>
                                            <td>${"1"==activity.sid?"转盘抽奖":("2"==activity.sid?"刮刮卡":("3"==activity.sid?"砸金蛋":"未知"))}</td>
<!--                                            <td>${activity.changetimes}</td>
                                            <td>${activity.changepercent}</td>-->
                                            <td>
                                                <!-- Icons -->
                                                <a href="${pageContext.servletContext.contextPath}/servlet/ActivityServlet?method=initManage&id=${activity.id}&page=${pu.page}" title="Edit"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil.png" alt="Edit" />编辑</a> 
                                                <a href="javascript:del('${activity.id}');" title="Delete"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross.png" alt="Delete" />删除</a> 
                                                <a href="${pageContext.servletContext.contextPath}/servlet/OpenprizeServlet?method=getList&sid=${activity.id}" title="Edit Meta"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" />查看中奖</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- End #tab1 -->
                    </div>
                    <!-- End .content-box-content -->
                </div>
                <!-- End .content-box -->

                <div class="clear"></div>
                <!-- Start Notifications -->
<!--                <div class="notification attention png_bg"> <a href="#" class="close"><img src="${pageContext.servletContext.contextPath}/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                    <div></div>
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