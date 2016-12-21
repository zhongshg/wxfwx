/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import job.tot.bean.DataField;
import job.tot.dao.DaoFactory;
import job.tot.exception.DatabaseException;
import job.tot.exception.ObjectNotFoundException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import wap.wx.dao.SubscriberDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.menu.WxPayUtils;
import wap.wx.service.PublicService;
import wap.wx.util.WxReader;

/**
 *
 * @author Administrator
 */
public class Wxpaynotify_urlServlet4 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("异步通知到了");
        //此处接收异步通知 见ar文档
        WxPayUtils wxPayUtils = new WxPayUtils();
        PrintWriter out = response.getWriter();
        SubscriberDAO subscriberDAO = new SubscriberDAO();

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String postStr = buffer.toString();
        try {
            postStr = new String(postStr.getBytes("gbk"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != postStr && !postStr.isEmpty()) {
            Document document = null;
            try {
                document = DocumentHelper.parseText(postStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Element root = document.getRootElement();

            String return_code = root.elementTextTrim("return_code");
            System.out.println("return_code " + return_code);
            if ("SUCCESS".equals(return_code)) {
                String appid = root.elementTextTrim("appid");
                String mch_id = root.elementTextTrim("mch_id");
                String device_info = root.elementTextTrim("device_info");
                String nonce_str = root.elementTextTrim("nonce_str");
                //验证签名
                Map<String, String> tempMap = new HashMap<String, String>();
                List<Element> elementList = root.elements();
                for (Element element : elementList) {
                    if ("sign".equals(element.getName())) {
                        continue;
                    }
                    tempMap.put(element.getName(), element.getText());
                }
                String sign = wxPayUtils.getSignature(tempMap, WxReader.getWxInfo().get("key"));
                System.out.println("万巷坊狗狗商城 sign " + root.elementTextTrim("sign") + " mysign " + sign);
//            if (sign.equals(root.elementTextTrim("sign"))) {
                System.out.println("返回值签名验证成功！");
                String result_code = root.elementTextTrim("result_code");
                if ("SUCCESS".equals(result_code)) {
                    String openid = root.elementTextTrim("openid");
                    String is_subscribe = root.elementTextTrim("is_subscribe");
                    String trade_type = root.elementTextTrim("trade_type");
                    String bank_type = root.elementTextTrim("bank_type");
                    String total_fee = root.elementTextTrim("total_fee");
                    String coupon_fee = root.elementTextTrim("coupon_fee");
                    String fee_type = root.elementTextTrim("fee_type");
                    String transaction_id = root.elementTextTrim("transaction_id");
                    String out_trade_no = root.elementTextTrim("out_trade_no");
                    String attach = root.elementTextTrim("attach");
                    System.out.println("attach " + attach);
                    String time_end = root.elementTextTrim("time_end");
                    //支付成功处理
                    //分解attach
                    String[] attachs = attach.split(",");//订单号 满减送 订单返现 优惠
                    String F_No = attachs[0];
                    int tuangouid = Integer.parseInt(attachs[1]);
                    int manjiansongid = Integer.parseInt(attachs[2]);
                    int dingdanfanxianid = Integer.parseInt(attachs[3]);
                    int zengpinid = Integer.parseInt(attachs[4]);
                    String shopactivityid = attachs[5];
                    String shopactivityidStr = "";
                    if ("-1".equals(shopactivityid)) {
                        shopactivityidStr = attachs[6];
                    }
                    System.out.println("F_No " + F_No + " manjiansongid " + manjiansongid + " dingdanfanxianid " + dingdanfanxianid + " zengpinid " + zengpinid + " shopactivityid " + shopactivityid);
                    //修改out_trade_no
                    try {
                        DaoFactory.getOrderDAO().modout_trade_no(F_No, out_trade_no);
                    } catch (ObjectNotFoundException ex) {
                        Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DatabaseException ex) {
                        Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //判断有无处理
                    System.out.println("out_trade_no订单异常 " + openid + " " + out_trade_no);
                    DataField order = DaoFactory.getOrderDAO().getByout_trade_no(out_trade_no);
                    System.out.println("order.getFieldValue(\"IsPay\")处理过没？ " + order.getFieldValue("IsPay"));
                    if ("1".equals(order.getFieldValue("IsPay"))) {
                        //更改订单状态 
                        DaoFactory.getOrderDAO().modIsPayByout_trade_no(out_trade_no, 2);
                        try {
                            DaoFactory.getOrderDAO().modStsByout_trade_no(out_trade_no, 1);
                            //添加transaction_id
                            DaoFactory.getOrderDAO().modtransaction_id(transaction_id, out_trade_no);
                        } catch (ObjectNotFoundException ex) {
                            Logger.getLogger(Wxpaynotify_urlServlet4.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DatabaseException ex) {
                            Logger.getLogger(Wxpaynotify_urlServlet4.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //更改会员身份 这里和现有会员不符 仅支持拓展
                        subscriberDAO.updateVip(openid, 1);
                        System.out.println("异步工作成功完成！");

                        StringBuffer message = new StringBuffer();
                        //团购处理
                        if (0 != tuangouid) {
                            DataField tuangou = DaoFactory.getShopactivityDAO().get(tuangouid);
                            List<DataField> prolist = (List<DataField>) DaoFactory.getBasketDAO().getListNo(F_No);
                            DataField basket = null;
                            for (DataField pro : prolist) {
                                if (pro.getInt("Pid") == tuangou.getInt("limitproduct")) {
                                    basket = pro;
                                    break;
                                }
                            }
                            message.append("\n团购优惠:减现金" + tuangou.getFloat("maaddtype") * basket.getInt("Pnum") + "元");
                            for (int i = 0; i < basket.getInt("Pnum"); i++) {
                                DaoFactory.getGiftsDAO().add(openid, tuangou.getFloat("maaddtype"), tuangouid, F_No, 1, WxMenuUtils.format.format(new Date()), "团购", 6);
                            }
                        }

                        //满减送处理
                        if (0 != manjiansongid) {
                            DataField manjiansong = DaoFactory.getShopactivityDAO().get(manjiansongid);
                            if (1 == manjiansong.getInt("matype")) {
                                message.append("\n满减优惠:减现金" + manjiansong.getFloat("maaddtype") + "元");
                            } else if (2 == manjiansong.getInt("matype")) {
                                message.append("\n满减优惠:免运费" + order.getFloat("TSF_Price") + "元");
                            } else if (3 == manjiansong.getInt("matype")) {
                                new SubscriberDAO().updatePercent(openid, manjiansong.getFloat("maaddtype"));
                                message.append("\n满减优惠:送积分" + manjiansong.getFloat("maaddtype") + "分");
                            }
                            DaoFactory.getGiftsDAO().add(openid, 1 == manjiansong.getInt("matype") ? manjiansong.getFloat("maaddtype") : 0, manjiansongid, F_No, 1, WxMenuUtils.format.format(new Date()), "满减送", 4);
                        }

                        //订单返现处理
                        if (0 != dingdanfanxianid) {
                            DataField dingdanfanxian = DaoFactory.getShopactivityDAO().get(dingdanfanxianid);
                            DaoFactory.getGiftsDAO().add(openid, dingdanfanxian.getFloat("minmoney") * order.getFloat("SF_Price") / 100, dingdanfanxianid, F_No, 1, WxMenuUtils.format.format(new Date()), "订单返现", 5);
                            message.append("\n订单返现减免:" + dingdanfanxian.getFloat("minmoney") * order.getFloat("SF_Price") / 100 + "元");
                        }

                        //优惠核销处理
                        System.out.println("youhuishopactivityid " + shopactivityidStr);
                        if ("-1".equals(shopactivityid)) {
                            System.out.println("优惠码未领取 " + shopactivityidStr);
                            //优惠码未领取
                            String macode = shopactivityidStr;
                            System.out.println("macode " + macode);
                            DataField youhuima = DaoFactory.getShopactivityDAO().getByMaaddtype(macode);//通用码取法
                            if (null == youhuima || (null != youhuima && 0 != youhuima.getInt("matype"))) {
                                DataField shopactivitydetail = DaoFactory.getShopactivitydetailDao().get(macode, 2);//一卡一码取法
                                if (null != shopactivitydetail && 0 == shopactivitydetail.getInt("giftid")) {
                                    youhuima = DaoFactory.getShopactivityDAO().get(shopactivitydetail.getInt("shopactivityid"));
                                }
                            }
                            if (null != youhuima) {
                                DaoFactory.getGiftsDAO().add(openid, youhuima.getFloat("moneys"), youhuima.getInt("id"), F_No, 1, WxMenuUtils.format.format(new Date()), "优惠码", 2);
                                int maxid = DaoFactory.getGiftsDAO().getMaxId();
                                DaoFactory.getShopactivitydetailDao().modBymacode(youhuima.getInt("id"), macode, maxid, WxMenuUtils.format.format(new Date()), 2, 1);
                                List<DataField> giftsyouhuiquanlogList = DaoFactory.getGiftsDAO().getByOpenidGiftidList(openid, youhuima.getInt("id"));//领取记录
                                if (0 == giftsyouhuiquanlogList.size()) {//未领取即为新用户
                                    DaoFactory.getShopactivityDAO().modallingsub(youhuima.getInt("id"));
                                }
                                DaoFactory.getShopactivityDAO().modallingtimes(youhuima.getInt("id"));
                                message.append("\n使用优惠码抵现：" + youhuima.getFloat("moneys") + "元");
                            }

                        } else {
                            DataField shopactivity = DaoFactory.getShopactivityDAO().get(Integer.parseInt(shopactivityid));
                            List<DataField> giftList = DaoFactory.getGiftsDAO().getByOpenidGiftidList(openid, null != shopactivity ? shopactivity.getInt("id") : 0, 0);
                            if (0 != giftList.size()) {
                                int giftid = giftList.get(0).getInt("id");
                                DaoFactory.getGiftsDAO().modisuse(openid, giftid);
                                DaoFactory.getGiftsDAO().modorderno(out_trade_no, openid, giftid);

                                if (1 == shopactivity.getInt("sid")) {
                                    message.append("\n使用优惠码抵现：" + shopactivity.getFloat("moneys") + "元");
                                } else if (1 == shopactivity.getInt("sid")) {
                                    message.append("\n使用优惠码抵现：" + shopactivity.getFloat("moneys") + "元");
                                }
                            }
                        }

                        //赠品处理
                        if (0 != zengpinid) {
                            DataField zengpin = DaoFactory.getShopactivityDAO().get(zengpinid);
                            DaoFactory.getGiftsDAO().add(openid, 0, zengpinid, F_No, 1, WxMenuUtils.format.format(new Date()), "赠品", 3);
                            if (0 == zengpin.getInt("matype")) {
                                message.append("\n赠品:赠送同等商品" + zengpin.getInt("paicounts") + "件");
                            } else {
                                message.append("\n赠品:赠送" + zengpin.getInt("name"));
                            }
                        }

                        //狗粮活动专用
                        String targetopenid = (String) request.getServletContext().getAttribute(openid + "targetopenid");
                        System.out.println("回调targetopenid " + targetopenid);
                        //取出订单明细
                        int pnum = 0;
                        List<DataField> basketList = (List<DataField>) DaoFactory.getBasketDAO().getListNo(order.getFieldValue("F_No"));
                        for (DataField basket : basketList) {
                            System.out.println("basket.getInt(\"Pid\") " + basket.getInt("Pid"));
                            if (1 == basket.getInt("Pid")) {
                                pnum += basket.getInt("Pnum");
                            }
                        }
                        if (0 != pnum && null != targetopenid && !"null".equals(targetopenid) && !"".equals(targetopenid)) {
                            //判断是否领养人
                            DataField testTarget = DaoFactory.getTargetDAO().getByOpenid(targetopenid);
                            if (null != testTarget) {
                                boolean flag = false;
                                do {
                                    //记录助养记录
                                    flag = DaoFactory.gettDetailDAO().add(openid, targetopenid, WxMenuUtils.format.format(new Date()), pnum * 5, "购买");
                                    if (flag) {
                                        flag = DaoFactory.getTargetDAO().updateCounts(targetopenid, pnum * 5);
                                    }
                                } while (!flag);
                            }
                        }
                        //理论上讲，这里的参数应该清除了，但有个别客户不退出重新购买
//                        request.getServletContext().removeAttribute(openid + "targetopenid");

                        //发送支付记录
                        Timestamp times = new Timestamp(System.currentTimeMillis());
                        Map<String, String> subscriber = subscriberDAO.getByOpenid(openid);
                        String content = "";
                        StringBuffer productinfo = new StringBuffer();
                        for (DataField basket : basketList) {
                            productinfo.append("\n商品名称：" + basket.getFieldValue("Pname"));
                            productinfo.append("\n商品数量：" + basket.getFieldValue("Pnum"));
                            productinfo.append("\n商品总计(含运费)：" + (basket.getFloat("Tot_Price") + basket.getFloat("TSF_Price")) + "元");
                            //会员处理， 打折，免运费 按会员等级值优先
                            DataField vip = new PublicService().getVip(openid);
                            float discount = 1f;
                            if (null != vip) {
                                discount = vip.getFloat("discount") / 10;
                                if (10 != vip.getInt("discount")) {
                                    productinfo.append("\n会员折扣后：" + (basket.getFloat("Tot_Price") * discount + basket.getFloat("TSF_Price")) + "元");
                                }
                                if (1 == vip.getInt("isyunfei")) {
                                    productinfo.append("\n会员免运费后：" + basket.getFloat("Tot_Price") * discount + "元");
                                }
                            }
                        }

                        //积分处理
                        List<DataField> percentList = DaoFactory.getPercentDao().getList();
                        for (DataField percent : percentList) {
                            if (2 == percent.getInt("type")) {
                                int totalordercount = DaoFactory.getOrderDAO().getTotalNum("1", openid, -1, 5);//Sts 5 已完成
                                if (totalordercount >= percent.getInt("limittrade")) {//符合条件
                                    new SubscriberDAO().updatePercent(openid, percent.getFloat("counts"));
                                    if (1 == percent.getInt("isnotice")) {
                                        message.append("\n积分优惠：获得积分：" + percent.getFloat("counts") + "分");
                                    }
                                    break;
                                }
                            } else if (3 == percent.getInt("type")) {
                                if (order.getFloat("F_Price") >= percent.getFloat("limitmoney")) {//符合条件
                                    new SubscriberDAO().updatePercent(openid, percent.getFloat("counts"));
                                    if (1 == percent.getInt("isnotice")) {
                                        message.append("\n积分优惠：获得积分：" + percent.getFloat("counts") + "分");
                                    }
                                    break;
                                }
                            }
                        }

                        content = "您在" + WxMenuUtils.format.format(times) + "成功付款，订单号为：" + order.getFieldValue("F_No") + "；订单金额为：" + order.getFieldValue("TF_Price") + "元；付款金额为：" + Float.parseFloat(total_fee) / 100 + "元，共计优惠" + (order.getFloat("TF_Price") - Float.parseFloat(total_fee) / 100) + "元。";
                        WxMenuUtils.sendCustomService(openid, content);
                        content = subscriber.get("nickname") + "在" + WxMenuUtils.format.format(times) + "付款，订单号为：" + order.getFieldValue("F_No") + "；订单总额为：" + order.getFieldValue("TF_Price") + "元；付款金额为：" + Float.parseFloat(total_fee) / 100 + "元，共计优惠" + (order.getFloat("TF_Price") - Float.parseFloat(total_fee) / 100) + "元"
                                + "\n以下为购买商品信息：" + productinfo.toString() + "\n以下为本次优惠信息：" + message;
                        //指定客服
//                        WxMenuUtils.sendCustomService("ou24btx0g0mJcSoblajwCeEaeGQc", content);
//                        WxMenuUtils.sendCustomService("ou24bt5xqpsHxYb_yiyFYuz-FgPg", content);
                        WxMenuUtils.sendCustomService("oSoAcuAyTCDLn8QZm2KrIC2ajzWA", content);//测试

                        //附加：优惠券 份
//                        if (Integer.parseInt(total_fee) >= 1000) {
//                            String coupon_stock_id = "1";//这个是添加时定义的id.决定了金额
////                            发放代金券（立减优惠）
//                            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
//                            //商户单据号
//                            String partner_trade_no = WxReader.getWxInfo().get("mch_id") + WxMenuUtils.formatd.format(new Date()) + String.valueOf(new Random().nextInt(10000)) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用
//
//                            //组织参数
//                            Map<String, String> send_coupon = new HashMap<String, String>();
//                            send_coupon.put("coupon_stock_id", coupon_stock_id);
//                            send_coupon.put("openid_count", "1");//openid记录数，1
//                            send_coupon.put("partner_trade_no", partner_trade_no);
//                            send_coupon.put("openid", openid);
//                            send_coupon.put("appid", WxReader.getWxInfo().get("AppId"));//公众账号id
//                            send_coupon.put("mch_id", WxReader.getWxInfo().get("mch_id"));//商户号
//                            send_coupon.put("nonce_str", timeStamp);//随机字符串
//
////        map.put("op_user_id", WxReader.getWxInfo().get("mch_id"));//操作员 否
////        map.put("device_info", "WEB");//设备号 否
////        map.put("version", "1.0");//协议版本 否
////        map.put("type", "XML");//协议类型 否
//                            send_coupon = new WxPayUtils().send_coupon(send_coupon, WxReader.getWxInfo().get("key"));
//                            if ("SUCCESS".equals(send_coupon.get("result_code"))) {
//                                //添加记录
//                                boolean flag = DaoFactory.getGiftsDAO().add(openid, Float.parseFloat(coupon_stock_id), partner_trade_no, 0, WxMenuUtils.format.format(new Date()), "优惠券", 1);
//                                System.out.println("代金券发放成功：" + flag);
//                            } else {
//                                System.out.println("代金券发放失败：");
//                            }
//
//                        }

//                        //附加：红包 份
//                        if (Integer.parseInt(total_fee) >= 1000) {
//                            String mch_billno = WxReader.getWxInfo().get("mch_id") + WxMenuUtils.formatd.format(new Date()) + String.valueOf(new Random().nextInt(10000)) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用
//                            Map<String, String> sendredpacklogs = new HashMap<String, String>();
//                            sendredpacklogs.put("nonce_str", nonce_str);
//                            sendredpacklogs.put("mch_id", mch_id);
//                            sendredpacklogs.put("mch_billno", mch_billno);
//                            sendredpacklogs.put("wxappid", appid);
//                            sendredpacklogs.put("send_name", "小狗队");
//                            sendredpacklogs.put("re_openid", openid);
//                            sendredpacklogs.put("total_amount", "100");
//                            sendredpacklogs.put("total_num", "1");
//                            sendredpacklogs.put("wishing", "恭喜您获得红包一枚");
//                            sendredpacklogs.put("client_ip", request.getRemoteAddr());
//                            sendredpacklogs.put("act_name", "微信支付抢红包");
//                            sendredpacklogs.put("remark", "支付越多红包越大");
//                            boolean flag = new WxPayUtils().sendredpack(request, sendredpacklogs, openid, "100", request.getRemoteAddr());
//                            if (flag) {
//                                //添加记录
//                                flag = DaoFactory.getGiftsDAO().add(openid, Float.parseFloat("100"), mch_billno, 0, WxMenuUtils.format.format(new Date()), "红包", 2);
//                            }
//                        }
                    }
                } else {
                    System.out.println("err_code  " + root.elementTextTrim("err_code"));
                    System.out.println("err_code_des  " + root.elementTextTrim("err_code_des"));
                    //支付异常，被动查询订单
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("appid", appid);
                    map.put("mch_id", mch_id);
                    map.put("out_trade_no", root.elementTextTrim("out_trade_no"));
                    map.put("nonce_str", nonce_str);
                    map = wxPayUtils.payorderquery(map, WxReader.getWxInfo().get("key"));
                    System.out.println("查询订单！");
                }
//            } else {
//                System.out.println("返回值签名验证失败！");
//            }
            } else {
                System.out.println("return_msg  " + root.elementTextTrim("return_msg"));
                System.out.println("异步工作失败成！");
            }
            String params = "<xml>"
                    + "   <return_code>" + root.elementTextTrim("return_code") + "</return_code>"
                    + "   <return_msg>" + root.elementTextTrim("return_msg") + "</return_msg>"
                    + "</xml>";
            System.out.println("回调params " + params);
            out.print(params);
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
