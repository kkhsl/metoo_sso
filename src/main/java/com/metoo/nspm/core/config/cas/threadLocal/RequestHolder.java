package com.metoo.nspm.core.config.cas.threadLocal;

import javax.servlet.http.HttpServletRequest;

public class RequestHolder {

    private static final ThreadLocal<String> userHolder = new ThreadLocal<String>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(String username) {
        userHolder.set(username);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static String getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
