<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="wap.wx.dao.StoreTypeDAO"%>
<%@include file="public.jsp" %>
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
<title>我要评分</title>
<meta content="" name="keywords">
<meta content="" name="description">
<meta name="sogou_site_verification" content="G7nmLR75yc">
<meta name="baidu-tc-cerfication" content="">
<meta name="360-site-verification" content="">
<link rel="stylesheet" type="text/css" href="style/css.css">
<script type="text/javascript" src="js/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/js/comment.js"></script>
<style type="text/css">
</style>

</head>

<body style="padding-bottom: initial;" class="hPC">
	<header class="u-header">
		<div class="l">
			<a href="javascript:void(0)" class="J_backToPrev"><span
				class="u-icon"><img src="images/kj_15.png" width="15" /></span></a>
		</div>
		<div class="u-hd-tit">
			<span>我要评论</span>
		</div>
		<div class="r">
			<a href=""><span class="u-icon"><img
					src="images/kj_16.png" / width="25"></span></a>
		</div>
	</header>
	<div class="space10"></div>
	<div class="quiz">
		<h3></h3>
		<div class="quiz_content">
			<form action="" id="" method="post">
				<div class="goods-comm">
					<div class="goods-comm-stars">
						<span class="star_l">满意度：</span>
						<div id="rate-comm-1" class="rate-comm">
							<ul class="rater-star"
								style="height: 24px; width: 120px; background-image: url(images/kj_14.png);">
								<li class="rater-star-item-tips"
									style="width: 120px; z-index: 7;"></li>
								<li class="rater-star-item-current"
									style="height: 24px; width: 0px; z-index: 6; background-image: url(images/kj_14.png);"></li>
								<li class="rater-star-item"
									style="height: 24px; width: 24px; z-index: 5; background: url(images/kj_14.png) left;">
									<div class="popinfo" style="left: 0px;">
										<div class="info-box">
											1分&nbsp;很不满意
											<div>商品样式和质量都非常差，太令人失望了！</div>
										</div>
									</div>
								</li>
								<li class="rater-star-item"
									style="height: 24px; width: 48px; z-index: 4; background: url(../images/kj_14.png);">
									<div class="popinfo" style="left: 24px;">
										<div class="info-box">
											2分&nbsp;不满意
											<div>商品样式和质量不好，不能满足要求。</div>
										</div>
									</div>
								</li>
								<li class="rater-star-item"
									style="height: 24px; width: 72px; z-index: 3; background-image: url(images/kj_14.png);">
									<div class="popinfo" style="left: 48px;">
										<div class="info-box">
											3分&nbsp;一般
											<div>商品样式和质量感觉一般。</div>
										</div>
									</div>
								</li>
								<li class="rater-star-item"
									style="height: 24px; width: 96px; z-index: 2; background-image: url(images/kj_14.png);">
									<div class="popinfo" style="left: 72px;">
										<div class="info-box">
											4分&nbsp;满意
											<div>商品样式和质量都比较满意，符合我的期望。</div>
										</div>
									</div>
								</li>
								<li class="rater-star-item"
									style="height: 24px; width: 120px; z-index: 1; background-image: url(images/kj_14.png);">
									<div class="popinfo" style="left: 96px;">
										<div class="info-box">
											5分&nbsp;非常满意
											<div>我很喜欢！商品样式和质量都很满意，太棒了！</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="rater-star-result"></div>
					</div>
				</div>
				<div class="l_text">
					<label class="m_flo">内 容：</label>
					<textarea name="" id="" class="text"></textarea>
					<span class="tr">字数限制为5-200个</span>
				</div>
				<button class="btm" type="submit">发表评论</button>
			</form>
		</div>
		<!--quiz_content end-->
	</div>
</body>
</html>