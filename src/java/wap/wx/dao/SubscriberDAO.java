/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.dao;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import wap.wx.util.DbConn;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class SubscriberDAO {

    public List<Map<String, String>> getList() {
        List<Map<String, String>> subscriberList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
                subscriberList.add(subscriber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriberList;
    }

    public Map<String, String> getByOpenid(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber where openid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriber;
    }

    public List<Map<String, String>> getList(PageUtil pu) {
        List<Map<String, String>> subscriberList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
                subscriberList.add(subscriber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriberList;
    }

    public int getCount() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from subscriber order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getCount(String parentopenid) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from subscriber where parentopenid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, parentopenid);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(String id) {
        Map<String, String> subscriber = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber where id=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, id);
            rs = ptst.executeQuery();
            if (rs.next()) {
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriber;
    }

    public Map<String, String> getByOpenid(String openid) {
        Map<String, String> subscriber = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber where openid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, openid);
            rs = ptst.executeQuery();
            if (rs.next()) {
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                	ex.printStackTrace();
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriber;
    }

    public Map<String, String> getByVipid(String vipid) {
        Map<String, String> subscriber = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber where vipid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, vipid);
            rs = ptst.executeQuery();
            if (rs.next()) {
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriber;
    }

    //微论坛赞排行
    public List<Map<String, String>> getVipGatItemsPraisecountList(String range) {
        List vipList = new LinkedList();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
//id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times
        String sql = new StringBuilder().append("select id,s.openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times,r.psums rcounts from subscriber s,(select author,sum(praisecount) psums from gatitems group by author) r where s.openid=r.author order by r.psums desc limit ").append(range).toString();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map vip = new HashMap();
                vip.put("id", rs.getString("id"));
                vip.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                vip.put("nickname", nickname);
                vip.put("sex", rs.getString("sex"));
                vip.put("language", rs.getString("language"));
                vip.put("city", rs.getString("city"));
                vip.put("province", rs.getString("province"));
                vip.put("country", rs.getString("country"));
                vip.put("headimgurl", rs.getString("headimgurl"));
                vip.put("unionid", rs.getString("unionid"));
                vip.put("percent", rs.getString("percent"));
                vip.put("remark", rs.getString("remark"));
                vip.put("times", rs.getString("times"));
                vip.put("rcounts", rs.getString("rcounts"));
                vipList.add(vip);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return vipList;
    }

    //vip话题排行
    public List<Map<String, String>> getVipDiscussConutList(String range) {
        List vipList = new LinkedList();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;

        String sql = new StringBuilder().append("select s.id,s.openid,s.nickname,s.sex,s.language,s.city,s.province,s.country,s.headimgurl,s.unionid,s.percent,s.remark,s.times,r.counts rcounts from subscriber s,gatitems g,(select vipid,d.counts from (select vipid,count(id) counts from discuss group by vipid) d) r where s.openid=g.author and g.id=r.vipid order by r.counts desc limit ").append(range).toString();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map vip = new HashMap();
                vip.put("id", rs.getString("id"));
                vip.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                vip.put("nickname", nickname);
                vip.put("sex", rs.getString("sex"));
                vip.put("language", rs.getString("language"));
                vip.put("city", rs.getString("city"));
                vip.put("province", rs.getString("province"));
                vip.put("country", rs.getString("country"));
                vip.put("headimgurl", rs.getString("headimgurl"));
                vip.put("unionid", rs.getString("unionid"));
                vip.put("percent", rs.getString("percent"));
                vip.put("remark", rs.getString("remark"));
                vip.put("times", rs.getString("times"));
                vip.put("rcounts", rs.getString("rcounts"));
                vipList.add(vip);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return vipList;
    }

    public void delete(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from subscriber where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("id"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> subscriber, Connection conn) {
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from subscriber where id=? ";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("id"));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByOpenid(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from subscriber where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set nickname=?,sex=?,language=?,city=?,province=?,country=?,headimgurl=?,unionid=?,remark=? where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            String nickname = subscriber.get("nickname");
            try {
                nickname = URLEncoder.encode(nickname, "utf-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println("nickname转义 " + nickname);
            }
            ptst.setString(1, nickname);
            ptst.setInt(2, Integer.parseInt(subscriber.get("sex")));
            ptst.setString(3, subscriber.get("language"));
            ptst.setString(4, subscriber.get("city"));
            ptst.setString(5, subscriber.get("province"));
            ptst.setString(6, subscriber.get("country"));
            ptst.setString(7, subscriber.get("headimgurl"));
            ptst.setString(8, subscriber.get("unionid"));
            ptst.setString(9, subscriber.get("remark"));
            ptst.setString(10, subscriber.get("openid"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    /**
     * 特区
     *
     * @param wxsid
     * @param parentopenid
     * @return
     */
    public List<Map<String, String>> getByParentopenidList(String parentopenid, Connection conn) {
        List<Map<String, String>> subscriberList = new ArrayList<Map<String, String>>();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber where parentopenid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, parentopenid);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
                subscriberList.add(subscriber);
            }
            rs.close();
            ptst.close();;
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subscriberList;
    }

    public List<Map<String, String>> getByParentopenidList(String parentopenid) {
        List<Map<String, String>> subscriberList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times from subscriber where parentopenid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, parentopenid);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                String nickname = rs.getString("nickname");
                try {
                    nickname = URLDecoder.decode(nickname, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("nickname转义 " + nickname);
                }
                subscriber.put("nickname", nickname);
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
                subscriberList.add(subscriber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriberList;
    }

    public void add(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into subscriber(openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            String nickname = subscriber.get("nickname");
            try {
                nickname = URLEncoder.encode(nickname, "utf-8");
            } catch (UnsupportedEncodingException ex) {
            	ex.printStackTrace();
                System.out.println("nickname转义 " + nickname);
            }
            ptst.setString(2, nickname);
            ptst.setInt(3, Integer.parseInt(subscriber.get("sex")));
            ptst.setString(4, subscriber.get("language"));
            ptst.setString(5, subscriber.get("city"));
            ptst.setString(6, subscriber.get("province"));
            ptst.setString(7, subscriber.get("country"));
            ptst.setString(8, subscriber.get("headimgurl"));
            ptst.setString(9, subscriber.get("unionid"));
            ptst.setInt(10, Integer.parseInt(subscriber.get("percent")));
            ptst.setString(11, subscriber.get("remark"));
            ptst.setString(12, subscriber.get("times"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateParentopenid(String openid, String parentopenid) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set parentopenid=? where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, parentopenid);
            ptst.setString(2, openid);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateVip(String openid, int isvip) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set isvip=? where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, isvip);
            ptst.setString(2, openid);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updatePercent(String openid, float percent) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set percent=percent+? where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setFloat(1, percent);
            ptst.setString(2, openid);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    //qrcode,qrcodemediaid,qrcodemediaidtimes
    public void updateqrcode(String openid, String qrcode, String qrcodemediaid, String qrcodemediaidtimes) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set qrcode=?,qrcodemediaid=?,qrcodemediaidtimes=? where openid=? ";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, qrcode);
            ptst.setString(2, qrcodemediaid);
            ptst.setString(3, qrcodemediaidtimes);
            ptst.setString(4, openid);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void deleteqrcode(String wxsid, String qrcode, String qrcodemediaid, Connection conn) {
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set qrcode=?,qrcodemediaid=? where wxsid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, qrcode);
            ptst.setString(2, qrcodemediaid);
            ptst.setString(3, wxsid);
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * parentopenid,salemoney,commission,isvip,vipid
     */
//    public void updateDistribution(String id, String wxsid,float nopaycommission) {
//        Connection conn = DbConn.getConn();
//        PreparedStatement ptst = null;
//        ResultSet rs = null;
//        String sql = "update subscriber set commission=commission+?,nopaycommission=nopaycommission+? where id=? and wxsid=?";
//        try {
//            ptst = conn.prepareStatement(sql);
//            ptst.setFloat(1, nopaycommission);
//            ptst.setFloat(2, nopaycommission);
//            ptst.setString(3, id);
//            ptst.setString(4, wxsid);
//            ptst.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            DbConn.getAllClose(conn, ptst, rs);
//        }
//    }
    public boolean updateTx(String openid, float commission) {
        boolean flag = false;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriber set commission=commission-? where openid=? ";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setFloat(1, commission);
            ptst.setString(2, openid);
            int i = ptst.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return flag;
    }

    public int getMaxid() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select max(id) count from subscriber";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }
}
