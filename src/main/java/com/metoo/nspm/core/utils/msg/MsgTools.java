package com.metoo.nspm.core.utils.msg;

import com.metoo.nspm.core.utils.DateTools;
import com.metoo.nspm.core.utils.crypto.MD5Utils;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class MsgTools {

    public static boolean sendSMS(String mobile, String content) throws UnsupportedEncodingException {
        boolean result = true;
        if (true/*this.configService.getSysConfig().isSmsEnbale()*/) {
//            String url = this.configService.getSysConfig().getSmsURL();
//            String userName = this.configService.getSysConfig().getSmsUserName();
//            String password = this.configService.getSysConfig().getSmsPassword();
//            String appkey = this.configService.getSysConfig().getSmsAppkey();
//            String secretkey = this.configService.getSysConfig().getSmsSecretkey();
            String url = "http://sms.cmasdx.com:8881/sywxApi/sms/send/json";

            String account = "63804";

            String password = "?#88kV!!w#43S";

            String timestamp = DateTools.getCurrentDate(new Date(), "yyyyMMddHHmmss");

            String sign = MD5Utils.encrypt(account + password + timestamp);


            // Globals.DEFAULT_SMS_URL
            SmsBase sb = new SmsBase(url, account, sign, timestamp);// 固定硬编码短信发送接口
            String ret = null;
            try {
//                ret = sb.postJson(mobile, content);
                ret = sb.SendSms(mobile, content);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (ret.equals("0")) {
                result = true;
            } else {
                result = false;
            }
            /*
             * if (!ret.substring(0, 3).equals("000")) { result = false; }
             */
        } else {
            result = false;
            System.out.println("系统关闭了短信发送功能");
        }
        return result;
    }
}
