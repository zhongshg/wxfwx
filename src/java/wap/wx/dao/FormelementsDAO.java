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
public class FormelementsDAO {

    public List<Map<Object, Object>> getList() {
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,type,keyss,valuess,evaluess,eplaceholder,isempty,isregular,regular,regulartext from formelements";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("type", rs.getString("type"));
                map.put("keyss", rs.getString("keyss"));
                map.put("valuess", rs.getString("valuess"));
                map.put("evaluess", rs.getString("evaluess"));
                map.put("eplaceholder", rs.getString("eplaceholder"));
                map.put("isempty", rs.getString("isempty"));
                map.put("isregular", rs.getString("isregular"));
                map.put("regular", rs.getString("regular"));
                map.put("regulartext", rs.getString("regulartext"));
                list.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormelementsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return list;
    }

    public Map<Object, Object> getById(Map map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,type,keyss,valuess,evaluess,eplaceholder,isempty,isregular,regular,regulartext from formelements where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("id"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                map = new HashMap<Object, Object>();
                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("type", rs.getString("type"));
                map.put("keyss", rs.getString("keyss"));
                map.put("valuess", rs.getString("valuess"));
                map.put("evaluess", rs.getString("evaluess"));
                map.put("eplaceholder", rs.getString("eplaceholder"));
                map.put("isempty", rs.getString("isempty"));
                map.put("isregular", rs.getString("isregular"));
                map.put("regular", rs.getString("regular"));
                map.put("regulartext", rs.getString("regulartext"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormelementsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "insert into formelements(name,type,keyss,valuess,evaluess,eplaceholder,isempty,isregular,regular,regulartext) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, (String) map.get("name"));
            ptst.setString(2, (String) map.get("type"));
            ptst.setString(3, (String) map.get("keyss"));
            ptst.setString(4, (String) map.get("valuess"));
            ptst.setString(5, (String) map.get("evaluess"));
            ptst.setString(6, (String) map.get("eplaceholder"));
            ptst.setInt(7, (Integer) map.get("isempty"));
            ptst.setInt(8, (Integer) map.get("isregular"));
            ptst.setString(9, (String) map.get("regular"));
            ptst.setString(10, (String) map.get("regulartext"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormelementsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "update formelements set name=?,type=?,keyss=?,valuess=?,evaluess=?,eplaceholder=?,isempty=?,isregular=?,regular=?,regulartext=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, (String) map.get("name"));
            ptst.setString(2, (String) map.get("type"));
            ptst.setString(3, (String) map.get("keyss"));
            ptst.setString(4, (String) map.get("valuess"));
            ptst.setString(5, (String) map.get("evaluess"));
            ptst.setString(6, (String) map.get("eplaceholder"));
            ptst.setInt(7, (Integer) map.get("isempty"));
            ptst.setInt(8, (Integer) map.get("isregular"));
            ptst.setString(9, (String) map.get("regular"));
            ptst.setString(10, (String) map.get("regulartext"));
            ptst.setInt(11, (Integer) map.get("id"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormelementsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "delete from formelements where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, (Integer) map.get("id"));
            int sign = ptst.executeUpdate();
            if (sign > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormelementsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }

        return flag;
    }
}
