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
    <c:if test="${null!=(null!=media_id?media_id:thumb_media_id)&&null!=type}" var="flag">
        <script>
//            parent.document.getElementById("result").innerHTML = "<img src='<%=request.getScheme() + "://" + request.getServerName()%>${pageContext.servletContext.contextPath}${requestScope.img}' width='200' height='200'/>";
            parent.document.getElementById("mediaid").value = "${null!=media_id?media_id:thumb_media_id},${now}";
            parent.document.getElementById("type").value = "${type}";
        </script>
    </c:if>
    <c:if test="${'-1'==errcode}">
        <script>
            alert("系统繁忙，请稍后再试！");
        </script>
    </c:if>
    <c:if test="${'40004'==errcode||'40005'==errcode}">
        <script>
            alert("媒体类型错误！");
        </script>
    </c:if>
    <c:if test="${'40006'==errcode||'40007'==errcode}">
        <script>
            alert("上传媒体太大！");
        </script>
    </c:if>
    <body>
        <form action="${pageContext.servletContext.contextPath}/servlet/PublicServlet?method=uploadmaterial" method="post" enctype="multipart/form-data">
            文件类型：<select name="type">
                <option value="image" ${"image"==type||"image"==param.type?"selected='selected'":""}>图片(JPG/<1mb)</option>
                <option value="voice" ${"voice"==type||"voice"==param.type?"selected='selected'":""}>语音(AMR\MP3/<2mb\60s)</option>
                <option value="video" ${"video"==type||"video"==param.type?"selected='selected'":""}>视频(MP4/<10mb)</option>
                <option value="thumb" ${"thumb"==type||"thumb"==param.type?"selected='selected'":""}>缩略图(JPG/<64kb)</option>
            </select><br/>
            文件:<input type="file" name="media"/><input type="submit" value="上传"/>
        </form>
    </body>
</html>
