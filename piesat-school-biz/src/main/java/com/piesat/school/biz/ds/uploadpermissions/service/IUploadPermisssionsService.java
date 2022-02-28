package com.piesat.school.biz.ds.uploadpermissions.service;

import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermisssions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 *  上传权限类
 * </p>
 *
 * @author suweipeng
 * @since 2022-02-28
 */
public interface IUploadPermisssionsService extends IService<UploadPermisssions> {


    TailPage<UploadPermissionsVTO> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData);
}
