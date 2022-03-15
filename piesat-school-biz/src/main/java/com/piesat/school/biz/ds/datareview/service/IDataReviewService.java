package com.piesat.school.biz.ds.datareview.service;

import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
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

    Boolean createReview(DataReview dataReview);

    Boolean firstReview(Long dataReviewId, Long reviewUserId);

    Boolean assign(Long dataReviewId, Long expertId,Long reviewUserId);

    TailPage<DataReviewUserVTO> userDataReview(UserDataReviewParamData userDataReviewParamData);

    Boolean userReview(Long dataReviewId, Long reviewUserId,int status);

    Boolean recheck(Long dataReviewId, Long reviewUserId, int status);
}
