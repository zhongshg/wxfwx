<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public.jsp"%>
<%
	String tid = request.getParameter("tid");
	//根据获取的店铺id获取店铺详细信息
	Map<String, String> store = new HashMap<String, String>();
	if (tid != null && tid.length() > 0) {
		StoreDAO storeDao = new StoreDAO();
		store.put("id", tid);
		store = storeDao.getById(store);
		if (store == null || store.get("id") == null) {
			return;
		}
		request.setAttribute("store", store);
		String[] imgs = store.get("img").split(";");
		request.setAttribute("imgs", imgs);
	}

	AreaDao areaDao = new AreaDao();
	DataField adf = areaDao.get(p_areaCode);
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
<title>商家详情</title>
<meta content="" name="keywords">
<meta content="" name="description">
<meta name="sogou_site_verification" content="G7nmLR75yc">
<meta name="baidu-tc-cerfication" content="">
<meta name="360-site-verification" content="">
<link rel="stylesheet" type="text/css" href="style/css.css">
<link rel="stylesheet" type="text/css" href="style/public.css">
<link rel="stylesheet" type="text/css" href="style/reset.css"
	media="all">
<%
	String msg = request.getParameter("msg");
	if (msg != null) {
		String tips = "";
		if ("1".equals(msg)) {
			tips = "评论成功!";
		} else if ("2".equals(msg)) {
			tips = "您已经评价过该店铺啦!";
		} else {
			tips = "系统错误，请稍候评论";
		}
		out.println("<script>alert(\"" + tips + "\");</script>");
	}
%>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/zepto.js"></script>
</head>

<body style="padding-bottom: initial; background: #fff" class="hPC">
	<header class="u-header">
		<div class="l">
			<a href="javascript:history.go(-1)" class="J_backToPrev"><span
				class="u-icon"><img src="images/kj_15.png" width="15" /></span></a>
		</div>
		<div class="u-hd-tit">
			<span>商家详情</span>
		</div>
		<div class="r">
			<a href="index.jsp"><span class="u-icon"><img
					src="images/kj_16.png" width="25"></span></a>
		</div>
	</header>
	<iframe class="video_iframe" data-vidtype="1"
		style="position: relative; z-index: 1;" height="260px" width="100%"
		frameborder="0" src="player.jsp?vid=${store.vid }" scrolling="no"
		allowfullscreen=""> </iframe>
	<!-- <div id="mod_player" class="mod_player"></div> -->
	<!--商品清单-->
	<div class="userdiv">
		<div class="orderlisttit">${store.name }</div>
		<div class="orderdeatilbox clearfix">
			<div class="orderdeatil clearfix">
				<div class="user_orderlist_r">
					<p class="cart_g_name"></p>
					<p>
						<span class="fl"
							onclick="javascript:window.location.href='guide.jsp?lon=${store.jing}&lat=${store.wei}'"><img
							src="images/kj_19.png" width="16" onclick="" />
							&nbsp;&nbsp;${store.area }</span>
					</p>
				</div>
			</div>
		</div>
		<div class="orderdeatilbox clearfix border_t">
			<p class="qx_p">
				<a class="" href="tel:${store.tel }"><img src="images/kj_18.png"
					width="30" /></a> <a class="dzf_zhifu"
					href="isscore.jsp?tid=${store.id }">我要评论</a>
			</p>
		</div>
	</div>
	<!-- 商铺详情 开始 -->
	<div id="tab" style="margin-top: 20px">
		<div class="tabList">
			<ul>
				<li class="cur">菜品详情</li>
				<li>菜品评论</li>
			</ul>
		</div>
		<div class="tabCon">
			<div class="cur">${store.content }</div>
			<div>
				<table
					style="width: 100%; border: 0; cellspacing: 0; bgcolor: #FFFFFF; cellpadding: 0;"
					class="py">
				</table>
			</div>
		</div>
	</div>
	<!-- 商铺详情 结束 -->
	<script>
		$(function() {
			var winH = $(window).height(); //页面可视区域高度
			var i = 1;//设置当前页数 
			$(window).scroll(function() {
				var pageH = $(document.body).height();
				var scrollT = $(window).scrollTop();
				//滚动条top 
				var aa = (pageH - winH - scrollT) / winH;
				if (aa <= 0) {
					i++;
					var tid = '${param.tid }';
					$.ajax({
						url : '${path}/IndexServlet?method=getScoreList',
						type : 'post',
						data : {
							page : i,
							sid : '${sessionScope.sid}',
							tid : tid
						},
						dataType : 'json',
						success : function(data) {
							eachData(data);
						}
					});
				}
			});
		});
		$(window).load(function() {
			//要执行的方法体
			init();
		});
		function init() {
			var tid = '${param.tid }';
			$.ajax({
				url : '${path}/IndexServlet?method=getScoreList',
				type : 'post',
				data : {
					page : 1,
					sid : '${sessionScope.sid}',
					tid : tid
				},
				dataType : 'json',
				success : function(data) {
					eachData(data);
				}
			});
		}

		function eachData(data) {
			if (data.length >= 1) {
				$
						.each(
								data,
								function(index, array) {
									var str = "<tr><td width=\"12%\" rowspan=\"2\"><img src=\"images/kj_04.png\" "; 
					str += "width=\"60\" height=\"60\"></td><td width=\"88%\">";
									str += " </td></tr><tr><td height=\"57\"><span class=\"an\">评语:</span>"
											+ array.content + "</td>";
									str += " class=\"l\" height=\"80\" width=\"80\"><div class=\"user_orderlist_r\"></tr>";
									$(".py").append(str);
								});
			} else {
				return false;
			}
		}
	</script>
</body>
</html>