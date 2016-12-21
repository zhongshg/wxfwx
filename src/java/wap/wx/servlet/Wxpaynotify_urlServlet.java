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
import wap.wx.util.WxReader;

/**
 *
 * @author Administrator
 */
public class Wxpaynotify_urlServlet extends HttpServlet {

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
            System.out.println("postStr " + postStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(postStr);
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
                System.out.println(root.elementTextTrim("sign") + " ");
                Map<String, String> tempMap = new HashMap<String, String>();
                tempMap.put("appid", appid);
                tempMap.put("mch_id", mch_id);
                tempMap.put("device_info", device_info);
                tempMap.put("nonce_str", nonce_str);

                //此处取出微信信息
                String sign = wxPayUtils.getSignature(tempMap, WxReader.getWxInfo().get("key"));
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
                    String time_end = root.elementTextTrim("time_end");
                    //支付成功处理
                    //判断有无处理
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
                            Logger.getLogger(Wxpaynotify_urlServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DatabaseException ex) {
                            Logger.getLogger(Wxpaynotify_urlServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //更改会员身份
                        subscriberDAO.updateVip(openid, 1);

//发送订单支付通知
                        //获取商品信息
                        List<DataField> basketlist = (List<DataField>) DaoFactory.getBasketDAO().getListByOrderNo(order.getFieldValue("F_No"));
                        String productStr = "";
                        for (DataField basket : basketlist) {
                            productStr += basket.getFieldValue("Pname") + " ";
                        }
                        String content = "尊敬的业主，您手机号：" + order.getFieldValue("Phone") + " 购买的 " + productStr + " 已支付成功，请您于明日16:00前至银座花园中心广场处领取您的商品，谢谢您的支持【万巷坊】";
//                        String content = "您在" + WxMenuUtils.format.format(new Date(System.currentTimeMillis())) + "成功付款，订单号为：" + order.getFieldValue("F_No") + "；订单金额为：" + order.getFieldValue("TF_Price") + "元。";
//                        content += "联系人：" + order.getFieldValue("Name") + ";";
//                        content += "电话：" + order.getFieldValue("Phone") + ";";
//                        DataField province = DaoFactory.getAreaDaoImplJDBC().get(order.getFieldValue("provience"));
//                        DataField city = DaoFactory.getAreaDaoImplJDBC().get(order.getFieldValue("city"));
//                        DataField area = DaoFactory.getAreaDaoImplJDBC().get(order.getFieldValue("area"));
//                        content += "地址：" + (null != province ? province.getFieldValue("title") : "") + (null != city ? city.getFieldValue("title") : "") + (null != area ? area.getFieldValue("title") : "") + order.getFieldValue("Address") + ";";
//                        content += "联系人：" + order.getFieldValue("Name") + ";";
                        WxMenuUtils.sendCustomService(openid, content);

                        //发送上级通知
                        Map<String, String> subscriber = subscriberDAO.getByOpenid(openid);
                        Map<String, String> parentsubscriber = subscriberDAO.getById(subscriber.get("parentopenid"));
//                    Map<String, String> map = new HashMap<String, String>();
//                    Map<String, Map<String, String>> mapmap = new HashMap<String, Map<String, String>>();
//                    Map<String, String> tempmap = null;
//                    if (null != parentsubscriber.get("openid")) {
//                        map.put("touser", parentsubscriber.get("openid"));
//                        map.put("template_id", "A_yMd2rsnxWuSqlnG2MW3hvMJQPPJi57A_18CpyCG94");
//                        map.put("url", Sysconfig.getLoginActionUrl() + "shop2/orderlist.jsp?openid=" + openid + "&F_No=" + order.getFieldValue("F_No"));
//                        map.put("topcolor", "#FF0000");
//                        //nickname
//                        tempmap = new HashMap<String, String>();
//                        tempmap.put("value", subscriber.get("nickname"));
//                        tempmap.put("color", "#173177");
//                        mapmap.put("nickname", tempmap);
//                        //times
//                        tempmap = new HashMap<String, String>();
//                        tempmap.put("value", WxMenuUtils.format.format(new Date(System.currentTimeMillis())));
//                        tempmap.put("color", "#173177");
//                        mapmap.put("times", tempmap);
//                        //orderno
//                        tempmap = new HashMap<String, String>();
//                        tempmap.put("value", order.getFieldValue("F_No"));
//                        tempmap.put("color", "#173177");
//                        mapmap.put("orderno", tempmap);
//                        //ordermoney
//                        tempmap = new HashMap<String, String>();
//                        tempmap.put("value", order.getFieldValue("Tot_Price"));
//                        tempmap.put("color", "#173177");
//                        mapmap.put("ordermoney", tempmap);
//                        //commission
//                        tempmap = new HashMap<String, String>();
//                        tempmap.put("value", order.getFieldValue("FirstYJ"));
//                        tempmap.put("color", "#173177");
//                        mapmap.put("commission", tempmap);
//                        wxPayUtils.sendtemplatemessage(map, mapmap);
//                    }
                        content = "您的大掌柜【" + subscriber.get("nickname") + "】在" + WxMenuUtils.format.format(new Date(System.currentTimeMillis())) + "付款，订单号为：" + order.getFieldValue("F_No") + "；付款金额为：" + order.getFieldValue("SF_Price") + "元；您将获得的提成为：" + order.getFieldValue("FirstYJ") + "元。";
                        WxMenuUtils.sendCustomService(parentsubscriber.get("openid"), content);
                        System.out.println("异步工作成功完成！");
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
