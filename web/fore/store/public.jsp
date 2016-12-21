<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="wap.wx.dao.StoreTypeDAO"%>
<%@page import="wap.wx.dao.AreaDao"%>
<%@page import="wap.wx.util.PageUtil"%>
<%@page import="wap.wx.dao.StoreDAO"%>
<%@page import="wap.wx.dao.ScoreDao"%>
<%@page import="job.tot.bean.DataField"%>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<%
	String sid = "1";
	String areaCode = (String) session.getAttribute("areaCode");
	String lat = (String) session.getAttribute("lat");
	String lon = (String) session.getAttribute("lon");
	String district = (String) session.getAttribute("district");
	String p_areaCode = (String) session.getAttribute("p_areaCode");
	if (areaCode == null || district == null) {
		district = "历城区";
	}
	request.setAttribute("path", request.getContextPath());
	request.setAttribute("ak", "54323E3C9a3aaee75d3d4bbd48a373a9");
	String openid = (String) session.getAttribute("openid");
	if (openid == null) {
		out.println("<script>confirm(\"当前链接已经失效，请重新登陆!\");window.opener=null;window.open('','_self');window.close();</script>");
		return;
	}
%>
<script type="text/JavaScript"
	src="http://api.map.baidu.com/api?v=2.0&ak=${ak}"></script>