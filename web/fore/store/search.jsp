<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public.jsp"%>
<%
	
/* 	String search = request.getParameter("sr");
	request.setAttribute("sr", search);
 */	
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
							class="form-control" placeholder="店铺搜索！" type="text" value="">
						<span class="input-group-btn">
							<button class="btn5 submit" type="submit" id="btnSearch"
								onclick="indexSearch()">
								<i class="search-icon"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="r"><a href="index.jsp" ><span class="u-icon"><img src="images/kj_16.png" width="25"></span></a></div>
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
					$.ajax({
						url: '${path}/IndexServlet?method=search',
						type: 'post',	                
		                data:{
		    				page:i,
		    				sid:'${sessionScope.sid}',
		    				name: '${param.sr }',
		    				lat : '${sessionScope.lat}',
		    				lon : '${sessionScope.lon}'
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
		$(".userdiv").html("<div class=\"orderlisttit\">查询结果</div>");
		$.ajax({
			url: '${path}/IndexServlet?method=search',
			type: 'post',	                
            data:{
				page : 1,
				sid : '${sessionScope.sid}',
				name : decodeURI('${param.sr}'),
				lat : '${sessionScope.lat}',
				lon : '${sessionScope.lon}',
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
				$(".userdiv").append("<div id=\"tip\">没有数据了</div>"); 
			}
			return false; 
		}
	}
	
	function selectT(tid){
		window.location.href="storeView.jsp?tid=" + tid;
	}
	
	function indexSearch(){
		var val = $('#input-main-nav-search').val();
		window.location.href=encodeURI(encodeURI("search.jsp?sr="+val));
	}
	</script>
</body>
</html>