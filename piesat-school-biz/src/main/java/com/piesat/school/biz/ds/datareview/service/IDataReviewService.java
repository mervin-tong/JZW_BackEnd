package com.piesat.school.biz.ds.datareview.service;

import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.support.page.TailPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suweipeng
 * @since 2022-03-08
 */
public interface IDataReviewService extends IService<DataReview> {

    TailPage<DataReviewVTO> dataReview(DataReviewParamData dataReviewParamData);
}
