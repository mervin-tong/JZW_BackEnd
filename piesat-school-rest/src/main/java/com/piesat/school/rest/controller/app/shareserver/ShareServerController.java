package com.piesat.school.rest.controller.app.shareserver;

import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.rest.aspect.AroundRecord;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "模块")
@RestController
@RequestMapping("/app/share")
public class ShareServerController {

    @DubboReference
    private IRDataShareInfService dataInfService;
    @AroundRecord
    @ApiOperation(value = "会员数据列表")
    @PostMapping("/dataList")
    public Result<TailPage<ShareInfVTO>> datalist(DataShareParamData paramData){
//        TailPage<ShareInfVTO> shareInfVTOS=dataInfService.datalist(paramData);
        return Result.ofSuccess(dataInfService.datalist(paramData));
    }
    @PostMapping("/applyForKey")
    public Result<ShareInfVTO> applyForKey(DataShareParamData paramData){
        return dataInfService.applyForKey(paramData);
    }
    @PostMapping("/checkStatus")
    public Result<ShareInfVTO> checkStatus(DataShareParamData dataShareParamData){
        return dataInfService.checkStatus(dataShareParamData);
    }
    @PostMapping("/keyToUrl")
    public Result<ShareInfVTO> keyToUrl(DataShareParamData dataShareParamData){
        return dataInfService.keyToUrl(dataShareParamData);
    }



}
