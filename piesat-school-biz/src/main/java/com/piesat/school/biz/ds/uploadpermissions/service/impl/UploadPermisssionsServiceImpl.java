package com.piesat.school.biz.ds.uploadpermissions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermisssions;
import com.piesat.school.biz.ds.uploadpermissions.mapper.UploadPermisssionsMapper;
import com.piesat.school.biz.ds.uploadpermissions.service.IUploadPermisssionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Page<UploadPermissionsVTO> page = new Page<>(uploadPermissionsParamData.getPn(),uploadPermissionsParamData.getPs());
        List<UploadPermissionsVTO> list = uploadPermisssionsMapper.uploadPermissionsList(page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public Boolean createPermissions(Long userId) {
        UploadPermisssions uploadPermisssions = new UploadPermisssions();
        uploadPermisssions.setStatus(BizEnumType.UploadPermissionsStatus.CreatePermissions.getKey());
        uploadPermisssions.setApplicatId(userId);
        uploadPermisssions.setCreatedAt(new Date());
        if (uploadPermisssionsMapper.insert(uploadPermisssions) >= 1){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean checkPermissions(Long uploadId,Integer status) {
        QueryWrapper<UploadPermisssions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",uploadId);
        UploadPermisssions uploadPermisssions = uploadPermisssionsMapper.selectList(queryWrapper).get(0);
        if (uploadPermisssions == null
                || uploadPermisssions.getVersion() == BizEnumType.CommonStatus.Valid.getKey()
                || uploadId == null
                || status == null){
            return Boolean.FALSE;
        }else {
            UpdateWrapper<UploadPermisssions> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("version",BizEnumType.CommonStatus.Invalid.getKey());
            if (status == BizEnumType.UploadPermissionsStatus.Pass.getKey()){
                updateWrapper.set("status",BizEnumType.UploadPermissionsStatus.Pass.getKey());
            }else {
                updateWrapper.set("status",BizEnumType.UploadPermissionsStatus.Reject.getKey());
            }
            updateWrapper.eq("id",uploadId);
            this.update(updateWrapper);


        }
        return null;
    }
}
