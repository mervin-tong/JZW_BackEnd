package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class DataInfSaveParamData extends UIDParamData {
    /**
     * 数据名
     */
    @ApiModelProperty(value = "数据名")
    private String dataName;

    /**
     * 制作者
     */
    @ApiModelProperty(value = "制作者")
    private String maker;

    /**
     * 数据所属单位
     */
    @ApiModelProperty(value = "数据所属单位")
    private String dataUnit;

    @ApiModelProperty(value = "公开状态")
    private Integer status;

    /**
     * 数据地址
     */
    @ApiModelProperty(value = "数据地址")
    private String address;

    @ApiModelProperty(value = "第一类别")
    private String firstClass;

    @ApiModelProperty(value = "第二类别")
    private String secClass;

    /**
     * 尺度
     */
    @ApiModelProperty(value = "尺度")
    private String meas;

    /**
     * 分辨率
     */
    @ApiModelProperty(value = "分辨率")
    private String ratio;

    /**
     * 数据摘要
     */
    @ApiModelProperty(value = "数据摘要")
    private String introduction;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private String origin;

    /**
     * 数据制作方法
     */
    @ApiModelProperty(value = "数据制作方法")
    private String solution;

    /**
     * 唯一标识符
     */
    @ApiModelProperty(value = "唯一标识符")
    private String doi;

    @ApiModelProperty(value = "关键字")
    private String topic ;

//    @ApiModelProperty(value = "关键字")
//    private KeyParamData keywords ;

    @ApiModelProperty(value = "联系人")
    private ContactParamData contact;

    /**
     * 数据所属开始时间
     */
    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    /**
     * 数据所属结束时间
     */
    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;

    /**
     * 上传人id
     */
    @ApiModelProperty(value = "上传人id")
    private Long uploadUserId;

}
