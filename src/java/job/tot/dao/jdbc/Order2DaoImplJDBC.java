/*
 * UserDaoImplJDBC.java
 *
 * Created on 2007锟斤拷4锟斤拷16锟斤拷, 锟斤拷锟斤拷4:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package job.tot.dao.jdbc;

import job.tot.dao.AbstractDao;
import job.tot.db.DBUtils;
import job.tot.exception.ObjectNotFoundException;
import job.tot.exception.DatabaseException;
import java.sql.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import job.tot.bean.DataField;

/**
 *
 * @author Useristrator
 */
public class Order2DaoImplJDBC extends AbstractDao {

    private static Log log = LogFactory.getLog(Order2DaoImplJDBC.class);

    /**
     * Creates a new instance of UserDaoImplJDBC
     */
    public Order2DaoImplJDBC() {
    }

    /*
     * id,fno,fdate,fprice,discount,sfprice,openid,ip,sts,state,ispay,name,address,mobile,tel,remark,postcode,out_trade_no,transaction_id t_order2
     */
    public boolean add(String fno, String fdate, float fprice, float discount, float sfprice, String openid, String ip, int sts, int state, int ispay,
            String name, String address, String mobile, String tel, String remark, String postcode, String out_trade_no, String transaction_id, int pid, int cid, int aid, float perprice, int pnum) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "insert into t_order2(fno,fdate,fprice,discount,sfprice,openid,ip,sts,state,ispay,name,address,mobile,tel,remark,postcode,out_trade_no,transaction_id,pid,cid,aid,perprice,pnum) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, fno);
            ps.setString(2, fdate);
            ps.setFloat(3, fprice);
            ps.setFloat(4, discount);
            ps.setFloat(5, sfprice);
            ps.setString(6, openid);
            ps.setString(7, ip);
            ps.setInt(8, sts);
            ps.setInt(9, state);
            ps.setInt(10, ispay);
            ps.setString(11, name);
            ps.setString(12, address);
            ps.setString(13, mobile);
            ps.setString(14, tel);
            ps.setString(15, remark);
            ps.setString(16, postcode);
            ps.setString(17, out_trade_no);
            ps.setString(18, transaction_id);
            ps.setInt(19, pid);
            ps.setInt(20, cid);
            ps.setInt(21, aid);
            ps.setFloat(22, perprice);
            ps.setInt(23, pnum);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            log.error("addUser error ", e);
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public DataField get(String fno) {
        String fieldArr = "id,fno,fdate,fprice,discount,sfprice,openid,ip,sts,state,ispay,name,address,mobile,tel,remark,postcode,out_trade_no,transaction_id,pid,cid,aid,perprice,pnum";
        String sql = "select " + fieldArr + " from t_order2 where fno='" + fno + "'";
        return getFirstData(sql, fieldArr);
    }

    public DataField getByout_trade_no(String out_trade_no) {
        String fieldArr = "id,fno,fdate,fprice,discount,sfprice,openid,ip,sts,state,ispay,name,address,mobile,tel,remark,postcode,out_trade_no,transaction_id,pid,cid,aid,perprice,pnum";
        String sql = "select " + fieldArr + " from t_order2 where out_trade_no='" + out_trade_no + "'";
        return getFirstData(sql, fieldArr);
    }

    //修改订单进度
    public boolean modSts(String fno, int num) throws ObjectNotFoundException, DatabaseException {
        return exe("update t_order2 set sts=" + num + " where fno='" + fno + "'");
    }

    public boolean modStsByout_trade_no(String out_trade_no, int num) throws ObjectNotFoundException, DatabaseException {
        return exe("update t_order2 set sts=" + num + " where out_trade_no='" + out_trade_no + "'");
    }

    //修改交易状态
    public void modState(String fno, int num) throws ObjectNotFoundException, DatabaseException {
        exe("update t_order2 set state=" + num + " where fno='" + fno + "'");
    }

    public void modStateByout_trade_no(String out_trade_no, int num) throws ObjectNotFoundException, DatabaseException {
        exe("update t_order2 set state=" + num + " where out_trade_no='" + out_trade_no + "'");
    }

    public void modout_trade_no(String fno, String out_trade_no) throws ObjectNotFoundException, DatabaseException {
        exe("update t_order2 set out_trade_no='" + out_trade_no + "' where fno='" + fno + "'");
    }

    public void modtransaction_id(String transaction_id, String out_trade_no) throws ObjectNotFoundException, DatabaseException {
        exe("update t_order2 set transaction_id='" + transaction_id + "' where out_trade_no='" + out_trade_no + "'");
    }

    public boolean modIsPay(String fno, int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "update t_order2 set ispay=? where fno=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            ps.setString(2, fno);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public boolean modIsPayByout_trade_no(String out_trade_no, int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "update t_order2 set ispay=? where out_trade_no=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            ps.setString(2, out_trade_no);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public void modPrice(String fno, float price) throws ObjectNotFoundException, DatabaseException {
        exe("update t_order set sfprice=" + price + " where fno='" + fno + "'");
    }

    public Collection getList(int sts, int state, int ispay, String fno, int currentpage, int pagesize) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,fno,fdate,fprice,discount,sfprice,openid,ip,sts,state,ispay,name,address,mobile,tel,remark,postcode,out_trade_no,transaction_id,pid,cid,aid,perprice,pnum";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from t_order2");
        sql.append(" where 1=1");
        if (sts > 0) {
            sql.append(" and sts=").append(sts);
        }
        if (ispay > 0) {
            sql.append(" and ispay=").append(ispay);
        }
        if (fno != null && !fno.equals("")) {
            sql.append(" and fno='").append(fno).append("'");
        }

        if (state > 0) {
            sql.append(" and state=").append(state);
        }

        sql.append(" order by id desc ");
        if (currentpage > 0 && pagesize > 0) {
            sql.append("limit ");
            sql.append((currentpage - 1) * pagesize);
            sql.append(",");
            sql.append(pagesize);
        }
        return this.getData(sql.toString(), fieldArr);
    }

    public int getTotalNum(int sts, int state, int cate, String fno) {
        int returnValue = 0;
        StringBuffer sql = new StringBuffer(512);
        sql.append("select count(*) from t_order2");
        sql.append(" where 1=1");
        if (sts > 0) {
            sql.append(" and sts=").append(sts);
        }
        if (fno != null && !fno.equals("")) {
            sql.append(" and fno='").append(fno).append("'");
        }

        if (state > 0) {
            sql.append(" and state=").append(state);
        }

        sql.append(" order by id desc ");
        returnValue = getDataCount(sql.toString());
        return returnValue;
    }

    public void del(String[] s) {
        this.bat_String("delete from t_order2 where fno=?", s);
    }
}
