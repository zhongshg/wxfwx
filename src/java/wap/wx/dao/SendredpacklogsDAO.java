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
public class SendredpacklogsDAO {

    public List<Map<String, String>> getList(PageUtil pu) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public List<Map<String, String>> getList(PageUtil pu, int state) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where state=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, state);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public List<Map<String, String>> getList(PageUtil pu, String mch_billno) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where mch_billno=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public List<Map<String, String>> getList(PageUtil pu, String mch_billno, int state) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where mch_billno=? and state=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setInt(2, state);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public List<Map<String, String>> getList(String mch_billno, String openid) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where mch_billno=? and openid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setString(2, openid);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public List<Map<String, String>> getList(String mch_billno, String openid, int state) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where mch_billno=? and openid=? and state=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setString(2, openid);
            ptst.setInt(3, state);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public int getConut() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(int state) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where state=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, state);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(String mch_billno) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(String mch_billno, int state) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=? and state=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setInt(2, state);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(String mch_billno, String starttime, String endtime) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=? and times between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setString(2, starttime);
            ptst.setString(3, endtime);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(String mch_billno, int state, String starttime, String endtime) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=? and state=? and times between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setInt(2, state);
            ptst.setString(3, starttime);
            ptst.setString(4, endtime);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(String mch_billno, String openid, String starttime, String endtime) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=? and openid=? and times between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setString(2, openid);
            ptst.setString(3, starttime);
            ptst.setString(4, endtime);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getConut(String mch_billno, String openid, int state, String starttime, String endtime) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=? and openid=? and state=? and times between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setString(2, openid);
            ptst.setInt(3, state);
            ptst.setString(4, starttime);
            ptst.setString(5, endtime);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getPrizePointConut(String mch_billno, String prizeid, String starttime, String endtime) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from sendredpacklogs where mch_billno=? and prizeid=? and times between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            ptst.setInt(2, Integer.parseInt(prizeid));
            ptst.setString(3, starttime);
            ptst.setString(4, endtime);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(map.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return map;
    }

    public Map<String, String> getByMch_billno(String mch_billno) {
        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes from sendredpacklogs where mch_billno=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            rs = ptst.executeQuery();
            if (rs.next()) {
                map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("openid", rs.getString("openid"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("total_amount", rs.getString("total_amount"));
                map.put("wishing", rs.getString("wishing"));
                map.put("client_ip", rs.getString("client_ip"));
                map.put("act_name", rs.getString("act_name"));
                map.put("remark", rs.getString("remark"));
                map.put("send_listid", rs.getString("send_listid"));
                map.put("send_time", rs.getString("send_time"));
                map.put("state", rs.getString("state"));
                map.put("prizeid", rs.getString("prizeid"));
                map.put("times", rs.getString("times"));
                map.put("mch_billno_send", rs.getString("mch_billno_send"));
                map.put("isexp", rs.getString("isexp"));
                map.put("exptimes", rs.getString("exptimes"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return map;
    }

    public boolean add(Map<String, String> map) {
        boolean flag = false;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into sendredpacklogs(openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time,state,prizeid,times,mch_billno_send,isexp,exptimes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, map.get("openid"));
            ptst.setString(2, map.get("mch_billno"));
            ptst.setString(3, map.get("nick_name"));
            ptst.setString(4, map.get("send_name"));
            ptst.setInt(5, Integer.parseInt(map.get("total_amount")));
            ptst.setString(6, map.get("wishing"));
            ptst.setString(7, map.get("client_ip"));
            ptst.setString(8, map.get("act_name"));
            ptst.setString(9, map.get("remark"));
            ptst.setString(10, map.get("send_listid"));
            ptst.setString(11, map.get("send_time"));
            ptst.setString(12, map.get("state"));
            ptst.setString(13, map.get("prizeid"));
            ptst.setString(14, map.get("times"));
            ptst.setString(15, map.get("mch_billno_send"));
            ptst.setInt(16, Integer.parseInt(map.get("isexp")));
            ptst.setString(17, map.get("exptimes"));
            int i = ptst.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return flag;
    }

    public void update(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update sendredpacklogs set openid=?,mch_billno=?,nick_name=?,send_name=?,total_amount=?,wishing=?,client_ip=?,act_name=?,remark=?,send_listid=?,send_time=?,state=?,prizeid=?,times=?,mch_billno_send=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, map.get("openid"));
            ptst.setString(2, map.get("mch_billno"));
            ptst.setString(3, map.get("nick_name"));
            ptst.setString(4, map.get("send_name"));
            ptst.setInt(5, Integer.parseInt(map.get("total_amount")));
            ptst.setString(6, map.get("wishing"));
            ptst.setString(7, map.get("client_ip"));
            ptst.setString(8, map.get("act_name"));
            ptst.setString(9, map.get("remark"));
            ptst.setString(10, map.get("send_listid"));
            ptst.setString(11, map.get("send_time"));
            ptst.setString(12, map.get("state"));
            ptst.setInt(13, Integer.parseInt(map.get("prizeid")));
            ptst.setString(14, map.get("times"));
            ptst.setString(15, map.get("mch_billno_send"));
            ptst.setInt(16, Integer.parseInt(map.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(String openid, String mch_billno, int isexp, String exptimes) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update sendredpacklogs set isexp=?,exptimes=? where openid=? and mch_billno=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, isexp);
            ptst.setString(2, exptimes);
            ptst.setString(3, openid);
            ptst.setString(4, mch_billno);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from sendredpacklogs where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(map.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SendredpacklogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
