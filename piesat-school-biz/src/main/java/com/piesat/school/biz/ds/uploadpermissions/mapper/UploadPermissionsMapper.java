package com.piesat.school.biz.ds.uploadpermissions.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suweipeng
 * @since 2022-02-28
 */
public interface UploadPermissionsMapper extends BaseMapper<UploadPermissions> {
    List<UploadPermissionsVTO> uploadPermissionsList(Integer uploadPermissionsStatus, String startAt,String endAt, Page<UploadPermissionsVTO> page);
}
