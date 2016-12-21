/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.SubscriberDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SubscriberServlet", urlPatterns = {"/SubscriberServlet"})
public class SubscriberServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubscriberDAO subscriberDAO = new SubscriberDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(subscriberDAO.getCount());
        pu.setList(subscriberDAO.getList(pu));
        pu.setServletName("SubscriberServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/subscriber/subscriber.jsp");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> subscriber = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            subscriber.put("id", id);
            new SubscriberDAO().delete(subscriber);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/SubscriberServlet?method=getList");
    }

    protected void isvip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String wxsid = request.getParameter("wxsid");
        int isvip = Integer.parseInt(request.getParameter("isvip"));
        new SubscriberDAO().updateVip(openid, isvip);
        response.sendRedirect(request.getContextPath() + "/servlet/SubscriberServlet?method=getList&page=" + request.getParameter("page"));
    }
}
