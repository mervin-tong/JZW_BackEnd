package com.piesat.school.provider.serv.banner;

import com.piesat.school.banner.iservice.IRBannerService;
import com.piesat.school.banner.param.BannerParam;
import com.piesat.school.biz.ds.banner.service.IBannerService;
import com.piesat.school.datainf.vto.BannerVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/18:25
 * @Description:
 */
@DubboService
public class RBannerService implements IRBannerService {
    @Resource
    private IBannerService iBannerService;
    @Override
    public Result<TailPage<BannerVTO>> bannerList(BannerParam bannerParam) {
        return iBannerService.bannerList(bannerParam);
    }

    @Override
    public Result<BannerVTO> save(BannerParam bannerParam) {
        return iBannerService.saveBanner(bannerParam);
    }

    @Override
    public Result<Boolean> delete(Long id) {
        return iBannerService.delete(id);
    }

    @Override
    public Result<Boolean> update(BannerParam param) {
        return iBannerService.updateByParam(param);
    }

    @Override
    public Result<BannerVTO> getBanner(Long id) {
        return iBannerService.getBanner(id);
    }
}
