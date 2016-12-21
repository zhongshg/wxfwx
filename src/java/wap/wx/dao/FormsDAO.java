/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import wap.wx.util.DbConn;

/**
 *
 * @author Administrator
 */
public class FormsDAO {

    public List<Map<Object, Object>> getList() {
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,eid,ip,times,aid from forms";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("eid", rs.getString("eid"));
                map.put("ip", rs.getString("ip"));
                map.put("times", rs.getString("times"));
                map.put("aid", rs.getInt("aid"));
                list.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return list;
    }

    public Map<Object, Object> getById(Map map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,eid,ip,times,aid from forms where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("id"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("eid", rs.getString("eid"));
                map.put("ip", rs.getString("ip"));
                map.put("times", rs.getString("times"));
                map.put("aid", rs.getInt("aid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return map;
    }

    public boolean add(Map map) {
        boolean flag = false;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into forms(name,eid,ip,times,aid) values (?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, (String) map.get("name"));
            ptst.setString(2, (String) map.get("eid"));
            ptst.setString(3, (String) map.get("ip"));
            ptst.setString(4, (String) map.get("times"));
            ptst.setInt(5, (Integer) map.get("aid"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return flag;
    }

    public boolean update(Map map) {
        boolean flag = false;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update forms set name=?,eid=?,ip=?,times=?,aid=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, (String) map.get("name"));
            ptst.setString(2, (String) map.get("eid"));
            ptst.setString(3, (String) map.get("ip"));
            ptst.setString(4, (String) map.get("times"));
            ptst.setInt(5, (Integer) map.get("aid"));
            ptst.setInt(6, (Integer) map.get("id"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return flag;
    }

    public boolean delete(Map map) {
        boolean flag = false;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from forms where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("id"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return flag;
    }
}
