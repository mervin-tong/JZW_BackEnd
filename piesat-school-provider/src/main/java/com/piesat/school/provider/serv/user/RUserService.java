package com.piesat.school.provider.serv.user;

import com.piesat.school.biz.ds.user.facade.UserFacadeService;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;
import java.util.Random;

/**
 * @author suweipeng
 * @data 2022/1/17 9:18
 * 用户服务
 */
@DubboService(interfaceClass = IRUserService.class)
public class RUserService implements IRUserService {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserFacadeService userFacadeService;

    @Override
    public UserVTO findUserByphoneOrEmail(String phoneOrEmail) {

        return userFacadeService.findUserByphoneOrEmail(phoneOrEmail);
    }

    @Override
    public Result<UserVTO> addUser(UserParamData userParamData) {
        if (verificationEmail(userParamData.getUserVerificationCode())){
            return userFacadeService.addUser(userParamData);
        }
        return Result.ofFail("4012","验证码错误");
    }

    @Autowired
    JavaMailSender jms;
    //读取配置文件邮箱账号参数
    @Value("${spring.mail.username}")
    private String sender;
    //用于验证
    Integer userVerificationCode = null;
    //发送验证码
    @Override
    public Result sendEmail(String email) {

        //随机数用作验证
        Integer userVerificationCode = new Random().nextInt(9999);

        try {
            //建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();

            //发送者
            mainMessage.setFrom(sender);

            //接收者
            mainMessage.setTo(email);

            //发送的标题
            mainMessage.setSubject("邮箱验证");

            //发送的内容
            String msg = "您好！" + email + ",您正在使用邮箱验证，验证码：" + userVerificationCode + "。";
            mainMessage.setText(msg);

            //发送邮件
            jms.send(mainMessage);

            //下面是加入缓存，以便于进行邮箱验证
            this.userVerificationCode = userVerificationCode;

        } catch (Exception e) {
            return Result.ofFail("4401","发送邮件失败，请核对邮箱账号");
        }
        return Result.ofSuccessMessage("邮件发送成功");
    }

    @Override
    public boolean verificationEmail(Integer code) {
        if (code!= null){
            if (code.intValue() == this.userVerificationCode.intValue()){
                return true;
            }
            return false;
        }

        return false;
    }
}
