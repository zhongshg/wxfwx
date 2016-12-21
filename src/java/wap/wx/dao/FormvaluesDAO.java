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
public class FormvaluesDAO {

    public List<Map<Object, Object>> getList(Map map) {
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,fid,eid,keyss,valuess,sid from formvalues where sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("sid"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("fid", rs.getInt("fid"));
                map.put("eid", rs.getInt("eid"));
                map.put("keyss", rs.getString("keyss"));
                map.put("valuess", rs.getString("valuess"));
                map.put("sid", rs.getString("sid"));
                list.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormvaluesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return list;
    }

    public List<Map<Object, Object>> getListValues(Map map) {
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,fid,eid,keyss,valuess,sid from formvalues where fid=? and eid=? and sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("fid"));
            ptst.setInt(2, (Integer) map.get("eid"));
            ptst.setInt(3, (Integer) map.get("sid"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("fid", rs.getInt("fid"));
                map.put("eid", rs.getInt("eid"));
                map.put("keyss", rs.getString("keyss"));
                map.put("valuess", rs.getString("valuess"));
                map.put("sid", rs.getString("sid"));
                list.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormvaluesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return list;
    }

    public Map<Object, Object> getValues(Map map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,fid,eid,keyss,valuess,sid from formvalues where fid=? and eid=? and keyss=? and sid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("fid"));
            ptst.setInt(2, (Integer) map.get("eid"));
            ptst.setString(3, (String) map.get("keyss"));
            ptst.setInt(4, (Integer) map.get("sid"));
            rs = ptst.executeQuery();
            if (rs.next()) {
                map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("fid", rs.getInt("fid"));
                map.put("eid", rs.getInt("eid"));
                map.put("keyss", rs.getString("keyss"));
                map.put("valuess", rs.getString("valuess"));
                map.put("sid", rs.getString("sid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormvaluesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return map;
    }

    public int updateValues(Map map) {
        int i = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update formvalues set valuess=? where fid=? and eid=? and keyss=? and sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, (String) map.get("valuess"));
            ptst.setInt(2, (Integer) map.get("fid"));
            ptst.setInt(3, (Integer) map.get("eid"));
            ptst.setString(4, (String) map.get("keyss"));
            ptst.setInt(5, (Integer) map.get("sid"));
            i = ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FormvaluesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return i;
    }

    public boolean add(Map map) {
        boolean flag = false;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into formvalues(fid,eid,keyss,valuess,sid) values (?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("fid"));
            ptst.setInt(2, (Integer) map.get("eid"));
            ptst.setString(3, (String) map.get("keyss"));
            ptst.setString(4, (String) map.get("valuess"));
            ptst.setInt(5, (Integer) map.get("sid"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormvaluesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "delete from formvalues where fid=? and keyss=? and sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("fid"));
            ptst.setString(2, (String) map.get("keyss"));
            ptst.setInt(3, (Integer) map.get("sid"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormvaluesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return flag;
    }
}
