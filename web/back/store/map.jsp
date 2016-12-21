<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/map.css" type="text/css" />
<!-- <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script> -->
<script src="//cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" id="apijs"
	src="http://api.map.baidu.com/api?v=2.0&ak=ybQ4QSIv8IOo9cul6ElsQDb88xOlLHnh"></script>
<script src="js/map.js" type="text/javascript"></script>
</head>
<body>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<td>
				<div style="z-index: 777">
					<input name="ctl00$c$BaiduMap1$_txtareaName" type="text"
						id="c_BaiduMap1__txtareaName" class="_txtareaName"
						style="width: 230px;" /> <span class="onoffbtn"
						data-commondarg="">显示地图</span>
					<div id="mainCMap" style="z-index: 888">
						<div class="poptip">
							<span class="poptip-arrow poptip-arrow-top"><em>◆</em><i>◆</i></span>
							<div id="mapContainer">
								<div class="datagrid-mask-msg">正在加载</div>
								<div id="allmap"></div>
								<a class="closeMap" title="关闭" onclick="onoff();">X</a>
							</div>
						</div>
					</div>
					<br /> <span id="c_BaiduMap1__lblinfo" class="infolbl"></span> <input
						type="hidden" name="ctl00$c$BaiduMap1$_hideValue"
						id="c_BaiduMap1__hideValue" value=",,,," />
				</div>
			</td>
		</tr>
		<tr>
			<th class="auto-style1">地区编码</th>
			<td class="auto-style1" colspan="2"><input
				name="ctl00$c$txtCode" type="number" value="0" id="c_txtCode"
				class="abc input-default" style="width: 120px;" /></td>
		</tr>
		<tr>
			<th class="auto-style1">经纬度</th>
			<td class="auto-style1" colspan="2">经：<input
				name="ctl00$c$txtLon" type="text" id="c_txtLon" /> &nbsp;纬：<input
				name="ctl00$c$txtLat" type="text" id="c_txtLat" />
			</td>
		</tr>
	</table>
</body>
</html>