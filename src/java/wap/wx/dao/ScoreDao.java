package wap.wx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import job.tot.bean.DataField;
import job.tot.dao.AbstractDao;
import job.tot.exception.DatabaseException;
import job.tot.exception.ObjectNotFoundException;
import wap.wx.util.DbConn;
import wap.wx.util.PageUtil;

/**
 * @author Administrator
 */
public class ScoreDao extends AbstractDao {

	private static Log log = LogFactory.getLog(ScoreDao.class);

	public List<Map<String, String>> getList(int tid) {
		String fieldArr = "id,content,sid,dr,ts,openid,uid,level,tid";
		String sqlStr = "select " + fieldArr + " from k_score where tid=? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		try {
			conn = DbConn.getConn();
			pst = conn.prepareStatement(sqlStr);
			pst.setInt(1, tid);
			rs = pst.executeQuery();
			Map<String, String> df = null;
			String[] splitStr = null;
			splitStr = fieldArr.split(",");
			while (rs.next()) {
				df = new HashMap<String, String>();
				for (int i = 0; i < splitStr.length; i++) {
					df.put(splitStr[i], rs.getString(i + 1));
				}
				returnList.add(df);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Sql Exception Error:", e);
		} finally {
			DbConn.getAllClose(conn, pst, rs);
		}
		return returnList;
	}

	public boolean add(String content, int sid, String openid, String uid, int tid, int level) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		String sql = "insert into k_score(content,sid,openid,uid,tid,level) values(?,?,?,?,?,?)";
		try {
			conn = DbConn.getConn();
			conn.setAutoCommit(returnValue);
			ps = conn.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, sid);
			ps.setString(3, openid);
			ps.setString(4, uid);
			ps.setInt(5, tid);
			ps.setInt(6, level);
			if (ps.executeUpdate() == 1) {
				returnValue = true;
			}
			
			//当添加新评分时，更新店铺的评分
			if(returnValue){
				returnValue = this.average(conn, tid, level);
			}
			//end 更新评分
			//如果成功  提交结果，如果不成功事务回滚
			if(returnValue){
				conn.commit();
			}else{
				conn.rollback();
			}
			//END
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("add score error", e);
		} finally {
			DbConn.getAllClose(conn, ps, null);
		}
		return returnValue;
	}

	public boolean mod(String content, int level) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = true;
		String sql = "update k_score set content=?,level=? where id=?";
		try {
			conn = DbConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, level);
			if (ps.executeUpdate() != 1) {
				returnValue = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("mod role error", e);
		} finally {
			DbConn.getAllClose(conn, ps, null);
		}
		return returnValue;
	}

	public boolean del(int id) throws ObjectNotFoundException, DatabaseException {
		return exe("delete from k_score where id=" + id);
	}

	public Collection get_Limit(int tid, int currentpage, int pagesize) {
		String str = "id,content,sid,dr,ts,openid,uid,level,tid";
		StringBuilder sql = new StringBuilder(
				"select id,content,sid,dr,ts,openid,uid,level,tid from k_score where tid=" + tid);
		return getDataList_mysqlLimit(sql.toString(), str, pagesize, (currentpage - 1) * pagesize);
	}

	public List<Map<String, String>> getList(PageUtil pu, int tid) {
		List<Map<String, String>> scoreList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,content,sid,dr,ts,openid,uid,level,tid";
		String sql = "select " + field + " from k_score where tid=? order by ts desc limit "
				+ pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, tid);
			rs = ptst.executeQuery();
			while (rs.next()) {
				Map<String, String> score = new HashMap<String, String>();
				score.put("id", rs.getString("id"));
				score.put("content", rs.getString("content"));
				score.put("sid", rs.getString("sid"));
				score.put("ts", rs.getString("ts"));
				score.put("openid", rs.getString("openid"));
				score.put("level", rs.getString("level"));
				score.put("tid", rs.getString("tid"));
				scoreList.add(score);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return scoreList;
	}

	public boolean get(String openid) {
		boolean flag = true;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "select count(*) count from k_score where openid=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, openid);
			rs = ptst.executeQuery();
			int i = 0;
			while (rs.next()) {
				i = rs.getInt(1);
			}
			if (i >= 1) {
				flag = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return flag;
	}

	public int getTotalCount(int tid) {
		String sqlStr = "select count(*) from k_score where tid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int returnInt = 0;
		try {
			conn = DbConn.getConn();
			pst = conn.prepareStatement(sqlStr);
			pst.setInt(1, tid);
			rs = pst.executeQuery();
			if (rs.next()) {
				returnInt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Sql Exception Error:", e);
		} finally {
			DbConn.getAllClose(conn, pst, rs);
		}
		return returnInt;
	}

	public int getMaxId() {
		return getDataCount("select max(id) from k_score");
	}

	/**
	 * 根据传入的评分和店铺id更新店铺的总评分
	 */
	public boolean average(Connection conn, int tid, int score) {
		boolean flag = false;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		try {
			// 查询这个店铺的总评分
			String sql = "update k_store set scores = scores+?,pnum = pnum+1, favorate=scores/pnum where id=?";
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, score);
			ptst.setInt(2, tid);
			int num = ptst.executeUpdate();
			if (num == 1) {
				flag = true;
			}
		} catch (Exception e) {
			DbConn.getAllClose(conn, ptst, rs);
			log.debug("ScoreDao.average() run error", e);
			e.printStackTrace();
		}
		return flag;
	}
}
