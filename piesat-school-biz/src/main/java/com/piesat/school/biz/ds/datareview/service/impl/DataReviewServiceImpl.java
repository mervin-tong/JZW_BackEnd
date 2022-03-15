package com.piesat.school.biz.ds.datareview.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.datareview.mapper.DataReviewMapper;
import com.piesat.school.biz.ds.datareview.service.IDataReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  评审处理实现类
 * </p>
 *
 * @author suweipeng
 * @since 2022-03-08
 */
@Service
public class DataReviewServiceImpl extends ServiceImpl<DataReviewMapper, DataReview> implements IDataReviewService {
    @Resource
    private DataReviewMapper dataReviewMapper;
    //获取评审列表
    @Override
    public TailPage<DataReviewVTO> dataReview(DataReviewParamData dataReviewParamData) {
        Page<DataInfListVTO> page = new Page<>(dataReviewParamData.getPn(),dataReviewParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataReviewVTO> list = dataReviewMapper.dataReview(dataReviewParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }
    //创建评审
    @Override
    public Boolean createReview(DataReview dataReview) {
        return this.save(dataReview);
    }

    //初审
    @Override
    public Boolean firstReview(Long dataReviewId, Long reviewUserId) {
            UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status",BizEnumType.ReviewStatus.FIRSTREVIEWPASS.getKey())
            .eq("id",dataReviewId)
            .and(wrapper  -> wrapper
                    .eq("admin_judge_id",reviewUserId)
                    .or()
                    .eq("admin_judge_id",BizEnumType.Default.NULL.getKey()));
            return this.update(updateWrapper);
    }
    //指定专家
    @Override
    public Boolean assign(Long dataReviewId, Long expertId,Long reviewUserId) {
            UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("user_judge_id", expertId)
                    .eq("id", dataReviewId)
                    .and(wrapper  -> wrapper
                            .eq("admin_judge_id",reviewUserId)
                            .or()
                            .eq("admin_judge_id",BizEnumType.Default.NULL.getKey()));
            return this.update(updateWrapper);
    }

    //专家获取评审列表
    @Override
    public TailPage<DataReviewUserVTO> userDataReview(UserDataReviewParamData userDataReviewParamData) {
        Page<DataInfListVTO> page = new Page<>(userDataReviewParamData.getPn(),userDataReviewParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataReviewUserVTO> list = dataReviewMapper.userDataReview(userDataReviewParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    //专家进行评审
    @Override
    public Boolean userReview(Long dataReviewId, Long reviewUserId,int status) {
        //评审id和用户id都正确后才可以处理
        UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",status);
        updateWrapper.eq("user_judge_id",reviewUserId);
        updateWrapper.eq("id",dataReviewId);
        return this.update(updateWrapper);
    }

    //管理员复审
    @Override
    public Boolean recheck(Long dataReviewId, Long reviewUserId, int status) {
        UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",status)
                .eq("id",dataReviewId)
                .and(wrapper  -> wrapper
                        .eq("admin_judge_id",reviewUserId)
                        .or()
                        .eq("admin_judge_id",BizEnumType.Default.NULL.getKey()));
        return this.update(updateWrapper);
    }
}
