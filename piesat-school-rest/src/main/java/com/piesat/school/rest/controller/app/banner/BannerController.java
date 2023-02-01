package com.piesat.school.rest.controller.app.banner;

import com.piesat.school.banner.iservice.IRBannerService;
import com.piesat.school.banner.param.BannerParam;
import com.piesat.school.datainf.vto.BannerVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/17:27
 * @Description:
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/app/banner")
public class BannerController {
    @DubboReference
    private IRBannerService irBannerServer;

    @ApiOperation(value = "列表")
    @GetMapping("/bannerList")
    public Result<TailPage<BannerVTO>> bannerList( BannerParam bannerParam){
        return irBannerServer.bannerList(bannerParam);
    }
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Result<BannerVTO> save(@RequestBody BannerParam bannerParam){
        return irBannerServer.save(bannerParam);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Boolean> delete(Long id){
        return irBannerServer.delete(id);
    }
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Boolean> update(BannerParam param){
        return irBannerServer.update(param);
    }

    @ApiModelProperty(value = "信息")
    @GetMapping("/get")
    public Result<BannerVTO> getBanner(Long id){
        return irBannerServer.getBanner(id);
    }
}
