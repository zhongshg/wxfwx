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
<script type="text/javascript" id="apijs"
	src="http://api.map.baidu.com/api?v=2.0&ak=54323E3C9a3aaee75d3d4bbd48a373a9"></script>
<style>
#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
	padding: 0;
	margin: 0;
	background: #ffc294;
	z-index: 999;
}

.poptip {
	position: absolute;
	top: 40px;
	left: 10px;
	line-height: 16px;
	color: #DB7C22;
	font-size: 12px;
	background-color: #FFFCEF;
	border: solid 1px #FFBB76;
	border-radius: 5px;
	box-shadow: 0 0 3px #ddd;
}

.poptip-arrow {
	position: absolute;
	overflow: hidden;
	font-style: normal;
	font-family: simsun;
	font-size: 12px;
	text-shadow: 0 0 2px #ccc;
}

.poptip-arrow em, .poptip-arrow i {
	position: absolute;
	left: 0;
	top: 0;
	font-style: normal;
}

.poptip-arrow em {
	color: #FFBB76;
}

.poptip-arrow i {
	color: #FFFCEF;
	text-shadow: none;
}

.poptip-arrow-top, .poptip-arrow-bottom {
	height: 6px;
	width: 12px;
	left: 12px;
	margin-left: -6px;
}

.poptip-arrow-left, .poptip-arrow-right {
	height: 12px;
	width: 6px;
	top: 12px;
	margin-top: -6px;
}

.poptip-arrow-top {
	top: -6px;
}

.poptip-arrow-top em {
	top: -1px;
}

.poptip-arrow-top i {
	top: 0px;
}

.poptip-arrow-bottom {
	bottom: -6px;
}

.poptip-arrow-bottom em {
	top: -8px;
}

.poptip-arrow-bottom i {
	top: -9px;
}

.poptip-arrow-left {
	left: -6px;
}

.poptip-arrow-left em {
	left: 1px;
}

.poptip-arrow-left i {
	left: 2px;
}

.poptip-arrow-right {
	right: -6px;
}

.poptip-arrow-right em {
	left: -6px;
}

.poptip-arrow-right i {
	left: -7px;
}

#mapContainer {
	width: 400px;
	height: 300px
}

.onoffbtn {
	cursor: pointer;
	background: #ccc;
	font-size: 12px;
	padding: 4px;
	border-radius: 5px;
	margin: 0 10px;
}

.infolbl {
	font-size: 2px;
	color: gray;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	clear: both;
	float: left;
}

.closeMap {
	display: block;
	width: 16px;
	height: 16px;
	background: #808080;
	color: white;
	position: absolute;
	right: 0;
	top: 0;
	border-radius: 8px;
	text-align: center;
	vertical-align: middle;
	font-family: Verdana;
	font-size: 8px;
	z-index: 888;
	cursor: pointer;
	text-decoration: none;
}

a:hover {
	background: #e40000;
}

.datagrid-mask-msg {
	font-size: 12px;
	margin: auto;
	text-align: center;
	position: absolute;
	background: #000000;
	color: #fff;
	padding: 2px;
	z-index: 777
}

._txtareaName {
	display: none;
	float: left
}

.allCon {
	width: 88px;
	float: left
}

#mainCMap {
	position: "relative";
	line-height: 0;
	height: 0;
	left: 200px
}

.tangram-suggestion-main {
	z-index: 999;
}
</style>
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
						<!-- //id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr -->
						<form
							action="${pageContext.servletContext.contextPath}/servlet/StoreServlet?method=manage"
							id="form1" method="post" onsubmit="return test();">
							<input type="hidden" id="id" name="id" value="${store.id}" /> <input
								type="hidden" id="sid" name="sid" value="${store.sid}" /> <input
								type="hidden" id="img" name="img" value="${store.img}" />
							<%--图片路径--%>
							<input type="hidden" name="page" value="${param.page}" />
							<fieldset>
								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								<p>
									<label>店铺类型</label> <select name="typeid">
										<c:forEach items="${storeTypeList }" var="storeType">
											<c:choose>
												<c:when test="${store.typeid == storeType.id}">
													<option value="${storeType.id }" selected="selected">${storeType.tname }</option>
												</c:when>
												<c:otherwise>
													<option value="${storeType.id }">${storeType.tname }</option>
												</c:otherwise>
											</c:choose>

										</c:forEach>
									</select>
								</p>
								<%-- <p>
									<label>店铺编号</label> <input class="text-input large-input"
										type="text" id="code" name="code" value="${store.code}" />
								</p> --%>
								<p>
									<label>店铺名称</label> <input class="text-input large-input"
										type="text" id="name" name="name" value="${store.name}" />
								</p>
								<p>
									<label>联系电话</label> <input class="text-input large-input"
										type="text" id="tel" name="tel" value="${store.tel}" />
								</p>
								<p>
									<label>固定电话</label> <input class="text-input large-input"
										type="text" id="phone" name="phone" value="${store.phone}" />
								</p>
								<p>
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
											src='${store.img}'
											width='200' height='200' />
									</c:if>
									<c:if test="${empty store.img}">
                                                请上传图片
                                            </c:if>
								</div>
								<p>
									<label>视频链接</label> <input class="text-input large-input"
										type="text" id="vid" name="vid" value="${store.vid}" onblur="splitVid(this)"/>
								</p>
								<p>
									<label>店铺描述</label>
									<textarea class="text-input" id="content" name="content"
										cols="79" rows="15">${store.content}</textarea>
									<script charset="utf-8"
										src="${pageContext.servletContext.contextPath}/kindeditor/kindeditor.js"></script>
									<script charset="utf-8"
										src="${pageContext.servletContext.contextPath}/kindeditor/lang/zh_CN.js"></script>
									<script>
										KindEditor
												.ready(function(K) {
													K
															.create(
																	'textarea[name="content"]',
																	{
																		uploadJson : '${pageContext.servletContext.contextPath}/kindeditor/jsp/upload_json.jsp',
																		fileManagerJson : '${pageContext.servletContext.contextPath}/kindeditor/jsp/file_manager_json.jsp',
																		allowFileManager : true,
																		width : '100%',
																		height : '300px'
																	});
												});
									</script>
								</p>
								<p>
									<label>地址</label> <input class="text-input large-input"
										type="text" id="area" name="area" value="${store.area}" />
								</p>
								<p>
									<label>地图位置</label>
								</p>
								<br />
								<div style="z-index: 777; position: relative;">
									<input name="ctl00$c$BaiduMap1$_txtareaName" type="text"
										id="c_BaiduMap1__txtareaName" class="_txtareaName"
										style="width: 230px;" /> <span class="onoffbtn"
										data-commondarg="">显示地图</span>
									<div id="mainCMap" style="z-index: 888">
										<div class="poptip">
											<span class="poptip-arrow poptip-arrow-top"><em>◆</em><i>◆</i></span>
											<div id="mapContainer">
												<!-- <div class="datagrid-mask-msg">正在加载</div> -->
												<div id="allmap"></div>
												<a class="closeMap" title="关闭" onclick="onoff();">X</a>
											</div>
										</div>
									</div>
								</div>
								<p>
									<input name="zcode" type="hidden" id="zcode"
										value="${store.zcode }" /> 
									<input name="cityCode"
										type="hidden" id="cityCode" value="${store.cityCode }" /> 
									<input name="jing" type="hidden" id="jing" value="${store.jing }" /> &nbsp;
									<input
										name="wei" type="hidden" id="wei" value="${store.wei }" />
									<input
										name="provice" type="hidden" id="provice" value="${store.provice }" />
									<input
										name="city" type="hidden" id="city" value="${store.city }" />
									<input
										name="street" type="hidden" id="street" value="${store.street }" />
									<input
										name="county" type="hidden" id="county" value="${store.county }" />
										
								</p>
								
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
	<script>
		$(document)
				.ready(

						function() {

							InitMap();

							var level = 12;
							var areaName = '';
							var areaAddress = '';
							var areaLat = '';
							var areaLon = '';
							var areaCode = '';
							var cityCode = '';
							
							var provice = '';
							var city = '';
							var district = '';
							var street = '';
							var county = '';
							// --------------------------------------

							// 百度地图API功能
							var map = new BMap.Map("allmap");
							var point = new BMap.Point(117.100851,36.66506091);
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
								myGeo.getPoint(areaAddress, function(point) {
									if (point) {
										map.centerAndZoom(point, level);
										addMarker(point);
									} else {
										alert("您选择地址没有解析到结果!");
									}
								}, "北京市");
							}
							;
							function InitMap() {
								$('#mapContainer').css({
									width : 500,
									height : 400,
									display : "block"

								});
								$('.datagrid-mask-msg')
										.css(
												{
													left : ($('#mapContainer')
															.width() - $(
															'.datagrid-mask-msg')
															.width()) / 2,
													top : $('#mapContainer')
															.height() / 2 - 20,
													display : "block"
												});

								$('#c_BaiduMap1__txtareaName').show();
								onoff();
							}
							function addMarker(point, index) {
								showLoding();
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
												"http://api.map.baidu.com/geocoder/v2/?ak=54323E3C9a3aaee75d3d4bbd48a373a9&location="
														+ point.lat
														+ ","
														+ point.lng
														+ "&output=json&pois=10&callback=?",
												null,
												function(data) {
													actionCallback(point, data);
												});
							}
							function getBoundary() {
								var bdary = new BMap.Boundary();
								bdary.get("重庆", function(rs) { // 获取行政区域
									map.clearOverlays(); // 清除地图覆盖物
									var count = rs.boundaries.length; // 行政区域的点有多少个
									for (var i = 0; i < count; i++) {
										var ply = new BMap.Polygon(
												rs.boundaries[i], {
													strokeWeight : 2,
													strokeColor : "#ff0000"
												}); // 建立多边形覆盖物
										map.addOverlay(ply); // 添加覆盖物
										map.setViewport(ply.getPath()); // 调整视野
									}
								});
							};

							function showLoding() {
								$('div.datagrid-mask-msg').css({
									display : ""
								});
							}
							function hideLoading() {
								$('div.datagrid-mask-msg').css({
									display : "none"
								});
							}
							function showMap(show) {
								$('.onoffbtn').attr('data-commondarg',
										show ? 'show' : 'hide');
								onoff();
							}
							function onoff() {
								var e = $('#mainCMap');
								var btn = $('.onoffbtn')
								if (btn.attr('data-commondarg') == 'show') {
									btn.attr('data-commondarg', 'hide');
									e.show();
									btn.html('隐藏地图');
								} else {
									btn.attr('data-commondarg', 'show');
									e.hide();
									btn.html('显示地图');
								}
							}
							;
							function actionCallback(point, data) {
								var d = data.result;
								var comp = d.addressComponent;
								//console.log(d);
								areaCode = comp.adcode;
								provice = comp.province;
								city = comp.city;
								district = comp.district;
								street = comp.street;
								county = d.sematic_description;
								areaLat = d.location.lat;
								areaLon = d.location.lng;
								areaAddress = d.formatted_address;
								cityCode = d.cityCode;
								
								//alert(areaCode+ "-"+provice + "-"+ city+ "-"+ district+ "-"+street+ "-"+county);
								if (d.pois.length > 0)
									areaName = d.pois[0].name;
								else
									areaName = areaAddress;
								if (d.sematic_description)
									areaAddress += "(" + d.sematic_description
											+ ")";
								updateui();

								map.panTo(point);

								hideLoading();
							}
							;
							function updateui() {
								$('#c_BaiduMap1__txtareaName').val(areaName);
								dataSync();
							}

							$('.onoffbtn').click(function() {
								onoff();
							});
							$('.closeMap').click(function() {
								onoff();
							});
							function dataSync() {
								var array = new Array();
								array[0] = 'area';
								array[1] = 'jing';
								array[2] = 'wei';
								array[3] = 'zcode';
								array[4] = 'cityCode';
								array[5] = 'provice';
								array[6] = 'city';
								array[7] = 'street';
								array[8] = 'county';
								
								

								$(array).each(function(i, data) {
									if (!data)
										return;
									var e = $('#' + data);
									if (e) {
										if (e.is('input') || e.is('textarea')) {
											if (i == 0)
												e.val(areaAddress);
											if (i == 1)
												e.val(areaLon);
											if (i == 2)
												e.val(areaLat);
											if (i == 3)
												e.val(areaCode);
											if (i == 4)
												e.val(cityCode);
											if (i == 5)
												e.val(provice);
											if (i == 6)
												e.val(city);
											if (i == 7)
												e.val(street);
											if (i == 8)
												e.val(county);
										} else {
											if (i == 0)
												e.html(areaAddress);
											if (i == 1)
												e.html(areaLon);
											if (i == 2)
												e.html(areaLat);
											if (i == 3)
												e.html(areaCode);
											if (i == 4)
												e.html(cityCode);
											if (i == 5)
												e.val(provice);
											if (i == 6)
												e.val(city);
											if (i == 7)
												e.val(street);
											if (i == 8)
												e.val(county);
										}
									}
								});
							}
							function valData(i, data, ele) {
								if (i == 0)
									ele.val(data);
							}

							// -----------------------------自动完成-------------------
							var ac = new BMap.Autocomplete(
							// 建立一个自动完成的对象
							{
								"input" : "c_BaiduMap1__txtareaName",
								"location" : map

							});

							ac.addEventListener("onhighlight", function(e) { // 鼠标放在下拉列表上的事件
								var str = "";
								var _value = e.fromitem.value;
								var value = "";
								if (e.fromitem.index > -1) {
									value = _value.province + _value.city
											+ _value.district + _value.street
											+ _value.business;
								}
								str = "FromItem<br />index = "
										+ e.fromitem.index + "<br />value = "
										+ value;

								value = "";
								if (e.toitem.index > -1) {
									_value = e.toitem.value;
									value = _value.province + _value.city
											+ _value.district + _value.street
											+ _value.business;
								}
								str += "<br />ToItem<br />index = "
										+ e.toitem.index + "<br />value = "
										+ value;
								$("#searchResultPanel").html(str);
							});

							var myValue;
							ac.addEventListener("onconfirm",
									function(e) { // 鼠标点击下拉列表后的事件
										showMap(true);
										showLoding();
										var _value = e.item.value;
										myValue = _value.province + _value.city
												+ _value.district
												+ _value.street
												+ _value.business;
										$("#searchResultPanel").html(
												"onconfirm<br />index = "
														+ e.item.index
														+ "<br />myValue = "
														+ myValue);

										setPlace();
									});

							function setPlace() {
								map.clearOverlays(); // 清除地图上所有覆盖物
								function myFun() {
									var pp = local.getResults().getPoi(0).point; // 获取第一个智能搜索的结果
									// map.centerAndZoom(pp, level);
									addMarker(pp);// 添加标注
								}
								var local = new BMap.LocalSearch(map, { // 智能搜索
									onSearchComplete : myFun
								});
								local.enableAutoViewport();
								local.search(myValue);
							}
						});
		//https://v.qq.com/x/cover/v9wsovm3avlt7y2/q0022sp0yap.html
		function splitVid(video){
			var url = video.value;
			if(url.indexOf("v.qq.com") > 0 ){
				var arr = url.split("/");
				var html = arr[arr.length-1];
				if(html.indexOf("htm") > 0.){
					var vids = html.split(".");
					if(vids.length == 2){
						var vid = vids[0];
						video.value = vid;
					}
				}
			}
		}
	</script>
</body>
</html>