/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.ActivityDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.service.ActivityService;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

public class ActivityServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(activityDAO.getConut());
        pu.setList(activityDAO.getList(pu));
        pu.setServletName("ActivityServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/activity/activity.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();
        String id = request.getParameter("id");
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", id);
        activity = activityDAO.getById(activity);
        request.setAttribute("activity", activity);
        Forward.forward(request, response, "/back/activity/activitymanage.jsp?page=" + request.getParameter("page"));
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();
        Map<String, String> activity = new HashMap<String, String>();
        //id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent
        activity.put("id", request.getParameter("id"));
        activity.put("activitykey", request.getParameter("activitykey"));
        activity.put("name", request.getParameter("name"));
        activity.put("img", request.getParameter("img"));
        activity.put("counts", request.getParameter("counts"));
        activity.put("taketimes", request.getParameter("taketimes"));
        activity.put("pointtimes", request.getParameter("pointtimes"));
        activity.put("starttime", !"".equals(request.getParameter("starttime")) ? request.getParameter("starttime") : WxMenuUtils.format.format(new Date()));
        activity.put("endtime", !"".equals(request.getParameter("endtime")) ? request.getParameter("endtime") : WxMenuUtils.format.format(new Date()));
        activity.put("state", request.getParameter("state"));
        activity.put("remark", request.getParameter("remark"));
        activity.put("successpoint", request.getParameter("successpoint"));
        activity.put("nopoint", request.getParameter("nopoint"));
        activity.put("overpoint", request.getParameter("overpoint"));
        activity.put("endpoint", request.getParameter("endpoint"));
        activity.put("sid", request.getParameter("sid"));
        activity.put("changetimes", request.getParameter("changetimes"));
        activity.put("changepercent", request.getParameter("changepercent"));
        if ("0".equals(activity.get("id"))) {
            activityDAO.add(activity);
        } else {
            activityDAO.update(activity);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/ActivityServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", request.getParameter("id"));
        //删除图片
        activity = new ActivityDAO().getById(activity);
        if (!"".equals(activity.get("img")) && null != activity.get("img")) {
            java.io.File oldFile = new java.io.File(this.getServletContext()
                    .getRealPath(activity.get("img")));
            oldFile.delete();
        }
        new ActivityService().delete(activity);
        response.sendRedirect(request.getContextPath() + "/servlet/ActivityServlet?method=getList");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> activity = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            activity.put("id", !"".equals(id) ? id : "0");
            activity = new ActivityDAO().getById(activity);
            if (!"".equals(activity.get("img")) && null != activity.get("img")) {
                java.io.File oldFile = new java.io.File(this.getServletContext()
                        .getRealPath(activity.get("img")));
                oldFile.delete();
            }
            new ActivityService().delete(activity);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/ActivityServlet?method=getList");
    }
}
