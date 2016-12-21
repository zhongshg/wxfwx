/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.WxsDAO;

/**
 * @author Administrator
 */
@WebServlet(name = "BaseServlet", urlPatterns = {"/BaseServlet"})
public class BaseServlet extends HttpServlet {

    public Map<String, String> wx = new HashMap<String, String>();
    public static Map<String, String> user;
    private WxsDAO wxsDAO = new WxsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //全局变量区初始化
        user = (Map<String, String>) request.getSession().getAttribute("users");
        if (null != user) {
            wx.put("id", "1");
            wx = wxsDAO.getById(wx);
            if (null == wx.get("id")) {
                wx.put("id", "0");
            }

            //微信控制第三方程序共享数据
            request.getSession().setAttribute("wx", wx);
        }

        String methodName = request.getParameter("method");
        try {
            Method method = this.getClass().getDeclaredMethod(
                    methodName,
                    new Class[]{HttpServletRequest.class,
                HttpServletResponse.class});
            method.invoke(this, new Object[]{request, response});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
