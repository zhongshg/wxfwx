/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.ActivityDAO;
import wap.wx.dao.PrizeDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

public class PrizeServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrizeDAO prizeDAO = new PrizeDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(prizeDAO.getConut());
        pu.setList(prizeDAO.getList(pu));
        pu.setServletName("PrizeServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/activity/prize.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrizeDAO prizeDAO = new PrizeDAO();
        String id = request.getParameter("id");
        Map<String, String> prize = new HashMap<String, String>();
        prize.put("id", id);
        prize = prizeDAO.getById(prize);
        List<Map<String, String>> activityList = new ActivityDAO().getList();
        request.setAttribute("prize", prize);
        request.setAttribute("activityList", activityList);
        Forward.forward(request, response, "/back/activity/prizemanage.jsp?page=" + request.getParameter("page"));
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrizeDAO prizeDAO = new PrizeDAO();
        Map<String, String> prize = new HashMap<String, String>();
        //id,codeid,codename,name,num,discounts,aid,remark
        prize.put("id", request.getParameter("id"));
        String[] codes = request.getParameter("codes").split(",");
        prize.put("codeid", codes[0]);
        prize.put("codename", codes[1]);
        prize.put("name", request.getParameter("name"));
        prize.put("num", request.getParameter("num"));
        prize.put("discounts", request.getParameter("discounts"));
        prize.put("aid", request.getParameter("aid"));
        prize.put("remark", request.getParameter("remark"));
        if ("0".equals(prize.get("id"))) {
            prizeDAO.add(prize);
        } else {
            prizeDAO.update(prize);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/PrizeServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> prize = new HashMap<String, String>();
        prize.put("id", request.getParameter("id"));
        new PrizeDAO().delete(prize);
        response.sendRedirect(request.getContextPath() + "/servlet/PrizeServlet?method=getList");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> prize = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            prize.put("id", !"".equals(id) ? id : "0");
            new PrizeDAO().delete(prize);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/PrizeServlet?method=getList");
    }
}
