package com.piesat.school.biz.ds.datareview.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.datareview.mapper.DataReviewMapper;
import com.piesat.school.biz.ds.datareview.service.IDataReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suweipeng
 * @since 2022-03-08
 */
@Service
public class DataReviewServiceImpl extends ServiceImpl<DataReviewMapper, DataReview> implements IDataReviewService {
    @Resource
    private DataReviewMapper dataReviewMapper;
    @Override
    public TailPage<DataReviewVTO> dataReview(DataReviewParamData dataReviewParamData) {
        Page<DataInfListVTO> page = new Page<>(dataReviewParamData.getPn(),dataReviewParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataReviewVTO> list = dataReviewMapper.dataReview(page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public Boolean createReview(DataReview dataReview) {
        return this.save(dataReview);
    }

    @Override
    public Boolean firstReview(Long dataReviewId, Long reviewUserId) {
        DataReview dataReview = BizCommonValidateHelper.valdiateGetById(dataReviewId, this);
        if (dataReview.getAdminJudgeId() == 0L
                || dataReview.getAdminJudgeId() == reviewUserId){
            UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status",BizEnumType.ReviewStatus.FIRSTREVIEWPASS.getKey());
            updateWrapper.eq("id",dataReviewId);
            return this.update(updateWrapper);
        }
        return Boolean.FALSE;

    }
}
