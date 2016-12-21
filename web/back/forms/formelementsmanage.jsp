<%-- 
    Document   : users
    Created on : 2014-7-9, 9:01:38
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>表单元素管理</title>
        <!--                       CSS                       -->
        <!-- Reset Stylesheet -->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/public.js"></script>
        <jsp:include page="../../public/css.jsp"></jsp:include>
            <script type="text/javascript">
                function test() {
                    if ("" == document.getElementById("name").value) {
                        return false;
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
                        <li><a class="shortcut-button" href="#"><span> <img src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png" alt="icon" /><br />
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
                        <h3>表单元素管理</h3>
                        <ul class="content-box-tabs">
                            <!--<li><a href="#tab1">Table</a></li>-->
                            <!-- href must be unique and match the id of target div -->
                            <li><a href="#tab2" class="default-tab">Forms</a></li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <div class="tab-content default-tab" id="tab2">
                            <form action="${pageContext.servletContext.contextPath}/servlet/FormelementsServlet?method=manage" id="form1" method="post" onsubmit="return test();">
                                <input type="hidden" id="id" name="id" value="${formelements.id}"/>
                                <input type="hidden" name="page" value="${param.page}"/>
                                <input type="hidden" id="keyss" name="keyss" value="${keyss}"/>
                                <fieldset>
                                    <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                                    <p>
                                        <label>名称</label>
                                        <input class="text-input large-input" type="text" id="name" name="name" value="${formelements.name}" />
                                        <span id="namespan" class="input-notification error png_bg"></span>
                                    </p>
                                    <p>
                                        <label>类型</label>
                                        <select name="type" id="type" class="large-input">
                                            <option value="text" ${"text"==formelements.type?"selected='selected'":""}>text(文本框)</option>
                                            <option value="select" ${"select"==formelements.type?"selected='selected'":""}>select(下拉框)</option>
                                            <option value="radio" ${"radio"==formelements.type?"selected='selected'":""}>radio(单选框)</option>
                                            <option value="checkbox" ${"checkbox"==formelements.type?"selected='selected'":""}>checkbox(复选框)</option>
                                            <option value="textarea" ${"textarea"==formelements.type?"selected='selected'":""}>textarea(文本区域)</option>
                                        </select>
                                    </p>
                                    <p>
                                        <label>实际值,显示值(英文逗号,多个用空格隔开)</label>
                                        <input class="text-input large-input" type="text" id="evaluess" name="evaluess" value="${formelements.evaluess}" />
                                    </p>
                                    <p>
                                        <label>提示值</label>
                                        <input class="text-input large-input" type="text" id="eplaceholder" name="eplaceholder" value="${formelements.eplaceholder}" />
                                    </p>
                                    <p>
                                        <label>是否可以为空</label>
                                        <input type="radio" name="isempty" value="0" checked="checked"/>
                                        是&nbsp;
                                        <input type="radio" name="isempty" value="1" ${"1"==formelements.isempty?"checked='checked'":""}/>
                                        否 
                                    </p>
                                    <p>
                                        <label>是否启用正则</label>
                                        <input type="radio" name="isregular" value="0" checked="checked"/>
                                        否&nbsp;
                                        <input type="radio" name="isregular" value="1" ${"1"==formelements.isregular?"checked='checked'":""}/>
                                        是 </p>
                                    <p>
                                        <label>正则表达式(不启用可忽略)</label>
                                        <input class="text-input large-input" type="text" id="regular" name="regular" value="${formelements.regular}" />
                                    </p>
                                    <p>
                                        <label>违反正则提示语(不启用可忽略)</label>
                                        <input class="text-input large-input" type="text" id="regulartext" name="regulartext" value="${formelements.regulartext}" />
                                    </p>
                                    <p>
                                        <label>源码(注：id与name不可更改)</label>
                                        <textarea class="text-input" id="valuess" name="valuess" cols="79" rows="10" >${formelements.valuess}</textarea>
                                    </p>
                                    <p>
                                        <label>预览</label>
                                        <div id="prediv"/>
                                    </p>
                                </fieldset>
                                <div class="clear"></div>
                                <p id="submit">
                                    <input class="button" id="testbutton" type="submit" value="提交" />
                                </p>
                                <!-- End .clear -->
                            </form>
                        </div>
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
        <script type="text/javascript">
                $(document).ready(function() {
                    change();
                    $("#type").change(change);
                    $("#valuess").blur(function() {
                        $("#prediv").html($(this).val());
                    });
                    $("#evaluess").blur(change);
                    $("#eplaceholder").blur(change);
                });
                function change() {
                    var keyss = $("#keyss").val();
                    var sign = ",";
                    var type = $("#type").val();
                    var valuess = "";
                    var eplaceholder = $("#eplaceholder").val();
                    var evaluess = $("#evaluess").val();
                    var evaluessarray = evaluess.split(" ");
                    if ("text" == type) {
                        valuess = "";
                        for (var i = 0; i < evaluessarray.length; i++) {
                            valuess += "<input class='text-input fore-input' type='text' id='" + keyss + "' name='" + keyss + "' value='" + evaluessarray[i].split(sign)[0] + "' placeholder='" + eplaceholder + "'/>";
                            break;
                        }
//                            valuess = "<input class='text-input fore-input' type='text' id='" + keyss + "' name='" + keyss + "' value='值' placeholder='默认提示语'/>";
                    } else if ("select" == type) {
                        valuess = "<select name='" + keyss + "' id='" + keyss + "' class='text-input fore-input'><option value=''>请选择</option>";
                        for (var i = 0; i < evaluessarray.length; i++) {
                            valuess += "<option value='" + evaluessarray[i].split(sign)[0] + "'>" + evaluessarray[i].split(sign)[1] + "</option>";
                        }
                        valuess += "</select>";
//                            valuess = "<select name='" + keyss + "' id='" + keyss + "' class='text-input fore-input'><option value='值1'>选项名称1</option><option value='值2'>选项名称2</option><option value='值3'>选项名称3</option></select>";
                    } else if ("radio" == type) {
                        valuess = "";
                        for (var i = 0; i < evaluessarray.length; i++) {
                            valuess += "<input type='radio' name='" + keyss + "' value='" + evaluessarray[i].split(sign)[0] + "'/>" + evaluessarray[i].split(sign)[1] + "&nbsp;";
                        }
//                            valuess = "<input type='radio' name='" + keyss + "' value='值1' checked='checked'/>单选名称1&nbsp;<input type='radio' name='" + keyss + "' value='值2' checked='checked'/>单选名称2&nbsp;<input type='radio' name='" + keyss + "' value='值3' checked='checked'/>单选名称3";
                    } else if ("checkbox" == type) {
                        valuess = "";
                        for (var i = 0; i < evaluessarray.length; i++) {
                            valuess += "<input type='checkbox' name='" + keyss + "' value='" + evaluessarray[i].split(sign)[0] + "'/>" + evaluessarray[i].split(sign)[1] + "&nbsp;";
                        }
//                            valuess = "<input type='checkbox' name='" + keyss + "' value='值1'/>多选名称1&nbsp;<input type='checkbox' name='" + keyss + "' value='值2'/>多选名称2&nbsp;<input type='checkbox' name='" + keyss + "' value='值3'/>多选名称3&nbsp;";
                    } else if ("textarea" == type) {
                        valuess = "";
                        for (var i = 0; i < evaluessarray.length; i++) {
                            valuess += "<textarea class='text-input fore-input' id='" + keyss + "' name='" + keyss + "' placeholder='" + eplaceholder + "'>" + evaluessarray[i].split(sign)[0] + "</textarea>";
                            break;
                        }
//                            valuess = "<textarea class='text-input fore-input' id='" + keyss + "' name='" + keyss + "' placeholder='默认提示语'>默认值</textarea>";
                    }
                    $("#valuess").val(valuess);
                    $("#prediv").html(valuess);
                }
        </script>
    </body>
    <!-- Download From www.exet.tk-->
</html>