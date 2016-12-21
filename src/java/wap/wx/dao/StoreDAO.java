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

import job.tot.db.DBUtils;
import wap.wx.util.DbConn;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class StoreDAO {

	public List<Map<String, String>> getList(PageUtil pu, Map<String, String> store) {
		List<Map<String, String>> storeList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr,zcode,cityCode,street";
		String sql = "select " + field + " from k_store where sid=? order by id desc limit "
				+ pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("sid")));
			rs = ptst.executeQuery();
			while (rs.next()) {
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("code", rs.getString("code"));
				store.put("name", rs.getString("name"));
				store.put("favorate", rs.getString("favorate"));
				store.put("jing", rs.getString("jing"));
				store.put("wei", rs.getString("wei"));
				store.put("level", rs.getString("level"));
				store.put("typeid", rs.getString("typeid"));
				store.put("img", rs.getString("img"));
				store.put("tel", rs.getString("tel"));
				store.put("phone", rs.getString("phone"));
				store.put("provice", rs.getString("provice"));
				store.put("city", rs.getString("city"));
				store.put("county", rs.getString("county"));
				store.put("content", rs.getString("content"));
				store.put("area", rs.getString("area"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("zcode", rs.getString("zcode"));
				store.put("cityCode", rs.getString("cityCode"));
				store.put("street", rs.getString("street"));
				storeList.add(store);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return storeList;
	}

	public List<Map<String, String>> search(PageUtil pu, String sid, String name, double lat, double lon) {
		List<Map<String, String>> storeList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,street,"
				+ "provice,city,county,area,ts,dr,zcode,cityCode,round(6378.138*2*asin(sqrt(pow(sin((wei*pi()/180-?*pi()"
			 + "/180)/2),2)+cos(wei*pi()/180)*cos(?*pi()/180)*pow(sin( (jing*pi()/180-?*pi()/180)/2),2)))*1000) as juli";

		String sql = "select " + field + " from k_store where sid=? and name like ? order by juli asc limit ";
		sql += pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setDouble(1, lat);
			ptst.setDouble(2, lat);
			ptst.setDouble(3, lon);
			ptst.setInt(4, Integer.parseInt(sid));
			ptst.setString(5, "%"+name +"%");
			rs = ptst.executeQuery();
			Map<String, String> store = null;
			while (rs.next()) {
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("code", rs.getString("code"));
				store.put("name", rs.getString("name"));
				store.put("favorate", rs.getString("favorate"));
				store.put("jing", rs.getString("jing"));
				store.put("wei", rs.getString("wei"));
				store.put("level", rs.getString("level"));
				store.put("typeid", rs.getString("typeid"));
				store.put("img", rs.getString("img"));
				store.put("tel", rs.getString("tel"));
				store.put("phone", rs.getString("phone"));
				store.put("provice", rs.getString("provice"));
				store.put("city", rs.getString("city"));
				store.put("county", rs.getString("county"));
				store.put("content", rs.getString("content"));
				store.put("area", rs.getString("area"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("zcode", rs.getString("zcode"));
				store.put("cityCode", rs.getString("cityCode"));
				store.put("juli", rs.getString("juli"));
				store.put("street", rs.getString("street"));
				storeList.add(store);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return storeList;
	}
	
	
	public List<Map<String, String>> getList(PageUtil pu, String sid, String tid, String zcode, double lat, double lon,
			String orders) {
		List<Map<String, String>> storeList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,street,"
				+ "provice,city,county,area,ts,dr,zcode,cityCode,round(6378.138*2*asin(sqrt(pow(sin((wei*pi()/180-?*pi()"
			 + "/180)/2),2)+cos(wei*pi()/180)*cos(?*pi()/180)*pow(sin( (jing*pi()/180-?*pi()/180)/2),2)))*1000) as juli";

		String sql = "select " + field + " from k_store where sid=? and zcode=? and typeid=? ";
		if ("1".equals(orders)) {
			sql += "order by juli asc limit ";
		} else if ("2".equals(orders)) {
			sql += "order by favorate desc limit ";
		}
		sql += pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setDouble(1, lat);
			ptst.setDouble(2, lat);
			ptst.setDouble(3, lon);
			ptst.setInt(4, Integer.parseInt(sid));
			ptst.setInt(5, Integer.parseInt(zcode));
			ptst.setInt(6, Integer.parseInt(tid));
			
			rs = ptst.executeQuery();
			Map<String, String> store = null;
			while (rs.next()) {
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("code", rs.getString("code"));
				store.put("name", rs.getString("name"));
				store.put("favorate", rs.getString("favorate"));
				store.put("jing", rs.getString("jing"));
				store.put("wei", rs.getString("wei"));
				store.put("level", rs.getString("level"));
				store.put("typeid", rs.getString("typeid"));
				store.put("img", rs.getString("img"));
				store.put("tel", rs.getString("tel"));
				store.put("phone", rs.getString("phone"));
				store.put("provice", rs.getString("provice"));
				store.put("city", rs.getString("city"));
				store.put("county", rs.getString("county"));
				store.put("content", rs.getString("content"));
				store.put("area", rs.getString("area"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("zcode", rs.getString("zcode"));
				store.put("cityCode", rs.getString("cityCode"));
				store.put("juli", rs.getString("juli"));
				store.put("street", rs.getString("street"));
				storeList.add(store);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return storeList;
	}

	public List<Map<String, String>> getIndexStore(PageUtil pu, String sid, String zcode, double lat, double lon,String orders) {
		List<Map<String, String>> storeList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,street,"
				+ "provice,city,county,area,ts,dr,zcode,cityCode,round(6378.138*2*asin(sqrt(pow(sin((wei*pi()/180-?*pi()"
			 + "/180)/2),2)+cos(wei*pi()/180)*cos(?*pi()/180)*pow(sin( (jing*pi()/180-?*pi()/180)/2),2)))*1000) as juli";

		String sql = "select " + field + " from k_store where sid=? and zcode=? ";
		if ("1".equals(orders)) {
			sql += "order by juli asc limit ";
		} else if ("2".equals(orders)) {
			sql += "order by favorate desc limit ";
		}
		sql += pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setDouble(1, lat);
			ptst.setDouble(2, lat);
			ptst.setDouble(3, lon);
			ptst.setInt(4, Integer.parseInt(sid));
			ptst.setInt(5, Integer.parseInt(zcode));
			//ptst.setInt(6, Integer.parseInt(tid));
			rs = ptst.executeQuery();
			Map<String, String> store = null;
			while (rs.next()) {
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("code", rs.getString("code"));
				store.put("name", rs.getString("name"));
				store.put("favorate", rs.getString("favorate"));
				store.put("jing", rs.getString("jing"));
				store.put("wei", rs.getString("wei"));
				store.put("level", rs.getString("level"));
				store.put("typeid", rs.getString("typeid"));
				store.put("img", rs.getString("img"));
				store.put("tel", rs.getString("tel"));
				store.put("phone", rs.getString("phone"));
				store.put("provice", rs.getString("provice"));
				store.put("city", rs.getString("city"));
				store.put("county", rs.getString("county"));
				store.put("content", rs.getString("content"));
				store.put("area", rs.getString("area"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("zcode", rs.getString("zcode"));
				store.put("cityCode", rs.getString("cityCode"));
				store.put("juli", rs.getString("juli"));
				store.put("street", rs.getString("street"));
				storeList.add(store);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
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
		String sql = "select count(id) count from k_store where sid=?";
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
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return count;
	}

	public int getConut(String sid, String tid, String acode) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "select count(id) count from k_store where typeid=? and sid=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(tid));
			ptst.setInt(2, Integer.parseInt(sid));
			rs = ptst.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return count;
	}

	public Map<String, String> getById(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,favorate,content,jing,wei,level,typeid,img,tel,phone,provice,city,county,area,ts,dr,sid,zcode,cityCode,street";
		String sql = "select " + field + " from k_store where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("id")));
			rs = ptst.executeQuery();
			if (rs.next()) {
				store = new HashMap<String, String>();
				store = new HashMap<String, String>();
				store.put("id", rs.getString("id"));
				store.put("code", rs.getString("code"));
				store.put("name", rs.getString("name"));
				store.put("favorate", rs.getString("favorate"));
				store.put("jing", rs.getString("jing"));
				store.put("wei", rs.getString("wei"));
				store.put("level", rs.getString("level"));
				store.put("typeid", rs.getString("typeid"));
				store.put("img", rs.getString("img"));
				store.put("tel", rs.getString("tel"));
				store.put("phone", rs.getString("phone"));
				store.put("provice", rs.getString("provice"));
				store.put("city", rs.getString("city"));
				store.put("county", rs.getString("county"));
				store.put("content", rs.getString("content"));
				store.put("area", rs.getString("area"));
				store.put("ts", rs.getString("ts"));
				store.put("dr", rs.getString("dr"));
				store.put("sid", rs.getString("sid"));
				store.put("zcode", rs.getString("zcode"));
				store.put("cityCode", rs.getString("cityCode"));
				store.put("street", rs.getString("street"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return store;
	}

	public void add(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "insert into k_store(code,name,content,jing,wei,typeid,img,tel,phone,provice,city,county,area,sid,zcode,cityCode,street) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, store.get("code"));
			ptst.setString(2, store.get("name"));
			//ptst.setDouble(3, 0);
			ptst.setString(3, store.get("content"));
			ptst.setDouble(4, Double.parseDouble(store.get("jing")));
			ptst.setDouble(5, Double.parseDouble(store.get("wei")));
			ptst.setInt(6, Integer.parseInt(store.get("typeid")));
			ptst.setString(7, store.get("img"));
			ptst.setString(8, store.get("tel"));
			ptst.setString(9, store.get("phone"));
			ptst.setString(10, store.get("provice"));
			ptst.setString(11, store.get("city"));
			ptst.setString(12, store.get("county"));
			ptst.setString(13, store.get("area"));
			ptst.setInt(14, Integer.parseInt(store.get("sid")));
			ptst.setString(15, store.get("zcode"));
			ptst.setString(16, store.get("cityCode"));
			ptst.setString(17, store.get("street"));
			ptst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
	}

	public void update(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "update k_store set code=?,name=?,jing=?,wei=?,typeid=?,img=?,tel=?,phone=?,provice=?,city=?,county=?,area=?,zcode=?,cityCode=?,content=?,street=? where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, store.get("code"));
			ptst.setString(2, store.get("name"));
			ptst.setDouble(3, Double.parseDouble(store.get("jing")));
			ptst.setDouble(4, Double.parseDouble(store.get("wei")));
			ptst.setInt(5, Integer.parseInt(store.get("typeid")));
			ptst.setString(6, store.get("img"));
			ptst.setString(7, store.get("tel"));
			ptst.setString(8, store.get("phone"));
			ptst.setString(9, store.get("provice"));
			ptst.setString(10, store.get("city"));
			ptst.setString(11, store.get("county"));
			ptst.setString(12, store.get("area"));
			ptst.setInt(13, Integer.parseInt(store.get("zcode")));
			ptst.setInt(14, Integer.parseInt(store.get("cityCode")));
			ptst.setString(15, store.get("content"));
			ptst.setString(16, store.get("street"));
			ptst.setInt(17, Integer.parseInt(store.get("id")));
			ptst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
	}

	public void delete(Map<String, String> store, Connection conn) {
		String sql = "delete from k_store where id=?";
		try {
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("id")));
			ptst.executeUpdate();
			ptst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void delete(Map<String, String> store) {
		Connection conn = null;
		PreparedStatement ptst = null;
		String sql = "delete from k_store where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(store.get("id")));
			ptst.executeUpdate();
			ptst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, null);
		}
	}

}
