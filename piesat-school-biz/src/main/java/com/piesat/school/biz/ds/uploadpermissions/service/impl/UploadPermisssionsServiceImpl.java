package com.piesat.school.biz.ds.uploadpermissions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermisssions;
import com.piesat.school.biz.ds.uploadpermissions.mapper.UploadPermisssionsMapper;
import com.piesat.school.biz.ds.uploadpermissions.service.IUploadPermisssionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  上传权限类
 * </p>
 *
 * @author suweipeng
 * @since 2022-02-28
 */
@Service
public class UploadPermisssionsServiceImpl extends ServiceImpl<UploadPermisssionsMapper, UploadPermisssions> implements IUploadPermisssionsService {

    @Autowired
    private UploadPermisssionsMapper uploadPermisssionsMapper;
    @Override
    public TailPage<UploadPermissionsVTO> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData) {
        List<UploadPermissionsVTO> list = uploadPermisssionsMapper.uploadPermissionsList(uploadPermissionsParamData);
        Page<UploadPermissionsVTO> page = new Page<>(uploadPermissionsParamData.getPn(),uploadPermissionsParamData.getPs());
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }
}
