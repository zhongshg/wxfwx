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
public class ForumDAO {

    public List<Map<String, String>> getByTargetList(PageUtil pu, Map<String, String> forum) {
        List<Map<String, String>> forumList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,target,ip,times,sid from forum where target=? and sid=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(forum.get("target")));
            ptst.setInt(2, Integer.parseInt(forum.get("sid")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                forum = new HashMap<String, String>();
                forum.put("id", rs.getString("id"));
                forum.put("openid", rs.getString("openid"));
                forum.put("target", rs.getString("target"));
                forum.put("ip", rs.getString("ip"));
                forum.put("times", rs.getString("times"));
                forumList.add(forum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return forumList;
    }

    public int getConut(Map<String, String> forum) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from forum where target=? and sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(forum.get("target")));
            ptst.setInt(2, Integer.parseInt(forum.get("sid")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public void add(Map<String, String> forum) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into forum(openid,target,ip,times,sid) values (?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, forum.get("openid"));
            ptst.setString(2, forum.get("target"));
            ptst.setString(3, forum.get("ip"));
            ptst.setString(4, forum.get("times"));
            ptst.setString(5, forum.get("sid"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public Map<String, String> getByOT(Map<String, String> forum) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,target,ip,times,sid from forum where openid=? and target=? and sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, forum.get("openid"));
            ptst.setString(2, forum.get("target"));
            ptst.setString(3, forum.get("sid"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                forum = new HashMap<String, String>();
                forum.put("id", rs.getString("id"));
                forum.put("openid", rs.getString("openid"));
                forum.put("target", rs.getString("target"));
                forum.put("ip", rs.getString("ip"));
                forum.put("times", rs.getString("times"));
                forum.put("sid", rs.getString("sid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return forum;
    }

    public void delete(Map<String, String> forum) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from forum where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, forum.get("id"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
