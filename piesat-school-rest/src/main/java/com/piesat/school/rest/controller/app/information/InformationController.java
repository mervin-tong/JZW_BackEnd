package com.piesat.school.rest.controller.app.information;

import com.piesat.school.information.iservice.IRInformationService;
import com.piesat.school.information.param.InformationAddParamData;
import com.piesat.school.information.param.InformationPageParam;
import com.piesat.school.information.vto.InformationVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import com.smartwork.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "资讯模块")
@RestController
@RequestMapping("/app/information")
public class InformationController extends BaseController {
    @DubboReference
    private IRInformationService informationService;

    @ApiOperation(value = "新增/修改资讯")
    @PostMapping("/addOrUpdate")
    public Result<InformationVTO> addOrUpdateInformation(InformationAddParamData paramData){
        return informationService.addOrUpdateInformation(paramData);
    }

    @ApiOperation(value = "删除资讯")
    @PostMapping("/del")
    @ApiImplicitParam(name = "id", value = "资讯id", dataType = "Long" )
    public Result<Boolean> delInformation(Long id){
        return informationService.delInformation(id);
    }

    @ApiOperation(value = "删除资讯")
    @PostMapping("/detail")
    @ApiImplicitParam(name = "id", value = "资讯id", dataType = "Long" )
    public Result<InformationVTO> detail(Long id){
        return informationService.detail(id);
    }

    @ApiOperation(value = "资讯列表")
    @PostMapping("/page")
    public Result<TailPage<InformationVTO>> informationPage(InformationPageParam paramData){
        return informationService.informationPage(paramData);
    }
}
