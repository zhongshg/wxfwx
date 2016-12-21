/*
 * CookieUtil.java
 *
 * Created on 2006��7��25��, ����3:58
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package job.tot.util;

import javax.servlet.http.*;

/**
 *
 * @author wy1
 */
public class CookieUtil {

    private static String cookieEncode(String encodestr) {
        return StringUtils.base64Encode(encodestr);
    }

    private static String cookiedecode(String encodestr) {
        return StringUtils.base64Decode(encodestr);
    }

    public static String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) {
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    return cookiedecode(cookie.getValue());
                }
            }
        } else {
            return null;
        }
        return defaultValue;
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String cookievalue) {
        Cookie cookie = new Cookie(cookieName, cookievalue);
        cookie.setDomain("wanxiangfangwx.cityapp360.com");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String cookievalue, int maxtime) {
        Cookie cookie = new Cookie(cookieName, cookieEncode(cookievalue));
        cookie.setDomain("wanxiangfangwx.cityapp360.com");
        cookie.setMaxAge(maxtime);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}