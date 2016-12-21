/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

import wap.wx.dao.ActivityDAO;
import wap.wx.dao.ItemsmDAO;
import wap.wx.dao.MaterialDAO;
import wap.wx.dao.NativeDAO;
import wap.wx.dao.OpenprizeDAO;
import wap.wx.dao.PrizeDAO;
import wap.wx.dao.SubscriberDAO;
import wap.wx.dao.SubscriberdetailDAO;
import wap.wx.dao.SubscriberpromoteDAO;
import wap.wx.dao.TextDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.util.WxReader;

public class WxServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String TOKEN = WxReader.getWxInfo().get("Token");
    private SubscriberDAO subscriberDAO = new SubscriberDAO();
    private SubscriberdetailDAO subscriberdetailDAO = new SubscriberdetailDAO();
    private NativeDAO nativeDAO = new NativeDAO();
    private TextDAO textDAO = new TextDAO();
    private ItemsmDAO itemsmDAO = new ItemsmDAO();
    private MaterialDAO materialDAO = new MaterialDAO();
    private ActivityDAO activityDAO = new ActivityDAO();
    private OpenprizeDAO openprizeDAO = new OpenprizeDAO();
    private PrizeDAO prizeDAO = new PrizeDAO();
    private Map<String, String> subscriber = new HashMap<String, String>();
    private Map<String, String> subscriberdetail = new HashMap<String, String>();
    private Map<String, String> natives = new HashMap<String, String>();
    private Map<String, String> texts = new HashMap<String, String>();
    private Map<String, String> newsm = new HashMap<String, String>();
    private Map<String, String> material = new HashMap<String, String>();
    private Map<String, String> activity = new HashMap<String, String>();
    private Map<String, String> openprize = new HashMap<String, String>();
    private Map<String, String> prize = new HashMap<String, String>();

    //自动回复内容
    public void responseMsg(HttpServletRequest request, HttpServletResponse response) {
        String postStr = null;
        try {
            postStr = this.readStreamParameter(request.getInputStream());
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
            if (null == document) {
                this.print(request, response, "");
                return;
            }
            Element root = document.getRootElement();
            String msgType = root.elementTextTrim("MsgType");
            String fromUsername = root.elementText("FromUserName");
            String toUsername = root.elementText("ToUserName");
            String keyword = "";
            try {
                keyword = root.elementTextTrim("Content");
            } catch (Exception e) {
            }
            String time = new Date().getTime() + "";
            String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

            //id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times
            subscriber = subscriberDAO.getByOpenid(fromUsername);
            subscriber.put("wxsid", "1");

            //用户发送语音消息时转为文本消息
            if ("voice".equals(msgType)) {
                keyword = root.elementText("Recognition");
                msgType = "text";
            }

            //用户输入文本消息时自动回复
            String event_key = "";
            if ("text".equals(msgType)) {
                //id,openid,dates,msgtype,event,content,mark  subscriberdetail
                subscriberdetail.put("openid", fromUsername);
                subscriberdetail.put("dates", date);
                subscriberdetail.put("msgtype", msgType);
                subscriberdetail.put("event", "");
                subscriberdetail.put("content", keyword);
                subscriberdetail.put("mark", "");
                subscriberdetailDAO.add(subscriberdetail);

                //id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent
                activity.put("activitykey", keyword);
                activity = activityDAO.getByActivitykey(activity);
                if (null != activity.get("id")) {
                    openprize.put("aid", activity.get("id"));
                    openprize.put("openid", fromUsername);
                    openprize.put("state", "1");
                    List<Map<String, String>> openprizeList = openprizeDAO.getByOpenprizeList(activity, openprize);
                    StringBuilder content = new StringBuilder();
                    String text = "<xml>"
                            + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                            + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                            + "<CreateTime>%3$s</CreateTime>"
                            + "<MsgType><![CDATA[%4$s]]></MsgType>"
                            + "<Content><![CDATA[%5$s]]></Content>"
                            + "</xml>";
                    if (0 != openprizeList.size()) {
                        content.append("恭喜您，您获得的奖项是");
                        for (Map<String, String> openprize : openprizeList) {
                            prize.put("id", openprize.get("pid"));
                            prize = prizeDAO.getById(prize);
                            content.append("\n").append(prize.get("codename")).append(":").append(prize.get("name"));
                        }
                    } else {
                        content.append("很抱歉，没有查询到您的中奖信息");
                    }
                    String resultStr = text.format(text, fromUsername, toUsername, time, msgType, content.toString());
                    this.print(request, response, resultStr);
                    return;
                }

                natives.put("name", keyword);
                natives = nativeDAO.getByName(natives);
                if (null == natives.get("id")) {
                    natives.put("wds", "2");
                    natives = nativeDAO.getByWds(natives);
                    if (null == natives.get("id")) {
                        return;
                    }
                }
                //id,name,type,lid,remark,wds
                event_key = natives.get("type") + "," + natives.get("lid");
                this.print(request, response, this.getContent(request, response, toUsername, fromUsername, time, event_key));
            }

            // 事件推送
            if (msgType.equals("event")) {
                // 事件类型
                String eventType = root.elementTextTrim("Event");

                //id,openid,dates,msgtype,event,content,mark  subscriberdetail
                subscriberdetail.put("openid", fromUsername);
                subscriberdetail.put("dates", date);
                subscriberdetail.put("msgtype", msgType);
                subscriberdetail.put("event", eventType);
                subscriberdetail.put("content", "");
                subscriberdetail.put("mark", "");
                subscriberdetailDAO.add(subscriberdetail);

                // 订阅发送文本消息
                if (eventType.equals("subscribe")) {
                    String parentopenid = "0";
                    /*try {
                        String eventKey = root.elementTextTrim("EventKey");
                        //String Ticket = root.elementTextTrim("Ticket");
                        if (eventKey.split("_").length ==2 && !eventKey.split("_")[1].equals(fromUsername)) {
                            parentopenid = eventKey.split("_")[1];
                        } else {
                        }
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }*/

                    if (null == subscriber.get("id")) {
                        subscriber.put("openid", fromUsername);
                        subscriber.put("percent", "0");
                        subscriber.put("remark", "");
                        subscriber.put("times", date);
                        //parentopenid,salemoney,commission,isvip,vipid
                        subscriber.put("parentopenid", parentopenid);
                        subscriber.put("salemoney", "0");
                        subscriber.put("commission", "0");
                        subscriber.put("isvip", "0");

                        //vipid 作为相对id
                        int tempcount = subscriberDAO.getCount(parentopenid);
                        subscriber.put("vipid", String.valueOf(tempcount + 1));

                        subscriber.put("qrcode", "");
                        subscriber.put("qrcodemediaid", "");
                        subscriber.put("qrcodemediaidtimes", String.valueOf(System.currentTimeMillis() / 1000 - 9 * 24 * 60 * 60));

                        JSONObject object = WxMenuUtils.getUserInfo(fromUsername);
                        object = null != object ? object : new JSONObject();
                        subscriber.put("nickname", object.getString("nickname"));
                        subscriber.put("sex", null != object.getString("sex") ? object.getString("sex") : "0");
                        subscriber.put("language", object.getString("language"));
                        subscriber.put("city", object.getString("city"));
                        subscriber.put("province", object.getString("province"));
                        subscriber.put("country", object.getString("country"));
                        subscriber.put("headimgurl", object.getString("headimgurl"));
                        subscriber.put("unionid", object.getString("unionid"));
                        try {
                            subscriberDAO.add(subscriber);
                        } catch (Exception e) {
                            System.out.println("用户关注nickname特殊字符,数据库存储error,存储空！");
                            subscriber.put("nickname", "");
                            subscriberDAO.add(subscriber);
                        }
                    }

                    natives.put("wds", "1");
                    natives = nativeDAO.getByWds(natives);
                    if (null == natives.get("id")) {
                        return;
                    }
                    //id,name,type,lid,remark,wds
                    event_key = natives.get("type") + "," + natives.get("lid");
                    this.print(request, response, this.getContent(request, response, toUsername, fromUsername, time, event_key));
                } else if (eventType.equals("SCAN")) {//关注后扫描事件
                    String parentopenid = "0";
                    //id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times
                    subscriber = subscriberDAO.getByOpenid(fromUsername);
                    if (null != subscriber.get("id")) {
//                        System.out.println("updateparentopenid " + parentopenid);
//                        subscriberDAO.updateParentopenid(fromUsername, String.valueOf(wxsid), parentopenid);
                    } else {
                        subscriber.put("openid", fromUsername);
                        subscriber.put("percent", "0");
                        subscriber.put("remark", "");
                        subscriber.put("times", date);
                        //parentopenid,salemoney,commission,isvip,vipid
                        subscriber.put("parentopenid", parentopenid);
                        subscriber.put("salemoney", "0");
                        subscriber.put("commission", "0");
                        subscriber.put("isvip", "0");
                        //vipid 作为相对id
                        int tempcount = subscriberDAO.getCount(parentopenid);
                        subscriber.put("vipid", String.valueOf(tempcount + 1));

                        subscriber.put("qrcode", "");
                        subscriber.put("qrcodemediaid", "");
                        subscriber.put("qrcodemediaidtimes", String.valueOf(System.currentTimeMillis() / 1000 - 9 * 24 * 60 * 60));

                        JSONObject object = WxMenuUtils.getUserInfo(fromUsername);
                        object = null != object ? object : new JSONObject();
                        subscriber.put("nickname", object.getString("nickname"));
                        subscriber.put("sex", null != object.getString("sex") ? object.getString("sex") : "0");
                        subscriber.put("language", object.getString("language"));
                        subscriber.put("city", object.getString("city"));
                        subscriber.put("province", object.getString("province"));
                        subscriber.put("country", object.getString("country"));
                        subscriber.put("headimgurl", object.getString("headimgurl"));
                        subscriber.put("unionid", object.getString("unionid"));
                        try {
                            subscriberDAO.add(subscriber);
                        } catch (Exception e) {
                            System.out.println("用户关注nickname特殊字符,数据库存储error,存储空！");
                            subscriber.put("nickname", "");
                            subscriberDAO.add(subscriber);
                        }
                    }
                    natives.put("wds", "1");
                    natives = nativeDAO.getByWds(natives);
                    if (null == natives.get("id")) {
                        return;
                    }
                    //id,name,type,lid,remark,wds
                    event_key = natives.get("type") + "," + natives.get("lid");
                    this.print(request, response, this.getContent(request, response, toUsername, fromUsername, time, event_key));
                }// 取消订阅
                else if (eventType.equals("unsubscribe")) {
                	// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                    subscriber.put("openid", fromUsername);
                    //取消关注记录
                    subscriberDAO.deleteByOpenid(subscriber);
                } // 自定义菜单点击事件
                else if (eventType.equals("CLICK")) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = root.elementTextTrim("EventKey");
                    request.setAttribute("openid", fromUsername);
                    this.print(request, response, this.getContent(request, response, toUsername, fromUsername, time, eventKey));
                }
            } 
        } else {
            this.print(request, response, "");
        }
    }

    //回复
    public String getContent(HttpServletRequest request, HttpServletResponse response, String toUsername, String fromUsername, String time, String event_key) {
        String return_text = "";
        String[] messageType = event_key.split(",");
        if ("transfer_customer_service".equals(messageType[0])) {//客服
            String text = "<xml>"
                    + "<ToUserName><![CDATA[" + fromUsername + "]]></ToUserName>"
                    + "<FromUserName><![CDATA[" + toUsername + "]]></FromUserName>"
                    + "<CreateTime>" + time + "</CreateTime>"
                    + "<MsgType><![CDATA[transfer_customer_service]]></MsgType>"
                    + "</xml>";
            return_text = text;//.format(text, fromUsername, toUsername, time, "transfer_customer_service");
        } else if ("text".equals(messageType[0])) {
            //id,name,describers
            texts.put("id", messageType[1]);
            texts = textDAO.getById(texts);
            String text = "<xml>"
                    + "<ToUserName><![CDATA[" + fromUsername + "]]></ToUserName>"
                    + "<FromUserName><![CDATA[" + toUsername + "]]></FromUserName>"
                    + "<CreateTime>" + time + "</CreateTime>"
                    + "<MsgType><![CDATA[text]]></MsgType>"
                    + "<Content><![CDATA[" + texts.get("describers") + "]]></Content>"
                    + "</xml>";
            return_text = text;//.format(text, fromUsername, toUsername, time, "text", texts.get("describers"));
            System.out.println(return_text);
        } else if ("news".equals(messageType[0])) {
            //id,name,describes,img,url,remark
            newsm.put("id", messageType[1]);
            List<Map<String, String>> itemsmList = itemsmDAO.getByNewsmList(newsm);
            String contentStr = "";
            for (Map<String, String> itemsm : itemsmList) {
                String picUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + itemsm.get("img");
                String urlAf = itemsm.get("url").indexOf("?") < 0 ? "?openid=" : "&openid=";
                String url = itemsm.get("url") + urlAf + fromUsername;// + "#mp.weixin.qq.com";
                contentStr += ("<item><Title><![CDATA[" + itemsm.get("name") + "]]></Title> "
                        + "<Description><![CDATA[" + itemsm.get("describes") + "]]></Description>"
                        + "<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>"
                        + "<Url><![CDATA[" + url + "]]></Url>"
                        + "</item>");
            }
            String text = "<xml>"
                    + "<ToUserName><![CDATA[" + fromUsername + "]]></ToUserName>"
                    + "<FromUserName><![CDATA[" + toUsername + "]]></FromUserName>"
                    + "<CreateTime>" + time + "</CreateTime>"
                    + "<MsgType><![CDATA[news]]></MsgType>"
                    + "<ArticleCount>" + itemsmList.size() + "</ArticleCount>"
                    + "<Articles>"
                    + contentStr
                    + "</Articles>"
                    + "</xml>";
            return_text = text;//.format(text, fromUsername, toUsername, time, "news");
        } else if ("image".equals(messageType[0]) || "thumb".equals(messageType[0])) {
            //id,name,describers
            material.put("id", messageType[1]);
            material = materialDAO.getById(material);
            String text = "<xml>"
                    + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                    + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                    + "<CreateTime>%3$s</CreateTime>"
                    + "<MsgType><![CDATA[%4$s]]></MsgType>"
                    + "<Image>"
                    + "<MediaId><![CDATA[%5$s]]></MediaId>"
                    + "</Image>"
                    + "</xml>";
            return_text = text.format(text, fromUsername, toUsername, time, "image", material.get("mediaid"));
        } else if ("voice".equals(messageType[0])) {
            //id,name,describers
            material.put("id", messageType[1]);
            material = materialDAO.getById(material);
            String text = "<xml>"
                    + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                    + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                    + "<CreateTime>%3$s</CreateTime>"
                    + "<MsgType><![CDATA[%4$s]]></MsgType>"
                    + "<Voice>"
                    + "<MediaId><![CDATA[%5$s]]></MediaId>"
                    + "</Voice>"
                    + "</xml>";
            return_text = text.format(text, fromUsername, toUsername, time, "voice", material.get("mediaid"));
        } else if ("video".equals(messageType[0])) {
            //id,name,describers
            material.put("id", messageType[1]);
            material = materialDAO.getById(material);
            String text = "<xml>"
                    + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                    + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                    + "<CreateTime>%3$s</CreateTime>"
                    + "<MsgType><![CDATA[%4$s]]></MsgType>"
                    + "<Video>"
                    + "<MediaId><![CDATA[%5$s]]></MediaId>"
                    + "<Title>" + material.get("title") + "</Title>"
                    + "<Description>" + material.get("description") + "</Description>"
                    + "</Video>"
                    + "</xml>";
            return_text = text.format(text, fromUsername, toUsername, time, "video", material.get("mediaid"));
        } else if ("music".equals(messageType[0])) {
            //id,name,describers
            material.put("id", messageType[1]);
            material = materialDAO.getById(material);
            String text = "<xml>"
                    + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                    + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                    + "<CreateTime>%3$s</CreateTime>"
                    + "<MsgType><![CDATA[%4$s]]></MsgType>"
                    + "<Music>"
                    + "<Title>" + material.get("title") + "</Title>"
                    + "<Description>" + material.get("description") + "</Description>"
                    + "<MusicUrl>" + material.get("musicurl") + "</MusicUrl>"
                    + "<HQMusicUrl>" + material.get("hqmusicurl") + "</HQMusicUrl>"
                    + "<ThumbMediaId><![CDATA[%5$s]]></ThumbMediaId>"
                    + "</Music>"
                    + "</xml>";
            return_text = text.format(text, fromUsername, toUsername, time, "music", material.get("mediaid"));
        } else if ("qrcode".equals(messageType[0])) {
            //id,name,describers
            String text = "<xml>"
                    + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                    + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                    + "<CreateTime>%3$s</CreateTime>"
                    + "<MsgType><![CDATA[%4$s]]></MsgType>"
                    + "<Image>"
                    + "<MediaId><![CDATA[%5$s]]></MediaId>"
                    + "</Image>"
                    + "</xml>";
            return_text = text.format(text, fromUsername, toUsername, time, "image", messageType[1]);
        }
        //返回输出值
        System.out.println("return_text="+return_text);
        return return_text;
    }

    protected void valid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String echostr = request.getParameter("echostr");
        if (null == echostr || echostr.isEmpty()) {
            responseMsg(request, response);
        } else {
            if (this.checkSignature(request, response)) {
                this.print(request, response, echostr);
            } else {
                this.print(request, response, echostr);
            }
        }
    }

    /**微信接口验证*/
    public boolean checkSignature(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String token = TOKEN;
        String[] tmpArr = {token, timestamp, nonce};
        Arrays.sort(tmpArr);
        String tmpStr = this.ArrayToString(tmpArr);
        tmpStr = this.SHA1Encode(tmpStr);
        if (tmpStr.equalsIgnoreCase(signature)) {
            return true;
        } else {
            return false;
        }
    }

    /**向请求端发送返回数据*/
    public void print(HttpServletRequest request, HttpServletResponse response, String content) {
        try {
            PrintWriter out = response.getWriter();
            out.print(content);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }

    /**数组转字符串*/
    public String ArrayToString(String[] arr) {
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            bf.append(arr[i]);
        }
        return bf.toString();
    }

    /**sha1加密*/
    public String SHA1Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

    /**从输入流读取post参数*/
    public String readStreamParameter(ServletInputStream in) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
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
        return buffer.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        valid(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
