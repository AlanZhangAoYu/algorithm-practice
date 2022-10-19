package com.example.cloud.common.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
    /**
     * 获取ip
     *
     * @return
     */
    public static String getIP() {
        HttpServletRequest request = SpringContextUtil.getRequest();
        String ip = request.getHeader("X-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取请求的接口地址 注：当前请求必须为前段发送，否则会出现异常
     * @return
     */
    public static String getPath() {
        HttpServletRequest request = SpringContextUtil.getRequest();
        return request.getRequestURI();
    }
}
