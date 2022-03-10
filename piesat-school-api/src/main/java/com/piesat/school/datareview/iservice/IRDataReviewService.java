package com.piesat.school.datareview.iservice;

import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRDataReviewService {
    Result<TailPage<DataReviewVTO>> dataReview(DataReviewParamData dataReviewParamData);
    //初审
    Result<Boolean>firstReview(Long dataReviewId, Long reviewUserId);
}
