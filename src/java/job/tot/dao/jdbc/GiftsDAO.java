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
 * @author ASUS
 */
public class GiftsDAO extends AbstractDao {

    public List<DataField> getList(int sid) {
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where sid=" + sid + " order by id desc", "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
        return list;
    }

    public List<DataField> getList(int giftid, int sid) {
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where giftid=" + giftid + " and sid=" + sid + " order by id desc", "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
        return list;
    }

    public List<DataField> getByOpenidList(String openid, int sid) {
        List<DataField> list = (List<DataField>) DaoFactory.getMysqlDao().getList("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where openid='" + openid + "' and sid=" + sid, "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
        return list;
    }

    public List<DataField> getByOpenidList(String openid, int sid, int isuse) {
        List<DataField> list = (List<DataField>) DaoFactory.getMysqlDao().getList("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where openid='" + openid + "' and sid=" + sid + " and isuse=" + isuse, "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
        return list;
    }

    public List<DataField> getByOpenidGiftidList(String openid, int giftid) {
        List<DataField> list = (List<DataField>) DaoFactory.getMysqlDao().getList("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where openid='" + openid + "' and giftid=" + giftid, "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
        return list;
    }

    public List<DataField> getByOpenidGiftidList(String openid, int giftid, int isuse) {
        List<DataField> list = (List<DataField>) DaoFactory.getMysqlDao().getList("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where openid='" + openid + "' and giftid=" + giftid + " and isuse=" + isuse, "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
        return list;
    }

    public DataField get(int id) {
        return DaoFactory.getMysqlDao().getFirstData("select id,openid,giftid,moneys,orderno,isuse,times,remark,sid from gifts where id=" + id, "id,openid,giftid,moneys,orderno,isuse,times,remark,sid");
    }

    public int getMaxId() {
        DataField df = DaoFactory.getMysqlDao().getFirstData("select max(id) maxid from gifts", "maxid");
        return df.getInt("maxid");
    }

    public boolean add(String openid, float moneys, int giftid, String orderno, int isuse, String times, String remark, int sid) {
        boolean flag = DaoFactory.getMysqlDao().add("insert into gifts(openid,moneys,giftid,orderno,isuse,times,remark,sid) values ('" + openid + "'," + moneys + "," + giftid + ",'" + orderno + "'," + isuse + ",'" + times + "','" + remark + "'," + sid + ")");
        return flag;
    }

    public boolean modisuse(String openid, int id) {
        boolean flag = DaoFactory.getMysqlDao().mod("update gifts set isuse=1 where openid='" + openid + "' and id=" + id);
        return flag;
    }

    public boolean modorderno(String orderno, String openid, int id) {
        boolean flag = DaoFactory.getMysqlDao().mod("update gifts set orderno='" + orderno + "' where openid='" + openid + "' and id=" + id);
        return flag;
    }
}
