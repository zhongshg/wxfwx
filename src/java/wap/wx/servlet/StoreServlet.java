/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wap.wx.dao.GatItemsDAO;
import wap.wx.dao.StoreDAO;
import wap.wx.dao.StoreTypeDAO;
import wap.wx.service.GgiService;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class StoreServlet extends BaseServlet {

	protected void getList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		int page = null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1;
		Map<String, String> store = new HashMap<String, String>();
		store.put("sid", sid);
		StoreDAO storeDao = new StoreDAO();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(10);
		pu.setPage(page);
		pu.setMaxSize(storeDao.getConut(store));
		pu.setList(storeDao.getList(pu, store));
		pu.setServletName("StoreServlet");
		request.setAttribute("pu", pu);
		request.setAttribute("sid", sid);
		Forward.forward(request, response, "/back/store/store.jsp");
	}

	protected void initManage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StoreDAO storeDao = new StoreDAO();
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		Map<String, String> store = new HashMap<String, String>();
		store.put("id", id);
		store.put("sid", sid);
		store = storeDao.getById(store);
		request.setAttribute("store", store);
		List<Map<String, String>> storeTypeList = new StoreTypeDAO().getAllList(sid);
		request.setAttribute("storeTypeList", storeTypeList);
		Forward.forward(request, response, "/back/store/storemanage.jsp?page=" + request.getParameter("page"));
	}

	protected void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr
		StoreDAO storeDao = new StoreDAO();// id,name,times,describer,remark,sid
		Map<String, String> store = new HashMap<String, String>();
		store.put("id", request.getParameter("id"));
		store.put("code", request.getParameter("code"));
		store.put("name", request.getParameter("name"));
		store.put("favorate", request.getParameter("favorate"));
		store.put("content", request.getParameter("content"));
		store.put("jing", request.getParameter("jing"));
		store.put("wei", request.getParameter("wei"));
		store.put("typeid", request.getParameter("typeid"));
		store.put("tel", request.getParameter("tel"));
		store.put("phone", request.getParameter("phone"));
		store.put("img", request.getParameter("img"));
		store.put("provice", request.getParameter("provice"));
		store.put("city", request.getParameter("city"));
		store.put("county", request.getParameter("county"));
		store.put("area", request.getParameter("area"));
		store.put("sid", request.getParameter("sid"));
		store.put("zcode", request.getParameter("zcode"));
		store.put("cityCode", request.getParameter("cityCode"));
		store.put("street", request.getParameter("street"));
		store.put("vid", request.getParameter("vid")==null?"":request.getParameter("vid"));
		if ("0".equals(store.get("id"))) {
			storeDao.add(store);
		} else {
			storeDao.update(store);
		}
		response.sendRedirect(request.getContextPath() + "/servlet/StoreServlet?method=getList&sid="
				+ request.getParameter("sid") + "&page=" + request.getParameter("page"));
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> store = new HashMap<String, String>();
		store.put("id", request.getParameter("id"));
		store = new StoreDAO().getById(store);
		if (!"".equals(store.get("img")) && null != store.get("img")) {
			java.io.File oldFile = new java.io.File(this.getServletContext().getRealPath(store.get("img")));
			oldFile.delete();
		}
		new StoreDAO().delete(store);
		response.sendRedirect(
				request.getContextPath() + "/servlet/StoreServlet?method=getList&sid=" + request.getParameter("sid"));
	}

	protected void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> store = new HashMap<String, String>();
		String ids = request.getParameter("ids");
		String array[] = ids.split(",");
		for (String id : array) {
			store.put("id", !"".equals(id) ? id : "0");
			store = new StoreDAO().getById(store);
			if (!"".equals(store.get("img")) && null != store.get("img")) {
				java.io.File oldFile = new java.io.File(this.getServletContext().getRealPath(store.get("img")));
				oldFile.delete();
			}
			//new GgiService().deleteGat(store);
		}
		response.sendRedirect(
				request.getContextPath() + "/servlet/StoreServlet?method=getList&sid=" + request.getParameter("sid"));
	}

	protected void initSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GatItemsDAO storeItemsDAO = new GatItemsDAO();
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		Map<String, String> store = new LinkedHashMap<String, String>();
		store.put("id", id);
		store.put("sid", sid);
		List<Map<String, String>> ggiList = storeItemsDAO.getGgiByGatList(store);
		List<Map<String, String>> storeItemsList = storeItemsDAO.getList(store);
		for (Map<String, String> storeItems : storeItemsList) {
			storeItems.put("selects", "0");
			for (Map<String, String> ggi : ggiList) {
				if (storeItems.get("id").equals(ggi.get("giid"))) {
					storeItems.remove("selects");
					storeItems.put("selects", "1");
					break;
				}
			}
		}
		request.setAttribute("store", store);
		request.setAttribute("storeItemsList", storeItemsList);
		Forward.forward(request, response, "/back/store/ggi.jsp?page=" + request.getParameter("page"));
	}

	protected void change(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String storeitemsckb[] = request.getParameterValues("storeitemsckb");
		new GgiService().change(id, storeitemsckb);
		Forward.forward(request, response, "/servlet/StoreServlet?method=getList&sid=" + request.getParameter("sid")
				+ "&page=" + request.getParameter("page"));
	}
}
