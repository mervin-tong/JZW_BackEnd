package com.piesat.school.orderfrom.param;

import com.smartwork.api.param.UIDParamData;
import lombok.Data;

/**
 * @author suweipeng
 * @data 2022/2/24 15:48
 */
@Data
public class OrderFromParamData extends UIDParamData {
    private static final long serialVersionUID = 1L;


    /**
     * 下载用户id
     */
    private Long downloadUserId;

    /**
     * 审核人员id
     */
    private Long auditorUserId;

    /**
     * 文章id
     */
    private Long dataInfoId;
    /**
     * 公开状态（1.完全公开 2.申请获取 3.保护期内）
     */
    private Integer status;






}
