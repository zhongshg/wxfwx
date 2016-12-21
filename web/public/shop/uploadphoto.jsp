<%-- 
    Document   : upload
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
        <form action="${pageContext.servletContext.contextPath}/servlet/PublicServlet?method=uploadphoto" method="post" enctype="multipart/form-data">
            <input type="hidden" name="oldimg" value="${param.oldimg }"/>
            <input type="hidden" name="img" value="${requestScope.img }"/>
            <input type="file" name="file"/>
            <input type="submit" value="上传"/>
        </form>
        <c:if test="${!empty requestScope.img }">
            <script>
                parent.document.getElementById("Photo").value = "${requestScope.img}";
            </script>
        </c:if>
    </body>
</html>
