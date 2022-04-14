package com.piesat.school.uploadpermissions.iservice;

import com.piesat.school.uploadpermissions.param.UploadPermissionOperateParamData;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRUploadPermissionsService {
    //获取上传权限列表
    Result<TailPage<UploadPermissionsVTO>> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData);

    //申请上传权限
    Result<Boolean> createPermissions(Long userId);

    //处理上传权限
    Result<Boolean> checkPermissions(UploadPermissionOperateParamData paramData);

    //管理员受理上传权限申请
    Result<Boolean> setApprover(Long approver, String uploadIds,Long limit);
    //解锁
    Result<Boolean> cleanApprover(Long approver, String uploadIds);
}
