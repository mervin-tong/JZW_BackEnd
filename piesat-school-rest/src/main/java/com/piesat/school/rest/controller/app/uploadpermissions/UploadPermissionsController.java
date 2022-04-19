package com.piesat.school.rest.controller.app.uploadpermissions;

import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.uploadpermissions.iservice.IRUploadPermissionsService;
import com.piesat.school.uploadpermissions.param.UploadPermissionAddParamData;
import com.piesat.school.uploadpermissions.param.UploadPermissionCloseUpParamData;
import com.piesat.school.uploadpermissions.param.UploadPermissionOperateParamData;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.piesat.school.uploadpermissions.vto.UserPermissionVTO;
import com.piesat.school.user.param.LimitUserParamData;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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

    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @ApiOperation(value = "获取上传权限列表(分页)")
    @PostMapping(value = "uploadpermissionsList")
    public Result<TailPage<UploadPermissionsVTO>> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData){
        return irUploadPermissionsService.uploadPermissionsList(uploadPermissionsParamData);
    }

    @ApiOperation(value = "申请上传权限")
    @PostMapping(value = "createPermissions")
    public Result<Boolean> createPermissions(UploadPermissionAddParamData paramData){
        return irUploadPermissionsService.createPermissions(paramData);
    }

    @ApiOperation(value = "上传权限申请详情")
    @GetMapping(value = "detail")
    @ApiImplicitParam(value = "上传申请id",name = "id")
    public Result<UploadPermissionsVTO> detail(Long id){
        return irUploadPermissionsService.detail(id);
    }
    @ApiOperation(value = "锁定上传权限申请")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping(value = "setApprover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "approver", value = "锁定申请的管理员", dataType = "Long" ),
            @ApiImplicitParam(name = "uploadIds", value = "要锁定的上传权限ids,逗号分割", dataType = "String" ),
            @ApiImplicitParam(name = "limit", value = "要锁定的时间（单位秒）", dataType = "Long" )
    })
    public Result<Boolean> setApprover(Long approver,String uploadIds,Long limit){
        return irUploadPermissionsService.setApprover(approver,uploadIds,limit);
    }
    @ApiOperation(value = "清除上传权限锁定")
    @PostMapping(value = "cleanApprover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "approver", value = "锁定申请的管理员", dataType = "Long" ),
            @ApiImplicitParam(name = "uploadIds", value = "要锁定的上传权限ids,逗号分割", dataType = "String" )
    })
    public Result<Boolean> cleanApprover(Long approver,String uploadIds){
        return irUploadPermissionsService.cleanApprover(approver,uploadIds);
    }


    @ApiOperation(value = "处理上传权限")
    @PostMapping(value = "checkPermissions")
    public Result<Boolean> checkPermissions(UploadPermissionOperateParamData paramData){
        return irUploadPermissionsService.checkPermissions(paramData);
    }

    @ApiOperation(value = "用户上传权限信息")
    @PostMapping("userPermissions")
    public Result<UserPermissionVTO> userPermissions(Long userId){
        return irUploadPermissionsService.userPermissions(userId);
    }

    @ApiOperation(value = "开启/关闭用户上传权限")
    @PostMapping("closeUpPermissions")
    public Result<Boolean> closeUpPermissions(UploadPermissionCloseUpParamData paramData){
        return irUploadPermissionsService.closeUpPermissions(paramData);
    }

}
