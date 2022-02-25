package com.piesat.school.orderfrom.param;

import com.smartwork.api.param.PageQueryParamData;
import lombok.Data;

/**
 * @author suweipeng
 * @data 2022/2/25 15:30
 */
@Data
public class OrderFromMenuPageParamData extends PageQueryParamData {
    private static final long serialVersionUID = 1L;

    private Long dataType;

    private Long downloadUserId;



}
