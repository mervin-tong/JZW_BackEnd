package com.piesat.school.rest.controller.app.weblink;

import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.iservice.IRWebLinkService;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.piesat.school.rest.aspect.AroundRecord;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */
@Api(tags = "web链接管理模块")
@RestController
@RequestMapping("/app/webLink")
public class WebLinkController {

    @DubboReference
    private IRDataShareInfService dataInfService;

    @DubboReference
    private IRWebLinkService irWebLinkService;
    @AroundRecord
    @ApiOperation(value = "web链接管理列表")
    @GetMapping("/linkList")
    public TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData){
        return irWebLinkService.linkList(webLinkParamData);
    }
    @ApiModelProperty(value = "新增链接")
    @PostMapping ("/addLink")
    public Result<WebLinkVTO> addLink(WebLinkParamData webLinkParamData){

        return irWebLinkService.addLink(webLinkParamData);
    }

    @ApiModelProperty(value = "修改链接")
    @PostMapping("/updateLink")
    public Result<WebLinkVTO> updateLink(WebLinkParamData webLinkParamData){
        return irWebLinkService.updateLink(webLinkParamData);
    }
    @ApiModelProperty(value = "删除链接")
    @PostMapping("/deleteLink")
    public Result<WebLinkVTO> deleteLink(WebLinkParamData webLinkParamData){
        return irWebLinkService.deleteLink(webLinkParamData);
    }



}
