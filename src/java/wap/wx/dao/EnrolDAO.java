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
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class EnrolDAO {

    public List<Map<String, String>> getList(PageUtil pu, Map<String, String> enrol) {
        List<Map<String, String>> enrolList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid from enrol where sid=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(enrol.get("sid")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                enrol = new HashMap<String, String>();
                enrol.put("id", rs.getString("id"));
                enrol.put("name", rs.getString("name"));
                enrol.put("sex", rs.getString("sex"));
                enrol.put("ages", rs.getString("ages"));
                enrol.put("phone", rs.getString("phone"));
                enrol.put("tel", rs.getString("tel"));
                enrol.put("email", rs.getString("email"));
                enrol.put("education", rs.getString("education"));
                enrol.put("specialty", rs.getString("specialty"));
                enrol.put("comname", rs.getString("comname"));
                enrol.put("remark", rs.getString("remark"));
                enrol.put("times", rs.getString("times"));
                enrol.put("openid", rs.getString("openid"));
                enrol.put("sid", rs.getString("sid"));
                enrolList.add(enrol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return enrolList;
    }

    public int getCount(Map<String, String> enrol) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from enrol where sid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(enrol.get("sid")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public void delete(Map<String, String> enrol) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from enrol where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, enrol.get("id"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void add(Map<String, String> enrol) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into enrol(name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, enrol.get("name"));
            ptst.setInt(2, Integer.parseInt(enrol.get("sex")));
            ptst.setInt(3, Integer.parseInt(enrol.get("ages")));
            ptst.setString(4, enrol.get("phone"));
            ptst.setString(5, enrol.get("tel"));
            ptst.setString(6, enrol.get("email"));
            ptst.setString(7, enrol.get("education"));
            ptst.setString(8, enrol.get("specialty"));
            ptst.setString(9, enrol.get("comname"));
            ptst.setString(10, enrol.get("remark"));
            ptst.setString(11, enrol.get("times"));
            ptst.setString(12, enrol.get("openid"));
            ptst.setInt(13, Integer.parseInt(enrol.get("sid")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public Map<String, String> getById(Map<String, String> enrol) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid from enrol where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(enrol.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                enrol = new HashMap<String, String>();
                enrol.put("id", rs.getString("id"));
                enrol.put("name", rs.getString("name"));
                enrol.put("sex", rs.getString("sex"));
                enrol.put("ages", rs.getString("ages"));
                enrol.put("phone", rs.getString("phone"));
                enrol.put("tel", rs.getString("tel"));
                enrol.put("email", rs.getString("email"));
                enrol.put("education", rs.getString("education"));
                enrol.put("specialty", rs.getString("specialty"));
                enrol.put("comname", rs.getString("comname"));
                enrol.put("remark", rs.getString("remark"));
                enrol.put("times", rs.getString("times"));
                enrol.put("openid", rs.getString("openid"));
                enrol.put("sid", rs.getString("sid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return enrol;
    }

    public Map<String, String> getByOpenId(String openid) {
        Map<String, String> enrol = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid from enrol where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, openid);
            rs = ptst.executeQuery();
            if (rs.next()) {
                enrol = new HashMap<String, String>();
                enrol.put("id", rs.getString("id"));
                enrol.put("name", rs.getString("name"));
                enrol.put("sex", rs.getString("sex"));
                enrol.put("ages", rs.getString("ages"));
                enrol.put("phone", rs.getString("phone"));
                enrol.put("tel", rs.getString("tel"));
                enrol.put("email", rs.getString("email"));
                enrol.put("education", rs.getString("education"));
                enrol.put("specialty", rs.getString("specialty"));
                enrol.put("comname", rs.getString("comname"));
                enrol.put("remark", rs.getString("remark"));
                enrol.put("times", rs.getString("times"));
                enrol.put("openid", rs.getString("openid"));
                enrol.put("sid", rs.getString("sid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return enrol;
    }
}
