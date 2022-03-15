package com.piesat.school.provider.serv.datareview;

import com.piesat.school.biz.ds.datareview.service.IDataReviewService;
import com.piesat.school.datareview.iservice.IRDataReviewService;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suweipeng
 * @data 2022/3/8 15:33
 */
@DubboService
public class RDataReviewService implements IRDataReviewService {
    @Autowired
    private IDataReviewService iDataReviewService;

    @Override
    public Result<TailPage<DataReviewVTO>> dataReview(DataReviewParamData dataReviewParamData) {
        return Result.ofSuccess(iDataReviewService.dataReview(dataReviewParamData));
    }

    @Override
    public Result<Boolean> firstReview(Long dataReviewId, Long reviewUserId) {
        return Result.ofSuccess(iDataReviewService.firstReview(dataReviewId,reviewUserId));
    }

    @Override
    public Result<Boolean> assign(Long dataReviewId, Long expertId,Long reviewUserId) {
        return Result.ofSuccess(iDataReviewService.assign(dataReviewId,expertId,reviewUserId));
    }

    @Override
    public Result<TailPage<DataReviewUserVTO>> userDataReview(UserDataReviewParamData userDataReviewParamData) {
        return Result.ofSuccess(iDataReviewService.userDataReview(userDataReviewParamData));
    }

    @Override
    public Result<Boolean> userReview(Long dataReviewId, Long reviewUserId,int status) {
        return Result.ofSuccess(iDataReviewService.userReview(dataReviewId,reviewUserId,status));
    }

    @Override
    public Result<Boolean> recheck(Long dataReviewId, Long reviewUserId, int status) {
        return Result.ofSuccess(iDataReviewService.recheck(dataReviewId,reviewUserId,status));
    }
}
