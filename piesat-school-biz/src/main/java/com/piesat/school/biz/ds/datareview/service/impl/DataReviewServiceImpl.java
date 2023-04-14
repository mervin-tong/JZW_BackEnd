package com.piesat.school.biz.ds.datareview.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.datareview.mapper.DataReviewMapper;
import com.piesat.school.biz.ds.datareview.service.IDataReviewService;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datareview.param.ConditionScreenParamData;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewListVTO;
import com.piesat.school.datareview.vto.DataReviewReVTO;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private IDatainfService datainfService;
    @Resource
    private UserMapper userMapper;
    //获取评审列表
    @Override
    public TailPage<DataReviewVTO> dataReview(DataReviewParamData dataReviewParamData) {
        Page<DataInfListVTO> page = new Page<>(dataReviewParamData.getPn(),dataReviewParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataReviewVTO> list = dataReviewMapper.dataReview(dataReviewParamData,page);
        list.stream().filter(o->o.getAdminJudgeId()!=null&&o.getAdminJudgeId()!=-1L&&o.getAdminJudgeId()!=0)
                .forEach(o->{o.setCheckMan(userMapper.selectById(o.getAdminJudgeId()).getName());});
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }
    //创建评审
    @Override
    public Boolean createReview(DataReview dataReview) {
        return this.save(dataReview);
    }

    //初审
    @Override
    public Boolean firstReview(Long dataReviewId, Long reviewUserId, Integer isPass, String reason) {
            DataReview dataReview=dataReviewMapper.selectOne(new QueryWrapper<DataReview>().eq("data_id", dataReviewId));
            if(dataReview == null){
                return false;
            }
            if(isPass==1){
                dataReview.setStatus(BizEnumType.ReviewStatus.FIRSTREVIEWPASS.getKey());
            }else {
                dataReview.setStatus(BizEnumType.ReviewStatus.FIRSTREVIEWNOPASS.getKey());
                dataReview.setNoPassReason(reason);
            }
            dataReview.setCheckedAt(new Date());
            dataReview.setAdminJudgeId(reviewUserId);

            return this.updateById(dataReview);
    }
    //指定专家
    @Override
    public Boolean assign(Long dataReviewId, Long expertId,Long reviewUserId) {
            UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("user_judge_id", expertId)
                    .set("admin_judge_id",reviewUserId)
                    .set("sent_review",new Date())
                    .eq("data_id", dataReviewId);
//                    .and(wrapper  -> wrapper
//                            .eq("admin_judge_id",reviewUserId)
//                            .or()
//                            .eq("admin_judge_id",BizEnumType.Default.NULL.getKey()));
            return this.update(updateWrapper);
    }

    //专家获取评审列表
    @Override
    public DataReviewListVTO userDataReview(UserDataReviewParamData userDataReviewParamData) {
        DataReviewListVTO dataReviewListVTO =new DataReviewListVTO();
        Page<DataInfListVTO> page = new Page<>(userDataReviewParamData.getPn(),userDataReviewParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataReviewUserVTO> list = dataReviewMapper.userDataReview(userDataReviewParamData,page);
        dataReviewListVTO.setDataReviewUserVTOS(CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list));
        List<Long> ids =dataReviewMapper.selectIds(userDataReviewParamData);
        dataReviewListVTO.setIds(ids);
        return dataReviewListVTO;
    }

    //专家进行评审
    @Override
    public Boolean userReview(Long dataId, Long reviewUserId, int status, String reason) {
        //评审id和用户id都正确后才可以处理
        UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
        Integer isPass;
        if(status==0){
            isPass=BizEnumType.ReviewStatus.RECHECKNOPASS.getKey();
            updateWrapper.set("no_pass_reason", reason);
        }else {
            isPass=BizEnumType.ReviewStatus.RECHECKPASS.getKey();
        }
        updateWrapper.set("status",isPass);
        updateWrapper.eq("user_judge_id",reviewUserId);
        updateWrapper.eq("data_id",dataId);
        updateWrapper.set("rechecked_at",new Date());
        return this.update(updateWrapper);
    }

    //发布上架
    @Override
    public Boolean recheck(Long reviewUserId, int dataId) {
        UpdateWrapper<DataReview> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",BizEnumType.ReviewStatus.RELEASE.getKey())
                .eq("data_id",dataId)
                .and(wrapper  -> wrapper
                        .eq("admin_judge_id",reviewUserId)
                        .or()
                        .eq("admin_judge_id",BizEnumType.Default.NULL.getKey()));
        return this.update(updateWrapper);
    }
    //筛选参数
    @Override
    public List<String> screen(ConditionScreenParamData paramData) {
        if (paramData.getTarget().equals("status")){
            List<String> list =new ArrayList<>();
            for(int i=0;i<6;i++){
                list.add(BizEnumType.ReviewStatus.fromKey(i).getName());
            }
            return list;
        }else if(paramData.getTarget().equals("dataName")){
            return dataReviewMapper.screen(paramData);
        }else {
            return null;
        }
    }
    //签入签出
    @Override
    public List<DataReviewReVTO> checkInOrOut(Long userId, List<Long> dataList, Integer checkStatus) {
        List<DataReview> dataReviewList =baseMapper.selectList(new QueryWrapper<DataReview>().in("data_id", dataList));
        List<DataReviewReVTO> dataReviewReVTOS =new ArrayList<>();
//        签出
        if(checkStatus == 0){
            for(DataReview dataReview:dataReviewList){
                DataReviewReVTO dataReviewReVT=new DataReviewReVTO();
                dataReview.setStatus(0);
                dataReview.setAdminJudgeId(-1L);
                baseMapper.updateById(dataReview);
                BeanUtils.copyProperties(dataReview, dataReviewReVT);
                dataReviewReVT.setCheckMan("");
                dataReviewReVTOS.add(dataReviewReVT);
            }
        }else {//签入
            for(DataReview dataReview:dataReviewList){
                DataReviewReVTO dataReviewReVT=new DataReviewReVTO();
                dataReview.setStatus(0);
                dataReview.setAdminJudgeId(userId);
                baseMapper.updateById(dataReview);
                BeanUtils.copyProperties(dataReview, dataReviewReVT);
                dataReviewReVT.setCheckMan(userMapper.selectById(userId).getName());
                dataReviewReVTOS.add(dataReviewReVT);
            }
        }
        return dataReviewReVTOS;
    }

    @Override
    public TailPage<DataReviewReVTO> selectReviewInfo(Long dataId, PageQueryParamData paramData) {
        Page<DataReviewReVTO> page = new Page<>(paramData.getPn(),paramData.getPs());
        page.setOptimizeCountSql(false);
        List<DataReviewReVTO> list = baseMapper.selectAll(dataId,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public Boolean release(Long reviewUserId, int dataId) {
        DataReview dataReview=baseMapper.selectOne(new QueryWrapper<DataReview>().eq("data_id", dataId));
        if(dataReview.getStatus().equals(BizEnumType.ReviewStatus.RECHECKPASS.getKey())){
            dataReview.setStatus(BizEnumType.ReviewStatus.RELEASE.getKey());
            int i=baseMapper.updateById(dataReview);
            datainfService.update(new UpdateWrapper<Datainf>().eq("id",dataId).set("through_review", BizEnumType.ThroughReview.PASS.getKey()));
            return i==1;
        }else {
            return false;
        }
    }
}
