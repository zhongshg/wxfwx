/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.SendredpackDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class SendredpackServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SendredpackDAO sendredpackDAO = new SendredpackDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(sendredpackDAO.getConut());
        pu.setList(sendredpackDAO.getList(pu));
        pu.setServletName("SendredpackServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/sendredpack/sendredpack.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SendredpackDAO sendredpackDAO = new SendredpackDAO();
        String id = request.getParameter("id");
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", id);
        activity = sendredpackDAO.getById(activity);
        request.setAttribute("activity", activity);
        Forward.forward(request, response, "/back/sendredpack/sendredpackmanage.jsp?page=" + request.getParameter("page"));
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SendredpackDAO sendredpackDAO = new SendredpackDAO();
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", request.getParameter("id"));
        activity.put("name", request.getParameter("name"));
        activity.put("type", request.getParameter("type"));
        activity.put("nick_name", request.getParameter("nick_name"));
        activity.put("send_name", request.getParameter("send_name"));
        activity.put("money", request.getParameter("money"));
        activity.put("num", request.getParameter("num"));
        activity.put("wishing", request.getParameter("wishing"));
        activity.put("remark", request.getParameter("remark"));
        activity.put("img", request.getParameter("img"));
        activity.put("starttime", request.getParameter("starttime"));
        activity.put("endtime", request.getParameter("endtime"));

        //订单号
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String mch_billno = timeStamp + new Random().nextInt(10000);
        activity.put("mch_billno", mch_billno);
        if ("0".equals(activity.get("id"))) {
            sendredpackDAO.add(activity);
        } else {
            sendredpackDAO.update(activity);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/SendredpackServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", request.getParameter("id"));
        //删除图片
//        activity = new SendredpackDAO().getById(activity);
//        if (!"".equals(activity.get("img")) && null != activity.get("img")) {
//            java.io.File oldFile = new java.io.File(this.getServletContext()
//                    .getRealPath(activity.get("img")));
//            oldFile.delete();
//        }
        new SendredpackDAO().delete(activity);
        response.sendRedirect(request.getContextPath() + "/servlet/SendredpackServlet?method=getList");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> activity = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            activity.put("id", !"".equals(id) ? id : "0");
//            activity = new SendredpackDAO().getById(activity);
//            if (!"".equals(activity.get("img")) && null != activity.get("img")) {
//                java.io.File oldFile = new java.io.File(this.getServletContext()
//                        .getRealPath(activity.get("img")));
//                oldFile.delete();
//            }
            new SendredpackDAO().delete(activity);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/SendredpackServlet?method=getList");
    }
}
