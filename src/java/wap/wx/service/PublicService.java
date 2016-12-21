/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.service;

import java.util.List;
import java.util.Map;
import job.tot.bean.DataField;
import job.tot.dao.DaoFactory;
import wap.wx.dao.SubscriberDAO;

/**
 *
 * @author Administrator
 */
public class PublicService {

    /**
     * 判断会员返回 返回会员类型 null为非会员
     */
    public DataField getVip(String openid) {
        DataField vip = null;
        List<DataField> vipList = DaoFactory.getVipDao().getList();
        for (DataField tempvip : vipList) {
            if (1 == tempvip.getInt("type")) {//如果是按交易笔数核算
                int totalordercount = DaoFactory.getOrderDAO().getTotalNum("1", openid, -1, 5);//Sts 5 已完成
                if (totalordercount >= tempvip.getInt("limittrade")) {//符合条件
                    vip = tempvip;
                    break;
                }
            } else if (2 == tempvip.getInt("type")) {//如果是按累计消费金额核算
                float totalordermoney = 0f;
                List<DataField> totalorderList = (List<DataField>) DaoFactory.getOrderDAO().getList("1", openid, 5, -1);
                for (DataField order : totalorderList) {
                    totalordermoney += order.getFloat("F_Price");//含运费
                }
                if (totalordermoney >= tempvip.getFloat("limitmoney")) {//符合条件
                    vip = tempvip;
                    break;
                }
            } else if (3 == tempvip.getInt("type")) {//如果是按累计积分核算
                Map<String, String> subscriber = new SubscriberDAO().getByOpenid(openid);
                if (Float.parseFloat(subscriber.get("percent")) >= tempvip.getFloat("limitpercent")) {//符合条件
                    vip = tempvip;
                    break;
                }
            }
        }
        return vip;
    }
}
