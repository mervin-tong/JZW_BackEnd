package com.piesat.school.biz.ds.datareview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.datareview.param.ConditionScreenParamData;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewListVTO;
import com.piesat.school.datareview.vto.DataReviewReVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

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

    Boolean firstReview(Long dataReviewId, Long reviewUserId, Integer isPass, String reason);

    Boolean assign(Long dataReviewId, Long expertId,Long reviewUserId);

    DataReviewListVTO userDataReview(UserDataReviewParamData userDataReviewParamData);

    Boolean userReview(Long dataId, Long reviewUserId, int status, String reason);

    Boolean recheck(Long reviewUserId, int dataId);

    List<String> screen(ConditionScreenParamData paramData);

    List<DataReviewReVTO> checkInOrOut(Long userId, List<Long> dataList, Integer checkStatus);

    TailPage<DataReviewReVTO> selectReviewInfo(Long dataId, PageQueryParamData paramData);

    Boolean release(Long reviewUserId, int dataId);
}
