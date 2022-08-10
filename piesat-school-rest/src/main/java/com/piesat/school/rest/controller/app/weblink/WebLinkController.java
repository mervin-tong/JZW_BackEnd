package com.piesat.school.rest.controller.app.weblink;

import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.rest.aspect.AroundRecord;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "api服务模块")
@RestController
@RequestMapping("/app/webLink")
public class WebLinkController {

    @DubboReference
    private IRDataShareInfService dataInfService;
    @AroundRecord
    @ApiOperation(value = "web链接管理列表")
    @PostMapping("/linkList")
    public Result<TailPage<AuditApplyListVTO>> linkList(AuditApplyListParamData auditApplyListParamData){
        return Result.ofSuccess(dataInfService.auditApplyList(auditApplyListParamData));
    }
    @ApiModelProperty(value = "新增链接")
    @PostMapping("/addLink")
    public Result<ShareInfVTO> addLink(DataShareParamData paramData){
        return dataInfService.addLink(paramData);
    }

    @ApiModelProperty(value = "修改链接")
    @PostMapping("/updateLink")
    public Result<ShareInfVTO> updateLink(DataShareParamData dataShareParamData){
        return dataInfService.updateLink(dataShareParamData);
    }
    @ApiModelProperty(value = "删除链接")
    @PostMapping("/deleteLink")
    public Result<String> deleteLink(DataShareParamData dataShareParamData){
        return dataInfService.deleteLink(dataShareParamData);
    }



}
