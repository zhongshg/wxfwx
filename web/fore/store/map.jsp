<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public.jsp"%>

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
<title>地图定位</title>
<meta content="" name="keywords">
<meta content="" name="description">
<meta name="sogou_site_verification" content="G7nmLR75yc">
<meta name="baidu-tc-cerfication" content="">
<meta name="360-site-verification" content="">
<link rel="stylesheet" type="text/css" href="style/css.css">
<!-- <script src="../../js/jquery.min.js" type="text/javascript"></script> -->
<!-- <script type="text/JavaScript"
	src="http://api.map.baidu.com/api?v=2.0&ak=54323E3C9a3aaee75d3d4bbd48a373a9"></script> -->
</head>
<body>
	<header class="u-header">
		<div class="l">
			<a href="javascript:void(0)" class="J_backToPrev"><span
				class="u-icon"> <img src="images/kj_15.png" width="15" /></span></a>
		</div>
		<div class="u-hd-tit">
			<span>定位</span>
		</div>
		<div class="r">
			<a href="index.jsp"><span class="u-icon"><img
					src="images/kj_16.png" width="25"></span></a>
		</div>
	</header>
	<div id="menu"></div>
	<div>
		<img src="images/kj_01.png" width="100%">
	</div>
	<!--商品清单-->
	<div class="userdiv">
		<div class="orderlisttit">百度地图  </div>
		<input type="hidden" value="" id="curlocation"/> 
		<input type="hidden" value="" id="areaLat" /> 
		<input type="hidden" value="" id="areaLon" />
		<div id="allmap"></div>
		<script type="text/javascript">
			$(document)
					.ready(function() {
								InitMap();
								var level = 10;
								var areaName = '';
								var areaAddress = '';
								var areaLat = '';
								var areaLon = '';
								var areaCode = '';
								var cityCode = '';
								// --------------------------------------
								// 百度地图API功能
								var map = new BMap.Map("allmap");
								var point = new BMap.Point(117.071918,36.685786);
								map.centerAndZoom(point, level);
								map.addControl(new BMap.NavigationControl());
								map.addControl(new BMap.ScaleControl());
								map.addControl(new BMap.OverviewMapControl());
								map.addControl(new BMap.MapTypeControl());
								map.enableScrollWheelZoom();

								map.addEventListener("click", function(e) { // 监听事件，提示标注点坐标信息
									map.clearOverlays();
									addMarker(e.point, 1);
								});

								// 创建地址解析器实例
								var myGeo = new BMap.Geocoder();
								if (areaLat && areaLon) {
									point = new BMap.Point(areaLon, areaLat);
									map.centerAndZoom(point, level);
									addMarker(point);
								} else if (areaAddress) {
									// 将地址解析结果显示在地图上,并调整地图视野
									myGeo.getPoint(areaAddress,
											function(point) {
												if (point) {
													map.centerAndZoom(point,
															level);
													addMarker(point);
												} else {
													alert("您选择地址没有解析到结果!");
												}
											}, "济南市");
								}
								;
								function InitMap() {
									var height = window.screen.availHeight;
									$('#allmap').css({
										width : '100%',
										height : height,
										display : "block"
									});
								}
								function addMarker(point, index) {
									var marker = new BMap.Marker(point);
									map.addOverlay(marker);
									myGeo = new BMap.Geocoder();
									myGeo.getLocation(point, function(rs) {
										getPoint(point, rs);
									});
								}
								function getPoint(point, rs) {
									$
											.getJSON(
													"http://api.map.baidu.com/geocoder/v2/?ak=${ak}&location="
															+ point.lat
															+ ","
															+ point.lng
															+ "&output=json&pois=10&callback=?",
													null, function(data) {
														actionCallback(point,
																data);
													});
								}
								function actionCallback(point, data) {
									var d = data.result;
									areaCode = d.addressComponent.adcode;
									district = d.addressComponent.district;
									city = d.addressComponent.city;
									areaLat = d.location.lat;
									areaLon = d.location.lng;
									areaAddress = d.formatted_address;
									cityCode = d.cityCode;
									$('#curlocation').val(areaCode);
									$('#areaLat').val(areaLat);
									$('#areaLon').val(areaLon);
									map.panTo(point);
									if(confirm("确定定位到"+ city+ district+"?")){
										window.location.href = "index.jsp?areaCode="+areaCode +"&lat="+areaLat+"&lon="+areaLon+"&dist="+district;
									}
								}
							});
			
		</script>

	</div>
</body>
</html>