package com.piesat.school.user.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 姓名（实名）
     */
    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "头像地址")
    private String avatar;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 密码：数字+英文字母（大小写敏感，不少于 8 位）
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 工作单位
     */
    @ApiModelProperty(value = "用户工作单位")
    private String workUnit;

    /**
     * 单位地址
     */
    @ApiModelProperty(value = "用户单位地址")
    private String unitAddress;

    /**
     * 最高学历
     */
    @ApiModelProperty(value = "最高学历")
    private String highEducation;

    /**
     * 从事专业
     */
    @ApiModelProperty(value = "从事专业")
    private String profession;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatedAt;

    /**
     * 是否有上传数据权限（0是 1否）
     */
    @ApiModelProperty(value = "是否有上传数据权限（0是 1否）")
    private Boolean isDataUpload;

    @ApiModelProperty(value = "当前是否有上传数据权限（0是 1否）")
    private Boolean currentUpload;

    /**
     * 账户状态（0正常 1停用）
     */
    @ApiModelProperty(value = "账户状态（0正常 1停用）")
    private Integer status;
    @ApiModelProperty(value = "用户权限")
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
