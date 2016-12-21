/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import wap.wx.dao.StoreDAO;
import wap.wx.dao.GatItemsDAO;
import wap.wx.util.DbConn;

/**
 *
 * @author Administrator
 */
public class GgiService {

    public void change(String id, String[] gatitemsckb) {
        GatItemsDAO gatItemsDAO = new GatItemsDAO();
        Map<String, String> gat = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            gat.put("id", id);
            gatItemsDAO.delGgiByGat(gat, conn);
            if (null != gatitemsckb) {
                for (String giid : gatitemsckb) {
                    Map<String, String> ggi = new HashMap<String, String>();
                    ggi.put("gid", id);
                    ggi.put("giid", giid);
                    gatItemsDAO.addGgi(ggi);
                }
            }
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

    public void deleteGat(Map<String, String> gat) {
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            new StoreDAO().delete(gat, conn);
            new GatItemsDAO().delGgiByGat(gat, conn);
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

    public void deleteGatItems(Map<String, String> gatItems) {
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            GatItemsDAO gatItemsDAO = new GatItemsDAO();
            gatItemsDAO.delete(gatItems, conn);
            gatItemsDAO.delGgiByGatItems(gatItems, conn);
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

    public void order(Map<String, String> beforeGatItems, Map<String, String> afterGatItems, GatItemsDAO gatItemsDAO) {
        Map<String, String> gat = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            gatItemsDAO.updateOrders(beforeGatItems, conn);
            gatItemsDAO.updateOrders(afterGatItems, conn);
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
