package com.piesat.school.rest.controller.app.helpcenter;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.datainf.param.UploadFileParamData;
import com.piesat.school.helpcenter.iservice.IRHelpCenterService;
import com.piesat.school.helpcenter.vto.HelpCenterVTO;
import com.piesat.school.rest.utils.FileUploadUtils;
import com.piesat.school.rest.utils.FileUtils;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@Api(tags = "帮助中心")
@RestController
@RequestMapping("/help")
public class HelpCenterController {
    @DubboReference
    private IRHelpCenterService helpCenterService;

    @ApiOperation(value = "文件列表（分页）")
    @GetMapping ("/page")
    public Result<TailPage<HelpCenterVTO>> page(PageQueryParamData query)  {
        TailPage<HelpCenterVTO> helpCenterVTOS = helpCenterService.page(query);
        return Result.ofSuccess(helpCenterVTOS);
    }

    @ApiOperation(value = "文件列表")
    @GetMapping("/list")
    public Result<List<HelpCenterVTO>> list() {
      List<HelpCenterVTO> result = helpCenterService.list();
        return Result.ofSuccess(result);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody HelpCenterVTO helpCenterVTO)  {
        Boolean result = helpCenterService.save(helpCenterVTO);
        return Result.ofSuccess(result);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody HelpCenterVTO helpCenterVTO) {
        Boolean result = helpCenterService.update(helpCenterVTO);
        return Result.ofSuccess(result);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Result<Boolean> delete(String ids) {
        Boolean result = helpCenterService.delete(ids);
        return Result.ofSuccess(result);
    }

    @ApiOperation(value = "根据Id查询")
    @GetMapping("/detail")
    public Result<HelpCenterVTO> detail(String id) {
        HelpCenterVTO result = helpCenterService.detail(id);
        return Result.ofSuccess(result);
    }
}
