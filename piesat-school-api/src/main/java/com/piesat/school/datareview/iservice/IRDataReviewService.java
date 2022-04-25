package com.piesat.school.datareview.iservice;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.datareview.param.ConditionScreenParamData;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewReVTO;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IRDataReviewService {
    //获取评审列表
    Result<TailPage<DataReviewVTO>> dataReview(DataReviewParamData dataReviewParamData);
    //初审
    Result<Boolean>firstReview(Long dataReviewId, Long reviewUserId, Integer isPass, String reason);
    //指定专家id
    Result<Boolean> assign(Long dataReviewId, Long expertId,Long reviewUserId);
    //获取用户评审列表
    Result<TailPage<DataReviewUserVTO>> userDataReview(UserDataReviewParamData userDataReviewParamData);
    //专家处理评审
    Result<Boolean> userReview(Long dataId, Long reviewUserId, int status, String reason);
    //管理员复审
    Result<Boolean> recheck( Long reviewUserId, int dataId);
    //查询条件筛选
    Result<List<String>> screen(ConditionScreenParamData paramData);
    //签入签出状态更改
    Result<List<DataReviewReVTO>> checkInOrOut(Long userId, List<Long> dataList, Integer checkStatus);
    //查询审核信息
    Result<TailPage<DataReviewReVTO>> selectRevicwInfo(Long dataId, PageQueryParamData paramData);
}
