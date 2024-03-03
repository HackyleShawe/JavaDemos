package com.ks.demo.susi.service;

import com.ks.demo.susi.dto.SignUpSignInDto;
import com.ks.demo.susi.entity.SmsRecordEntity;
import com.ks.demo.susi.entity.UserEntity;
import com.ks.demo.susi.service.sms.SendSmsDataDto;
import com.ks.demo.susi.service.sms.TencentSendSmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SignUpSignInService {
    @Autowired
    private KaptchaService kaptchaService;
    @Autowired
    private TencentSendSmsService tencentSendSmsService;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsRecordService smsRecordService;
    @Autowired
    private ValueOperations<String, String> redisValueOperations;

    public String sendSmsCode(SignUpSignInDto signUpSignInDto) {
        String mobilePhone = signUpSignInDto.getMobilePhone();
        String verifyCode = signUpSignInDto.getVerifyCode();
        String uuid = signUpSignInDto.getUuid();

        String res = "";
        boolean checked = kaptchaService.checkCode(uuid, verifyCode);
        if(checked) { //图形验证码检查通过，可以发送短信
            SendSmsDataDto sendSmsDataDto = new SendSmsDataDto();
            sendSmsDataDto.setPhone(mobilePhone);
            String smsCode = tencentSendSmsService.send(sendSmsDataDto);
            if(StringUtils.isNotEmpty(smsCode)) {
                //缓存：以mobilePhone作为Key，后续在校验smsCode的时候，根据手机号获取
                redisValueOperations.set(mobilePhone, smsCode, 60L, TimeUnit.SECONDS);

                SmsRecordEntity smsRecordEntity = new SmsRecordEntity();
                smsRecordEntity.setMobilePhone(mobilePhone);
                smsRecordEntity.setSmsCode(smsCode);
                smsRecordEntity.setStatus("已发送");
                //记录短信发送
                smsRecordService.record(smsRecordEntity);

                res = "图形验证码检查成功，发送短信成功（smsCode=" +smsCode+ "）";
            } else {
                res = "图形验证码检查成功，发送短信失败，请重试";
            }

        } else { //图形验证码检查失败，不能发送短信
            res = "图形验证码检查失败，请重试";
        }

        return res;
    }

    public String signUpSignInByMobileNumber(SignUpSignInDto signUpSignInDto) {
        String mobilePhone = signUpSignInDto.getMobilePhone();
        String smsCode = signUpSignInDto.getSmsCode();
        String res = "";

        String smsCodeRedis = redisValueOperations.get(mobilePhone);
        //注意：这里不要立即删除该电话号码对应的短信验证码，因为发送一次短信是非常珍贵的资源，
        //    校验短信验证码不通过可能是用户输入时看错了，给用户再次输入的机会
        //redisValueOperations.getOperations().delete(mobilePhone);

        SmsRecordEntity smsRecordEntity = new SmsRecordEntity();
        smsRecordEntity.setMobilePhone(mobilePhone);
        smsRecordEntity.setStatus("已使用，已过期");
        smsRecordService.updateStatus(smsRecordEntity);
        if(smsCode.equalsIgnoreCase(smsCodeRedis)) {
            System.out.println("手机号mobilePhone="+mobilePhone+"，校验短信验证码通过");
        } else {
            res = "手机号mobilePhone="+mobilePhone+"，校验短信验证码不通过";
            return res;
        }

        //检查是否已经注册
        boolean signUp = userService.checkSignUp(mobilePhone);
        if(signUp) { //已经注册，校验短信验证码后直接登录直接登录
            //其他登录逻辑

            res = "短信验证码校验通过，已经注册，登录成功";

        } else { //进行注册，再登录
            UserEntity userEntity = new UserEntity();
            userEntity.setMobilePhone(mobilePhone);
            boolean signUpSuccess = userService.signUp(userEntity);
            if(signUpSuccess) { //注册成功
                //其他登录逻辑

                res = "短信验证码校验通过，注册成功，并登录成功";

            } else {
                res = "短信验证码校验通过，注册失败";
            }
        }

        return res;
    }
}
