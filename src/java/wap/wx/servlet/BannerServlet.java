package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wap.wx.dao.BannerDao;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class BannerServlet extends BaseServlet {

	protected void getList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		int page = null != request.getParameter("page") && !"null".equals(request.getParameter("page"))
				? Integer.parseInt(request.getParameter("page")) : 1;
		Map<String, String> banner = new HashMap<String, String>();
		banner.put("sid", sid);
		BannerDao BannerDao = new BannerDao();
		PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
		pu.setPageSize(30);
		pu.setPage(page);
		pu.setMaxSize(BannerDao.getConut(banner));
		pu.setList(BannerDao.getList(Integer.parseInt(sid)));
		pu.setServletName("BannerServlet");
		request.setAttribute("pu", pu);
		request.setAttribute("sid", sid);
		Forward.forward(request, response, "/back/store/banner.jsp");
	}

	protected void initManage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BannerDao BannerDao = new BannerDao();
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		Map<String, String> banner = new HashMap<String, String>();
		banner.put("id", id);
		banner.put("sid", sid);
		banner = BannerDao.getById(banner);
		request.setAttribute("banner", banner);
		Forward.forward(request, response, "/back/store/bannermanage.jsp?page=" + request.getParameter("page"));
	}

	protected void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr
		BannerDao BannerDao = new BannerDao();// id,name,times,describer,remark,sid
		Map<String, String> banner = new HashMap<String, String>();
		banner.put("id", request.getParameter("id"));
		banner.put("code", request.getParameter("code"));
		banner.put("dr", request.getParameter("dr"));
		banner.put("name", request.getParameter("name"));
		banner.put("img", request.getParameter("img"));
		banner.put("sid", request.getParameter("sid"));
		if ("0".equals(banner.get("id"))) {
			BannerDao.add(banner);
		} else {
			BannerDao.update(banner);
		}
		response.sendRedirect(request.getContextPath() + "/servlet/BannerServlet?method=getList&sid="
				+ request.getParameter("sid") + "&page=" + request.getParameter("page"));
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> banner = new HashMap<String, String>();
		banner.put("id", request.getParameter("id"));
		banner = new BannerDao().getById(banner);
		if (!"".equals(banner.get("img")) && null != banner.get("img")) {
			java.io.File oldFile = new java.io.File(this.getServletContext().getRealPath(banner.get("img")));
			oldFile.delete();
		}
		new BannerDao().delete(banner);
		response.sendRedirect(
				request.getContextPath() + "/servlet/BannerServlet?method=getList&sid=" + request.getParameter("sid"));
	}

	protected void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> banner = new HashMap<String, String>();
		String ids = request.getParameter("ids");
		String array[] = ids.split(",");
		for (String id : array) {
			banner.put("id", !"".equals(id) ? id : "0");
			banner = new BannerDao().getById(banner);
			if (!"".equals(banner.get("img")) && null != banner.get("img")) {
				java.io.File oldFile = new java.io.File(this.getServletContext().getRealPath(banner.get("img")));
				oldFile.delete();
			}
			// new GgiService().deleteGat(banner);
		}
		response.sendRedirect(
				request.getContextPath() + "/servlet/BannerServlet?method=getList&sid=" + request.getParameter("sid"));
	}
}
