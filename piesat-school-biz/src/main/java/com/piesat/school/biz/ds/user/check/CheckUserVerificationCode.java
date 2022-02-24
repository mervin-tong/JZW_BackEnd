package com.piesat.school.biz.ds.user.check;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.piesat.school.biz.ds.user.entity.Email;
import com.piesat.school.biz.ds.user.mapper.EmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/2/23 17:01
 */
@Component
public class CheckUserVerificationCode {

    @Autowired
    private EmailMapper emailMapper;
    //验证验证码是否正确
    public  boolean verificationEmail(String email,Integer code) {
        if (code != null && email != null){
            //通过邮箱号查询验证码
            QueryWrapper<Email> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",email);
            List<Email> emails = emailMapper.selectList(queryWrapper);
            if (emails.isEmpty()){
                return false;
            }
            if (code.intValue() == emails.get(0).getUserVerificationCode().intValue()){
                return true;
            }
            return false;
        }

        return false;
    }
}
