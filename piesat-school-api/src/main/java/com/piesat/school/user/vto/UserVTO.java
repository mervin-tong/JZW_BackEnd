package com.piesat.school.user.vto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/1/17 13:55
 */
@Data
public class UserVTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;

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

    /**
     * 密码：数字+英文字母（大小写敏感，不少于 8 位）
     */
    private String password;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 单位地址
     */
    private String unitAddress;

    /**
     * 最高学历
     */
    private String highEducation;

    /**
     * 从事专业
     */
    private String profession;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 是否有上传数据权限（0是 1否）
     */
    private String isDataUpload;

    /**
     * 账户状态（0正常 1停用）
     */
    private String status;

    private Set<RoleVTO> roles = new HashSet<RoleVTO>(0);


    @Override
    public String toString() {
        return "UserVTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", workUnit='" + workUnit + '\'' +
                ", unitAddress='" + unitAddress + '\'' +
                ", highEducation='" + highEducation + '\'' +
                ", profession='" + profession + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isDataUpload='" + isDataUpload + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
