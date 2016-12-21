package wap.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wap.wx.dao.ScoreDao;
import wap.wx.dao.StoreDAO;
import wap.wx.dao.WxsDAO;
import wap.wx.util.PageUtil;

/**
 * 看见味道微信平台前端通用Servlet
 * 
 */
public class IndexServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	
	protected void indexStore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		//String tid = request.getParameter("tid");
		String district = request.getParameter("district");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		String orders = "1";
		int page = null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1;
		StoreDAO storeDao = new StoreDAO();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(10);
		pu.setPage(page);
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		List<Map<String, String>> list = storeDao.getIndexStore(pu, sid, district, lat, lon, orders);
		String listStr = getJson(path, list);
		out.write(listStr);
		out.flush();
		out.close();
	}
	
	protected void getList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		String tid = request.getParameter("tid");
		String district = request.getParameter("district");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		String orders = request.getParameter("orders");
		int page = null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1;
		StoreDAO storeDao = new StoreDAO();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(10);
		pu.setPage(page);
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		List<Map<String, String>> list = storeDao.getList(pu, sid, tid, district, lat, lon, orders);
		out.write(getJson(path, list));
		out.flush();
		out.close();
	}

	private String getJson(String path, List<Map<String, String>> list) {
		StringBuffer storeJson = new StringBuffer(125);
		// id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr,zcode
		storeJson.append("[");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> temp = list.get(i);
				storeJson.append("{\"name\":\"").append(temp.get("name")).append("\",");
				storeJson.append("\"code\":\"").append(temp.get("code")).append("\",");
				storeJson.append("\"id\":\"").append(temp.get("id")).append("\",");
				storeJson.append("\"img\":\"").append(path + temp.get("img")).append("\",");
				storeJson.append("\"jing\":\"").append(temp.get("jing")).append("\",");
				storeJson.append("\"wei\":\"").append(temp.get("wei")).append("\",");
				storeJson.append("\"area\":\"").append(temp.get("area")).append("\",");
				storeJson.append("\"tel\":\"").append(temp.get("tel")).append("\",");
				storeJson.append("\"juli\":\"").append(temp.get("juli")).append("\",");
				storeJson.append("\"phone\":\"").append(temp.get("phone")).append("\"");
				if (i != list.size() - 1) {
					storeJson.append("},");
				} else {
					storeJson.append("}");
				}
			}
		}
		storeJson.append("]");
		return storeJson.toString();
	}

	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		String sr = request.getParameter("name");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		int page = null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1;
		StoreDAO storeDao = new StoreDAO();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(10);
		pu.setPage(page);
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		List<Map<String, String>> list = storeDao.search(pu, sid, sr, lat, lon);
		out.write(getJson(path, list));
		out.flush();
		out.close();
	}
	
	public void score(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String content = request.getParameter("content");
		String pingfen = request.getParameter("pingfen");
		String sid = request.getParameter("sid");
		String tid = request.getParameter("tid");
		String openid = request.getParameter("openid");
		ScoreDao scoreDao = new ScoreDao();
		//校验用户是否评价过
		boolean flag = scoreDao.get(openid);
		int msg = 0;
		if(flag){
			flag = scoreDao.add(content, Integer.parseInt(sid), openid, "1", Integer.parseInt(tid),
				Integer.parseInt(pingfen));
			if (flag) {
				msg = 1;
			}
		}else{
			msg = 2;
		}
		response.sendRedirect("./fore/store/storeView.jsp?msg=" + msg + "&tid=" + tid);
	}
	
	
	protected void getScoreList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String sid = request.getParameter("sid");
		String tid = request.getParameter("tid");
		int page = null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1;
		ScoreDao scoreDao = new ScoreDao();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(10);
		pu.setPage(page);
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		List<Map<String, String>> list = scoreDao.getList(pu, Integer.parseInt(tid));
		out.write(getScoreJson(path, list));
		out.flush();
		out.close();
	}
	
	private String getScoreJson(String path, List<Map<String, String>> list) {
		StringBuffer storeJson = new StringBuffer(125);
		// //id,content,sid,dr,ts,openid,uid,level,tid
		storeJson.append("[");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> temp = list.get(i);
				storeJson.append("{\"content\":\"").append(temp.get("content")).append("\",");
				storeJson.append("\"id\":\"").append(temp.get("id")).append("\",");
				storeJson.append("\"ts\":\"").append(temp.get("ts")).append("\",");
				storeJson.append("\"openid\":\"").append(temp.get("openid")).append("\",");
				storeJson.append("\"level\":\"").append(temp.get("level")).append("\",");
				storeJson.append("\"tid\":\"").append(temp.get("tid")).append("\"");
				if (i != list.size() - 1) {
					storeJson.append("},");
				} else {
					storeJson.append("}");
				}
			}
		}
		storeJson.append("]");
		return storeJson.toString();
	}
	
	// 网页获取openid
/*	protected void share(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetopenid = request.getParameter("targetopenid");// 分享目标
		if (null == targetopenid) {
			targetopenid = "";
		}
		String sid = request.getParameter("wx");
		wx = new HashMap<String, String>();
		wx.put("id", sid);
		wx = new WxsDAO().getById(wx);
		String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath()
				+ "/IndexServlet?method=share_do&wxsid=" + wx.get("id") + "&targetopenid=" + targetopenid;
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid")
				+ "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8")
				+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
	}*/

	// 回调url
	protected void go(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wxsid = request.getParameter("wx");
		wx.put("id", wxsid);
		wx = new WxsDAO().getById(wx);
		String openid = request.getParameter("openid");
		System.out.println("openid="+openid);
		if(wx !=null && wx.get("id") != null && openid != null){
			response.sendRedirect("/fore/store/login.jsp?openid=" + openid + "&wx=" + wx.get("id"));
		}
	}
}
