package com.piesat.school.rest.controller.app.uploadpermissions;

import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.uploadpermissions.iservice.IRUploadPermissionsService;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )

    })
    public Result<TailPage<UploadPermissionsVTO>> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData){
        return irUploadPermissionsService.uploadPermissionsList(uploadPermissionsParamData);
    }

    @ApiOperation(value = "申请上传权限")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping(value = "createPermissions")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "申请上传权限的用户id", dataType = "Long" ),
    })
    public Result<Boolean> createPermissions(Long userId){
        return irUploadPermissionsService.createPermissions(userId);
    }


    @ApiOperation(value = "锁定上传权限申请")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping(value = "setApprover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "approver", value = "锁定申请的管理员", dataType = "Long" ),
            @ApiImplicitParam(name = "uploadId", value = "要锁定的上传权限id", dataType = "Long" ),
            @ApiImplicitParam(name = "limit", value = "要锁定的时间（单位秒）", dataType = "Long" )
    })
    public Result<Boolean> setApprover(Long approver,Long uploadId,Long limit){
        return irUploadPermissionsService.setApprover(approver,uploadId,limit);
    }


    @ApiOperation(value = "处理上传权限")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping(value = "checkPermissions")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uploadId", value = "要处理的上传权限id", dataType = "Long" ),
            @ApiImplicitParam(name = "status", value = "处理状态（1 = 通过，2 = 拒绝）默认为0", dataType = "Integer" ,defaultValue = "0"),
            @ApiImplicitParam(name = "approver", value = "审批人id", dataType = "Long" )
    })
    public Result<Boolean> checkPermissions(Long uploadId,Integer status,Long approver){
        return irUploadPermissionsService.checkPermissions(uploadId,status,approver);
    }



}
