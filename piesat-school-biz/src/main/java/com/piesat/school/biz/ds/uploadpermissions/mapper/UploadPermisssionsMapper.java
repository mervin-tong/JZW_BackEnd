package com.piesat.school.biz.ds.uploadpermissions.mapper;

import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermisssions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suweipeng
 * @since 2022-02-28
 */
public interface UploadPermisssionsMapper extends BaseMapper<UploadPermisssions> {

    List<UploadPermissionsVTO> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData);

}
