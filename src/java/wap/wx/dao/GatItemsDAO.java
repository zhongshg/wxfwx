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
public class GatItemsDAO {

    public List<Map<String, String>> getList(Map<String, String> gatitems) {
        List<Map<String, String>> gatitemsList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,img,describer,sid,orders,praisecount,author,times,state from gatitems where sid=? order by orders desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("sid")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                gatitems = new HashMap<String, String>();
                gatitems.put("id", rs.getString("id"));
                gatitems.put("name", rs.getString("name"));
                gatitems.put("img", rs.getString("img"));
                gatitems.put("describer", rs.getString("describer"));
                gatitems.put("sid", rs.getString("sid"));
                gatitems.put("orders", rs.getString("orders"));
                gatitems.put("praisecount", rs.getString("praisecount"));
                gatitems.put("author", rs.getString("author"));
                gatitems.put("times", rs.getString("times"));
                gatitems.put("state", rs.getString("state"));
                gatitemsList.add(gatitems);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return gatitemsList;
    }

    public List<Map<String, String>> getList(PageUtil pu, Map<String, String> gatitems) {
        List<Map<String, String>> gatitemsList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select g.id,name,img,describer,sid,orders,praisecount,author,g.times,state,s.nickname nickname from gatitems g left join subscriber s on g.author=s.openid where sid=? order by orders desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("sid")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                gatitems = new HashMap<String, String>();
                gatitems.put("id", rs.getString("id"));
                gatitems.put("name", rs.getString("name"));
                gatitems.put("img", rs.getString("img"));
                gatitems.put("describer", rs.getString("describer"));
                gatitems.put("sid", rs.getString("sid"));
                gatitems.put("orders", rs.getString("orders"));
                gatitems.put("praisecount", rs.getString("praisecount"));
                gatitems.put("author", rs.getString("author"));
                gatitems.put("times", rs.getString("times"));
                gatitems.put("state", rs.getString("state"));
                gatitems.put("nickname", rs.getString("nickname"));
                gatitemsList.add(gatitems);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return gatitemsList;
    }

    public int getConut(Map<String, String> gatitems) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from gatitems where sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("sid")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getMaxId() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select max(id) count from gatitems";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsmDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> gatitems) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,img,describer,sid,orders,praisecount,author,times,state from gatitems where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                gatitems = new HashMap<String, String>();
                gatitems.put("id", rs.getString("id"));
                gatitems.put("name", rs.getString("name"));
                gatitems.put("img", rs.getString("img"));
                gatitems.put("describer", rs.getString("describer"));
                gatitems.put("sid", rs.getString("sid"));
                gatitems.put("orders", rs.getString("orders"));
                gatitems.put("praisecount", rs.getString("praisecount"));
                gatitems.put("author", rs.getString("author"));
                gatitems.put("times", rs.getString("times"));
                gatitems.put("state", rs.getString("state"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return gatitems;
    }

    public List<Map<String, String>> getByGatList(Map<String, String> gat) {
        List<Map<String, String>> gatitemsList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,img,describer,sid,orders,praisecount,author,times,state from gatitems where id in (select giid from ggi where gid=? ) order by orders desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gat.get("id")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> gatitems = new HashMap<String, String>();
                gatitems.put("id", rs.getString("id"));
                gatitems.put("name", rs.getString("name"));
                gatitems.put("img", rs.getString("img"));
                gatitems.put("describer", rs.getString("describer"));
                gatitems.put("sid", rs.getString("sid"));
                gatitems.put("orders", rs.getString("orders"));
                gatitems.put("praisecount", rs.getString("praisecount"));
                gatitems.put("author", rs.getString("author"));
                gatitems.put("times", rs.getString("times"));
                gatitems.put("state", rs.getString("state"));
                gatitemsList.add(gatitems);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return gatitemsList;
    }

    public void add(Map<String, String> gatitems) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into gatitems(name,img,describer,sid,orders,praisecount,author,times,state) values (?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, gatitems.get("name"));
            ptst.setString(2, gatitems.get("img"));
            ptst.setString(3, gatitems.get("describer"));
            ptst.setInt(4, Integer.parseInt(gatitems.get("sid")));
            ptst.setInt(5, Integer.parseInt(gatitems.get("orders")));
            ptst.setInt(6, Integer.parseInt(gatitems.get("praisecount")));
            ptst.setString(7, gatitems.get("author"));
            ptst.setString(8, gatitems.get("times"));
            ptst.setInt(9, Integer.parseInt(gatitems.get("state")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> gatitems) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update gatitems set name=?,img=?,describer=?,sid=?,author=?,times=?,state=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, gatitems.get("name"));
            ptst.setString(2, gatitems.get("img"));
            ptst.setString(3, gatitems.get("describer"));
            ptst.setInt(4, Integer.parseInt(gatitems.get("sid")));
            ptst.setString(5, gatitems.get("author"));
            ptst.setString(6, gatitems.get("times"));
            ptst.setInt(7, Integer.parseInt(gatitems.get("state")));
            ptst.setInt(8, Integer.parseInt(gatitems.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateOrders(Map<String, String> gatitems, Connection conn) {
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update gatitems set orders=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("orders")));
            ptst.setInt(2, Integer.parseInt(gatitems.get("id")));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Map<String, String> gatitems, Connection conn) {
        String sql = "delete from gatitems where id=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("id")));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Map<String, String>> getGgiByGatList(Map<String, String> gat) {
        List<Map<String, String>> ggiList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,gid,giid from ggi where gid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gat.get("id")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> ggi = new HashMap<String, String>();
                ggi.put("id", rs.getString("id"));
                ggi.put("gid", rs.getString("gid"));
                ggi.put("giid", rs.getString("giid"));
                ggiList.add(ggi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return ggiList;
    }

    public void delGgiByGat(Map<String, String> gat, Connection conn) {
        String sql = "delete from ggi where gid=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(!"".equals(gat.get("id")) ? gat.get("id") : "0"));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delGgiByGatItems(Map<String, String> gatitems, Connection conn) {
        String sql = "delete from ggi where giid=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(!"".equals(gatitems.get("id")) ? gatitems.get("id") : "0"));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addGgi(Map<String, String> ggi) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into ggi(gid,giid,wxsid) values (?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(ggi.get("gid")));
            ptst.setInt(2, Integer.parseInt(ggi.get("giid")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updatePraisecount(Map<String, String> gatitems) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update gatitems set praisecount=praisecount+1 where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatitems.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GatItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
