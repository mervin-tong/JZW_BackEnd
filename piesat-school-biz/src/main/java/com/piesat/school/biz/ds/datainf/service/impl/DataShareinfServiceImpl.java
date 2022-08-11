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
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DataShareinfServiceImpl extends ServiceImpl<DataShareinfMapper, DataShareinf> implements IDataShareinfService {



    @Resource
    private  DataShareinfMapper dataShareinfMapper;

    @Override
    public TailPage<ShareInfVTO> dataList(DataShareParamData paramData) {
//      审核申请列表查询
        Page<ShareInfVTO> page = new Page<>(paramData.getPn(),paramData.getPs());
        page.setOptimizeCountSql(false);
        List<ShareInfVTO> list = dataShareinfMapper.searchAll(paramData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }

    @Override
    public Result<ShareInfVTO> applyForKey(DataShareParamData paramData) {
//      传入applyExplain、applyId
        ShareInfVTO shareInfVTO=new ShareInfVTO();
        if (paramData.getApplyId()!=null){
           QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
           queryWrapper.lambda().eq(DataShareinf::getApplyId,paramData.getApplyId()).orderByDesc(DataShareinf::getUpdatedAt);
            List<DataShareinf> dataShareInfos=this.list(queryWrapper);
            if(dataShareInfos!=null&&dataShareInfos.size()>0){
                shareInfVTO=new ShareInfVTO();
                BeanUtils.copyProperties(dataShareInfos.get(0),shareInfVTO);

            }
            shareInfVTO.setApplyExplain(paramData.getApplyExplain());
        }

        DataShareinf dataShareinf=new DataShareinf();
        BeanUtils.copyProperties(shareInfVTO,dataShareinf);
        if (dataShareinf.getApplyStatus()==0){
            dataShareinfMapper.updateById(dataShareinf);
        }
        dataShareinfMapper.insert(dataShareinf);


        return Result.ofSuccess(shareInfVTO);
    }


    @Override
    public Result<ShareInfVTO> checkStatus(DataShareParamData paramData) {
//      判断状态，0表示审核不通过，1表示审核通过，2表示审核中
        ShareInfVTO vto=null;
        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(DataShareinf::getApplyId,paramData.getApplyId()).orderByDesc(DataShareinf::getUpdatedAt);
        List<DataShareinf> dataShareInfos=this.list(queryWrapper);
        if(dataShareInfos!=null&&dataShareInfos.size()>0){
            vto=new ShareInfVTO();
            BeanUtils.copyProperties(dataShareInfos.get(0),vto);
        }
        return Result.ofSuccess(vto);

    }


    @Override
    public ShareInfVTO keyToUrl(DataShareParamData dataShareParamData) {
//      传入参数key，返回拼接好的url
        ShareInfVTO vto=new ShareInfVTO();
        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(DataShareinf::getApiKey,dataShareParamData.getApiKey());
        List<DataShareinf> dataShareInfos=this.list(queryWrapper);
        if(dataShareInfos!=null&&dataShareInfos.size()>0){
            BeanUtils.copyProperties(dataShareInfos.get(0),vto);
        }
        return vto;
    }

    @Override
    public TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData) {
        Page<AuditApplyListVTO> page = new Page<>(auditApplyListParamData.getPn(),auditApplyListParamData.getPs());
        page.setOptimizeCountSql(false);
        List<AuditApplyListVTO> list = dataShareinfMapper.auditApplyList(auditApplyListParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }

    @Override
    public AuditApplyListVTO detail(AuditApplyListParamData auditApplyListParamData) {
        List<AuditApplyListVTO> list=dataShareinfMapper.detail(auditApplyListParamData);


        return list.get(0);
    }

    @Override
    public Result<ShareInfVTO> pass(DataShareParamData dataShareParamData) {
//      传入参数applyId、applyStatus、mark
        ShareInfVTO vto=null;
        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(DataShareinf::getApplyId,dataShareParamData.getApplyId()).orderByDesc(DataShareinf::getUpdatedAt);
        List<DataShareinf> dataShareInfos=this.list(queryWrapper);
        if(dataShareInfos!=null&&dataShareInfos.size()>0){
            vto=new ShareInfVTO();
            BeanUtils.copyProperties(dataShareInfos.get(0),vto);
        }
        DataShareinf dataShareinf=new DataShareinf();
        if (dataShareParamData.getApplyStatus()==1){
            vto.setApplyStatus(1);
            BeanUtils.copyProperties(vto,dataShareinf);
        }
        else if (dataShareParamData.getApplyStatus()==0){
            vto.setApplyStatus(0);
            vto.setMark(dataShareParamData.getMark());
            BeanUtils.copyProperties(vto,dataShareinf);
        }
        dataShareinfMapper.updateById(dataShareinf);
        return Result.ofSuccess(vto);
    }


}
