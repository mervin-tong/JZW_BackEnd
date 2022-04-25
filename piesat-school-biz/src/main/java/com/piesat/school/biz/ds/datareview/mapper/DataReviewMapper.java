package com.piesat.school.biz.ds.datareview.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datareview.param.ConditionScreenParamData;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewReVTO;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suweipeng
 * @since 2022-03-08
 */
public interface DataReviewMapper extends BaseMapper<DataReview> {

    List<DataReviewVTO> dataReview(@Param("param") DataReviewParamData dataReviewParamData, Page<DataInfListVTO> page);

    List<DataReviewUserVTO> userDataReview(@Param("userDataReviewParamData") UserDataReviewParamData userDataReviewParamData, Page<DataInfListVTO> page);

    List<String> screen(@Param("param") ConditionScreenParamData paramData);

    List<DataReviewReVTO> selectAll(@Param("param") Long dataId, Page<DataReviewReVTO> page);
}
