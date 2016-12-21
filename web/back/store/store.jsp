<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#exeall")
								.click(
										function() {
											var ids = "";
											var dropdown = $("#dropdown").val();
											if ("delall" == dropdown) {
												if (confirm("不可恢复，确认？")) {
													$(
															"input[name=gatckb]:checked")
															.each(
																	function() {
																		ids += ","
																				+ $(
																						this)
																						.val();
																	});
												}
											}
											$("#ids").val(ids);
											var form1 = document
													.getElementById("form1");
											form1.action = "${pageContext.servletContext.contextPath}/servlet/StoreServlet?method=delAll";
											form1.submit();
										});
					});
</script>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<jsp:include page="../../public/css.jsp"></jsp:include>
<script type="text/javascript">
	function del(id, sid) {
		if (confirm("不可恢复,确认？")) {
			window.location.href = "${pageContext.servletContext.contextPath}/servlet/StoreServlet?method=delete&id="
					+ id + "&sid=" + sid;
		}
	}
</script>
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<p id="page-intro"></p>
			<ul class="shortcut-buttons-set">
				<li><a class="shortcut-button"
					href="${pageContext.servletContext.contextPath}/servlet/StoreServlet?method=initManage&id=0&sid=${sid}"><span>
							<img
							src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil_48.png"
							alt="icon" /><br /> 添加
					</span></a></li>
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.servletContext.contextPath}/resources/images/icons/paper_content_pencil_48.png"
							alt="icon" /><br /> &nbsp;
					</span></a></li>
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.servletContext.contextPath}/resources/images/icons/image_add_48.png"
							alt="icon" /><br /> &nbsp;
					</span></a></li>
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.servletContext.contextPath}/resources/images/icons/clock_48.png"
							alt="icon" /><br /> &nbsp;
					</span></a></li>
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.servletContext.contextPath}/resources/images/icons/comment_48.png"
							alt="icon" /><br /> &nbsp;
					</span></a></li>
			</ul>
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>图文页面管理</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">Table</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<form id="form1" method="post">
						<input type="hidden" id="ids" name="ids" /> <input type="hidden"
							name="sid" value="${sid}" />
					</form>
					<div class="tab-content default-tab" id="tab1">
						<table>
							<thead>
								<tr>
									<th><input class="check-all" type="checkbox" /></th>
									<!-- //id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr -->
									<th>店铺编号</th>
									<th>店铺名称</th>
									<th>店铺图片</th>
									<th>店铺好评率</th>
									<th>店铺级别</th>
									<th>店铺类型</th>
									<th>加入时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td colspan="15">
										<div class="bulk-actions align-left">
											<select id="dropdown">
												<option value="delall">批量删除</option>
											</select> <a class="button" id="exeall" href="#">执行</a>
										</div>
										<div class="pagination">
											<!--<a href="#" title="First Page">&laquo; First</a><a href="#" title="Previous Page">&laquo; Previous</a> <a href="#" class="number" title="1">1</a> <a href="#" class="number" title="2">2</a> <a href="#" class="number current" title="3">3</a> <a href="#" class="number" title="4">4</a> <a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a>-->
											${pu.style}
										</div> <!-- End .pagination -->
										<div class="clear"></div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<!-- //id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr -->
								<c:forEach items="${pu.list}" var="store">
									<tr>
										<td><input type="checkbox" name="gatckb"
											value="${store.id}" /></td>
										<td>${store.code}</td>
										<td>${store.name}</td>
										<td><img
											src="${store.img}"
											width="60px" height="40px" /></td>
										<td>${store.favorate}</td>
										<td>${store.level}</td>
										<td>${store.typeid}</td>
										<td>${store.ts}</td>
										<td>
											<!-- Icons --> <a
											href="${pageContext.servletContext.contextPath}/servlet/StoreServlet?method=initManage&id=${store.id}&sid=${sid}&page=${pu.page}"
											title="Edit"><img
												src="${pageContext.servletContext.contextPath}/resources/images/icons/pencil.png"
												alt="Edit" />编辑</a> <a
											href="javascript:del('${store.id}','${sid}');" title="Delete"><img
												src="${pageContext.servletContext.contextPath}/resources/images/icons/cross.png"
												alt="Delete" />删除</a> <%-- <a
											href="${pageContext.servletContext.contextPath}/servlet/StoreServlet?method=initSelect&id=${store.id}&sid=${sid}&page=${pu.page}"
											title="Edit Meta"><img
												src="${pageContext.servletContext.contextPath}/resources/images/icons/hammer_screwdriver.png"
												alt="Edit Meta" />设置明细</a> --%>
										</td>
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
			<!-- End Notifications -->
			<%@include file="../../public/foot.jsp"%>
			<!-- End #footer -->
		</div>
		<!-- End #main-content -->
	</div>
</body>
</html>