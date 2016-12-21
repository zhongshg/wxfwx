<%-- 
    Document   : uploadmaterial
    Created on : 2014-7-9, 14:31:48
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        图文信息：<select id="newsm" name="newsm">
            <option value="0">请选择上传图文信息</option>
            <c:forEach items="${newsmList}" var="newsm">
                <option value="${newsm.id}">${newsm.name}</option>
            </c:forEach>
        </select><br/>
        <input id="button" type="button" value="上传"/>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#button").click(function() {
                    var newsm = $("#newsm").val();
                    if ("0" == newsm) {
                        alert("请选择图文信息上传！");
                        return false;
                    } else {
                        $.post("${pageContext.servletContext.contextPath}/servlet/PerMaterialServlet?method=uploadmassmaterial", {"newsm": newsm}, function(result) {
                            result = eval("(" + result + ")");
                            if ("invalid" == result.msg) {
                                alert("缩略图无效！");
                                return false;
                            } else if ("upfalse" == result.msg) {
                                alert("信息上传失败，请重试！");
                                return false;
                            } else if ("success" == result.msg) {
                                alert("上传成功！");
                                parent.document.getElementById("mediaid").value = result.mediaid;
                                parent.document.getElementById("type").value = result.type;
                            }
                        });
                    }
                });
            });
        </script>
    </body>
</html>
