/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.DiscussDAO;
import wap.wx.service.DiscussService;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class DiscussServlet extends BaseServlet {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String openid = null != request.getParameter("openid") ? request.getParameter("openid") : request.getParameter("sign");
//        Map<String, String> vip = new HashMap<String, String>();
//        vip.put("openid", openid);
//        DiscussDAO discussDAO = new DiscussDAO();
//        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
//        pu.setPageSize(9);
//        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
//        pu.setMaxSize(discussDAO.getConut(vip));
//        pu.setList(discussDAO.getByVipList(pu, vip));
//        pu.setServletName("DiscussServlet");
//        pu.setSign(openid);
//        request.setAttribute("pu", pu);
//        Forward.forward(request, response, "/back/vip/vipdiscuss.jsp");
//    }
    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = null != request.getParameter("id") ? request.getParameter("id") : request.getParameter("sign");
        Map<String, String> gatItems = new HashMap<String, String>();
        gatItems.put("id", id);
        gatItems.put("isreply", "0");
        DiscussDAO discussDAO = new DiscussDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(discussDAO.getConut(gatItems));
        pu.setList(discussDAO.getByGatItemsList(pu, gatItems));
        pu.setServletName("DiscussServlet");
        pu.setSign(id);
        request.setAttribute("pu", pu);
        request.setAttribute("gatItems", gatItems);
        Forward.forward(request, response, "/back/weiform/targetdiscuss.jsp");
    }

    //评论回复
    protected void discussInitReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscussDAO discussDAO = new DiscussDAO();

        //主题id
        String quesid = request.getParameter("quesid");

        //评论id
        String targetid = request.getParameter("id");

        Map<String, String> users = (Map<String, String>) request.getSession().getAttribute("users");

        Map<String, String> discussReply = new HashMap<String, String>();
        discussReply.put("quesid", quesid);
        discussReply.put("id", targetid);
        discussReply.put("username", users.get("username"));
        discussReply.put("isreply", "1");

        List<Map<String, String>> discussReplyList = new DiscussDAO().getByTargetReplyList(discussReply);
        request.setAttribute("discussReply", discussReply);
        request.setAttribute("discussReplyList", discussReplyList);
        Forward.forward(request, response, "/back/weiform/discussreply.jsp?page=" + request.getParameter("page"));
    }

    protected void discussReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscussDAO discussDAO = new DiscussDAO();

        //主题id
        String quesid = request.getParameter("quesid");
        Map<String, String> discussReply = new HashMap<String, String>();
        discussReply.put("openid", request.getParameter("username"));
        discussReply.put("content", request.getParameter("content"));
        discussReply.put("vipid", request.getParameter("targetid"));
        discussReply.put("times", format.format(new Date()));
        discussReply.put("isreply", "1");
        discussReply.put("praisecount", "0");
        discussDAO.add(discussReply);
        Forward.forward(request, response, "/servlet/DiscussServlet?method=getList&id=" + quesid + "&page=" + request.getParameter("page"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> discuss = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            discuss.put("id", !"".equals(id) ? id : "0");
            discuss.put("vipid", !"".equals(id) ? id : "0");
            new DiscussService().delete(discuss);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/DiscussServlet?method=getList&id=" + request.getParameter("openid"));
    }

    protected void delReplyAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> discussReply = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            discussReply.put("id", !"".equals(id) ? id : "0");
            new DiscussDAO().delete(discussReply);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/DiscussServlet?method=discussInitReply&id=" + request.getParameter("targetid") + "&quesid=" + request.getParameter("quesid") + "&page=" + request.getParameter("page"));
    }
}
