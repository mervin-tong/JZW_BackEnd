package com.piesat.school.biz.ds.banner.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.banner.param.BannerParam;
import com.piesat.school.biz.ds.banner.entity.Banner;
import com.piesat.school.datainf.vto.BannerVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/17:54
 * @Description:
 */
public interface IBannerService extends IService<Banner> {
    Result<TailPage<BannerVTO>> bannerList(BannerParam bannerParam);

    Result<BannerVTO> saveBanner(BannerParam bannerParam);

    Result<Boolean> delete(Long id);

    Result<Boolean> updateByParam(BannerParam param);

    Result<BannerVTO> getBanner(Long id);
}
