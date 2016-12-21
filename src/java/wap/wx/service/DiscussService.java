/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import wap.wx.dao.DiscussDAO;
import wap.wx.util.DbConn;

/**
 *
 * @author Administrator
 */
public class DiscussService {

    public void delete(Map<String, String> discuss) {
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            DiscussDAO discussDAO = new DiscussDAO();
            discussDAO.delete(discuss, conn);
            discussDAO.deleteByTargetId(discuss, conn);
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
