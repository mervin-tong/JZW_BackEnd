package com.piesat.school.provider.serv.uploadpermissions;

import com.piesat.school.biz.ds.uploadpermissions.service.IUploadPermisssionsService;
import com.piesat.school.uploadpermissions.iservice.IRUploadPermissionsService;
import com.piesat.school.uploadpermissions.param.UploadPermissionCloseUpParamData;
import com.piesat.school.uploadpermissions.param.UploadPermissionOperateParamData;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.piesat.school.uploadpermissions.vto.UserPermissionVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suweipeng
 * @data 2022/2/28 16:51
 */
@DubboService
public class RUploadPermissionsService implements IRUploadPermissionsService {
    @Autowired
    private IUploadPermisssionsService iUploadPermisssionsService;
    //申请上传权限列表
    @Override
    public Result<TailPage<UploadPermissionsVTO>> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData) {
        return Result.ofSuccess(iUploadPermisssionsService.uploadPermissionsList(uploadPermissionsParamData));
    }
    //申请上传权限
    @Override
    public Result<Boolean> createPermissions(Long userId) {
        return Result.ofSuccess(iUploadPermisssionsService.createPermissions(userId));
    }
    //处理上传权限
    @Override
    public Result<Boolean> checkPermissions(UploadPermissionOperateParamData paramData) {
        return Result.ofSuccess(iUploadPermisssionsService.checkPermissions(paramData));
    }
    //锁定上传权限申请
    @Override
    public Result<Boolean> setApprover(Long approver, String uploadIds, Long limit) {
        return Result.ofSuccess(iUploadPermisssionsService.setApprover(approver,uploadIds,limit));
    }

    @Override
    public Result<Boolean> cleanApprover(Long approver, String uploadIds) {
        return Result.ofSuccess(iUploadPermisssionsService.cleanApprover(approver,uploadIds));
    }

    @Override
    public Result<UserPermissionVTO> userPermissions(Long userId) {
        return Result.ofSuccess(iUploadPermisssionsService.userPermissions(userId));
    }

    @Override
    public Result<Boolean> closeUpPermissions(UploadPermissionCloseUpParamData paramData) {
        return Result.ofSuccess(iUploadPermisssionsService.closeUpPermissions(paramData));
    }
}
