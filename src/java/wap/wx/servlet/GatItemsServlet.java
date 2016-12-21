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
import wap.wx.dao.GatItemsDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.service.GgiService;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

public class GatItemsServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        Map<String, String> gatItems = new HashMap<String, String>();
        gatItems.put("sid", sid);
        GatItemsDAO gatItemsDAO = new GatItemsDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(gatItemsDAO.getConut(gatItems));
        pu.setList(gatItemsDAO.getList(pu, gatItems));
        pu.setServletName("GatItemsServlet");
        request.setAttribute("pu", pu);
        request.setAttribute("sid", sid);
        Forward.forward(request, response, "/back/gat/gatitems.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GatItemsDAO gatItemsDAO = new GatItemsDAO();
        String id = request.getParameter("id");
        String sid = request.getParameter("sid");
        Map<String, String> gatItems = new HashMap<String, String>();
        gatItems.put("id", id);
        gatItems.put("sid", sid);
        gatItems.put("orders", String.valueOf(gatItemsDAO.getMaxId()));
        gatItems = gatItemsDAO.getById(gatItems);
        request.setAttribute("gatitems", gatItems);
        Forward.forward(request, response, "/back/gat/gatitemsmanage.jsp");
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GatItemsDAO gatItemsDAO = new GatItemsDAO();
        Map<String, String> gatItems = new HashMap<String, String>();
//        id,name,img,describer,sid,orders,praisecount,author,times,state
        gatItems.put("id", request.getParameter("id"));
        gatItems.put("name", request.getParameter("name"));
        gatItems.put("img", request.getParameter("img"));
        gatItems.put("describer", request.getParameter("describer"));
        gatItems.put("sid", request.getParameter("sid"));
        gatItems.put("orders", request.getParameter("orders"));
        gatItems.put("praisecount", "0");
        gatItems.put("author", "");
        gatItems.put("times", WxMenuUtils.format.format(new Date()));
        gatItems.put("state", "0");
        if ("0".equals(gatItems.get("id"))) {
            gatItemsDAO.add(gatItems);
        } else {
            gatItemsDAO.update(gatItems);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/GatItemsServlet?method=getList&sid=" + request.getParameter("sid"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> gatItems = new HashMap<String, String>();
        gatItems.put("id", request.getParameter("id"));
        gatItems = new GatItemsDAO().getById(gatItems);
        if (!"".equals(gatItems.get("img")) && null != gatItems.get("img")) {
            java.io.File oldFile = new java.io.File(this.getServletContext()
                    .getRealPath(gatItems.get("img")));
            oldFile.delete();
        }
        new GgiService().deleteGatItems(gatItems);
        response.sendRedirect(request.getContextPath() + "/servlet/GatItemsServlet?method=getList&sid=" + request.getParameter("sid"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> gatItems = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            gatItems.put("id", !"".equals(id) ? id : "0");
            gatItems = new GatItemsDAO().getById(gatItems);
            if (!"".equals(gatItems.get("img")) && null != gatItems.get("img")) {
                java.io.File oldFile = new java.io.File(this.getServletContext()
                        .getRealPath(gatItems.get("img")));
                oldFile.delete();
            }
            new GgiService().deleteGatItems(gatItems);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/GatItemsServlet?method=getList&sid=" + request.getParameter("sid"));
    }

    protected void order(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GatItemsDAO gatItemsDAO = new GatItemsDAO();
        GgiService ggiService = new GgiService();
        String gatid = request.getParameter("gatid");
        String id = request.getParameter("id");
        String dir = request.getParameter("dir");
        String sid = request.getParameter("sid");
        Map<String, String> gat = new HashMap<String, String>();
        gat.put("id", gatid);
        gat.put("sid", sid);
//        List<Map<String, String>> gatItemsList = gatItemsDAO.getByGatList(gat);
        List<Map<String, String>> gatItemsList = gatItemsDAO.getList(gat);
        for (int i = 0; i < gatItemsList.size(); i++) {
            Map<String, String> gatItems = gatItemsList.get(i);
            if (id.equals(gatItems.get("id"))) {
                String orders = gatItems.get("orders");
                if ("0".equals(dir) && 0 < i) {
                    Map<String, String> beforeGatItems = gatItemsList.get(i - 1);
                    gatItems.put("orders", beforeGatItems.get("orders"));
                    beforeGatItems.put("orders", orders);
                    ggiService.order(beforeGatItems, gatItems, gatItemsDAO);
                }
                if ("1".equals(dir) && i < gatItemsList.size() - 1) {
                    Map<String, String> afterGatItems = gatItemsList.get(i + 1);
                    gatItems.put("orders", afterGatItems.get("orders"));
                    afterGatItems.put("orders", orders);
                    ggiService.order(gatItems, afterGatItems, gatItemsDAO);
                }
                break;
            }
        }
        response.sendRedirect(request.getContextPath() + "/servlet/GatServlet?method=initSelect&id=" + gatid + "&sid=" + sid + "&page=" + request.getParameter("page"));
    }
}
