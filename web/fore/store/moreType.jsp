<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public.jsp"%>
<%
	//String sid = request.getParameter("sid");
	StoreTypeDAO storeTypeDAO = new StoreTypeDAO();
	List<Map<String, String>> storeTypeList = storeTypeDAO.getAllList(sid);
	request.setAttribute("storeTypeList", storeTypeList);
	request.setAttribute("sid", sid);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta name="baidu-site-verification" content="t7oDT96Amk">
<title>分类</title>
<meta content="" name="keywords">
<meta content="" name="description">
<meta name="sogou_site_verification" content="G7nmLR75yc">
<meta name="baidu-tc-cerfication" content="">
<meta name="360-site-verification" content="">
<link rel="stylesheet" type="text/css" href="style/css.css">
</head>

<body class=" hAndroid hPC">
	<header class="u-header">
		<div class="l">
			<a href="javascript:history.go(-1)" class="J_backToPrev"><span
				class="u-icon"><img src="images/kj_15.png" width="15" /></span></a>
		</div>
		<div class="u-hd-tit">
			<span>分类</span>
		</div>
		<div class="r">
			<a href="index.jsp"><span class="u-icon"><img
					src="images/kj_16.png" width="25"></span></a>
		</div>
	</header>
	<ul class="search_category_list">
		<li><c:forEach items="${storeTypeList}" var="storeType">
				<div class="hd">
					<h2>
						<a href="storeList.jsp?tid=${storeType.id }">${storeType.tname }</a>
					</h2>
				</div>
			</c:forEach></li>
	</ul>
</body>
</html>