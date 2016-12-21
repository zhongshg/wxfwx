/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
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
import wap.wx.menu.WxPayUtils;
import wap.wx.util.WxReader;

/**
 *
 * @author Administrator
 */
public class Wxpaynotify_urlServlet2 extends HttpServlet {

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
                    //更改订单状态 
                    DaoFactory.getOrder2DaoImplJDBC().modIsPayByout_trade_no(out_trade_no, 2);
                    try {
                        DaoFactory.getOrder2DaoImplJDBC().modStsByout_trade_no(out_trade_no, 1);
                        //添加transaction_id
                        DaoFactory.getOrder2DaoImplJDBC().modtransaction_id(transaction_id, out_trade_no);
                    } catch (ObjectNotFoundException ex) {
                        Logger.getLogger(Wxpaynotify_urlServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DatabaseException ex) {
                        Logger.getLogger(Wxpaynotify_urlServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //发送上级通知
                    Map<String, String> subscriber = subscriberDAO.getByOpenid(openid);
                    DataField order = DaoFactory.getOrder2DaoImplJDBC().getByout_trade_no(out_trade_no);
                    Map<String, String> map = new HashMap<String, String>();
                    Map<String, Map<String, String>> mapmap = new HashMap<String, Map<String, String>>();
                    Map<String, String> tempmap = null;
                    map.put("touser", openid);
                    map.put("template_id", "lZNixszaRXSq9cIFL6QfLI8nv5Rs4K_BT5o6Z-vCmaU");
                    map.put("url", "");
                    map.put("topcolor", "#FF0000");
                    //first
                    tempmap = new HashMap<String, String>();
                    tempmap.put("value", "您好，您的订单已付款成功");
                    tempmap.put("color", "#173177");
                    mapmap.put("first", tempmap);
                    //keyword1
                    tempmap = new HashMap<String, String>();
                    tempmap.put("value", order.getFieldValue("fno"));
                    tempmap.put("color", "#173177");
                    mapmap.put("keyword1", tempmap);
                    //keyword2
                    tempmap = new HashMap<String, String>();
                    tempmap.put("value", order.getFieldValue("fdate"));
                    tempmap.put("color", "#173177");
                    mapmap.put("keyword2", tempmap);
                    //keyword3
                    tempmap = new HashMap<String, String>();
                    tempmap.put("value", order.getFieldValue("sfprice"));
                    tempmap.put("color", "#173177");
                    mapmap.put("keyword3", tempmap);
                    //keyword4
                    tempmap = new HashMap<String, String>();
                    tempmap.put("value", "微信支付");
                    tempmap.put("color", "#173177");
                    mapmap.put("keyword4", tempmap);
                    //remark
                    tempmap = new HashMap<String, String>();
                    tempmap.put("value", "感谢您的惠顾");
                    tempmap.put("color", "#173177");
                    mapmap.put("remark", tempmap);
                    wxPayUtils.sendtemplatemessage(map, mapmap);
//                    String content = "您的大掌柜【" + subscriber.get("nickname") + "】在" + WxMenuUtils.format.format(new Date(System.currentTimeMillis())) + "付款，订单号为：" + order.getFieldValue("F_No") + "；付款金额为：" + order.getFieldValue("SF_Price") + "元；您将获得的提成为：" + order.getFieldValue("FirstYJ") + "元。";
//                    WxMenuUtils.sendCustomService(parentsubscriber.get("openid"), content);
                    System.out.println("异步工作成功完成！");
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
                System.out.println("异步工作失败！");
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
