<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>图文页面管理</title>
<link rel="stylesheet" href="../../resources/css/reset.css"
	type="text/css" media="screen" />
<link rel="stylesheet" href="../../resources/css/style.css"
	type="text/css" media="screen" />
<link rel="stylesheet" href="../../resources/css/invalid.css"
	type="text/css" media="screen" />
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<!-- Page Head -->
			<p id="page-intro">What would you like to do?</p>
			<ul class="shortcut-buttons-set">
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png"
							alt="icon" /><br /> &nbsp;
					</span></a></li>
			</ul>
			<!-- End .shortcut-buttons-set -->
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>图文页面管理</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab2" class="default-tab">Forms</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab2">
						<form
							action="${pageContext.servletContext.contextPath}/servlet/StoreTypeServlet?method=manage"
							id="form1" method="post" onsubmit="return test();">
							<input type="hidden" id="id" name="id" value="${storetype.id}" /> <input
								type="hidden" id="sid" name="sid" value="${storetype.sid}" /> <input
								type="hidden" id="img" name="img" value="${storetype.img}" />
							<%--图片路径--%>
							<input type="hidden" name="page" value="${param.page}" />
							<fieldset>
								<p>
									<label>店铺编号</label> <input class="text-input large-input"
										type="text" id="tcode" name="tcode" value="${storetype.tcode}" />
								</p>
								<p>
									<label>店铺名称</label> <input class="text-input large-input"
										type="text" id="tname" name="tname" value="${storetype.tname}" />
								</p>
								<%-- 			<p>
									<label>图片上传</label>
									<iframe
										src="${pageContext.servletContext.contextPath}/public/upload.jsp?oldimg=${store.img}"
										class="text-input medium-input" width="300" height="26"
										frameborder="0" marginheight="0" marginwidth="0"
										scrolling="no"></iframe>
								</p>
								<p>
									<label>店铺封面图</label>
								<div id="result" name="result">
									<c:if test="${!empty store.img}">
										<img
											src='${pageContext.servletContext.contextPath}${store.img}'
											width='200' height='200' />
									</c:if>
									<c:if test="${empty store.img}">
                                                请上传图片
                                            </c:if>
								</div> --%>
								<div class=" clear "></div>
								<p id=" submit ">
									<input class="button" id="testbutton" type="submit" value="提交 " />
								</p>
							</fieldset>
							<div class=" clear "></div>
						</form>
					</div>
				</div>
			</div>
			<div class=" clear "></div>

			<div id="footer">
				<small> 技术支持：<a href="http://www.tl-kj.com/">天澜科技</a> | <a
					href="#">返回头部</a>

				</small>
			</div>

		</div>
	</div>
</body>
</html>