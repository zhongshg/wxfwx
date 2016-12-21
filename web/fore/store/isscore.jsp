<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<%@include file="public.jsp"%>
<style>
#rating-star, #tips {
	margin-left: 10px;
	width: 150px;
}
#rating-star span {
	background: url(images/commentstar.png) no-repeat 0 -90px;
	display: inline-block;
	height: 23px;
	text-indent: -999em;
	width: 23px;
}
.text {
	width: 80%;
	height: 109px;
	border: solid 1px #ccc;
}
</style>

</head>
<body style="padding-bottom: initial;" class="hPC">
	<header class="u-header">
		<div class="l">
			<a href="javascript:history.go(-1);" class="J_backToPrev"><span
				class="u-icon"><img src="images/kj_15.png" width="15" /></span></a>
		</div>
		<div class="u-hd-tit">
			<span>我要评论</span>
		</div>
		<div class="r">
			<a href="index.jsp"><span class="u-icon"><img
					src="images/kj_16.png" width="25"></span></a>
		</div>
	</header>
	<div class="space10"></div>
	<div class="quiz">
		<h3></h3>
		<form action="${path }/IndexServlet?method=score" class="quiz-content" method="post">
			<input type="hidden" value="${param.tid }" id="tid" name="tid"/> 
			<input type="hidden" value="" id="pingfen" name="pingfen"/>
			<input type="hidden" value="${sid }" id="sid" name="sid"/>
			<input type="hidden" value="<%=openid %>" id="openid" name="openid"/>
			<div class="goods-comm">
				<table
					style="width: 100%; border: 0; cellspacing: 0; cellpadding: 0">
					<tr>
						<td style="width: 50px">满意度:</td>
						<td><div id="rating-star">
								<span>20</span> <span>40</span> <span>60</span> <span>80</span>
								<span>100</span>
							</div></td>
					</tr>
					<tr>
						<td>内 容：</td>
						<td><textarea name="content" id="content" class="text"
								placeholder="字数限制为5-200个"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><button class="btm" type="submit">发表评论</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<script>
		$('#rating-star').on('click', 'span', function() {
			$('#rating-star').data('star', this.innerHTML);
			setStar(this);
		})

		function setStar(star) {
			var $this = $(star);
			var level = $this.html();
			var n;
			if (level >= 80) {
				n = '0 0';
			} else if (level >= 60) {
				n = '0 -30px';
			} else {
				n = '0 -60px';
			}
			$this.prevAll().andSelf().css('background-position', n);
			$this.nextAll().css('background-position', '0 -90px');
			$('#pingfen').val(level);
		}
	</script>
</body>
</html>