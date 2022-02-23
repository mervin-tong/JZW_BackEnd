package com.piesat.school.biz.ds.user.check;

import cn.jiguang.common.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suweipeng
 * @data 2022/2/22 14:55
 */
public class CheckPhoneOrEmail {
    public static boolean checkPhone(String phoneOrEmail){
        Pattern p = Pattern.compile("^[1](([3-9][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$");
        if(p.matcher(phoneOrEmail).matches()){
            return true;
        }
        return false;
    }
    public static boolean checkEmail(String phoneOrEmail) {
        if (StringUtils.isEmpty(phoneOrEmail)){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(phoneOrEmail);
        if (m.matches()){
            return true;
        }
        return false;
    }
}
