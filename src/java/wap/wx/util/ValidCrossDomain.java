/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrator
 */
public class ValidCrossDomain {

    /**
     * 验证请求的合法性，防止跨域攻击
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean validateRequest(HttpServletRequest request) {
        boolean referer_sign = true;// true 站内提交，验证通过 //false 站外提交，验证失败
        String referer = "";
        Enumeration headerValues = request.getHeaders("referer");
        while (headerValues.hasMoreElements()) {
            referer = (String) headerValues.nextElement();
        }
        // 判断是否存在请求页面
        if (StringUtils.isBlank(referer)) {
            referer_sign = false;
        } else {
            // 判断请求页面和getRequestURI是否相同
            String servername_str = request.getServerName();
            if (StringUtils.isNotBlank(servername_str)) {
                int index = 0;
                if (StringUtils.indexOf(referer, "https://") == 0) {
                    index = 8;
                } else if (StringUtils.indexOf(referer, "http://") == 0) {
                    index = 7;
                }
                if (referer.length() - index < servername_str.length()) {// 长度不够
                    referer_sign = false;
                } else { // 比较字符串（主机名称）是否相同
                    String referer_str = referer.substring(index, index + servername_str.length());
                    if (!servername_str.equalsIgnoreCase(referer_str)) {
                        referer_sign = false;
                    }
                }
            } else {
                referer_sign = false;
            }
        }
        return referer_sign;
    }
}
