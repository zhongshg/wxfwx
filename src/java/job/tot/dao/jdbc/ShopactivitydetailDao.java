/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package job.tot.dao.jdbc;

import java.util.List;
import job.tot.bean.DataField;
import job.tot.dao.AbstractDao;
import job.tot.dao.DaoFactory;

/**
 *
 * @author Administrator
 */
public class ShopactivitydetailDao extends AbstractDao {

    public List<DataField> getList(int shopactivityid, int giftid, int isuse, int sid) {
        List list = (List) DaoFactory.getMysqlDao().getList("select id,shopactivityid,macode,giftid,times,sid,isuse from shopactivitydetail where shopactivityid=" + shopactivityid + " and giftid=" + giftid + " and isuse=" + isuse + " and sid=" + sid + " order by id desc", "id,shopactivityid,macode,giftid,times,sid,isuse");
        return list;
    }

    public DataField get(int id) {
        return DaoFactory.getMysqlDao().getFirstData("select id,shopactivityid,macode,giftid,times,sid,isuse from shopactivitydetail where id=" + id, "id,shopactivityid,macode,giftid,times,sid,isuse");
    }

    public DataField get(String macode, int sid) {
        return DaoFactory.getMysqlDao().getFirstData("select id,shopactivityid,macode,giftid,times,sid,isuse from shopactivitydetail where macode='" + macode + "' and sid=" + sid, "id,shopactivityid,macode,giftid,times,sid,isuse");
    }

    public boolean add(int shopactivityid, String macode, int giftid, String times, int sid, int isuse) {
        boolean flag = DaoFactory.getMysqlDao().add("insert into shopactivitydetail(shopactivityid,macode,giftid,times,sid,isuse) values (" + shopactivityid + ",'" + macode + "'," + giftid + ",'" + times + "'," + sid + "," + isuse + ")");
        return flag;
    }

    public boolean modBymacode(int shopactivityid, String macode, int giftid, String times, int sid, int isuse) {
        boolean flag = DaoFactory.getMysqlDao().mod("update shopactivitydetail set shopactivityid=" + shopactivityid + ",giftid=" + giftid + ",times='" + times + "',isuse=" + isuse + " where macode='" + macode + "'");
        return flag;
    }
}
