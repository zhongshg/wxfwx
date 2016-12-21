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
import wap.wx.dao.ForumDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class ForumServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sign = request.getParameter("sign");
        String gatitemsid = null != sign ? sign.split(",")[0] : request.getParameter("id");
        String sid = null != sign ? sign.split(",")[1] : request.getParameter("sid");
        Map<String, String> forum = new HashMap<String, String>();
        forum.put("target", gatitemsid);
        forum.put("sid", sid);
        ForumDAO forumDAO = new ForumDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(forumDAO.getConut(forum));
        pu.setList(forumDAO.getByTargetList(pu, forum));
        pu.setServletName("ForumServlet");
        pu.setSign(gatitemsid + "," + sid);
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/weiform/targetforum.jsp");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> forum = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            forum.put("id", !"".equals(id) ? id : "0");
            new ForumDAO().delete(forum);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/ForumServlet?method=getList&sign=" + request.getParameter("id"));
    }
}
