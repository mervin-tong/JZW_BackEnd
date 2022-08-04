package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.biz.ds.datainf.mapper.DataShareinfMapper;
import com.piesat.school.biz.ds.datainf.service.IContactService;
import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.datareview.mapper.DataReviewMapper;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DataShareinfServiceImpl extends ServiceImpl<DataShareinfMapper, DataShareinf> implements IDataShareinfService {


    @Resource
    private UserMapper userMapper;
    @Resource
    private DataReviewMapper dataReviewMapper;
    @Resource
    private IContactService contactService;
    @Resource
    private  DataShareinfMapper dataShareinfMapper;

    @Override
    public TailPage<ShareInfVTO> dataList(DataShareParamData paramData) {
//        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
//
//        queryWrapper.lambda().eq(DataShareinf::getApplyId, BizEnumType.CommonStatus.Invalid.getKey());
//
//        Page<DataShareinf> dataInfos=this.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper);
//
//        List<ShareInfVTO> myDataInfVTOS=new ArrayList<>();
//        if(dataInfos.getRecords().size()>0){
//            List<Long> userIds=dataInfos.getRecords().stream().map(DataShareinf::getId).collect(Collectors.toList());
//            List<Long> applyids=dataInfos.getRecords().stream().filter(e->e.getApplyId()!=null).map(DataShareinf::getApplyId).collect(Collectors.toList());
//            List<Long> dataIds = dataInfos.getRecords().stream().map(DataShareinf::getId).collect(Collectors.toList());
//            List<DataReview> dataReviews=dataReviewMapper.selectList(new QueryWrapper<DataReview>().in("data_id",dataIds));
//        }
//        return CommonPage.buildPage(dataInfos.getCurrent(),dataInfos.getSize(),dataInfos.getTotal(),myDataInfVTOS);
        Page<ShareInfVTO> page = new Page<>(paramData.getPn(),paramData.getPs());
        page.setOptimizeCountSql(false);
        List<ShareInfVTO> list = dataShareinfMapper.searchAll(paramData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }




}
