<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="wap.wx.menu.WxMenuUtils"%>
<%@page import="wap.wx.dao.SubscriberDAO"%>
<%@page import="wap.wx.dao.WxsDAO"%>
<%@page import="wap.wx.service.SubscriberService"%>
<%
	String sid = "1";
	if (null != request.getParameter("wx")) {
		sid = request.getParameter("wx");
		session.setAttribute("sid", sid);
	}
	String openid = request.getParameter("openid");

	Map<String, String> wx = new HashMap<String, String>();
	wx.put("id", sid);
	wx = new WxsDAO().getById(wx);

	SubscriberDAO subscriberDAO = new SubscriberDAO();
	Map<String, String> subscriber = subscriberDAO.getByOpenid(openid);
	//为空时存入
	if (openid != null && (subscriber == null || null == subscriber.get("id"))) {
		String parentid = "0";
		Map<String, String> targetsubscriber = new HashMap<String, String>();
		String targetopenid = request.getParameter("targetopenid");
		new SubscriberService().addSubscriber(wx, openid);
	} else if(openid == null){
		out.println("<script>alert(\"微信校验错误，请联系公众号管理员!\");</script>");
		return;
	}
	session.setAttribute("openid", openid);
	request.setAttribute("ak", "54323E3C9a3aaee75d3d4bbd48a373a9");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon"
	href="http://simbyone.com/wp-content/uploads/2014/04/3455e6f65c33232a060c28829a49b1cb.png" />
<link
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />
<script type="text/JavaScript"
	src="http://api.map.baidu.com/api?v=2.0&ak=${ak}"></script>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<style>
#loading {
	background-color: #8B3E2F;
	height: 100%;
	width: 100%;
	position: fixed;
	z-index: 1;
	margin-top: 0px;
	top: 0px;
}

#loading-center {
	width: 100%;
	height: 100%;
	position: relative;
}

#loading-logo {
	position: absolute;
	left: 45%;
	top: 30%;
	height: 200px;
	width: 200px;
	margin-top: -100px;
	margin-left: -100px;
}

#loading-center-absolute {
	position: absolute;
	left: 50%;
	top: 50%;
	height: 200px;
	width: 200px;
	margin-top: -100px;
	margin-left: -100px;
}

.object {
	-moz-border-radius: 50% 50% 50% 50%;
	-webkit-border-radius: 50% 50% 50% 50%;
	border-radius: 50% 50% 50% 50%;
	position: absolute;
	border-left: 5px solid #FFF;
	border-right: 5px solid #FFF;
	border-top: 5px solid transparent;
	border-bottom: 5px solid transparent;
	-webkit-animation: animate 2s infinite;
	animation: animate 2s infinite;
}

#object_one {
	left: 75px;
	top: 75px;
	width: 50px;
	height: 50px;
}

#object_two {
	left: 65px;
	top: 65px;
	width: 70px;
	height: 70px;
	-webkit-animation-delay: 0.1s;
	animation-delay: 0.1s;
}

#object_three {
	left: 55px;
	top: 55px;
	width: 90px;
	height: 90px;
	-webkit-animation-delay: 0.2s;
	animation-delay: 0.2s;
}

#object_four {
	left: 45px;
	top: 45px;
	width: 110px;
	height: 110px;
	-webkit-animation-delay: 0.3s;
	animation-delay: 0.3s;
}

@-webkit-keyframes animate { 
	50% {
		-ms-transform: rotate(180deg);
		-webkit-transform: rotate(180deg);
		transform: rotate(180deg);
	}
	100%{
	-ms-transform:rotate(0deg);
	-webkit-transform:rotate(0deg);
	transform:rotate(0deg);
	}
}
@keyframes animate { 
	50% {
		-ms-transform: rotate(180deg);
		-webkit-transform: rotate(180deg);
		transform: rotate(180deg);
	}
	100%{
	-ms-transform:rotate(0deg);
	-webkit-transform:rotate(0deg);
	transform:rotate(0deg);
	}
}
</style>
</head>
<body>
	<div id="loading">
		<div id="loading-center">
			<div id="loading-logo">
				<img src="images/logo.png" alt="logo" />
			</div>
			<div id="loading-center-absolute">
				<div class="object" id="object_four"></div>
				<div class="object" id="object_three"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_one"></div>
			</div>
		</div>
	</div>
	<div id="allmap"></div>
	<script>
		var map = new BMap.Map("allmap");
		var geolocation = new BMap.Geolocation();
		var gc = new BMap.Geocoder();
		geolocation.getCurrentPosition(function(r) {
			if (this.getStatus() == BMAP_STATUS_SUCCESS) {
				var point = r.point;
				gc.getLocation(point, function(rs) {
					getPoint(point, rs);
				});
			} else {
				confirm("自动定位失败，请手动定位");
				window.location.href = "map.jsp";
			}
		});
		function getPoint(point, rs) {
			$.getJSON(
					"http://api.map.baidu.com/geocoder/v2/?ak=${ak}&location="
							+ point.lat + "," + point.lng
							+ "&output=json&pois=10&callback=?", null,
					function(data) {
						actionCallback(point, data);
					});
		}
		function actionCallback(point, data) {
			var d = data.result;
			var areaCode = d.addressComponent.adcode;
			var district = d.addressComponent.district;
			var areaLat = d.location.lat;
			areaLon = d.location.lng;
			window.location.href = "index.jsp?areaCode=" + areaCode + "&lat="
					+ areaLat + "&lon=" + areaLon + "&dist=" + district;
		}
	</script>
</body>
</html>
