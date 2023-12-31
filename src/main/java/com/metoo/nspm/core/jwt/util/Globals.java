package com.metoo.nspm.core.jwt.util;

public class Globals {

    private static Globals globals = new Globals();

    public final static String AUTHORIZATION = "AUTHORIZATION";

    public final static String BEARER = "BEARER";

    public final static long EXPIRE = 2 * 60 * 1000;

    public final static String AES_KEY = "#+NPzwDvPmCJvpY@";

//    public final static String AUTH_URL = "https://java1234.com:18443/cas/login?service=http://java1234.com:8089/auth?redirectUrl=http://java1234.com";
public final static String AUTH_URL = "https://java1234.com:18443/cas/login?service=http://java1234.com/cas/auth?redirectUrl=http://java1234.com";

    public Globals() {
    }

    public static Globals getInstance() {
        return globals;
    }
}
