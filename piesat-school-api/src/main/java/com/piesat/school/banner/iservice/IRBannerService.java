package com.piesat.school.banner.iservice;

import com.piesat.school.banner.param.BannerParam;
import com.piesat.school.datainf.vto.BannerVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/18:24
 * @Description:
 */
public interface IRBannerService {
    Result<TailPage<BannerVTO>> bannerList(BannerParam bannerParam);

    Result<BannerVTO> save(BannerParam bannerParam);

    Result<Boolean> delete(Long id);

    Result<Boolean> update(BannerParam param);

    Result<BannerVTO> getBanner(Long id);
}
