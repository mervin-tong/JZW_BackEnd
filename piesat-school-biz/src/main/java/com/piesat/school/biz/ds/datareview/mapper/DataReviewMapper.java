package com.piesat.school.biz.ds.datareview.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;

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

    List<DataReviewVTO> dataReview(Page<DataInfListVTO> page);
}
