package com.piesat.school.user.vto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suweipeng
 * @data 2022/3/11 10:22
 */
@Data
public class UserListVTO implements Serializable {
    /**
     * 用户编号
     */
    private Long id;

    /**
     * 姓名（实名）
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

}
