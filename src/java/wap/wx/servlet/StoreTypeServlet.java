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
import wap.wx.dao.StoreTypeDAO;
import wap.wx.service.GgiService;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class StoreTypeServlet extends BaseServlet {

	protected void getList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		Map<String, String> storetype = new HashMap<String, String>();
		storetype.put("sid", sid);
		StoreTypeDAO StoreTypeDAO = new StoreTypeDAO();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(9);
		pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1);
		pu.setMaxSize(StoreTypeDAO.getConut(storetype));
		pu.setList(StoreTypeDAO.getList(pu, storetype));
		pu.setServletName("StoreTypeServlet");
		request.setAttribute("pu", pu);
		request.setAttribute("sid", sid);
		Forward.forward(request, response, "/back/store/storeType.jsp");
	}

	protected void initManage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StoreTypeDAO StoreTypeDAO = new StoreTypeDAO();
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		Map<String, String> storetype = new HashMap<String, String>();
		storetype.put("id", id);
		storetype.put("sid", sid);
		storetype = StoreTypeDAO.getById(storetype);
		request.setAttribute("storetype", storetype);
		Forward.forward(request, response, "/back/store/storeTypemanage.jsp?page=" + request.getParameter("page"));
	}

	protected void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// id,tname,tcode,ts,dr
		StoreTypeDAO StoreTypeDAO = new StoreTypeDAO();
		Map<String, String> storetype = new HashMap<String, String>();
		storetype.put("id", request.getParameter("id"));
		storetype.put("tname", request.getParameter("tname"));
		storetype.put("tcode", request.getParameter("tcode"));
		storetype.put("ts", request.getParameter("ts"));
		storetype.put("dr", request.getParameter("dr"));
		storetype.put("sid", request.getParameter("sid"));
		if ("0".equals(storetype.get("id"))) {
			StoreTypeDAO.add(storetype);
		} else {
			StoreTypeDAO.update(storetype);
		}
		response.sendRedirect(request.getContextPath() + "/servlet/StoreTypeServlet?method=getList&sid="
				+ request.getParameter("sid") + "&page=" + request.getParameter("page"));
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> storetype = new HashMap<String, String>();
		storetype.put("id", request.getParameter("id"));
		storetype = new StoreTypeDAO().getById(storetype);
		if (!"".equals(storetype.get("img")) && null != storetype.get("img")) {
			java.io.File oldFile = new java.io.File(this.getServletContext().getRealPath(storetype.get("img")));
			oldFile.delete();
		}
		new StoreTypeDAO().delete(storetype);
		response.sendRedirect(request.getContextPath() + "/servlet/StoreTypeServlet?method=getList&sid="
				+ request.getParameter("sid"));
	}

	protected void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> storetype = new HashMap<String, String>();
		String ids = request.getParameter("ids");
		String array[] = ids.split(",");
		for (String id : array) {
			storetype.put("id", !"".equals(id) ? id : "0");
			storetype = new StoreTypeDAO().getById(storetype);
			if (!"".equals(storetype.get("img")) && null != storetype.get("img")) {
				java.io.File oldFile = new java.io.File(this.getServletContext().getRealPath(storetype.get("img")));
				oldFile.delete();
			}
			new GgiService().deleteGat(storetype);
		}
		response.sendRedirect(request.getContextPath() + "/servlet/StoreTypeServlet?method=getList&sid="
				+ request.getParameter("sid"));
	}

	protected void initSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GatItemsDAO storeItemsDAO = new GatItemsDAO();
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		Map<String, String> storetype = new LinkedHashMap<String, String>();
		storetype.put("id", id);
		storetype.put("sid", sid);
		List<Map<String, String>> ggiList = storeItemsDAO.getGgiByGatList(storetype);
		List<Map<String, String>> storeItemsList = storeItemsDAO.getList(storetype);
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
		request.setAttribute("store", storetype);
		request.setAttribute("storeItemsList", storeItemsList);
		Forward.forward(request, response, "/back/store/ggi.jsp?page=" + request.getParameter("page"));
	}

	protected void change(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String storeitemsckb[] = request.getParameterValues("storeitemsckb");
		new GgiService().change(id, storeitemsckb);
		Forward.forward(request, response, "/servlet/StoreTypeServlet?method=getList&sid=" + request.getParameter("sid")
				+ "&page=" + request.getParameter("page"));
	}
}
