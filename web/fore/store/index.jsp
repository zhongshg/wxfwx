<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="wap.wx.dao.BannerDao" %>
<%@include file="public.jsp"%>
<%
	Map<String, String> storetype = new HashMap<String, String>();
	//String sid = request.getParameter("sid");
	storetype.put("sid", sid);
	StoreTypeDAO storeTypeDao = new StoreTypeDAO();
	PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
	pu.setPageSize(7);
	pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
			? Integer.parseInt(request.getParameter("page"))
			: 1);
	pu.setMaxSize(storeTypeDao.getConut(storetype));
	pu.setList(storeTypeDao.getList(pu, storetype));
	request.setAttribute("pu", pu);
	request.setAttribute("sid", sid);
	//开始获取地址信息
	district = request.getParameter("dist") == null ? district : request.getParameter("dist");
	areaCode = request.getParameter("areaCode") == null ? areaCode : request.getParameter("areaCode");
	if ((district == null && areaCode == null) || district.length() == 0) {
		district = "历城区";
		areaCode = "370112";
	}
	lat = request.getParameter("lat") == null ? areaCode : request.getParameter("lat");
	lon = request.getParameter("lon") == null ? areaCode : request.getParameter("lon");

	if (request.getParameter("lat") != null) {
		session.setAttribute("district", district);
		session.setAttribute("lon", lon);
		session.setAttribute("lat", lat);
		session.setAttribute("areaCode", areaCode);
		session.setAttribute("sid", sid);
	}

	//开始获取地域信息
	AreaDao areaDao = new AreaDao();
	//获取所有最上级地址
	p_areaCode = areaCode.substring(0, 4) + "00";
	session.setAttribute("p_areaCode", p_areaCode);
	List<Map<String, String>> list = areaDao.getList(Integer.parseInt(p_areaCode));
	request.setAttribute("areaList", list);
	request.setAttribute("path", request.getContextPath());
	
	//开始获取首页推荐店铺信息
	BannerDao bannerDao = new BannerDao();
	List<Map<String, String>> bannerList = bannerDao.getList(Integer.parseInt(sid));
	request.setAttribute("bannerList", bannerList);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>看见味道</title>
<link rel="stylesheet" type="text/css" href="style/css.css">
<link rel="stylesheet" type="text/css" href="style/public.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/common1.css"
	media="all">
<!--首页幻灯js-->
<script type="text/javascript" src="js/zepto.js"></script>
<script type="text/javascript" src="js/swipe.js"></script>
<!-- <script src="../../js/jquery.min.js" type="text/javascript"></script> -->
<!--首页幻灯js end-->
</head>
<body>
	<div style="overflow: hidden; background-color: #301a11;">
		<div class="clearboth wrap-logo-zn ">
			<select id="areaSelect" onchange="changeArea()">
				<c:forEach items="${areaList }" var="area">
					<c:choose>
						<c:when test="${sessionScope.areaCode == area.id }">
							<option value="${area.id }" selected="selected">${area.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${area.id }">${area.name }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		<div class="searchnew" id="searchContainer">
			<div class="products-search-item">
				<div class="main-nav-search ">
					<div class="input-group">
						<input autocomplete="off" id="input-main-nav-search"
							class="form-control" placeholder="店铺搜索！" type="text"> <span
							class="input-group-btn">
							<button class="btn5 submit" type="submit" id="btnSearch"
								onclick="indexSearch()">
								<i class="search-icon"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="wrap-logo-zn2">
			<a href="map.jsp"><img src="images/logo-zn.png" width="20"></a>
		</div>
	</div>
	<div class="banner openwebview">
		<div style="-webkit-transform: translate3d(0, 0, 0);">
			<div id="banner_box" class="box_swipe">
				<ul
					style="list-style: none outside none; transition-duration: 500ms;">
					<c:forEach items="${bannerList }" var="banner">
						<li><a href="storeView.jsp?tid=${banner.code}"> <img src="${path}${banner.img }" 
								alt="2" style="width: 100%;">
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<script>
			$(function() {
				new Swipe(document.getElementById('banner_box'), {
					speed : 500,
					auto : 3000,
					callback : function() {
						var lis = $(this.element).next("ol").children();
						lis.removeClass("on").eq(this.index).addClass("on");
					}
				});
			});
		</script>
	</div>
	<div class="kan001">
		<ul>
			<c:forEach items="${pu.list}" var="storeType">
				<li><a href="storeList.jsp?tid=${storeType.id}">${storeType.tname}</a></li>
			</c:forEach>
			<li><a href="moreType.jsp?sid=${sid}">更多</a></li>
		</ul>
	</div>
	<div class="recommend openwebview">
		<div class="wrap">
			<div class="item">
				<div class="item_con clearfix"></div>
			</div>
		</div>
	</div>
	<script>
		function changeArea() {
			//切换地域的时候  更换底部店铺信息
			var district = $('#areaSelect').val();
			init(district);
		}

		$(window).load(function() {
			//要执行的方法体
			var district = '${sessionScope.areaCode}';
			init(district);
		});
		function init(district) {
			$(".item_con").html("");
			$.ajax({
				url : '${path}/IndexServlet?method=indexStore',
				type : 'post',
				data : {
					page : 1,
					sid : '${sessionScope.sid}',
					//tid : tid,
					district : district,
					lat : '${sessionScope.lat}',
					lon : '${sessionScope.lon}'
				//orders : orders
				},
				dataType : 'json',
				success : function(data) {
					eachData(data);
				},
				error : function(data) {
					alert("error");
					return false;
				}
			});
		}
		function eachData(data) {
			if (data && data.length >= 1) {
				$.each(
					data,
					function(index, array) {
					var juli = '';
					if (array.juli.length < 5) {
						juli += array.juli + "m";
					} else {
						juli += array.juli / 1000 + "km";
					}
					var str = "<dl><p class=\"txt2\">"
							+ array.name + "</p>";
					str += "<dt><a href=\"storeView.jsp?tid="
							+ array.id
							+ "\" data-url=\"\"><img src=\""+array.img+"\""; 
					str += " width=\"44%\" height=\"60px\" class=\"fadeInImg\"></a></dt>";
					str += "</dt><dd><p class=\"txt\">"
							+ array.name + "</p>";
					str += "<div class=\"price clearfix\">";
					str += "<span class=\"now_price\" \"><em>"
							+ juli + "</em></span>";
					str += "</div></dd></dl>";
					$(".item_con").append(str);
				});
			} else {
				return false;
			}
		}
		function indexSearch() {
			var val = $('#input-main-nav-search').val();
			window.location.href = encodeURI(encodeURI("search.jsp?sr=" + val));
		}
	</script>

</body>
</html>