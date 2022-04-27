package com.piesat.school.datainf.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MyDataInfVTO implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "空间位置")
    private String address;
    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date startAt;
    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date endAt;
    @ApiModelProperty(value = "上传人id")
    private Long uploadUserId;
    @ApiModelProperty(value = "上传人姓名")
    private String uploadUserName;
    @ApiModelProperty(value = "审核状态")
    private Integer throughReview;
    @ApiModelProperty(value = "数据名称")
    private String dataName;
    @ApiModelProperty(value = "数据量")
    private String dataAmount;
    @ApiModelProperty(value = "用户封禁状态")
    private Integer publisherStatus;
    @ApiModelProperty(value = "联系人")
    private String contactName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "评审状态")
    private Integer status;
    @ApiModelProperty(value = "图片")
    private String pic;
    @ApiModelProperty(value = "摘要")
    private String introduction;
}
