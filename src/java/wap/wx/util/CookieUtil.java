/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
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
