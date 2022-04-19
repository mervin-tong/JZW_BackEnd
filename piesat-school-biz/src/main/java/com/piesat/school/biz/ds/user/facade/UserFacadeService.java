package com.piesat.school.biz.ds.user.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.user.check.CheckPhoneOrEmail;
import com.piesat.school.biz.ds.user.check.CheckUserVerificationCode;
import com.piesat.school.biz.ds.user.entity.Email;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.entity.UserRole;
import com.piesat.school.biz.ds.user.mapper.EmailMapper;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.biz.ds.user.service.IRoleService;
import com.piesat.school.biz.ds.user.service.IUserRoleService;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.user.param.*;
import com.piesat.school.user.vto.RoleVTO;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author suweipeng
 * @data 2022/2/11 17:41
 */
@Slf4j
@Service
public class UserFacadeService {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserRoleService iUserRoleService;
    @Resource
    private CheckUserVerificationCode checkUserVerificationCode;
    @Resource
    private IDatainfService datainfService;
    @Resource
    JavaMailSender jms;
    @Resource
    private EmailMapper emailMapper;
    public UserVTO findUserByPhoneOrEmail(String phoneOrEmail){
        UserVTO userVTO = userService.findUserByPhoneOrEmail(phoneOrEmail);
        Set<RoleVTO> roleVTOs = roleService.getRolesByUserId(userVTO.getId());
        userVTO.setRoles(roleVTOs);
        return userVTO;
    }
    //普通用户注册
    public Result<UserVTO> addUser(UserParamData userParamData){
        //判断验证码是否正确
        if (!checkUserVerificationCode.verificationEmail(userParamData.getEmail()
                ,userParamData.getUserVerificationCode())){
            return Result.ofFail("4011","验证码错误");
        }
        UserVTO userVTO = new UserVTO();
        //如果注册的手机号或邮箱重复则捕获异常
        try {
            userVTO = userService.addUser(userParamData);

        }catch (DataAccessException e){
                return Result.ofFail("4012","手机号/邮箱已存在");

        }
        //添加权限
        UserRole userRole = new UserRole();
        userRole.setUserId(userVTO.getId());
        userRole.setRoleId(BizEnumType.RoleStatus.USER.getKey());
        log.info(userRole.toString());
        iUserRoleService.addUserRole(userRole);
        return Result.ofSuccess(userVTO);
    }


    //读取配置文件邮箱账号参数
    @Value("${spring.mail.username}")
    private String sender;
    public Result<Boolean> sendEmail(String email) {
        //随机数用作验证
        Integer userVerificationCode = new Random().nextInt(9999-1000+1)+1000;

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

            //把验证码存入数据库
            Email toEmail = new Email();
            toEmail.setUserVerificationCode(userVerificationCode);
            toEmail.setEmail(email);
            //如果数据库没有直接存
            try {
                emailMapper.insert(toEmail);
            }catch (DataAccessException e){
                //如果已经有该邮箱直接修改
                UpdateWrapper<Email> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("user_verification_code",userVerificationCode);
                updateWrapper.eq("email",email);
                emailMapper.update(null,updateWrapper);
            }
        } catch (Exception e) {
            return Result.ofFail("4401","发送邮件失败，请核对邮箱账号");
        }
        return Result.ofSuccess(Boolean.TRUE);

    }
    //忘记密码
    public Result<Boolean> forgetPassword(ForgetPasswordParamData forgetPasswordParamData) {
        boolean isEqual = false;
        if (checkUserVerificationCode.verificationEmail(forgetPasswordParamData.getEmail(),
                forgetPasswordParamData.getUserVerificationCode())){
            //判断两次输入的密码是否一样
            if (!forgetPasswordParamData.getNewPassword().isEmpty() && !forgetPasswordParamData.getReNewPassword().isEmpty()){
                isEqual = forgetPasswordParamData.getNewPassword()
                        .equals(forgetPasswordParamData.getReNewPassword());
            }

            if(isEqual){
                Boolean isBoolean = userService.alterPassword(forgetPasswordParamData.getEmail()
                        , forgetPasswordParamData.getNewPassword());
                if (isBoolean){
                    return Result.ofSuccess(Boolean.TRUE);
                }

            }
        }
        return Result.ofFail("4403","密码修改失败");
    }

    public Result<UserVTO> updateUser(UpdateUserParamData paramData) {
        if(paramData.getId()==null){
            return Result.ofFail("1111","id为空");
        }
        User user=this.userMapper.selectById(paramData.getId());
        if(user==null){

        }
        if(StringUtils.isNotBlank(paramData.getAvatar())){
            user.setAvatar(paramData.getAvatar());
        }
        if(StringUtils.isNotBlank(paramData.getHighEducation())){
            user.setHighEducation(paramData.getHighEducation());
        }
        if(StringUtils.isNotBlank(paramData.getName())){
            user.setName(paramData.getName());
        }
        if(StringUtils.isNotBlank(paramData.getPhone())){
            user.setPhone(paramData.getPhone());
        }
        if(StringUtils.isNotBlank(paramData.getProfession())){
            user.setProfession(paramData.getProfession());
        }
        if(StringUtils.isNotBlank(paramData.getUnitAddress())){
            user.setUnitAddress(paramData.getUnitAddress());
        }
        if (StringUtils.isNotBlank(paramData.getWorkUnit())){
            user.setWorkUnit(paramData.getWorkUnit());
        }
        user.setUpdatedAt(new Date());
        this.userMapper.updateById(user);
        UserVTO userVTO=new UserVTO();
        BeanUtils.copyProperties(user,userVTO);
        return Result.ofSuccess(userVTO);
    }

    public Result<Boolean> limitUser(LimitUserParamData paramData) {
        //用户状态更新
        User user=this.userService.getById(paramData.getUserId());
        user.setStatus(paramData.getLimitStatus());
        user.setCurrentUpload(false);
        this.userService.updateById(user);
        //若是封禁则在其下所有数据上增加封禁标识
        QueryWrapper<Datainf> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Datainf::getUploadUserId,paramData.getUserId());
        List<Datainf> datainfs=this.datainfService.list(queryWrapper);
        for(Datainf i:datainfs){
            i.setPublisherStatus(paramData.getLimitStatus());
        }
        this.datainfService.updateBatchById(datainfs);
        return Result.ofSuccess(Boolean.TRUE);
    }

    public Result<Boolean> feedback(FeedBackParamData paramData) {
        User user=this.userService.getById(paramData.getUserId());
        try {
            //建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();

            //发送者
            mainMessage.setFrom(sender);

            //接收者
            mainMessage.setTo(user.getEmail());

            //发送的标题
            mainMessage.setSubject("邮箱验证");

            //发送的内容
            String msg = paramData.getContent();
            mainMessage.setText(msg);
            //发送邮件
            jms.send(mainMessage);

            //TODO 反馈入库
        } catch (Exception e) {
            return Result.ofFail("4401","发送邮件失败，请核对邮箱账号");
        }
        return Result.ofSuccess(Boolean.TRUE);
    }
}
