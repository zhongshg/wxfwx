package wap.wx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import job.tot.dao.AbstractDao;
import wap.wx.util.DbConn;

/**
 * @author Administrator
 */
public class ScoreSumDao extends AbstractDao {

	private static Log log = LogFactory.getLog(ScoreSumDao.class);

	/**
	 * 根据传入的评分和店铺id更新店铺的总评分
	 */
	public boolean average(Connection conn, int tid, int score) {
		boolean flag = false;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		try {
			// 查询这个店铺的总评分
			String sql = "update k_store set scores = scores+?,pnum = pnum+1, favorate=scores/pnum where tid=?";
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, score);
			ptst.setInt(2, tid);
			int num = ptst.executeUpdate();
			if (num == 1) {
				flag = true;
			}
		} catch (Exception e) {
			DbConn.getAllClose(conn, ptst, rs);
			log.debug("ScoreSumDao.average() run error", e);
			e.printStackTrace();
		}
		return flag;
	}
}
