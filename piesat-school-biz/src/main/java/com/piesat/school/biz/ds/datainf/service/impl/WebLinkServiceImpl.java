package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.datainf.entity.WebLink;
import com.piesat.school.biz.ds.datainf.mapper.WebLinkMapper;
import com.piesat.school.biz.ds.datainf.service.IWebLinkService;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */
@Service
public class WebLinkServiceImpl extends ServiceImpl<WebLinkMapper, WebLink> implements IWebLinkService {

    @Resource
    WebLinkMapper webLinkMapper;

    @Override
    public TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData) {
        Page<WebLinkVTO> page = new Page<>(webLinkParamData.getPn(),webLinkParamData.getPs());
        page.setOptimizeCountSql(false);
        List<WebLinkVTO> list = webLinkMapper.linkList(webLinkParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public Result<WebLinkVTO> addLink(WebLinkParamData webLinkParamData) {
        WebLink webLink=new WebLink();
        BeanUtils.copyProperties(webLinkParamData,webLink);
        WebLinkVTO webLinkVTO=new WebLinkVTO();
        BeanUtils.copyProperties(webLinkParamData,webLinkVTO);
        webLinkMapper.insert(webLink);
        return Result.ofSuccess(webLinkVTO);

    }

    @Override
    public Result<WebLinkVTO> updateLink(WebLinkParamData webLinkParamData) {
        WebLink webLink=new WebLink();
        BeanUtils.copyProperties(webLinkParamData,webLink);
        webLinkMapper.updateById(webLink);
        WebLinkVTO webLinkVTO=new WebLinkVTO();
        BeanUtils.copyProperties(webLinkParamData,webLinkVTO);
        return Result.ofSuccess(webLinkVTO);
    }

    @Override
    public Result<WebLinkVTO> deleteLink(WebLinkParamData webLinkParamData) {
        webLinkMapper.deleteById(webLinkParamData.getId());
        WebLinkVTO webLinkVTO=new WebLinkVTO();
        BeanUtils.copyProperties(webLinkParamData,webLinkVTO);
        return Result.ofSuccess(webLinkVTO);
    }
}
