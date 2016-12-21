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
public class StoreTypeDAO {

	public List<Map<String, String>> getList(PageUtil pu, Map<String, String> store) {
		List<Map<String, String>> storeList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,tname,tcode,ts,dr,sid";
		String sql = "select " + field + " from k_storetype where sid=? order by tcode asc limit "
				+ pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("sid")));
			rs = ptst.executeQuery();
			while (rs.next()) {
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("tname", rs.getString("tname"));
				store.put("tcode", rs.getString("tcode"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("sid", rs.getString("sid"));
				storeList.add(store);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return storeList;
	}

	public List<Map<String, String>> getAllList(String sid) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,tname,tcode,ts,dr,sid";
		String sql = "select " + field + " from k_storetype where sid=? order by tcode asc";
		List<Map<String, String>> storeList = new ArrayList<Map<String, String>>();
		try {
			conn = DbConn.getConn();
			Map<String, String> storeType = null;
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(sid));
			rs = ptst.executeQuery();
			while (rs.next()) {
				storeType = new HashMap<String, String>();
				storeType.put("id", rs.getString("id"));
				storeType.put("tname", rs.getString("tname"));
				storeType.put("tcode", rs.getString("tcode"));
				storeType.put("ts", rs.getString("ts"));
				storeType.put("dr", rs.getString("dr"));
				storeType.put("sid", rs.getString("sid"));
				storeList.add(storeType);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return storeList;
	}

	public int getConut(Map<String, String> store) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "select count(id) count from k_storetype where sid=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("sid")));
			rs = ptst.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return count;
	}

	public Map<String, String> getById(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,tname,tcode,ts,dr,sid";
		String sql = "select " + field + " from k_storetype where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("id")));
			rs = ptst.executeQuery();
			if (rs.next()) {
				store = new HashMap<String, String>();
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("tname", rs.getString("tname"));
				store.put("tcode", rs.getString("tcode"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("sid", rs.getString("sid"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return store;
	}

	public void add(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "insert into k_storetype(tname,tcode,sid) values(?,?,?)";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, store.get("tname"));
			ptst.setString(2, store.get("tcode"));
			ptst.setString(3, store.get("sid"));
			ptst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
	}

	public void update(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "update k_storetype set tname=?,tcode=? where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, store.get("tname"));
			ptst.setString(2, store.get("tcode"));
			ptst.setInt(3, Integer.parseInt(store.get("id")));
			ptst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
	}

	public void delete(Map<String, String> store, Connection conn) {
		String sql = "delete from k_storetype where id=?";
		try {
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("id")));
			ptst.executeUpdate();
			ptst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void delete(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		String sql = "delete from k_storetype where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("id")));
			ptst.executeUpdate();
			ptst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, null);
		}
	}

}
