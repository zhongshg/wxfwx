package wap.wx.menu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import wap.wx.dao.ApiDAO;
import wap.wx.dao.NewstypesDAO;
import wap.wx.dao.SaleMemberDAO;
import wap.wx.util.ImageUtils;
import wap.wx.util.MatrixToImageWriter;
import wap.wx.util.WxReader;

/**
 * 微信自定义菜单创建
 */
public class WxMenuUtils {

	public static CloseableHttpClient httpclient;
	public static CloseableHttpClient httpsclient;
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatd = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat formatdt = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final String APPID = WxReader.getWxInfo().get("AppId");
	public static final String APPSECRET = WxReader.getWxInfo().get("AppSecret");
	public static ApiDAO apiDAO = new ApiDAO();

	public static CloseableHttpClient httpsclient(HttpServletRequest request, String mch_id, String certificate) {
		try {
			// 附加 获取绝对路径
			Map<String, String> api = new HashMap<String, String>();
			api.put("apikey", "realpath");
			api = apiDAO.get(api.get("apikey"));
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(api.get("apivalue") + certificate));
			try {
				keyStore.load(instream, mch_id.toCharArray());
			} catch (IOException ex) {
				Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NoSuchAlgorithmException ex) {
				Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
			} catch (CertificateException ex) {
				Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					instream.close();
				} catch (IOException ex) {
					Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpsclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyStoreException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnrecoverableKeyException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (KeyManagementException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return httpsclient;
	}

	public static CloseableHttpClient httpclient() {
		httpclient = HttpClients.createDefault();
		return httpclient;
	}

	public static void close(CloseableHttpClient client) {
		try {
			client.close();
		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		try {
			// 获取accessToken -参数appid，secret
			String accessToken = getAccessToken();
			// System.out.println(accessToken);
			// 创建菜单
			StringBuffer params = new StringBuffer();
			params.append("");
			params.append("{");
			params.append("     \"button\":[");
			params.append("      {");
			params.append("           \"name\":\"1222\",");
			params.append("           \"sub_button\":[");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"东鹏介绍\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/index.jsp?#mp.weixin.qq.com\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"产品案例\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/type/list.jsp?type=jj\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"建材产品\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/type/list.jsp?type=jc\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"东鹏荣誉\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/search.jsp\"");
			params.append("            }]");
			params.append("       },");
			params.append("       {");
			params.append("           \"name\":\"优惠促销\",");
			params.append("           \"sub_button\":[");
			params.append("            {");
			params.append("               \"type\":\"click\",");
			params.append("               \"name\":\"签到有礼\",");
			params.append("               \"key\":\"wddm\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"click\",");
			params.append("               \"name\":\"促销信息\",");
			params.append("               \"key\":\"cxxx\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"click\",");
			params.append("               \"name\":\"新品推荐\",");
			params.append("               \"key\":\"zxbm\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"click\",");
			params.append("               \"name\":\"转盘抽奖\",");
			params.append("               \"key\":\"zpcj\"");
			params.append("            }]");
			params.append("       },");
			params.append("       {");
			params.append("           \"name\":\"位置/售后\",");
			params.append("           \"sub_button\":[");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"位置导航\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/map.jsp\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"售后服务\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/service.jsp?#mp.weixin.qq.com\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"招商在线\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/service.jsp?#mp.weixin.qq.com\"");
			params.append("            },");
			params.append("            {");
			params.append("               \"type\":\"view\",");
			params.append("               \"name\":\"人才招聘\",");
			params.append("               \"url\":\"http://dmjj.gcxm360.com/zs/zsonline.jsp\"");
			params.append("            }]");
			params.append("       }]");
			params.append("}");
			String s = params.toString();
			String res = createMenu(s, accessToken);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建菜单
	 */
	public static String createMenu(String params, String accessToken) throws Exception {
		HttpPost httpost = HttpClientConnectionManager
				.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken);
		httpost.setEntity(new StringEntity(params, "UTF-8"));
		HttpResponse response = httpclient.execute(httpost);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println(jsonStr);
		JSONObject object = JSON.parseObject(jsonStr);
		return null != object ? object.getString("errmsg") : "error";
	}

	/**
	 * 获取accessToken
	 */
	public static String getAccessToken(String appid, String secret) throws Exception {
		HttpGet get = HttpClientConnectionManager
				.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid
						+ "&secret=" + secret);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject object = JSON.parseObject(jsonStr);
		return object.getString("access_token");
	}

	/**
	 * 查询菜单
	 */
	public static String getMenuInfo(String accessToken) throws Exception {
		HttpGet get = HttpClientConnectionManager
				.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		return jsonStr;
	}

	/**
	 * 删除自定义菜单
	 */
	public static String getAccessToken(String accessToken) throws Exception {
		HttpGet get = HttpClientConnectionManager
				.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject object = JSON.parseObject(jsonStr);
		return object.getString("errmsg");
	}

	/**
	 * 删除上传素材
	 */
	public static boolean delPerMaterial(String mediaid) throws Exception {
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(
				"https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + getAccessToken());
		String params = "{" + "\"media_id\":\"" + mediaid + "\"" + "}";
		httpost.setEntity(new StringEntity(params, "UTF-8"));
		HttpResponse response = httpclient.execute(httpost);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println(jsonStr);
		JSONObject object = JSON.parseObject(jsonStr);
		return "0".equals(object.getString("errcode"));
	}

	/**
	 * 获取accessToken
	 */
	public static String getAccessToken() {
		Map<String, String> map = apiDAO.get("access_token");
		String accessToken = map.get("apivalue");
		long times = 0;
		try {
			times = format.parse(map.get("times")).getTime();
		} catch (Exception e) {
			System.out.println("无原始数据！");
		}
		if (7000 * 1000 < System.currentTimeMillis() - times) {// access_token超时
			try {
				accessToken = WxMenuUtils.getAccessToken(APPID, APPSECRET);
				map.put("apikey", "access_token");
				map.put("apivalue", accessToken);
				map.put("times", format.format(new Date()));
				apiDAO.set(map);
			} catch (Exception ex) {
				System.out.println("获取accessToken失败！");
			}
		}
		return accessToken;
	}

	// 获取用户基本信息
	public static JSONObject getUserInfo(String openid) {
		JSONObject object = null;
		try {
			String accessToken = WxMenuUtils.getAccessToken();
			HttpGet get = HttpClientConnectionManager
					.getGetMethod("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid="
							+ openid + "&lang=zh_CN");
			HttpResponse responses = WxMenuUtils.httpclient.execute(get);
			String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
			object = JSON.parseObject(jsonStr);
		} catch (Exception ex) {
			System.out.println("未获取到相关资料！");
		}
		return object;
	}

	// 注意：为了方便，这里使用绝对路径
	public static void copy(String fromPath, String targetPath) {
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(fromPath);
			BufferedInputStream in = new BufferedInputStream(fi);
			FileOutputStream fo = new FileOutputStream(targetPath);
			BufferedOutputStream out = new BufferedOutputStream(fo);
			byte[] buf = new byte[4096];
			int len = in.read(buf);
			while (len != -1) {
				out.write(buf, 0, len);
				len = in.read(buf);
			}
			out.close();
			fo.close();
			in.close();
			fi.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fi.close();
			} catch (IOException ex) {
				Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// 生成推广二维码
	public static String getShopQRCode(HttpServletRequest request, String paramstr) {
		StringBuilder path = null;
		try {
			// String params = "{\"expire_seconds\": \"30\",\"action_name\":
			// \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": \"" +
			// paramstr + "\"}}}";
			String params = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""
					+ paramstr + "\"}}}";
			HttpPost httpost = HttpClientConnectionManager.getPostMethod(
					"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + WxMenuUtils.getAccessToken());
			httpost.setEntity(new StringEntity(params, "UTF-8"));
			CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
			JSONObject object = JSON.parseObject(jsonStr);
			String ticket = object.getString("ticket");
			HttpGet get = HttpClientConnectionManager.getGetMethod(
					"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(ticket, "UTF-8"));
			responses = WxMenuUtils.httpclient.execute(get);
			byte[] bytes = EntityUtils.toByteArray(responses.getEntity());
			// 获取图片信息
			String type = responses.getHeaders("Content-Type")[0].getValue().split("/")[0];
			String fileExt = responses.getHeaders("Content-Type")[0].getValue().split("/")[1];
			path = new StringBuilder("upload/qrcode_").append(String.valueOf(System.currentTimeMillis())).append(".")
					.append(fileExt);
			FileOutputStream fileOutputStream = new FileOutputStream(
					new File(request.getServletContext().getRealPath("/" + path.toString())));
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			return path.toString();

		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return path.toString();
	}

	public static String getWxQRCode(HttpServletRequest request, String paramstr) {
		StringBuilder path = null;
		try {
			HttpPost httpost = HttpClientConnectionManager.getPostMethod(
					"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + WxMenuUtils.getAccessToken());
			httpost.setEntity(new StringEntity(paramstr, "UTF-8"));
			CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
			JSONObject object = JSON.parseObject(jsonStr);
			String ticket = object.getString("ticket");
			HttpGet get = HttpClientConnectionManager.getGetMethod(
					"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(ticket, "UTF-8"));
			responses = WxMenuUtils.httpclient.execute(get);
			byte[] bytes = EntityUtils.toByteArray(responses.getEntity());
			// 获取图片信息
			String type = responses.getHeaders("Content-Type")[0].getValue().split("/")[0];
			String fileExt = responses.getHeaders("Content-Type")[0].getValue().split("/")[1];
			path = new StringBuilder("upload/qrcode_").append(String.valueOf(System.currentTimeMillis())).append(".")
					.append(fileExt);
			FileOutputStream fileOutputStream = new FileOutputStream(
					new File(request.getServletContext().getRealPath("/" + path.toString())));
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			return path.toString();

		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return path.toString();
	}

	// 二维码处理
	public static String doShopQRCode(HttpServletRequest request, String qrcode) {
		String basePath = request.getServletContext().getRealPath("/");
		String mubanPath = "";
		List<Map<String, String>> newstypesList = new NewstypesDAO().getList("10");
		if (0 < newstypesList.size()) {
			mubanPath = basePath + newstypesList.get(0).get("img");
		}

		// 将模板文件复制到目标文件
		java.io.File fromFile = new java.io.File(mubanPath);
		String targetPath = basePath
				+ new StringBuilder("upload/myqrcode_").append(String.valueOf(System.currentTimeMillis())).append(".")
						.append(fromFile.getName().substring(fromFile.getName().lastIndexOf(".") + 1)).toString();
		WxMenuUtils.copy(mubanPath, targetPath);
		String logoText = request.getServletContext().getRealPath("/" + qrcode);
		// targetPath = ImageUtils.markByText(wx, subscriber.get("nickname"),
		// targetPath, basePath, 211, 96);
		// targetPath = ImageUtils.markByText(wx, wx.get("qrcodewarns"),
		// targetPath, basePath, 355, 135);
		targetPath = ImageUtils.markImageByIcon(logoText, targetPath, basePath, 170, 330);
		return targetPath.replace(basePath, "");
	}

	// 上传微信多媒体（3天）
	public static Map<String, String> uploadQrcode(HttpServletRequest request, HttpServletResponse response,
			ServletConfig config, String qrcode) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			File media = new File(request.getServletContext().getRealPath("/" + qrcode));
			HttpPost httpPost = new HttpPost("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
					+ WxMenuUtils.getAccessToken() + "&type=image");
			FileBody fileBody = new FileBody(media);
			StringBody stringBody = new StringBody("文件的描述");
			MultipartEntity entity = new MultipartEntity();
			entity.addPart("media", fileBody);
			entity.addPart("desc", stringBody);
			httpPost.setEntity(entity);
			HttpResponse responses = WxMenuUtils.httpclient.execute(httpPost);
			String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
			System.out.println(jsonStr);

			JSONObject object = JSON.parseObject(jsonStr);
			String media_id = object.getString("media_id");
			String created_at = object.getString("created_at");
			System.out.println(media_id + "  " + created_at);
			map.put("media_id", media_id);
			map.put("created_at", created_at);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return map;
	}

	// 发送客服文本消息
	public static void sendCustomService(String openid, String content) {
		try {
			String params = "{" + "    \"touser\":\"" + openid + "\"," + "    \"msgtype\":\"text\"," + "    \"text\":"
					+ "    {" + "         \"content\":\"" + content + "\"" + "    }" + "}";
			HttpPost httpost = HttpClientConnectionManager
					.getPostMethod("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
							+ WxMenuUtils.getAccessToken());
			httpost.setEntity(new StringEntity(params, "UTF-8"));
			HttpResponse responses = WxMenuUtils.httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
			System.out.println(jsonStr);
		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// 生成带参数的二维码
	public static String getQRCode(HttpServletRequest request, String url) {
		String text = url;// 文本
		int width = 300;
		int height = 300;
		String format = "png";
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix;
		StringBuilder path = new StringBuilder("upload/").append(String.valueOf(System.currentTimeMillis())).append(".")
				.append(format);
		try {
			bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			File outputFile = new File(request.getServletContext().getRealPath("/" + path.toString()));
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (WriterException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(WxMenuUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return path.toString();
	}

	public static String saleqrcode(HttpServletRequest request, String openid) {
		String qrcode = "";
		SaleMemberDAO saleMemberDAO = new SaleMemberDAO();
		Map<String, String> salemember = new HashMap<String, String>();
		salemember.put("openid", openid);
		salemember = saleMemberDAO.getByOpenid(salemember);
		if ("1".equals(salemember.get("issm"))) {// 是推广人员
			qrcode = salemember.get("saleqrcode");
			if ("".equals(qrcode)) {
				// 不存在新生成
				qrcode = WxMenuUtils.getShopQRCode(request, openid);
				// 处理二维码
				qrcode = WxMenuUtils.doShopQRCode(request, qrcode);
				// 同时上传临时文件
				// Map<String, String> qrcodemap =
				// WxMenuUtils.uploadQrcode(request, response,
				// this.getServletConfig(), qrcode, wx);
				saleMemberDAO.updateqrcode(openid, qrcode);

			} else {
				// 已存在
				qrcode = salemember.get("saleqrcode");
			}
		} else {
			// 不是推广人员
			qrcode = "fore/saleMember/saleqrcode.jpg";
		}
		return qrcode;
	}
}