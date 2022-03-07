package com.piesat.school.provider.serv.uploadpermissions;

import com.piesat.school.biz.ds.uploadpermissions.service.IUploadPermisssionsService;
import com.piesat.school.uploadpermissions.iservice.IRUploadPermissionsService;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
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
    public Result<Boolean> checkPermissions(Long uploadId,Integer status,Long approver) {
        return Result.ofSuccess(iUploadPermisssionsService.checkPermissions(uploadId,status,approver));
    }
    //锁定上传权限申请
    @Override
    public Result<Boolean> setApprover(Long approver, Long uploadId, Long limit) {
        return Result.ofSuccess(iUploadPermisssionsService.setApprover(approver,uploadId,limit));
    }
}
