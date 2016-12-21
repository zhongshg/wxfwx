/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.SendredpacklogsDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class SendredpacklogsServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mch_billno = request.getParameter("mch_billno");
        mch_billno = null != mch_billno ? mch_billno : "";

        SendredpacklogsDAO sendredpacklogsDAO = new SendredpacklogsDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        if (!"".equals(mch_billno)) {
            pu.setMaxSize(sendredpacklogsDAO.getConut(mch_billno));
            pu.setList(sendredpacklogsDAO.getList(pu, mch_billno));
        } else {
            pu.setMaxSize(sendredpacklogsDAO.getConut());
            pu.setList(sendredpacklogsDAO.getList(pu));
        }
        pu.setSign(mch_billno);
        pu.setServletName("SendredpacklogsServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/sendredpack/sendredpacklogs.jsp");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> subscriberdetail = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            if (!"".equals(id)) {
                subscriberdetail.put("id", id);
                new SendredpacklogsDAO().delete(subscriberdetail);
            }
        }
        response.sendRedirect(request.getContextPath() + "/servlet/SendredpacklogsServlet?method=getList");
    }
}
