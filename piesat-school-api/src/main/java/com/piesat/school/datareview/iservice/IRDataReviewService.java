package com.piesat.school.datareview.iservice;

import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRDataReviewService {
    //获取评审列表
    Result<TailPage<DataReviewVTO>> dataReview(DataReviewParamData dataReviewParamData);
    //初审
    Result<Boolean>firstReview(Long dataReviewId, Long reviewUserId);
    //指定专家id
    Result<Boolean> assign(Long dataReviewId, Long expertId,Long reviewUserId);
    //获取用户评审列表
    Result<TailPage<DataReviewUserVTO>> userDataReview(UserDataReviewParamData userDataReviewParamData);
    //专家处理评审
    Result<Boolean> userReview(Long dataReviewId, Long reviewUserId,int status);
    //管理员复审
    Result<Boolean> recheck(Long dataReviewId, Long reviewUserId, int status);
}
