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
import wap.wx.dao.ActivityDAO;
import wap.wx.dao.OpenprizeDAO;
import wap.wx.dao.PrizeDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class OpenprizeServlet extends BaseServlet {

    //临时
    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = null != request.getParameter("sid") ? request.getParameter("sid") : request.getParameter("sign");
        OpenprizeDAO openprizeDAO = new OpenprizeDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(15);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setSign(sid);
        pu.setMaxSize(openprizeDAO.getCount(sid));
        pu.setList(openprizeDAO.getList(pu, sid));
        pu.setServletName("OpenprizeServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/subscriber/pointlist.jsp");
    }

//    protected void getList(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String sid = null != request.getParameter("sid") ? request.getParameter("sid") : request.getParameter("sign");
//        Map<String, String> activity = new HashMap<String, String>();
//        activity.put("id", sid);
//        OpenprizeDAO openprizeDAO = new OpenprizeDAO();
//        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
//        pu.setPageSize(9);
//        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
//        pu.setSign(sid);
//        pu.setMaxSize(openprizeDAO.getCount(activity));
//        pu.setList(openprizeDAO.getList(pu, activity));
//        pu.setServletName("OpenprizeServlet");
//        request.setAttribute("pu", pu);
//        Forward.forward(request, response, "/back/subscriber/pointlist.jsp");
//    }
    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> openprize = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            openprize.put("id", !"".equals(id) ? id : "0");
            new OpenprizeDAO().delete(openprize);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/OpenprizeServlet?method=getList&sid=" + request.getParameter("sign"));
    }

    protected void exp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> openprize = new HashMap<String, String>();
        openprize.put("id", request.getParameter("id"));
        openprize.put("isexp", "1");
        new OpenprizeDAO().updateExp(openprize);
        response.sendRedirect(request.getContextPath() + "/servlet/OpenprizeServlet?method=getList&page=" + request.getParameter("page") + "&sid=" + request.getParameter("sign"));
    }

    protected void getPointStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = null != request.getParameter("sid") ? request.getParameter("sid") : "0";
        Map<Map<String, String>, List<Map<String, String>>> map = new LinkedHashMap<Map<String, String>, List<Map<String, String>>>();
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("sid", sid);
        List<Map<String, String>> activityList = new ActivityDAO().getBySidList(activity);
        for (Map<String, String> a : activityList) {
            List<Map<String, String>> prizeList = new PrizeDAO().getByActivityList(a);
            for (Map<String, String> p : prizeList) {
                Map<String, String> openprize = new HashMap<String, String>();
                openprize.put("pid", p.get("id"));
                openprize.put("state", "1");
                p.put("pointcount", String.valueOf(new OpenprizeDAO().getPrizePointCount(a, openprize)));
            }
            map.put(a, prizeList);
        }
        request.setAttribute("map", map);
        Forward.forward(request, response, "/back/subscriber/pointstatistics.jsp");
    }
}
