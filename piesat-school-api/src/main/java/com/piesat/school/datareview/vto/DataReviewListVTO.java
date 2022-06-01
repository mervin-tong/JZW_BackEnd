package com.piesat.school.datareview.vto;

import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "数据信息列表模型")
public class DataReviewListVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private TailPage<DataReviewUserVTO> dataReviewUserVTOS;

    private List<Long> ids;
}
