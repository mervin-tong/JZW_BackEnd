package com.piesat.school.biz.ds.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.information.builder.InformationBuilder;
import com.piesat.school.biz.ds.user.bulider.UserBuilder;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.user.param.UpdatePasswordParamData;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.param.UserQueryParamData;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Resource
    private UserMapper userMapper;
    @Override
    public UserVTO findUserByPhoneOrEmail(String phoneOrEmail) {
//        User user = new User();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //判断是不是手机号
//        if (CheckPhoneOrEmail.checkPhone(phoneOrEmail)){
//            queryWrapper.eq("phone",phoneOrEmail);
//            user = userMapper.selectList(queryWrapper).get(0);
//            return UserBulider.toUserVTO(user);
//        }
        //如果不是手机号用邮箱查询
        queryWrapper.eq("email",phoneOrEmail);
        User user = userMapper.selectList(queryWrapper).get(0);
        return UserBuilder.toUserVTO(user);
    }

    @Override
    public UserVTO addUser(UserParamData userParamData)  {
        User user = UserBuilder.toUser(userParamData);
        user.setPassword(passwordEncoder.encode(userParamData.getPassword()));
        this.save(user);
        return UserBuilder.toUserVTO(user);
    }

    @Override
    public Boolean alterPassword(String email, String password) {
        if (email == null && password == null){
            return Boolean.FALSE;
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password",passwordEncoder.encode(password));
        updateWrapper.eq("email",email);
        return this.update(updateWrapper);
    }

    //获取用户列表
    @Override
    public TailPage<UserVTO> getUserList(UserQueryParamData paramData) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(paramData.getCondition())){
            queryWrapper.lambda().like(User::getName,paramData.getCondition());
        }
        Page<User> userPage=this.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper);
        return CommonPage.buildPage(userPage.getCurrent(), userPage.getSize(), userPage.getTotal(), UserBuilder.toUserVTOs(userPage.getRecords()));
    }
    //根据用户id修改密码
    @Override
    public Result<Boolean> updatePassword(UpdatePasswordParamData paramData) {
        User user=userMapper.selectById(paramData.getId());
        if(!passwordEncoder.matches(paramData.getFormerPassword(), user.getPassword())){
            return Result.ofFail("4401","原密码错误");
        }
        user.setPassword(passwordEncoder.encode(paramData.getFormerPassword()));
        int i=userMapper.updateById(user);
        if(i==1) {
            return Result.ofSuccess(Boolean.TRUE);
        }else {
            return Result.ofFail("4402", "密码修改失败");
        }
    }
}
