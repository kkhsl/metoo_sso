package com.metoo.nspm.core.manager;

import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.IMobileWhiteListService;
import com.metoo.nspm.core.service.ISysConfigService;
import com.metoo.nspm.core.service.IUserService;
import com.metoo.nspm.core.service.IVerifyCodeService;
import com.metoo.nspm.core.utils.CommUtil;
import com.metoo.nspm.core.utils.crypto.MD5Utils;
import com.metoo.nspm.core.utils.msg.MsgTools;
import com.metoo.nspm.entity.MobileWhiteList;
import com.metoo.nspm.entity.SysConfig;
import com.metoo.nspm.entity.User;
import com.metoo.nspm.entity.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@RequestMapping("/sms")
@RestController
public class MsgManagerController {

    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    private IMobileWhiteListService mobileWhiteListService;

    @RequestMapping("send")
    public Object send(HttpServletRequest request, String mobile) throws UnsupportedEncodingException {
        System.out.println(request.getRequestURI());
        if(mobile != null && !mobile.equals("")){

            boolean isValid = validate(mobile);
            if(!isValid){
                return ResponseUtil.badArgument("手机号码格式错误");
            }

            boolean phone_flag = true;
            SysConfig sysConfig = configService.select();
            if(sysConfig.isPhoneDisable()){
                MobileWhiteList mobileWhiteList = this.mobileWhiteListService.selectObjByMobile(mobile);
                if(mobileWhiteList == null){
                    phone_flag = false;
                }
            }
            if(!phone_flag){
                return ResponseUtil.badArgument("此号码不允许登录");
            }
            User user = this.userService.selectObjByMobile(mobile);
            if(user != null){

                VerifyCode verifyCode = this.verifyCodeService.selectObjByMobile(mobile);

                boolean canSendSMS = canSendSMS(verifyCode);

                if(!canSendSMS){
                    return ResponseUtil.badArgument("短信已发送");
 }

//            VerifyCode verifyCode = this.verifyCodeService.selectObjByMobile(mobile);
//                if(verifyCode == null){
//                    String code = CommUtil.randomInt(6);
//                    String msg = "【湖南工程技师】您的验证码是" + code;
//                    boolean flag = MsgTools.sendSMS(mobile, msg);
//                    if(flag){
//                        int i = this.verifyCodeService.update(mobile, MD5Utils.encrypt(code));
//                        return ResponseUtil.ok();
//                    }
//                    return ResponseUtil.error();
//                }else{
//                    return ResponseUtil.ok("短信已发送");
//                }

                String code = CommUtil.randomInt(6);
                String msg = "【湖南工程技师】您的验证码是" + code;
                boolean flag = MsgTools.sendSMS(mobile, msg);
                if(flag){
                    int i = this.verifyCodeService.update(mobile, MD5Utils.encrypt(code));
                    return ResponseUtil.ok();
                }else{
                    return ResponseUtil.badArgument("短信发送失败");
                }
            }else{
                return ResponseUtil.badArgument("用户不存在");
            }
        }
        return ResponseUtil.badArgumentValue();
    }


    public boolean canSendSMS(VerifyCode verifyCode){
        if(verifyCode == null){
            return true;
        }

        long currentTimestamp = System.currentTimeMillis();
        long time = verifyCode.getAddTime().getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(currentTimestamp - time);
        if(minutes >= 1){
            return true;
        }else{
            return false;
        }

    }

    private LocalDateTime lastSentTime;

//    public boolean canSendSMS(){
//        LocalDateTime currentTime = LocalDateTime.now();
//        if(lastSentTime == null){
//            // 第一次发送短信
//            lastSentTime = currentTime;
//            return true;
//        }else{
//            // 计算时隔
//            long minutesPassd = lastSentTime.until(currentTime, ChronoUnit.MINUTES);
//            if(minutesPassd >= 1){
//                // 可以发送短信
//                lastSentTime = currentTime;
//                return true;
//            }else{
//                // 时间间隔不够，无法发送短信
//                return false;
//            }
//        }
//
//    }

    public static boolean validate(String phoneNumber) {
//        String pattern = "^[1]\\d{10}$";
        String pattern = "^1[3-9]\\d{9}$";

        return Pattern.matches(pattern, phoneNumber);
    }

    public static void main(String[] args) {
        String[] phoneNumbers = {"13312345678", "12345678901", "123456789", "19529666076"};

        for (String phoneNumber: phoneNumbers) {
            boolean isValid = validate(phoneNumber);
            System.out.println(phoneNumber + " is valid: " + isValid);
        }

        try {
            String url = URLDecoder.decode("http%3A%2F%2Fjpzxxg.baju.com.cn%2Fcas", "UTF-8");
            System.out.println(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
