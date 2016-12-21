/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import wap.wx.dao.ActivityDAO;
import wap.wx.dao.PrizeDAO;
import wap.wx.util.DbConn;

/**
 *
 * @author Administrator
 */
public class ActivityService {

    public void delete(Map<String, String> activity) {
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            new ActivityDAO().delete(activity, conn);
            new PrizeDAO().deleteByActivity(activity);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
