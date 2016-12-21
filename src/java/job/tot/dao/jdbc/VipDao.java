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
public class VipDao extends AbstractDao {

    public List<DataField> getList(int currentpage, int pagesize) {//popular 人气
        List list = (List) DaoFactory.getMysqlDao().getList("select id,name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark from vip order by id desc limit " + (currentpage - 1) * pagesize + "," + pagesize, "id,name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark");
        return list;
    }

    public int getTotalNum() {
        int returnValue = 0;
        StringBuffer sql = new StringBuffer(512);
        sql.append("select count(*) from vip");
        returnValue = getDataCount(sql.toString());
        return returnValue;
    }

    public List<DataField> getList() {//这个方法在判断会员时需要根据优先级，因此排序不能变更
        List list = (List) DaoFactory.getMysqlDao().getList("select id,name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark from vip order by grade desc limit 0,9", "id,name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark");
        return list;
    }

    public DataField get(int id) {//popular 人气
        DataField df = DaoFactory.getMysqlDao().getFirstData("select id,name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark from vip where id=" + id, "id,name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark");
        return df;
    }

    public boolean add(String name, int grade, float discount, int isyunfei, int type, int limittrade, float limitmoney, float limitpercent, String remark) {
        boolean flag = DaoFactory.getMysqlDao().add("insert into vip(name,grade,discount,isyunfei,type,limittrade,limitmoney,limitpercent,remark) values ('" + name + "'," + grade + "," + discount + "," + isyunfei + "," + type + "," + limittrade + "," + limitmoney + "," + limitpercent + ",'" + remark + "')");
        return flag;
    }

    public boolean mod(int id, String name, int grade, float discount, int isyunfei, int type, int limittrade, float limitmoney, float limitpercent, String remark) {
        boolean flag = DaoFactory.getMysqlDao().mod("update vip set name='" + name + "',grade=" + grade + ",discount=" + discount + ",isyunfei=" + isyunfei + ",type=" + type + ",limittrade=" + limittrade + ",limitmoney=" + limitmoney + ",limitpercent=" + limitpercent + ",remark='" + remark + "' where id=" + id);
        return flag;
    }

    public void batDel(String[] s) {
        this.bat("delete from vip where id=?", s);
    }
}
