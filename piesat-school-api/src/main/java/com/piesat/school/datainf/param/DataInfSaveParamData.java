package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DataInfSaveParamData implements Serializable {
    @ApiModelProperty(value = "数据id")
    private Long id;

    @ApiModelProperty(value = "数据名")
    private String dataName;

    @ApiModelProperty(value = "制作者")
    private String maker;

    @ApiModelProperty(value = "数据所属单位")
    private String dataUnit;

    @ApiModelProperty(value = "公开状态")
    private Integer status;

    @ApiModelProperty(value = "数据地址")
    private String address;

    @ApiModelProperty(value = "第一类别")
    private String firstClass;

    @ApiModelProperty(value = "第二类别")
    private String secClass;

    @ApiModelProperty(value = "尺度")
    private String meas;

    @ApiModelProperty(value = "分辨率")
    private String ratio;

    @ApiModelProperty(value = "数据摘要")
    private String introduction;

    @ApiModelProperty(value = "数据来源")
    private String origin;

    @ApiModelProperty(value = "数据制作方法")
    private String solution;

    @ApiModelProperty(value = "唯一标识符")
    private String doi;

    @ApiModelProperty(value = "关键字")
    private String keyword ;

    @ApiModelProperty(value = "联系人")
    private ContactParamData contact;

    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;

    @ApiModelProperty(value = "上传人id")
    private Long uploadUserId;
    @ApiModelProperty(value = "资源地址")
    private String content;
    @ApiModelProperty(value = "资源大小")
    private String dataAmount;
    @ApiModelProperty(value = "缩略图地址")
    private String pic;
    @ApiModelProperty(value = "左上坐标")
    private String leftUp;
    @ApiModelProperty(value = "右下坐标")
    private String rightDown;

}
