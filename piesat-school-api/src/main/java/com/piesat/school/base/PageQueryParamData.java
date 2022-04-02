package com.piesat.school.base;

import com.smartwork.api.param.ParamData;
import lombok.Data;

@Data
public class PageQueryParamData extends ParamData {
    private int pn = 1;
    private int ps = 20;
}
