package com.piesat.school.rest.controller.app.FileUpload;

import com.piesat.school.datainf.param.UploadFileParamData;
import com.piesat.school.rest.utils.FileUploadUtils;
import com.piesat.school.rest.utils.FileUtils;
import com.smartwork.api.Result;
import com.smartwork.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Api(tags = "文件上传模块")
@RestController
@RequestMapping("")
public class FileUploadController extends BaseController {
    @ApiOperation(value = "上传文件")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/uploadData")
    public Result<UploadFileParamData> uploadDataInf(MultipartFile file) throws Exception {
        UploadFileParamData res=new UploadFileParamData();
        String fileLocation = FileUploadUtils.upload(file);
        String amount = FileUtils.getAmount(file.getSize());
        res.setAmount(amount);
        res.setUploadPath(fileLocation);
        return Result.ofSuccess(res);
    }
}
