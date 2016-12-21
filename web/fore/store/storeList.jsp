<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="wap.wx.dao.StoreTypeDAO"%>
<%@page import="wap.wx.dao.StoreDAO"%>
<%@page import="wap.wx.dao.AreaDao"%>
<%@page import="wap.wx.util.PageUtil"%>
<%@include file="public.jsp"%>

<%
	String tid = request.getParameter("tid");
	request.setAttribute("tid", tid);
	//获取当前页面的店铺类别信息
	StoreTypeDAO storeTypeDao = new StoreTypeDAO();
	//storeType.put("id", tid);
	List<Map<String, String>> storeTypeList = storeTypeDao.getAllList(sid);
	request.setAttribute("storeTypeList", storeTypeList);
	//开始获取地域信息
	AreaDao areaDao = new AreaDao();
	//获取所有最上级地址
	List<Map<String, String>> list = areaDao.getList(Integer.parseInt(p_areaCode));
	request.setAttribute("areaList", list);
	request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta name="baidu-site-verification" content="t7oDT96Amk">
	<title>店铺列表</title>
	<meta content="" name="keywords">
	<meta content="" name="description">
	<meta name="sogou_site_verification" content="G7nmLR75yc">
	<meta name="baidu-tc-cerfication" content="">
	<meta name="360-site-verification" content="">
	<link rel="stylesheet" type="text/css" href="style/css.css">
	<!-- <script src="../../js/jquery.min.js" type="text/javascript"></script> -->
</head>
<body>
<header class="u-header">
  <div class="l"> <a href="javascript:history.go(-1)" class="J_backToPrev"><span class="u-icon">
  <img src="images/kj_15.png" width="15"/></span></a> </div>
  <div class="u-hd-tit"><span>店铺列表</span></div>
  <div class="r"><a href="index.jsp" ><span class="u-icon"><img src="images/kj_16.png" width="25"></span></a></div>
</header>
	<div id="menu">
	  <select onchange="init()" id="stype" name="stype">
		  <c:forEach items="${storeTypeList }" var="storeType">
		 	 <c:choose>
			 	<c:when test="${tid == storeType.id }">
			    	<option value="${storeType.id}" selected="selected">${storeType.tname}</option>
			    </c:when>
			    <c:otherwise>
			    	<option value="${storeType.id}">${storeType.tname}</option>
			    </c:otherwise>
		    </c:choose>
		    </c:forEach>
	  </select>
	  <select onchange="init()" id="district" name="district">
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
	  <select onchange="init()" id="orders" name="orders">
	    <option value="1">距我最近</option>
	    <option value="2">评价最高</option>
	  </select>
	</div>
	<div>
		<img src="images/kj_01.png" width="100%">
	</div>
	<!--商品清单-->
	<div class="userdiv"></div>
	<!--商品清单结束-->
	<script>
	 $(function(){ 
		var winH = $(window).height(); //页面可视区域高度
		var i = 1;//设置当前页数 
		 $(window).scroll(function () { 
				var pageH = $(document.body).height(); 
				var scrollT = $(window).scrollTop();
				//滚动条top 
				var aa = (pageH- winH - scrollT)/winH;
				if(aa <= 0){ 
					i++;
					var tid = $('#stype').val();
					var district = $('#district').val();
					var orders = $('#orders').val();
					$.ajax({
						url: '${path}/IndexServlet?method=getList',
						type: 'post',	                
		                data:{
		    				page:i,
		    				sid:'${sessionScope.sid}',
		    				tid: tid,
		    				district : district,
		    				lat : '${sessionScope.lat}',
		    				lon : '${sessionScope.lon}',
		    				orders : orders
		    			},
		                dataType: 'json',
		                success: function (data) {
		                	eachData(data);
		                },
		                error: function (data) {
	    					$("#tip").html("获取数据错误"); 
	    					return false; 
		                }
           	     	});
           		 }
			}); 
	 }); 
	$(window).load(function(){
	    //要执行的方法体
		init();
	});
	function init(){
		var tid = $('#stype').val();
		var district = $('#district').val();
		var orders = $('#orders').val();
		$(".userdiv").html("<div class=\"orderlisttit\">店铺列表</div>");
		$.ajax({
			url: '${path}/IndexServlet?method=getList',
			type: 'post',	                
            data:{
				page : 1,
				sid : '${sessionScope.sid}',
				tid : tid,
				district : district,
				lat : '${sessionScope.lat}',
				lon : '${sessionScope.lon}',
				orders : orders
			},
            dataType: 'json',
            success: function (data) {
            	eachData(data);
            },
            error: function (data) {
				$("#tip").html("获取数据错误"); 
				return false; 
            }
          });
	}
	
	function eachData(data){
		if(data && data.length >= 1 ){
			$.each(data,function(index,array){ 
				var juli = '';
				if(array.juli.length < 5){
					juli += array.juli+"m";
				}else{
					juli += array.juli/1000+"km";
				}
				var str = "<div class=\"orderdeatil clearfix\" onclick=\"selectT("+array.id+")\">"; 
				str += "<img src=\""+array.img+"\""; 
				str += " class=\"l\" height=\"80\" width=\"80\"><div class=\"user_orderlist_r\">";
				str += "<p class=\"cart_g_name\">"+array.name+"("+juli+")"; 
					str += "</p><p class=\"space10\"></p><p style=\"margin: 5px 0;\">";
					str += "<span class=\"fl\"><img src=\"images/kj_19.png\" width=\"14\" />";
					str += "&nbsp;&nbsp;"+array.area;
					str += "</span></p></div><div class=\"orderdeatilbox clearfix border_t\"><p class=\"qx_p\">";
					//str += "<a class=\"dzf_quxiao_button\" href=\"\">收藏</a>";
					str += "<a class=\"dzf_zhifu\" href=\"isscore.jsp?tid="+array.id;
					str += "\">评论</a>";
				$(".userdiv").append(str); 
			});  
		}else{
			if($("#tip").text().length == 0){
				$(".userdiv").append("<div id=\"tip\">已经到底了</div>"); 
			}
			return false; 
		}
	}
	
	function selectT(tid){
		window.location.href="storeView.jsp?tid=" + tid;
	}
	</script>
</body>
</html>