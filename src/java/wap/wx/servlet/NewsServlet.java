/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.NewsDAO;
import wap.wx.dao.NewstypesDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

public class NewsServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stypeid = request.getParameter("stypeid");
        if (null != stypeid) {
            request.getSession().setAttribute("stypeid", stypeid);
        }
        stypeid = null != request.getSession().getAttribute("stypeid") ? (String) request.getSession().getAttribute("stypeid") : "0";

        String sid = request.getParameter("sid");
        NewsDAO newsDAO = new NewsDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(newsDAO.getConut(sid, stypeid));
        pu.setList(newsDAO.getList(pu, sid, stypeid));
        pu.setServletName("NewsServlet");
        request.setAttribute("sid", sid);
        request.setAttribute("pu", pu);

        List<Map<String, String>> newstypeslist = new NewstypesDAO().getList(sid);
        request.setAttribute("newstypeslist", newstypeslist);
        Forward.forward(request, response, "/back/news/news.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        NewsDAO newsDAO = new NewsDAO();
        String id = request.getParameter("id");
        Map<String, String> news = new HashMap<String, String>();
        news.put("id", id);
        news = newsDAO.getById(news);
        if (null == news.get("orders")) {
            news.put("orders", String.valueOf(newsDAO.getMaxId()));
        }
        request.setAttribute("news", news);
        List<Map<String, String>> newstypeslist = new NewstypesDAO().getList(sid);
        request.setAttribute("newstypeslist", newstypeslist);
        Forward.forward(request, response, "/back/news/newsmanage.jsp?page=" + request.getParameter("page") + "&sid=" + sid);
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String typeid = request.getParameter("typeid");
        NewsDAO newsDAO = new NewsDAO();
        Map<String, String> news = new HashMap<String, String>();
        news.put("id", request.getParameter("id"));
        news.put("name", request.getParameter("name"));
        news.put("author", request.getParameter("author"));
        news.put("source", request.getParameter("source"));
        news.put("img", request.getParameter("img"));
        news.put("briefintro", request.getParameter("briefintro"));
        news.put("content", request.getParameter("content"));
        news.put("times", WxMenuUtils.format.format(new Date()));
        news.put("typeid", typeid);
        news.put("sid", sid);
        news.put("uid", ((Map<String, String>) request.getSession().getAttribute("users")).get("id"));
        news.put("orders", request.getParameter("orders"));
        news.put("viewcounts", "0");

        if ("0".equals(news.get("id"))) {
            newsDAO.add(news);
        } else {
            newsDAO.update(news);
        }
        request.getSession().setAttribute("stypeid", typeid);
        response.sendRedirect(request.getContextPath() + "/servlet/NewsServlet?method=getList&page=" + request.getParameter("page") + "&sid=" + sid);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        Map<String, String> news = new HashMap<String, String>();
        news.put("id", request.getParameter("id"));
        news = newsDAO.getById(news);
        if (!"".equals(news.get("img")) && null != news.get("img")) {
            java.io.File oldFile = new java.io.File(this.getServletContext()
                    .getRealPath(news.get("img")));
            oldFile.delete();
        }
        newsDAO.delete(news);
        response.sendRedirect(request.getContextPath() + "/servlet/NewsServlet?method=getList&sid=" + request.getParameter("sid"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        Map<String, String> news = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            news.put("id", !"".equals(id) ? id : "0");
            news = newsDAO.getById(news);
            if (!"".equals(news.get("img")) && null != news.get("img")) {
                java.io.File oldFile = new java.io.File(this.getServletContext()
                        .getRealPath(news.get("img")));
                oldFile.delete();
            }
            newsDAO.delete(news);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/NewsServlet?method=getList&sid=" + request.getParameter("sid"));
    }
}
