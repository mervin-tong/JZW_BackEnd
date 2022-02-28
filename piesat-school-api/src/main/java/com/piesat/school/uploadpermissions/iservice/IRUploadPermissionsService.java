package com.piesat.school.uploadpermissions.iservice;

import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRUploadPermissionsService {
    Result<TailPage<UploadPermissionsVTO>> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData);
}
