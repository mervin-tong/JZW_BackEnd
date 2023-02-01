package com.piesat.school.biz.ds.banner.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.piesat.school.banner.param.BannerParam;
import com.piesat.school.biz.ds.banner.entity.Banner;
import com.piesat.school.biz.ds.banner.mapper.BannerMapper;
import com.piesat.school.biz.ds.banner.service.IBannerService;
import com.piesat.school.datainf.vto.BannerVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/17:58
 * @Description:
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Override
    public Result<TailPage<BannerVTO>> bannerList(BannerParam bannerParam) {
        Page<BannerVTO> page=new Page<>(bannerParam.getPn(),bannerParam.getPs());
        QueryWrapper<Banner> queryWrapper=new QueryWrapper<>();
        List<Banner> list=bannerMapper.selectList(queryWrapper);
        List<BannerVTO> al=list.stream().map(o->{
            BannerVTO bannerVTO=new BannerVTO();
            BeanUtils.copyProperties(o,bannerVTO);
        return bannerVTO;
        }).collect(Collectors.toList());

        return Result.ofSuccess(CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),al));
    }

    @Override
    public Result<BannerVTO> saveBanner(BannerParam bannerParam) {
        Banner banner=new Banner();
        if (bannerParam.getName()!=null&&!Objects.equals(bannerParam.getName(), "")) {
            banner.setName(bannerParam.getName());
        }
        if (bannerParam.getUrl()!=null&& !Objects.equals(bannerParam.getUrl(), "")){
            banner.setUrl(bannerParam.getUrl());
        }
        BannerVTO bannerVTO=new BannerVTO();
        bannerMapper.insert(banner);
        BeanUtils.copyProperties(banner,bannerVTO);
        return Result.ofSuccess(bannerVTO);
    }

    @Override
    public Result<Boolean> delete(Long id) {
        boolean flag=false;
        if (bannerMapper.deleteById(id)>0) {
            flag=true;
        }
        return Result.ofSuccess(flag);

    }

    @Override
    public Result<Boolean> updateByParam(BannerParam param) {
        Banner banner=bannerMapper.selectById(param.getId());
        if (param.getName()!=null&&!Objects.equals(param.getName(), "")) {
            banner.setName(param.getName());
        }
        if (param.getUrl()!=null&& !Objects.equals(param.getUrl(), "")){
            banner.setUrl(param.getUrl());
        }
        boolean flag=false;
        if (bannerMapper.updateById(banner)>0){
            flag=true;
        }
        return Result.ofSuccess(flag);
    }

    @Override
    public Result<BannerVTO> getBanner(Long id) {
        BannerVTO bannerVTO=new BannerVTO();
        Banner banner=bannerMapper.selectById(id);
        bannerVTO.setCreatedAt(banner.getCreatedAt());
        bannerVTO.setId(banner.getId());
        bannerVTO.setName(banner.getName());
        bannerVTO.setUpdatedAt(banner.getUpdatedAt());
        bannerVTO.setUrl(banner.getUrl());
        return Result.ofSuccess(bannerVTO);
    }

}
