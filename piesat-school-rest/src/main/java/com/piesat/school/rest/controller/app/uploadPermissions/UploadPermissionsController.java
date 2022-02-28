package com.piesat.school.rest.controller.app.uploadPermissions;

import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.uploadpermissions.iservice.IRUploadPermissionsService;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suweipeng
 * @data 2022/2/28 15:42
 * 上传权限控制类
 */
@Api(tags = "上传权限模块")
@RestController
@RequestMapping("/app/uploadPermissions")
public class UploadPermissionsController {
    @DubboReference(consumer = DubboConstant.CONSUMER_NAME)
    private IRUploadPermissionsService irUploadPermissionsService;
    @PostMapping(value = "uploadpermissionsList")
    public Result<TailPage<UploadPermissionsVTO>> uploadPermissionsList(@RequestBody UploadPermissionsParamData uploadPermissionsParamData){
        return irUploadPermissionsService.uploadPermissionsList(uploadPermissionsParamData);
    }


}
