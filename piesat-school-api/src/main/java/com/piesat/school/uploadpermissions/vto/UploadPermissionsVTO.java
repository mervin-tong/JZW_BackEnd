package com.piesat.school.uploadpermissions.vto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/28 16:17
 */
@Data
public class UploadPermissionsVTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //申请人
    private String name;
    //邮箱
    private String email;
    //工作地址
    private String unitAddress;
    //从事专业
    private String profession;
    //申请时间
    private Date createdAt;




}
